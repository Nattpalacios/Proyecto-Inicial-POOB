package pruebas;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import conquerWorld.ConquerWorld;

/**
 * The test class ConquerWorldTest02.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ConquerWorldTest02 {
    /**
     * Default constructor for test class ConquerWorldTest02
     */
    public ConquerWorldTest02() {
    }

    @Test
    public void shouldConsultNations() {
        ConquerWorld cw = new ConquerWorld(200, 200);
        
        cw.addNation("Triangle", 30, "blue", new int[]{9, 8}, 3);
        assertTrue(cw.ok());
          
        cw.addNation("Circle", 20, "red", new int[]{18, 16}, 3);
        assertTrue(cw.ok());
        
        String[] nations = cw.nations();
        
        assertArrayEquals(nations, new String[]{"blue", "red"});
    }
    
    @Test
    public void shouldConsultRoutesFromNation() {
        ConquerWorld cw = new ConquerWorld(200, 200);

        cw.addNation("Triangle", 30, "blue", new int[] { 9, 8 }, 3);
        cw.addNation("Rectangle", 10, "red", new int[] { 18, 16 }, 2);
        assertTrue(cw.ok());

        cw.addRoute(new String[] { "blue", "red" }, 10);
        assertTrue(cw.ok());

        cw.addNation("Circle", 20, "yellow", new int[] { 27, 24 }, 1);
        assertTrue(cw.ok());

        cw.addRoute(new String[] { "blue", "yellow" }, 6);
        assertTrue(cw.ok());
        String[] rutas = {"red", "yellow"};
        assertArrayEquals(cw.routes("blue"), rutas);
    }
    
    @Test
    public void shouldDoAGoodMovement2() {
        ConquerWorld cw = new ConquerWorld(200, 200);
        
        cw.addNation("Triangle", 10, "blue", new int[]{9, 8}, 1);
        cw.addNation("Rectangle", 10, "red", new int[]{18, 8}, 1);
        assertTrue(cw.ok());
   
        cw.addRoute(new String[]{"blue", "red"}, 1);
        
        cw.addCash(200);
        cw.addArmy("blue");
        cw.addArmy("blue");
        
        cw.moveArmy("red", "blue");
        assertFalse(cw.ok());
        
        cw.moveArmy();
        assertTrue(cw.ok());
        
        cw.moveArmy("red", "blue");
        assertTrue(cw.ok());
    }
    
    @Test
    public void shouldDoAGoodMovement3() {
        ConquerWorld cw = new ConquerWorld(200, 200);
        
        cw.addNation("Triangle", 10, "blue", new int[]{9, 8}, 1);
        cw.addNation("Rectangle", 10, "red", new int[]{18, 8}, 1);
        cw.addNation("Circle", 10, "yellow", new int[]{18, 18}, 1);
        assertTrue(cw.ok());
   
        cw.addRoute(new String[]{"blue", "red"}, 1);
        cw.addRoute(new String[]{"red", "yellow"}, 1);
        cw.addRoute(new String[]{"blue", "yellow"}, 1);
        
        cw.addCash(200);
        cw.addArmy("blue");
        cw.addArmy("blue");
        cw.addArmy("red");
        
        cw.moveArmy("yellow", "red");
        assertFalse(cw.ok());
        
        cw.moveArmy();
        assertTrue(cw.ok());
        
        cw.moveArmy("yellow", "red");
        assertTrue(cw.ok());
    }
    
    //Students cases
    
    @Test
    public void segunGSDeberiaPasar() {
        ConquerWorld cw = new ConquerWorld(300, 300);

        cw.addNation("Rectangle", 30, "yellow", new int[] { 0, 0 }, 2);
        assertTrue(cw.ok());
        cw.addNation("Circle", 60, "magenta", new int[] { 50, 50 }, 5);
        assertTrue(cw.ok());
        cw.addNation("Rectangle", 30, "blue", new int[] { 120, 120 }, 3);
        assertTrue(cw.ok());
        cw.addNation("Triangle", 30, "red", new int[] { 200, 200 }, 1);

        assertEquals(cw.nations(), new String[] { "blue", "magenta", "red", "yellow" });
    }

    @Test
    public void segunBSdeberiaDevolverTodasLasNacionesConQuienTieneUnaRutaEnOrdenAlfabetico() {
        ConquerWorld cw = new ConquerWorld(300, 300);

        cw.addNation("Rectangle", 20, "red", new int[] { 10, 10 }, 6);
        assertTrue(cw.ok());

        cw.addNation("Circle", 30, "blue", new int[] { 30, 30 }, 6);
        assertTrue(cw.ok());

        cw.addNation("Triangle", 45, "yellow", new int[] { 50, 50 }, 7);
        assertTrue(cw.ok());

        cw.addRoute(new String[] { "red", "blue" }, 20);
        assertTrue(cw.ok());

        cw.addRoute(new String[] { "red", "yellow" }, 40);
        assertTrue(cw.ok());

        String rs[] = cw.routes("red");
        assertEquals(rs[0], "blue");
        assertEquals(rs[1], "yellow");

    }

    @Test
    public void segunDJShouldDoAMove() {
        ConquerWorld cw = new ConquerWorld(200, 200);

        cw.addNation("Triangle", 30, "blue", new int[] { 9, 8 }, 3);
        cw.addNation("Rectangle", 10, "red", new int[] { 18, 8 }, 2);
        cw.addNation("Pentagon", 10, "yellow", new int[] { 60, 60 }, 4);
        cw.addArmies(new String[] { "blue", "red" });

        cw.addArmy("blue");
        cw.addArmy("blue");
        cw.addArmy("blue");
        cw.addCash(50);
        cw.addArmies(new String[] { "blue", "red" });

        cw.addRoute(new String[] { "blue", "red" }, 2);
        cw.addRoute(new String[] { "blue", "yellow" }, 2);

        cw.moveArmy();
        assertTrue(cw.ok());
    }

    @Test
    public void segunJJShouldNotCreateRoute() {

        ConquerWorld cw = new ConquerWorld(200, 200);

        cw.addNation("TriAnGle", 10, "blue", new int[] { 9, 8 }, 3);
        cw.addNation("RECTANGLE", 10, "red", new int[] { 18, 8 }, 2);

        cw.addRoute(new String[] { "blue", "red" }, 10);

        cw.addArmies(new String[] { "red", "red" });

        assertTrue(cw.ok());

        cw.addRoute(new String[] { "blue", "blue" }, 10);

        assertFalse(cw.ok());

    }

    @Test
    public void segunGVDeberiaConsultarRutasExistentes() {
        ConquerWorld cw = new ConquerWorld(200, 200);

        cw.addNation("Triangle", 30, "blue", new int[] { 9, 8 }, 3);
        cw.addNation("Rectangle", 10, "red", new int[] { 18, 16 }, 2);
        assertTrue(cw.ok());

        cw.addRoute(new String[] { "blue", "red" }, 10);
        assertTrue(cw.ok());

        cw.addNation("Circle", 20, "yellow", new int[] { 27, 24 }, 1);
        assertTrue(cw.ok());

        cw.addRoute(new String[] { "blue", "yellow" }, 6);
        assertTrue(cw.ok());
        String[][] rutas = { { "blue", "red" }, { "blue", "yellow" } };
        assertEquals(cw.routes(), rutas);
    }

    @Test
    public void segundGJDeberiaHacerZoom() {
        ConquerWorld cw = new ConquerWorld(200, 200);

        cw.addNation("Triangle", 30, "blue", new int[] { 9, 8 }, 3);
        cw.addNation("Rectangle", 10, "red", new int[] { 18, 16 }, 2);
        assertTrue(cw.ok());

        cw.addRoute(new String[] { "blue", "red" }, 10);
        assertTrue(cw.ok());

        cw.zoom('+');
        assertTrue(cw.ok());

    }
}