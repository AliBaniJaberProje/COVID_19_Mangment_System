package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.time.LocalDate;
import java.util.*;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.util.Pair;


public class Controller  implements Initializable {


    @FXML
    ImageView coronaimage2;
    @FXML
    ImageView coronaimage3;
    @FXML
    ImageView coronaimage4;
    @FXML
    ImageView coronaimage5;
    @FXML
    ImageView coronaimage6;
    @FXML
    ImageView coronaimage7;
    @FXML
    ImageView coronaimage8;
    @FXML
    ImageView coronaimage9;
    @FXML
    ImageView coronaimage10;
    @FXML
    ImageView coronaimage11;
    @FXML
    ImageView coronaimage12;
    @FXML
    ImageView coronaimage13;
    @FXML
    ImageView coronaimage14;
    @FXML
    ImageView coronaimage15;
    @FXML
    ImageView coronaimage16;
    @FXML
    ImageView coronaimage17;
    @FXML
    ImageView coronaimage18;

    ///
    @FXML
    ImageView coronaimage22;
    @FXML
    ImageView coronaimage32;
    @FXML
    ImageView coronaimage42;
    @FXML
    ImageView coronaimage52;
    @FXML
    ImageView coronaimage62;
    @FXML
    ImageView coronaimage72;
    @FXML
    ImageView coronaimage82;
    @FXML
    ImageView coronaimage92;
    @FXML
    ImageView coronaimage102;
    @FXML
    ImageView coronaimage112;
    @FXML
    ImageView coronaimage122;
    @FXML
    ImageView coronaimage132;
    @FXML
    ImageView coronaimage142;
    @FXML
    ImageView coronaimage152;
    @FXML
    ImageView coronaimage162;
    @FXML
    ImageView coronaimage172;


    /////////////////////////////
    @FXML
    ImageView coronaimage21;
    @FXML
    ImageView coronaimage31;
    @FXML
    ImageView coronaimage41;
    @FXML
    ImageView coronaimage51;
    @FXML
    ImageView coronaimage61;
    @FXML
    ImageView coronaimage71;
    @FXML
    ImageView coronaimage81;
    @FXML
    ImageView coronaimage91;
    @FXML
    ImageView coronaimage101;
    @FXML
    ImageView coronaimage111;
    @FXML
    ImageView coronaimage121;
    @FXML
    ImageView coronaimage131;
    @FXML
    ImageView coronaimage141;
    @FXML
    ImageView coronaimage151;
    @FXML
    ImageView coronaimage161;
    @FXML
    ImageView coronaimage171;
    @FXML
    ImageView coronaimage181;


    @FXML
    ImageView coronaimage211;
    @FXML
    ImageView coronaimage311;
    @FXML
    ImageView coronaimage411;
    @FXML
    ImageView coronaimage511;
    @FXML
    ImageView coronaimage611;
    @FXML
    ImageView coronaimage711;
    @FXML
    ImageView coronaimage811;
    @FXML
    ImageView coronaimage911;
    @FXML
    ImageView coronaimage1011;
    @FXML
    ImageView coronaimage1111;
    @FXML
    ImageView coronaimage1211;
    @FXML
    ImageView coronaimage1311;
    @FXML
    ImageView coronaimage1411;
    @FXML
    ImageView coronaimage1511;
    @FXML
    ImageView coronaimage1611;
    @FXML
    ImageView coronaimage1711;
    @FXML
    ImageView coronaimage1811;


    @FXML
    TableView general_table;
    @FXML
    TableColumn id_general_table;
    @FXML
    TableColumn city_general_table;
    @FXML
    TableColumn active_cases_general_table;
    @FXML
    TableColumn healed_cases_general_table;
    @FXML
    TableColumn total_general_table;

    @FXML
    RadioButton select_table;
    @FXML
    RadioButton select_chart;
    ToggleGroup group_table_chart_selection = new ToggleGroup();
    ToggleGroup group_POST_GET_selection = new ToggleGroup();
    ToggleGroup group_PHP_Servlet_selection = new ToggleGroup();

