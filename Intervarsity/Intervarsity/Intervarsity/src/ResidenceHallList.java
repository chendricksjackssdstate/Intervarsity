/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 *
 * @author Chris
 */
public class ResidenceHallList {
    ResidenceHallList(University school){
        String sch = null;
        if(school == University.SOUTHDAKOTASTATE){
            sch = "SDSUIVCC";
        }
        else if(school == University.NORTHERNSTATE){
            sch = "NSUIVCC";
        }
        else if(school == University.DAKOTASTATE){
            sch = "DSUIVCC";
        }
        String username = System.getProperty("user.name");
        String OS = System.getProperty("os.name").toLowerCase();
        if(OS.contains("windows")){
            directory = "C:/Users/" + username + "/Google Drive/" + sch + "/ETC/residencehall.sql";
        }
        else{
            directory = "/Users/" + username + "/Google Drive/" + sch + "/ETC/residencehall.sql";
        }
        
        String oneline;
        
        try(BufferedReader inFile = new BufferedReader(new FileReader(directory))){
            while((oneline = inFile.readLine()) != null){
                insert(oneline);
            }
            inFile.close();
        }
        catch(IOException e){
            
        }
        
    }
    public Boolean savehalls(){
        try(BufferedWriter outFile = new BufferedWriter(new FileWriter(directory))){
            int i = 0;
            while(i < head.size()){
                String name = head.remove(i);
                outFile.write(name + "\n");
            }
            outFile.close();
        }
        catch(IOException e){
            
        }
        
        
        
        
        
        return true;
    }
    public void getHallList(String[] array){
        List<String> temp = new LinkedList<String>();
        int i = 0;
        int k = 0;
        while(i < head.size()){
            String name = head.remove(i);
            array[k] = name;
            k++;
            temp.add(name);
        }
        head = temp;
    }
    public Boolean delete(String hallname){
        List<String> temp = new LinkedList<String>();
        Boolean status = false;
        int i = 0;
        while(i < head.size()){
            String name = head.remove(i);
            if(hallname.equalsIgnoreCase(name)){
                status = true;
            }
            else{
                temp.add(name);
            }
        }
        head = temp;
        return status;
    }
    public Boolean insert(String hallname){
        head.add(hallname);
        Collections.sort(head);
        return true;
    }
    public int getHallCount(){
        return head.size();
    }
    String directory;
    private List<String> head = new LinkedList<String>();
}
