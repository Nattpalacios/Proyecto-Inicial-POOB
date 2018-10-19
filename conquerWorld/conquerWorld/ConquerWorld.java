package conquerWorld;
import java.util.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.PriorityQueue;
import shapes.Rectangle;
/**
 * Crea un mundo.
 * 
 * @author (Natalia Palacios and Jonatan Gonzalez) 
 * @version (27/09/2018)
 */

public class ConquerWorld
{
    public static double PI=3.1416;
    
    private Rectangle tablero;
    private int ancho;
    private int alto;
    private boolean isVisible;
    private TreeMap <String,Nation> naciones;
    private int presupuesto;
    private Rectangle dinero;
    private final int grosor;
    private double altura;
    private int posX;
    private int posY;
    private boolean containsKey;
    private int[] position = new int[2];
    private int diameter;
    private TreeMap <String,Route> rutas; 
    private int anchoF;
    private int altoF;
    private int anchoF2;
    private int altoF2;
    private boolean isOkRoute;
    private boolean isOkRoutes;
    private boolean isOkAll;
    private HashMap<String,String> padres;
    private HashMap<String,Integer> rangos;
    private HashMap<String,HashMap<String,Integer>> grafoD;
    
    /**
     * Constructor de Objetos de la clase ConquerWorld
     *  @param maxX. Indica el tamaño en X del tablero.
     *  @param maxY. Indica el tamaño en Y del tablero.
     */
    public ConquerWorld(int maxX, int maxY)
    {
        tablero = new Rectangle();
        ancho = maxX;
        alto = maxY;
        grosor = maxX/20;
        tablero.changeColor("black");
        tablero.changeSize(alto,ancho);
        isVisible = false;
        naciones = new TreeMap<String,Nation>();
        presupuesto = 0;
        dinero = new Rectangle();
        marcoPresupuesto();
        rutas = new TreeMap<String,Route>();
        isOkRoute = true;
        isOkRoutes = true;
        isOkAll = true;
        
    }
    
    /**
     * El metodo Adiciona efectivo al presupuesto de batalla.
     * 
     * @param value. Valor a adicionar al presupuesto de batalla.
     */
    public void addCash(int value)
    {
        presupuesto += value;
        marcoPresupuesto();
        Iterator getRutas = rutas.keySet().iterator();
        while(getRutas.hasNext()){
            Route ruta = rutas.get(getRutas.next());
            if(ruta.getCostoRuta() <= presupuesto){
                ruta.changeColor("green");
            }
            else{
                ruta.changeColor("red");
            }
        }
        isOkAll = true;
    }
    
    /**
     * El metodo crea un marco en la parte izquierda del tablero, que aumenta con el ingreso del dinero.
     */
    private void marcoPresupuesto(){
        altura = presupuesto*0.15;
        dinero.changeColor("verdeMoneda");
        dinero.changeSize((int)altura,grosor);
        if(isVisible){
            dinero.makeVisible();
        }
        else{
            dinero.makeInvisible();
        }
    }
    
    /**
     * El metodo Adiciona una nación. 
     * 
     * @param shape. Forma en que desea adicionar la nacion. Figuras validas: "Rectangle", "Square", "Circle", "Triangle".
     * @param area. Area que desea que tenga la nacion.
     * @param color. Color que desea que tenga la nacion. Colores validos: "red","blue","yellow","green","magenta","white","gray","lightGray","darkGray","pink","orange","cyan","salmon","aquamarine","purple","chocolate","beige","lila","brown".
     * @param posX. Posicion X en la que quiere ubbicar la nacion.
     * @param posY. Posicion Y en la que quiere ubbicar la nacion.
     * @param armiesNeeded. Ejercitos que necesita en la nacion.
     */
    public void addNation(String shape,int area, String color,int[] position,int armiesNeeded)
    {
        boolean colorYaEsta = false;
        if(naciones.containsKey(color)){
            colorYaEsta = true;
        }
        String mayus = shape.substring(0,1).toUpperCase();
        String minus = shape.substring(1,shape.length()).toLowerCase();
        shape = mayus+minus;
        if(!shape.equals("Rectangle") && !shape.equals("Square") && !shape.equals("Circle") && !shape.equals("Triangle")){
            shape = "Rectangle";
        }
        posX = position[0]; posY = position[1]; boolean rango = false;
        if(shape.equals("Rectangle")){
            rango = addNationRectangle(rango,posX,posY,area);
        }else if(shape.equals("Square")){
            rango = addNationSquare(rango,posX,posY,area);
        }else if(shape.equals("Triangle")){
            rango = addNationTriangle(rango,posX,posY,area);
        }else if(shape.equals("Circle")){
            rango = addNationCircle(rango,posX,posY,area);
        }
        if(!colorYaEsta && rango){
            Nation nuevaNacion = new Nation(shape,posX,posY,area,color,armiesNeeded);
            naciones.put(color,nuevaNacion);
            if(isVisible){
                nuevaNacion.visible(shape);
            }else{
                nuevaNacion.invisible(shape);
            }
            isOkAll = true;
        }else{
            isOkAll = false;
        }
    }
    
