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

<title>Staff Page</title>

<!-- Bootstrap core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Additional CSS Files -->
<link rel="stylesheet" href="assets/css/fontawesome.css">
<link rel="stylesheet" href="assets/css/templatemo-style.css">
<link rel="stylesheet" href="assets/css/owl.css">
<style type="text/css">
*[id$=errors]{
	color:red;
	font-style: italic;
}
</style>
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
									<strong>Staff Page</strong>
								</p>
							</div>
						</div>
					</div>
				</div>
				<!-- Form Demo -->
				<form action="formStaff.htm" method="post"
					enctype="multipart/form-data">
					<div class="col-md-12">
						<div class="row">
							<div class="col-md-6">
								<div style="border: 1px solid red; height: 300px">
									<c:if test="${nameimage == null }">
										<img src="assets/images/employee.png" height="300px" />
									</c:if>
									<c:if test="${nameimage != null }">
										<img src="assets/images/${nameimage }" height="300px" />
									</c:if>
								</div>
							</div>
							<div class="col-md-6">
								<button name="btn-submit">Submit</button>
							</div>
							<div class="col-md-12" style="margin-top: 30px">
								<input type="file" name="photo" /> <span style="color: red">${messageImage }</span>
							</div>
						</div>
					</div>
				</form>
				<form:form action="formStaff.htm" modelAttribute="staff">
					<div class="col-md-12">
						<div class="row">


							<div class="col-md-6" style="margin-top: 30px">
								<fieldset>
									<form:input path="id" class="form-control" type="text"
										placeholder="ID..." />
									<form:errors path="id" />
								</fieldset>
							</div>
							<div class="col-md-6" style="margin-top: 30px">
								<fieldset>
									<form:input path="name" class="form-control" type="text"
										placeholder="Your name..." />
									<form:errors path="name" />
								</fieldset>
							</div>
							<div class="col-md-6" style="margin-top: 30px">
								<fieldset>
								<%-- <fmt:formatNumber value="${salary }"
												pattern="#,##0" var="mySalary"/> --%>
									<form:input path="salary" class="form-control" type="text"
										placeholder="Salary..." />
									<form:errors path="salary" />
								</fieldset>
							</div>
							<div class="col-md-6" style="margin-top: 30px">
								<fieldset>
									<form:input path="email" class="form-control" type="text"
										placeholder="Email..." />
									<form:errors path="email" />
								</fieldset>
							</div>
							<div class="col-md-4" style="margin-top: 30px">
								<fieldset>
									<form:input path="phone" class="form-control" type="text" 
										placeholder="Phone..." />
									<form:errors path="phone" />
								</fieldset>
							</div>
							<div class="col-md-4" style="margin-top: 30px">
								<fieldset>
									<form:input path="birthday" class="form-control" type="text"
										placeholder="Birthday format dd/MM/yyyy..." />
									<form:errors path="birthday" />
								</fieldset>
							</div>
							<div class="col-md-4" style="margin-top: 30px">
								<fieldset>
									<form:input path="photo" class="form-control" type="text"
										placeholder="Photo..." value="${nameimage }" readonly="true" />
									<form:errors path="photo" />
								</fieldset>
							</div>
							<div class="col-md-4 col-sm-4" style="margin-top: 30px;">
								<div class="circle-item">
									<form:radiobutton path="gender" id="demo-small" value="1"
										checked="checked" />
									<label for="demo-small">Nam</label>
								</div>
							</div>
							<div class="col-md-8 col-sm-4" style="margin-top: 30px">
								<div class="circle-item">
									<form:radiobutton path="gender" id="demo-medium" value="0" />
									<label for="demo-medium" class="col-md-6 col-sm-4">Nữ</label>
								</div>
							</div>


							<div class="col-md-12" style="margin-top: 30px">
								<form:select id="category" class="form-control" path="depart.id"
									items="${departs }" itemValue="id" itemLabel="name">
								</form:select>
							</div>

							<div class="col-md-12" style="margin-top: 30px">
								<textarea name="notes" id="demo-message"
									placeholder="Your notes..." rows="3" class="col-md-12"></textarea>
							</div>
							<div class="col-md-12">
								<span style="color: red">${messageAll }</span>
							</div>
							<span style="color:${color}" class="col-md-12">${message }</span>
							<div class="col-md-3" style="margin-top: 70px">
								<button class="button" name="btn-insert">INSERT</button>
							</div>
							<div class="col-md-3" style="margin-top: 70px">
								<button class="button" name="btn-update" onclick="reloadPage();">UPDATE</button>
							</div>
							<div class="col-md-3" style="margin-top: 50px" id="sa">
								<c:if test="${displaySt == null }">
								<div style="display: block; margin-top: 20px">
									<button class="button" name="btn-delete" >DELETE</button>
								</div>
								</c:if>
								<c:if test="${displaySt != null }">
								<div style="display: block">
									<span>Bạn có thật sự muốn xóa ?</span>
									<button class="button" name="btn-yes">YES</button>
									<button class="button" name="btn-no">No</button>
								</div>
								</c:if>
							</div>
							<div class="col-md-3" style="margin-top: 70px">
								<button class="button" name="btn-clear">CLEAR</button>
							</div>
						</div>
					</div>
				</form:form>
				<br />
				<br />
				<br />
				<section class="alt">
					<form method="get" action="formStaff.htm">
						<div class="col-md-12">
							<div class="row">
								<div class="col-md-3" style="margin-top: 2px">
									<input type="text" id="search" name="search"
										placeholder="Search..." class="form-control" />
								</div>
								<div class="col-md-9">
									<button class="button" name="btn-search">Search</button>
								</div>
							</div>
						</div>
					</form>
				</section>
				<br /> <br /> <br />
				<section>
					<!-- Table Demo -->
					<table>
						<thead>
							<tr>
								<th>ID</th>
								<th>Name</th>
								<th>Gender</th>
								<th>Birthday</th>
								<th>Salary</th>
								<th>Photo</th>
								<th>DepartID</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="r" items="${tableStaff }">
								<form:form action="tableStaff.htm" method="post">
									<tr>
										<td>${r.id }</td>
										<td>${r.name }</td>
										<td>${r.gender ?'Nam':'Nữ' }</td>
										<td><fmt:formatDate value="${r.birthday }"
												pattern="dd/MM/yyyy" /></td>
										<td><fmt:formatNumber value="${r.salary }"
												pattern="#,##0" /></td>
										<td><img src="assets/images/${r.photo }"></td>
										<td>${r.depart.id }</td>
										<td><button name="btn-edit">Edit</button> <input
											type="hidden" value="${r.id }" name="id-info" /></td>
									</tr>
								</form:form>
							</c:forEach>
						</tbody>
					</table>
				</section>

			</div>
		</div>

		<!-- Sidebar -->
		<div id="sidebar">

			<div class="inner">

				<!-- Search Box -->
				<section id="search" class="alt"></section>

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
<!-- 	<script type="text/javascript">
	setTimeout(function(){
		   window.location.reload(1);
		}, 5000);
	</script> -->
	<!-- Scripts -->
	<script type="text/javascript">
	function reloadPage(){
        location.reload(true);
    }
	</script>
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