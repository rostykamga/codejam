/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codejam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Rostand
 */
public class Solution {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{

            List<TestCase> testCases= TestCase.loadTestCases();
            int i=1;
            for(TestCase tCase: testCases){
                String out= "Case #"+(i++)+": "+solveTestCase(tCase)+"\n";
                System.out.print(out);
            }
        }
        catch(Exception ex){
            
        }
    }
    
    private static Object solveTestCase(TestCase tCase){

        int nbHack=0;
        String instructions= tCase.instructions;
        while(true){
            if(computeDamage(instructions)<=tCase.D)
                break;
            
            String swappedInstructions= swap(instructions);
           
            if(swappedInstructions.equals(instructions)){
               return "IMPOSSIBLE";
            }
            else{
                nbHack++;
                instructions=swappedInstructions;
            }
        }
        return nbHack;
    }
    
    private static String swap(String str){
        int lastShoot= str.lastIndexOf("S");
        char[] arr= str.toCharArray();
        int posOfC= lastShoot-1;
        while(posOfC>0 && arr[posOfC]!='C'){
            posOfC--;
        }
        if(arr[posOfC]=='C' && arr[posOfC+1]=='S'){
            arr[posOfC]='S';
            arr[posOfC+1]='C';
        }
        return new String(arr);  
    }
    
    
    private static int computeDamage(String instr){
        int damageLevel=1, totalDamage=0;
        
        for(char ch : instr.toCharArray()){
            if(ch=='C')
                damageLevel*=2;
            else
                totalDamage+=damageLevel;
        }
        return totalDamage;
    }
        
      
    
}

class TestCase{
    
    int D;
    String instructions;
    
    public static List<TestCase> loadTestCases(){
        
        List<TestCase> result= new ArrayList<>();
       
        try{
            Scanner scan= new Scanner(System.in);
            int N= scan.nextInt();
            scan.nextLine();
            
            for(int caseCount=1; caseCount<=N; caseCount++){
                TestCase tCase= new TestCase();
                
                // Reading the number of search engines
                String[] parts= scan.nextLine().trim().split(" ");
                tCase.D=Integer.parseInt(parts[0].trim());
                tCase.instructions=parts[1];
                
              
                result.add(tCase);
            }
        }
        catch(Exception ex){
        }
        
        return result;
    }
}