    /**
     * El metodo Adiciona una nación. 
     * 
     * @param shape. Forma en que desea adicionar la nacion. Figuras validas: "Rectangle", "Square", "Circle", "Triangle".
     * @param area. Area que desea que tenga la nacion.
     * @param color. Color que desea que tenga la nacion. Colores validos: "red","blue","yellow","green","magenta","white","gray","lightGray","darkGray","pink","orange","cyan","salmon","aquamarine","purple","chocolate","beige","lila","brown".
     * @param posX. Posicion X en la que quiere ubbicar la nacion.
     * @param posY. Posicion Y en la que quiere ubbicar la nacion.
     * @param armiesNeeded. Ejercitos que necesita en la nacion.
     */
    public void addNation(String shape,int area, String color,int[] position,int armiesNeeded,String type)
    {
        boolean colorYaEsta = false;
        if(naciones.containsKey(color)){
            colorYaEsta = true;
        }
        String mayus = shape.substring(0,1).toUpperCase();
        String minus = shape.substring(1,shape.length()).toLowerCase();
        shape = mayus+minus;
        if(!shape.equals("Rectangle") && !shape.equals("Square") && !shape.equals("Circle") && !shape.equals("Triangle")){
            shape = "Rectangle";
        }
        posX = position[0]; posY = position[1]; boolean rango = false;
        if(shape.equals("Rectangle")){
            rango = addNationRectangle(rango,posX,posY,area);
        }else if(shape.equals("Square")){
            rango = addNationSquare(rango,posX,posY,area);
        }else if(shape.equals("Triangle")){
            rango = addNationTriangle(rango,posX,posY,area);
        }else if(shape.equals("Circle")){
            rango = addNationCircle(rango,posX,posY,area);
        }
        if(!colorYaEsta && rango){
            Nation nuevaNacion;
            if(type.equals("normal")){
                nuevaNacion = new Nation(shape,posX,posY,area,color,armiesNeeded);
            }else if(type.equals("steady")){
                nuevaNacion = new NationSteady(shape,posX,posY,area,color,armiesNeeded);
            }else{
                nuevaNacion = new Nation(shape,posX,posY,area,color,armiesNeeded);
            }
            naciones.put(color,nuevaNacion);
            if(isVisible){
                nuevaNacion.visible(shape);
            }else{
                nuevaNacion.invisible(shape);
            }
            isOkAll = true;
        }else{
            isOkAll = false;
        }
    }
    
    
    /**
     * El metodo Adiciona una nación (Si es de tipo Rectangulo). 
     * 
     * @param rango. Indica true si no se sobrepasa del rango del tablero. 
     * @param posX. Indica la posicion X en la que quiere ubicar la nacion.
     * @param posY. Indica la posicion Y en la que quiere ubicar la nacion.
     * @param area. Area que desea que tenga la nacion.
     * 
     * @return rango. Retorna true si la nacion no se sobrepone a ninguna otra y no se sale del rango del tablero.
     */
    private boolean addNationRectangle(boolean rango, int posX, int posY, int area){
        rango = estaEnElRangoRectangle(posX,posY,area);
        int[] medidas = calcularMedidasRectangle(area);
        int height = medidas[1];
        int width = medidas[0];
        Iterator getNaciones = naciones.keySet().iterator();
        while(getNaciones.hasNext()){
            Nation nacion = naciones.get(getNaciones.next());
            if(nacion.getShape().equals("Rectangle")){
                rango = rango && sonDisyuntosRectangle(posX,posY,width,height,nacion.getPosX(),nacion.getPosY(),nacion.getWidth(),nacion.getHeight());
            }
            else if(nacion.getShape().equals("Circle")){
                rango = rango && sonDisyuntosCircle(nacion.getPosX(),nacion.getPosY(),nacion.getDiametro(),posX,posY,width,height);
            }
            else if(nacion.getShape().equals("Square")){
                rango = rango && sonDisyuntosRectangle(posX,posY,width,height,nacion.getPosX(),nacion.getPosY(),nacion.getLadoSquare(),nacion.getLadoSquare());
            }
            else if(nacion.getShape().equals("Triangle")){
                rango = rango && sonDisyuntosRectangle(posX,posY,width,height,nacion.getPosX(),nacion.getPosY(),nacion.getLadoTriangle(),nacion.getLadoTriangle());
            }
        }
        
        return rango;
    }
    
