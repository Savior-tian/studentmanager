# Student Manager

一个基于 JSP + Servlet + MVC 的学生信息管理系统实验项目，支持学生信息的新增、查询、修改、删除，以及批量导入。

项目访问入口：

- http://localhost:8080/studentmanager/ListStudentServlet.do

## 功能说明

- 学生信息列表展示
- 新增学生信息
- 修改学生信息
- 删除学生信息
- 批量导入学生信息
  - 支持直接粘贴 Excel 表格内容
  - 支持上传 csv、xls、xlsx 文件
  - 支持填写本地 csv、xls、xlsx 文件路径
  - 支持自动跳过首行表头

## 技术栈

- Java Servlet / JSP
- JSTL
- MySQL
- Apache Tomcat 9
- Apache POI
- Commons FileUpload

## 项目结构

```text
6.3/
├─ student.sql                    数据库初始化脚本
├─ studentmanager/
│  ├─ src/                        Java 源码
│  │  ├─ control/                 Servlet 控制层
│  │  ├─ model/                   数据访问与业务逻辑
│  │  ├─ entity/                  实体类
│  │  └─ dbutil/                  数据库连接工具
│  ├─ WebRoot/                    Web 资源目录
│  │  ├─ jsp/                     JSP 页面
│  │  ├─ assets/                  页面样式资源
│  │  └─ WEB-INF/
│  │     ├─ web.xml               Web 配置
│  │     └─ lib/                  项目依赖包
│  └─ ECLIPSE-IMPORT.md           Eclipse 导入说明
└─ README.md
```

## 运行环境

建议使用以下环境：

- JDK 8 或以上
- MySQL 8
- Apache Tomcat 9
- Eclipse EE 或其他支持 Java Web 的 IDE

## 数据库配置

当前数据库连接配置位于 studentmanager/src/dbutil/Dbconn.java：

- 数据库地址：localhost:3306/students
- 用户名：root
- 密码：root

连接串使用 UTF-8：

```java
jdbc:mysql://localhost:3306/students?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&useSSL=false
```

如果你的数据库用户名或密码不同，请先修改 studentmanager/src/dbutil/Dbconn.java。

## 数据库初始化

1. 先启动 MySQL。
2. 创建数据库 students。
3. 执行根目录下的 student.sql。

也可以直接执行类似命令：

```sql
CREATE DATABASE students DEFAULT CHARACTER SET utf8;
USE students;
SOURCE student.sql;
```

## 部署与运行

### 方式一：使用 Eclipse 导入运行

1. 打开 Eclipse。
2. 选择 File -> Import -> Existing Projects into Workspace。
3. 选择 studentmanager 的上一级目录。
4. 导入项目后，配置 Tomcat Runtime。
5. 右键项目，Run on Server。
6. 浏览器访问：

```text
http://localhost:8080/studentmanager/ListStudentServlet.do
```

### 方式二：部署到本地 Tomcat

1. 将 studentmanager/WebRoot 下的内容部署到 Tomcat 的 webapps/studentmanager 目录。
2. 编译 src 下的 Java 文件到 WEB-INF/classes。
3. 启动 Tomcat。
4. 访问系统入口地址。

## 主要页面与接口

- /ListStudentServlet.do：学生列表页
- /InsertStudentservlet.do：新增学生
- /UpdateStudentservlet.do?id=...：进入修改页
- /DoUpdateStudentservlet.do?id=...：提交修改
- /showStudent.do?id=...：删除确认页
- /DeleteStudentservlet.do?id=...：执行删除
- /ImportStudentservlet.do：批量导入

## 导入格式说明

导入数据格式统一为：

```text
学号,姓名,性别,年龄,班级,成绩
```

示例：

```text
学号	姓名	性别	年龄	班级	成绩
101	张三	男	20	软件1班	90
102	李四	女	21	软件1班	95
```

说明：

- 第一行表头可保留，系统会自动跳过。
- 粘贴导入时可使用 Excel 直接复制后的制表符分隔内容。
- 文件导入时支持 csv、xls、xlsx。
