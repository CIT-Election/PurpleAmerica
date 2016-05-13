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
        
    File file = new File("src/data/USA.txt");
    File elect = new File("src/data/USA2012.txt");
   
    mapMake map = new mapMake(file, elect);  
    map.getVotes(elect);
    map.mapColor();
    map.mapBorder();
    //HELLO WORLD


     
    }
}
