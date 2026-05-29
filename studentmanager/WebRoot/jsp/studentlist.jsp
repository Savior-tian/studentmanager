<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>学生信息列表</title>
		<link rel="stylesheet" type="text/css" href="../assets/site.css">

	</head>
	<body>
	<div class="page-shell">
		<div class="page-card">
			<div class="page-header">
				<h1 class="page-title">学生信息列表</h1>
				<p class="page-subtitle">查看、维护和导入学生信息，页面和数据库连接已经统一调整为 UTF-8，便于直接演示和截图。</p>
			</div>
			<div class="page-body">
				<div class="toolbar">
					<p class="muted-note">当前可执行新增、修改、删除和批量导入操作。</p>
					<div class="toolbar-actions">
						<a class="btn" href="jsp/studentinsert.jsp">新增学生</a>
						<a class="btn btn-secondary" href="ImportStudentservlet.do">批量导入</a>
					</div>
				</div>
		<c:if test="${not empty message}">
			<div class="notice notice-success">${message}</div>
		</c:if>
		<c:if test="${not empty error}">
			<div class="notice notice-error">${error}</div>
		</c:if>
		<table class="data-table">
			<tr>
				<th width="50px">学号</th>
				<th width="100px">姓名</th>
				<th width="80px">性别</th>
				<th width="50px">年龄</th>
				<th width="150px">班级</th>
				<th width="50px">成绩</th>
				<th width="50px">修改</th>
				<th width="50px">删除</th>
			</tr>
			<c:forEach var="studentitem" items="${studentlist}">
				<tr>
					<td >
						${studentitem.id}
					</td>
					<td >
						${studentitem.name}
					</td>
					<td >
						${studentitem.sex}
					</td>
					<td >
						${studentitem.age}
					</td>
					<td >
						${studentitem.grade}
					</td>
					<td >
						${studentitem.score}
					</td>
					<td >
						<a class="action-link" href="UpdateStudentservlet.do?id=${studentitem.id}">修改</a>
					</td>
					<td >
						<a class="action-link" href="showStudent.do?id=${studentitem.id}">删除</a>
					</td>
				</tr>
			</c:forEach>
		</table>
			</div>
		</div>
	</div>
	</body>
</html>