    @FXML
    CheckBox select_Jerusalem;
    @FXML
    CheckBox select_Nablues;
    @FXML
    CheckBox select_Hebron;
    @FXML
    CheckBox select_Ramallah;
    @FXML
    CheckBox select_Tulkarm;

    @FXML
    RadioButton select_PHP;
    @FXML
    RadioButton select_servlet;

    @FXML
    RadioButton select_POST;
    @FXML
    RadioButton select_GET;
    @FXML
    Button back;
    @FXML
    Button Add;
    @FXML
    Button Edit;
    @FXML
    DatePicker start_date;
    @FXML
    DatePicker end_date;
    @FXML
    PieChart  covid_19_chart;
    @FXML
    Label caption;
    @FXML
    Label city_name_Special;
    @FXML
    TableView spital_table_for_city;
    @FXML
    TableColumn id;
    @FXML
    TableColumn active_cases;
    @FXML
    TableColumn healed_cases;
    @FXML
    TableColumn date_created;
    @FXML
    TableColumn update_date;
    @FXML
    Label state_of_server;

    Request obj_request;

    Set<String> keys;
//    boolean select_table_bool = false;
//    boolean select_chart_bool = false;
    HashMap<String, Boolean> selection_city = new HashMap<String, Boolean>();

    private void update_viewBySendRequest(){
        for ( int i = 0; i<general_table.getItems().size(); i++) {
            general_table.getItems().clear();
        }
        StringBuffer respos=obj_request.sendToSelectData(true,null);
        try {
            for ( int i = 0; i<general_table.getItems().size(); i++) {
                general_table.getItems().clear();
            }
            ProcessResponse.add_Data_In_General_Table(general_table,respos);
            initialize_chart(ProcessResponse.data_chart[0],ProcessResponse.data_chart[1],ProcessResponse.data_chart[2],ProcessResponse.data_chart[3],ProcessResponse.data_chart[4]);
        }catch (Exception e)
        {
            System.out.println("pleas select city at least ");
        }
    }

    @FXML
    public void back_function(){

        general_table.setVisible(true);
        covid_19_chart.setVisible(false);
        back.setVisible(false);
        spital_table_for_city.setVisible(false);
        city_name_Special.setVisible(false);
    }



