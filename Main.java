/*
author: Advait Deochakke
date: 9/April/2022
CSE 1007 Digital Assignment - 1
On my honor, this programming assignment is my own work.
*/

import java.util.*;
import java.io.*;

public class Main{

  static<myGen> void print(myGen s)
  {
    System.out.println(s);
  }

  static void wowSoGood(ResultBase x, Scanner reader)
  {
    String resultLine=reader.nextLine();
    for(int i=0; i<10; i++)
      { 
        for(int j=0; j<7; j++)
          {
            int finder=i*7+j;
            String ans=(""+resultLine.charAt(finder)).toLowerCase();
            
            switch(j){
              case 0:
                x.ei.add(ans);
                break;
              case 1:
                x.sn.add(ans);
                break;
              case 3:
                x.tf.add(ans);
                break;
              case 5:
                x.jp.add(ans);
                break;
              case 2:
                x.sn.add(ans);
                break;
              case 4:
                x.tf.add(ans);
                break;
              case 6:
                x.jp.add(ans);
                break;
            }
          }
      }
  }
  
  static void takeInput(ArrayList<ResultBase> testResults)
  {
    try{
      File one = new File("Keirsey.txt");
      print("Successfully opened Input File");
      Scanner reader = new Scanner(one);
      while(reader.hasNextLine())
        {
          ResultBase x= new ResultBase();
          x.name=reader.nextLine();
          wowSoGood(x, reader);
          testResults.add(x);
        }
      reader.close();
      print("Input file closed");
    }
    catch(Exception e)
      {
        print(e);
      }
  
  }

  static void findPercent(ArrayList<String> x, ResultFinal toBeAdded, String A, String B)
  {
    int aCount=0;
    int bCount=0;
    int tCount=0;
    int bCent=0;
    String a="a";
    String b="b";
    for(String check:x)
      {
        if(check.equals(a))
        {
          aCount++;
        }
        if(check.equals(b))
        {
          bCount++;
        }
      }
    tCount=aCount+bCount;
    bCent=(int)((((double)bCount)/((double)tCount))*100);
    toBeAdded.vals.add(bCent);
    if(bCent>50)
    {
      toBeAdded.personalityType.add(B);
    }
    if(bCent<50)
    {
      toBeAdded.personalityType.add(A);
    }
    if(bCent==50)
    {
      toBeAdded.personalityType.add("X");
    }
  }

  static void processInput(ArrayList<ResultFinal> finalResults, ArrayList<ResultBase> testResults)
  {
    for(ResultBase x : testResults)
      {
        ResultFinal toBeAdded = new ResultFinal();
        toBeAdded.name=x.name;
        findPercent(x.ei, toBeAdded, "E", "I");
        findPercent(x.sn, toBeAdded, "S", "N");
        findPercent(x.tf, toBeAdded, "T", "F");
        findPercent(x.jp, toBeAdded, "J", "P");
        finalResults.add(toBeAdded);
      }
  }

  static void displayOutput(ArrayList<ResultFinal> finalResults)
  {
    try{
      FileWriter two = new FileWriter("results.txt");
      print("Successfully opened result file");
      two.write("Kerisey Test Results : 20BCE1143");
    
      for(ResultFinal x : finalResults)
        {
          String toWrite="\n";
          toWrite=toWrite+x.name+": "+x.vals+" = ";
          for(String y : x.personalityType)
            {
              toWrite=toWrite+y;
            }
          two.write(toWrite);
        }
  
      two.close();
      print("Result file closed");
    }
    catch(Exception e)
      {
        print(e);
      }
    
  }

  static class ResultBase
  {
    String name;
    ArrayList<String> ei = new ArrayList<String>();
    ArrayList<String> sn = new ArrayList<String>();
    ArrayList<String> tf = new ArrayList<String>();
    ArrayList<String> jp = new ArrayList<String>();
    
  }

  static class ResultFinal
  {
    String name;
    ArrayList<Integer> vals = new ArrayList<Integer>();
    ArrayList<String> personalityType = new ArrayList<String>();
  }

  public static void main(String args[])
  {
    print("Processing of Resulsts initializing . . .\n20BCE1143");
    ArrayList<ResultBase> testResults = new ArrayList<ResultBase>();
    ArrayList<ResultFinal> finalResults = new ArrayList<ResultFinal>();
    takeInput(testResults);
    print("Results successfully read from 'Keirsey.txt'");
    processInput(finalResults, testResults);
    print("Calculating output");
    displayOutput(finalResults);
    print("Outputted results to 'results.txt'");
  }
}
