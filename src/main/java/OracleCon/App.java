package OracleCon;

import java.sql.*;

/**
 * Hello world!
 * <p/>
 * Before you can run this you need to run the following SQL on the database
 * <p/>
 * -> create table emp(id number(10),name varchar2(40),age number(3));
 * <p/>
 * and
 * <p/>
 * insert into emp values (1, 'John', 44);
 * <p/>
 * You also need to download the Oracle JDBC Driver and install it in Maven
 * <p/>
 * mvn install:install-file -Dfile={Path/to/your/ojdbc.jar} -DgroupId=com.oracle -DartifactId=ojdbc6 -Dversion=11.2.0 -Dpackaging=jar
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World, This is OracleCon");
        try {
            //step1 load the driver class
            Class.forName("oracle.jdbc.driver.OracleDriver");

            //step2 create  the connection object
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "system", "Regan123");

            // Print all warnings
            for (SQLWarning warn = con.getWarnings(); warn != null; warn = warn.getNextWarning()) {
                System.out.println("SQL Warning:");
                System.out.println("State  : " + warn.getSQLState());
                System.out.println("Message: " + warn.getMessage());
                System.out.println("Error  : " + warn.getErrorCode());
            }

            //step3 create the statement object
            Statement stmt = con.createStatement();

            //step4 execute query
            Boolean ret = stmt.execute("insert into emp values (1, \'John\', 43)");
            int num = stmt.getUpdateCount() ;

            System.out.println( num + " rows affected" ) ;

            ResultSet rs = stmt.executeQuery("select * from emp");
            while (rs.next())
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));

            //step5 close the connection object
            con.close();

        } catch (SQLException se) {
            System.out.println("SQL Exception:");

            // Loop through the SQL Exceptions
            while (se != null) {
                System.out.println("State  : " + se.getSQLState());
                System.out.println("Message: " + se.getMessage());
                System.out.println("Error  : " + se.getErrorCode());

                se = se.getNextException();
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}

