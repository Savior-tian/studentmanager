<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>����ѧ����Ϣ</title>
  </head>

  <body>
    <center>
      <h1>����ѧ����Ϣ</h1>
      <p>֧���� Excel �п���ճ���ı�������Ҳ������ Excel ����Ϊ CSV �����ļ�·����</p>
      <p style="color: red;">${error}</p>
      <form action="ImportStudentservlet.do" method="post">
        <p>Excel ճ�����ݣ�</p>
        <p>
          <textarea name="pastedContent" rows="10" cols="90"></textarea>
        </p>
        <p>���� CSV �ļ�·����</p>
        <p>
          <input type="text" name="filePath" size="90" />
        </p>
        <p>����ʽ��ѧ��,����,�Ա�,����,�༶,�ɼ�</p>
        <p>
          <input type="submit" value="��ʼ����" />
          <input type="button" value="���ز鿴�б�" onclick="window.location.href='ListStudentServlet.do'" />
        </p>
      </form>
    </center>
  </body>
</html>