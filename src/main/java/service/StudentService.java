package service;

import java.io.PrintWriter;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.srccodes.example.Student;

import repository.StudentRepository;

public class StudentService {
    
    private Logger log = LogManager.getLogger(StudentService.class);
    private StudentRepository studentRepository = new StudentRepository();
    
    public ArrayList<Student> getAll() {
        ArrayList<Student> students = new ArrayList<Student>();
        
        try {
            students = studentRepository.getAll();
        } catch (Exception e) {
            log.error("Error in getAll", e);
        }
        return students;
    }
    public Student getOne(String key) {
        Student a = new Student();
        
        try {
            a = studentRepository.getOne(key);
        } catch (Exception e) {
            log.error("Error in getOne", e);
        }
     
        return a;
    }
    
    public Student create(Student s) {
    
        Student a = new Student();
        
        try {
            a = studentRepository.Create(s);
        } catch (Exception e) {
            log.error("Error in Create", e);
        }
        
        return a;
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
