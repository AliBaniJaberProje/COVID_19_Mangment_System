package Net2;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;


public class ModelCOVID {
    public String requestDBselect(HashMap<String, String> citys, String start_date, String end_date) {
        String sql = " SELECT city,SUM(activ_case),SUM(healed_cases) ";
        if (start_date == null) {
            System.out.println("in ModelCOVID start date null");
        }
// SELECT city,SUM(activ_case),SUM(healed_cases)  FROM Record_COVID WHERE Date_created >= '2020-08-4'and   Date_created <='2020-08-5'  group by city
        if (start_date == null && end_date == null) {
            sql += " FROM Record_COVID  ";

        } else if (start_date == null && end_date != null) {
            sql += " FROM `Record_COVID` WHERE Date_created <= '" + end_date + "'";
        } else if (start_date != null && end_date == null) {
            sql += " FROM `Record_COVID` WHERE Date_created >= '" + start_date + "'";
        } else {
            sql += " FROM Record_COVID WHERE Date_created >= '" + start_date + "'and Date_created <='" + end_date + "'";
        }
        Set keys = citys.keySet();
        Iterator i = keys.iterator();
        boolean start_citry = true;
        String[] local_city = {"Jerusalem", "Nablues", "Ramallah", "Hebron", "Tulkarm"};


//        for (int index = 0; index < 5; index++) {
//            System.out.println(citys.get(i.next()));
//            if (citys.get(local_city[index]) != null) {
//                if (start_citry) {
//                    if (start_date == null && end_date == null) {
//                        sql += " city='" + local_city[index] + "'";
//                    } else {
//                        sql += " and city='" + local_city[index] + "'";
//                    }
//                    start_citry = false;
//
//                } else {
//                    sql += " or city='" + local_city[index] + "'";
//                }
//            }
//
//
//        }
        sql += "  group by city";
        try
        {
            dbConection db_co = new dbConection();
            ResultSet respons = db_co.db_executer(sql,false);
            String result="[ ";
            Gson gson = new Gson();

            ArrayList<HashMap<String,String>> all_pro_json=new ArrayList<HashMap<String,String>>();
            while (respons.next())
            {
                HashMap<String,String> entity= new HashMap<String,String>();
                if (citys.get(respons.getString(1)) != null)
                {
                    entity.put("city",respons.getString(1));

                    entity.put("activ_case",respons.getString(2));
                    entity.put("healed_cases",respons.getString(3));
                    all_pro_json.add(entity);

                }




            }
            result = gson.toJson(all_pro_json);
            all_pro_json.clear();
            db_co.close_db_con();
            return result;
        }
        catch (Exception e)
        {

        }
        return null;




    }

    public String requestDBselectWithSpicalCity(HashMap<String, String> citys, String start_date, String end_date)
    {
        String sql = " SELECT id,activ_case,healed_cases,Date_created,Update_date ";
        if (start_date == null) {
            System.out.println("in ModelCOVID start date null");
        }

        if (start_date == null && end_date == null) {
            sql += " FROM Record_COVID where ";

        } else if (start_date == null && end_date != null) {
            sql += " FROM `Record_COVID` WHERE Date_created <= '" + end_date + "'";
        } else if (start_date != null && end_date == null) {
            sql += " FROM `Record_COVID` WHERE Date_created >= '" + start_date + "'";
        } else {
            sql += " FROM Record_COVID WHERE Date_created >= '" + start_date + "'And Date_created<='" + end_date + "'";
        }
        Set keys = citys.keySet();
        Iterator i = keys.iterator();
        boolean start_citry = true;
        String[] local_city = {"Jerusalem", "Nablues", "Ramallah", "Hebron", "Tulkarm"};


        for (int index = 0; index < 5; index++) {
            System.out.println(citys.get(i.next()));
            if (citys.get(local_city[index]) != null) {
                if (start_citry)
                {
                    if (start_date == null && end_date == null) {
                        sql += " city='" + local_city[index] + "'";
                    }else {
                        sql += " and city='" + local_city[index] + "'";
                    }
                    start_citry = false;

                }
            }

        }

        try
        {
            dbConection db_co = new dbConection();
            ResultSet respons = db_co.db_executer(sql,false);
            String result="[ ";
            Gson gson = new Gson();

            ArrayList<HashMap<String,String>> all_pro_json=new ArrayList<HashMap<String,String>>();
            while (respons.next())
            {
                HashMap<String,String> entity= new HashMap<String,String>();
                entity.put("id",respons.getString(1)+"");
                entity.put("activ_case",respons.getString(2));
                entity.put("healed_cases",respons.getString(3));
                entity.put("Date_created",respons.getString(4));
                entity.put("Update_date",respons.getString(5));
                all_pro_json.add(entity);

            }
            result = gson.toJson(all_pro_json);
            db_co.close_db_con();
            return result;
        }
        catch (Exception e)
        {

        }
        return null;
    }


