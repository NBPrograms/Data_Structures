import java.io.FileNotFoundException;
import java.util.*;

public class Course_Manipulator {
    public static void main(String[] args) {
        //Filename (change when necessary)
        String fname = "input/courses.txt";

        //Main Body
        ArrayList<String> rows = getTheCourseData(fname);
        TreeMap<String, ArrayList<Course_Object>> courses = arrangeCourseData(rows);
        outputCourses(courses);
        courseFinder(courses);
    }

    private static void courseFinder(TreeMap<String, ArrayList<Course_Object>> courses) {
        //User's input dictates whether to quit or output courses that match
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.printf("\nEnter Course ID or Q to quit->");
            String userInput = scan.nextLine();
            if (userInput.equalsIgnoreCase("Q")) {
                System.exit(0);
            } else {
                outputMatchingCourses(userInput, courses);
            }
        }
    }

    private static void outputMatchingCourses(String userInput, TreeMap<String, ArrayList<Course_Object>> courses) {
        int ct = 0;

        //Displays course for a certain ID sorted by year (then section)
        for (String id : courses.keySet()) {
            if (userInput.equalsIgnoreCase(id)) {
                System.out.printf("\n--------SHOWING COURSES FOR ID: %s-------------", id);
                ArrayList<Course_Object> coursesInfo = courses.get(id);
                Collections.sort(coursesInfo);
                ct++;

                for (Course_Object course : coursesInfo) {
                    System.out.printf("\nID:%s | Year:%s | Section:%s | Title:%s | Instructor:%s | " +
                            "Students:%s | Seats:%s", course.getCourseID(), course.getYear(), course.getSection(),
                            course.getCourseName(), course.getInstructor(), course.getNumStudents(),
                            course.getNumSeats());
                }
            }
        }

        if (ct == 0) {
            System.out.printf("\nSorry, there are no courses for ID:%s", userInput);
        }
    }

    private static void outputCourses(TreeMap<String, ArrayList<Course_Object>> courses) {
        for (String id : courses.keySet()) {
            //Initializations (TreeSet allows for only unique instructors)
            ArrayList<Course_Object> coursesInfo = courses.get(id);
            String courseTitle = coursesInfo.get(0).getCourseName();
            TreeSet<String> instructors = new TreeSet<>();
            int courseCt = 0; int studCt = 0; int seatCt = 0;

            //Counts # of courses, students, and seats
            //Finds unique instructors too
            for (Course_Object course : coursesInfo) {
                courseCt++;
                studCt += course.getNumStudents();
                seatCt += course.getNumSeats();
                instructors.add(course.getInstructor());
            }
            System.out.printf("\n%s:%s | Courses Offered:%s | Students:%s | Seats:%s | Instructors:%s",
                    id, courseTitle, courseCt, studCt, seatCt, instructors);
        }
    }

    private static TreeMap<String, ArrayList<Course_Object>> arrangeCourseData(ArrayList<String> rows) {
        //Initialization
        TreeMap<String, ArrayList<Course_Object>> courses = new TreeMap<>();

        for (String row : rows) {
            String [] toks = row.split(",");

            try {
                //Parses data and puts it into a course object
                String id = toks[0];
                String section = toks[1];
                String name = toks[2];
                int seats = Integer.parseInt(toks[3]);
                int students = Integer.parseInt(toks[4]);
                String instructor = toks[5];
                int year = Integer.parseInt(toks[6]);

                Course_Object course = new Course_Object(id, name, section, year, students, seats, instructor);

                //Puts course information into TreeMap with the course id being the key
                //and the ArrayList of different class information being the value
                if (!courses.containsKey(id)) {
                    ArrayList<Course_Object> courseInfo = new ArrayList<>();
                    courseInfo.add(course);
                    courses.put(id, courseInfo);
                } else {
                    ArrayList<Course_Object> courseInfo = courses.get(id);
                    courseInfo.add(course);
                    courses.put(id, courseInfo);
                }
            } catch (NumberFormatException e) {
                continue;
            }
        }
        return courses;
    }

    private static ArrayList<String> getTheCourseData(String file) {
        //Gets file data and puts it into an ArrayList
        FileIOManager fileData = new FileIOManager(file);
        try {
            fileData.setFileData();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<String> rowData = fileData.getFileData();
        return rowData;
    }
}
