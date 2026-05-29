<%@ page language="java" import="java.util.*,dbutil.*,entity.*,model.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>修改学生信息</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/site.css">
	</head>

	<body>
		<div class="page-shell">
			<div class="page-card">
				<div class="page-header">
					<h1 class="page-title">修改学生信息</h1>
					<p class="page-subtitle">当前正在编辑学号为 ${student.id} 的学生信息，确认无误后保存修改。</p>
				</div>
				<div class="page-body">
					<form action="${pageContext.request.contextPath}/DoUpdateStudentservlet.do?id=${student.id}" method="post">
						<div class="form-grid">
							<div class="field field-wide">
								<label>学号</label>
								<input type="text" value="${student.id}" readonly>
							</div>
							<div class="field">
								<label for="name">姓名</label>
								<input id="name" type="text" name="name" value="${student.name}">
							</div>
							<div class="field">
								<label for="sex">性别</label>
								<input id="sex" type="text" name="sex" value="${student.sex}">
							</div>
							<div class="field">
								<label for="age">年龄</label>
								<input id="age" type="text" name="age" value="${student.age}">
							</div>
							<div class="field">
								<label for="grade">班级</label>
								<input id="grade" type="text" name="grade" value="${student.grade}">
							</div>
							<div class="field">
								<label for="score">成绩</label>
								<input id="score" type="text" name="score" value="${student.score}">
							</div>
						</div>
						<div class="form-actions">
							<input class="btn" type="submit" value="保存修改">
							<input class="btn btn-secondary" type="reset" value="重置">
							<a class="btn btn-secondary" href="${pageContext.request.contextPath}/ListStudentServlet.do">返回列表</a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</body>
</html>
