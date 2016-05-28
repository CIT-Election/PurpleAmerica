/*
 * Political Map Project
 * Name: Bashir, Sree, Ethan 
 * Block: 6
 * 
 * Program Purpose:
 *This program's purpose is to illustrate the election data visually on a map. 
 *It does this by taking in the voting data and using an equation to generate the 
 *RGB that is associated with it. Then the map is genenrated with or without the 
 *counties and then is drawn. It is then filled in the correct RGB. The final result
 *will be a map of the US with colors representing the election data. 
 * 
 * Algorithm:
 * Our algorithm was to modularize as much as possible and we made multiple classes
 * to store different methods. For example, we have one class for the counties separate 
 * from the one that draws the map without the counties. 
 *
 * Future/possible improvements:
 * One improvement we could've made was to comment and communicate much more on GIT Hub. 
 * Our communication was very poor and we didn't utilize the tools that GIT Hub has. 
 * We should've commented our code much earlier so that other members could use and 
 * comprehend that code. 
 */
package map;
import edu.princeton.cs.introcs.*; //Imports 
import java.io.File;
import java.util.*;
/**
 *
 * @author 
 */
public class PoliticalMap {
    public static void main(String[] args) throws Exception{
    boolean isLoui = false;//will see if the state lousiana is being looked at
    boolean go = false;
    File turnout = new File("src/data/voterTurnout.txt");// file with turnout data
    File file = new File("src/map/USA.txt");
    File elect = new File("src/data/USA2012.txt");    

    
    String strState[] = {"AL","AR","AZ","CA","CO","CT","DC","DE","FL","GA",
        "IA","ID","IL","IN","KS","KY","LA","MA","MD","ME","MI","MN","MO","MS",
        "MT","NC","ND","NE","NH","NJ","NM","NV","NY","OH","OK","OR","PA","RI",
        "SC","SD","TN","TX","UT","VA","VT","WA","WI","WV","WY"}; 
        //array with all the names of the States' abbreviation
    
    double[] stateMinX = new double[48];
    double[] stateMaxX = new double[48]; 
    double[] stateMinY = new double [48]; 
    double[] stateMaxY = new double[48];
    //4 arrays to store coordiates of each state
    
    String finalYear = ""; //user input
    Scanner sc = new Scanner(System.in); //Initializes a scanner 
  
    String fInput = ""; 
    boolean isInputv = false; 
    
    while(!isInputv){ //loops until a valid input is received 
        System.out.println("Would you like to see Presidential Election or Senator Election? (Presidential/Senator)"); 
        try{ //tries to see if the input is Senator, if it is then it draws the senator map 
            fInput = sc.next(); 
            if(fInput.equals("Senator")){ //Checks if it is senator 
                isInputv = true; 
                File elect2 = new File("src/data/Senate2012.txt"); 
                mapMake map2 = new mapMake(file, elect2);//creates mapmake object
                map2.getVotes(elect);
                map2.mapColor();
                map2.mapBorder();
            }   
            
            if(fInput.equals("Presidential")){ //If it is presidential, then it exits the loop and continues through the program
                isInputv = true; 
            }
        } catch (InputMismatchException e){ //Catches the InputMismatchException
            System.out.println("That is not a valid input. Please try again."); 
        }
    }
        
    boolean isInputvalid = false; 
    while(!isInputvalid){ //Loops until the input is valid 
        System.out.println("Enter the year that you would like to see: ");
        int year = sc.nextInt(); 
        try { //Trys to see if the input is between the valid years 

            if(year >= 1960 && year <= 2012){
                isInputvalid = true; 
                if(year % 4 == 0){ //Checks if the year is divisible by 4, only years that are divisible by 4 are correct
                        isInputvalid = true; 
                         finalYear = year + ".txt";  //Adds the year to be properly formatted for the file name 
                    } else {
                        System.out.println("That is not a valid input");
                        System.out.println("Your options are: 1960, 1964, 1968, 1972, 1976, 1980, 1992, 1996, 2000, 2004, 2008, 2012"); 
                        isInputvalid = false; 
                    }
            } else { 
                System.out.println("That is not a valid input"); 
                System.out.println("Your options are: 1960, 1964, 1968, 1972, 1976, 1980, 1992, 1996, 2000, 2004, 2008, 2012");
                isInputvalid = false; 
            }

        } catch (InputMismatchException e){ //Catches the InputMismatchException 
            System.out.println("That is not a valid input"); 
            System.out.println("Your options are: 1960, 1964, 1968, 1972, 1976, 1980, 1992, 1996, 2000, 2004, 2008, 2012");
            
        }
        
    } 
    
    
    StdDraw.setCanvasSize(1100, 700);
    boolean counties = false;// will check to see if counties want to be looked at
    mapMake map = new mapMake(file, elect);//creates mapmake object      

    System.out.println("Do you want to the the USA with counties?(y/n)");
    String desc = sc.next();
    if(desc.equals("y") || desc.equals("yes")){//if user wants to see counties
        counties = true;
    }
    
    if(!counties){//if they dont want to see counties just show states
        map.getVotes(elect);
        map.mapColor();
        map.mapBorder();
    }
    // Calls strState and calls methods involed from CountiesMap class.  
    if(counties){
        for (int i=0;i<strState.length;i++){
            if(strState[i].equals("LA")){//checks to see if state is california
                isLoui = true;
            }
            File countyFile = new File ("src/data/" + strState[i]+ ".txt");
            File countyVotes = new File ("src/data/"+ strState[i]+ finalYear);
            CountiesMap county = new CountiesMap(countyFile,countyVotes); 
            county.getVotes(countyVotes, isLoui);
            county.mapColor(); 
            //county.mapBorder();
            isLoui = false;
        } 
    }   
    
    System.out.println("Do you want to see voter turnout percentages " + 
            "for the past 36 elections?(y/n)");
    desc = sc.next();
    
    if(desc.equals("y") || desc.equals("yes")){//if user wants to see turnout
        go = true;
    }    
    
    while(go){
        System.out.println("Ok. Enter an election year past 1980.");
        int r = sc.nextInt();
        try{    
            if(r > 1979 && r < 2015 && r%2 == 0 ){//checks to see election year is available
                //invokes turnout methods
                    map.getTurnout(r, turnout);
                    map.mapTurnout();
                    go = false;
            }else{
                System.out.println("Wrong input. Try again");
            }
        }catch(InputMismatchException wd){
                System.out.println("Wrong input. Try again");
                
        }
    }
    
    boolean clicked = false;
    boolean stateFound = false;
    int k = 0; //variable to find state coordinates
    double xCoordinate, yCoordinate;
    for (int j = 0; j < 48; j++) { 
        File stateFile = new File ("src/data/" + strState[j]+ ".txt");
        Scanner scan = new Scanner(stateFile);
        stateMinX[j] = scan.nextDouble();
        stateMaxX[j] = scan.nextDouble();
        stateMinY[j] = scan.nextDouble();  
        stateMaxY[j] = scan.nextDouble();
    }//scans text docs and stores the boundary coordinates in separate arrays
    
    while (!clicked) { 
        if (StdDraw.mousePressed()){
            xCoordinate = StdDraw.mouseX();
            yCoordinate = StdDraw.mouseY();
            System.out.println(xCoordinate);
            System.out.println(yCoordinate);
            clicked = true;
            k = 0;
            while (!stateFound && k < 48) { 
                if (xCoordinate >= stateMinX[k] && xCoordinate <= stateMaxX[k]) { 
                    if (yCoordinate >= stateMinY[k] && yCoordinate <= stateMaxY[k]) { 
                        stateFound = true;
                    } 
                }
                else { 
                 k++;   
                } 
            }//Finds the state of the clicked coordinate
            if(strState[k].equals("LA")){
                isLoui = true;
            }
            
            File drawState = new File ("src/data/" + strState[k]+ ".txt");
            File stateVotes = new File ("src/data/"+ strState[k]+ finalYear);
                        
            CountiesMap bigState = new CountiesMap(drawState,stateVotes);
            
            StdDraw.setCanvasSize(1100, 700); //redraws canvas(window)
            
            StdDraw.setXscale(stateMinX[k]  , stateMaxX[k]);
            StdDraw.setYscale(stateMinY[k]  , stateMaxY[k] );
            //StdDraw.setScale(-.05, 1.05);
            bigState.getVotes(stateVotes, isLoui);
            
            bigState.mapBorder();
            bigState.mapColor();
      
            isLoui = false;
      }
    } 
  }
} 
