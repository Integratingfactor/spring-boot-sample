<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Integratingfactor.com</title>

<!-- Bootstrap core CSS -->
<link href="/css/bootstrap.min.css" rel="stylesheet">
<!-- Bootstrap theme -->
<link href="/css/bootstrap-theme.min.css" rel="stylesheet">
<!-- custom themes for sign-in and form pages -->
<link href="/css/signin.css" rel="stylesheet">
<!-- favicons for the site -->
<link rel="apple-touch-icon" sizes="57x57" href="/favicon/apple-icon-57x57.png">
<link rel="apple-touch-icon" sizes="60x60" href="/favicon/apple-icon-60x60.png">
<link rel="apple-touch-icon" sizes="72x72" href="/favicon/apple-icon-72x72.png">
<link rel="apple-touch-icon" sizes="76x76" href="/favicon/apple-icon-76x76.png">
<link rel="apple-touch-icon" sizes="114x114" href="/favicon/apple-icon-114x114.png">
<link rel="apple-touch-icon" sizes="120x120" href="/favicon/apple-icon-120x120.png">
<link rel="apple-touch-icon" sizes="144x144" href="/favicon/apple-icon-144x144.png">
<link rel="apple-touch-icon" sizes="152x152" href="/favicon/apple-icon-152x152.png">
<link rel="apple-touch-icon" sizes="180x180" href="/favicon/apple-icon-180x180.png">
<link rel="icon" type="image/png" sizes="192x192"  href="/favicon/android-icon-192x192.png">
<link rel="icon" type="image/png" sizes="32x32" href="/favicon/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="96x96" href="/favicon/favicon-96x96.png">
<link rel="icon" type="image/png" sizes="16x16" href="/favicon/favicon-16x16.png">
<link rel="manifest" href="/favicon/manifest.json">
<meta name="msapplication-TileColor" content="#ffffff">
<meta name="msapplication-TileImage" content="/favicon/ms-icon-144x144.png">
<meta name="theme-color" content="#ffffff">
</head>
<body style="background-color: #fff;">
    <c:if test="${user != null }">
      <c:set var="openIdAction" value="/openid/logout"/>
      <c:set var="buttonLabel" value="Logout"/>
      <c:set var="userName" value="${user.firstName }"/>
    </c:if>
    <c:if test="${user == null }">
      <c:set var="openIdAction" value="/openid/login"/>
      <c:set var="buttonLabel" value="Login"/>
      <c:set var="userName" value="Guest"/>
    </c:if>
    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <div class="navbar-brand">Integratingfactor.com</div>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <form class="navbar-form navbar-right" action="${openIdAction }" method="POST">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button class="btn btn-success" type="submit">${buttonLabel}</button>
          </form>
        </div><!--/.navbar-collapse -->
      </div>
    </nav>
	<div class="jumbotron">
      <div class="container">
        <h1>Integratingfactor.com</h1>
        <p>Welcome ${userName}</p>
        <p>Integrate technology into life!</p>
        <p><a class="btn btn-primary btn-lg" href="#" role="button">Coming soon</a></p>
      </div>
    </div>
    <div class="container">
      <!-- Example row of columns -->
      <div class="row">
        <div class="col-md-4">
          <h2>Mission</h2>
          <p>Make life simple by creating platform and services that unify technology with life.</p>
          <p><a class="btn btn-default" href="http://blog.integratingfactor.com" role="button">View details</a></p>
        </div>
        <div class="col-md-4">
          <h2>Services</h2>
          <p>Enable developers to build applications that make life simple for individuals.</p>
          <p><a class="btn btn-default" href="#" role="button">Coming soon</a></p>
       </div>
        <div class="col-md-4">
          <h2>About</h2>
          <p>Learn more about Integratingfactor.com</p>
          <br>
          <p><a class="btn btn-default" href="#" role="button">Coming soon</a></p>
        </div>
      </div>

  		<!-- FOOTER -->
  		<hr>
  		<div class="form-signin">
		<footer>
			<p class="pull-center">
			&copy; 2015 Integratingfactor.com
			</p>
		</footer>
		</div>
    </div>
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>