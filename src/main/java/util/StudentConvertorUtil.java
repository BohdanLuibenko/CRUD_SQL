package util;

import com.google.gson.Gson;
import com.srccodes.example.Student;

public class StudentConvertorUtil {
    
public Student convertJson(String jsonstr)
{
    Gson g = new Gson();
    Student s= g.fromJson(jsonstr, Student.class);
   return  s;
   
   
}
}
