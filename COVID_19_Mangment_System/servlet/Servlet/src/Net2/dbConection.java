package Net2;

import com.google.gson.Gson;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class dbConection {

    Connection con;
    public dbConection(){
        con=null;
    }
    public void close_db_con(){
        try {
            con.close();
        }
        catch (Exception e)
        {

        }
    }
    // SELECT city,SUM(activ_case),SUM(healed_cases)  FROM Record_COVID WHERE Date_created >= '2020-08-04'And Date_created<='2020-08-05' and city='Nablues'  group by city

    public  ResultSet db_executer(String SQL,boolean update_query ){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println(SQL);
            con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/COVID_19_DB","root","");
            Statement stmt=con.createStatement();
            ResultSet rs=null;
            if(!update_query)
                rs=stmt.executeQuery(SQL);
            else
                stmt.executeUpdate(SQL);

          //  con.close();
            return rs;
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }





}
