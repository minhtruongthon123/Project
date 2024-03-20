package Model;

import java.util.Date;

public class Schedule {
    private String DayofWeek;
    private String date;
    private String roomID, className, subjectName;
    private String timeStart, timeEnd;

    public Schedule(String DayofWeek, String date, String timeStart, String timeEnd, String roomID, String className, String subjectName) {
        this.DayofWeek = DayofWeek;
        this.date = date;
        this.roomID = roomID;
        this.className = className;
        this.subjectName = subjectName;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    

    public String getDayofWeek() {
        return DayofWeek;
    }

    // Getter và Setter cho các thuộc tính
    public void setDayofWeek(String DayofWeek) {
        this.DayofWeek = DayofWeek;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