    /**
     * El metodo Adiciona una nación (Si es de tipo Cuadrado). 
     * 
     * @param rango. Indica true si no se sobrepasa del rango del tablero.
     * @param posX. Indica la posicion X en la que quiere ubicar la nacion.
     * @param posY. Indica la posicion Y en la que quiere ubicar la nacion.
     * @param area. Area que desea que tenga la nacion.
     * 
     * @return rango. Retorna true si la nacion no se sobrepone a ninguna otra y no se sale del rango del tablero.
     */
    private boolean addNationSquare(boolean rango, int posX, int posY, int area){
        rango = estaEnElRangoSquare(posX,posY,area);
        int medida = calcularMedidasSquare(area);
        Iterator getNaciones = naciones.keySet().iterator();
        while(getNaciones.hasNext()){
            Nation nacion = naciones.get(getNaciones.next());
            if(nacion.getShape().equals("Rectangle")){
                rango = rango && sonDisyuntosRectangle(posX,posY,medida,medida,nacion.getPosX(),nacion.getPosY(),nacion.getWidth(),nacion.getHeight());
            }
            else if(nacion.getShape().equals("Circle")){
                rango = rango && sonDisyuntosCircle(nacion.getPosX(),nacion.getPosY(),nacion.getDiametro(),posX,posY,medida,medida);
            }
            else if(nacion.getShape().equals("Square")){
                rango = rango && sonDisyuntosRectangle(posX,posY,medida,medida,nacion.getPosX(),nacion.getPosY(),nacion.getLadoSquare(),nacion.getLadoSquare());
            }
            else if(nacion.getShape().equals("Triangle")){
                rango = rango && sonDisyuntosRectangle(posX,posY,medida,medida,nacion.getPosX(),nacion.getPosY(),nacion.getLadoTriangle(),nacion.getLadoTriangle());
            }
        }
        
        return rango;
    }
    
    /**
     * El metodo Adiciona una nacion (Si es de tipo Triangulo). 
     * 
     * @param rango. Indica true si no se sobrepasa del rango del tablero.
     * @param posX. Indica la posicion X en la que quiere ubicar la nacion.
     * @param posY. Indica la posicion Y en la que quiere ubicar la nacion.
     * @param area. Area que desea que tenga la nacion.
     * 
     * @return rango. Retorna true si la nacion no se sobrepone a ninguna otra y no se sale del rango del tablero.
     */
    private boolean addNationTriangle(boolean rango, int posX, int posY, int area){
        rango = estaEnElRangoTriangle(posX,posY,area);
        int medida = calcularMedidasTriangle(area);
        Iterator getNaciones = naciones.keySet().iterator();
        while(getNaciones.hasNext()){
            Nation nacion = naciones.get(getNaciones.next());
            if(nacion.getShape().equals("Rectangle")){
                rango = rango && sonDisyuntosRectangle(posX,posY,medida,medida,nacion.getPosX(),nacion.getPosY(),nacion.getWidth(),nacion.getHeight());
            }
            else if(nacion.getShape().equals("Circle")){
                rango = rango && sonDisyuntosCircle(nacion.getPosX(),nacion.getPosY(),nacion.getDiametro(),posX,posY,medida,medida);
            }
            else if(nacion.getShape().equals("Square")){
                rango = rango && sonDisyuntosRectangle(posX,posY,medida,medida,nacion.getPosX(),nacion.getPosY(),nacion.getLadoSquare(),nacion.getLadoSquare());
            }
            else if(nacion.getShape().equals("Triangle")){
                rango = rango && sonDisyuntosRectangle(posX,posY,medida,medida,nacion.getPosX(),nacion.getPosY(),nacion.getLadoTriangle(),nacion.getLadoTriangle());
            }
        }
        
        return rango;
    }
    
    /**
     * El metodo Adiciona una nacion (Si es de tipo Circulo). 
     * 
     * @param rango. Indica true si no se sobrepasa del rango del tablero.
     * @param posX. Indica la posicion X en la que quiere ubicar la nacion.
     * @param posY. Indica la posicion Y en la que quiere ubicar la nacion.
     * @param area. Area que desea que tenga la nacion.
     * 
     * @return rango. Retorna true si la nacion no se sobrepone a ninguna otra y no se sale del rango del tablero.
     */
    private boolean addNationCircle(boolean rango, int posX, int posY, int area){
        rango = estaEnElRangoCircle(posX,posY,area);
        int medida = calcularMedidasCircle(area);
        Iterator getNaciones = naciones.keySet().iterator();
        while(getNaciones.hasNext()){
            Nation nacion = naciones.get(getNaciones.next());
            rango = rango && sonDisyuntosCircle(posX,posY,medida,nacion.getPosX(),nacion.getPosY(),nacion.getWidth(),nacion.getHeight());
            if(nacion.getShape().equals("Rectangle")){
                rango = rango && sonDisyuntosCircle(posX,posY,medida,nacion.getPosX(),nacion.getPosY(),nacion.getWidth(),nacion.getHeight());
            }
            else if(nacion.getShape().equals("Circle")){
                rango = rango && sonDisyuntosCircleCircle(posX,posY,medida,nacion.getPosX(),nacion.getPosY(),nacion.getDiametro());
            }
            else if(nacion.getShape().equals("Square")){
                rango = rango && sonDisyuntosCircle(posX,posY,medida,nacion.getPosX(),nacion.getPosY(),nacion.getLadoSquare(),nacion.getLadoSquare());
            }
            else if(nacion.getShape().equals("Triangle")){
                rango = rango && sonDisyuntosCircle(posX,posY,medida,nacion.getPosX(),nacion.getPosY(),nacion.getLadoTriangle(),nacion.getLadoTriangle());
            }
        }
        
        return rango;
    }
    
