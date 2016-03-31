<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
<title>demo</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<meta name="keywords"
	content="Elvis Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript">
	
	 addEventListener("load", function() {setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 

</script>
<meta charset utf="8">

<!--bootstrap-->
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
<!--coustom css-->
<link href="css/style.css" rel="stylesheet" type="text/css" />
<!--default-js-->
<script src="js/jquery-2.1.4.min.js"></script>
<!--bootstrap-js-->
<script src="js/bootstrap.min.js"></script>
<!--script-->
<!---- start-smoth-scrolling---->
<script type="text/javascript" src="js/move-top.js"></script>
<script type="text/javascript" src="js/easing.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function($) {
		$(".scroll").click(function(event) {
			event.preventDefault();
			$('html,body').animate({
				scrollTop : $(this.hash).offset().top
			}, 1000);
		});
	});
</script>
<!--start-smoth-scrolling-->
</head>
<body>
	<div class="header-top" id="home">
		<div class="navbar-wrapper">
			<nav class="navbar navbar-default">
			<div class="container">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
						aria-expanded="false">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<div class="logo">
						<a class="navbar-brand" href="index.html">iPhone mic</a>
					</div>
				</div>
				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<ul class="form_acess nav-floats2">
						<form class="re-disgn1" method="post" action="servlet/Searchsongs">
							<input type="text" name="search" class="textbox" value="Search.."
								onfocus="this.value = '';"
								onblur="if (this.value == '') {this.value = 'Search...';}">

						</form>
					</ul>
					<div class="clearfix"></div>
				</div>
				<!-- /.navbar-collapse -->
				<div class="clearfix"></div>
			</div>
			<!-- /.container-fluid --> </nav>
		</div>
	</div>
	<div class="header-bottom">
		<div id="myCarousel" class="carousel slide" data-ride="carousel">
			<!-- Indicators -->

			<!-- Wrapper for slides -->
			<div class="carousel-inner" role="listbox">
				<div class="item active">
					<img src="./images/bn1.jpg" alt="First slide" class="img-wid" />
				</div>
			</div>

			<!-- Controls -->
			<a class="left carousel-control" href="#myCarousel" role="button"
				data-slide="prev"> <span
				class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
				<span class="sr-only">Previous</span>
			</a> <a class="right carousel-control" href="#myCarousel" role="button"
				data-slide="next"> <span
				class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
				<span class="sr-only">Next</span>
			</a>
		</div>
		<!--our-partners-->
		<div class="our-partners">
			<div class="container">
				<h3>Controls</h3>
				<div class="col-md-3 flot-stn2">
					<form method="post" action="servlet/PlayandPause">
						<input type="submit" value="Play/Pause" />
					</form>
				</div>
				<div class="col-md-3 flot-stn2">
					<form method="post">
						<input type="submit" value="Voice switch" />
					</form>
				</div>
				<div class="col-md-3 flot-stn2">
					<form method="post">
						<input type="submit" value="Vol +" />
					</form>
				</div>
				<div class="col-md-3 flot-stn2">
					<form method="post">
						<input type="submit" value="Vol -" />
					</form>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
		<!--our-partners-->

		<div class="footer" id="contact">
			<div class="fot-bottom">
				<p>Copyright &copy; 2016. Dai</p>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			/*
			var defaults = {
			    containerID: 'toTop', // fading element id
			    containerHoverID: 'toTopHover', // fading element hover id
			    scrollSpeed: 1200,
			    easingType: 'linear' 
			};
			 */

			$().UItoTop({
				easingType : 'easeOutQuart'
			});

		});
	</script>
	<a href="#home" id="toTop" class="scroll" style="display: block;">
		<span id="toTopHover" style="opacity: 1;"> </span>
	</a>
	</div>
</body>
</html>
