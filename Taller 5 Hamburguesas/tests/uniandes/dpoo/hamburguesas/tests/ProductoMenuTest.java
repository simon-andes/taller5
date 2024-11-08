package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoMenuTest
{
	private ProductoMenu productoMenu;
	
	
	@BeforeEach
	void setUp()
		{
		productoMenu = new ProductoMenu("Hamburguesa", 15000);
		
		}
	@AfterEach
    void tearDown( ) throws Exception
    {
    }
	
	@Test
	void testGetPrecio()
		{
			assertEquals(15000, productoMenu.getPrecio(), "Precio incorrecto.");
			
		}
	
	@Test
	void testGetNombre() 
		{
			assertEquals("Hamburguesa",productoMenu.getNombre(), "Nombre incorrecto.");
		}
	
	@Test
	void testGenerarTextoFactura() {
		String expectedText1 = "Hamburguesa";
		String expectedText2 = "15000";
		
		assertTrue((productoMenu.generarTextoFactura()).contains(expectedText1), "Factura incorrecta");
		assertTrue((productoMenu.generarTextoFactura()).contains(expectedText2), "Factura incorrecta");
	}
}