    @FXML
    public void EventHandler_select_PHP_Servlet() {

        if (select_PHP.isSelected()) {
            if(obj_request.php_ServerIsRun())
            {
                obj_request.setServer("PHP");
                state_of_server.setVisible(false);
                select_GET.setDisable(false);
                select_POST.setDisable(false);
                state_of_server.setText("PHP_Server  in run state pleas Select Method");

                //select_chart.setSelected(true);

                EventHandler_select_table_chart();

                startAnimation();
                obj_request.setServer("PHP");

            }else{
                state_of_server.setVisible(true);
                state_of_server.setText("PHP_Server in Stop state try run Server ");
            }
            System.out.println("Implemant_php");
        } else {
            if(obj_request.servlet_serverIsRun())
            {
                select_GET.setDisable(false);
                select_POST.setDisable(false);
                state_of_server.setVisible(false);
                state_of_server.setText("Servlet_Server in run state pleas Select Method");
                //select_GET.setSelected(true);
               // select_chart.setSelected(true);
                state_of_server.setVisible(false);

                EventHandler_select_table_chart();
                startAnimation();

                obj_request.setServer("SERVLET");
            }else{
                state_of_server.setVisible(true);
                state_of_server.setText("Servlet_Server in Stop state try run Server  ");
            }
            System.out.println("implemant_servlet");

        }
        initialize_chart(0,0,0,0,0);

        update_viewBySendRequest();

    }
    @FXML
    public void EventHandler_select_POST_GET() {
        if (select_GET.isSelected()) {
            obj_request.setMethod("GET");
            System.out.println("Implemant_get");
        } if (select_POST.isSelected()) {
            System.out.println("implemant_post");
            obj_request.setMethod("POST");
        }
        start_date.setDisable(false);
        end_date.setDisable(false);
        Add.setDisable(false);
        Edit.setDisable(false);
        initialize_chart(0,0,0,0,0);
        update_viewBySendRequest();

    }
    @FXML
    public void EventHandler_select_table_chart() {
        if (select_table.isSelected()) {
           // System.out.println("select_table");
            general_table.setVisible(true);
            covid_19_chart.setVisible(false);
            caption.setVisible(false);
//
//
//            select_table_bool = true;
//            select_chart_bool = false;

        } else {
            System.out.println("select_chart");
            caption.setVisible(true);

            general_table.setVisible(false);
            covid_19_chart.setVisible(true);
//            select_table_bool = false;
//            select_chart_bool = true;
        }
        spital_table_for_city.setVisible(false);
        city_name_Special.setVisible(false);

    }
    @FXML
    public void EventHandler_select_citys() {

        //////////---------------- احتمال ما يلزم




        selection_city.put("Jerusalem", select_Jerusalem.isSelected());
        selection_city.put("Nablues", select_Nablues.isSelected());
        selection_city.put("Ramallah", select_Ramallah.isSelected());
        selection_city.put("Hebron", select_Hebron.isSelected());
        selection_city.put("Tulkarm", select_Tulkarm.isSelected());

        obj_request.setJerusalem( select_Jerusalem.isSelected());
        obj_request.setNablue(select_Nablues.isSelected());
        obj_request.setRamallah( select_Ramallah.isSelected());
        obj_request.setHebron(select_Hebron.isSelected());
        obj_request.setTulkarm(select_Tulkarm.isSelected());
         keys = selection_city.keySet();
        initialize_chart(0,0,0,0,0);

        update_viewBySendRequest();

        //obj_request.requestGetAllData(true);

    }
    @FXML
    public void EventHandler_select_start_date(){
       System.out.println(start_date.getValue().toString());
       obj_request.setStart_date(start_date.getValue().toString());
        initialize_chart(0,0,0,0,0);

        update_viewBySendRequest();


    }
    public void EventHandler_select_end_date(){
        System.out.println(end_date.getValue().toString());
        obj_request.setEnd_date(end_date.getValue().toString());
        initialize_chart(0,0,0,0,0);

        update_viewBySendRequest();

    }
    @FXML
    public void Add_New_Record()
    {


        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Login Dialog");
        dialog.setHeaderText("Look, a Custom Login Dialog");
        ButtonType loginButtonType = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField username = new TextField();
        username.setPromptText("Username");
        PasswordField password = new PasswordField();
        password.setPromptText("Password");

        grid.add(new Label("Username:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("Password:"), 0, 1);
        grid.add(password, 1, 1);

        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

        username.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

        Platform.runLater(() -> username.requestFocus());

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(username.getText(), password.getText());
            }
            return null;
        });
        Optional<Pair<String, String>> result = dialog.showAndWait();
        StringBuffer respons=obj_request.sendLogin(username.getText(),password.getText());

           if(new String(respons).contains("add_new_casenot Authorised user")) {
               Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setTitle("Error Dialog");
               alert.setHeaderText("ERROR ");
               alert.setContentText("Please enter correct username and passwerd \n ");
               alert.showAndWait();
           }
           else{
               List<String> choices = new ArrayList<>();
               choices.add("Jerusalem");
               choices.add("Nablues");
               choices.add("Tulkarm");
               choices.add("Ramallah");
               choices.add("Hebron");
               ChoiceDialog<String> dialog2 = new ChoiceDialog<>("Jerusalem", choices);
               dialog.setTitle("Choice Dialog");
               dialog.setHeaderText("Look, a Choice Dialog");
               dialog.setContentText("Choose city to add cases:");

               Optional<String> result2 = dialog2.showAndWait();
               if (result2.isPresent()){
                   System.out.println("Your choice: " + result.get());
                   String city =result2.get().toString();
                   System.out.println(city);

                   TextInputDialog dialog3 = new TextInputDialog("activ_case");
                   dialog3.setTitle("Text Input Dialog");
                   dialog3.setHeaderText("Look, a Text Input Dialog");
                   dialog3.setContentText("Please enter #activ_case:");

                   Optional<String> result3 = dialog3.showAndWait();
                   if (result3.isPresent()){

                       try {
                           System.out.println(result3.get());

                           int activ=Integer.parseInt(result3.get());
                           if(activ<0)
                               throw new Exception();
                           System.out.println(activ);
                           TextInputDialog dialog4 = new TextInputDialog("walter");
                           dialog4.setTitle("Text Input Dialog");
                           dialog4.setHeaderText("Look, a Text Input Dialog");
                           dialog4.setContentText("Please enter your name:");
                           Optional<String> result4 = dialog4.showAndWait();
                           if (result4.isPresent()){
                               System.out.println(result4.toString());

                               int healed_cases=Integer.parseInt(result4.get().toString());
                               if(healed_cases<0)
                                   throw new Exception();
                               StringBuffer respons1=obj_request.sendInsertCases(username.getText(),password.getText(),city,activ,healed_cases);

                               System.out.println("\n"+respons1);
                               Alert alert = new Alert(Alert.AlertType.INFORMATION);
                               alert.setTitle("Information Dialog");
                               alert.setHeaderText("Look, an Information Dialog");
                               alert.setContentText(new String(respons1));

                               alert.showAndWait();

                           }


                       }catch (Exception e)
                       {
                           Alert alert = new Alert(Alert.AlertType.ERROR);
                           alert.setTitle("Error Dialog");
                           alert.setHeaderText("Look, an Error Dialog");
                           alert.setContentText("Please enter valid data");

                           alert.showAndWait();

                       }

                   }

               }

           }

