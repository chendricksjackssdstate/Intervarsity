
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Chris
 */
public class BibleStudy {
    BibleStudy(University school, String semester){
        this.school = school;
        this.semester = semester;
        if(school == University.SOUTHDAKOTASTATE)
            university = "SDSUIVCC";
        else if(school == University.NORTHERNSTATE)
            university = "NSUIVCC";
        else if(school == University.DAKOTASTATE)
            university = "DSUIVCC";
        
        String username = System.getProperty("user.name");
        String OS = System.getProperty("os.name").toLowerCase();
        if(OS.contains("windows")){
            directory = "C:/Users/" + username + "/Google Drive/" + university + "/ETC/BibleStudy/" + semester + ".ivcc";
        }
        else{
            directory = "/Users/" + username + "/Google Drive/" + university + "/ETC/BibleStudy/" + semester + ".ivcc";
        }
    }
    String oneline;
    private void readfile(){
        File file = new File(directory);
        if(file.exists()){
            try(BufferedReader inFile = new BufferedReader(new FileReader(directory))){
                while((oneline = inFile.readLine()) != null){
                    head.add(oneline);
                }
                inFile.close();
            }
            catch(IOException e){
            
            }
        }
        else{
            try {
                file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(BibleStudy.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    public void insert(String name){
        head.add(name);
    }
    public boolean savelist(){
        
        try {
            BufferedWriter outFile = new BufferedWriter(new FileWriter(directory));
            String oneline;
            while(!head.isEmpty()){
                oneline = head.remove(0);
                outFile.write(oneline);
            }
            outFile.close();
        } catch (IOException ex) {
            Logger.getLogger(BibleStudy.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true;
    }
    
    private List<String> head = new LinkedList<String>();
    private String semester;
    private University school;
    private String university;
    private String directory = null;
}
