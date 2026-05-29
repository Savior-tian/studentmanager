# Eclipse 导入说明

这个工程已经从旧版 MyEclipse 项目描述转换为标准 Eclipse Dynamic Web Project。

推荐导入方式：

1. 在 Eclipse 中选择 File -> Import -> Existing Projects into Workspace。
2. 选择当前 studentmanager 的上一级目录。
3. 导入后如果看到缺少 Servlet 相关类：
   - 右键项目 -> Properties -> Targeted Runtimes
   - 勾选本机安装的 Apache Tomcat
4. 如果没有 Tomcat 运行时：
   - Window -> Preferences -> Server -> Runtime Environments
   - Add -> Apache Tomcat -> 选择安装目录
5. 然后执行 Project -> Clean。

项目结构：

- Java 源码目录：src
- Web 资源目录：WebRoot
- 编译输出目录：build/classes