import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;



//This class displays all the students.
public class View_All extends javax.swing.JFrame {


    /**
     * Creates new form View_All
     */
    public View_All(String full, Boolean ad, University sch, String sem, int id) {
        initComponents();
        this.setLocationRelativeTo(null);
        
        fullname = full;
        admin = ad;
        school = sch;
        semester = sem;
        staffid = id;
        model = (DefaultTableModel) jTable2.getModel();
        
        
        // Get Residence Halls
        jComboBox1.removeAllItems();
        jComboBox1.addItem("Select Residence Hall");
        jComboBox1.setSelectedItem("Select Residence Hall");
        
        ResidenceHallList hall = new ResidenceHallList(school);
        String[] halllist = new String[hall.getHallCount()];
        hall.getHallList(halllist);
        for(int i = 0; i < halllist.length; i++){
            jComboBox1.addItem(halllist[i]);
        }
        
        //Get Staff List
        jComboBox2.removeAllItems();
        jComboBox2.addItem("Select Staff");
        jComboBox2.addItem("No Staff Assigned");
        jComboBox2.setSelectedItem(fullname);
        StaffList staff = new StaffList();
        String[] stafflist = new String[staff.getStaffCount()];
        staff.getStafflist(stafflist, school);
        for(int i = 0; i < stafflist.length; i++){
            if(stafflist[i] != null)
                jComboBox2.addItem(stafflist[i]);
        }
        
        //Get Filters
        FilterList list = new FilterList(school, staffid);
        String[] names = new String[list.getCount()];
        list.getFilterNames(names);
        jComboBox5.removeAllItems();
        jComboBox5.addItem("Choose Filter");
        for(int i = 0; i < names.length; i++){
            jComboBox5.addItem(names[i]);
        }
        jComboBox5.setSelectedItem("Choose Filter");
        
        SemesterList semlist = new SemesterList(school);
        names = null;
        names = new String[semlist.getSemCount()];
        semlist.getSemesterNames(names);
        jComboBox3.removeAllItems();
        for(int i = 0; i < names.length; i++){
            jComboBox3.addItem(names[i]);
        }
        jComboBox3.setSelectedItem(semlist.getCurrentSemester());
        
        
        //filtertable();
        
        load = true;
        filtertable();
        load = false;
        old.jDateChooser2 = "";
        jComboBox2.setSelectedItem(fullname);
    }
    ViewAllAttributes old = new ViewAllAttributes();
    private boolean filterenabled = false;
    final private String fullname;
    final private Boolean admin;
    final private University school;
    final private String semester;
    private Boolean load = true;
    private DefaultTableModel model;
    private StudentNode[] student;
    private void filtertable(){
        
        
        if(model.getRowCount() != 0){
            model.setRowCount(0);
        }
        
        List<StudentNode> linkedlist = new LinkedList<>();
        
        int i = 0;
        
        
        boolean update = false;
        if(load == false){
            if(student.length == 0){
                StudentList list = new StudentList(jComboBox3.getSelectedItem().toString(), school);
                student = null;
                student = new StudentNode[list.getStudentCount()];
                list.getStudentList(student);
            }
            while(i < student.length){
                update = false;
                boolean added = false;


                if(jCheckBox2.isSelected()){
                    update = true;
                    old.jCheckBox2 = true;
                    if(student[i].biblestudy){
                        if(added == false){
                            added = true;
                            filterenabled = true;
                            linkedlist.add(student[i]);
                        }
                    }
                    else{
                        if(linkedlist.contains(student[i])){
                            linkedlist.remove(student[i]);
                        }
                    }

                }
                if(jCheckBox3.isSelected()){
                    update = true;
                    old.jCheckBox3 = true;
                    if(student[i].meetpeople){
                        if(added == false){
                            added = true;
                            filterenabled = true;
                            linkedlist.add(student[i]);
                        }
                    }
                    else{
                        if(linkedlist.contains(student[i])){
                            linkedlist.remove(student[i]);
                        }
                    }

                }

                if(jCheckBox4.isSelected()){
                    old.jCheckBox4 = true;
                    update = true;
                    if(student[i].talkjesus){
                        if(added == false){
                            added = true;
                            filterenabled = true;
                            linkedlist.add(student[i]);
                        }
                    }
                    else{
                        if(linkedlist.contains(student[i])){
                            linkedlist.remove(student[i]);
                        }
                    }

                }
                if(jCheckBox5.isSelected()){
                    update = true;
                    old.jCheckBox5 = true;
                    if(student[i].Findchurch){
                        if(added == false){
                            filterenabled = true;
                            added = true;
                            linkedlist.add(student[i]);
                        }
                    }
                    else{
                        if(linkedlist.contains(student[i])){
                            linkedlist.remove(student[i]);
                        }
                    }

                }
                if(jCheckBox6.isSelected()){
                    update = true;
                    old.jCheckBox6 = true;
                    if(student[i].Learniv){
                        if(added == false){
                            filterenabled = true;
                            added = true;
                            linkedlist.add(student[i]);
                        }
                    }
                    else{
                        if(linkedlist.contains(student[i])){
                            linkedlist.remove(student[i]);
                        }
                    }

                }
                if(jCheckBox7.isSelected()){
                    update = true;
                    old.jCheckBox7 = true;
                    if(student[i].funevents){
                        if(added == false){
                            filterenabled = true;
                            added = true;
                            linkedlist.add(student[i]);
                        }
                    }
                    else{
                        if(linkedlist.contains(student[i])){
                            linkedlist.remove(student[i]);
                        }
                    }

                }
                if(jCheckBox8.isSelected()){
                    update = true;
                    old.jCheckBox8 = true;
                    if(student[i].nothing){
                        if(added == false){
                            added = true;
                            filterenabled = true;
                            linkedlist.add(student[i]);
                        }
                    }
                    else{
                        if(linkedlist.contains(student[i])){
                            linkedlist.remove(student[i]);
                        }
                    }

                }
                if(jCheckBox9.isSelected()){
                    update = true;
                    old.jCheckBox9 = true;
                    if(student[i].other){
                        if(added == false){
                            added = true;
                            filterenabled = true;
                            linkedlist.add(student[i]);
                        }
                    }
                    else{
                        if(linkedlist.contains(student[i])){
                            linkedlist.remove(student[i]);
                        }
                    }

                }
                if(jRadioButton1.isSelected()){
                    update = true;
                    old.jRadioButton1 = true;
                    if(student[i].studentyear == Year.FRESHMAN){
                        if(added == false){
                            added = true;
                            filterenabled = true;
                            linkedlist.add(student[i]);
                        }
                    }
                    else{
                        if(linkedlist.contains(student[i])){
                            linkedlist.remove(student[i]);
                        }
                    }

                }
                if(jRadioButton2.isSelected()){
                    update = true;
                    old.jRadioButton2 = true;
                    if(student[i].studentyear == Year.SOPHOMORE){
                        if(added == false){
                            added = true;
                            filterenabled = true;
                            linkedlist.add(student[i]);
                        }
                    }
                    else{
                        if(linkedlist.contains(student[i])){
                            linkedlist.remove(student[i]);
                        }
                    }

                }
                if(jRadioButton3.isSelected()){
                    update = true;
                    old.jRadioButton3 = true;
                    if(student[i].studentyear == Year.JUNIOR){
                        if(added == false){
                            added = true;
                            filterenabled = true;
                            linkedlist.add(student[i]);
                        }
                    }
                    else{
                        if(linkedlist.contains(student[i])){
                            linkedlist.remove(student[i]);
                        }
                    }

                }
                if(jRadioButton4.isSelected()){
                    update = true;
                    old.jRadioButton4 = true;
                    if(student[i].studentyear == Year.SENIOR){
                        if(added == false){
                            added = true;
                            filterenabled = true;
                            linkedlist.add(student[i]);
                        }
                    }
                    else{
                        if(linkedlist.contains(student[i])){
                            linkedlist.remove(student[i]);
                        }
                    }

                }
                if(jRadioButton5.isSelected()){
                    update = true;
                    old.jRadioButton5 = true;
                    if(student[i].studentyear == Year.GRADSTUDENT){
                        if(added == false){
                            added = true;
                            filterenabled = true;
                            linkedlist.add(student[i]);
                        }
                    }
                    else{
                        if(linkedlist.contains(student[i])){
                            linkedlist.remove(student[i]);
                        }
                    }

                }
                
                if(!jComboBox1.getSelectedItem().toString().equals("Select Residence Hall")){
                    update = true;
                    old.jComboBox1 = jComboBox1.getSelectedItem().toString();
                    if(jComboBox1.getSelectedItem().toString().equals(student[i].residencehall)){
                        if(added == false){
                            added = true;
                            filterenabled = true;
                            linkedlist.add(student[i]);
                        }
                    }
                    else{
                        if(linkedlist.contains(student[i])){
                            linkedlist.remove(student[i]);
                        }
                    }


                }
                if(jRadioButton6.isSelected()){
                    update = true;
                    old.jRadioButton6 = true;
                    if(student[i].studentcontact == PreferContact.PHONE){
                        if(added == false){
                            added = true;
                            filterenabled = true;
                            linkedlist.add(student[i]);
                        }
                    }
                    else{
                        if(linkedlist.contains(student[i])){
                            filterenabled = true;
                            linkedlist.remove(student[i]);
                        }
                    }

                }
                if(jRadioButton7.isSelected()){
                    update = true;
                    old.jRadioButton7 = true;
                    if(student[i].studentcontact == PreferContact.TEXT){
                       if(added == false){
                            added = true;
                            filterenabled = true;
                            linkedlist.add(student[i]);
                        } 
                    }
                    else{
                        if(linkedlist.contains(student[i])){
                            linkedlist.remove(student[i]);
                        }
                    }

                }
                if(jRadioButton8.isSelected()){
                    update = true;
                    old.jRadioButton8 = true;
                    if(student[i].studentcontact == PreferContact.EMAIL){
                        if(added == false){
                            added = true;
                            filterenabled = true;
                            linkedlist.add(student[i]);
                        }
                    }
                    else{
                        if(linkedlist.contains(student[i])){
                            linkedlist.remove(student[i]);
                        }
                    }

                }
                if(jRadioButton9.isSelected()){
                    update = true;
                    old.jRadioButton9 = true;
                    if(student[i].studentcontact == PreferContact.NEITHER){
                        if(added == false){
                            added = true;
                            filterenabled = true;
                            linkedlist.add(student[i]);
                        }
                    }
                    else{
                        if(linkedlist.contains(student[i])){
                            linkedlist.remove(student[i]);
                        }
                    }


                }
                if(jRadioButton12.isSelected()){
                    update = true;
                    old.jRadioButton12 = true;
                    if(student[i].offcampus){
                        if(added == false){
                            added = true;
                            filterenabled = true;
                            linkedlist.add(student[i]);
                        }
                    }
                    else{
                        if(linkedlist.contains(student[i])){
                            linkedlist.remove(student[i]);
                        }
                    }
                }
                if(jComboBox2.getSelectedItem().toString().compareTo("Select Staff") != 0){
                    update = true;
                    old.jComboBox2 = jComboBox2.getSelectedItem().toString();
                    if(jComboBox2.getSelectedItem().toString().equals(student[i].staffcontact)){
                        if(added == false){
                            added = true;
                            filterenabled = true;
                            linkedlist.add(student[i]);
                        }
                    }
                    else if(jComboBox2.getSelectedItem().toString().equals("No Staff Assigned")){
                        if(added = false){
                            added = true;
                            filterenabled = true;
                            linkedlist.add(student[i]);
                        }
                    }
                    else{
                        if(linkedlist.contains(student[i])){
                            linkedlist.remove(student[i]);
                        }
                    }

                }
                //if(jDateChooser1.getDate().toString().compareTo(student[i].dateadded) > 0){
                  //  update = true;
                    //datacopy(data, student, i);
                //}
                if(jCheckBox10.isSelected()){
                    update = true;
                    old.jCheckBox10 = true;
                    if(student[i].facetoface){
                        if(added == false){
                            added = true;
                            filterenabled = true;
                            linkedlist.add(student[i]);
                        }
                    }
                    else{
                        if(linkedlist.contains(student[i])){
                            linkedlist.remove(student[i]);
                        }
                    }

                }
                if(jCheckBox11.isSelected()){
                    update = true;
                    old.jCheckBox11 = true;
                    if(student[i].callemail){
                        if(added == false){
                            added = true;
                            filterenabled = true;
                            linkedlist.add(student[i]);
                        }
                    }
                    else{
                        if(linkedlist.contains(student[i])){
                            linkedlist.remove(student[i]);
                        }
                    }

                }
                if(jCheckBox12.isSelected()){
                    update = true;
                    old.jCheckBox12 = true;
                    if(student[i].twoxconnection){
                        if(added == false){
                            added = true;
                            filterenabled = true;
                            linkedlist.add(student[i]);
                        }
                    }
                    else{
                        if(linkedlist.contains(student[i])){
                            linkedlist.remove(student[i]);
                        }
                    }

                }
                if(jCheckBox13.isSelected()){
                    update = true;
                    old.jCheckBox13 = true;
                    if(student[i].threexconnection){
                        if(added == false){
                            added = true;
                            filterenabled = true;
                            linkedlist.add(student[i]);
                        }
                    }
                    else{
                        if(linkedlist.contains(student[i])){
                            linkedlist.remove(student[i]);
                        }
                    }

                }
                if(jCheckBox14.isSelected()){
                    update = true;
                    old.jCheckBox14 = true;
                    if(student[i].lginvite){
                        if(added == false){
                            added = true;
                            filterenabled = true;
                            linkedlist.add(student[i]);
                        }
                    }
                    else{
                        if(linkedlist.contains(student[i])){
                            linkedlist.remove(student[i]);
                        }
                    }

                }
                if(jCheckBox15.isSelected()){
                    update = true;
                    old.jCheckBox15 = true;
                    if(student[i].sginvite){
                        if(added == false){
                            added = true;
                            filterenabled = true;
                            linkedlist.add(student[i]);
                        }
                    }
                    else{
                        if(linkedlist.contains(student[i])){
                            linkedlist.remove(student[i]);
                        }
                    }

                }
                if(load == false && date != null && update == false){
                    update = true;
                    old.jDateChooser2 = date;
                    if(lessthan(student[i])){
                        if(added == false){
                            added = true;
                            filterenabled = true;
                            linkedlist.add(student[i]);
                        }
                    }
                    else{
                        if(linkedlist.contains(student[i])){
                            linkedlist.remove(student[i]);
                        }
                    }
                }
                
                i++;
            }//end while
        }

        if(update == false){
            update = true;
            filterenabled = false;
            StudentList studentlist = studentlist = new StudentList(jComboBox3.getSelectedItem().toString(), school);
            student = null;
            student = new StudentNode[studentlist.getStudentCount()];
            studentlist.getStudentList(student);
            for(int k = 0; k < student.length; k++){
                boolean added = false;
                if(added == false){
                        added = true;
                        linkedlist.add(student[k]);
                    }
            }
            
        }
        String[] columnNames = {"Name", "Year", "Room Number", "Residence Hall", "Phone", "Email", "Prefer to be contacted", "Bible Study", "Meet new People", "Talk about Jesus", "Find a church", "Learn about IV", "Fun Events", "Nothing", "Other", "Other", "Staff Contact", "Date Added", "Semester", "Face to Face", "Call/Email", "2XConnection", "3XConnection", "LG Invite", "SG Invite", "Notes"};
        Object[][] data = new Object[linkedlist.size()][26];
        int k = 0;
        student = null;
        student = new StudentNode[linkedlist.size()];
        while(!linkedlist.isEmpty()){
            
            StudentNode temp;
            temp = linkedlist.remove(0);
            student[k] = temp;
            datacopy(data, temp, k);
            k++;
        }
        load = true;
        jComboBox4.removeAllItems();
        jComboBox4.addItem("Select Student");
        jTable2 = new JTable(data, columnNames);
        for(int j = 0; j < jTable2.getRowCount(); j++){
            model.addRow(data[j]);
            if(data[j][0] != null)
                jComboBox4.addItem(data[j][0].toString());
            
        }
        jComboBox4.setSelectedItem("Select Student");
        load = false;
        new JScrollPane(jTable2, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jTable2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        
    }
    private boolean lessthan(StudentNode student){
        int bmonth, bday, byear, smonth, sday, syear;
        String[] array = student.dateadded.split("-");
        smonth = Integer.parseInt(array[0]);
        sday = Integer.parseInt(array[1]);
        syear = Integer.parseInt(array[2]);
        array = date.split("-");
        bmonth = Integer.parseInt(array[0]);
        bday = Integer.parseInt(array[1]);
        byear = Integer.parseInt(array[2]);
        
        //year is same
        if(byear == syear){
            //month is same
            if(bmonth == smonth){
                //day is equals--return true
                if(bday == sday){
                    return true;
                }
                //day is less -- return true
                else if(bday < sday){
                    return true;
                }
                //day is greater--return false
                else{
                    return false;
                }
            }
            //month is less--return true;
            else if(bmonth < smonth){
                return true;
            }
            else{
                return false;
            }
            //month is greater--return false
        }
        //byear is earlier--return true
        else if(byear < syear){
            return true;
        }
        else{
            return false;
        }
    }
    private void datacopy(Object[][] data, StudentNode student, int i){
        
        data[i][0] = student.name;
        if(student.studentyear == Year.FRESHMAN){
            data[i][1] = "FRESHMAN";
        }
        else if(student.studentyear == Year.SOPHOMORE){
            data[i][1] = "SOPHOMORE";
        }
        else if(student.studentyear == Year.JUNIOR){
            data[i][1] = "JUNIOR";
        }
        else if(student.studentyear == Year.SENIOR){
            data[i][1] = "SENIOR";
        }
        else{
            data[i][1] = "Grad Student";
        }
        data[i][2] = student.offcampus;
        data[i][3] = student.residencehall;
        if(student.studentcontact == PreferContact.PHONE){
            data[i][4] = "PHONE";
        }
        else if(student.studentcontact == PreferContact.TEXT){
            data[i][4] = "TEXT";
        }
        else if(student.studentcontact == PreferContact.EMAIL){
            data[i][4] = "EMAIL";
        }
        else{
            data[i][4] = "NEITHER";
        }
        data[i][5] = student.phone;
        data[i][6] = student.email;
        data[i][7] = student.biblestudy;
        data[i][8] = student.meetpeople;
        data[i][9] = student.talkjesus;
        data[i][10] = student.Findchurch;
        data[i][11] = student.Learniv;
        data[i][12] = student.funevents;
        data[i][13] = student.nothing;
        data[i][14] = student.other;
        data[i][15] = student.otherstring;
        data[i][16] = student.staffcontact;
        data[i][17] = student.dateadded;
        data[i][18] = student.semester;
        data[i][19] = student.facetoface;
        data[i][20] = student.callemail;
        data[i][21] = student.twoxconnection;
        data[i][22] = student.threexconnection;
        data[i][23] = student.lginvite;
        data[i][24] = student.sginvite;
        data[i][25] = student.notes;
            
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jCheckBox6 = new javax.swing.JCheckBox();
        jComboBox3 = new javax.swing.JComboBox<>();
        jCheckBox7 = new javax.swing.JCheckBox();
        jCheckBox10 = new javax.swing.JCheckBox();
        jCheckBox8 = new javax.swing.JCheckBox();
        jCheckBox11 = new javax.swing.JCheckBox();
        jCheckBox9 = new javax.swing.JCheckBox();
        jCheckBox12 = new javax.swing.JCheckBox();
        jRadioButton1 = new javax.swing.JRadioButton();
        jCheckBox13 = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jCheckBox14 = new javax.swing.JCheckBox();
        jRadioButton2 = new javax.swing.JRadioButton();
        jCheckBox15 = new javax.swing.JCheckBox();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jRadioButton6 = new javax.swing.JRadioButton();
        jRadioButton7 = new javax.swing.JRadioButton();
        jRadioButton8 = new javax.swing.JRadioButton();
        jRadioButton9 = new javax.swing.JRadioButton();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jComboBox2 = new javax.swing.JComboBox<>();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        jRadioButton12 = new javax.swing.JRadioButton();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jButton5 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jComboBox4 = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jCheckBox6.setText("Learn about IV");
        jCheckBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox6ActionPerformed(evt);
            }
        });

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        jCheckBox7.setText("Fun Events");
        jCheckBox7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox7ActionPerformed(evt);
            }
        });

        jCheckBox10.setText("Face-to-Face");
        jCheckBox10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox10ActionPerformed(evt);
            }
        });

        jCheckBox8.setText("Nothing");
        jCheckBox8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox8ActionPerformed(evt);
            }
        });

        jCheckBox11.setText("Call/Email");
        jCheckBox11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox11ActionPerformed(evt);
            }
        });

        jCheckBox9.setText("Other");
        jCheckBox9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox9ActionPerformed(evt);
            }
        });

        jCheckBox12.setText("2X Connection");
        jCheckBox12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox12ActionPerformed(evt);
            }
        });

        jRadioButton1.setText("Freshman");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jCheckBox13.setText("3X Connection");
        jCheckBox13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox13ActionPerformed(evt);
            }
        });

        jLabel1.setText("Year");

        jCheckBox14.setText("LG Invite");
        jCheckBox14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox14ActionPerformed(evt);
            }
        });

        jRadioButton2.setText("Sophomore");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jCheckBox15.setText("SG Invite");
        jCheckBox15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox15ActionPerformed(evt);
            }
        });

        jRadioButton3.setText("Junior");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        jRadioButton4.setText("Senior");
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });

        jRadioButton5.setText("Grad Student");
        jRadioButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton5ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Prefer to be Contacted:");

        jRadioButton6.setText("Phone");
        jRadioButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton6ActionPerformed(evt);
            }
        });

        jRadioButton7.setText("Text");
        jRadioButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton7ActionPerformed(evt);
            }
        });

        jRadioButton8.setText("Email");
        jRadioButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton8ActionPerformed(evt);
            }
        });

        jRadioButton9.setText("Neither");
        jRadioButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton9ActionPerformed(evt);
            }
        });

        jCheckBox2.setText("Bible Study");
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        jCheckBox3.setText("Meeting New People");
        jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jCheckBox4.setText("Talking about Jesus");
        jCheckBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox4ActionPerformed(evt);
            }
        });

        jCheckBox5.setText("Find Local Church");
        jCheckBox5.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckBox5StateChanged(evt);
            }
        });
        jCheckBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox5ActionPerformed(evt);
            }
        });

        jRadioButton12.setText("Off Campus");
        jRadioButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton12ActionPerformed(evt);
            }
        });

        jDateChooser2.setDateFormatString("MM-dd-yyyy");

        jButton5.setText("Filter Date");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox3)
                            .addComponent(jCheckBox4)
                            .addComponent(jCheckBox5)
                            .addComponent(jCheckBox6)
                            .addComponent(jCheckBox7)
                            .addComponent(jCheckBox8)
                            .addComponent(jCheckBox9)
                            .addComponent(jRadioButton5)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jRadioButton1)
                                    .addComponent(jRadioButton3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jRadioButton4)
                                    .addComponent(jRadioButton2)))
                            .addComponent(jLabel1))
                        .addGap(159, 159, 159))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton12)
                            .addComponent(jCheckBox2)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jComboBox3, 0, 202, Short.MAX_VALUE))
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jRadioButton8)
                                    .addComponent(jRadioButton6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jRadioButton7)
                                    .addComponent(jRadioButton9)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jCheckBox12)
                                        .addComponent(jCheckBox14)
                                        .addComponent(jCheckBox10))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jCheckBox11)
                                        .addComponent(jCheckBox15)
                                        .addComponent(jCheckBox13)))))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jCheckBox2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton3)
                    .addComponent(jRadioButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton6)
                    .addComponent(jRadioButton7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton8)
                    .addComponent(jRadioButton9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox10)
                    .addComponent(jCheckBox11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox12)
                    .addComponent(jCheckBox13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox14)
                    .addComponent(jCheckBox15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane3MouseClicked(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Year", "Off Campus", "Residence Hall", "Prefer to be contacted", "Phone", "Email", "Bible Study", "Meet New People", "Talk about Jesus", "Find a church", "Learn about IV", "Fun Events", "Nothing", "Other", "Other", "Staff Contact", "Date Added", "Semester", "Face to Face", "Call Email", "2X Connection", "3X Connection", "LG Invite", "SG Invite", "notes"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable2.setColumnSelectionAllowed(true);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable2MousePressed(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable2);
        jTable2.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jLabel5.setText("Filter");

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox5ActionPerformed(evt);
            }
        });

        jLabel6.setText("New");

        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Delete");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setText("Close");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });

        jButton3.setText("Refresh");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(106, 106, 106)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addContainerGap())
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1181, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1)
                        .addComponent(jButton2)
                        .addComponent(jButton3))
                    .addComponent(jButton4)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 24, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 679, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        if(!jCheckBox2.isSelected() && old.jCheckBox2){
            student = null;
            student = new StudentNode[0];
        }
        load = false;
        filtertable();
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox3ActionPerformed
        if(!jCheckBox3.isSelected() && old.jCheckBox3){
            student = null;
            student = new StudentNode[0];
        }
        load = false;
        filtertable();
    }//GEN-LAST:event_jCheckBox3ActionPerformed

    private void jCheckBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox4ActionPerformed
        if(!jCheckBox4.isSelected() && old.jCheckBox4){
            student = null;
            student = new StudentNode[0];
        }
        load = false;
        filtertable();
    }//GEN-LAST:event_jCheckBox4ActionPerformed

    private void jCheckBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox5ActionPerformed
        if(!jCheckBox5.isSelected() && old.jCheckBox5){
            student = null;
            student = new StudentNode[0];
        }
        load = false;
        filtertable();
    }//GEN-LAST:event_jCheckBox5ActionPerformed

    private void jCheckBox5StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckBox5StateChanged
        
    }//GEN-LAST:event_jCheckBox5StateChanged

    private void jCheckBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox6ActionPerformed
        if(!jCheckBox6.isSelected() && old.jCheckBox6){
            student = null;
            student = new StudentNode[0];
        }
        load = false;
        filtertable();
    }//GEN-LAST:event_jCheckBox6ActionPerformed

    private void jCheckBox7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox7ActionPerformed
        if(!jCheckBox7.isSelected() && old.jCheckBox7){
            student = null;
            student = new StudentNode[0];
        }
        load = false;
        filtertable();
    }//GEN-LAST:event_jCheckBox7ActionPerformed

    private void jCheckBox8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox8ActionPerformed
        if(!jCheckBox8.isSelected() && old.jCheckBox8){
            student = null;
            student = new StudentNode[0];
        }
        load = false;
        filtertable();
    }//GEN-LAST:event_jCheckBox8ActionPerformed

    private void jCheckBox9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox9ActionPerformed
        if(!jCheckBox5.isSelected() && old.jCheckBox5){
            student = null;
            student = new StudentNode[0];
        }
        load = false;
        filtertable();
    }//GEN-LAST:event_jCheckBox9ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        if(!jRadioButton1.isSelected() && old.jRadioButton1){
            student = null;
            student = new StudentNode[0];
        }
        load = false;
        filtertable();
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        if(!jRadioButton2.isSelected() && old.jRadioButton2){
            student = null;
            student = new StudentNode[0];
        }
        load = false;
        filtertable();
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        if(!jRadioButton3.isSelected() && old.jRadioButton3){
            student = null;
            student = new StudentNode[0];
        }
        load = false;
        filtertable();
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        if(!jRadioButton4.isSelected() && old.jRadioButton4){
            student = null;
            student = new StudentNode[0];
        }
        load = false;
        filtertable();
    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void jRadioButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton5ActionPerformed
        if(!jRadioButton5.isSelected() && old.jRadioButton5){
            student = null;
            student = new StudentNode[0];
        }
        load = false;
        filtertable();
    }//GEN-LAST:event_jRadioButton5ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        if(!load){
            if(filterenabled == false){
                StudentList list = new StudentList(jComboBox3.getSelectedItem().toString(), school);
                student = null;
                student = new StudentNode[list.getStudentCount()];
                list.getStudentList(student);
            }
            if(!old.jComboBox1.equalsIgnoreCase(jComboBox1.getSelectedItem().toString())){
                student = null;
                student = new StudentNode[0];
            }
            filtertable();
        }
        
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jRadioButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton6ActionPerformed
        if(!jRadioButton6.isSelected() && old.jRadioButton6){
            student = null;
            student = new StudentNode[0];
        }
        load = false;
        filtertable();
    }//GEN-LAST:event_jRadioButton6ActionPerformed

    private void jRadioButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton7ActionPerformed
        
        if(!jRadioButton7.isSelected() && old.jRadioButton7){
            student = null;
            student = new StudentNode[0];
        }
        load = false;
        filtertable();
    }//GEN-LAST:event_jRadioButton7ActionPerformed

    private void jRadioButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton8ActionPerformed
        if(!jRadioButton8.isSelected() && old.jRadioButton8){
            student = null;
            student = new StudentNode[0];
        }
        load = false;
        filtertable();
    }//GEN-LAST:event_jRadioButton8ActionPerformed

    private void jRadioButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton9ActionPerformed
        if(!jRadioButton9.isSelected() && old.jRadioButton9){
            student = null;
            student = new StudentNode[0];
        }
        load = false;
        filtertable();
    }//GEN-LAST:event_jRadioButton9ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        if(!load){
            if(filterenabled = false){
                StudentList list = new StudentList(jComboBox3.getSelectedItem().toString(), school);
                student = null;
                student = new StudentNode[list.getStudentCount()];
                list.getStudentList(student);
            }
            if(!jComboBox2.getSelectedItem().toString().equalsIgnoreCase(old.jComboBox2)){
                student = null;
                student = new StudentNode[0];
            }
            filtertable();
        }
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jCheckBox10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox10ActionPerformed
        if(!jCheckBox10.isSelected() && old.jCheckBox10){
            student = null;
            student = new StudentNode[0];
        }
        load = false;
        filtertable();
    }//GEN-LAST:event_jCheckBox10ActionPerformed

    private void jCheckBox11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox11ActionPerformed
        if(!jCheckBox11.isSelected() && old.jCheckBox11){
            student = null;
            student = new StudentNode[0];
        }
        load = false;
        filtertable();
    }//GEN-LAST:event_jCheckBox11ActionPerformed

    private void jCheckBox12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox12ActionPerformed
        if(!jCheckBox12.isSelected() && old.jCheckBox12){
            student = null;
            student = new StudentNode[0];
        }
        load = false;
        filtertable();
    }//GEN-LAST:event_jCheckBox12ActionPerformed

    private void jCheckBox13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox13ActionPerformed
        if(!jCheckBox13.isSelected() && old.jCheckBox13){
            student = null;
            student = new StudentNode[0];
        }
        load = false;
        filtertable();
    }//GEN-LAST:event_jCheckBox13ActionPerformed

    private void jCheckBox14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox14ActionPerformed
        if(!jCheckBox14.isSelected() && old.jCheckBox14){
            student = null;
            student = new StudentNode[0];
        }
        load = false;
        filtertable();
    }//GEN-LAST:event_jCheckBox14ActionPerformed

    private void jCheckBox15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox15ActionPerformed
        if(!jCheckBox15.isSelected() && old.jCheckBox15){
            student = null;
            student = new StudentNode[0];
        }
        load = false;
        filtertable();
    }//GEN-LAST:event_jCheckBox15ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        load = true;
        FilterList list = new FilterList(school, staffid);
        int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete " + jComboBox5.getSelectedItem().toString());
        if(option == JOptionPane.YES_OPTION){
            list.delete(jComboBox5.getSelectedItem().toString());
            JOptionPane.showMessageDialog(null, jComboBox5.getSelectedItem() + " has been successfully deleted.");
            while(!list.SaveList());
            FilterList temp = new FilterList(school, staffid);
            String[] names = new String[temp.getCount()];
            jComboBox5.removeAllItems();
            jComboBox5.addItem("Choose Filter");
            jComboBox5.setSelectedItem("Choose Filter");
            for(int i = 0; i < names.length; i++){
                jComboBox5.addItem(names[i]);
            }
        }
        
        
        load = false;
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox5ActionPerformed
        if(!load && jComboBox5.getItemCount() != 0){
            if(!jComboBox5.getSelectedItem().toString().equals("Choose Filter")){
                FilterList list = new FilterList(school, staffid);
                FilterNode node = list.search(jComboBox5.getSelectedItem().toString());
                jCheckBox2.setSelected(node.jCheckBox2);
                jCheckBox3.setSelected(node.jCheckBox3);
                jCheckBox4.setSelected(node.jCheckBox4);
                jCheckBox5.setSelected(node.jCheckBox5);
                jCheckBox6.setSelected(node.jCheckBox6);
                jCheckBox7.setSelected(node.jCheckBox7);
                jCheckBox8.setSelected(node.jCheckBox8);
                jCheckBox9.setSelected(node.jCheckBox9);
                jCheckBox10.setSelected(node.jCheckBox10);
                jCheckBox11.setSelected(node.jCheckBox11);
                jCheckBox12.setSelected(node.jCheckBox12);
                jCheckBox13.setSelected(node.jCheckBox13);
                jCheckBox14.setSelected(node.jCheckBox14);
                jCheckBox15.setSelected(node.jCheckBox15);
                jRadioButton1.setSelected(node.jRadioButton1);
                jRadioButton2.setSelected(node.jRadioButton2);
                jRadioButton3.setSelected(node.jRadioButton3);
                jRadioButton4.setSelected(node.jRadioButton4);
                jRadioButton5.setSelected(node.jRadioButton5);
                jRadioButton6.setSelected(node.jRadioButton6);
                jRadioButton7.setSelected(node.jRadioButton7);
                jRadioButton8.setSelected(node.jRadioButton8);
                jRadioButton9.setSelected(node.jRadioButton9);
                jRadioButton12.setSelected(node.jRadioButton12);
                jComboBox1.setSelectedItem(node.jComboBox1);
                jComboBox2.setSelectedItem(node.jComboBox2);
                jComboBox3.setSelectedItem(node.jComboBox3); 
            }
        
        }
        
    }//GEN-LAST:event_jComboBox5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        FilterNode node = new FilterNode();
        node.name = jTextField1.getText();
        node.jCheckBox2 = jCheckBox2.isSelected();
        node.jCheckBox3 = jCheckBox3.isSelected();
        node.jCheckBox4 = jCheckBox4.isSelected();
        node.jCheckBox5 = jCheckBox5.isSelected();
        node.jCheckBox6 = jCheckBox6.isSelected();
        node.jCheckBox7 = jCheckBox7.isSelected();
        node.jCheckBox8 = jCheckBox8.isSelected();
        node.jCheckBox9 = jCheckBox9.isSelected();
        node.jCheckBox10 = jCheckBox10.isSelected();
        node.jCheckBox11 = jCheckBox11.isSelected();
        node.jCheckBox12 = jCheckBox12.isSelected();
        node.jCheckBox13 = jCheckBox13.isSelected();
        node.jCheckBox14 = jCheckBox14.isSelected();
        node.jCheckBox15 = jCheckBox15.isSelected();
        node.jRadioButton1 = jRadioButton1.isSelected();
        node.jRadioButton2 = jRadioButton2.isSelected();
        node.jRadioButton3 = jRadioButton3.isSelected();
        node.jRadioButton4 = jRadioButton4.isSelected();
        node.jRadioButton5 = jRadioButton5.isSelected();
        node.jRadioButton6 = jRadioButton6.isSelected();
        node.jRadioButton7 = jRadioButton7.isSelected();
        node.jRadioButton8 = jRadioButton8.isSelected();
        node.jRadioButton9 = jRadioButton9.isSelected();
        node.jRadioButton12 = jRadioButton12.isSelected();
        node.jComboBox1 = jComboBox1.getSelectedItem().toString();
        node.jComboBox2 = jComboBox2.getSelectedItem().toString();
        node.jComboBox3 = jComboBox3.getSelectedItem().toString();
        FilterList list = new FilterList(school, staffid);
        list.insert(node);
        while(!list.SaveList()){
            
        }
        JOptionPane.showMessageDialog(null, "Successfully saved filter");
        jComboBox5.removeAllItems();
        list = null;
        list = new FilterList(school, staffid);
        String[] names = new String[list.getCount()];
        list.getFilterNames(names);
        for(int i = 0; i < names.length; i++){
            jComboBox5.addItem(names[i]);
        }
        jComboBox5.setSelectedItem(node.name);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jRadioButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton12ActionPerformed
        filtertable();
    }//GEN-LAST:event_jRadioButton12ActionPerformed
    private boolean convertbool(String value){
        return value.equals("true");
    }
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jScrollPane3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane3MouseClicked
        
    }//GEN-LAST:event_jScrollPane3MouseClicked

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        
        
    }//GEN-LAST:event_jTable2MouseClicked

    private void jTable2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MousePressed
        /*jTable2.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                if(e.getClickCount() == 1){
                    int row = jTable2.getSelectedRow();
                    View view = new View(student[row], school);
                    view.setVisible(true);
                }
            }
            
            
        });*/
        //int row = this.jTable2.getRowCount();
        //TableModel model = jTable2.getModel();
        //View view = new View(student[row - 1], school);
        //view.setVisible(true);
    }//GEN-LAST:event_jTable2MousePressed
    private void updatedata(){
           StudentList list = new StudentList(jComboBox3.getSelectedItem().toString(), school);
            for(int i = 0; i < student.length; i++){
                student[i] = list.search(student[i].name);
            } 
        
        
    }
    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        if(load == false){
            String name = jComboBox4.getSelectedItem().toString();
            for(int i = 0; i < student.length; i++){
                if(student[i].name.equals(name)){
                    View view = new View(student[i], school);
                    view.setVisible(true);
                    
                }
            }
            updatedata();
            filtertable();
        }

        
    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        if(load == false){
            if(filterenabled = false){
                StudentList list = new StudentList(jComboBox3.getSelectedItem().toString(), school);
                student = null;
                student = new StudentNode[list.getStudentCount()];
                list.getStudentList(student);
            }
            filtertable();
        }
        
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        student = null;
        student = new StudentNode[0];
        filtertable();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if(!load && !jDateChooser2.getDate().toString().isEmpty()){
            int month = jDateChooser2.getCalendar().get(MONTH) + 1;
            int day = jDateChooser2.getCalendar().get(DAY_OF_MONTH);
            int year = jDateChooser2.getCalendar().get(YEAR);
            date = Integer.toString(month) + "-" + Integer.toString(day) + "-" + Integer.toString(year);
            if(filterenabled == false){
                StudentList list = new StudentList(jComboBox3.getSelectedItem().toString(), school);
                student = null;
                student = new StudentNode[list.getStudentCount()];
                list.getStudentList(student);
            }
            if(!old.jDateChooser2.equalsIgnoreCase(date)){
                student = null;
                student = new StudentNode[0];
            }
            filtertable();
        }
        
        
 
        
    }//GEN-LAST:event_jButton5ActionPerformed
    private String date;    private int staffid;
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(View_All.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(View_All.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(View_All.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(View_All.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new View_All().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JCheckBox jCheckBox10;
    private javax.swing.JCheckBox jCheckBox11;
    private javax.swing.JCheckBox jCheckBox12;
    private javax.swing.JCheckBox jCheckBox13;
    private javax.swing.JCheckBox jCheckBox14;
    private javax.swing.JCheckBox jCheckBox15;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JCheckBox jCheckBox8;
    private javax.swing.JCheckBox jCheckBox9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton12;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JRadioButton jRadioButton7;
    private javax.swing.JRadioButton jRadioButton8;
    private javax.swing.JRadioButton jRadioButton9;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
