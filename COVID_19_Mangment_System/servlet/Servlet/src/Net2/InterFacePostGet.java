package Net2;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

public class InterFacePostGet {
//true Get
//False POST
private static HashMap<String,String> readbodyRequest(HttpServletRequest request) throws IOException {
    StringBuilder sb = new StringBuilder();
    //request.setCharacterEncoding("UTF-8");
    try (BufferedReader br = request.getReader()) {
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
    }
    String[] boday_date =sb.toString().split("&");
    HashMap<String,String> param_body = new HashMap<String, String>();
    for(int i=0;i<boday_date.length;i++)
    {

        param_body.put(boday_date[i].split("=")[0],boday_date[i].split("=")[1]);



    }
    return param_body;
}





    public static HashMap<String,String> getParameterToSelectAllData(HttpServletRequest request,Boolean GET_POST_FLAF){
        HashMap<String,String> result_param=new HashMap<>();

        try{
            if(GET_POST_FLAF)
            {
                //implemant get
                result_param.put("type_request",request.getParameter("type_request"));
                result_param.put("id",request.getParameter("id"));
                result_param.put("start_date",request.getParameter("start_date"));
                result_param.put("end_date",request.getParameter("end_date"));
                result_param.put("Jerusalem",request.getParameter("Jerusalem"));
                result_param.put("Nablues",request.getParameter("Nablues"));
                result_param.put("Ramallah",request.getParameter("Ramallah"));
                result_param.put("Hebron",request.getParameter("Hebron"));
                result_param.put("Tulkarm",request.getParameter("Tulkarm"));
                result_param.put("activ_case",request.getParameter("activ_case"));
                result_param.put("healed_cases",request.getParameter("healed_cases"));
                result_param.put("username",request.getParameter("username"));
                result_param.put("password",request.getParameter("password"));


            }
            if(!GET_POST_FLAF)
            {
                //implemant post

                return  readbodyRequest(request);

            }
        }catch (Exception e)
        {
            System.out.println("ERROR");
        }
        return result_param;
    }




}









//
//
//
//
//
//
//    StringBuilder sb = new StringBuilder();
//    String result[] = null;
//    String val = null;
//    int flage = 1;
//    HashMap<String,String> param_body = new HashMap<String, String>();
//
//    try (BufferedReader br = request.getReader()) {
//            String line;
//
//
//            while ((line = br.readLine()) != null) {
//            sb.append(line);
//            if (flage == 1) {
//            System.out.println(line);
//            }
//
//            if (flage == 2) {
//            result = line.split(";")[1].split("=");
//            System.out.println(result[1]);
//
//            }
//
//            if (flage == 4) {
//            val = line;
//            System.out.println(val);
//
//            }
//
//            flage++;
//            if (flage == 5) {
//
//            param_body.put(result[1].replaceAll("\"",""),val);
//            flage = 1;
//            }
//            }
//            System.out.println(sb);
//            }catch (Exception e )
//            {
//
//            }
//            System.out.println(param_body);
//            return param_body;