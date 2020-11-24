package sample;

import javafx.scene.chart.PieChart;
import javafx.scene.control.TableView;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ProcessResponse {


    public static void add_Data_In_General_Table(TableView table ,StringBuffer response )
    {

        for ( int i = 0; i<table.getItems().size(); i++) {
            table.getItems().clear();
        }
        for(int i=0;i<5;i++)
        {
            data_chart[i]=0;
        }

        //int id,String city, int active_cases, int healed_cases
        System.out.println(response);
        JsonArray jsonObject4 = new JsonParser().parse(response.toString()).getAsJsonArray();
        //System.out.println(response);
        String city=null;
        int activ_case=-1;
        int healed_cases=-1;

        for (int i = 0; i <  jsonObject4.size(); i++) {
            JsonObject g =(JsonObject)jsonObject4.get(i);
            city = g.getAsJsonPrimitive("city").getAsString();

            activ_case =  Integer.parseInt(g.getAsJsonPrimitive("activ_case").getAsString());
            healed_cases= Integer.parseInt(g.getAsJsonPrimitive("healed_cases").getAsString());

            if(city.equals("Jerusalem")){
                data_chart[0]=activ_case+healed_cases;
            }
            if(city.equals("Nablues")){
                data_chart[1]=activ_case+healed_cases;
            }
            if(city.equals("Ramallah")){

                data_chart[2]=activ_case+healed_cases;
            }
            if(city.equals("Hebron")){

                data_chart[3]=activ_case+healed_cases;
            }
            if(city.equals("Tulkarm")){
                data_chart[4]=activ_case+healed_cases;

            }

            table.getItems().add(new RecordinGeneralTable(i ,city,activ_case,healed_cases));

        }
    }


    public static void add_Data_In_Spital_Table(TableView table,StringBuffer respos) {


        System.out.println(respos);
        JsonArray jsonObject4 = new JsonParser().parse(respos.toString()).getAsJsonArray();
        //System.out.println(response);
        int id;
        int activ_case=-1;
        int healed_cases=-1;
        String Date_created=null;
        String Update_date=null;

        for (int i = 0; i <  jsonObject4.size(); i++) {
            JsonObject g =(JsonObject)jsonObject4.get(i);
            id = Integer.parseInt(g.getAsJsonPrimitive("id").getAsString());

            activ_case =  Integer.parseInt(g.getAsJsonPrimitive("activ_case").getAsString());
            healed_cases= Integer.parseInt(g.getAsJsonPrimitive("healed_cases").getAsString());
            Date_created= g.getAsJsonPrimitive("Date_created").getAsString();
            Update_date= g.getAsJsonPrimitive("Update_date").getAsString();

            table.getItems().add(new RecordForCity(id,activ_case,healed_cases,Date_created,Update_date));

        }



    }

    public static int data_chart[]=new int[5];

    public static void prpcessResponsAddToChart(PieChart chart  , StringBuffer response){



        System.out.println(response);
        JsonArray jsonObject4 = new JsonParser().parse(response.toString()).getAsJsonArray();
        String city=null;
        int activ_case=-1;
        int healed_cases=-1;
        for (int i = 0; i < 5; i++) {
           data_chart[i]=0;
        }
        int j = -1;
        int n= -1;
        int t= -1;
        int r= -1;
        int h= -1;
        PieChart.Data data[] = new PieChart.Data[5];

        // string and integer data


        int values[] = {20, 30, 10, 4, 2};

        for (int i = 0; i < 5; i++) {

        }
        String status[] = {"Jerusalem Nablues", "Ramallah",
                "Hebron", "Tulkarm"};


        for (int i = 0; i <  jsonObject4.size(); i++) {
            JsonObject g = (JsonObject) jsonObject4.get(i);
            city = g.getAsJsonPrimitive("city").getAsString();

            activ_case = Integer.parseInt(g.getAsJsonPrimitive("activ_case").getAsString());
            healed_cases = Integer.parseInt(g.getAsJsonPrimitive("healed_cases").getAsString());

            if(city.equals("Jerusalem")){
                data_chart[0]=activ_case+healed_cases;
            }
            if(city.equals("Nablues")){
                data_chart[1]=activ_case+healed_cases;
            }
            if(city.equals("Ramallah")){

                data_chart[2]=activ_case+healed_cases;
            }
            if(city.equals("Hebron")){

                data_chart[3]=activ_case+healed_cases;
            }
            if(city.equals("Tulkarm")){
                data_chart[4]=activ_case+healed_cases;

            }




        }

        }
}
