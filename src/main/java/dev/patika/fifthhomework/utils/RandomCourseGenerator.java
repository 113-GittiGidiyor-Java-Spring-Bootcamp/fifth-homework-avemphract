package dev.patika.fourthhomeworkavemphract.utils;

import dev.patika.fourthhomeworkavemphract.model.Course;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RandomCourseGenerator {
    private static final String LETTER="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS="0123456789";
    private final Random random=new Random();

    private final Set<String> codeSet=new HashSet<>();

    public void init(List<Course> courses){
        codeSet.clear();
        courses.forEach(c->codeSet.add(c.getCourseCode()));
    }

    private String getCourseCode(){
        String result="";
        for (int i=0;i<3;i++){
            result+=LETTER.charAt(random.nextInt(LETTER.length()));
        }
        result+="-";
        for (int i=0;i<3;i++){
            result+=NUMBERS.charAt(random.nextInt(NUMBERS.length()));
        }
        return result;
    }

    private String getUniqueCourseCode(){
        String courseCode="";
        while (codeSet.contains(courseCode) || "".equals(courseCode)){
            courseCode=getCourseCode();
        }
        codeSet.add(courseCode);
        return courseCode;
    }

    private String getCourseName(){
        String result="";
        for (int i=0;i<10+random.nextInt(10);i++){
            result+=LETTER.charAt(random.nextInt(LETTER.length()));
        }
        return result;
    }

    private double getCredit(){
        return random.nextInt(10);
    }

    public Course generateCourse(){
        Course course=new Course();
        course.setCourseCode(getUniqueCourseCode());
        course.setCourseName(getCourseName());
        course.setCredit(getCredit());
        return course;
    }


}
