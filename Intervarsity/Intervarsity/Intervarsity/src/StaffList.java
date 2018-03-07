/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.*;
import java.util.*;
/**
 *
 * @author Chris
 */
//This class holds all the staff read in from Google Drive
public class StaffList {
    StaffList(){
        String username = System.getProperty("user.name");
        String OS = System.getProperty("os.name").toLowerCase();
        if(OS.contains("windows")){
            directory = "C:/Users/" + username + "/Google Drive/IVCC/staff.ivcc";
        }
        else{
            directory = "/Users/" + username + "/Google Drive/IVCC/staff.ivcc";
        }
        
        //head = null;
        readfile();
    }
    public String hash(String name){
        String hashcode = null;
        char letter;
        int code;
        int i = 0;
        while(i < name.length()){
            letter = name.charAt(i);
            code = letter + 144;
            hashcode = hashcode + Integer.toString(code);
            i++;
        }
        return hashcode;
    }
    public StaffNode getNode(String username){
        StaffNode node = new StaffNode();
        List<StaffNode> temp = new LinkedList<>();
        while(!head.isEmpty()){
            StaffNode node1 = head.remove(0);
            if(node1.username.equals(username)){
                node = node1;
            }
            temp.add(node1);
        }
        head = temp;
        return node;
    }
        public StaffNode getName(String fullname){
        StaffNode node = new StaffNode();
        List<StaffNode> temp = new LinkedList<>();
        while(!head.isEmpty()){
            StaffNode node1 = head.remove(0);
            if(node1.fullname.equals(fullname)){
                node = node1;
            }
            temp.add(node1);
        }
        head = temp;
        return node;
    }
    
    private void readfile(){
        
        
        try(BufferedReader inFile = new BufferedReader(new FileReader(directory))){
            String oneline;
            while((oneline = inFile.readLine()) != null){
                String[] array = oneline.split("~");
                Boolean admin;
                University school;
                if(array[4].equals("true")){
                    admin = true;
                }
                else{
                    admin = false;
                }
                if(array[5].equals("ALL")){
                    school = University.ALL;
                }
                else if(array[5].equals("SOUTHDAKOTASTATE")){
                    school = University.SOUTHDAKOTASTATE;
                }
                else if(array[5].equals("DAKOTASTATE")){
                    school = University.DAKOTASTATE;
                }
                else{
                    school = University.NORTHERNSTATE;
                }
                StaffNode node = new StaffNode();
                node.staffid = Integer.parseInt(array[0]);
                node.fullname = array[1];
                node.username = array[2];
                node.password = array[3];
                node.admin = admin;
                node.school = school;
                head.add(node);
                
            }
            inFile.close();
        }
        catch(IOException e){
            
        }
    }
    public void getStafflist(String[] stafflist, University school){
        if(head.size() != 0){
            List<StaffNode> s1 = new LinkedList<StaffNode>();
            StaffNode temp = new StaffNode();
            int i = 0;
            int k = 0;
            while(i < head.size()){
                temp = head.remove(i);
                if(temp.school == University.ALL || temp.school == school){
                    stafflist[k] = temp.fullname;
                    k++;
                }
                s1.add(temp);
            }
            head = s1;
        }
    }
    public void getStafflist(String[] stafflist){
        if(head.size() != 0){
            List<StaffNode> s1 = new LinkedList<StaffNode>();
            StaffNode temp = new StaffNode();
            int i = 0;
            int k = 0;
            while(i < head.size()){
                temp = head.remove(i);
                stafflist[k] = temp.fullname;
                k++;
                s1.add(temp);
            }
            head = s1;
        }
    }
    
    public Boolean savelist(){
        if(head.size() != 0){
            try(BufferedWriter outFile = new BufferedWriter(new FileWriter(directory))){
            
            StaffNode temp = new StaffNode();
            int i = 0;
            while(i < head.size()){
                temp = head.remove(i);
                String school, admin;
                
                if(temp.admin){
                    admin = "true";
                }
                else{
                    admin = "false";
                }
                if(temp.school == University.ALL){
                    school = "ALL";
                }
                else if(temp.school == University.SOUTHDAKOTASTATE){
                    school = "SOUTHDAKOTASTATE";
                }
                else if(temp.school == University.DAKOTASTATE){
                    school = "DAKOTASTATE";
                }
                else{
                    school = "NORTHERNSTATE";
                }
                outFile.write(temp.staffid + "~" + temp.fullname + "~" + temp.username + "~" + temp.password + "~" + admin + "~" + school + "\n");
            }
            
            outFile.close();
            return true;
        }
        catch(IOException e){
            return false;
        }
        
    }
        return true;
}
    public Boolean setDelete(String fullname){
        StaffNode temp = new StaffNode();
        List<StaffNode> temp1 = new LinkedList<StaffNode>();
        int i = 0;
        Boolean status = false;
        while(i < head.size()){
            temp = head.remove(i);
            
            if(fullname.equals(temp.fullname)){
                
                status = true;
            }
            else {
                
                temp1.add(temp);
            }
        }
        i = 0;
        while(i < temp1.size()){
            temp = temp1.remove(i);
            head.add(temp);
        }
        savelist();
        return status;
    }
    public void insert(String fullname, String username, String password, Boolean admin, University school){
        StaffNode temp = new StaffNode();
        Random rand = new Random();
        temp.staffid = rand.nextInt();
        temp.username = username;
        temp.fullname = fullname;
        temp.password = password;
        temp.admin = admin;
        temp.school = school;
        head.add(temp);
        
        
    }
    public boolean update(StaffNode node){
        List<StaffNode> temp = new LinkedList<>();
        
        while(!head.isEmpty()){
            StaffNode node1 = head.remove(0);
            if(node.username.equals(node1.username)){
                node1 = node;
            }
            temp.add(node1);
        }
        
        head = temp;

        while(!savelist());
        return true;
    }
    public Boolean checkcredentials(String username, String password){
        Boolean status = false;
        StaffNode node = new StaffNode();
        List<StaffNode> temp = new LinkedList<StaffNode>();
        int i = 0;
        while(i < head.size()){
            node = head.remove(i);
            temp.add(node);
            if(username.equals(node.username) && password.equals(node.password)){
                status = true;
            }
        }
        
        head = null;
        head = temp;
        return status;
    }
    public int getStaffCount(){
        return head.size();
    }
    private List<StaffNode> head = new LinkedList<StaffNode>();
    private String directory;
}
