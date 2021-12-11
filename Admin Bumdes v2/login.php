<?php
require('koneksi.php');
// $koneksi = mysqli_connect("localhost", "root", "", "user");
session_start();
if(isset($_POST['login'])){
    $username = trim($_POST['username']);
    $password = trim($_POST['password']);

	if($username == "" || $password == "") {
		header("Location: loginerror.php");
	}

	$sql = mysqli_query($koneksi, "SELECT * FROM user WHERE username = '$username' AND password = '$password'");

	if(mysqli_num_rows($sql) != 0) {
		header("Location: index.php");
	} else {
		header("Location: loginerror.php");
	}

    
}
?>

<!DOCTYPE html>
<html>
<head>
	<title>Animated Login Form</title>
	<link rel="stylesheet" type="text/css" href="style_login.css">
	<link href="https://fonts.googleapis.com/css?family=Poppins:600&display=swap" rel="stylesheet">
	<script src="https://kit.fontawesome.com/a81368914c.js"></script>
	<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<img class="wave" src="images/17545.jpg">
	<div class="container">
		<div class="img">
			<img src="images/undraw_web_devices_re_m8sc.svg">
		</div>
		<div class="login-content">
			<form action="" method="POST">
				<img src="images/undraw_profile_pic_ic-5-t.svg">
				<h2 class="title">Welcome</h2>
           		<div class="input-div one">
           		   <div class="i">
           		   		<i class="fas fa-user"></i>
           		   </div>
           		   <div class="div">
           		   		<h5>Username</h5>
           		   		<input type="text" class="input" name="username" autofocus>
           		   </div>
           		</div>
           		<div class="input-div pass">
           		   <div class="i"> 
           		    	<i class="fas fa-lock"></i>
           		   </div>
           		   <div class="div">
           		    	<h5>Password</h5>
           		    	<input type="password" class="input" name="password">
            	   </div>
            	</div>
            	<a href="#">Forgot Password?</a>
            	<input type="submit" class="btn" name="login" value="Login">
            </form>
        </div>
    </div>
    <script type="text/javascript" src="assets/js/main.js"></script>
</body>
</html>
