package dev.patika.fourthhomeworkavemphract.utils;

import dev.patika.fourthhomeworkavemphract.model.Course;
import dev.patika.fourthhomeworkavemphract.model.Student;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RandomStudentGenerator {
    private static final String LETTER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String S_LETTER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toLowerCase();
    private static final String NUMBERS = "0123456789";
    private static final List<String> ADDRESSES = Arrays.asList("İstanbul", "Ankara", "Bursa", "İzmir", "Antalya", "Diyarbakır", "Gaziantep", "Şanlıurfa", "Trabzon", "Adana", "Konya", "Manisa", "Muğla", "Siirt", "Ağrı", "Van", "Erzurum", "Sivas", "Elazığ", "Malatya", "Rize");

    private Random random = new Random();
    public int studentCourseLimit;
    public int courseStudentLimit;

    public RandomStudentGenerator(int studentCourseLimit, int courseStudentLimit) {
        this.studentCourseLimit = studentCourseLimit;
        this.courseStudentLimit = courseStudentLimit;
    }

    private String getName() {
        String result = "";
        result += LETTER.charAt(random.nextInt(LETTER.length()));
        for (int i = 1; i < 4 + random.nextInt(8); i++) {
            result += S_LETTER.charAt(random.nextInt(S_LETTER.length()));
        }
        result += " " + LETTER.charAt(random.nextInt(LETTER.length()));
        for (int i = 1; i < 5 + random.nextInt(10); i++) {
            result += S_LETTER.charAt(random.nextInt(S_LETTER.length()));
        }
        return result;
    }

    private LocalDate getBirthDate() {
        return LocalDate.of(
                1983 + random.nextInt(20),
                Month.of(1 + random.nextInt(12)),
                1 + random.nextInt(28));
    }

    private String getGender() {
        if (random.nextBoolean())
            return "Woman";
        else
            return "Man";
    }

    private String getAddress() {
        return ADDRESSES.get(random.nextInt(ADDRESSES.size()));
    }

    public Student generateRandomStudent() {
        Student student = new Student();
        student.setAddress(getAddress());
        student.setGender(getGender());
        student.setName(getName());
        student.setBirthDate(getBirthDate());
        return student;
    }

    public void setCourses(List<Student> students, List<Course> courses) {
        ArrayList<Course> tempList = new ArrayList<>(courses);

        a:
        for (Student student : students) {
            for (int i = 0; i < random.nextInt(studentCourseLimit - 1) + 1; i++) {
                Course r = tempList.get(random.nextInt(tempList.size()));
                student.getCourses().add(r);
                r.getStudents().add(student);
                if (r.getStudents().size() > courseStudentLimit) {
                    tempList.remove(r);
                }
                if (tempList.size() == 0)
                    break a;
            }
        }
    }
}
