<?php
require_once __DIR__.'/confg.php';

class API{
    function login_valedation($username,$pass)
    {
        $db=new Connect();
        $item_of_product=array();

        $data=$db->prepare("SELECT * FROM user WHERE username='$username' and password='$pass'");
        $data->execute();
        while ($outputdata = $data->fetch(PDO::FETCH_ASSOC))
        {
            $item_of_product[]=array(
                'id'=>$outputdata['id'],
                'username'=>$outputdata['username'],
                'password'=>$outputdata['password']
            );
        }
        if(sizeof($item_of_product)==0)
        {
            return ("user not found");
        }
        return json_encode($item_of_product);

    }

}

$APIP=new API;
header('Content-Tyoe:application/json');
if(isset($_GET["username"]) && isset($_GET["password"]))
{
   // echo  $APIP->login_valedation($_GET["username"],$_GET["password"]);
}
$currentDate = date('Y-m-d');

if(isset($_GET["type_request"])&&($_GET["type_request"]=="start"))
{
    echo "yes";
}
if(isset($_GET["type_request"])&&($_GET["type_request"]=="GetGenralData"|| $_GET["type_request"]=="GetSpicalCity"))
{
    $servername = "localhost";
    $username = "root";
    $password = "";
    $dbname = "COVID_19_DB";
    $conn = new mysqli($servername, $username, $password, $dbname);
    if (!$conn) {
        die("Connection failed: " . mysqli_connect_error());
    }
    $sql="";
    if($_GET["type_request"]=="GetGenralData")
    {
        $sql=$sql." SELECT city,SUM(activ_case),SUM(healed_cases) ";
    }
    elseif($_GET["type_request"]=="GetSpicalCity")
    {
        $sql=$sql." SELECT city,id,activ_case,healed_cases,Date_created,Update_date ";
    }

    $db=new Connect();
    $item_of_product=array();

    if(!isset($_GET["start_date"]) && !isset($_GET["end_date"])){
        $sql.=" FROM Record_COVID  ";

    }elseif (!isset($_GET["start_date"])&& isset($_GET["end_date"]))
    {
        $sql.=" FROM `Record_COVID` WHERE Date_created <= '".$_GET["end_date"]."'";
    }
    elseif (isset($_GET["start_date"]) && !isset($_GET["end_date"]))
    {
        $sql.=" FROM `Record_COVID` WHERE Date_created >= '".$_GET["start_date"]."'";
    }
    else{
        $sql.=" FROM Record_COVID WHERE Date_created >= '".$_GET["start_date"]."'And Date_created <='".$_GET["end_date"]."'";
    }

    $citys_selection=array();
    $citys_selection["Jerusalem"]=(isset($_GET["Jerusalem"]));
    $citys_selection["Nablues"]=(isset($_GET["Nablues"]));
    $citys_selection["Ramallah"]=(isset($_GET["Ramallah"]));
    $citys_selection["Hebron"]=(isset($_GET["Hebron"]));
    $citys_selection["Tulkarm"]=(isset($_GET["Tulkarm"]));

    $flag_start_city=1;
    $flag_at_least_onecity=0;
    $flag_must_one_city_in_spical=0;

    if($_GET["type_request"]=="GetGenralData"){

        $sql.=" group by city";

    }
    //nop


    $result = $conn->query($sql);

    $activ_healed_cases=array();

    // echo $sql;
    if ($result->num_rows > 0) {

        if($_GET["type_request"]=="GetGenralData")
        {
            //echo $_GET["tpey_request"];
            while($row = mysqli_fetch_assoc($result))
            {
                if(isset($_GET[$row['city']])){
                    $activ_healed_cases[]=array(
                        'city'=>$row['city'],
                        'activ_case'=>$row['SUM(activ_case)'],
                        'healed_cases'=>$row['SUM(healed_cases)']
                    );
                }


            }
        }
        else {//id,activ_case,healed_cases,Date_created,Update_date
            while ($row = mysqli_fetch_assoc($result))
            {
                if(isset($_GET[$row['city']])) {
                    $activ_healed_cases[] = array(
                        'id' => $row['id'],
                        'activ_case' => $row['activ_case'],
                        'healed_cases' => $row['healed_cases'],
                        'Date_created' => $row['Date_created'],
                        'Update_date' => $row['Update_date']
                    );
                }
            }
        }

    }

    else {
        echo "0 results";
    }
    mysqli_close($conn);
    echo json_encode($activ_healed_cases);



}


