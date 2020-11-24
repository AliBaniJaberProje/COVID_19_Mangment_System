package Net2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

public class Controller {

    public HashMap<String,String> readbodyRequest(HttpServletRequest request) throws IOException {
        StringBuilder sb = new StringBuilder();
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

    public String doSelecAlltData(HashMap<String,String> request) {
    String start_date=request.get("start_date");
    String end_date=request.get("end_date");
    HashMap<String,String>citys=new HashMap<>();

    citys.put("Jerusalem",request.get("Jerusalem"));
    citys.put("Nablues",request.get("Nablues"));
    citys.put("Ramallah",request.get("Ramallah"));
    citys.put("Hebron",request.get("Hebron"));
    citys.put("Tulkarm",request.get("Tulkarm"));

    ModelCOVID model_covid=new ModelCOVID();
    String result= model_covid.requestDBselect(citys,start_date,end_date);
    System.out.println(result);
    return result;



    }

    public String doSelectRecordForCity(HashMap<String,String> request) {
        String start_date=request.get("start_date");
        String end_date=request.get("end_date");
        HashMap<String,String>citys=new HashMap<>();

        citys.put("Jerusalem",request.get("Jerusalem"));
        citys.put("Nablues",request.get("Nablues"));
        citys.put("Ramallah",request.get("Ramallah"));
        citys.put("Hebron",request.get("Hebron"));
        citys.put("Tulkarm",request.get("Tulkarm"));

        ModelCOVID model_covid=new ModelCOVID();
        String result= model_covid.requestDBselectWithSpicalCity(citys,start_date,end_date);
        return result;

    }

    public void doAddnewRecord(HashMap<String,String> request,HttpServletResponse response) {
        ModelCOVID model_covid=new ModelCOVID();
        try {
            String result= model_covid.requestDBInsertRecord(request,  response);

        }catch (Exception e){

        }
        //return result;
    }

    public void doUpdateRecord(HashMap<String,String> request,HttpServletResponse response) {
        ModelCOVID model_covid=new ModelCOVID();
        try {
            String result= model_covid.requestToupdateRecord(request,  response);

        }catch (Exception e){

        }
    }

}
