/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ems.dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.junit.Test;

/**
 *
 * @author User
 */

public class DataSourceTest {
    
    //@Resource(name="jdbc/emsDatasource")
    private DataSource ds;
    
    @Test
    public void testConnection() throws Exception {
        try {
            ds = InitialContext.doLookup("jdbc/emsDatasource");
        } catch (NamingException e) {
            e.printStackTrace();
        }
        try (Connection conn = ds.getConnection()) {
            try (PreparedStatement selStmt = conn.prepareStatement("select * from module")) {
                //selStmt.setInt(1, 1);
                ResultSet result = selStmt.executeQuery();
                if (result.next()) {
                    System.out.println(result.getString("Code"));
                }
                else {
                    
                }
            }
        }
    }
}
