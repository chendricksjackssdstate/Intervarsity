/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chris
 */

public class SemesterList {
    SemesterList(University scho){
        school = scho;
        String sch = null;
        String username = System.getProperty("user.name");
        if(school == University.SOUTHDAKOTASTATE){
            sch = "SDSUIVCC";
        }
        else if(school == University.NORTHERNSTATE){
            sch = "NSUIVCC";
        }
        else if(school == University.DAKOTASTATE) {
            sch = "DSUIVCC";
        }
        
        String OS = System.getProperty("os.name").toLowerCase();
        if(OS.contains("windows")){
            directory1 = "C:/Users/" + username + "/Google Drive/" + sch + "/Contacts/";
            directory2 = "C:/Users/" + username + "/Google Drive/" + sch + "/ETC/semgroup.sql";
        }
        else{
            directory1 = "/Users/" + username + "/Google Drive/" + sch + "/Contacts/";
            directory2 = "/Users/" + username + "/Google Drive/" + sch + "/ETC/semgroup.sql";
        }
        
        
        readlist();
        sort();
        if(head.isEmpty()){
            createsemester();
        }
        else{
            if(!semestercurrent()){
                createsemester();
            }
        }

        
        
    }
    
    private void readlist(){
        try(BufferedReader inFile = new BufferedReader(new FileReader(directory2))){
            String oneline;
            while((oneline = inFile.readLine()) != null){
                SemesterNode temp = new SemesterNode();
                String[] data = oneline.split("~");
                temp.semestername = data[0];
                temp.datecreated = data[1];
                head.add(temp);
            }
            inFile.close();
        }
        catch(IOException e){
            
        }
    }
    public int getSemCount(){
        return head.size();
    }
    public boolean savelist(){
        sort();
        try(BufferedWriter outFile = new BufferedWriter(new FileWriter(directory2))){
            while(!head.isEmpty()){
                SemesterNode node = head.remove(0);
                outFile.write(node.semestername + "~" + node.datecreated + "\n");
            }
            outFile.close();
        }
        catch(IOException e){
            return false;
        }
        return true;
    }
    public void sort(){
        array = new SemesterNode[head.size()];
        getArray(array);
        
        boolean swap;
        SemesterNode temp = new SemesterNode();
        do{
           swap = false;
           for(int count = 0; count < (array.length - 1); count++){
               try {
                   Date date1, date2;
                   DateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
                   date1 = format.parse(array[count].datecreated);
                   date2 = format.parse(array[count + 1].datecreated);
                   if(date2.after(date1)){
                       temp = array[count];
                       array[count] = array[count + 1];
                       array[count + 1] = temp;
                       swap = true;
                   }
               } catch (ParseException ex) {
                   Logger.getLogger(SemesterList.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
        }while(swap);
        head = null;
        head = new LinkedList<>();
        for(int i = 0; i < array.length; i++){
            head.add(array[i]);
        }
    }
    private boolean semestercurrent(){
        getDate();
        SemesterNode node = array[0];
        String[]data = node.datecreated.split("/");
        String semestermonth = data[0];
        String semesterday = data[1];
        String semesteryear = data[2];
        String currentdate, semesterdate;
        currentdate = currentmonth + "/" + currentday;
        semesterdate = semestermonth + "/" + semesterday;
        
        if(semesteryear.equals(currentyear)){
            //Spring Semester
            String date = currentdate.replace("/", ".");
            double cdate = Double.parseDouble(date);
            //1.1 && 6.15
            if(cdate > 1.01 && cdate < 7.32){
                date = semesterdate.replace("/", ".");
                double sdate = Double.parseDouble(date);
                //1.1 && 6.15
                if(sdate >= 1.01 && sdate <= 7.31){
                    return true;
                }
                else{
                    return false;
                }
            }
            //Fall Semester
            else{
                date = semesterdate.replace("/", ".");
                double sdate = Double.parseDouble(date);
                //8.1 && 12.31
                if(sdate >= 8.01 && sdate <= 12.32){
                    return true;
                }
                else{
                    return false;
                }
            }
        }
        else{
            return false;
        }
    }
    private void createsemester(){
        getDate();
        String currentdate;
        String semestername;
        currentdate = currentmonth + "/" + currentday;
        String date = currentdate.replace("/", ".");
        double cdate = Double.parseDouble(date);
        //1.1 && 7.15
        if(cdate > 1.00 && cdate < 7.32){
            semestername = "Spring " + currentyear;
            
            
        }
        else{
            semestername = "Fall " + currentyear;
        }
        insert(semestername);
        
    }
    private boolean deleteDir(File dir) {
      if (dir.isDirectory()) {
         String[] children = dir.list();
         for (int i = 0; i < children.length; i++) {
            boolean success = deleteDir (new File(dir, children[i]));
            
            if (!success) {
               return false;
            }
         }
      }
      return dir.delete();
      
   }
    public boolean deleteSemester(String name){
        boolean status = false;
        SemesterNode node;
        List<SemesterNode> temp = new LinkedList<>();
        while(!head.isEmpty()){
            node = head.remove(0);
            if(name.equals(node.semestername)){
                File file = new File(directory1 + name);
                if(file.isDirectory()){
                   status = deleteDir(file);
                }
            }
            else{
                temp.add(node);
            }
        }
        head = temp;
        sort();
        while(!savelist());
        return status;
    }
    public void getSemesterNames(String[] names){
        sort();
        for(int i = 0; i < array.length; i++){
            names[i] = array[i].semestername;
        }
    }
    public String getCurrentSemester(){
        sort();
        return array[0].semestername;
    }
    public void insert(String name){
        SemesterNode node = new SemesterNode();
        node.semestername = name;
        node.datecreated = currentmonth + "/" + currentday + "/" + currentyear;
        head.add(node);
        try {
            Files.createDirectories(Paths.get(directory1 + name));
        }
        catch(IOException e){
                
        }
        sort();
    }
    private void getDate(){
        Calendar cal = Calendar.getInstance();
        currentmonth = new SimpleDateFormat("M").format(cal.getTime());
        currentday = new SimpleDateFormat("dd").format(cal.getTime());
        currentyear = new SimpleDateFormat("YYYY").format(cal.getTime());
        
    }
    private void getArray(SemesterNode[] array){
        head.toArray(array);
    }
    SemesterNode[] array;
    private String currentmonth;
    private String currentday;
    private String currentyear;
    private University school;
    private String directory1, directory2;
    private List<SemesterNode> head = new LinkedList<>();
}
