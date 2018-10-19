package pruebas;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import conquerWorld.ConquerWorld;

/**
* The test class ConquerWorldTest04.
*
* @author (Benitez-Sanchez)
*/
public class ConquerWorldTest04
{
/**
* Default constructor for test class ConquerWorldTest04
*/
public ConquerWorldTest04()
{
}

/**
* Sets up the test fixture.
*
* Called before every test case method.
*/
@Before
public void setUp()
{
}


@Test
public void segunBSshouldNotEraseSteadyNation(){

    ConquerWorld cw = new ConquerWorld(200,200);
    
    cw.addNation("Rectangle", 50, "blue", new int[]{5,5}, 2, "steady");
    assertTrue(cw.ok());
    
    cw.removeNation("blue");
    assertFalse(cw.ok());
}

@Test
public void segunBSshouldNotEraseRouteOfSteadyNation(){
    ConquerWorld cw = new ConquerWorld(200,200);
    
    cw.addNation("Rectangle", 50, "blue", new int[]{5,5}, 2, "steady");
    assertTrue(cw.ok());
    
    cw.addNation("Triangle",60,"red",new int[]{15,15},2);
    assertTrue(cw.ok());
    
    cw.addRoute(new String[]{"blue","red"}, 10);
    assertTrue(cw.ok());
    
    cw.removeRoute(new String[]{"blue","red"});
    assertFalse(cw.ok());
}



/**
* Tears down the test fixture.
*
* Called after every test case method.
*/
@After
public void tearDown()
{
}
}