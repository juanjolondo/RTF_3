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

import com.capiro.appWeb.entidades.UsuarioDTO;

public class ArchivoUsuariosDAO {
	
	private static final String NOMBRE_ARCHIVO = "usuarios";
	private static final Path file = Paths.get(NOMBRE_ARCHIVO);
	private static final int LONGITUD_REGISTRO = 51;	
	private static final int USUARIO_LONGITUD = 20;
	private static final int CLAVE_LONGITUD = 20;
	private static final int IDENTIFICACION_LONGITUD = 10;
	
	private Map<String, UsuarioDTO> cache;
	
	public ArchivoUsuariosDAO(){
		this.cache = new HashMap<String, UsuarioDTO>();
		
		if(!Files.exists(file)){
			try {
				Files.createFile(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean guardar(UsuarioDTO usuario){
		
		String registro = parseString(usuario);
		
		byte data[] = registro.getBytes();
		ByteBuffer out = ByteBuffer.wrap(data);	
		try (FileChannel fc = (FileChannel.open(file, APPEND))) {
			fc.write(out);
			this.cache.put(usuario.getUsuario(), usuario);
			return true;
		} catch (IOException x) {
			System.out.println("I/O Exception: " + x);
		}
		return false;
		
	}
	
	public boolean existe(String usuario){
		return this.cache.containsKey(usuario);
	}
	
	public UsuarioDTO buscar(String id){
		
		llenarCache();		
		UsuarioDTO usuario = cache.get(id);		
		return usuario;
		
	}
	
	public List<UsuarioDTO> obtenerTodos(){
		
		llenarCache();		
		List<UsuarioDTO> usuarios = (List<UsuarioDTO>) this.cache.values();		
		return usuarios;
		
	}
	
	private void llenarCache(){
		
		this.cache.clear();
		
		try (SeekableByteChannel sbc = Files.newByteChannel(file)) {
		    ByteBuffer buf = ByteBuffer.allocate(LONGITUD_REGISTRO);  
		    String encoding = System.getProperty("file.encoding");
		    while (sbc.read(buf) > 0) {
		        buf.rewind();
		        UsuarioDTO usuario = parseUsuario(Charset.forName(encoding).decode(buf));
		        buf.flip();
		        cache.put(usuario.getUsuario(), usuario);		        
		    }
		} catch (IOException x) {
		    System.out.println("caught exception: " + x);
		}
		
	}
	
	private UsuarioDTO parseUsuario(CharBuffer registro){
		
		String tipo = registro.subSequence(0, 1).toString();
		registro.position(1);
		registro = registro.slice();
		
		String usuario = registro.subSequence(0, USUARIO_LONGITUD).toString().trim();
		registro.position(USUARIO_LONGITUD);
		registro = registro.slice();
		
		String clave = registro.subSequence(0, CLAVE_LONGITUD).toString().trim();
		registro.position(CLAVE_LONGITUD);
		registro = registro.slice();
		
		String id = registro.subSequence(0, IDENTIFICACION_LONGITUD).toString().trim();
		registro.position(IDENTIFICACION_LONGITUD);
		registro = registro.slice();		
		
		UsuarioDTO u = new UsuarioDTO(id, usuario, clave, tipo);
		return u;
		
	}

	private String parseString(UsuarioDTO usuario) {
		StringBuilder registro = new StringBuilder(LONGITUD_REGISTRO);
		registro.append(usuario.getTipo());
		registro.append(completarCampoConEspacios(usuario.getUsuario(), USUARIO_LONGITUD));
		registro.append(completarCampoConEspacios(usuario.getClave(), CLAVE_LONGITUD));
		registro.append(completarCampoConEspacios(usuario.getIdUsuario(), IDENTIFICACION_LONGITUD));
				
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
