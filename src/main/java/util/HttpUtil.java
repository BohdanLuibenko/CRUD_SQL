package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import com.srccodes.example.Student;

import jakarta.servlet.http.HttpServletRequest;

public class HttpUtil {

    private StudentConvertorUtil jsonconvertor = new StudentConvertorUtil();

    public Student convertToStudent(HttpServletRequest r) {
        
        StringBuilder res = new StringBuilder();
        String responseLine = null;
        
        Student student = new Student();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(r.getInputStream(), "utf-8"));

            while ((responseLine = br.readLine()) != null) {
                res.append(responseLine.trim());
            }
            student = jsonconvertor.convertJson(res.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return student;
    }
}
