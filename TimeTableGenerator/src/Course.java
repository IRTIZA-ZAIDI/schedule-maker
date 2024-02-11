public class Course
{
    String program;
    String room;
    int code;
    String teacher;
    int lecDay;
    int lecTime;
    int labDay;
    int labTime;
    String course;

    public Course(int code, String teacher, int lecDay, int lecTime, int labDay, int labTime, String course) {
        this.code = code;
        this.teacher = teacher;
        this.lecDay = lecDay;
        this.lecTime = lecTime;
        this.labDay = labDay;
        this.labTime = labTime;
        this.course = course;
    }

    public Course() {
    }

    @Override
    public String toString() {
        if (course==null && teacher==null) {
            return " ";
        }
        return course + ": " + teacher + " " + code;
    }
}