if(isset($_POST["type_request"])&&($_POST["type_request"]=="GetGenralData"|| $_POST["type_request"]=="GetSpicalCity"))
{
    $servername = "localhost";
    $username = "root";
    $password = "";
    $dbname = "COVID_19_DB";
    $conn = new mysqli($servername, $username, $password, $dbname);
    if (!$conn) {
        die("Connection failed: " . mysqli_connect_error());
    }
    $sql="";
    if($_POST["type_request"]=="GetGenralData")
    {
        $sql=$sql." SELECT city,SUM(activ_case),SUM(healed_cases) ";
    }
    elseif($_POST["type_request"]=="GetSpicalCity")
    {
        $sql=$sql." SELECT id,city,activ_case,healed_cases,Date_created,Update_date ";
    }

    $db=new Connect();
    $item_of_product=array();

    if(!isset($_POST["start_date"]) && !isset($_POST["end_date"])){
        $sql.=" FROM Record_COVID  ";

    }elseif (!isset($_POST["start_date"])&& isset($_POST["end_date"]))
    {
        $sql.=" FROM `Record_COVID` WHERE Date_created <= '".$_POST["end_date"]."'";
    }
    elseif (isset($_POST["start_date"]) && !isset($_POST["end_date"]))
    {
        $sql.=" FROM `Record_COVID` WHERE Date_created >= '".$_POST["start_date"]."'";
    }
    else{
        $sql.=" FROM Record_COVID WHERE Date_created >= '".$_POST["start_date"]."'And Date_created<='".$_POST["end_date"]."'";
    }

    $citys_selection=array();
    $citys_selection["Jerusalem"]=(isset($_POST["Jerusalem"]));
    $citys_selection["Nablues"]=(isset($_POST["Nablues"]));
    $citys_selection["Ramallah"]=(isset($_POST["Ramallah"]));
    $citys_selection["Hebron"]=(isset($_POST["Hebron"]));
    $citys_selection["Tulkarm"]=(isset($_POST["Tulkarm"]));

    $flag_start_city=1;
    $flag_at_least_onecity=0;
    $flag_must_one_city_in_spical=0;

    if($_POST["type_request"]=="GetGenralData")
        $sql.=" group by city";




    $result = $conn->query($sql);

    $activ_healed_cases=array();

    // echo $sql;
    if ($result->num_rows > 0) {

        if($_POST["type_request"]=="GetGenralData")
        {
            //echo $_GET["tpey_request"];
            while($row = mysqli_fetch_assoc($result))
            {
              if(isset($_POST[$row['city']])){
                  $activ_healed_cases[]=array(
                      'city'=>$row['city'],
                      'activ_case'=>$row['SUM(activ_case)'],
                      'healed_cases'=>$row['SUM(healed_cases)']
                  );
              }


            }
        }
        else {//id,activ_case,healed_cases,Date_created,Update_date
            while ($row = mysqli_fetch_assoc($result))
            {
                if(isset($_POST[$row['city']])) {
                    $activ_healed_cases[] = array(
                        'id' => $row['id'],
                        'activ_case' => $row['activ_case'],
                        'healed_cases' => $row['healed_cases'],
                        'Date_created' => $row['Date_created'],
                        'Update_date' => $row['Update_date']
                    );
                }
            }
        }
    }

    else {
        echo "0 results";
    }
    mysqli_close($conn);
    echo json_encode($activ_healed_cases);



}

if(isset($_GET["type_request"])&&$_GET["type_request"]=="add_new_case")
{
    if(isset($_GET["username"])&&isset($_GET["password"]))
    {
        echo $_GET["type_request"];
        $username =$_GET["username"];
        $password=$_GET["password"];
        $reuslt_Autho=$APIP->login_valedation($username,$password);

        if($reuslt_Autho=="user not found")
        {
            echo "not Authorised user ";
            return;
        }
        else{
           // echo $reuslt_Autho;
            $servername = "localhost";
            $username = "root";
            $password = "";
            $dbname = "COVID_19_DB";

            $city=$_GET["city"];
            $activ_case=$_GET["activ_case"];
            $healed_cases=$_GET["healed_cases"];

            $conn = new mysqli($servername, $username, $password, $dbname);
            if ($conn->connect_error) {
                die("Connection failed: " . $conn->connect_error);
            }
            $currentDate = date('Y-m-d');
            $sql = "INSERT INTO Record_COVID (id, city, activ_case,healed_cases,Date_created,Update_date)VALUES ( NULL ,'" . $city ."'," . $activ_case .", " . $healed_cases.",'".$currentDate."','".$currentDate."')" ;

            if ($conn->query($sql) === TRUE) {
                echo "New record created successfully";
            } else {
                echo "Error: " . $sql . "<br>" . $conn->error;
            }

            $conn->close();


        }



    }else{
        echo "not Authorised user ";
        return;
    }

}

