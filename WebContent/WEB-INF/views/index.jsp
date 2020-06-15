<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

<title>Trang chu</title>

<!-- Bootstrap core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!--
Ramayana CSS Template
https://templatemo.com/tm-529-ramayana
-->

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

				<!-- Banner -->
				<section class="main-banner">
					<div class="container-fluid">
						<div class="row">
							<div class="col-md-12">
								<div class="banner-content">
									<div class="row">
										<div class="col-md-12">
											<div class="banner-caption">
												<h4>
													Hello, this is <br /> <em>Sang Hub's</em> Theme.
												</h4>
												<span>ASSIGNMENT &amp; MANAGER EMPLOYEE</span>
												<p>
													Do you know that <strong>Sang Hub's</strong>
												</p>
												<div class="primary-button">
													<a href="#">Read More</a>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</section>

				<!-- Services -->
				<section class="services">
					<div class="container-fluid">
						<div class="row">
							<c:forEach var="r" items="${staffIndex }">
								<div class="col-md-4">
									<div class="service-item first-item">
										<div>
											<img src="assets/images/${r[0] }" class="col-md-12" width="200px" height="266px"/>
										</div>
										<h4>${r[1] }</h4>
										<p>Depart: ${r[2] }</p>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
				</section>

				<!-- Top Image -->
				<section class="top-image">
					<div class="container-fluid">
						<div class="row">
							<div class="col-md-12">
								<img src="assets/images/top-image.jpg" alt="">
								<div class="down-content">
									<h4>Ante Interdum Chambray</h4>
									<p>Lorem ipsum dolor amet raclette chambray bitters,
										hammock celiac slow-carb flexitarian four dollar toast food
										truck health goth. Air plant brunch food truck vegan scenester
										organic crucifix irony pour-over pop-up austin hexagon kitsch
										swag. Godard literally humblebrag cloud bread vice master
										cleanse chambray typewriter put a bird on it brooklyn forage.</p>
									<div class="primary-button">
										<a href="#">Read More</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</section>
				<br />


			</div>
		</div>

		<!-- Sidebar -->
		<div id="sidebar">

			<div class="inner">

				<!-- Search Box -->
				<section id="search" class="alt">
					<%-- <form method="get" action="#">
						<input type="text" value="Welcome to ${username }" />
					</form>  --%>
				</section>


				<!-- Menu -->
				<nav id="menu">
					<ul>
						<li><a href="index.htm">Homepage</a></li>
						<li><a href="staff.htm">Staff</a></li>
						<li><a href="depart.htm">Depart</a></li>
						<li><a href="record.htm">Record</a></li>
						<li><a href="general.htm">General</a></li>
						<c:if test="${chkUsername == 'Admin' }">
							<li><a href="account.htm">Account</a></li>
						</c:if>
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
					<p class="copyright">
					<div id="translate_select" style="height: 20px; width: 30px">
					</div>
					<br /> <a rel="nofollow"
						href="https://www.facebook.com/templatemo">Copyright &copy;
						2020 Sang Hub's</a>
					</p>
				</footer>

			</div>
		</div>

	</div>
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
	<!-- Scripts -->
	<!-- Bootstrap core JavaScript -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<script src="assets/js/browser.min.js"></script>
	<script src="assets/js/breakpoints.min.js"></script>
	<script src="assets/js/transition.js"></script>
	<script src="assets/js/owl-carousel.js"></script>
	<script src="assets/js/custom.js"></script>
</body>


</body>

</html>