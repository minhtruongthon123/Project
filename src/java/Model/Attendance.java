/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author minhn
 */
public class Attendance {

    public int id;
    public String date, TimeStart, TimeEnd, room, instructor, groupname;
    public int isPresent;
    public String StudentID;
    public String Comment;

    public String getComment() {
        return Comment;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getTimeStart() {
        return TimeStart;
    }

    public void setTimeStart(String TimeStart) {
        this.TimeStart = TimeStart;
    }

    public String getTimeEnd() {
        return TimeEnd;
    }

    public void setTimeEnd(String TimeEnd) {
        this.TimeEnd = TimeEnd;
    }

    public String getStudentID() {
        return StudentID;
    }

    public void setStudentID(String StudentID) {
        this.StudentID = StudentID;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public void setComment(String Comment) {
        this.Comment = Comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int isIsPresent() {
        return isPresent;
    }

    public void setIsPresent(int isPresent) {
        this.isPresent = isPresent;
    }

    public Attendance() {
    }

    public Attendance(int id, String date, String TimeStart, String TimeEnd, String room, String instructor, String groupname, int isPresent, String Comment) {
        this.id = id;
        this.date = date;
        this.TimeStart = TimeStart;
        this.TimeEnd = TimeEnd;
        this.room = room;
        this.instructor = instructor;
        this.groupname = groupname;
        this.isPresent = isPresent;
        this.Comment = Comment;
    }

    public Attendance(int id, String StudentID, int isPresent) {
        this.id = id;
        this.isPresent = isPresent;
        this.StudentID = StudentID;
    }

    public Attendance(int id, String date, String TimeStart, String TimeEnd, String room, String instructor) {
        this.id = id;
        this.date = date;
        this.TimeStart = TimeStart;
        this.TimeEnd = TimeEnd;
        this.room = room;
        this.instructor = instructor;
    }

}
