package Net2;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.stream.JsonReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.MalformedInputException;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet(name = "ServletNet2_wafaa")
public class ServletNet2_wafaa extends HttpServlet {

    private HashMap<String,String> readbodyRequest(HttpServletRequest request) throws IOException {
        StringBuilder sb = new StringBuilder();
        String result[] = null;
        String val = null;
        int flage = 1;
        HashMap<String,String> param_body = new HashMap<String, String>();

        try (BufferedReader br = request.getReader()) {
            String line;


            while ((line = br.readLine()) != null) {
                sb.append(line);
                if (flage == 1) {
                    System.out.println(line);
                }

                if (flage == 2) {
                    result = line.split(";")[1].split("=");
                    System.out.println(result[1]);

                }

                if (flage == 4) {
                     val = line;
                    System.out.println(val);

                }

                flage++;
                if (flage == 5) {

                    param_body.put(result[1].replaceAll("\"",""),val);
                    flage = 1;
                }
            }
            System.out.println(sb);
        }catch (Exception e )
        {
            
        }
        System.out.println(param_body);
        return param_body;
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HandelRequest(request,response,false);

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(request.getParameter("type_request").equals("start"))
        {   PrintWriter out=response.getWriter();
            response.setContentType("application/html");
            out.println("yes");

        }
        HandelRequest(request,response,true);

    }


    protected static void HandelRequest(HttpServletRequest request, HttpServletResponse response,boolean flag_get_post) throws ServletException, IOException{

         HashMap<String,String> parametar=InterFacePostGet.getParameterToSelectAllData(request,flag_get_post);

         System.out.println(request);
         Controller controller=new Controller();
         response.setContentType("application/json");
         response.setCharacterEncoding("UTF-8");
         PrintWriter out=response.getWriter();

         try {


             if(parametar.get("type_request").equals("GetGenralData")) {
                 out.println(controller.doSelecAlltData(parametar));
             }
             if(parametar.get("type_request").equals("GetSpicalCity")) {
                 out.println(controller.doSelectRecordForCity(parametar));

             }
             if(parametar.get("type_request").equals("add_new_case"))
                 controller.doAddnewRecord(parametar,response);// need login
             if(parametar.get("type_request").equals("update_record"))
                 controller.doUpdateRecord(parametar,response);// need login



         }catch (Exception e){
            System.out.println("errrrrrrrrrorrrrrrr");
         }


     }




}
