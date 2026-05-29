<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/site.css">

	<title>错误提示</title>
    

  </head>
  
  <body>
	<div class="page-shell">
		<div class="page-card">
			<div class="page-header">
				<h1 class="page-title">系统处理出错</h1>
				<p class="page-subtitle">当前请求未能正常完成，请根据提示信息检查输入或返回列表页继续操作。</p>
			</div>
			<div class="page-body">
				<div class="notice notice-error">${error}</div>
				<div class="form-actions">
					<a class="btn btn-secondary" href="${pageContext.request.contextPath}/ListStudentServlet.do">返回学生列表</a>
				</div>
			</div>
		</div>
	</div>
  </body>
</html>