    /**
     * El metodo Calcula las medidas de una nación (Si es de tipo Rectangulo).
     * 
     * @param area. Area que desea que tenga la nacion.
     * 
     * @return ret. Retorna la lista con los valores de la altura y el ancho del rectangulo.
     */
    private int[] calcularMedidasRectangle(int area){
        int height = 0;
        int width = 0;
        while(height*width < area/2){
            height += 1;
            width += 1;
        }
         while(height*width < area){
            width += 1;
        }
        int[] ret = new int[2];
        ret[0] = width; ret[1] = height;
        return ret;
    }
    
    /**
     * El metodo Calcula las medidas de una nación (Si es de tipo Cuadrado).
     * 
     * @param area. Area que desea que tenga la nacion.
     * 
     * @return lado. Retorna el valor del lado del cuadrado.
     */
    private int calcularMedidasSquare(int area){
        double lado = Math.sqrt(area);
        return (int)lado;
    }
    
    /**
     * El metodo Calcula las medidas de una nación (Si es de tipo Triangulo).
     * 
     * @param area. Area que desea que tenga la nacion.
     * 
     * @return lado. Retorna el valor del lado del triangulo.
     */
    private int calcularMedidasTriangle(int area){
        double lado = Math.sqrt(2*area);
        return (int)lado;
    }
    
    /**
     * El metodo Calcula las medidas de una nación (Si es de tipo Circulo).
     * 
     * @param area. Area que desea que tenga la nacion.
     * 
     * @return diametro. Retorna el valor del diametro del circulo.
     */
    private int calcularMedidasCircle(int area){
        double diametro = Math.sqrt(area/PI)*2;
        return (int)diametro;
    }
    
     /**
     * Verifica que el objeto a ingresar este en el rango adecuado de la pantalla (Si el objeto es de tipo Rectangle).
     * 
     * @param posX. Valor de la posicion x de la nacion.
     * @param posY. Valor de la posicion y de la nacion.
     * @param area. Valor del area de la nacion.
     * 
     * @return flag. Retorna el valor booleano, dependiendo si el objeto esta en el rango adecuado o no.
     */
    private boolean estaEnElRangoRectangle(int posX, int posY, int area)
    {
        boolean flag = true;
        int[] medidas = calcularMedidasRectangle(area);
        if(posX+medidas[0]>ancho || posY+medidas[1]>alto || posX<0 || posY<0){
            flag = false;
        }
        return flag;
    }
    
    /**
     * Verifica que el objeto a ingresar este en el rango adecuado de la pantalla (Si el objeto es de tipo Square).
     * 
     * @param posX. Valor de la posicion x de la nacion.
     * @param posY. Valor de la posicion y de la nacion.
     * @param area. Valor del area de la nacion.
     * 
     * @return flag. Retorna el valor booleano, dependiendo si el objeto esta en el rango adecuado o no.
     */
    private boolean estaEnElRangoSquare(int posX, int posY, int area){
        boolean flag = true;
        int medida = calcularMedidasSquare(area);
        if(posX+medida>ancho || posY+medida>alto || posX<0 || posY<0){
            flag = false;
        }
        return flag;
    }
    
    /**
     * Verifica que el objeto a ingresar este en el rango adecuado de la pantalla (Si el objeto es de tipo Triangle).
     * 
     * @param posX. Valor de la posicion x de la nacion.
     * @param posY. Valor de la posicion y de la nacion.
     * @param area. Valor del area de la nacion.
     * 
     * @return flag. Retorna el valor booleano, dependiendo si el objeto esta en el rango adecuado o no.
     */
    private boolean estaEnElRangoTriangle(int posX, int posY, int area){
        boolean flag = true;
        int medida = calcularMedidasTriangle(area);
        if(posX+medida>ancho || posY+medida>alto || posX<0 || posY<0){
            flag = false;
        }
        return flag;
    }
    
    /**
     * Verifica que el objeto a ingresar este en el rango adecuado de la pantalla (Si el objeto es de tipo Circle).
     * 
     * @param posX. Valor de la posicion x de la nacion.
     * @param posY. Valor de la posicion y de la nacion.
     * @param area. Valor del area de la nacion.
     * 
     * @return flag. Retorna el valor boleano, dependiendo si el objeto esta en el rango adecuado o no.
     */
    private boolean estaEnElRangoCircle(int posX, int posY, int area){
        boolean flag = true;
        int medida = calcularMedidasCircle(area);
        if(posX+medida>ancho || posY+medida>alto || posX<0 || posY<0){
            flag = false;
        }
        return flag;
    }
    
