package co.analisis2.rtf3.DAO;

import static java.nio.file.StandardOpenOption.APPEND;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.analisis2.rtf3.entidades.Factura;

public class ArchivoFacturas {

	private static final String NOMBRE_ARCHIVO = "facturas";
	private static final Path file = Paths.get(NOMBRE_ARCHIVO);
	private static final int LONGITUD_REGISTRO = 40;	
	private static final int CONSECUTIVO_LONGITUD = 10;
	private static final int NIT_LONGITUD = 20;
	private static final int IDENTIFICACION_LONGITUD = 10;

	private Map<String, Factura> cache;

	public ArchivoFacturas(){
		
		this.cache = new HashMap<String, Factura>();
		
		if(!Files.exists(file)){
			try{
				Files.createFile(file);
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		
	}
	
	public boolean guardar(Factura f){
		
		String registro = parseString(f);
		
		byte data[] = registro.getBytes();
		ByteBuffer out = ByteBuffer.wrap(data);	
		try (FileChannel fc = (FileChannel.open(file, APPEND))) {
			fc.write(out);
			this.cache.put(f.getConsecutivo(), f);
			return true;
		} catch (IOException x) {
			System.out.println("I/O Exception: " + x);
		}
		return false;
		
	}
	
	public boolean existe(String consecutivo){
		return this.cache.containsKey(consecutivo);
	}
	
	public Factura buscar(String consecutivo){
		
		llenarCache();		
		Factura factura = cache.get(consecutivo);		
		return factura;
		
	}
	
	public List<Factura> obtenerTodos(){
		
		llenarCache();		
		List<Factura> facturas = (List<Factura>) this.cache.values();		
		return facturas;
		
	}
	
	private void llenarCache(){
		
		this.cache.clear();
		
		try (SeekableByteChannel sbc = Files.newByteChannel(file)) {
		    ByteBuffer buf = ByteBuffer.allocate(LONGITUD_REGISTRO);  
		    String encoding = System.getProperty("file.encoding");
		    while (sbc.read(buf) > 0) {
		        buf.rewind();
		        Factura factura = parseFactura(Charset.forName(encoding).decode(buf));
		        buf.flip();
		        cache.put(factura.getConsecutivo(), factura);		        
		    }
		} catch (IOException x) {
		    System.out.println("caught exception: " + x);
		}
		
	}
	
	private Factura parseFactura(CharBuffer registro){
		
		String consecutivo = registro.subSequence(0, CONSECUTIVO_LONGITUD).toString().trim();
		registro.position(CONSECUTIVO_LONGITUD);
		registro = registro.slice();
		
		String id = registro.subSequence(0, IDENTIFICACION_LONGITUD).toString().trim();
		registro.position(IDENTIFICACION_LONGITUD);
		registro = registro.slice();
				
		String nit = registro.subSequence(0, NIT_LONGITUD).toString().trim();
		registro.position(NIT_LONGITUD);	
		registro = registro.slice();		
		
		Factura c = new Factura(consecutivo, nit, id);
		return c;
		
	}

	private String parseString(Factura f) {
		
		StringBuilder registro = new StringBuilder(LONGITUD_REGISTRO);
		registro.append(completarCampoConEspacios(f.getConsecutivo(), CONSECUTIVO_LONGITUD));
		registro.append(completarCampoConEspacios(f.getIdCliente(), IDENTIFICACION_LONGITUD));
		registro.append(completarCampoConEspacios(f.getNit(), NIT_LONGITUD));
				
		return registro.toString();
		
	}

	private String completarCampoConEspacios(String campo, int tamaño) {
		if(campo.length()>tamaño){
			campo=campo.substring(0, tamaño);
			return campo;
		}
		return String.format("%1$-" + tamaño + "s", campo);
	}
	
}
