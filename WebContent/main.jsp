<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<!-- meta charset="utf-8"-->
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>JustPhoto</title>

	<link rel="shortcut icon" href="<c:url value='/photos/diagram-22_4332.ico' />" type="image/x-icon">

	<link href="<c:url value='/css/default.css' />" rel="stylesheet">
	<link href="<c:url value='/css/bootstrap.min.css' />" rel="stylesheet">
	<!-- Add fancyBox -->
	<link rel="stylesheet" href="<c:url value='/css/jquery.fancybox.css?v=2.1.5' />" type="text/css" media="screen" />
	
</head>
<body>
	<jsp:include page="header.jsp" />
	
	<div class="container">
		<div class="row">
  			<c:if test="${listNamesPhotos.size() > 0}">
  				<c:forEach var="i" begin="0" end="${listNamesPhotos.size() - 1}">
  					<c:if test="${i % 4 eq 0}">
  						</div>
  						<div class="row">
  					</c:if>
  					<div class="col-xs-6 col-md-3">
    					<a href="<c:url value='/photos/${listNamesPhotos.get(i)}' />" class="fancybox thumbnail" rel="group">
      						<img src="<c:url value='/photos/resize/${listNamesPhotos.get(i)}' />" alt="...">
    					</a>
  					</div>
  				</c:forEach>
  			</c:if>
		</div>
	</div>

	<script src="https://code.jquery.com/jquery.js"></script>
    <script src="<c:url value='/js/bootstrap.min.js' />"></script>
    
	<!-- Add mousewheel plugin (this is optional) -->
	<script type="text/javascript" src="<c:url value='/js/jquery.mousewheel-3.0.6.pack.js' />"></script>

	<!-- Add fancyBox -->
	<script type="text/javascript" src="<c:url value='/js/jquery.fancybox.pack.js?v=2.1.5' />"></script>

	<script type="text/javascript">
		$(document).ready(function() {
			$(".fancybox").fancybox({
				openEffect	: 'none',
				closeEffect	: 'none'
			});
		});
	</script>
</body>
</html>