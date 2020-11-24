package sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Request {
    private String Server;
    private String Method;
    private boolean Jerusalem;
    private boolean Nablue;
    private boolean Hebron;
    private boolean Ramallah;
    private boolean Tulkarm;
    private String start_date="NOT SELECTED";
    private String end_date="NOT SELECTED";
    private String request_type;
    String url_php="http://localhost/COVID_19/COVID.php?";
    String url_servlet="http://localhost:8080/Servlet_war_exploded/ServletCOVID-19?";




    private ProduceRequest obj_ProduceRequest=new ProduceRequest();
    Map<Object, Object> parametar_showAllData = new HashMap<>();
    Map<Object, Object> parameastr_to_login = new HashMap<>();
    Map<Object, Object> parametar_toinsertdata = new HashMap<>();

    public String getRequest_type() {
        return request_type;
    }

    public void setServer(String server) {
        Server = server;
    }

    public void setMethod(String method) {
        Method=method;
    }

    public void setJerusalem(boolean jerusalem) {
        Jerusalem = jerusalem;
    }

    public void setNablue(boolean nablue) {
        Nablue = nablue;
    }

    public void setHebron(boolean hebron) {
        Hebron = hebron;
    }

    public void setRamallah(boolean ramallah) {
        Ramallah = ramallah;
    }

    public void setTulkarm(boolean tulkarm) {
        Tulkarm = tulkarm;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    boolean php_ServerIsRun()
    {
     try {
         StringBuffer resul=sendGET("http://localhost/COVID_19/COVID.php?type_request=start");
         if(new String(resul).equals("yes"))
         {
             return true;
         }
     }catch (Exception e)
     {
         return false;
     }
        return false;

    }
    boolean servlet_serverIsRun(){
        try {
            StringBuffer resul=sendGET("http://localhost:8080/Servlet_war_exploded/ServletCOVID-19?type_request=start");
            if(new String(resul).equals("yes"))
            {
                return true;
            }
        }catch (Exception e)
        {
            return false;
        }
        return false;
    }

    public String getServer() {
        return Server;
    }

    public String getMethod() {
        return Method;
    }


    public String getStart_date() {
        return start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public StringBuffer sendToSelectData(boolean flag, String city)
    {


        String url_tosend=SetparametarToUrl_ToShowAllData(flag,city);

        System.out.println(url_tosend);
       if(getMethod().equals("GET")){
               return sendGET(url_tosend);
       }
        if(getMethod().equals("POST")){

            if(getServer().equals("PHP")) {
                StringBuffer result= sendPOST(url_php, (HashMap) parametar_showAllData);
                parametar_showAllData.clear();
                return result;
            }
            if(getServer().equals("SERVLET")){
                StringBuffer result=sendPOST(url_servlet, (HashMap) parametar_showAllData);
                parametar_showAllData.clear();;
                return result;


            }
       }

        return new StringBuffer("");

    }

    public StringBuffer sendLogin(String username,String pass){

        String url_tosend_php=Setparametar_to_login(username,pass);
        System.out.println(getMethod());
        if(getMethod().equals("GET")){
            if(getServer().equals("PHP"))
                return sendGET(url_tosend_php);
            if(getServer().equals("SERVLET"))
                return sendGET(url_tosend_php);

        }
        if(getMethod().equals("POST")){
            if(getServer().equals("PHP")) {
                StringBuffer result = sendPOST(url_php, (HashMap) parameastr_to_login);
                parameastr_to_login.clear();
                return result;
            }
            if(getServer().equals("SERVLET")) {
                StringBuffer result = sendPOST(url_servlet, (HashMap) parameastr_to_login);
                parameastr_to_login.clear();
                return result;

            }
        }

        return new StringBuffer("");

    }

    public StringBuffer sendInsertCases(String user, String pass,String city,int activ_case,int healed_cases)
    {

        String url_tosend= setparametar_toInsert(user,pass,city,activ_case,healed_cases);
        System.out.println(getMethod());
        if(getMethod().equals("GET")){
                return sendGET(url_tosend);
        }

        if(getMethod().equals("POST")){
            if(getServer().equals("PHP"))
                return sendPOST(url_php, (HashMap) parametar_toinsertdata);
            if(getServer().equals("SERVLET"))
                return sendPOST(url_servlet, (HashMap) parametar_toinsertdata);        }

        return new StringBuffer("");
    }

    public StringBuffer sendToUpdat(String user ,String pass ,int id, int active ,int healed_cases )
    {
        String url_tosend=setparam_toupdat(user,pass,id,active,healed_cases);

        System.out.println(getMethod());
        if(getMethod().equals("GET")){
            return sendGET(url_tosend);
        }

        if(getMethod().equals("POST")){
                if(getServer().equals("PHP")) {
                    StringBuffer result= sendPOST(url_php, (HashMap) parametar_toinsertdata);
                    parametar_toinsertdata.clear();
                    return result;


                }

            if(getServer().equals("SERVLET")) {
               StringBuffer result= sendPOST(url_servlet, (HashMap) parametar_toinsertdata);
                parametar_toinsertdata.clear();
                return result;
            }
        }

        return new StringBuffer("");
    }

    private String setparam_toupdat(String user, String pass,int id , int activ_case, int healed_cases) {

        String tmp=Setparametar_to_login(user,pass);
        tmp+="&type_request=update_record"+"&activ_case="+activ_case+"&healed_cases="+healed_cases+"&id="+id;
        parametar_toinsertdata.put("type_request","update_record");
        parametar_toinsertdata.put("id",id);
        parametar_toinsertdata.put("username",user);
        parametar_toinsertdata.put("password",pass);
        parametar_toinsertdata.put("activ_case",activ_case);
        parametar_toinsertdata.put("healed_cases",healed_cases);
        return tmp;



    }


    public StringBuffer sendGET(String url ){
        StringBuffer response = null;
        try {
            response=new StringBuffer();
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();

            System.out.println("GET Response Code :: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                    System.out.println(inputLine);
                }
                in.reset();
                in.close();
            } else {
                System.out.println("GET request not worked");
            }
        }
        catch (Exception e)
        {

        }

        return response;

    }
    public StringBuffer sendPOST(String url,HashMap hashMap_ref)  {
    try {
        String path=null;
        HttpRequest request = HttpRequest.newBuilder()
                .POST(buildFormDataFromMap(hashMap_ref))
                .uri(URI.create(url))
                .setHeader("User-Agent", "Java 11 HttpClient Bot")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        System.out.println(response.body());

        return  new StringBuffer( response.body());
    }catch (Exception e){
        return new StringBuffer("");
    }


    }

/*done*/
    String setparametar_toInsert(String username, String pass , String city , int activ_case, int healed_cases)
    {
        String tmp=Setparametar_to_login(username,pass);
        tmp+="&city="+city+"&activ_case="+activ_case+"&healed_cases="+healed_cases;
        parametar_toinsertdata.put("type_request","add_new_case");
        parametar_toinsertdata.put("username",username);
        parametar_toinsertdata.put("password",pass);
        parametar_toinsertdata.put("city",city);
        parametar_toinsertdata.put("activ_case",activ_case);
        parametar_toinsertdata.put("healed_cases",healed_cases);
        return tmp;

    }
    String Setparametar_to_login(String username,String pass){
        String parametar_url="";

        parametar_url+="type_request=add_new_case"+"&username="+username+"&"+"password="+pass;
        parameastr_to_login.put("type_request","add_new_case");
        parameastr_to_login.put("username",username);
        parameastr_to_login.put("password",pass);
        if(getServer().equals("PHP"))
            return url_php+parametar_url;
        if(getServer().equals("SERVLET"))
            return url_servlet+parametar_url;
        return null;
    }//don


    String SetparametarToUrl_ToShowAllData(boolean flag ,String city) {

        //flag==true all
        //flag == false 0ne

        String parametarurl="";
        if(flag) {
            parametarurl = parametarurl + "type_request=GetGenralData";
            parametar_showAllData.put("type_request", "GetGenralData");
        }
        if(!flag)
        {
            parametarurl = parametarurl + "type_request=GetSpicalCity";
            parametar_showAllData.put("type_request", "GetSpicalCity");
            parametar_showAllData.put(city, "true");
            parametarurl = parametarurl +"&"+ city+"=true";

            if(getServer().equals("PHP"))
                return url_php+parametarurl;
            if(getServer().equals("SERVLET"))
            {
                return url_servlet+parametarurl;
            };
        }
        if (!getStart_date().equals("NOT SELECTED")) {
                parametarurl = parametarurl + "&start_date=" + getStart_date();
                parametar_showAllData.put("start_date",getStart_date());
        }
        if (!getEnd_date().equals("NOT SELECTED")) {
                parametarurl = parametarurl + "&end_date=" + getEnd_date();
                parametar_showAllData.put("end_date",getEnd_date());
        }
        if (Jerusalem) {
                parametarurl += "&Jerusalem=true";
                parametar_showAllData.put("Jerusalem","true");
        }
        if (Nablue) {
                parametarurl += "&Nablues=true";
                parametar_showAllData.put("Nablues","true");
        }
        if (Ramallah) {
                parametarurl += "&Ramallah=true";
                parametar_showAllData.put("Ramallah","true");
        }
        if (Hebron) {
                parametarurl += "&Hebron=true";
                parametar_showAllData.put("Hebron","true");

        }
        if (Tulkarm) {
                parametarurl += "&Tulkarm=true";
                parametar_showAllData.put("Tulkarm","true");

        }
        parametar_showAllData.put("ts", System.currentTimeMillis());


        System.out.println(parametarurl);
        if(getServer().equals("PHP"))
            return url_php+parametarurl;
        if(getServer().equals("SERVLET"))
        {
            return url_servlet+parametarurl;
        }
     return  null;
    }//don


    private static HttpRequest.BodyPublisher buildFormDataFromMap(Map<Object, Object> data) {
        var builder = new StringBuilder();
        for (Map.Entry<Object, Object> entry : data.entrySet()) {
            if (builder.length() > 0) {
                builder.append("&");
            }
            builder.append(URLEncoder.encode(entry.getKey().toString(), StandardCharsets.UTF_8));
            builder.append("=");
            builder.append(URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8));
        }
        System.out.println(builder.toString());
        return HttpRequest.BodyPublishers.ofString(builder.toString());
    }



}