    /**
     * Verifica que la figura no se sobreponga a otra (Si el objeto es de tipo Rectangle).
     * 
     * @param x. Indica la posicion en X de la primera figura.
     * @param y. Indica la posicion en Y de la primera figura.
     * @param w. Indica el ancho de la primera figura.
     * @param h. Indica el alto de la primera figura.
     * @param x2. Indica la posicion en X de la segunda figura.
     * @param y2. Indica la posicion en Y de la segunda figura.
     * @param w2. Indica el ancho de la segunda figura.
     * @param h2. Indica el alto de la segunda figura.
     * 
     * @return flag. Retorna el valor boleano, dependiendo si el objeto esta sobrepuesto a otro o no.
     */
    private boolean sonDisyuntosRectangle(int x, int y, int w, int h,int x2,int y2, int w2, int h2)
    {
        boolean flag = false;
        if(x >= (x2+w2) || y >= (y2+h2)){
            flag = true;
        }
        if(x2 >= (x+w) || y2 >= (y+h)){
            flag = true;
        }
        return flag;
    }
    
    /**
     * Verifica que la figura no se sobreponga a otra figura que no sea de su mismo tipo (Si el objeto es de tipo Circle).
     * 
     * @param x. Indica la posicion en X de la primera figura.
     * @param y. Indica la posicion en Y de la primera figura.
     * @param d. Indica el diametro de la primera figura.
     * @param x2. Indica la posicion en X de la segunda figura.
     * @param y2. Indica la posicion en Y de la segunda figura.
     * @param w2. Indica el ancho de la segunda figura.
     * @param h2. Indica el alto de la segunda figura.
     * 
     * @return flag. Retorna el valor boleano, dependiendo si el objeto esta sobrepuesto a otro o no.
     */
    private boolean sonDisyuntosCircle(int x, int y, int d, int x2,int y2, int w2, int h2)
    {
        boolean flag = false;
        int centroX = x+(d/2);
        int centroY = y+(d/2);
        if((centroX-(d/2)) >= (x2+w2) || (centroY-(d/2)) >= (y2+h2)){
            flag = true;
        }
        if((centroX+(d/2)) <= x2 || (centroY+(d/2)) <= y2){
            flag = true;
        }
        return flag;
    }
    
    /**
     * Verifica que la figura no se sobreponga a otra de su mismo tipo (Si el objeto es de tipo Circle).
     * 
     * @param x. Indica la posicion en X de la primera figura.
     * @param y. Indica la posicion en Y de la primera figura.
     * @param d. Indica el diametro de la primera figura.
     * @param x2. Indica la posicion en X de la segunda figura.
     * @param y2. Indica la posicion en Y de la segunda figura.
     * @param d2. Indica el diametro de la segunda figura.
     * 
     * @return flag. Retorna el valor boleano, dependieno si el objeto esta sobrepuesto a otro o no.
     */
    private boolean sonDisyuntosCircleCircle(int x, int y, int d, int x2,int y2, int d2)
    {
        boolean flag = false;
        int centroX = x+(d/2);
        int centroY = y+(d/2);
        int centroX2 = x2+(d2/2);
        int centroY2 = y2+(d2/2);
        if((centroX-(d/2)) >= (centroX2+(d2/2)) || (centroY-(d/2)) >= (centroY2+(d2/2))){
            flag = true;
        }
        if((centroX+(d/2)) <= (centroX2-(d2/2)) || (centroY+(d/2)) <= (centroY2-(d2/2))){
            flag = true;
        }
        return flag;
    }
    
    /**
     * Verifica que la figura no se encuentre dentro de otra.
     * 
     * @param x. Indica la posicion en X de la primera figura.
     * @param y. Indica la posicion en Y de la primera figura.
     * @param x2. Indica la posicion en X de la segunda figura.
     * @param y2. Indica la posicion en Y de la segunda figura.
     * @param w. Indica el ancho de la figura.
     * @param h. Indica el alto de la figura.
     * 
     * @return flag. Retorna el valor boleano, dependieno si el objeto esta dentro de otro o no.
     */
    private boolean estaAdentro(int x, int y,int x2,int y2, int w, int h)
    {
        boolean flag = false;
        if(x >= x2 && x <= x2+w && y >= y2 && y <= y2+h){
            flag = true;
        }
        return flag;
    }
    
