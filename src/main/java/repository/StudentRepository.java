package repository;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.logging.log4j.Logger;
import org.apache.tomcat.jakartaee.bcel.util.ClassPath.ClassFile;

import com.srccodes.example.Student;

import Util.ConnectorService;

public class StudentRepository {
   private static Connection con = ConnectorService.getConnection();
   private static PreparedStatement ps = null;
   private static ResultSet rs = null;
   private static Logger log = null;
    public static void GetAll(PrintWriter p) {
        try {
            ps = con.prepareStatement("select * from data");
            rs = ps.executeQuery();
            p.print("<h1>id name midlename lastname</h1>");
            while (rs.next()) {

                p.print("<h1>" + rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4)
                        + "</h1>");

            }
            log.trace("Made all getData");
        } catch (Exception e) {
            System.out.println("Error in getData" + e);
            log.error("Error in getData", e);
        }
    }
    public static void GetOne(PrintWriter p, String id) {
        try {
            ps = con.prepareStatement("select * from data");
            rs = ps.executeQuery();
            p.print("<h1>id name midlename lastname</h1>");
            while (rs.next()) {
                if (Integer.parseInt(id) == Integer.parseInt(rs.getString(1)))
                    p.print("<h1>" + rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " "
                            + rs.getString(4) + "</h1>");

            }
            log.trace("Made one getData");
        } catch (Exception e) {
            System.out.println("Error in getData" + e);
            log.error("Error in getData", e);
        }
    }
    public static void Create(PrintWriter p, Student a) {
        try {
            String insertSQL = "INSERT INTO data (id, name,midlename,lastname) VALUES (?, ?,?,?)";
            ps = con.prepareStatement(insertSQL);
            ps.setString(1, String.valueOf(a.id));
            ps.setString(2, a.name);
            ps.setString(3, a.midlename);
            ps.setString(4, a.lastname);
            ps.executeUpdate();
            ps = con.prepareStatement("SELECT * FROM data WHERE id=(SELECT max(id) FROM data);");
            rs = ps.executeQuery();
            p.print("<h1>Created:</h1>");
            p.print("<h1>id name midlename lastname</h1>");
            while (rs.next()) {
                p.print("<h1>" + rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4)
                        + "</h1>");
            }
            log.trace("Made create");
        } catch (Exception e) {
            System.out.println("Error in create" + e);
            log.error("Error in create", e);
        }
    }
    public static void Update(Student a, String wid) {
        try {
            
            String updateSQL = "UPDATE data SET id=?,name=?,midlename=?,lastname=? WHERE id=?";
            ps = con.prepareStatement(updateSQL);
            ps.setString(1, String.valueOf(a.id));
            ps.setString(2, a.name);
            ps.setString(3, a.midlename);
            ps.setString(4, a.lastname);
            ps.setString(5, wid);
            ps.executeUpdate();
            log.trace("Made update");
        } catch (Exception e) {
            System.out.println("Error in update" + e);
            log.error("Error in update", e);
        }
    }
    public static void Delete(String id,PrintWriter p) {
       
        try {
            ps = con.prepareStatement("select * from data");
            rs = ps.executeQuery();
            p.print("<h1>Deleted:</h1>");
            p.print("<h1>id name midlename lastname</h1>");
            while (rs.next()) {
                if (Integer.parseInt(id) == Integer.parseInt(rs.getString(1)))
                    p.print("<h1>" + rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " "
                            + rs.getString(4) + "</h1>");

            }
            String delSQL = "DELETE FROM data WHERE id=?";
            ps = con.prepareStatement(delSQL);
            ps.setString(1, id);
            ps.executeUpdate();
            log.trace("Made delete");
        } catch (Exception e) {
            System.out.println("Error in delete" + e);
            log.error("Error in delete", e);
        }
    }
}
