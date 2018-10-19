package shapes; 
import java.awt.*;
import java.awt.geom.*;

/**
 * A line that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Natalia Palacios && Jonatan Gonzalez
 * @version 1.0  (27 September 2018)
 */


 
public class Line{

    private int xPosition1;
    private int yPosition1;
    private int xPosition2;
    private int yPosition2;
    private String color;
    private boolean isVisible;

    /**
     * Create a new line at default position with default color.
     */
    public Line(){
        xPosition1 = 0;
        yPosition1 = 0;
        xPosition2 = 1000;
        yPosition2 = 1000;
        color = "red";
        isVisible = false;
    }
    
    /**
     * Create a new line at default position with default color.
     * @param x1. Posicion x de la figura 1
     * @param y1. Posicion y de la figura 1
     * @param x2. Posicion x de la figura 2
     * @param y2. Posicion y de la figura 2
     */
    public Line(int x1, int y1, int x2, int y2){
        xPosition1 = x1;
        yPosition1 = y1;
        xPosition2 = x2;
        yPosition2 = y2;
        color = "red";
        isVisible = false;
    }
    
    /**
     * Da los valores a las posiciones de la linea.
     * @param x1. Posicion x de la figura 1
     * @param y1. Posicion y de la figura 1
     * @param x2. Posicion x de la figura 2
     * @param y2. Posicion y de la figura 2
     */
    public void setLine(int x1, int y1, int x2, int y2){
        xPosition1 = x1;
        yPosition1 = y1;
        xPosition2 = x2;
        yPosition2 = y2;
    }
    
    /**
     * Make this line visible. If it was already visible, do nothing.
     */
    public void makeVisible(){
        isVisible = true;
        draw();
    }
    
    /**
     * Make this line invisible. If it was already invisible, do nothing.
     */
    public void makeInvisible(){
        erase();
        isVisible = false;
    }
        
    /**
     * Change the color. 
     * @param color the new color. Valid colors are "red" and "green".
     */
    public void changeColor(String newColor){
        color = newColor;
        draw();
    }

    public String getColor(){
        return color;
    }
    
    /**
     * Draw the rectangle with current specifications on screen.
     */

    private void draw() {
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color, new java.awt.geom.Line2D.Double(xPosition1,yPosition1,xPosition2,yPosition2));
            canvas.wait(10);
        }
    }

    /**
     * Erase the line on screen.
     */
    private void erase(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
}

