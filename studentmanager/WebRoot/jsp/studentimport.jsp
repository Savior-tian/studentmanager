<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>批量导入学生信息</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/site.css">
  </head>

  <body>
    <div class="page-shell">
    <div class="page-card">
    <div class="page-header">
      <h1 class="page-title">批量导入学生信息</h1>
      <p class="page-subtitle">支持直接粘贴 Excel 内容，也支持填写 CSV 文件路径进行导入，适合课堂演示时快速补录数据。</p>
    </div>
    <div class="page-body">
      <c:if test="${not empty error}">
      <div class="notice notice-error">${error}</div>
      </c:if>
      <form action="${pageContext.request.contextPath}/ImportStudentservlet.do" method="post" enctype="multipart/form-data">
      <div class="form-grid">
        <div class="field field-wide">
        <label for="pastedContent">Excel 粘贴内容</label>
        <textarea id="pastedContent" name="pastedContent"></textarea>
        </div>
        <div class="field field-wide">
        <label for="uploadFile">上传文件</label>
        <input id="uploadFile" type="file" name="uploadFile" accept=".csv,.xls,.xlsx">
        </div>
        <div class="field field-wide">
        <label for="filePath">本地文件路径</label>
        <input id="filePath" type="text" name="filePath">
        </div>
      </div>
      <p class="muted-note">支持三种方式：直接粘贴表格内容、上传 csv/xls/xlsx 文件、填写本地 csv/xls/xlsx 文件路径。首行表头会自动跳过。导入格式：学号,姓名,性别,年龄,班级,成绩</p>
      <div class="form-actions">
        <input class="btn" type="submit" value="开始导入">
        <input class="btn btn-secondary" type="button" value="返回学生列表" onclick="window.location.href='${pageContext.request.contextPath}/ListStudentServlet.do'">
      </div>
      </form>
    </div>
    </div>
  </div>
  </body>
</html>