/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codejam.qround2012;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TreeSet;

/**
 *
 * @author Rostand
 */
public class SpeakingInTongue {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            String filename="A-small-practice.in";

            List<TestCase> testCases= TestCase.loadTestCases(filename);
            FileWriter writer= new FileWriter(filename.replace(".in", ".out"));
            
            String[] input1={"ejp mysljylc kd kxveddknmc re jsicpdrysi", "rbcpc ypc rtcsra dkh wyfrepkym veddknkmkrkcd",
            "de kr kd eoya kw aej tysr re ujdr lkgc jv"};
          

            String[] output1={"our language is impossible to understand", "there are twenty six factorial possibilities",
            "so it is okay if you want to just give up"};
           
            
            Map<Character, Character> codeMap= new HashMap<>();
            codeMap.put('q', 'z');
            codeMap.put('z', 'q');
            for(int i=0; i<output1.length; i++){
                String output= output1[i];
                String input= input1[i];
                for(int j=0;j<output.length(); j++){
                   codeMap.put(input.charAt(j), output.charAt(j));
                }
            }
            List<Character> l=new ArrayList<>(codeMap.values());
            Collections.sort(l);
            System.out.println(l);
                   
            int i=1;
            for(TestCase tCase: testCases){
                String out= "Case #"+(i++)+": "+solveTestCase(tCase, codeMap)+"\n";
                writer.write(out);
                System.out.print(out);
            }
            writer.flush();
            writer.close();
        }
        catch(Exception ex){
            
        }
    }
    
    private static Object solveTestCase(TestCase tCase,  Map<Character, Character> codeMap){
        
        StringBuilder sb= new StringBuilder();
        for(Character ch: tCase.input.toCharArray())
            sb.append(codeMap.get(ch));
        
        return sb.toString();
    }
    
}

class TestCase{
    
   String input;
    
    public static List<TestCase> loadTestCases(String filename){
        
        List<TestCase> result= new ArrayList<>();
        try{
            BufferedReader bf= new BufferedReader(new FileReader(filename));

            int T =Integer.parseInt(bf.readLine());
            for(int caseCount=1; caseCount<=T; caseCount++){
                TestCase tCase= new TestCase();
                tCase.input=bf.readLine().trim();
                result.add(tCase);
            }
            bf.close();
        }
        catch(Exception ex){}
        
        return result;
    }
}
