package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoAjustadoTest{
	private ProductoMenu baseProduct;
	private ProductoAjustado productoAjustado;
	
	@BeforeEach
	void setUp() {
		baseProduct = new ProductoMenu("Hamburguesa", 15000);
		productoAjustado = new ProductoAjustado(baseProduct);
		
		try { 
        Field agregadosField = ProductoAjustado.class.getDeclaredField("agregados");
        
        Field eliminadosField = ProductoAjustado.class.getDeclaredField("eliminados");

        agregadosField.setAccessible(true);
        eliminadosField.setAccessible(true);

        ArrayList<Ingrediente> agregados = new ArrayList<>();
        
        agregados.add(new Ingrediente("Queso", 3000));
        
        agregadosField.set(productoAjustado, agregados);

        ArrayList<Ingrediente> eliminados = new ArrayList<>();
        eliminados.add(new Ingrediente("Cebolla", 0));
        eliminadosField.set(productoAjustado, eliminados);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			e.printStackTrace();
			fail("Error de test por reflexión");
		}
    }
	@AfterEach
    void tearDown() throws Exception
    {
    }
	
	@Test
	void testGetPrecio() {
		int expectedPrice = 15000 + 3000;
		assertEquals(expectedPrice, productoAjustado.getPrecio(), "Precio ajustado incorrecto.");

	}
	
	@Test
	void testGenerarTextoFactura() {
		String expectedText1 = "Hamburguesa";
		String expectedText2 = "Queso";
		String expectedText3 = "15000";
		String expectedText4 = "3000";
		
		assertTrue((productoAjustado.generarTextoFactura()).contains(expectedText1), "Factura incorrecta");
		assertTrue((productoAjustado.generarTextoFactura()).contains(expectedText2), "Factura incorrecta");
		assertTrue((productoAjustado.generarTextoFactura()).contains(expectedText3), "Factura incorrecta");
		assertTrue((productoAjustado.generarTextoFactura()).contains(expectedText4), "Factura incorrecta");
	}
	
}