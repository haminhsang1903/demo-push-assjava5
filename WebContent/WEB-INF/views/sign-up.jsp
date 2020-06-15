<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
	integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
	integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
	integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
	crossorigin="anonymous"></script>
<link href="css/style.css" rel="stylesheet">
<style>
.form-login {
	height: 500px;
	width: 400px;
	margin-left: 200px;
	margin-top: 100px;
	/* border: solid 1px red; */
}

.form-login img {
	margin-left: 140px;
}

.button-login {
	margin-left: 130px;
}

body {
	background-image: url("imgs/background-login.jpg");
	background-repeat: no-repeat;
	background-size: cover;
	background-position: center;
}
</style>

<meta charset="UTF-8">
<title>Sign Up</title>
</head>
<body>
	
	<div class="form-login">
		<img src="imgs/logo.png" alt="Login" class="rounded-pill"> <br />
		<label for="exampleInputEmail1">Email address</label>
		<div class="input-group">
			<div class="input-group-prepend">
				<span class="input-group-text" id="basic-addon1">@</span>
			</div>
			<input type="text" class="form-control" placeholder="Username"
				aria-label="Username" aria-describedby="basic-addon1">
		</div>
		<br /> <label for="exampleInputPassword1">Password</label> <input
			type="password" class="form-control" id="exampleInputPassword1"
			placeholder="Password"><br /> 
			<label for="exampleInputEmail1">Fullname</label>
			<input type="text" class="form-control" placeholder="Fullname"
				aria-label="Username" aria-describedby="basic-addon1">
				<br/>
		<div class="button-login">

			<a href="login.htm"><button type="submit" class="btn btn-primary mb-2">Sign Up</button></a>
			
		</div>
	</div>
</body>
</html>