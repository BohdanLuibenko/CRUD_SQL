package service;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.srccodes.example.Student;

import repository.StudentRepository;

public class StudentService {

    private Logger log = LogManager.getLogger(StudentService.class);
    private StudentRepository studentRepository = new StudentRepository();

    public ArrayList<Student> getAll() {

        try {
            return studentRepository.getAll();
        } catch (Exception e) {
            log.error("Error in getAll", e);
            throw new RuntimeException("Error in getAll");
        }
    }
    public Student getOne(String key) {

        try {
            return studentRepository.getOne(key);
        } catch (Exception e) {
            log.error("Error in getOne", e);
            throw new RuntimeException("Error in getOne");
        }

    }

    public Student create(Student s) {

        Student a = null;

        try {
            return studentRepository.Create(s);
        } catch (Exception e) {
            log.error("Error in Create", e);
            throw new RuntimeException("Error in Create");
        }

    }

    public void update(Student s, String key) {

        try {
            studentRepository.Update(s, key);
        } catch (Exception e) {
            log.error("Error in Update", e);
        }

    }
    public void delete(String key) {

        try {
            studentRepository.Delete(key);
        } catch (Exception e) {
            log.error("Error in Delete", e);
        }

    }

}
