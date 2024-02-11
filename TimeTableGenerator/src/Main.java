public class Main {
    public static void main(String[] args)
    {
        ScheduleGenerator scheduleGenerator = new ScheduleGenerator();
        scheduleGenerator.initialize_courses();
        scheduleGenerator.generateSchedules();
        scheduleGenerator.printSchedules();
    }
}