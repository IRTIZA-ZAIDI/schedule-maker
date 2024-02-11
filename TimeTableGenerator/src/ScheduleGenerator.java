import com.jakewharton.fliptables.FlipTableConverters;

import java.util.ArrayList;
import java.util.List;

public class ScheduleGenerator
{
    List<Course[]> coursesLists;
    ArrayList<Course[][]> validSchedules;

    public ScheduleGenerator() {
        coursesLists = new ArrayList<>();
        validSchedules = new ArrayList<>();
    }

    public void initialize_courses() {
        Course[] caal = new Course[4];
        Course[] sepi = new Course[3];
        Course[] ccn = new Course[3];
        Course[] ai = new Course[4];
        Course[] toa = new Course[3];

        caal[0] = new Course(71196, "faisal iradat", 0, 0, 0, 1, "caal");
        caal[1] = new Course(75903, "zain", 0, 1, 0, 2, "caal");
        caal[2] = new Course(75920, "salman zafar", 1, 1, 1, 2, "caal");
        caal[3] = new Course(95912, "salman zafar", 1, 4, 2, 4, "caal");

        //-1 for no lab courses

        sepi[0] = new Course(96056, "wahab suri", 0, 0, -1, -1, "sepi");
        sepi[1] = new Course(71190, "moiz hasan", 0, 1, -1, -1, "sepi");
        sepi[2] = new Course(71187, "amana", 0, 1, -1, -1, "sepi");

        ccn[0] = new Course(75904, "waseem arain", 0, 2, 0, 4, "ccn");
        ccn[1] = new Course(75912, "asma tauqir", 1, 0, 1, 1, "ccn");
        ccn[2] = new Course(75916, "tasbiha", 1, 1, 1, 2, "ccn");

        ai[0] = new Course(75913, "ali raza", 1, 0, -1, -1, "ai");
        ai[1] = new Course(75921, "sumera", 1, 1, -1, -1, "ai");
        ai[2] = new Course(75911, "ali raza", 0, 2, -1, -1, "ai");
        ai[3] = new Course(71194, "sumera", 0, 4, -1, -1, "ai");

        toa[0] = new Course(71207, "shahid hussain", 0, 5, -1, -1, "toa");
        toa[1] = new Course(96062, "imran rauf", 2, 4, -1, -1, "toa");
        toa[2] = new Course(71206, "imran rauf", 2, 5, -1, -1, "toa");

        coursesLists.add(caal);
        coursesLists.add(sepi);
        coursesLists.add(ccn);
        coursesLists.add(ai);
        coursesLists.add(toa);
    }

    public void generateSchedules() {
        Course[][] currentSchedule = new Course[8][3];
        generateHelper(0, currentSchedule);
    }

    private void generateHelper(int i, Course[][] currentSchedule) {
        //base case
        if (i==coursesLists.size()) {
            //we have 5 complete courses
            validSchedules.add(copySchedule(currentSchedule));
            return;
        }

        for (Course course: coursesLists.get(i)) {
            if(isCompatible(course, currentSchedule)) {
                //if non lab course
                if(course.labDay==-1) {
                    currentSchedule[course.lecTime][course.lecDay] = course;
                } else if (course.labDay!=-1) {
                    currentSchedule[course.lecTime][course.lecDay] = course;
                    currentSchedule[course.labTime][course.labDay] = course;
                    currentSchedule[course.labTime+1][course.labDay] = course;
                }
                generateHelper(i+1, currentSchedule);
                if(course.labDay==-1) {
                    currentSchedule[course.lecTime][course.lecDay] = null;
                } else if (course.labDay!=-1) {
                    currentSchedule[course.lecTime][course.lecDay] = null;
                    currentSchedule[course.labTime][course.labDay] = null;
                    currentSchedule[course.labTime+1][course.labDay] = null;
                }
            }
        }
    }

    private boolean isCompatible(Course course, Course[][] currentSchedule)
    {
        //if no lab course
        if(course.labDay==-1) {
            if(currentSchedule[course.lecTime][course.lecDay]==null) {
                return true;
            }
        } else {
            if((currentSchedule[course.lecTime][course.lecDay]==null) && (currentSchedule[course.labTime][course.labDay]==null) && (currentSchedule[course.labTime+1][course.labDay]==null)) {
                return true;
            }
        }
        return false;
    }

    public void printSchedules() {
        int index = 1;
        String[] headers = { "Mon", "Tue", "Wed", "Thurs", "Fri", "Sat", "Sun"};
        for(Course[][] schedule: validSchedules) {
            schedule = replaceNullValues(schedule);
            System.out.println("Schedule: " + index);
            System.out.println(FlipTableConverters.fromObjects(headers, schedule));
            System.out.println();
            System.out.println();
            index++;
        }

    }

    private Course[][] replaceNullValues(Course[][] schedule)
    {
        for (int i = 0; i < schedule.length; i++) {
            for (int j = 0; j < schedule[0].length; j++) {
                if (schedule[i][j]==null){
                    schedule[i][j] = new Course();
                }
            }
        }
        return schedule;
    }

    private Course[][] copySchedule(Course[][] original) {
        Course[][] copy = new Course[8][7];
        for (int i = 0; i < original.length; i++) {
            for (int j = 0; j < original[i].length; j++) {
                if (j==0) {
                    copy[i][0] = original[i][j];
                    copy[i][2] = original[i][j];
                } else if (j==1) {
                    copy[i][1] = original[i][j];
                    copy[i][3] = original[i][j];
                } else if (j==2) {
                    copy[i][4] = original[i][j];
                    copy[i][5] = original[i][j];
                }
            }
        }
        return copy;
    }


}
