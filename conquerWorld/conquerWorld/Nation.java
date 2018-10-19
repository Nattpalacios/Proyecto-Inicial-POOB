package conquerWorld;
import shapes.Rectangle;
import shapes.Triangle;
import shapes.Circle;
/**
 * Esta clase crea las naciones del mundo.
 * 
 * @author (Natalia Palacios and Jonatan Gonzalez) 
 * @version (30/08/2018)
 */
public class Nation
{
    public static double PI=3.1416;
    private String shape;
    private Rectangle nacion;
    private Triangle nacion2;
    private Circle nacion3;
    private int posX;
    private int posY;
    private int armadasNecesitadas;
    private int ejercito;
    private int alto;
    private int ancho;
    private double ladoSquare;
    private double diametro;
    private double ladoTriangle;
    private String color;
    private int area;

    /**
     * Crea las naciones.
     * 
     * @param shape. Indica el tipo de figura que va a tomar la nacion.
     * @param posX. Indica la posicion X donde se va a insertar la nueva nacion.
     * @param posY. Indica la posicion X donde se va a insertar la nueva nacion.
     * @param area. Indica el area de la nacion.
     * @param color. Indica el color que va a tomar la nacion.
     * @param armiesNeeded. Indica la cantidad de ejercitos que se nececita en la nacion.
     */
    public Nation(String shape, int posX, int posY,int area, String color,int armiesNeeded)
    {
        armadasNecesitadas = armiesNeeded;
        ejercito = 0;
        if (shape.equals("Square")){
            shapeSquare(area,color,posX,posY);
        }
        else if (shape.equals("Rectangle")){
            shapeRectangle(area,color,posX,posY);
        }
        else if(shape.equals("Triangle")){
            shapeTriangle(area,color,posX,posY);
        }
        else if(shape.equals("Circle")){
            shapeCircle(area,color,posX,posY);
        }
        this.area = area;
        this.shape = shape;
        this.color = color;
        this.posX= posX;
        this.posY= posY;
    }
    
    /**
     * Obtiene la cantidad de ejercito que necesita la nacion.
     * 
     * @return armadasNecesitadas. Retorna la cantidad de ejercito que necesita la nacion. 
     */
    public int getArmiesNeeded(){
        return armadasNecesitadas;
    }
    
    /**
     * Obtiene la cantidad de ejercitos que tiene la nacion.
     * 
     * @return ejercito. Retorna los ejercitos que tiene la nacion.
     */
    public int getEjercito(){
        return ejercito;
    }
    
    /**
     * Suma al ejercito de la nacion.
     */
    public void sumaEjercito(){
        ejercito += 1;
    }
    
    /**
     * Resta al ejercito de la nacion.
     */
    public void restaEjercito(){
        ejercito -= 1;
    }
    
    /**
     * Elimina el ejercito de la nacion.
     */
    public void quitaEjercito(){
        ejercito = 0;
    }
    
    /**
     * Obtiene el tipo de figura.
     * 
     * @return shape. Retorna el tipo de figura que tomara la nacion. 
     */
    public String getShape(){
        return shape;
    }
    
    /**
     * Obtiene la Posicion X de la figura.
     * 
     * @return posX. Retorna la posicion en X de la nacion. 
     */
    public int getPosX(){
        return posX;
    }
    
    /**
     * Obtiene la Posicion Y de la figura.
     * 
     * @return posY. Retorna la posicion en Y de la nacion. 
     */
    public int getPosY(){
        return posY;
    }
    
    /**
     * Obtiene el ancho de la figura.
     * 
     * @return ancho. Retorna el ancho de la nacion. 
     */
    public int getWidth(){
        return ancho;
    }
    
    /**
     * Obtiene el alto de la figura.
     * 
     * @return alto. Retorna la altura de la nacion. 
     */
    public int getHeight(){
        return alto;
    }
    
    /**
     * Obtiene el valor entero del lado del cuadrado.
     * 
     * @return ladoSquare. Retorna el valor del lado de la nacion (Si es un cuadrado). 
     */
    public int getLadoSquare(){
        return (int)ladoSquare;
    }
    
    /**
     * Obtiene el valor entero del lado del triangulo.
     * 
     * @return ladoTriangle. Retorna el valor del lado de la nacion (Si es un triangulo). 
     */
    public int getLadoTriangle(){
        return (int)ladoTriangle;
    }
    
    /**
     * Obtiene el valor entero del Diametro.
     * 
     * @return diametro. Retorna el valor del diametro de la nacion (Si es un circulo). 
     */
    public int getDiametro(){
        return (int)diametro;
    }
    
