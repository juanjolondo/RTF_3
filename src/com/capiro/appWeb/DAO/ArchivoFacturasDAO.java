package com.capiro.appWeb.DAO;

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

import com.capiro.appWeb.entidades.FacturaDTO;

public class ArchivoFacturasDAO {

	private static final String NOMBRE_ARCHIVO = "facturas";
	private static final Path file = Paths.get(NOMBRE_ARCHIVO);
	private static final int LONGITUD_REGISTRO = 40;	
	private static final int CONSECUTIVO_LONGITUD = 10;
	private static final int NIT_LONGITUD = 20;
	private static final int IDENTIFICACION_LONGITUD = 10;

	private Map<String, FacturaDTO> cache;

	public ArchivoFacturasDAO(){
		
		this.cache = new HashMap<String, FacturaDTO>();
		
		if(!Files.exists(file)){
			try{
				Files.createFile(file);
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		
	}
	
	public boolean guardar(FacturaDTO f){
		
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
	
	public FacturaDTO buscar(String consecutivo){
		
		llenarCache();		
		FacturaDTO factura = cache.get(consecutivo);		
		return factura;
		
	}
	
	public List<FacturaDTO> obtenerTodos(){
		
		llenarCache();		
		List<FacturaDTO> facturas = (List<FacturaDTO>) this.cache.values();		
		return facturas;
		
	}
	
	private void llenarCache(){
		
		this.cache.clear();
		
		try (SeekableByteChannel sbc = Files.newByteChannel(file)) {
		    ByteBuffer buf = ByteBuffer.allocate(LONGITUD_REGISTRO);  
		    String encoding = System.getProperty("file.encoding");
		    while (sbc.read(buf) > 0) {
		        buf.rewind();
		        FacturaDTO factura = parseFactura(Charset.forName(encoding).decode(buf));
		        buf.flip();
		        cache.put(factura.getConsecutivo(), factura);		        
		    }
		} catch (IOException x) {
		    System.out.println("caught exception: " + x);
		}
		
	}
	
	private FacturaDTO parseFactura(CharBuffer registro){
		
		String consecutivo = registro.subSequence(0, CONSECUTIVO_LONGITUD).toString().trim();
		registro.position(CONSECUTIVO_LONGITUD);
		registro = registro.slice();
		
		String id = registro.subSequence(0, IDENTIFICACION_LONGITUD).toString().trim();
		registro.position(IDENTIFICACION_LONGITUD);
		registro = registro.slice();
				
		String nit = registro.subSequence(0, NIT_LONGITUD).toString().trim();
		registro.position(NIT_LONGITUD);	
		registro = registro.slice();		
		
		FacturaDTO c = new FacturaDTO(consecutivo, nit, id);
		return c;
		
	}

	private String parseString(FacturaDTO f) {
		
		StringBuilder registro = new StringBuilder(LONGITUD_REGISTRO);
		registro.append(completarCampoConEspacios(f.getConsecutivo(), CONSECUTIVO_LONGITUD));
		registro.append(completarCampoConEspacios(f.getIdCliente(), IDENTIFICACION_LONGITUD));
		registro.append(completarCampoConEspacios(f.getNit(), NIT_LONGITUD));
				
		return registro.toString();
		
	}

	private String completarCampoConEspacios(String campo, int tama�o) {
		if(campo.length()>tama�o){
			campo=campo.substring(0, tama�o);
			return campo;
		}
		return String.format("%1$-" + tama�o + "s", campo);
	}
	
}
