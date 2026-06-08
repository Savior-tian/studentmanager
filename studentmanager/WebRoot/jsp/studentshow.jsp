<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>学生详细信息</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/site.css">
	</head>
	<body>
		<div class="page-shell">
			<div class="page-card">
				<div class="page-header">
					<h1 class="page-title">删除学生信息</h1>
					<p class="page-subtitle">请确认以下信息无误。点击确认删除后，这条学生记录将被移除。</p>
				</div>
				<div class="page-body">
					<div class="details-grid">
						<div class="detail-item"><p class="detail-label">学号</p><p class="detail-value">${student.id}</p></div>
						<div class="detail-item"><p class="detail-label">姓名</p><p class="detail-value">${student.name}</p></div>
						<div class="detail-item"><p class="detail-label">性别</p><p class="detail-value">${student.sex}</p></div>
						<div class="detail-item"><p class="detail-label">班级</p><p class="detail-value">${student.grade}</p></div>
						<div class="detail-item"><p class="detail-label">年龄</p><p class="detail-value">${student.age}</p></div>
						<div class="detail-item"><p class="detail-label">成绩</p><p class="detail-value">${student.score}</p></div>
					</div>
					<div class="form-actions">
						<form action="${pageContext.request.contextPath}/DeleteStudentServlet.do?id=${student.id}" method="post">
							<input class="btn" type="submit" value="确认删除">
						</form>
						<form action="${pageContext.request.contextPath}/ListStudentServlet.do" method="post">
							<input class="btn btn-secondary" type="submit" value="返回列表">
						</form>
					</div>
				</div>
			</div>
		</div>

	</body>
</html>