    /**
     * Obtiene el el color de la nacion.
     * 
     * @return color. Retorna el color de la nacion. 
     */
    public String getColor(){
        return color;
    }
    
    /**
     * Crear un Cuadrado.
     * 
     * @param area. Indica el area de la nueva nacion (Si la nacion es de tipo cuadrado).
     * @param color. Indica el color de la nacion (Si la nacion es de tipo cuadrado).
     * @param posX. Indica la poscion en X donde se va a ubicar de la nacion (Si la nacion es de tipo cuadrado).
     * @param posY. Indica la poscion en Y donde se va a ubicar de la nacion (Si la nacion es de tipo cuadrado).
     */
    private void shapeSquare(int area, String color, int posX, int posY)
    {
        nacion = new Rectangle();
        ladoSquare = Math.sqrt(area);
        nacion.changeSize((int)ladoSquare,(int)ladoSquare);
        nacion.moveHorizontal(posX);
        nacion.moveVertical(posY);
        nacion.changeColor(color);
    }
    
    /**
     * Crear un Rectangulo.
     * 
     * @param area. Indica el area de la nueva nacion (Si la nacion es de tipo rectangulo).
     * @param color. Indica el color de la nacion (Si la nacion es de tipo rectangulo).
     * @param posX. Indica la poscion en X donde se va a ubicar de la nacion (Si la nacion es de tipo rectangulo).
     * @param posY. Indica la poscion en Y donde se va a ubicar de la nacion (Si la nacion es de tipo rectangulo).
     */
    private void shapeRectangle(int area, String color, int posX, int posY)
    {
        nacion = new Rectangle();
        alto = 0;
        ancho = 0;
        while(alto*ancho < area/2){
            alto += 1;
            ancho += 1;
        }
         while(alto*ancho < area){
            ancho += 1;
        }
        nacion.changeSize(alto,ancho);
        nacion.moveHorizontal(posX);
        nacion.moveVertical(posY);
        nacion.changeColor(color);
    }
    
    /**
     * Crear un Circulo.
     * 
     * @param area. Indica el area de la nueva nacion (Si la nacion es de tipo circulo).
     * @param color. Indica el color de la nacion (Si la nacion es de tipo circulo).
     * @param posX. Indica la poscion en X donde se va a ubicar de la nacion (Si la nacion es de tipo circulo).
     * @param posY. Indica la poscion en Y donde se va a ubicar de la nacion (Si la nacion es de tipo circulo).
     */
    private void shapeCircle(int area, String color, int posX, int posY){
        nacion3 = new Circle();
        diametro = Math.sqrt(area/PI)*2;
        nacion3.changeSize((int)diametro);
        nacion3.moveHorizontal(posX);
        nacion3.moveVertical(posY);
        nacion3.changeColor(color);
    }
    
    /**
     * Crear un Triangulo.
     * 
     * @param area. Indica el area de la nueva nacion (Si la nacion es de tipo triangulo).
     * @param color. Indica el color de la nacion (Si la nacion es de tipo triangulo).
     * @param posX. Indica la poscion en X donde se va a ubicar de la nacion (Si la nacion es de tipo triangulo).
     * @param posY. Indica la poscion en Y donde se va a ubicar de la nacion (Si la nacion es de tipo triangulo).
     */
    private void shapeTriangle(int area, String color, int posX, int posY)
    {
        nacion2 = new Triangle();
        ladoTriangle = Math.sqrt(2*area);
        nacion2.changeSize((int)ladoTriangle,(int)ladoTriangle);
        nacion2.moveHorizontal(posX);
        nacion2.moveVertical(posY);
        nacion2.changeColor(color);
    }
    
    /**
     * Hace visibles las naciones.
     * 
     * @param shape. Indica el tipo de figura la cual va ha tomar la nacion.
     */
    public void visible(String shape)
    {
        if(shape.equals("Circle")){
            nacion3.makeVisible();
        }
        else if(shape.equals("Triangle")){
            nacion2.makeVisible();
        }
        else{
            nacion.makeVisible();
        }
    }
   
    
    /**
     * Hace invisibles las naciones.
     * 
     * @param shape. Indica el tipo de figura la cual va ha tomar la nacion.
     */
    public void invisible(String shape)
    {
        if(shape.equals("Circle")){
            nacion3.makeInvisible();
        }
        else if(shape.equals("Triangle")){
            nacion2.makeInvisible();
        }
        else{
            nacion.makeInvisible();
        }
    }
    
    public String toString(){
        String s = "";
        s += "Shape: " + shape +"\n";
        s += "Color: " + color +"\n";
        s += "Area: " + area +"\n";
        s += "Armies needed: " + ejercito +"\n";
        return s;
    }
}
