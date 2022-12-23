package com.srccodes.example;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.theopentutorials.jdbc.db.mysqlconection;

public class dbworker {
    static Connection con = mysqlconection.getConnection();
    static PreparedStatement ps = null;
    static ResultSet rs = null;
    public static void getall(PrintWriter p) {
        try {
            ps = con.prepareStatement("select * from data");
            rs = ps.executeQuery();
            p.print("<h1>id name midlename lastname</h1>");
            while (rs.next()) {

                p.print("<h1>" + rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4)
                        + "</h1>");

            }

        } catch (Exception e) {
            System.out.println("Error in getData" + e);
        }
    }
    public static void getone(PrintWriter p, String id) {
        try {
            ps = con.prepareStatement("select * from data");
            rs = ps.executeQuery();
            p.print("<h1>id name midlename lastname</h1>");
            while (rs.next()) {
                if (Integer.parseInt(id) == Integer.parseInt(rs.getString(1)))
                    p.print("<h1>" + rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " "
                            + rs.getString(4) + "</h1>");

            }

        } catch (Exception e) {
            System.out.println("Error in getData" + e);
        }
    }
    public static void create(PrintWriter p, Student a) {
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

        } catch (Exception e) {
            System.out.println("Error in create" + e);
        }
    }
    public static void update(Student a,String wid) {
        try {
            String updateSQL = "UPDATE data SET id=?,name=?,midlename=?,lastname=? WHERE id=?";
            ps = con.prepareStatement(updateSQL);
            ps.setString(1, String.valueOf(a.id));
            ps.setString(2, a.name);
            ps.setString(3, a.midlename);
            ps.setString(4, a.lastname);
            ps.setString(5, wid);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error in update" + e);
        }
    }
    public static void delete(String id)
    {
        try {
        String delSQL ="DELETE FROM data WHERE id=?";
        ps=con.prepareStatement(delSQL);
        ps.setString(1, id);
        ps.executeUpdate();
        }
        catch (Exception e) {
            System.out.println("Error in delete" + e);
        }
    }
}
