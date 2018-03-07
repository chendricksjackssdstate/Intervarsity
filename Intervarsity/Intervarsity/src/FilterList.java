
//Class holds the filters for each staff member
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class FilterList {
    private String directory;
    private University school;//input: University schoo: SOUTHDAKOTASTATE, DAKOTASTATE, NORTHERNSTATE
    private int staffid;//staff member id number
    

    //creates instance of FilterList
    FilterList(University schoo, int staff){
        staffid = staff;
        school = schoo;
        String sch;
        
        //get operating system username
        String username = System.getProperty("user.name");
        if(school == University.SOUTHDAKOTASTATE){
            sch = "SDSUIVCC";
        }
        else if(school == University.NORTHERNSTATE){
            sch = "NSUIVCC";
        }
        else{
            sch = "DSUIVCC";
        }
        
        //determine if OS is windows or mac
        String OS = System.getProperty("os.name").toLowerCase();
        
        //set directory to folder in Google Drive
        if(OS.contains("windows")){
           directory = "C:/Users/" + username + "/Google Drive/" + sch + "/ETC/Filter/" + staffid + ".sql"; 
        }
        else{
           directory = "/Users/" + username + "/Google Drive/" + sch + "/ETC/Filter/" + staffid + ".sql"; 
        }
        //get all filter's for staff member from their staffid number
        readlist();
    }
    
    //read in the list of filters from Google Drive
    private void readlist(){
        System.out.write(staffid);
        String oneline;
        try(BufferedReader inFile = new BufferedReader(new FileReader(directory))){
            while((oneline = inFile.readLine()) != null){
                //29
                FilterNode filter = new FilterNode();
                String[] data = oneline.split("~");
                filter.name = data[0];
                filter.jCheckBox2 = readbool(data[1]);
                filter.jCheckBox3 = readbool(data[2]);
                filter.jCheckBox4 = readbool(data[3]);
                filter.jCheckBox5 = readbool(data[4]);
                filter.jCheckBox6 = readbool(data[5]);
                filter.jCheckBox7 = readbool(data[6]);
                filter.jCheckBox8 = readbool(data[7]);
                filter.jCheckBox9 = readbool(data[8]);
                filter.jCheckBox10 = readbool(data[9]);
                filter.jCheckBox11 = readbool(data[10]);
                filter.jCheckBox12 = readbool(data[11]);
                filter.jCheckBox13 = readbool(data[12]);
                filter.jCheckBox14 = readbool(data[13]);
                filter.jCheckBox15 = readbool(data[14]);
                filter.jRadioButton1 = readbool(data[15]);
                filter.jRadioButton2 = readbool(data[16]);
                filter.jRadioButton3 = readbool(data[17]);
                filter.jRadioButton4 = readbool(data[18]);
                filter.jRadioButton5 = readbool(data[19]);
                filter.jRadioButton6 = readbool(data[20]);
                filter.jRadioButton7 = readbool(data[21]);
                filter.jRadioButton8 = readbool(data[22]);
                filter.jRadioButton9 = readbool(data[23]);
                filter.jRadioButton12 = readbool(data[24]);
                filter.jComboBox1 = data[25];
                filter.jComboBox2 = data[26];
                filter.jComboBox3 = data[27];
                head.add(filter);
                
            }
            inFile.close();
        }
        catch(IOException e){
            
        }
    }
    
    //writes all filters in list to Google Drive
    public boolean SaveList(){
        
        try(BufferedWriter outFile = new BufferedWriter(new FileWriter(directory))){
            while(!head.isEmpty()){
                FilterNode filter = new FilterNode();
                filter = head.remove(0);
                outFile.write(filter.name);
                outFile.write(writebool(filter.jCheckBox2));
                outFile.write(writebool(filter.jCheckBox3));
                outFile.write(writebool(filter.jCheckBox4));
                outFile.write(writebool(filter.jCheckBox5));
                outFile.write(writebool(filter.jCheckBox6));
                outFile.write(writebool(filter.jCheckBox7));
                outFile.write(writebool(filter.jCheckBox8));
                outFile.write(writebool(filter.jCheckBox9));
                outFile.write(writebool(filter.jCheckBox10));
                outFile.write(writebool(filter.jCheckBox11));
                outFile.write(writebool(filter.jCheckBox12));
                outFile.write(writebool(filter.jCheckBox13));
                outFile.write(writebool(filter.jCheckBox14));
                outFile.write(writebool(filter.jCheckBox15));
                outFile.write(writebool(filter.jRadioButton1));
                outFile.write(writebool(filter.jRadioButton2));
                outFile.write(writebool(filter.jRadioButton3));
                outFile.write(writebool(filter.jRadioButton4));
                outFile.write(writebool(filter.jRadioButton5));
                outFile.write(writebool(filter.jRadioButton6));
                outFile.write(writebool(filter.jRadioButton7));
                outFile.write(writebool(filter.jRadioButton8));
                outFile.write(writebool(filter.jRadioButton9));
                outFile.write(writebool(filter.jRadioButton12));
                outFile.write("~" + filter.jComboBox1 + "~");
                outFile.write(filter.jComboBox2 + "~");
                outFile.write(filter.jComboBox3 + "\n");
                
            }
            outFile.close();
        }
        catch(IOException e){
            return false;
        }
        return true;
    }
    //return's the number of filters for a staff member
    public int getCount(){
        return head.size();
    }
    //returns if value is true or false as a string
    private String writebool(boolean value){
        if(value){
            return "~true";
        }
        else{
            return "~false";
        }
    }
    //returns true or false as boolean
    private boolean readbool(String value){
        return value.equals("true");
    }
    //inserts new filter into list
    //returns if insert was successful
    public boolean insert(FilterNode filter){
        head.add(filter);
        return true;
    }
    //deletes a node from FilterList
    public boolean delete(String name){
        FilterNode filter;
        List<FilterNode> temp = new LinkedList<>();
        while(!head.isEmpty()){
            filter = head.remove(0);
            if(filter.name.equals(name)){
                
            }
            else{
                temp.add(filter);
            }
        }
        head = temp;
        return true;
    }
    //returns all the names of the filters
    public String[] getFilterNames(){
        String[] names = new String[head.size()];
        List<FilterNode> temp = new LinkedList<>();
        int i = 0;
        while(!head.isEmpty()){
            FilterNode filter = new FilterNode();
            filter = head.remove(0);
            names[i] = filter.name;
            i++;
            temp.add(filter);
        }
        head = temp;
        return names;
    }
    //search's for a filter, then returns its attributes if it is found
    public FilterNode search(String filter){
        FilterNode node = new FilterNode();
        List<FilterNode> temp = new LinkedList<>();
        while(!head.isEmpty()){
            FilterNode node1 = head.remove(0);
            if(node1.name.equalsIgnoreCase(filter)){
                node = node1;
            }
            temp.add(node1);
        }
        head = temp;
        return node;
    }
    //linkedlist holds each filter
    private List<FilterNode> head = new LinkedList<FilterNode>();
}
