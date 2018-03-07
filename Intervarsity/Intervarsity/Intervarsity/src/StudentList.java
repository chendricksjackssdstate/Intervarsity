/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.*;
import java.io.File;
import java.util.*;
import java.io.IOException;
import java.util.Random;


/**
 *
 * @author Chris
 */

public class StudentList {
    private Convert convert;
    public StudentList(String semester, University schoo){
        convert = new Convert(); 
        String OS = System.getProperty("os.name").toLowerCase();
        if(OS.contains("windows")){
            directoryName = "C:/Users/";
        }
        else{
            directoryName = "/Users/";
        }
        currentsemester = semester;
        school = schoo;
        readstudent();
    }
    private void readstudent(){
        
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
            
        }
        else{
            
        }
        
        directoryName = directoryName + username + "/Google Drive/" + sch + "/Contacts/";
        String directoryName2 = directoryName + currentsemester + "/";
        File folder = new File(directoryName2);
        File[] listOfFiles = folder.listFiles();
        int filecount = listOfFiles.length;
        int k = 0;
        String[] filename = new String[filecount];
        
        for (int i = 0; i < listOfFiles.length; i++) {
          if (listOfFiles[i].isFile()) {
              if(listOfFiles[i].getName().equalsIgnoreCase(".DS_Store")){
                  
              }
              else if(listOfFiles[i].getName().contains(".sql")){
                 filename[k] = listOfFiles[i].getName();
                 k++;
              }
          } else if (listOfFiles[i].isDirectory()) {
            
          }
        }
        int i = 0;
        while(i < filename.length){
            readrecord(directoryName + currentsemester + "/" + filename[i]);
            i++;
        }
        
        
        
    }
    public void getStudentList(StudentNode[] list){
        List<StudentNode> temp = new LinkedList<StudentNode>();
        int i = 0;
        while(head.size() != 0){
            list[i] = head.remove(0);
            temp.add(list[i]);
            i++;
        }
        head = temp;
    }
    public void update(StudentNode s1, String originalname){
        StudentNode student = new StudentNode();
        List<StudentNode> temp = new LinkedList<StudentNode>();
        while(head.size() != 0){
            student = head.remove(0);
            
            if(student.name.equalsIgnoreCase(originalname)){
                if(!student.semester.equalsIgnoreCase(s1.semester)){
                    File file = new File(directoryName + student.semester + "/" + student.contactid + ".sql");
                    file.delete();
                }
                s1.contactid = student.contactid;
                student = s1;
                student.updated = true;
            }
            
            temp.add(student);
        }
        
        head = temp;
        savelist();
    }
    private void readrecord(String filename){
        
        try(BufferedReader inFile = new BufferedReader(new FileReader(filename))){
            
            StudentNode student = new StudentNode();
            student.contactid = Integer.parseInt(convert.unhash(inFile.readLine()));
            student.name = convert.unhash(inFile.readLine());
            String year = convert.unhash(inFile.readLine());
            
            if(year.equals("FRESHMAN")){
                student.studentyear = Year.FRESHMAN;
            }
            else if(year.equals("SOPHOMORE")){
                student.studentyear = Year.SOPHOMORE;
            }
            else if(year.equals("JUNIOR")){
                student.studentyear = Year.JUNIOR;
            }
            else if(year.equals("SENIOR")){
                student.studentyear = Year.SENIOR;
            }
            else{
                student.studentyear = Year.GRADSTUDENT;
            }
            student.offcampus = setBool(convert.unhash(inFile.readLine()));
            student.residencehall = convert.unhash(inFile.readLine());
            student.phone = convert.unhash(inFile.readLine());
            student.email = convert.unhash(inFile.readLine());
            String preference = convert.unhash(inFile.readLine());
            if(preference.equals("PHONE")){
                student.studentcontact = PreferContact.PHONE;
            }
            else if(preference.equals("TEXT")){
                student.studentcontact = PreferContact.TEXT;
            }
            else if(preference.equals("EMAIL")){
                student.studentcontact = PreferContact.EMAIL;
            }
            else{
                student.studentcontact = PreferContact.NEITHER;
            }
            //String permission = inFile.readLine();
            student.biblestudy = setBool(convert.unhash(inFile.readLine()));
            student.meetpeople = setBool(convert.unhash(inFile.readLine()));
            student.talkjesus = setBool(convert.unhash(inFile.readLine()));
            student.Findchurch = setBool(convert.unhash(inFile.readLine()));
            student.Learniv = setBool(convert.unhash(inFile.readLine()));
            student.funevents = setBool(convert.unhash(inFile.readLine()));
            student.other = setBool(convert.unhash(inFile.readLine()));
            student.nothing = setBool(convert.unhash(inFile.readLine()));
            student.otherstring = convert.unhash(inFile.readLine());
            student.staffcontact = convert.unhash(inFile.readLine());
            student.dateadded = convert.unhash(inFile.readLine());
            student.semester = convert.unhash(inFile.readLine());
            student.facetoface = setBool(convert.unhash(inFile.readLine()));
            student.callemail = setBool(convert.unhash(inFile.readLine()));
            student.twoxconnection = setBool(convert.unhash(inFile.readLine()));
            student.threexconnection = setBool(convert.unhash(inFile.readLine()));
            student.lginvite = setBool(convert.unhash(inFile.readLine()));
            student.sginvite = setBool(convert.unhash(inFile.readLine()));
            student.notes = "";
            String oneline;
            while((oneline = convert.unhash(inFile.readLine())) != null){
                student.notes = student.notes + oneline + "\n";
            }
            student.updated = false;
            head.add(student);
            inFile.close();
        }
        catch(IOException e){
        }
    }
    public Boolean savelist(){
        
        StudentNode student = new StudentNode();
        
        int i = 0;
        while(i < head.size()){
            
            student = head.remove(i);
            if(student.updated){
                    String directory = directoryName + student.semester + "/" + student.contactid + ".sql";

                try(BufferedWriter outFile = new BufferedWriter(new FileWriter(directory))){

                    outFile.write(convert.hash(String.valueOf(student.contactid))+ "\n");
                    outFile.write(convert.hash(student.name )+ "\n");
                    if(student.studentyear == Year.FRESHMAN){
                        outFile.write(convert.hash("FRESHMAN") + '\n');
                    }
                    else if(student.studentyear == Year.SOPHOMORE){
                        outFile.write(convert.hash("SOPHOMORE") + '\n');
                    }
                    else if(student.studentyear == Year.JUNIOR){
                        outFile.write(convert.hash("JUNIOR") + "\n");
                    }
                    else if(student.studentyear == Year.SENIOR){
                        outFile.write(convert.hash("SENIOR") + '\n');
                    }
                    else{
                        outFile.write(convert.hash("GRADSTUDENT") + "\n");
                    }
                    if(student.offcampus){
                        outFile.write(convert.hash("true") + "\n");
                    }
                    else{
                        outFile.write(convert.hash("false") + '\n');
                    }
                    outFile.write(convert.hash(student.residencehall) + "\n");
                    outFile.write(convert.hash(student.phone) + "\n");
                    outFile.write(convert.hash(student.email) + "\n");
                    if(student.studentcontact == PreferContact.PHONE){
                        outFile.write(convert.hash("PHONE") + "\n");
                    }
                    else if(student.studentcontact == PreferContact.TEXT){
                        outFile.write(convert.hash("TEXT") + "\n");
                    }
                    else if(student.studentcontact == PreferContact.EMAIL){
                        outFile.write(convert.hash("EMAIL") + "\n");
                    }
                    else{
                        outFile.write(convert.hash("NEITHER") + "\n");
                    }
                    if(student.biblestudy){
                        outFile.write(convert.hash("true") + "\n");
                    }
                    else{
                        outFile.write(convert.hash("false") + "\n");
                    }
                    if(student.meetpeople){
                        outFile.write(convert.hash("true") + "\n");
                    }
                    else{
                        outFile.write(convert.hash("false") + "\n");
                    }
                    if(student.talkjesus){
                        outFile.write(convert.hash("true") + "\n");
                    }
                    else{
                        outFile.write(convert.hash("false") + "\n");
                    }
                    if(student.Findchurch){
                        outFile.write(convert.hash("true") + "\n");
                    }
                    else{
                        outFile.write(convert.hash("false") + "\n");
                    }
                    if(student.Learniv){
                        outFile.write(convert.hash("true") + "\n");
                    }
                    else{
                        outFile.write(convert.hash("false") + "\n");
                    }
                    if(student.funevents){
                        outFile.write(convert.hash("true") + "\n");
                    }
                    else{
                        outFile.write(convert.hash("false") + "\n");
                    }
                    if(student.other){
                        outFile.write(convert.hash("true") + "\n");
                    }
                    else{
                        outFile.write(convert.hash("false") + "\n");
                    }
                    if(student.nothing){
                        outFile.write(convert.hash("true") + "\n");
                    }
                    else{
                        outFile.write(convert.hash("false") + "\n");
                    }
                    outFile.write(convert.hash(student.otherstring) + "\n");
                    outFile.write(convert.hash(student.staffcontact) + "\n");
                    outFile.write(convert.hash(student.dateadded) + "\n");
                    outFile.write(convert.hash(student.semester) + "\n");
                    if(student.facetoface){
                        outFile.write(convert.hash("true") + "\n");
                    }
                    else{
                        outFile.write(convert.hash("false") + "\n");
                    }
                    if(student.callemail){
                        outFile.write(convert.hash("true") + "\n");
                    }
                    else{
                        outFile.write(convert.hash("false") + "\n");
                    }
                    if(student.twoxconnection){
                        outFile.write(convert.hash("true") + "\n");
                    }
                    else{
                        outFile.write(convert.hash("false") + "\n");
                    }
                    if(student.threexconnection){
                        outFile.write(convert.hash("true") + "\n");
                    }
                    else{
                        outFile.write(convert.hash("false") + "\n");
                    }
                    if(student.lginvite){
                        outFile.write(convert.hash("true") + "\n");
                    }
                    else{
                        outFile.write(convert.hash("false") + "\n");
                    }
                    if(student.sginvite){
                        outFile.write(convert.hash("true") + "\n");
                    }
                    else{
                        outFile.write(convert.hash("false") + "\n");
                    }
                    outFile.write(convert.hash(student.notes) + "\n");
                    outFile.close();


                }
                catch(IOException e){

                    return false;
                }
            }
            
            
        }
        
        return true;
        
    }
    private Boolean setBool(String input){
        if(input.equals("true")){
            return true;
        }
        else{
            return false;
        }
    }
    public Boolean insert(StudentNode student){
        Random temp = new Random();
        int contact = temp.nextInt(999999999);

        student.contactid = contact;
        student.updated = true;
        head.add(student);
        
        return true;
    }
    public Boolean setdelete(String name){
        Boolean status = false;
        List<StudentNode> temp = new LinkedList<StudentNode>();
        StudentNode s1 = new StudentNode();
        
        while(head.size() != 0){
            s1 = head.remove(0);
            if(name.equals(s1.name)){
                File file = new File(directoryName + "/" + s1.semester + "/" + s1.contactid + ".sql");
                file.delete();
                status = true;
            }
            else{
                temp.add(s1);
            }
        }
        head = temp;
        
        return status;
    }
    public StudentNode search(String name){
        //System.out.println("Searching");
        StudentNode s1 = new StudentNode();
        s1.contactid = -883344;
        StudentNode s2 = new StudentNode();
        List<StudentNode> temp = new LinkedList<StudentNode>();
        //System.out.println("Real Searching");
        if(head.size() != 0){
            while(head.size() != 0){
            
            s2 = head.remove(0);
            temp.add(s2);
            if(s2.name.contains(name) || s2.email.contains(name) || s2.phone.contains(name)){
                s1 = s2;
            }
        }
        head = temp;
        }
        
        return s1;
    }
    public int getStudentCount(){
        return head.size();
    }
    //private Stack<int> contactid = new Stack<int>();
    //private Stack contactid = new Stack();
    //private Stack<int> contactid = new LinkedList<int>();
    private List<StudentNode> head = new LinkedList<StudentNode>();
    private University school;
    private String currentsemester;
    String directoryName = "/Users/";
}