        initialize_chart(0,0,0,0,0);

        update_viewBySendRequest();


    }
    @FXML
    public void edit_record(){
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Login Dialog");
        dialog.setHeaderText("Look, a Custom Login Dialog");
        ButtonType loginButtonType = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField username = new TextField();
        username.setPromptText("Username");
        PasswordField password = new PasswordField();
        password.setPromptText("Password");

        grid.add(new Label("Username:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("Password:"), 0, 1);
        grid.add(password, 1, 1);

        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

        username.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

        Platform.runLater(() -> username.requestFocus());

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(username.getText(), password.getText());
            }
            return null;
        });
        Optional<Pair<String, String>> result = dialog.showAndWait();
        StringBuffer respons=obj_request.sendLogin(username.getText(),password.getText());

        if(new String(respons).contains("add_new_casenot Authorised user")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("ERROR ");
            alert.setContentText("Please enter correct username and passwerd \n ");
            alert.showAndWait();
            return;
        }
        else {

        try {
            TextInputDialog d2 = new TextInputDialog("id ");
            d2.setTitle("Text Input Dialog");
            d2.setHeaderText("Look, a Text Input Dialog");
            d2.setContentText("Please enter id record tu update :");
            Optional<String> r4 = d2.showAndWait();
            if (r4.isPresent()) {
                int id = Integer.parseInt(r4.get());

                TextInputDialog dialog3 = new TextInputDialog("walter");
                dialog3.setTitle("Text Input Dialog");
                dialog3.setHeaderText("Look, a Text Input Dialog");
                dialog3.setContentText("Please enter #activ_case:");

                Optional<String> result3 = dialog3.showAndWait();
                if (result3.isPresent()) {


                    int activ = Integer.parseInt(result3.get());
                    if(activ<0)
                        throw new Exception();
                    System.out.println(activ);
                    TextInputDialog dialog4 = new TextInputDialog("walter");
                    dialog4.setTitle("Text Input Dialog");
                    dialog4.setHeaderText("Look, a Text Input Dialog");
                    dialog4.setContentText("Please enter # healed_cases:");
                    Optional<String> result4 = dialog4.showAndWait();
                    if (result4.isPresent()) {
                        System.out.println(result4.toString());

                        int healed_cases = Integer.parseInt(result4.get().toString());
                        if(healed_cases<0)
                            throw new Exception();
                        StringBuffer respons12 = obj_request.sendToUpdat(username.getText(), password.getText(), id, activ, healed_cases);

                        System.out.println("\n" + respons12);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Dialog");
                        alert.setHeaderText("Look, an Information Dialog");
                        alert.setContentText(new String(respons12));

                        alert.showAndWait();

                    }


                }
            }
        }
            catch (Exception e)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("Look, an Error Dialog");
                    alert.setContentText("Please enter valid data");

                    alert.showAndWait();

                }

            }
        initialize_chart(0,0,0,0,0);
        update_viewBySendRequest();


        }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

     obj_request=new Request();
     select_POST.setDisable(true);
     select_GET.setDisable(true);


     Add.setDisable(true);
     Edit.setDisable(true);


     city_name_Special.setVisible(false);
        back.setVisible(false);
     initialize_general_table();
     initialize_select_table_chart();
     initialize_select_POST_GET();
     initialize_select_PHP_servlet();
     initialize_spital_table_for_city();
    }
    private void initialize_chart(int a ,int b ,int c ,int d ,int g) {

        city_name_Special.setVisible(false);
        ObservableList<PieChart.Data> chartData = FXCollections.observableArrayList
                (
                        new PieChart.Data("Jerusalem", a),
                        new PieChart.Data("Nablues", b),
                        new PieChart.Data("Ramallah", c),
                        new PieChart.Data("Hebron", d),
                        new PieChart.Data("Tulkarm", g));



        covid_19_chart.setData(chartData);

        caption.setTextFill(Color.BLACK);
        caption.setStyle("-fx-font: 16 Tahoma;");
        covid_19_chart.getData().forEach((data) -> {
            data.getNode().addEventHandler(MouseEvent.MOUSE_MOVED, (MouseEvent e) -> {
                caption.setTranslateX(e.getX());
                caption.setTranslateY(e.getY());
                caption.setText((int)(data.getPieValue()/(a+b+c+d+g)*100 )+ "%");

            });
        });
    }
    private void startAnimation(){

        MyAnimation obj_animation=new MyAnimation();

        obj_animation.myAnamation_corona(coronaimage2);
        obj_animation.myAnamation_corona(coronaimage3);
        obj_animation.myAnamation_corona(coronaimage4);
        obj_animation.myAnamation_corona(coronaimage5);
        obj_animation.myAnamation_corona(coronaimage6);
        obj_animation.myAnamation_corona(coronaimage7);
        obj_animation.myAnamation_corona(coronaimage8);
        obj_animation.myAnamation_corona(coronaimage9);
        obj_animation.myAnamation_corona(coronaimage10);
        obj_animation.myAnamation_corona(coronaimage11);
        obj_animation.myAnamation_corona(coronaimage12);
        obj_animation.myAnamation_corona(coronaimage13);
        obj_animation.myAnamation_corona(coronaimage14);
        obj_animation.myAnamation_corona(coronaimage15);
        obj_animation.myAnamation_corona(coronaimage16);
        obj_animation.myAnamation_corona(coronaimage17);
        obj_animation.myAnamation_corona(coronaimage18);
        obj_animation.myAnamation_corona(coronaimage22);
        obj_animation.myAnamation_corona(coronaimage32);
        obj_animation.myAnamation_corona(coronaimage42);
        obj_animation.myAnamation_corona(coronaimage52);
        obj_animation.myAnamation_corona(coronaimage62);
        obj_animation.myAnamation_corona(coronaimage72);
        obj_animation.myAnamation_corona(coronaimage82);
        obj_animation.myAnamation_corona(coronaimage92);
        obj_animation.myAnamation_corona(coronaimage102);
        obj_animation.myAnamation_corona(coronaimage112);
        obj_animation.myAnamation_corona(coronaimage122);
        obj_animation.myAnamation_corona(coronaimage132);
        obj_animation.myAnamation_corona(coronaimage142);
        obj_animation.myAnamation_corona(coronaimage152);
        obj_animation.myAnamation_corona(coronaimage162);
        obj_animation.myAnamation_corona(coronaimage172);
        obj_animation.myAnamation_corona2(coronaimage21);
        obj_animation.myAnamation_corona2(coronaimage31);
        obj_animation.myAnamation_corona2(coronaimage41);
        obj_animation.myAnamation_corona2(coronaimage51);
        obj_animation.myAnamation_corona2(coronaimage61);
        obj_animation.myAnamation_corona2(coronaimage71);
        obj_animation.myAnamation_corona2(coronaimage81);
        obj_animation.myAnamation_corona2(coronaimage91);
        obj_animation.myAnamation_corona2(coronaimage101);
        obj_animation.myAnamation_corona2(coronaimage111);
        obj_animation.myAnamation_corona2(coronaimage121);
        obj_animation.myAnamation_corona2(coronaimage131);
        obj_animation.myAnamation_corona2(coronaimage141);
        obj_animation.myAnamation_corona2(coronaimage151);
        obj_animation.myAnamation_corona2(coronaimage161);
        obj_animation.myAnamation_corona2(coronaimage171);
        obj_animation.myAnamation_corona2(coronaimage181);
        obj_animation.myAnamation_corona2(coronaimage211);
        obj_animation.myAnamation_corona2(coronaimage311);
        obj_animation.myAnamation_corona2(coronaimage411);
        obj_animation.myAnamation_corona2(coronaimage511);
        obj_animation.myAnamation_corona2(coronaimage611);
        obj_animation.myAnamation_corona2(coronaimage711);
        obj_animation.myAnamation_corona2(coronaimage811);
        obj_animation.myAnamation_corona2(coronaimage911);
        obj_animation.myAnamation_corona2(coronaimage1011);
        obj_animation.myAnamation_corona2(coronaimage1111);
        obj_animation.myAnamation_corona2(coronaimage1211);
        obj_animation.myAnamation_corona2(coronaimage1311);
        obj_animation.myAnamation_corona2(coronaimage1411);
        obj_animation.myAnamation_corona2(coronaimage1511);
        obj_animation.myAnamation_corona2(coronaimage1611);
        obj_animation.myAnamation_corona2(coronaimage1711);
        obj_animation.myAnamation_corona2(coronaimage1811);
    }
    private void initialize_general_table(){
        id_general_table.setCellValueFactory(new PropertyValueFactory<>("id"));
        city_general_table.setCellValueFactory(new PropertyValueFactory<>("city"));
        active_cases_general_table.setCellValueFactory(new PropertyValueFactory<>("active_cases"));
        healed_cases_general_table.setCellValueFactory(new PropertyValueFactory<>("healed_cases"));
        total_general_table.setCellValueFactory(new PropertyValueFactory<>("total_cases"));



        //test add dame data
        //ProcessResponse.add_Data_In_General_Table(general_table);

      try {
          general_table.setOnMousePressed(new EventHandler<MouseEvent>() {
              @Override
              public void handle(MouseEvent mouseEvent) {
                  String city=general_table.getSelectionModel().getSelectedItem().toString();
                  System.out.println(city);
                  city_name_Special.setVisible(true);
                  back.setVisible(true);

                  city_name_Special.setText(city);
                  for ( int i = 0; i<spital_table_for_city.getItems().size(); i++) {
                      spital_table_for_city.getItems().clear();
                  }

                  ProcessResponse.add_Data_In_Spital_Table(spital_table_for_city,obj_request.sendToSelectData(false,city));
                  covid_19_chart.setVisible(false);
                  caption.setVisible(false);
                  general_table.setVisible(false);
                  spital_table_for_city.setVisible(true);

              }
          });
      }
      catch (Exception e)
      {

      }



    }
    private void initialize_select_table_chart(){
        select_chart.setToggleGroup(group_table_chart_selection);
        select_table.setToggleGroup(group_table_chart_selection);

    }
    private void initialize_select_POST_GET(){
        select_GET.setToggleGroup(group_POST_GET_selection);
        select_POST.setToggleGroup(group_POST_GET_selection);
    }
    private void initialize_select_PHP_servlet(){
        select_PHP.setToggleGroup(group_PHP_Servlet_selection);
        select_servlet.setToggleGroup(group_PHP_Servlet_selection);
    }
    private void initialize_spital_table_for_city() {

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        active_cases.setCellValueFactory(new PropertyValueFactory<>("active_cases"));
        healed_cases.setCellValueFactory(new PropertyValueFactory<>("healed_cases"));
        date_created.setCellValueFactory(new PropertyValueFactory<>("date_created"));
        update_date.setCellValueFactory(new PropertyValueFactory<>("update_date"));



    }

}
