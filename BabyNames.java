/*
   @author Safal Adhikari
   @class IT 219
   @date 02/24/2020
   @file: BabyNames.java
   @This program will read through the data file searching for 
    the name that is input by the user. If it finds it, it should
    display the years and rankings for that name. If not, it should
    generate a short message indicating that the name was not found.
*/

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.*;

public class BabyNames
{
   //Source File
   static final String FILENAME = "names.txt";
   
   //Starting year of input data
   static final int STARTYEAR = 1910;
   
   public static void main(String[] args)
   throws FileNotFoundException
   {
      intro();    
      String name = getUserInput();
      String lineData = readFile(name);
      decadeResults(lineData);        
   }
   
   //Prints an introductory message.
   public static void intro()
   {
      System.out.println("This program uses data from the Social Security Administration" + 
               "\nto see how popular a particular name has been over the last century." +
               "\nThe first column displays the year, and the second column displays the ranking." +
               "\nOnly the top 100 rankings are tracked. A zero means that the name was not in" +
               "\nthe top 1000 for that year.");
   }
   
   //Prompts the user for a name
   public static String getUserInput()
   {
      Scanner keyboard = new Scanner(System.in);
      
      System.out.print("Enter a name: ");
      String userInput = keyboard.nextLine();
      System.out.println();
      return userInput;
   }
   
   //Checks if the name the user inputs is in the source file
   //Prints a statement if the name is not found
   //Otherwise returns a String containg the line where the name is found.
   public static String readFile(String tempName)
   throws FileNotFoundException
   {
      Scanner fileInput = new Scanner(new File(FILENAME));
      
      boolean nameFound = false;
      
      String lineData = "";
      
      while(fileInput.hasNextLine())
      {
         String line = fileInput.nextLine();
         
         Scanner lineNow = new Scanner(line);
         
         String lineNowToken = lineNow.next();
         
         if(lineNowToken.equalsIgnoreCase(tempName))
         {
            nameFound = true;
            lineData = line;
         }
      }
      
      if (nameFound == false)
      {
         System.out.println(tempName + " not found.");  
      }
      return lineData;
   }
   
   //Prints the year and ranking for the input name
   //Prints the year and input name was most popular.
   public static void decadeResults(String tempLineData)
   throws FileNotFoundException   
   {
      Scanner lineDataReport = new Scanner(tempLineData);
      
      String name = "";    
      int yearCounter = STARTYEAR;
      int popularYear = 0;
      int topRank = 1001;
      int decadeRank = 0;
      
      while(lineDataReport.hasNext())
      {
         name = lineDataReport.next();
         for(int i=0; i<11; i++)
         {   
            decadeRank = lineDataReport.nextInt();   
            System.out.println(yearCounter + ": " + decadeRank);
            if(decadeRank !=0 && decadeRank < topRank)
            {
               topRank = decadeRank;
               popularYear = yearCounter;              
            }         
            yearCounter += 10; 
         }
         System.out.println(name + " was most popular in " + popularYear); 
      }        
   }  
}