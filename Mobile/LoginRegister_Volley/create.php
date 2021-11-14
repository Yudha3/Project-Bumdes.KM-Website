<?php 
require_once 'conn.php';
if ( $conn ) {
    $fullname = $_POST['fullname'];
    $username = $_POST['username'];
    $email = $_POST['email'];
    $password = $_POST['password'];

    $rows = mysqli_num_rows(mysqli_query($conn, "SELECT * FROM users WHERE username = '$username' OR email = '$email'"));
    $insert = "INSERT INTO users (fullname, username, email, password) VALUES ('$fullname', '$username', '$email', '$password')";
    $response = array();

    if ( $fullname != "" && $username != "" && $email !="" && $password != "" ) {
        if ($rows > 0 ) {
            array_push($response, array(
                "status" => "REGISTERED"
            ));
        } else {
            $result = mysqli_query($conn, $insert);
            
            if ($result) {
                array_push($response, array(
                    "status" => "OK"
                ));
            } else {
                array_push($response, array(
                    "status" => "FAILED"
                ));
            }
        }
    } else {
        array_push($response, array(
            "status" => "FAILED"
        ));
    }

} else {
    array_push($response, array(
        "status" => "FAILED"
    ));
}

echo json_encode(array("server_response" => $response));
mysqli_close($conn);
?>