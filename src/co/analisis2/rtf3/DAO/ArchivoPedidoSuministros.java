package co.analisis2.rtf3.DAO;

import static java.nio.file.StandardOpenOption.APPEND;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.analisis2.rtf3.entidades.PedidoSuministros;

public class ArchivoPedidoSuministros {

	private static final String NOMBRE_ARCHIVO = "pedidosSuministros";
	private static final Path file = Paths.get(NOMBRE_ARCHIVO);
	private static final File fileDescripciones = new File("descripciones");
	private static final File fileListas = new File("listas");
	private static final int LONGITUD_REGISTRO = 59;	
	private static final int CONSECUTIVO_LONGITUD = 10;
	private static final int IDENTIFICACION_LONGITUD = 10;
	private static final int MEDIO_PAGO_LONGITUD = 8;
	private static final int PRECIO_LONGITUD = 15;
	private static final int PAGADO_LONGITUD = 5;
	private static final int FECHA_LONGITUD = 10;
	
	private Map<String, PedidoSuministros> cache;

	public ArchivoPedidoSuministros(){
		
		this.cache = new HashMap<String, PedidoSuministros>();
		
		if(!Files.exists(file)){
			try{
				Files.createFile(file);
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		if(!fileDescripciones.exists()){
			try{
				fileDescripciones.createNewFile();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		if(!fileListas.exists()){
			try{
				fileListas.createNewFile();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		
	}
	
	public boolean guardar(PedidoSuministros ps){
		
		String registro = parseString(ps);
		
		byte data[] = registro.getBytes();
		ByteBuffer out = ByteBuffer.wrap(data);	
		try (FileChannel fc = (FileChannel.open(file, APPEND))) {
			fc.write(out);
			this.cache.put(ps.getConsecutivo(), ps);
			if(!guardarDescripcion(ps.getConsecutivo(), ps.getDescripcion()) || 
			   !guardarListas(ps.getConsecutivo(), ps.getListaProductos(), ps.getCantidad())){
				return false;
			}
			return true;
		} catch (IOException x) {
			System.out.println("I/O Exception: " + x);
		}
		return false;
		
	}
	
	public boolean existe(String consecutivo){
		return this.cache.containsKey(consecutivo);
	}
	
	public PedidoSuministros buscar(String consecutivo){
		
		llenarCache();		
		PedidoSuministros ps = cache.get(consecutivo);		
		return ps;
		
	}
	
	public List<PedidoSuministros> obtenerTodos(){
		
		llenarCache();		
		List<PedidoSuministros> ps = (List<PedidoSuministros>) this.cache.values();		
		return ps;
		
	}
	
	private boolean guardarDescripcion(String consecutivo, String descripcion){
		String registro = consecutivo + ";" + descripcion + "\n";
		
		try {
			FileWriter fw = new FileWriter(fileDescripciones, true);
			BufferedWriter bf = new BufferedWriter(fw);
			
			bf.write(registro);
			
			fw.close();
			bf.close();
			
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private boolean guardarListas(String consecutivo, List<String> productos, List<Integer> cantidades){
		String regProds = consecutivo + ";";
		String regCants = consecutivo + ";";
		for(int i = 0 ; i < productos.size() ; i++){
			regProds = regProds + productos.get(i) + "-";
			regCants = regCants + cantidades.get(i).intValue() + "-";
		}
		
		regProds = regProds + "\n";
		regCants = regCants + "\n";
		
		try{
			FileWriter fw = new FileWriter(fileListas, true);
			BufferedWriter bf = new BufferedWriter(fw);
			
			bf.write(regProds);
			bf.write(regCants);
			
			fw.close();
			bf.close();
			
			return true;
		}catch(IOException e){
			e.printStackTrace();
		}
		return false;
	}
	
	private String obtenerDescripcion(String consecutivo){

		try{
			FileReader fr = new FileReader(fileDescripciones);
			BufferedReader br = new BufferedReader(fr);
			
			String linea = br.readLine();
			String sub;
			while(linea != null){
				sub = linea.substring(0, CONSECUTIVO_LONGITUD).trim();
				
				if(sub.equals(consecutivo)){
					sub = linea.substring(CONSECUTIVO_LONGITUD + 1, linea.length()).trim();
					
					fr.close();
					br.close();
					
					return sub;
				}
				
				linea = br.readLine();
			}
			
			fr.close();
			br.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		return null;
		
	}
	
	private List<String> obtenerListaProductos(String consecutivo){
		
		try{
			FileReader fr = new FileReader(fileListas);
			BufferedReader br = new BufferedReader(fr);
			List<String> productos = new ArrayList<String>();
			
			String linea = br.readLine();
			String prod = "";
			String sub;
			while(linea != null){
				sub = linea.substring(0, CONSECUTIVO_LONGITUD).trim();
				
				if(sub.equals(consecutivo)){
					sub = linea.substring(CONSECUTIVO_LONGITUD + 1, linea.length()).trim();
					
					for(int i = 0 ; i < sub.length() ; i++){
						if(sub.charAt(i) != '-'){
							prod = prod + sub.charAt(i);
						}else{
							productos.add(prod);
							prod = "";
						}
					}
					
					fr.close();
					br.close();
					return productos;
				}
				
				linea = br.readLine();
			}
			fr.close();
			br.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		return null;
		
	}
	
	private List<Integer> obtenerListaCantidades(String consecutivo){
		
		try{
			FileReader fr = new FileReader(fileListas);
			BufferedReader br = new BufferedReader(fr);
			List<Integer> cantidades = new ArrayList<Integer>();
			
			String linea = br.readLine();
			String cant = "";
			String sub;
			while(linea != null){
				sub = linea.substring(0, CONSECUTIVO_LONGITUD).trim();
				
				if(sub.equals(consecutivo)){
					linea = br.readLine();
					sub = linea.substring(CONSECUTIVO_LONGITUD + 1, linea.length()).trim();
					
					for(int i = 0 ; i < sub.length() ; i++){
						if(sub.charAt(i) != '-'){
							cant = cant + sub.charAt(i);
						}else{
							cantidades.add(Integer.parseInt(cant));
							cant = "";
						}
					}
					
					fr.close();
					br.close();
					return cantidades;
				}
				
				linea = br.readLine();
			}
			fr.close();
			br.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		return null;
		
	}
	
	private void llenarCache(){
		
		this.cache.clear();
		
		try (SeekableByteChannel sbc = Files.newByteChannel(file)) {
		    ByteBuffer buf = ByteBuffer.allocate(LONGITUD_REGISTRO);  
		    String encoding = System.getProperty("file.encoding");
		    while (sbc.read(buf) > 0) {
		        buf.rewind();
		        PedidoSuministros ps = parsePedidoSuministros(Charset.forName(encoding).decode(buf));
		        buf.flip();
		        cache.put(ps.getConsecutivo(), ps);		        
		    }
		} catch (IOException x) {
		    System.out.println("caught exception: " + x);
		}
		
	}
	
	private PedidoSuministros parsePedidoSuministros(CharBuffer registro){
		
		String consecutivo = registro.subSequence(0, CONSECUTIVO_LONGITUD).toString().trim();
		registro.position(CONSECUTIVO_LONGITUD);
		registro = registro.slice();
				
		String id = registro.subSequence(0, IDENTIFICACION_LONGITUD).toString().trim();
		registro.position(IDENTIFICACION_LONGITUD);	
		registro = registro.slice();		
		
		String medioPago = registro.subSequence(0, MEDIO_PAGO_LONGITUD).toString().trim();
		registro.position(MEDIO_PAGO_LONGITUD);
		registro = registro.slice();
		
		Double precio = Double.parseDouble(registro.subSequence(0, PRECIO_LONGITUD).toString().trim());
		registro.position(PRECIO_LONGITUD);
		registro = registro.slice();
		
		LocalDate fecha = LocalDate.parse(registro.subSequence(0, FECHA_LONGITUD).toString().trim());
		registro.position(FECHA_LONGITUD);
		registro = registro.slice();
				
		boolean pagado = Boolean.parseBoolean(registro.subSequence(0, PAGADO_LONGITUD).toString().trim());
		registro.position(PAGADO_LONGITUD);	
		registro = registro.slice();		
				
		char estado = registro.charAt(0);
		
		String descripcion = obtenerDescripcion(consecutivo);
		List<String> productos = obtenerListaProductos(consecutivo);
		List<Integer> cantidades = obtenerListaCantidades(consecutivo);
		
		PedidoSuministros ps = new PedidoSuministros(id, descripcion, medioPago, precio, pagado, consecutivo, cantidades, estado, fecha, productos);
		return ps;
		
	}

	private String parseString(PedidoSuministros ps) {
		
		StringBuilder registro = new StringBuilder(LONGITUD_REGISTRO);
		registro.append(completarCampoConEspacios(ps.getConsecutivo(), CONSECUTIVO_LONGITUD));
		registro.append(completarCampoConEspacios(ps.getIdCliente(), IDENTIFICACION_LONGITUD));
		registro.append(completarCampoConEspacios(ps.getMedioPago(), MEDIO_PAGO_LONGITUD));
		registro.append(completarCampoConEspacios(ps.getPrecioTotal() + "", PRECIO_LONGITUD));
		registro.append(completarCampoConEspacios(ps.getFecha().toString(), FECHA_LONGITUD));
		registro.append(completarCampoConEspacios(ps.isPagado() + "",PAGADO_LONGITUD));
		registro.append(ps.getEstado());
				
		return registro.toString();
		
	}

	private String completarCampoConEspacios(String campo, int tamaño) {
		if(campo.length()>tamaño){
			campo = campo.substring(0, tamaño);
			return campo;
		}
		return String.format("%1$-" + tamaño + "s", campo);
	}
	
}
