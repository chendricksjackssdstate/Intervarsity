/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.time.LocalDate;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.Date;

/**
 *
 * @author Chris
 */
enum Year{FRESHMAN, SOPHOMORE, JUNIOR, SENIOR, GRADSTUDENT};
enum PreferContact{PHONE, TEXT, EMAIL, NEITHER};
public class StudentNode {
    int contactid;
    Boolean biblestudy;
    Boolean talkjesus;
    Boolean Learniv;
    Boolean nothing;
    Boolean meetpeople;
    Boolean Findchurch;
    Boolean funevents;
    Boolean other;
    String otherstring;
    String name;
    Year studentyear;
    boolean offcampus;
    String residencehall;
    String phone;
    String email;
    PreferContact studentcontact;
    String dateadded;
    String staffcontact;
    String semester;
    Boolean facetoface, callemail, twoxconnection, threexconnection, lginvite, sginvite;
    String notes;
    boolean updated;
}
