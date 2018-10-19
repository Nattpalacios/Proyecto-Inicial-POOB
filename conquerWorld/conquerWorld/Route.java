package conquerWorld;
import java.awt.*;
import shapes.Line;
/**
 * Esta clase crea las rutas que relacionan a las naciones.
 * 
 * @author (Natalia Palacios and Jonatan Gonzalez) 
 * @version (27/09/2018)
 */
public class Route
{
    private int posX1;
    private int posY1;
    private int posX2;
    private int posY2;
    private int width;
    private int height;
    private int width2;
    private int height2;
    private String nacion1;
    private String nacion2;
    private int costoRuta;
    private Line linea;
    
    /**
     * Constructor de las rutas.
     * 
     * @param nations. Indica las naciones que une la ruta.
     * @param cost. Indica el costo para pasar por esa ruta.
     */
    public Route(String[] nations, int cost)
    {
        linea = new Line();
        costoRuta = cost;
        nacion1 = nations[0];
        nacion2 = nations[1];
    }
    
    /**
     * Asigna las medidas de las dos naciones que estaran relacionadas por la ruta.
     * 
     * @param posX1. Valor posicion x de la nacion 1.
     * @param posY1. Valor posicion y de la nacion 1.
     * @param width. Valor ancho de la nacion 1.
     * @param height. Valor alto de la nacion 1.
     * @param posX2. Valor posicion x de la nacion 2.
     * @param posY2. Valor posicion x de la nacion 2.
     * @param width. Valor ancho de la nacion 2.
     * @param height. Valor alto de la nacion 2.
     */
    public void shapeRoute(int posX1, int posY1, int posX2, int posY2, int width, int height, int width2, int height2, boolean cost)
    {
        this.posX1 = posX1;
        this.posY1 = posY1;
        this.posX2 = posX2;
        this.posY2 = posY2;
        this.width = width;
        this.height = height;
        this.height2 = height2;
        this.width2 = width2;
        linea.setLine(this.posX1, this.posY1, this.posX2, this.posY2);
        if(cost){
            linea.changeColor("green");
        }
        else{
            linea.changeColor("red");
        }
    }
    
    public void changeColor(String color){
        linea.changeColor(color);
    }
    
    public int getCostoRuta(){
        return costoRuta;
    }
    
    public String[] getNations(){
        String[] a = new String[2];
        a[0] = nacion1;
        a[1] = nacion2;
        return a;
    }
    
    /**
     * Vuelve visible una ruta.
     */
    public void visible(){
        linea.makeVisible();
    }
    
    /**
     * Vuelve invisible una ruta.
     */
    public void invisible(){
        linea.makeInvisible();
    }
}
