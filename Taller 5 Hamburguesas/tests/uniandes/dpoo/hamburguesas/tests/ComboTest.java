package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;


public class ComboTest{
	
	private Combo combo;
	private ProductoMenu item1;
	private ProductoMenu item2;
	
	@BeforeEach
	void setUp() {
		item1 = new ProductoMenu("Hamburguesa", 15000);
        item2 = new ProductoMenu("Papas medianas", 3000);
        ArrayList<ProductoMenu> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);
        combo = new Combo("Combo Familiar", 0.07, items);
	}
	
	@AfterEach
	
	void tearDown() throws Exception{
		
	}
	
	@Test
	
	void testGetPrecio() {
		int expectedPrice = (int) ((15000+3000)*(1-0.07));
		assertEquals(expectedPrice, combo.getPrecio(), "Precio incorrecto.");
	}
	void testGetNombre() {
		String expectedText = "Combo Familiar";
		assertEquals(expectedText, combo.getNombre(), "Nombre incorrecto");
	}
	void testGenerarTextoFactura() {
		String expectedText1 = "Combo Familiar";
		String expectedText2 = "0.07";
		String expectedText3 = "16740";
		
		assertTrue((combo.generarTextoFactura()).contains(expectedText1), "Factura incorrecta");
		assertTrue((combo.generarTextoFactura()).contains(expectedText2), "Factura incorrecta");
		assertTrue((combo.generarTextoFactura()).contains(expectedText3), "Factura incorrecta");
	}
}