if(isset($_POST["type_request"])&&$_POST["type_request"]=="add_new_case")
{
    if(isset($_POST["username"])&&isset($_POST["password"]))
    {
        echo $_POST["type_request"];
        $username =$_POST["username"];
        $password=$_POST["password"];
        $reuslt_Autho=$APIP->login_valedation($username,$password);

        if($reuslt_Autho=="user not found")
        {
            echo "not Authorised user ";
            return;
        }
        else{
            // echo $reuslt_Autho;
            $servername = "localhost";
            $username = "root";
            $password = "";
            $dbname = "COVID_19_DB";

            $city=$_POST["city"];
            $activ_case=$_POST["activ_case"];
            $healed_cases=$_POST["healed_cases"];

// Create connection
            $conn = new mysqli($servername, $username, $password, $dbname);
            if ($conn->connect_error) {
                die("Connection failed: " . $conn->connect_error);
            }
            $currentDate = date('Y-m-d');
            $sql = "INSERT INTO Record_COVID (id, city, activ_case,healed_cases,Date_created,Update_date)VALUES ( NULL ,'" . $city ."'," . $activ_case .", " . $healed_cases.",'".$currentDate."','".$currentDate."')" ;
           echo $sql;
            if ($conn->query($sql) === TRUE) {
                echo "New record created successfully";
            } else {
                echo "Error: " . $sql . "<br>" . $conn->error;
            }

            $conn->close();


        }



    }else{
        echo "not Authorised user ";
        return;
    }

}


if(isset($_GET["type_request"]) && $_GET["type_request"]=="update_record")
{

   // echo $_GET["type_request"];
    $servername = "localhost";
    $username = "root";
    $password = "";
    $dbname = "COVID_19_DB";
    $conn = new mysqli($servername, $username, $password, $dbname);
    if ($conn->connect_error) {
        die("Connection failed: " . $conn->connect_error);
    }
    $id=$_GET["id"];
    $sql = "SELECT id,city FROM Record_COVID WHERE id='$id'";
    $result = $conn->query($sql);
    $row = $result->fetch_assoc();
    if ($result->num_rows > 0) {

        $activ_case=$_GET["activ_case"];
        $healed_cases=$_GET["healed_cases"];
        $currentDate = date('Y-m-d');
//UPDATE Record_COVID SET activ_case='3',healed_cases='10',Update_date='2020-7-4' WHERE id=4
        $sql = "UPDATE Record_COVID SET activ_case='$activ_case',healed_cases='$healed_cases',Update_date='$currentDate' WHERE id='$id'";
                if ($conn->query($sql) === TRUE) {
                    echo "Record updated successfully";
                } else {
                    echo "Error updating record: " . $conn->error;
                }

        if(intval($activ_case)==0)
        {
           // echo "There are no cases of corona in ".$row["city"];
        }


    } else {
        echo "not found ";
    }
    $conn->close();

}

if(isset($_POST["type_request"]) && $_POST["type_request"]=="update_record")
{

    // echo $_GET["type_request"];
    $servername = "localhost";
    $username = "root";
    $password = "";
    $dbname = "COVID_19_DB";
    $conn = new mysqli($servername, $username, $password, $dbname);
    if ($conn->connect_error) {
        die("Connection failed: " . $conn->connect_error);
    }
    $id=$_POST["id"];
    $sql = "SELECT id,city FROM Record_COVID WHERE id='$id'";
    $result = $conn->query($sql);
    $row = $result->fetch_assoc();

    if ($result->num_rows > 0) {

        $activ_case=$_POST["activ_case"];
        $healed_cases=$_POST["healed_cases"];
        $currentDate = date('Y-m-d');
//UPDATE Record_COVID SET activ_case='3',healed_cases='10',Update_date='2020-7-4' WHERE id=4
        $sql = "UPDATE Record_COVID SET activ_case='$activ_case',healed_cases='$healed_cases',Update_date='$currentDate' WHERE id='$id'";
        echo $sql;
        if ($conn->query($sql) === TRUE) {
            echo "Record updated successfully";
        } else {
            echo "Error updating record: " . $conn->error;
        }

        if(intval($activ_case)==0)
        {
            // echo "There are no cases of corona in ".$row["city"];
        }


    } else {
        echo "not found ";
    }
    $conn->close();

}


?>