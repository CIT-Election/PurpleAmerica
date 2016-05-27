/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package map;

import edu.princeton.cs.introcs.StdDraw;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 *
 * @author hcps-sreekeshs
 */
public class CountiesMap {
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
    int electionNum = 0;
    String[] stateName;
    int[][] votes;             
     double sum;
     File election;
    
    public CountiesMap (File f, File e) throws FileNotFoundException{
     scan = new Scanner(f);
     minX = scan.nextDouble();
     minY = scan.nextDouble();
     mostX = scan.nextDouble();
     mostY = scan.nextDouble();
     
     
     //StdDraw.setCanvasSize(1100, 700);
     //StdDraw.setXscale(minX, mostX);
     //StdDraw.setYscale(minY, mostY);
     scanColor = new Scanner(e);     
     file = f;
     election = e;
     
    }
    
    String state;
    int p = 0;
    int stateNum;
    String[] stateAry;
    int voteNum;
    
    public void mapColor() throws FileNotFoundException{
        scan = new Scanner(file);
        scan.nextLine();
        scan.nextLine();
        stateNum = scan.nextInt();
        stateAry = new String[stateNum];
        scan.nextLine();        
        scan.nextLine();
        while(scan.hasNext()){
            try{
                state = scan.nextLine();
                stateAry[p] = state;
                p++;
                scan.nextLine();
                terNum = scan.nextInt();
                x = new double[terNum];
                y = new double[terNum];
                for(i = 0; i < terNum; i++){
                    scan.nextLine();
                    x[i] = scan.nextDouble();
                    y[i] = scan.nextDouble();
                }
                
                for(i=0; i < stateName.length; i++){
                    if(state.equals(stateName[i])){
                        voteNum = i;
                    }
                }
                StdDraw.setPenColor(votes[voteNum][0], votes[voteNum][2], votes[voteNum][1]);
                StdDraw.filledPolygon(x, y);
                scan.nextLine();
                scan.nextLine();
            }catch(InputMismatchException e){
               scan.nextLine();
            }
        }
     scan = new Scanner(file);       
    }
    

    public void getVotes(File f, boolean isLoui) throws FileNotFoundException{
        scanColor = new Scanner(f);
        scanColor.nextLine();
        i = 0;
        
        while(scanColor.hasNext()){
            electionNum++;
            scanColor.nextLine();            
        }
        votes = new int[electionNum][3];
        stateName = new String[electionNum];
        scanColor = new Scanner(f);
        scanColor.nextLine();        
        
        while(scanColor.hasNext()){
            sVotes = scanColor.nextLine();
            ary = sVotes.split(",");
            if(isLoui){
            stateName[i] = (ary[0] + " Parish") ;
                
            }else{
            stateName[i] = ary[0] ;
            }
            ary2[0] = Double.parseDouble(ary[1]);
            ary2[1] = Double.parseDouble(ary[2]);
            ary2[2] = Double.parseDouble(ary[3]);
            sum = ary2[0] + ary2[1] + ary2[2];
            votes[i][0] = (int) (225.0 * ary2[0]/sum);//red
            votes[i][1] = (int) (225.0 * ary2[1]/sum);//blue
            votes[i][2] = (int) (225.0 * ary2[2]/sum);//green            
            i++;
        }
              
            StdDraw.setPenColor((int)red,(int)green,(int)blue);
    }
    
    
    public void mapBorder()throws FileNotFoundException{
        scan = new Scanner(file);               
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