    /**
     * Agrega una ruta entre dos naciones.
     * 
     * @param nations. Indica las dos naciones que une la ruta.
     * @param cost. Indica el costo de pasar de cualquiera de las dos naciones a la otra.
     */
    public void addRoute(String[] nations, int cost){
        boolean flag = true;
        Arrays.sort(nations);
        if(naciones.containsKey(nations[0]) && naciones.containsKey(nations[1])){
            if(!nations[0].equals(nations[1])){
                String nombreRuta = nations[0] + nations[1];
                if(!rutas.containsKey(nombreRuta)){
                    Route nuevaRuta = new Route(nations,cost);
                    rutas.put(nombreRuta,nuevaRuta);
                    if(isVisible){
                        nuevaRuta.visible();
                    }else{
                        nuevaRuta.invisible();
                    }
                    mapeoNaciones(nations,nuevaRuta,cost);               
                    isOkRoute = true && flag;
                    isOkAll = true;
                }else{
                    isOkRoute = false;
                    isOkAll = false;
                }
            }else{
                isOkAll = false;
            }
        }else{ 
            isOkRoute = false;
            isOkAll =  false;
        }
    }

    /**
     * Une las naciones mediante rutas.
     * 
     * @param x. Nacion 1
     * @param y. Nacion 2
     */
    private void union(String x, String y,String x_padre,String y_padre){
        if (rangos.get(x_padre) > rangos.get(y_padre)){
            padres.put(y,x_padre);
        }else{
            padres.put(x,y_padre);
            if(rangos.get(x_padre).equals(rangos.get(y_padre))){
                rangos.put(y,rangos.get(y_padre)+1);
            }
        }
    }
    
    /**
     * Mira con quien esta unida la nacion indicada
     * 
     * @param x. Nacion de la cual se busca con quien esta unida. 
     */
    private String padre(String x){
        if(!padres.get(x).equals(x)){
            padres.put(x,padre(padres.get(x))); 
        }
        return padres.get(x);
    }
    
    /**
     * Segun la figura se establece el alto y el ancho para poder enviar las coordenadas para la creacion de la ruta.
     * 
     * @param nations. Naciones que une la ruta.
     * @param nuevaRuta. Objeto ruta que se agrego al diccionario.
     */
    private void mapeoNaciones(String[] nations, Route nuevaRuta,int cost){
        Nation n1 = naciones.get(nations[0]);
        Nation n2 = naciones.get(nations[1]);
        if(n1.getShape().equals("Rectangle")){
            anchoF = n1.getWidth(); altoF = n1.getHeight();
        }
        else if(n2.getShape().equals("Rectangle")){
            anchoF2 = n2.getWidth(); altoF2 = n2.getHeight();
        }
        else if(n1.getShape().equals("Square")){
            anchoF = n1.getLadoSquare(); altoF = n1.getLadoSquare();
        }
        else if(n2.getShape().equals("Square")){
            anchoF2 = n2.getLadoSquare(); altoF2 = n2.getLadoSquare();
        }
        else if(n1.getShape().equals("Triangle")){
            anchoF = n1.getLadoTriangle(); altoF = n1.getLadoTriangle();
        }
        else if(n2.getShape().equals("Triangle")){
            anchoF2 = n2.getLadoTriangle(); altoF2 = n2.getLadoTriangle();
        }
        else if(n1.getShape().equals("Circle")){
            anchoF = n1.getDiametro(); altoF = n1.getDiametro();
        }
        else if(n2.getShape().equals("Circle")){
            anchoF2 = n2.getDiametro(); altoF2 = n2.getDiametro();
        }
        
        nuevaRuta.shapeRoute(n1.getPosX(),n1.getPosY(),n2.getPosX(),n2.getPosY(),anchoF,altoF,anchoF2,altoF2,presupuesto >= cost);
    }
    
    /**
     * Agrega un ejercito a la nacion dada.
     * 
     * @param nation. Indica la nacion a la que se le va a anadir el ejercito.
     */
    public void addArmy(String nation){
        if(naciones.containsKey(nation)){
            Nation n = naciones.get(nation);
            n.sumaEjercito();
            isOkAll = true;
        }
        else{
            isOkAll = false;
        }
    }
    
    /**
     * Agrega los ejercitos a la naciones indicadas.
     * 
     * @param nations. Indica las naciones a las que se les va a anadir el ejercito.
     */
    public void addArmies(String[] nations){
        boolean flag = true;
        for(int i = 0; i < nations.length; i++){
            if(!naciones.containsKey(nations[i])){
                flag = flag && false;
            }
        }
        if(flag){
            for(int i = 0; i < nations.length; i++){
                Nation n = naciones.get(nations[i]);
                n.sumaEjercito();
            }
            isOkAll = true;
        }
        else{
            isOkAll = false;
        }
    }
    
    /**
     * Remueve una nacion segun el color indicado.
     * 
     * @param color. Indica el color de la nacion que se va a remover.
     */
    public void removeNation(String color){
        if(!naciones.containsKey(color)){
                isOkAll = false;
        }else{
            if(naciones.get(color) instanceof NationSteady){
            isOkAll = false;
            }
            else{
                Nation nacion = naciones.get(color);
                boolean flag = false;
                for(String ruta : rutas.keySet()){
                    String[] r = rutas.get(ruta).getNations();
                    if(r[0].equals(color) || r[1].equals(color)){
                        flag = flag || true;
                    }
                }
                if(!flag){
                    Nation n = naciones.get(color);
                    n.invisible(n.getShape());
                    naciones.remove(color);
                    isOkAll = true;
                }
                else{
                    isOkAll = false;
                }
            }
        }
    }
    
