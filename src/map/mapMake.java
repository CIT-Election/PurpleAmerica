/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package map;

import edu.princeton.cs.introcs.StdDraw;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 *
 * @author hcps-mosavellb
 */
public class mapMake {
     Scanner scan;
     Scanner scanColor;     
     File file;
     double minX; 
     double minY; 
     double mostX;
     double mostY; 
     double[] x;
     double[] y;     
     int terNum;
     int i = 0;
     String sVotes;
     String[] ary;
     double[] ary2 = new double[3];
     double red; 
     double green; 
     double blue;
     double sum;
    
    public mapMake(File f, File e) throws FileNotFoundException{
     scan = new Scanner(f);
     minX = scan.nextDouble();
     minY = scan.nextDouble();
     mostX = scan.nextDouble();
     mostY = scan.nextDouble();
     StdDraw.setCanvasSize(1100, 700);
     StdDraw.setXscale(minX, mostX);
     StdDraw.setYscale(minY, mostY);
     scanColor = new Scanner(e);     
     file = f;
     scanColor.nextLine();
     
    }
    
    public void mapColor() throws FileNotFoundException{
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
                
                sVotes = scanColor.next();
                ary = sVotes.split(",");
                ary2[0] = Double.parseDouble(ary[1]);
                ary2[1] = Double.parseDouble(ary[2]);
                ary2[2] = Double.parseDouble(ary[3]);
                sum = ary2[0] + ary2[1] + ary2[2];
                
                red = 225.0 * ary2[0]/sum;
                blue = 225.0 * ary2[1]/sum;
                green = 225.0 * ary2[2]/sum;
                StdDraw.setPenColor((int)red,(int)green,(int)blue);
                
                StdDraw.filledPolygon(x, y);

            }catch(InputMismatchException e){
               scan.nextLine();
            }        
        }
     scan = new Scanner(file);       
    }
    
    public void mapBorder(){
        StdDraw.setPenColor(StdDraw.BLACK);
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
