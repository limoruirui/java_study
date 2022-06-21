package com.company.dao.test;

import com.company.dao.dao.StudentDAO;
import com.company.dao.domain.Student;
import org.testng.annotations.Test;

import java.util.List;

public class TestDAO {
    @Test
    public void testActorDAO() {
        StudentDAO studentDAO = new StudentDAO();
        List<Student> students = studentDAO.queryMulti("select * from student where id >= ?", Student.class, 1);
        for (Student student : students) {
            System.out.println(student);
        }

        Student student = studentDAO.querySingle("select * from student where id = ?", Student.class, 1);
        System.out.println(student);

        Object o = studentDAO.queryScanlar("select * from student where id = ?",2, 1);
        System.out.println(o);
    }
}
