package problem1;

import java.util.Arrays;

/**
 * Created by Chris Burgess on 10/25/2017.
 */
public class Main {
    public static void main(String[] args) {
        //switch the has prereue variables

        Course.Courses[] pre401 = new Course.Courses[]{Course.Courses.COMP101, Course.Courses.COMP110};
        Course comp401 = new CourseImpl("Comp401", 401, "kmp", pre401);



        Course.Courses[] badreqs = new Course.Courses[]{Course.Courses.COMP101};
        Course.Courses[] goodreqs = new Course.Courses[]{Course.Courses.COMP101, Course.Courses.COMP110};


        comp401.addStudent(new Student(1,"Chris", "Burgess", goodreqs));
        comp401.addStudent(new Student(1,"Chris", "Arran", goodreqs));
        comp401.addStudent(new Student(1,"Chris", "CCC", goodreqs));
        comp401.addStudent(new Student(1,"Chris", "Don", goodreqs));
        comp401.addStudent(new Student(1,"Chris", "Zeb", goodreqs));
        comp401.dropStudent(new Student(1,"Chris", "Burgess", goodreqs));

        System.out.println(comp401.getRoster());

    }
}
