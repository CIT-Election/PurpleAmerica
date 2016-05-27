/*
 * Names: Sreepradha, Bashir, Eathan 
 * Class Name: CountiesMap
 * Variables: 
 * Methods: 
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
     scanColor = new Scanner(e); //makes scanner of vote data    
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
        scan.nextLine();//skips through useless data
        scan.nextLine();
        stateNum = scan.nextInt();
        stateAry = new String[stateNum];
        scan.nextLine();        
        scan.nextLine();
        while(scan.hasNext()){
            try{
                state = scan.nextLine(); //holds state name
                stateAry[p] = state;
                p++;
                scan.nextLine();
                terNum = scan.nextInt();//holds # of coordinates
                x = new double[terNum];
                y = new double[terNum];
                //goes through file & puts the x and y values into it
                
                for(i = 0; i < terNum; i++){
                    scan.nextLine();
                    x[i] = scan.nextDouble();
                    y[i] = scan.nextDouble();
                } //checks to see at what index the the county names match
                
                for(i=0; i < stateName.length; i++){
                    if(state.equals(stateName[i])){
                        voteNum = i;
                    }
                }
        //gets votes at the index found and sets the pen color to those values
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
        scanColor.nextLine();//checks to see how many states' results are shown
        i = 0;
        
        while(scanColor.hasNext()){
            electionNum++; //will be equal to # of states in file
            scanColor.nextLine();            
        } //checks to see how many states' results are shown
        votes = new int[electionNum][3];//double int array that holds the votes
        stateName = new String[electionNum];//will hold each state/county name
        scanColor = new Scanner(f);
        scanColor.nextLine();        
        
        while(scanColor.hasNext()){
            sVotes = scanColor.nextLine();
            ary = sVotes.split(",");
                //splits the data held together by commas apart
            if(isLoui){
            stateName[i] = (ary[0] + " Parish") ;
                
            }else{
            stateName[i] = ary[0] ;// sets this array index equal to a state name
            //sets another array equal to the double value of the votes
            }
            ary2[0] = Double.parseDouble(ary[1]);
            ary2[1] = Double.parseDouble(ary[2]);
            ary2[2] = Double.parseDouble(ary[3]);
            sum = ary2[0] + ary2[1] + ary2[2]; //sum of votes
            votes[i][0] = (int) (225.0 * ary2[0]/sum);//red
            votes[i][1] = (int) (225.0 * ary2[1]/sum);//blue
            votes[i][2] = (int) (225.0 * ary2[2]/sum);//green            
            i++;
        }
              
            StdDraw.setPenColor((int)red,(int)green,(int)blue);
    }
    
    //sets the outline of states
    public void mapBorder()throws FileNotFoundException{
        scan = new Scanner(file);               
        StdDraw.setPenColor(StdDraw.BLACK);
        while(scan.hasNext()){
            //goes through state coordinates and makes arrays of x & y values
            try{
             terNum = scan.nextInt();//holds number of coordinates
             x = new double[terNum];
             y = new double[terNum];
             for(i = 0; i < terNum; i++){
                scan.nextLine();
                x[i] = scan.nextDouble();//holds x values
                y[i] = scan.nextDouble();//holds y values 
             }
             StdDraw.polygon(x, y);//draws state
             
         }catch(InputMismatchException e){
            scan.nextLine();
         }
         
        }    
        
    }
    
}
