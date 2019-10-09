# SayLoveWall（表白墙）
一款基于易班授权的表白墙
![](http://47.93.201.74:8082/01.png)
![](http://47.93.201.74:8082/02.png)
![](http://47.93.201.74:8082/03.png)
##介绍
 * 本项目采用比较原始的开发模式
 * 将普通JavaWeb项目改为maven项目
 * 本项目目前没有使用任何框架，采用原始的BaseServlet进行路径映射
## 运行方案
 * git https://github.com/wangyang1749/saylovewall.git
 * 将saylovewall的sql导入数据库
 * mvn jetty:run
 * http://localhost:8080
## 期望
 * 采用嵌入式的H2数据库
 * 采用Spring JdbcTemplate进行持久化
 * 完善权限管理 