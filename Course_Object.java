public class Course_Object implements Comparable<Course_Object>{
    private String courseID;
    private String courseName;
    private String section;
    private int year;
    private int numStudents;
    private int numSeats;
    private String instructor;

    //Constructor
    public Course_Object(String courseID, String courseName, String section, int year, int numStudents, int numSeats, String instructor) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.section = section;
        this.year = year;
        this.numStudents = numStudents;
        this.numSeats = numSeats;
        this.instructor = instructor;
    }

    //Getters
    public String getCourseID() {
        return courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getSection() {
        return section;
    }

    public int getYear() {
        return year;
    }

    public int getNumStudents() {
        return numStudents;
    }

    public int getNumSeats() {
        return numSeats;
    }

    public String getInstructor() {
        return instructor;
    }

    //Use of Comparable interface
    @Override
    public int compareTo(Course_Object course) {
        if (this.year - course.year == 0){
            return this.section.compareTo(course.section);
        } else{
            return this.year - course.year;
        }
    }
}
