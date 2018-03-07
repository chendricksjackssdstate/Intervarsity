/*
Open the list of Residence Halls in Google Drive,
save the list as a LinkedList
*/


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class ResidenceHallList {
    //Read in the list of all residence halls for the specified university
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
        //fetch system username
        String username = System.getProperty("user.name");
        //fetch the Operating System type
        String OS = System.getProperty("os.name").toLowerCase();
        if(OS.contains("windows")){
            directory = "C:/Users/" + username + "/Google Drive/" + sch + "/ETC/residencehall.sql";
        }
        else{
            directory = "/Users/" + username + "/Google Drive/" + sch + "/ETC/residencehall.sql";
        }
        
        String oneline;
        //open the file for read
        try(BufferedReader inFile = new BufferedReader(new FileReader(directory))){
            while((oneline = inFile.readLine()) != null){
                insert(oneline);
            }
            inFile.close();
        }
        catch(IOException e){
            
        }
        
    }
    //save the list of residence halls
    public Boolean savehalls(){
        //open the file for read
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
    //fetches the list of residence halls
    public String[] getHallList(){
        String[] array = new String[head.size()];
        List<String> temp = new LinkedList<String>();
        int k = 0;
        while(head.size() != 0){
            array[k] = head.remove(0);
            temp.add(array[k]);
            k++; 
        }
        head = temp;
        return array;
    }
    //deletes the residence hall
    public Boolean delete(String hallname){
        //head each residence hall from LinkedList, if the name matches hallname,
        //  if it matches, do copy into temp LinkedList, if it doesn't, copy into
        //  temp LinkedList.
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
        //copy the temp linked list into head
        head = temp;
        return status;
    }
    //inserts the hallname into list
    public Boolean insert(String hallname){
        head.add(hallname);
        Collections.sort(head);
        return true;
    }
    //returns the number of residence halls
    public int getHallCount(){
        return head.size();
    }
    String directory;
    private List<String> head = new LinkedList<String>();
}
