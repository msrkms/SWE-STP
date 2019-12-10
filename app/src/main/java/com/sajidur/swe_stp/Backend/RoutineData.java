package com.sajidur.swe_stp.Backend;

public class RoutineData {
    private String Day;
    private String Time;
    private String CourseCode;
    private String Teacher;
    private String RooomNo;
    private String Sec;

    public RoutineData(String Day, String Time, String RoomNo, String CourseCode, String Teacher, String Sec){
        this.Day=Day;
        this.Time=Time;
        this.RooomNo= RoomNo;
        this.CourseCode=CourseCode;
        this.Teacher=Teacher;
        this.Sec=Sec;

    }

    public String getDay() {
        return Day;
    }

    public void setDay(String day) {
        Day = day;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getCourseCode() {
        return CourseCode;
    }

    public void setCourseCode(String courseCode) {
        CourseCode = courseCode;
    }

    public String getTeacher() {
        return Teacher;
    }

    public void setTeacher(String teacher) {
        Teacher = teacher;
    }

    public String getRooomNo() {
        return RooomNo;
    }

    public void setRooomNo(String rooomNo) {
        RooomNo = rooomNo;
    }

    public String getSec() {
        return Sec;
    }

    public void setSec(String sec) {
        Sec = sec;
    }
}