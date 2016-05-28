/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package map;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author hcps-xieey
 */
public class RGB {
    public static void main (String[] args) throws FileNotFoundException{
        File file2 = new File("src/data/USA2012.txt"); 
        Scanner sc = new Scanner(file2); 
        sc.nextLine(); 
        String testInput = sc.next();
        String[] ary = testInput.split(",");
        double a1 = (double)(Integer.parseInt(ary[1])); 
        double a2 = (double)(Integer.parseInt(ary[2]));
        double a3 = (double)(Integer.parseInt(ary[3]));  //Just used to test out the formula for finding the correct color 
        System.out.println(a1);
        System.out.println(a2);
        System.out.println(a3);
        double r = ((a1)/(a1 + a2 + a3));
        double g = ((a2)/(a1 + a2 + a3));
        double b = ((a3)/(a1 + a2 + a3)); 
        int red = (int)(r*255); 
        int green = (int)(g*255);
        int blue = (int)(b*255); 
        System.out.println(red + ", " + green + ", " + blue);
    }
}