    /**
     * Remueve la ruta que une a las naciones indicadas.
     * 
     * @param nations. Indica las naciones unidas por la ruta que se quiere remover.
     */
    public void removeRoute(String[] nations){
        Arrays.sort(nations);
        String nombreRuta = nations[0] + nations[1];
        if(!rutas.containsKey(nombreRuta)){
            isOkAll = false;
        }
        else{
            for(String ruta : rutas.keySet()){
                if(ruta.equals(nombreRuta)){
                    Route r = rutas.get(nombreRuta);
                    r.invisible();
                }
            }
            rutas.remove(nombreRuta);
            isOkAll = true;
        }
    }
    
    /**
     * Remueve el ejercito de la nacion indicada.
     * 
     * @param nation. Indica la nacion de la que hay que remover el ejercito.
     */
    public void removeArmies(String nation){
        if(naciones.containsKey(nation)){
            Nation n = naciones.get(nation);
            if(n.getEjercito() > 0){
                n.quitaEjercito();
                isOkAll = true;
            }
            else{
                isOkAll = false;
            }
        }
        else{
            isOkAll = false;
        }
    }
    
    /**
     * Mueve el ejercito de una nacion a otra.
     * 
     * @param fromNation. Indica la nacion donde esta actualmente el ejercito.
     * @param toNation. Indica la nacion a donde se mueve el ejercito.
     */
    public void moveArmy(String fromNation, String toNation){
        okRoutes();
        if(naciones.containsKey(fromNation) && naciones.containsKey(toNation)){
            String x_padre = padre(fromNation);
            String y_padre = padre(toNation);
            if(x_padre.equals(y_padre) && naciones.get(fromNation).getEjercito() > 0){
                formarGrafo();
                int rutaMenorValor = dijkstra(fromNation,toNation);
                if(presupuesto >= rutaMenorValor){
                    naciones.get(fromNation).restaEjercito();
                    naciones.get(toNation).sumaEjercito();
                    presupuesto = presupuesto - rutaMenorValor;
                    isOkAll = true;
                }else{
                    isOkAll = false;
                }
            }else{
                isOkAll = false;
            }
        }else{
            isOkAll = false;
        }
        
    }
    
    private void formarGrafo(){
        grafoD = new HashMap<String,HashMap<String,Integer>>();
        for(String rut : rutas.keySet()){
            String[] nacioncitas = rutas.get(rut).getNations();
            if(grafoD.containsKey(nacioncitas[0])){
                grafoD.get(nacioncitas[0]).put(nacioncitas[1],rutas.get(rut).getCostoRuta());
            }else{
                HashMap b = new HashMap<String,Integer>();
                b.put(nacioncitas[1],rutas.get(rut).getCostoRuta());
                grafoD.put(nacioncitas[0],b);
            }
            if(grafoD.containsKey(nacioncitas[1])){
                grafoD.get(nacioncitas[1]).put(nacioncitas[0],rutas.get(rut).getCostoRuta());
            }else{
                HashMap b = new HashMap<String,Integer>();
                b.put(nacioncitas[0],rutas.get(rut).getCostoRuta());
                grafoD.put(nacioncitas[1],b);
            }
        }
        
    }
    
    private int dijkstra(String fromNation, String toNation){
        HashMap<String,Integer> dist = new HashMap<String,Integer>();
        HashMap<String,Boolean> sptset = new HashMap<String,Boolean>();
        for(String naci : naciones.keySet()){
            dist.put(naci,Integer.MAX_VALUE);
            sptset.put(naci,false);
        }
        dist.put(fromNation,0);
        for(int count = 0; count < naciones.size() -1 ; count++){
            String u = minDistance(dist,sptset);
            sptset.put(u,true);
            for(String nacion : naciones.keySet()){
                if(!sptset.get(nacion) && grafoD.get(u).get(nacion) != 0 && dist.get(u) != Integer.MAX_VALUE){
                    if(dist.get(u) + grafoD.get(u).get(nacion) < dist.get(nacion)){
                        dist.put(nacion,dist.get(u) + grafoD.get(u).get(nacion));
                    }
                }
            }
        }
        
        return dist.get(toNation);
    }
    
    private String minDistance(HashMap<String,Integer> dist, HashMap<String,Boolean> sptset){
        int min = Integer.MAX_VALUE;
        String minIndex = "";
        for(String i : dist.keySet()){
            if(!sptset.get(i) && dist.get(i) <= min){
                min = dist.get(i);
                minIndex = i;
            }
        }
        
        return minIndex;
    }
    
    /**
     * Mueve el ejercito de forma estrategica.
     */
    public void moveArmy(){
        
    }
    
