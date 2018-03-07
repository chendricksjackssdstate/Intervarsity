//read in list of semesters in google drive


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


public class SemesterList {
    //Initialize items needed to read in semesters from file
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
            directory1 = "C:/Users/" + username + "/Google Drive/" + sch + "/Contacts/";//points to contact folder
            directory2 = "C:/Users/" + username + "/Google Drive/" + sch + "/ETC/semgroup.sql";//points to semester folder
        }
        else{
            directory1 = "/Users/" + username + "/Google Drive/" + sch + "/Contacts/";//points to contact folder
            directory2 = "/Users/" + username + "/Google Drive/" + sch + "/ETC/semgroup.sql";//points to semester folder
        }
        
        //read all semesters
        readlist();
        //sort semesters placing most recent on top
        sort();
        //check if semesters are empty
        if(head.isEmpty()){
            createsemester();
        }
        //check if most recent semester is current
        else{
            if(!semestercurrent()){
                createsemester();
            }
        }

        
        
    }
    //reads in list of semesters from file
    private void readlist(){
        //open the semester file for read
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
    //return the number of semesters
    public int getSemCount(){
        return head.size();
    }
    //save the list of semesters to Google Drive
    public boolean savelist(){
        sort();
        //open the file for write
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
    //sorts the list of semesters based on date created, placing most recent on top
    public void sort(){
        array = getArray();
        
        
        boolean swap;
        SemesterNode temp;
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
    //determines if there is a semester that is current
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
        //check if the years are same
        if(semesteryear.equals(currentyear)){
            //Convert the month and day to decimal point for sorting(Month.Day)
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
    
    //creates new semester, semester is not current
    private void createsemester(){
        //get current date
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
    
    //deletes the directory from Contacts folder in google drive
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
    //deletes the semester from the list
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
    //fetches the list of semester names
    public String[] getSemesterNames(){
        String[] names = new String[head.size()];
        sort();
        for(int i = 0; i < array.length; i++){
            names[i] = array[i].semestername;
        }
        return names;
    }
    //fetches the current semester
    public String getCurrentSemester(){
        sort();
        return array[0].semestername;
    }
    //inserts the new semester into list
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
    //gets the current date from the system
    private void getDate(){
        Calendar cal = Calendar.getInstance();
        currentmonth = new SimpleDateFormat("M").format(cal.getTime());
        currentday = new SimpleDateFormat("dd").format(cal.getTime());
        currentyear = new SimpleDateFormat("YYYY").format(cal.getTime());
        
    }
    //returns an array of the list of semesters
    private SemesterNode[] getArray(){
        SemesterNode[] array = new SemesterNode[head.size()];
        head.toArray(array);
        return array;
    }
    SemesterNode[] array;
    private String currentmonth;
    private String currentday;
    private String currentyear;
    private University school;
    private String directory1, directory2;//directory1 points to contacts folder
                                          //directory2 points to etc folder
    private List<SemesterNode> head = new LinkedList<>();
}
