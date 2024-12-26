<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%
   request.setCharacterEncoding("UTF-8");
   String cp = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>SearchGroup.jsp</title>
<link rel="stylesheet" type="text/css" href="<%=cp%>/css/main.css">
<link rel="stylesheet" type="text/css" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
<script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
	<div class="panel-group">
		<div class="panel panel-default">
		
			<!-- <div class="panel-heading row"> -->
			<div class="panel-heading" style="height: 60px;">
				<span style="font-size: 17pt; font-weight: bold;" class="col-md-3">
					검색 결과 그룹 리스트 
				</span>
			</div>
			
			<div class="panel-body">
				검색 결과 그룹 수
				<span class="badge">${count }</span>
			</div>
			
			<div class="panel-body">
				<table class="table table-hover table-striped">
					<tr class="trTitle">
						<th>그룹ID</th>
						<th>그룹명</th>
						<th>소개글</th>
						<th>아이콘</th>
						<th>오픈 일자</th>
						<th>그룹 홈</th>
					</tr>
					 
					 <c:forEach var="group" items="${resultList }">
						 <tr>
						 	<td>${group.id }</td>
						 	<td>${group.name }</td>
						 	<td>${group.introduction }</td>
						 	<td>${group.root }</td>
						 	<td>${group.open_date }</td>
						 	
						 	<td>
								<a href="group.action?group_id=${group.id }" role="button" class="btn btn-success">그룹 홈</a>
					 		</td>
						 </tr>
					 </c:forEach>
				</table>
			</div>
		</div>
	</div>
</div>

</body>
</html>