    /**
     * Indica si ya existe una ruta entre las dos naciones que quiero unir.
     * 
     * @param nations. Naciones que voy a unir.
     * 
     * @return. Retorna si es posible que pueda crear la ruta o no. True en caso de no existir una ruta.
     *          False en caso de existir una ruta.
     */
    public boolean okRoute(String[] nations){
        boolean existeNacion = false;
        boolean noExisteRuta = true;
        okRoutes();
        if(naciones.containsKey(nations[0]) && naciones.containsKey(nations[1])){
            existeNacion = true;
            String x_padre = padre(nations[0]);
            String y_padre = padre(nations[1]);
            if(x_padre.equals(y_padre)){
                isOkRoute = false;
            }
            else{
                isOkRoute = existeNacion;
            }
        }
        else{
            isOkRoute = false;
        }
        return isOkRoute;
    }
    
    /**
     *  Revisa que las rutas cumplan los requisitos.
     *  
     *  @return. Retorna si las rutas cumplen los requisitos o no. 
     */
    public boolean okRoutes(){
        isOkRoutes = true;
        padres = new HashMap<String,String>();
        rangos = new HashMap<String,Integer>();
        for (String color : naciones.keySet()) {
            padres.put(color,color);
            rangos.put(color,0);
        }
        for (String rut : rutas.keySet()) {
            String[] nations = rutas.get(rut).getNations();
            Arrays.sort(nations);
            String x_padre = padre(nations[0]);
            String y_padre = padre(nations[1]);
            if(!x_padre.equals(y_padre)){
                union(nations[0],nations[1],x_padre,y_padre);
            }else{
                isOkRoutes = false;
            }
        }
        return isOkRoutes;
    }
    
    /**
     * Hace visible el simulador.
     */
    public void makeVisible(){
        tablero.makeVisible();
        isVisible = true;
        marcoPresupuesto();
        for (String color : naciones.keySet()) {
            Nation n = naciones.get(color);
            n.visible(n.getShape());
        }
        for (String ruta : rutas.keySet()) {
            Route r = rutas.get(ruta);
            r.visible();
        }
    }
    
    /**
     * Hace invisible el simulador. (Tiene que seguir funcionando)
     */
    public void makeInvisible(){
        tablero.makeInvisible();
        isVisible = false;
        marcoPresupuesto();
        for (String color : naciones.keySet()) {
            Nation n = naciones.get(color);
            n.invisible(n.getShape());
        }
        for (String ruta : rutas.keySet()) {
            Route r = rutas.get(ruta);
            r.invisible();
        }
    }
    
    /**
     * Finaliza el simulador.
     */
    public void finish(){
        System.exit(0);
    }
    
    /**
     * Verifica que las instrucciones dadas se cumplan.
     * 
     * @return. Retorna si la ultima operacion se pudo realizar o no.
     */
    public boolean ok(){
        return isOkAll;
    }
    
    /**
     * Aumenta o disminuye el tamaño del tablero.
     * 
     * @param c. "+" indica el aumento en el tamano del tablero y "-" indica la disminucion del tamano. 
     */
    public void zoom(char c){
        if (c=='+'){
            tablero.changeSize(alto+25,ancho+25);
            
        }
        else{
            tablero.changeSize(alto-25,ancho-25);
        }
    }
    
    /**
     * Consulta las naciones existentes en el mundo.
     * @return La lista de las naciones existentes.
     */
    public String[] nations(){
        String[] nation = new String[naciones.size()];
        int c = 0;
        for (String color : naciones.keySet()){
            nation[c] = color;
            c++;
        }
        return nation;
    }
    
    /**
     * Consulta las rutas existentes en el mundo.
     * @return Las rutas existentes.
     */
    public String[][] routes(){
        String[][] routas = new String[2][rutas.size()]; 
        int c = -1;
        for (String ruta : rutas.keySet()) {
            c++;
            Route r = rutas.get(ruta);
            String[] s = r.getNations();
            routas[c][0] = s[0];
            routas[c][1] = s[1];    
        }
        
        return routas;
    }
    
    /**
     * Consulta las rutas que tiene una nacion dada.
     * @param nation. Indica la nacion de la que se quieren conocer las rutas.
     * @return La lista de rutas de la nacion.
     */
    public String[] routes(String nation){
        ArrayList<String> rutasConectadas = new ArrayList<String>();
        for (String ruta : rutas.keySet()){
            Route r = rutas.get(ruta);
            String[] a = r.getNations();
            if(a[0].equals(nation)){
                rutasConectadas.add(a[1]);
            }
            if(a[1].equals(nation)){
                rutasConectadas.add(a[0]);
            }
        }
        String[] listRutasConectadas = new String[rutasConectadas.size()];
        for(int i = 0; i<rutasConectadas.size(); i++){
            listRutasConectadas[i] = rutasConectadas.get(i);
        }
        Arrays.sort(listRutasConectadas);        
        return listRutasConectadas;
    }
}

