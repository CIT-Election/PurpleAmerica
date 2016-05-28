/*
 * Sreepradha, Bashir, Ethan 
 * Class Name: mapMake  
 * Methods: 
 * mapColor() : Sets RGB for printed state
 * getVotes() : tallies Democratic, Republican and Indipendent votes 
 * mapBorder(): Draws borderlines for each of the counties 
 */

package map;

import edu.princeton.cs.introcs.StdDraw;
import java.io.File;
import java.io.FileNotFoundException;
import java.awt.*;
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
    int electionNum = 0;
    String[] stateName;
    int[][] votes;             
    double sum;
    File election;
    String year = "2012";
    String state;
    String county;
    int p = 0;
    int stateNum;
    int voteNum;
    File file2;        
    int[] turnout = new int[52];
    String[] stateTurn = new String[52];
    Scanner scan2;
    int givYear;// the given year by the file
    String test;//used to see if state is DC among other things
    Double preColor;
    int turnYear;
    
    
    
    
    public mapMake(File f, File e) throws FileNotFoundException{
     scan = new Scanner(f);//makes scanner of file with coordinates
     //scans in the bounds of the states
     minX = scan.nextDouble();
     minY = scan.nextDouble();
     mostX = scan.nextDouble();
     mostY = scan.nextDouble();
     StdDraw.setCanvasSize(1100, 700);//good size for USA
     //sets the scales equal to the bounds of the states
     StdDraw.setXscale(minX, mostX);
     StdDraw.setYscale(minY, mostY);
     scanColor = new Scanner(e);//makes scanner of vote data  
     file = f;
     election = e;
     
    }
    

    public void mapColor() throws FileNotFoundException{
        scan = new Scanner(file);
        scan.nextLine();//skips through useless data
        scan.nextLine();
        stateNum = scan.nextInt();
        scan.nextLine();        
        scan.nextLine();
        while(scan.hasNext()){
            try{
                state = scan.nextLine();//holds state name
                county = scan.nextLine();//holds county name
                terNum = scan.nextInt();//holds # of coordinates
                x = new double[terNum];
                y = new double[terNum];
                //goes through file & puts the x and y values into 
                
                for(i = 0; i < terNum; i++){
                    scan.nextLine();
                    x[i] = scan.nextDouble();
                    y[i] = scan.nextDouble();
                }
                i = 0;
                //checks to see at what index the the county names match
                
                for(i=0; i < stateName.length; i++){
                    if(state.equals(stateName[i])){
                        voteNum = i;//when names match mark the index
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
    
    public void mapTurnout() throws FileNotFoundException{
        scan = new Scanner(file);
        scan.nextLine();//skips through useless data
        scan.nextLine();
        stateNum = scan.nextInt();
        scan.nextLine();        
        scan.nextLine();
        while(scan.hasNext()){
            try{
                state = scan.nextLine();//holds state name
                county = scan.nextLine();//holds county name
                terNum = scan.nextInt();//holds # of coordinates
                
                x = new double[terNum];
                y = new double[terNum];
                //goes through file & puts the x and y values into 
                for(i = 0; i < terNum; i++){
                    scan.nextLine();
                    x[i] = scan.nextDouble();
                    y[i] = scan.nextDouble();
                }
                //checks to see at what index the the county names match
                for(i=0; i < stateTurn.length; i++){
                    if(state.equals(stateTurn[i])){
                        voteNum = i;//when names match mark the index
                    }
                }
                //gets votes at the index found and sets the pen color to those values
                StdDraw.setPenColor(turnout[voteNum], turnout[voteNum], 255);
                StdDraw.filledPolygon(x, y);//draws state
                scan.nextLine();
                scan.nextLine();
            }catch(InputMismatchException e){
               scan.nextLine();
            }
        }
        
    StdDraw.setPenColor(Color.BLACK);
    StdDraw.setFont(new Font ("Serif", Font.BOLD, 25));
    //makes header text
    StdDraw.text(-78, 48, "Voter Turnout Percentages for "); 
    StdDraw.text(-78, 47, turnYear + ""); 
    
    StdDraw.setFont(new Font ("Times New Roman", Font.BOLD, 14));
    
    StdDraw.text(-121, 29, "100% Turnout"); 
    StdDraw.text(-115, 29, "50% Turnout"); 
    StdDraw.text(-109, 29, "0% Turnout"); 
    //sets the color of each box to the color the text is representing
    StdDraw.setPenColor(0, 0,255);//100% turnout
    StdDraw.filledSquare(-121, 27, 1.5);
    
    StdDraw.setPenColor(128, 128,255);// 50% turnout    
    StdDraw.filledSquare(-115, 27, 1.5);
    
    StdDraw.setPenColor(0,0,0);//0% turnout       
    StdDraw.filledSquare(-109, 27, 1.53);    
    
    StdDraw.setPenColor(254, 254,255);//sets outline for white box   
    StdDraw.filledSquare(-109, 27, 1.5);


    
    
    
    
    
    
     scan = new Scanner(file);       
    }

    
    public void getTurnout(int year, File f) throws FileNotFoundException{
        
        scan2 = new Scanner(f);
        scan2.nextLine();
        scan2.nextLine();
        i = 0;
        turnYear = year;
        
        while(scan2.hasNext()){
            givYear = scan2.nextInt();
            
            if(givYear == year){
                scan2.nextInt();
                scan2.nextInt();
                
                stateTurn[i] = scan2.next();
                try{
                    scan2.nextDouble();
                
                }catch(InputMismatchException al){
                    if(stateTurn[i].equals("District")){
                        stateTurn[i] = "District" + " "+ scan2.next() + " "+scan2.next();
                    }else{
                        test = stateTurn[i] + " ";//sets the test to the first word of a state
                        stateTurn[i] = test + scan2.next();
                    }
                    scan2.nextDouble();                
                }
                    preColor = scan2.nextDouble();
                    
                    
                    turnout[i] = (int)(255.0 - (255 *(preColor/100)));              
                    i++;
                    scan2.nextLine();
            }else{
                scan2.nextLine();
            }  
        }        
        
    }

    
    
    
    public void getVotes(File f) throws FileNotFoundException{
        scanColor = new Scanner(f);
        scanColor.nextLine();
        i = 0;
        
        while(scanColor.hasNext()){//just checks to see how many states' results are shown
            electionNum++;//will be equal to # of states in file
            scanColor.nextLine();            
        }
        votes = new int[electionNum][3];//double int array that holds the votes
        stateName = new String[electionNum];//will hold each state/county name
        scanColor = new Scanner(f);
        scanColor.nextLine();        
        
        while(scanColor.hasNext()){
            sVotes = scanColor.nextLine();
            ary = sVotes.split(",");//splits the data held together by commas apart
            stateName[i] = ary[0];// sets this array index equal to a state name
            //sets another array equal to the double value of the votes
            ary2[0] = Double.parseDouble(ary[1]);
            ary2[1] = Double.parseDouble(ary[2]);
            ary2[2] = Double.parseDouble(ary[3]);
            sum = ary2[0] + ary2[1] + ary2[2];//sum of votes
            //sets final array equal to the percent representation of each voting party
            votes[i][0] = (int) (225.0 * ary2[0]/sum);//red
            votes[i][1] = (int) (225.0 * ary2[1]/sum);//blue
            votes[i][2] = (int) (225.0 * ary2[2]/sum);//green            
            i++;
        }
              
            StdDraw.setPenColor((int)red,(int)green,(int)blue);
    }
    
    
    public void mapBorder()throws FileNotFoundException{//sets the outline of states
        scan = new Scanner(file);               
        StdDraw.setPenColor(StdDraw.BLACK);
        while(scan.hasNext()){
         try{//goes through state coordinates and makes arrays of x & y values
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
