package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
public class PedidoTest{
	
	private Pedido pedido1;
	private Pedido pedido2;
	private ProductoMenu producto;
	private ProductoAjustado productoAjustado;
	
	@BeforeEach
	void setUp() {
		pedido1 = new Pedido("Pepito Perez", "Calle 100");
		pedido2 = new Pedido("Pepito Perez", "Calle 100");
		producto = new ProductoMenu("Hamburguesa", 15000);
	}
	
	@AfterEach
	void tearDown() throws Exception{
	}
	
	@Test
	void testGetPrecioNetBase() {
		pedido1.agregarProducto(producto);
		int expectedPriceIVA = (int)(15000*0.19);
		int expectedPrice = 15000 + expectedPriceIVA;
		assertEquals(expectedPrice, pedido1.getPrecioTotalPedido(), "Precio incorrecto");
	}
	
	@Test
	void testGetPrecioNetAjuste() {
		
		try {
			pedido2.agregarProducto(producto);
			productoAjustado = new ProductoAjustado(producto);
            Field agregadosField = ProductoAjustado.class.getDeclaredField("agregados");
            Field eliminadosField = ProductoAjustado.class.getDeclaredField("eliminados");

            agregadosField.setAccessible(true);
            eliminadosField.setAccessible(true);

            ArrayList<Ingrediente> agregados = new ArrayList<>(Arrays.asList(new Ingrediente("Queso", 3000)));
            ArrayList<Ingrediente> eliminados = new ArrayList<>(Arrays.asList(new Ingrediente("Cebolla", 0)));

            agregadosField.set(productoAjustado, agregados);
            eliminadosField.set(productoAjustado, eliminados);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            fail("Error de test por reflexión");
        }

        pedido2.agregarProducto(productoAjustado);
        int expectedPriceIVA = (int) (productoAjustado.getPrecio() * 0.19);
        int expectedPrice = expectedPriceIVA + 18000;
        assertEquals(expectedPrice, pedido2.getPrecioTotalPedido(), "Precio incorrecto");
        
    }
	
	@Test
	void testGetNombre() {
		assertEquals("Pepito Perez", pedido2.getNombreCliente(), "Nombre cliente incorrecto");
	}
	
	@Test
	void testGenerarFactura() {
		String expectedText1 = "Pepito Perez";
		String expectedText2 = "Calle 100";
		String expectedText3 = "Hamburguesa";
		String expectedText4 = "18000";
		String expectedText5 = "3420";
		String expectedText6 = "21420";
		
		assertTrue((pedido2.generarTextoFactura()).contains(expectedText1), "Factura incorrecta");
		assertTrue((pedido2.generarTextoFactura()).contains(expectedText2), "Factura incorrecta");
		assertTrue((pedido2.generarTextoFactura()).contains(expectedText3), "Factura incorrecta");
		assertTrue((pedido2.generarTextoFactura()).contains(expectedText4), "Factura incorrecta");
		assertTrue((pedido2.generarTextoFactura()).contains(expectedText5), "Factura incorrecta");
		assertTrue((pedido2.generarTextoFactura()).contains(expectedText6), "Factura incorrecta");
		
	}

}