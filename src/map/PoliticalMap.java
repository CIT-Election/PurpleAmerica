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
    boolean isLoui = false;
        
    File file = new File("src/map/USA.txt");
    File elect = new File("src/data/USA2012.txt");
    String strState[] = {"AL","AR","AZ","CA","CO","CT","DC","DE","FL","GA",
        "IA","ID","IL","IN","KS","KY","LA","MA","MD","ME","MI","MN","MO","MS",
        "MT","NC","ND","NE","NH","NJ","NM","NV","NY","OH","OK","OR","PA","RI",
        "SC","SD","TN","TX","UT","VA","VT","WA","WI","WV","WY"}; 
        //array with all the names of the States' abbreviations
    String finalYear = ""; //user input
    Scanner sc = new Scanner(System.in); 
    boolean isInputvalid = false; 
    while(!isInputvalid){
        System.out.println("Enter the year that you would like to see: ");
        int year = sc.nextInt(); 
        try {

            if(year >= 1960 && year <= 2012){
                isInputvalid = true; 
                if(year % 4 == 0){
                        isInputvalid = true; 
                         finalYear = year + ".txt";  
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

        } catch (InputMismatchException e){
            System.out.println("That is not a valid input"); 
            System.out.println("Your options are: 1960, 1964, 1968, 1972, 1976, 1980, 1992, 1996, 2000, 2004, 2008, 2012");
            
        }
        
    } 
    
    
    StdDraw.setCanvasSize(1100, 700);
    boolean counties = false;
    
    System.out.println("Do you want to the the USA with counties?(y/n)");
    String desc = sc.next();
    if(desc.equals("y") || desc.equals("yes")){
        counties = true;
    }
    
        mapMake map = new mapMake(file, elect);      
    
    if(!counties){
        map.getVotes(elect);
        map.mapColor();
        map.mapBorder();
    }
    // Calls strState and calls methods involed from CountiesMap class.  
    if(counties){
        for (int i=0;i<strState.length;i++){
            if(strState[i].equals("LA")){
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
    
    boolean clicked = false;
    double xCoordinate, yCoordinate;
    while (!clicked) { 
        if (StdDraw.mousePressed()){
            xCoordinate = StdDraw.mouseX();
            yCoordinate = StdDraw.mouseY();
            clicked = true;
            
           if(counties){
                for (int i=0;i<strState.length;i++){
                    if(strState[i].equals("LA")){
                        isLoui = true;
                    }
                    StdDraw.setCanvasSize(1100, 700);
                    File countyFile = new File ("src/data/" + strState[i]+ ".txt");
                    File countyVotes = new File ("src/data/"+ strState[i]+ finalYear);
                    CountiesMap county = new CountiesMap(countyFile,countyVotes); 
                    county.getVotes(countyVotes, isLoui);
                    county.mapColor(); 
                    //county.mapBorder();
                    isLoui = false;
                } 
            } 
        }
    } 
  }
}
