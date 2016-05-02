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
import java.util.*;
/**
 *
 * @author 
 */
public class PoliticalMap {
    public static void main(String[] args) throws Exception{
        
     File file = new File("src/map/USA.txt");
     Scanner scan = new Scanner(file);
     double minX = scan.nextDouble(); 
     double minY = scan.nextDouble(); 
     double mostX = scan.nextDouble(); 
     double mostY = scan.nextDouble(); 
     StdDraw.setCanvasSize(1000, 700);
     StdDraw.setXscale(minX, mostX);
     StdDraw.setYscale(minY, mostY);
     double[] x;
     double[] y;     
     int terNum;
     int i = 0;

 
     
     while(scan.hasNext()){
         try{
             terNum = scan.nextInt();
             x = new double[terNum];
             y = new double[terNum];
             for(i = 0; i < terNum; i++){
                scan.nextLine();
                x[i] = scan.nextDouble();
                y[i] = scan.nextDouble();
             }
             StdDraw.polygon(x, y);
             
         }catch(InputMismatchException e){
            scan.nextLine();
         }
         
    }
    }
}
