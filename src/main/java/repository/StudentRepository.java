package repository;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.srccodes.example.Student;

import util.DBConnectorUtil;

public class StudentRepository {

    public static PreparedStatement ps = null;
    public static ResultSet rs = null;
    public static Logger log = LogManager.getLogger(StudentRepository.class);
    public ArrayList<Student> getAll() throws Exception {
        ArrayList<Student> students = new ArrayList<Student>();
        Connection con = DBConnectorUtil.getConnection();
        ps = con.prepareStatement("select * from data");
        rs = ps.executeQuery();
        while (rs.next()) {
            Student a = new Student();
            a.id = Integer.parseInt(rs.getString(1));
            a.name = rs.getString(2);
            a.midlename = rs.getString(3);
            a.lastname = rs.getString(4);
            students.add(a);
        }
        log.info("Made getAll");
        return students;
    }
    public Student getOne(String id) throws Exception {

        Connection con = DBConnectorUtil.getConnection();
        ps = con.prepareStatement("SELECT * FROM data");
        rs = ps.executeQuery();
        Student a = new Student();
        while (rs.next()) {
            if (Integer.parseInt(id) == Integer.parseInt(rs.getString(1))) {
                a.id = Integer.parseInt(rs.getString(1));
                a.name = rs.getString(2);
                a.midlename = rs.getString(3);
                a.lastname = rs.getString(4);
            }
        }
        log.info("Made getOne");
        return a;
    }
    public Student Create(Student a) throws Exception {

        Connection con = DBConnectorUtil.getConnection();
        String insertSQL = "INSERT INTO data (id, name,midlename,lastname) VALUES (?, ?,?,?)";
        ps = con.prepareStatement(insertSQL);
        ps.setString(1, String.valueOf(a.id));
        ps.setString(2, a.name);
        ps.setString(3, a.midlename);
        ps.setString(4, a.lastname);
        ps.executeUpdate();
        ps = con.prepareStatement("SELECT * FROM data WHERE id=(SELECT max(id) FROM data);");
        rs = ps.executeQuery();
        Student b = new Student();
        while (rs.next()) {
            b.id = Integer.parseInt(rs.getString(1));
            b.name = rs.getString(2);
            b.midlename = rs.getString(3);
            b.lastname = rs.getString(4);
        }
        log.info("Made create");
        return b;
    }
    public static void Update(Student a, String wid) throws Exception {

        Connection con = DBConnectorUtil.getConnection();
        String updateSQL = "UPDATE data SET id=?,name=?,midlename=?,lastname=? WHERE id=?";
        ps = con.prepareStatement(updateSQL);
        ps.setString(1, String.valueOf(a.id));
        ps.setString(2, a.name);
        ps.setString(3, a.midlename);
        ps.setString(4, a.lastname);
        ps.setString(5, wid);
        ps.executeUpdate();
        log.info("Made update");
    }
    public static void Delete(String id, PrintWriter p) throws Exception {
        Connection con = DBConnectorUtil.getConnection();
        String delSQL = "DELETE FROM data WHERE id=?";
        ps = con.prepareStatement(delSQL);
        ps.setString(1, id);
        ps.executeUpdate();
        log.info("Made delete");
    }
}
