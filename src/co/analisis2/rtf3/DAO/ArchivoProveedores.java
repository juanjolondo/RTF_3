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

import co.analisis2.rtf3.entidades.Proveedor;

public class ArchivoProveedores {

	private static final String NOMBRE_ARCHIVO = "proveedores";
	private static final Path file = Paths.get(NOMBRE_ARCHIVO);
	private static final int LONGITUD_REGISTRO = 161;	
	private static final int NOMBRES_LONGITUD = 20;
	private static final int APELLIDOS_LONGITUD = 20;
	private static final int IDENTIFICACION_LONGITUD = 10;
	private static final int CORREO_LONGITUD = 40;
	private static final int TELEFONO_LONGITUD = 10;
	private static final int TIPO_ID_LONGITUD = 20;
	private static final int DIRECCION_LONGITUD = 40;
	
	private Map<String, Proveedor> cache;

	public ArchivoProveedores(){
		
		this.cache = new HashMap<String, Proveedor>();
		
		if(!Files.exists(file)){
			try{
				Files.createFile(file);
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		
	}
	
	public boolean guardar(Proveedor proveedor){
		
		String registro = parseString(proveedor);
		
		byte data[] = registro.getBytes();
		ByteBuffer out = ByteBuffer.wrap(data);	
		try (FileChannel fc = (FileChannel.open(file, APPEND))) {
			fc.write(out);
			this.cache.put(proveedor.getId(), proveedor);
			return true;
		} catch (IOException x) {
			System.out.println("I/O Exception: " + x);
		}
		return false;
		
	}
	
	public boolean existe(String id){
		return this.cache.containsKey(id);
	}
	
	public Proveedor buscar(String id){
		
		llenarCache();		
		Proveedor proveedor = cache.get(id);		
		return proveedor;
		
	}
	
	public List<Proveedor> obtenerTodos(){
		
		llenarCache();		
		List<Proveedor> proveedores = (List<Proveedor>) this.cache.values();		
		return proveedores;
		
	}
	
	private void llenarCache(){
		
		this.cache.clear();
		
		try (SeekableByteChannel sbc = Files.newByteChannel(file)) {
		    ByteBuffer buf = ByteBuffer.allocate(LONGITUD_REGISTRO);  
		    String encoding = System.getProperty("file.encoding");
		    while (sbc.read(buf) > 0) {
		        buf.rewind();
		        Proveedor prov = parseProveedor(Charset.forName(encoding).decode(buf));
		        buf.flip();
		        cache.put(prov.getId(), prov);		        
		    }
		} catch (IOException x) {
		    System.out.println("caught exception: " + x);
		}
		
	}
	
	private Proveedor parseProveedor(CharBuffer registro){
		
		String id = registro.subSequence(0, IDENTIFICACION_LONGITUD).toString().trim();
		registro.position(IDENTIFICACION_LONGITUD);
		registro = registro.slice();
				
		String nombres = registro.subSequence(0, NOMBRES_LONGITUD).toString().trim();
		registro.position(NOMBRES_LONGITUD);	
		registro = registro.slice();		
		
		String apellidos = registro.subSequence(0, APELLIDOS_LONGITUD).toString().trim();
		registro.position(APELLIDOS_LONGITUD);
		registro = registro.slice();
		
		String tipoId = registro.subSequence(0, TIPO_ID_LONGITUD).toString().trim();
		registro.position(TIPO_ID_LONGITUD);
		registro = registro.slice();
		
		String telefono = registro.subSequence(0, TELEFONO_LONGITUD).toString().trim();
		registro.position(TELEFONO_LONGITUD);
		registro = registro.slice();
				
		String correo = registro.subSequence(0, CORREO_LONGITUD).toString().trim();
		registro.position(CORREO_LONGITUD);	
		registro = registro.slice();		
		
		String direccion = registro.subSequence(0, DIRECCION_LONGITUD).toString().trim();
		registro.position(DIRECCION_LONGITUD);
		registro = registro.slice();
				
		char genero = registro.charAt(0);
		
		Proveedor c = new Proveedor(nombres, apellidos, id, genero, tipoId, telefono, direccion, correo);
		return c;
		
	}

	private String parseString(Proveedor prov) {
		
		StringBuilder registro = new StringBuilder(LONGITUD_REGISTRO);
		registro.append(completarCampoConEspacios(prov.getId(), IDENTIFICACION_LONGITUD));
		registro.append(completarCampoConEspacios(prov.getNombre(), NOMBRES_LONGITUD));
		registro.append(completarCampoConEspacios(prov.getApellido(), APELLIDOS_LONGITUD));
		registro.append(completarCampoConEspacios(prov.getTipoId(), TIPO_ID_LONGITUD));
		registro.append(completarCampoConEspacios(prov.getTelefono(), TELEFONO_LONGITUD));
		registro.append(completarCampoConEspacios(prov.getCorreo(),CORREO_LONGITUD));
		registro.append(completarCampoConEspacios(prov.getDireccion(), DIRECCION_LONGITUD));
		registro.append(prov.getGenero());
				
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
