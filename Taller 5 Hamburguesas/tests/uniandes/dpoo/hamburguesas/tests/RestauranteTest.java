package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;

import uniandes.dpoo.hamburguesas.mundo.Restaurante;
import uniandes.dpoo.hamburguesas.excepciones.NoHayPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.excepciones.YaHayUnPedidoEnCursoException;


public class RestauranteTest{
	private Restaurante restaurante;
	
	
	@BeforeEach
	void setUp() {
		restaurante = new Restaurante();
	}
	
	@AfterEach
	
	void tearDown() throws Exception{
	}
	
	@Test
	void testIniciarPedido(){
		try {
			restaurante.iniciarPedido("Fabio", "Calle 150");
			assertNotNull(restaurante.getPedidos(), "No hay pedido en curso");
		}catch(YaHayUnPedidoEnCursoException e) {
			fail("Error de código, ya hay un pedido en la primera inicialización");
		}
	}
	
	@Test
	void testIniciarPedidoConExcepcion() {
		
		try {
			restaurante.iniciarPedido("Fabio", "Calle 150");
			assertThrows(YaHayUnPedidoEnCursoException.class, ()-> {
				restaurante.iniciarPedido("Andrés", "Carrera 11");
			}, "Ya hay un pedido en curso");
		}catch (YaHayUnPedidoEnCursoException e) {
			fail("Error de código, ya hay un pedido en la priemra inicialización");
				
			}
		}
		
	@Test
	void testCerrarYGuardarConPedido() {		
		try {
			restaurante.iniciarPedido("Fabio", "Calle 150");
			restaurante.cerrarYGuardarPedido();
		}catch(NoHayPedidoEnCursoException | IOException | YaHayUnPedidoEnCursoException e){
			fail("Error: No se cierra y guarda un pedido correctamente");
			
		}
		
	}
	
	
	@Test
	void testCerrarYGuardarSinPedido() {
		assertThrows(NoHayPedidoEnCursoException.class, () ->{
			restaurante.cerrarYGuardarPedido();
		}, "Error: debería retornar excepción de NoHayPedidoEnCurso");
	}
	
	
	
	
	}