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

import co.analisis2.rtf3.entidades.Producto;

public class ArchivoProductos {

	private static final String NOMBRE_ARCHIVO = "productos";
	private static final Path file = Paths.get(NOMBRE_ARCHIVO);
	private static final int LONGITUD_REGISTRO = 60;	
	private static final int NOMBRE_LONGITUD = 20;
	private static final int PRECIO_LONGITUD = 15;
	private static final int IDENTIFICACION_LONGITUD = 10;
	private static final int COLOR_LONGITUD = 10;
	private static final int EXISTENCIAS_LONGITUD = 5;
	
	private Map<String, Producto> cacheId;
	private Map<String, Producto> cacheNombre;

	public ArchivoProductos(){
		
		this.cacheId = new HashMap<String, Producto>();
		this.cacheNombre = new HashMap<String, Producto>();
		
		if(!Files.exists(file)){
			try{
				Files.createFile(file);
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		
	}
	
	public boolean guardar(Producto prod){
		
		String registro = parseString(prod);
		
		byte data[] = registro.getBytes();
		ByteBuffer out = ByteBuffer.wrap(data);	
		try (FileChannel fc = (FileChannel.open(file, APPEND))) {
			fc.write(out);
			this.cacheId.put(prod.getIdProducto(), prod);
			this.cacheNombre.put(prod.getNombre(), prod);
			return true;
		} catch (IOException x) {
			System.out.println("I/O Exception: " + x);
		}
		return false;
		
	}
	
	public boolean existePorId(String id){
		return this.cacheId.containsKey(id);
	}
	
	public boolean existePorNombre(String nombre){
		return this.cacheNombre.containsKey(nombre);
	}
	
	public Producto buscarPorId(String id){
		
		llenarCache();		
		Producto cliente = cacheId.get(id);		
		return cliente;
		
	}
	
	public Producto buscarPorNombre(String nombre){
		
		llenarCache();		
		Producto cliente = cacheNombre.get(nombre);		
		return cliente;
		
	}
	
	public List<Producto> obtenerTodos(){
		
		llenarCache();		
		List<Producto> productos = (List<Producto>) this.cacheId.values();		
		return productos;
		
	}
	
	private void llenarCache(){
		
		this.cacheId.clear();
		this.cacheNombre.clear();
		
		try (SeekableByteChannel sbc = Files.newByteChannel(file)) {
		    ByteBuffer buf = ByteBuffer.allocate(LONGITUD_REGISTRO);  
		    String encoding = System.getProperty("file.encoding");
		    while (sbc.read(buf) > 0) {
		        buf.rewind();
		        Producto prod = parseProducto(Charset.forName(encoding).decode(buf));
		        buf.flip();
		        cacheId.put(prod.getIdProducto(), prod);
		        cacheNombre.put(prod.getNombre(), prod);
		    }
		} catch (IOException x) {
		    System.out.println("caught exception: " + x);
		}
		
	}
	
	private Producto parseProducto(CharBuffer registro){
		
		String id = registro.subSequence(0, IDENTIFICACION_LONGITUD).toString().trim();
		registro.position(IDENTIFICACION_LONGITUD);
		registro = registro.slice();
				
		String nombre = registro.subSequence(0, NOMBRE_LONGITUD).toString().trim();
		registro.position(NOMBRE_LONGITUD);	
		registro = registro.slice();		
		
		double precio = Double.parseDouble(registro.subSequence(0, PRECIO_LONGITUD).toString().trim());
		registro.position(PRECIO_LONGITUD);
		registro = registro.slice();
		
		String color = registro.subSequence(0, COLOR_LONGITUD).toString().trim();
		registro.position(COLOR_LONGITUD);
		registro = registro.slice();
		
		int existencias = Integer.parseInt(registro.subSequence(0, EXISTENCIAS_LONGITUD).toString().trim());
		registro.position(EXISTENCIAS_LONGITUD);
		registro = registro.slice();
		
		Producto p = new Producto(nombre, id, precio, color, existencias);
		return p;
		
	}

	private String parseString(Producto prod) {
		
		StringBuilder registro = new StringBuilder(LONGITUD_REGISTRO);
		registro.append(completarCampoConEspacios(prod.getIdProducto(), IDENTIFICACION_LONGITUD));
		registro.append(completarCampoConEspacios(prod.getNombre(), NOMBRE_LONGITUD));
		registro.append(completarCampoConEspacios(prod.getPrecio() + "", PRECIO_LONGITUD));
		registro.append(completarCampoConEspacios(prod.getColor(), COLOR_LONGITUD));
		registro.append(completarCampoConEspacios(prod.getExistencias() + "", EXISTENCIAS_LONGITUD));
				
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
