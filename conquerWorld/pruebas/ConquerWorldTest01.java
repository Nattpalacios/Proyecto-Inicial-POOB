package pruebas;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import conquerWorld.ConquerWorld;
/**
 * The test class ConquerWorldTest01.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ConquerWorldTest01
{
    /**
     * Default constructor for test class ConquerWorldTest01
     */
    public ConquerWorldTest01() {
    }
    
    @Test
    public void shouldAddNations() {
        ConquerWorld cw = new ConquerWorld(200, 200);
        
        cw.addNation("Triangle", 30, "blue", new int[]{9, 8}, 3);
        assertTrue(cw.ok());
          
        cw.addNation("Circle", 20, "red", new int[]{18, 16}, 3);
        assertTrue(cw.ok());
    }
    
    @Test
    public void shouldNotAddNationsWithTheSameColor() {
        ConquerWorld cw = new ConquerWorld(200, 200);
        
        cw.addNation("Triangle", 30, "blue", new int[]{9, 8}, 3);
        assertTrue(cw.ok());
          
        cw.addNation("Circle", 20, "blue", new int[]{18, 16}, 3);
        assertFalse(cw.ok());
    }
    
    @Test
    public void shouldNotOverlapNations() {
        ConquerWorld cw = new ConquerWorld(200, 200);
        
        cw.addNation("Triangle", 30, "blue", new int[]{9, 8}, 3);
        
        cw.addNation("Circle", 20, "red", new int[]{9, 8}, 3);
        assertFalse(cw.ok());
        
        cw.addNation("Circle", 20, "red", new int[]{18, 16}, 3);
        
        cw.addNation("Rectangle", 15, "yellow", new int[]{18, 16}, 2);
        assertFalse(cw.ok());
    }
    
    @Test
    public void shoudAddRoutes() {
        ConquerWorld cw = new ConquerWorld(200, 200);
        
        cw.addNation("Triangle", 30, "blue", new int[]{9, 8}, 3);
        cw.addNation("Rectangle", 10, "red", new int[]{18, 16}, 2);
        assertTrue(cw.ok());
        
        cw.addRoute(new String[]{"blue", "red"}, 10);
        assertTrue(cw.ok());
        
        cw.addNation("Circle", 20, "yellow", new int[]{27, 24}, 1);
        assertTrue(cw.ok());
        
        cw.addRoute(new String[]{"blue", "yellow"}, 6);
        assertTrue(cw.ok());
    }
    
    @Test
    public void shoudNotDuplicateRoutes() {
        ConquerWorld cw = new ConquerWorld(200, 200);
        
        cw.addNation("Triangle", 30, "blue", new int[]{9, 8}, 3);
        cw.addNation("Rectangle", 10, "red", new int[]{18, 16}, 2);
        assertTrue(cw.ok());
        
        cw.addRoute(new String[]{"blue", "red"}, 10);
        assertTrue(cw.ok());
        
        cw.addRoute(new String[]{"blue", "red"}, 5);
        assertFalse(cw.ok());
    }
    
    @Test
    public void shoudNotAddRoutesToNonExistentNations() {
        ConquerWorld cw = new ConquerWorld(200, 200);
        
        cw.addNation("Triangle", 30, "blue", new int[]{9, 8}, 3);
        cw.addNation("Rectangle", 10, "red", new int[]{18, 16}, 2);
    
        cw.addRoute(new String[]{"blue", "yellow"}, 6);
        assertFalse(cw.ok());
    }
    
    @Test
    public void shouldAddArmy() {
        ConquerWorld cw = new ConquerWorld(200, 200);
        
        cw.addNation("Triangle", 10, "blue", new int[]{9, 8}, 3);
        cw.addArmy("blue");
        assertTrue(cw.ok());
        
        cw.addArmy("blue");
        assertTrue(cw.ok());    
    }
    
    @Test
    public void shouldNotAddArmyToNonExistentNations() {
        ConquerWorld cw = new ConquerWorld(200, 200);
        
        cw.addArmy("blue");
        assertFalse(cw.ok());
        
        cw.addArmy("red");
        assertFalse(cw.ok());
    }
    
    @Test
    public void shouldAddArmies() {
        ConquerWorld cw = new ConquerWorld(200, 200);
    
        cw.addNation("Triangle", 30, "blue", new int[]{9, 8}, 3);    
        cw.addNation("Rectangle", 10, "red", new int[]{18, 8}, 2);
        cw.addArmies(new String[]{"blue", "red"});
        assertTrue(cw.ok());
        
        cw.addArmies(new String[]{"blue", "red"});
        assertTrue(cw.ok());    
    }
    
    @Test
    public void shouldNotAddArmiesToNonExistentNations() {
        ConquerWorld cw = new ConquerWorld(200, 200);
        
        cw.addArmies(new String[]{"blue", "red"});
        assertFalse(cw.ok());
        
        cw.addNation("Triangle", 10, "blue", new int[]{9, 8}, 3);
        cw.addArmies(new String[]{"blue", "red"});
        assertFalse(cw.ok());
    }
    
    @Test
    public void shouldRemoveNation() {
        ConquerWorld cw = new ConquerWorld(200, 200);
        cw.removeNation("blue");
        assertFalse(cw.ok());
        
        cw.addNation("Triangle", 30, "blue", new int[]{9, 8}, 3);
        cw.addNation("Circle", 20, "red", new int[]{18, 16}, 3);
        assertTrue(cw.ok());
        
        cw.removeNation("blue");
        assertTrue(cw.ok());
        
        cw.removeNation("red");
        assertTrue(cw.ok());
    }
    
    @Test
    public void shouldNotRemoveNonExistentNations() {
        ConquerWorld cw = new ConquerWorld(200, 200);
        cw.removeNation("blue");
        assertFalse(cw.ok());
        
        cw.addNation("Triangle", 30, "blue", new int[]{9, 8}, 3);
        cw.addNation("Circle", 20, "red", new int[]{18, 16}, 3);
        
        cw.removeNation("yellow");
        assertFalse(cw.ok());
        
        cw.removeNation("red");
        assertTrue(cw.ok());
        
        cw.removeNation("red");
        assertFalse(cw.ok());
    }
        
    @Test
    public void shouldNotRemoveNationsWithRoutes() {
        ConquerWorld cw = new ConquerWorld(200, 200);
    
        cw.addNation("Triangle", 30, "blue", new int[]{9, 8}, 3);
        cw.addNation("Circle", 20, "red", new int[]{18, 16}, 3);
        cw.addRoute(new String[]{"blue", "red"}, 10);
        
        cw.removeNation("red");
        assertFalse(cw.ok());
        
        cw.removeNation("blue");
        assertFalse(cw.ok());

    }
    
    @Test
    public void shouldRemoveRoute() {
        ConquerWorld cw = new ConquerWorld(200, 200);
        
        cw.addNation("Triangle", 30, "blue", new int[]{9, 8}, 3);
        cw.addNation("Rectangle", 10, "red", new int[]{18, 8}, 2);
        cw.addNation("Circle", 20, "yellow", new int[]{18, 18}, 1);
        assertTrue(cw.ok());
        
        cw.addRoute(new String[]{"blue", "red"}, 10);
        cw.addRoute(new String[]{"blue", "yellow"}, 6);
        
        cw.removeRoute(new String[]{"blue", "yellow"});
        assertTrue(cw.ok());
        
        cw.removeRoute(new String[]{"blue", "red"});
        assertTrue(cw.ok());
    }
    
    @Test
    public void shouldNotRemoveNonExistentRoute() {
        ConquerWorld cw = new ConquerWorld(200, 200);
        
        cw.addNation("Triangle", 30, "blue", new int[]{9, 8}, 3);
        cw.addNation("Rectangle", 10, "red", new int[]{18, 8}, 2);
        cw.addNation("Circle", 20, "yellow", new int[]{18, 18}, 1);
        assertTrue(cw.ok());
        
        cw.removeRoute(new String[]{"blue", "red"});
        assertFalse(cw.ok());

        cw.addRoute(new String[]{"blue", "red"}, 10);
        cw.addRoute(new String[]{"blue", "yellow"}, 6);
        assertTrue(cw.ok());
        
        cw.removeRoute(new String[]{"blue", "yellow"});
        assertTrue(cw.ok());
        
        cw.removeRoute(new String[]{"blue", "yellow"});        
        assertFalse(cw.ok()); 
    }
    
    @Test
    public void shouldRemoveArmies() {
        ConquerWorld cw = new ConquerWorld(200, 200);

        cw.addNation("Triangle", 10, "blue", new int[]{9, 8}, 3);
        cw.addNation("Rectangle", 10, "red", new int[]{18, 8}, 2);
        cw.addNation("Circle", 10, "yellow", new int[]{18, 18}, 1);
        assertTrue(cw.ok());
        
        cw.addArmy("blue");
        assertTrue(cw.ok());
        cw.addArmy("blue");
        assertTrue(cw.ok());
        
        cw.addArmy("red");
        assertTrue(cw.ok());
        cw.addArmy("red");
        assertTrue(cw.ok());
        
        cw.removeArmies("blue");
        assertTrue(cw.ok());
        cw.removeArmies("red");
        assertTrue(cw.ok());
    }
    
    
    @Test
    public void shouldNotRemoveNonExistentArmies() {
        ConquerWorld cw = new ConquerWorld(200, 200);
        
        cw.addNation("Triangle", 10, "blue", new int[]{9, 8}, 3);
        cw.addNation("Rectangle", 10, "red", new int[]{18, 8}, 2);
        assertTrue(cw.ok());
        
        cw.removeArmies("blue");
        assertFalse(cw.ok());
 
        cw.addArmy("red");
        assertTrue(cw.ok());       
        cw.removeArmies("red");
        assertTrue(cw.ok());
        cw.removeArmies("red");
        assertFalse(cw.ok());        
    }
    
    @Test
    public void shouldNotRemoveArmiesToOfNonExistentNations() {
        ConquerWorld cw = new ConquerWorld(200, 200);
        
        cw.removeArmies("blue");
        assertFalse(cw.ok());

        cw.addNation("Rectangle", 10, "red", new int[]{18, 8}, 2);
        assertTrue(cw.ok());
        
        cw.removeNation("red");
        assertTrue(cw.ok());
        
        cw.removeArmies("red");
        assertFalse(cw.ok());
    }
    
    @Test
    public void souldMoveArmyUnidirectional() {
        ConquerWorld cw = new ConquerWorld(200, 200);
        
        cw.addNation("Triangle", 10, "blue", new int[]{9, 8}, 3);
        cw.addNation("Rectangle", 10, "red", new int[]{18, 8}, 2);
        cw.addNation("Circle", 10, "yellow", new int[]{18, 18}, 1);
        assertTrue(cw.ok());
   
        cw.addRoute(new String[]{"blue", "red"}, 10);
        cw.addRoute(new String[]{"red", "yellow"}, 5);
        cw.addRoute(new String[]{"blue", "yellow"}, 6);
        
        cw.addCash(20);
        cw.addArmy("blue");
        cw.moveArmy("blue", "red");
        assertTrue(cw.ok());       
        cw.moveArmy("red", "yellow");
        assertTrue(cw.ok());

        cw.addCash(11);
        cw.addArmy("blue");        
        cw.moveArmy("blue", "yellow"); 
    }    
    
    @Test
    public void souldMoveArmyBidirectional() {
        ConquerWorld cw = new ConquerWorld(200, 200);
        
        cw.addNation("Triangle", 10, "blue", new int[]{9, 8}, 3);
        cw.addNation("Rectangle", 10, "red", new int[]{18, 8}, 2);
        cw.addNation("Circle", 10, "yellow", new int[]{18, 18}, 1);
        assertTrue(cw.ok());
   
        cw.addRoute(new String[]{"blue", "red"}, 10);
        cw.addRoute(new String[]{"red", "yellow"}, 5);
        cw.addRoute(new String[]{"blue", "yellow"}, 6);
        
        cw.addCash(20);
        cw.addArmy("blue");
        cw.moveArmy("blue", "red");
        assertTrue(cw.ok());       
        cw.moveArmy("red", "blue");
        assertTrue(cw.ok());
    
        cw.addCash(11);
        cw.moveArmy("blue", "yellow");
        assertTrue(cw.ok());
        cw.moveArmy("yellow", "red");
        assertTrue(cw.ok());
    }
    
    @Test
    public void souldNotMoveNonExistentArmy() {
        ConquerWorld cw = new ConquerWorld(200, 200);
        
        cw.addNation("Triangle", 10, "blue", new int[]{9, 8}, 3);
        cw.addNation("Rectangle", 10, "red", new int[]{18, 8}, 2);
        cw.addRoute(new String[]{"blue", "red"}, 10);        
        assertTrue(cw.ok());
        
        cw.addCash(100);
        cw.moveArmy("blue", "red");   
        assertFalse(cw.ok());
        
        cw.addArmy("blue");
        assertTrue(cw.ok());
        cw.moveArmy("blue", "red");
        assertTrue(cw.ok());
        cw.moveArmy("blue", "red");
        assertFalse(cw.ok()); 
    }
    
    @Test
    public void souldNotMoveArmyOnANonExistentRoute() {
        ConquerWorld cw = new ConquerWorld(200, 200);
        
        cw.addNation("Triangle", 10, "blue", new int[]{9, 8}, 3);
        cw.addNation("Rectangle", 10, "red", new int[]{18, 8}, 2);
        cw.addNation("Circle", 10, "yellow", new int[]{18, 18}, 1);
        assertTrue(cw.ok());
        
        cw.addCash(100);
        cw.addArmy("blue");
        cw.moveArmy("blue", "red");
        assertFalse(cw.ok());
        cw.moveArmy("yellow", "red");
        assertFalse(cw.ok());
        
        cw.addRoute(new String[]{"blue", "red"}, 10);
        cw.addRoute(new String[]{"red", "yellow"}, 5);
        cw.addRoute(new String[]{"blue", "yellow"}, 6);
        
        cw.moveArmy("red", "yellow");
        assertFalse(cw.ok());
    }
    
    @Test
    public void souldNotMoveArmyIfNotCash() {
        ConquerWorld cw = new ConquerWorld(200, 200);
        
        cw.addNation("Triangle", 10, "blue", new int[]{9, 8}, 3);
        cw.addNation("Rectangle", 10, "red", new int[]{18, 8}, 2);
        cw.addRoute(new String[]{"blue", "red"}, 10);
        assertTrue(cw.ok());
        
        cw.addArmy("blue");
        cw.moveArmy("blue", "red");
        assertFalse(cw.ok());
       
        cw.addCash(5);
        cw.addArmy("blue");
        cw.moveArmy("blue", "red");
        assertFalse(cw.ok());  
    }
    
    @Test
    public void shouldVerifyRouteBetweenTwoCitiesBeUnique() {
        ConquerWorld cw = new ConquerWorld(200, 200);
        
        cw.addNation("Triangle", 10, "blue", new int[]{9, 8}, 3);
        cw.addNation("Rectangle", 10, "red", new int[]{18, 8}, 2);
        cw.addNation("Circle", 10, "yellow", new int[]{18, 18}, 1);
        assertTrue(cw.ok());
        
        assertTrue(cw.okRoute(new String[]{"blue", "red"}));        
        cw.addRoute(new String[]{"blue", "red"}, 10);
        assertTrue(cw.ok());
        
        assertTrue(cw.okRoute(new String[]{"red", "yellow"}));               
        cw.addRoute(new String[]{"red", "yellow"}, 5);
        assertTrue(cw.ok());
        

        assertFalse(cw.okRoute(new String[]{"blue", "yellow"})); 
        cw.addRoute(new String[]{"blue", "yellow"}, 6);
        assertTrue(cw.ok());
    }
    
    @Test
    public void shouldVerifyRoutesBeUnique() {
        ConquerWorld cw = new ConquerWorld(200, 200);
        
        cw.addNation("Triangle", 10, "blue", new int[]{9, 8}, 3);
        cw.addNation("Rectangle", 10, "red", new int[]{18, 8}, 2);
        cw.addNation("Circle", 10, "yellow", new int[]{18, 18}, 1);
        assertTrue(cw.ok());
        
        assertTrue(cw.okRoutes());     
        cw.addRoute(new String[]{"blue", "red"}, 10);
        assertTrue(cw.ok());
        
        assertTrue(cw.okRoutes());               
        cw.addRoute(new String[]{"red", "yellow"}, 5);
        assertTrue(cw.ok());
        

        assertTrue(cw.okRoutes()); 
        cw.addRoute(new String[]{"blue", "yellow"}, 6);
        assertTrue(cw.ok());
        
        assertFalse(cw.okRoutes());        
    }   
}