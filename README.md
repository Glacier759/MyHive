MyHive
======

参考Hive项目自己实现的一个简单的通用爬虫

#说明

>Hive.conf为程序的配置文件 - 有待完善

#2014-6-18 21.02 配置文件组织格式

>'serverIP'			服务端IP

>'serverUser'		服务端用户名

>'serverPassword'	服务端用户密码

>'redisIP'			redis服务IP

>'redisPort'		redis服务端口号

>'mysqlIP'			mysql服务IP		(暂时废弃)

>'mysqlUser'		mysql服务用户名		(暂时废弃)

>'mysqlPassword'	mysql服务用户密码	(暂时废弃)

>'Username'			登录用户名，同时也作为redis的Key值与抓取下目录所在位置

>'URL'				垂直爬虫的种子URL

>'Tinfo'			搜索爬虫搜索内容

>'Ttag'				搜索爬虫保存结果的目录结构

>'Flag'				判断是搜索爬虫(2)还是垂直爬虫(1)

>'ThreadNumber'		指定多线程模块的线程个数

>'SavePath'			指定抓取下来文件的保存路径

>'Maxpn'			搜索爬虫的搜索深度

>'DownloadURL'		抓取完成后提供给用户下载URL的前缀路径（与SavePath对应）

#JSP说明

>部署在Tomcat上后访问localhost:8080/MyHive/Hive_S_basic.jsp

>在标签栏填上信息后台即可开始数据抓取

>待更新..

#修复在一次抓取过后BloomFilter未清false的漏洞

#搜索爬虫与垂直爬虫均可使用 但由于前端不熟练还有一些反馈的bug等待修改
