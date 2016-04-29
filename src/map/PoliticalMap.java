/*
 * Political Map Project
 * Name: 
 * Block:
 * 
 * Program Purpose:
 *
 * Algorithm:
 * 
 * Future/possible improvements:
 *
 */
package map;
import edu.princeton.cs.introcs.*;
import java.io.File;
import java.util.Scanner;
/**
 *
 * @author 
 */
public class PoliticalMap {
    public static void main(String[] args) throws Exception{
        
     File file = new File("src/map/bama.txt");
     Scanner scan = new Scanner(file);
     double latitude; // (φ)
     double longitude;   // (λ)
     double mapWidth = 200;
     double mapHeight = 100;
     StdDraw.setCanvasSize(200,200);
     StdDraw.setXscale( 50.9, 51.3);
     StdDraw.setYscale(32.3, 33);
     double latRad;
     double mercN;
     double[] x = new double[20];
     double[] y = new double[20];     
     int i = 0;
     
     while(scan.hasNext()){
         longitude = scan.nextDouble();
         latitude = scan.nextDouble();
         scan.nextLine();
         
        // get x value
        x[i] = (longitude+180)*(mapWidth/360);

        // convert from degrees to radians
        latRad = latitude*(Math.PI)/180;

        // get y value
        mercN = Math.log(Math.tan(((Math.PI)/4)+(latRad/2)));
        y[i] = (mapHeight/2)-(mapWidth*mercN/(2*(Math.PI)));
        i++;
     }    
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.filledPolygon(x, y);

    }
}
