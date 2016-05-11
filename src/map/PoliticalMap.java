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
    File elect = new File("src/data/USA2012.txt");
   
    mapMake map = new mapMake(file, elect);    
    map.mapColor();
    map.mapBorder();
    


     
    }
}
