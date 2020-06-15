<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<link
	href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700"
	rel="stylesheet">

<title>Record Page</title>

<!-- Bootstrap core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Additional CSS Files -->
<link rel="stylesheet" href="assets/css/fontawesome.css">
<link rel="stylesheet" href="assets/css/templatemo-style.css">
<link rel="stylesheet" href="assets/css/owl.css">
</head>
<base href="${pageContext.servletContext.contextPath}/">
<body class="is-preload">

	<!-- Wrapper -->
	<div id="wrapper">

		<!-- Main -->
		<div id="main">
			<div class="inner">

				<!-- Header -->
				<header id="header">
					<div class="logo">
						<a href="index.htm">Sang Hub's</a>
					</div>
				</header>

				<!-- Page Heading -->
				<div class="page-heading">
					<div class="container-fluid">
						<div class="row">
							<div class="col-md-12">
								<h1 id="info">Sang Hub's</h1>
								<p>
									<strong>SendMail Page</strong>
								</p>
							</div>
						</div>
					</div>
				</div>

				<!-- Form Demo -->

				<form:form action="formMail.htm" >
					<div class="col-md-12">
						<div class="row">
							<div class="col-md-12" style="margin-top: 30px">
								<fieldset>
									<input name="from" class="form-control col-md-6" type="text"
										placeholder="From..." />
								</fieldset>
							</div>
							<div class="col-md-12" style="margin-top: 30px">
								<fieldset>
									<input name="to" class="form-control col-md-6" type="text"
										value="${emailstaff }"/>
								</fieldset>
							</div>
							<div class="col-md-12" style="margin-top: 30px">
								<fieldset>
									<input name="subject" class="form-control col-md-6" type="text"
										placeholder="Subject..." />
								</fieldset>
							</div>
							<div class="col-md-12" style="margin-top: 30px">
								<fieldset>
									<textarea name="body" class="form-control col-md-6" 
										placeholder="Body..." rows="5"></textarea>
								</fieldset>
							</div>
							<span style="color:${color}" class="col-md-12">${message }</span>
							<div class="col-md-6" style="margin-top: 30px">
								<button class="button col-md-3" name="btn-send">SEND</button>
							</div>
							
						</div>
					</div>
				</form:form>
				<br /> <br /> <br />
				
				
				

			</div>
		</div>

		<!-- Sidebar -->
		<div id="sidebar">

			<div class="inner">

				<!-- Search Box -->
				<%-- <section id="search" class="alt">
					<form method="get" action="#">
						<input type="text" name="search" id="search"
							placeholder="Search..." />
					</form>
				</section> --%>

				<!-- Menu -->
				<nav id="menu">
					<ul>
						<li><a href="index.htm">Homepage</a></li>
						<li><a href="staff.htm">Staff</a></li>
						<li><a href="depart.htm">Depart</a></li>
						<li><a href="record.htm">Record</a></li>
						<li><a href="general.htm">General</a></li>
						<li><a href="account.htm">Account</a></li>
						<li><a href="logout.htm">Log-out</a></li>
					</ul>
				</nav>

				<!-- Featured Posts -->
				<div class="featured-posts">
					<div class="heading">
						<h2>Featured Posts</h2>
					</div>
					<div class="owl-carousel owl-theme">
						<a href="#">
							<div class="featured-item">
								<img src="assets/images/featured_post_01.jpg" alt="featured one">
								<p>Aliquam egestas convallis eros sed gravida. Curabitur
									consequat sit.</p>
							</div>
						</a> <a href="#">
							<div class="featured-item">
								<img src="assets/images/featured_post_01.jpg" alt="featured two">
								<p>Donec a scelerisque massa. Aliquam non iaculis quam. Duis
									arcu turpis.</p>
							</div>
						</a> <a href="#">
							<div class="featured-item">
								<img src="assets/images/featured_post_01.jpg"
									alt="featured three">
								<p>Suspendisse ac convallis urna, vitae luctus ante. Donec
									sit amet.</p>
							</div>
						</a>
					</div>
				</div>

				<!-- Footer -->
				<footer id="footer">
					<p class="copyright"><div id="translate_select" style="height: 20px; width: 30px">
					</div>
					<br />
						<a rel="nofollow" href="https://www.facebook.com/templatemo">Copyright
							&copy; 2020 Sang Hub's</a>
					</p>


				</footer>

			</div>
		</div>

	</div>

	<!-- Scripts -->
	<!-- Bootstrap core JavaScript -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- SCRIPT  -->
	<script type="text/javascript"
		src="//translate.google.com/translate_a/element.js?cb=googleTranslateElementInit">
		
	</script>
	<script type="text/javascript">
		function googleTranslateElementInit() {
			new google.translate.TranslateElement({
				pageLanguage : 'vi'
			}, 'translate_select');
		}
	</script>
	<script src="assets/js/browser.min.js"></script>
	<script src="assets/js/breakpoints.min.js"></script>
	<script src="assets/js/transition.js"></script>
	<script src="assets/js/owl-carousel.js"></script>
	<script src="assets/js/custom.js"></script>
</body>


</body>
</html>