<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/site.css">

	<title>新增学生信息</title>
    
  </head>
  
  <body>
  
 <div class="page-shell">
	<div class="page-card">
		<div class="page-header">
			<h1 class="page-title">新增学生信息</h1>
			<p class="page-subtitle">录入完整的学生基础信息，提交后会自动回到学生列表页面。</p>
		</div>
		<div class="page-body">
			<form action="${pageContext.request.contextPath}/InsertStudentServlet.do" method="post">
				<div class="form-grid">
					<div class="field">
						<label for="id">学号</label>
						<input id="id" type="text" name="id">
					</div>
					<div class="field">
						<label for="name">姓名</label>
						<input id="name" type="text" name="name">
					</div>
					<div class="field">
						<label for="sex">性别</label>
						<input id="sex" type="text" name="sex">
					</div>
					<div class="field">
						<label for="age">年龄</label>
						<input id="age" type="text" name="age">
					</div>
					<div class="field">
						<label for="grade">班级</label>
						<input id="grade" type="text" name="grade">
					</div>
					<div class="field">
						<label for="score">成绩</label>
						<input id="score" type="text" name="score">
					</div>
				</div>
				<div class="form-actions">
					<input class="btn" type="submit" value="提交">
					<input class="btn btn-secondary" type="reset" value="重置">
					<a class="btn btn-secondary" href="${pageContext.request.contextPath}/ListStudentServlet.do">返回列表</a>
				</div>
			</form>
		</div>
	</div>
 </div>
  </body>
</html>