    public String requestDBInsertRecord(HashMap<String,String> request, HttpServletResponse response) throws IOException {
        PrintWriter out=response.getWriter();

        if(isAuthoris(request)){
            String city=request.get("city");

            String activ_case=request.get("activ_case");
            String healed_cases=request.get("healed_cases");
            LocalDate date = LocalDate.now();


            String sql = "INSERT INTO Record_COVID (id, city, activ_case,healed_cases,Date_created,Update_date)VALUES ( NULL ,'" + city +"',"+ activ_case+","+ healed_cases+",'"+date+"','"+date+"')";
            dbConection db_co2 = new dbConection();
            System.out.println(sql);
            ResultSet respons_updateaccess = db_co2.db_executer(sql,true);
            db_co2.close_db_con();
            response.setContentType("application/html");
            out.println("New record created successfully");


        }
        else{
            response.setContentType("application/html");
            out.println("add_new_casenot Authorised user");
        }

     return null;
    }

     boolean isAuthoris(HashMap<String,String> request)
    {
        if(request.get("username")!=null && request.get("password")!=null )
        {

            String sql="SELECT * FROM user WHERE username='"+request.get("username")+"' and password='"+request.get("password")+"'";

            try
            {
                dbConection db_co = new dbConection();
                ResultSet respons = db_co.db_executer(sql,false);
                String result=null;
                Gson gson = new Gson();

                ArrayList<HashMap<String,String>> all_pro_json=new ArrayList<HashMap<String,String>>();
                boolean flag_test_into_loop=false;
                while (respons.next())
                {
                    HashMap<String,String> entity= new HashMap<String,String>();
                    entity.put("id",respons.getString(1)+"");
                    entity.put("username",respons.getString(2));
                    entity.put("password",respons.getString(3));
                    all_pro_json.add(entity);
                    flag_test_into_loop=true;

                }
                result = gson.toJson(all_pro_json);
                db_co.close_db_con();
             return flag_test_into_loop;
            }
            catch (Exception e)
            {

            }

        }
        return false;


    }


    public String requestToupdateRecord(HashMap<String,String> request, HttpServletResponse response) {
        try {
            PrintWriter out=response.getWriter();

            if (isAuthoris(request)) {
                String id=request.get("id");
                String sql1="SELECT id,city FROM Record_COVID WHERE id='"+id+"'";
                dbConection db_co = new dbConection();
                ResultSet respons = db_co.db_executer(sql1,false);
                if (respons.next()) {
                    LocalDate date = LocalDate.now();

                    String sql = "UPDATE Record_COVID SET activ_case='"+request.get("activ_case")+"',healed_cases='"+request.get("healed_cases")+"',Update_date='"+date+"' WHERE id='"+id+"'";
                    db_co.close_db_con();
                    dbConection db_co2 = new dbConection();
                    ResultSet respons2 = db_co2.db_executer(sql,true);
                    response.setContentType("application/html");
                    out.println("Record updated successfully");
                    db_co2.close_db_con();
                }

                else{
                    response.setContentType("application/html");
                    out.println("not found ");

                }

            } else {
                response.setContentType("application/html");
                out.println("add_new_casenot Authorised user");

            }
        }catch (Exception e)
        {

        }
            return null;

    }
}

