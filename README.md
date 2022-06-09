# payroll-management-system

## 1.介绍

项目地址：[gitee](https://gitee.com/pymjl_0/payroll-management-system)

JSP课设：学校内部工资管理系统

这是大二JSP课程的课设，考虑到组员欠缺项目的开发经验，技术的积累也不够，所以这次课设我们选用的是原生的Servlet整合了一些其他的诸如Mybatis等常用的框架进行开发

因为最近考试太多，所有人都在忙着期末冲刺，所以这个项目的完成度不高，另外，因为其他组员是第一次进行项目开发,所以很多接口存在潜在的bug以及很多不规范的地方。

但是，这个项目对于刚入门的同学来说依然还是有着一定的学习价值，因为这个项目我设置了异常处理，单例工厂创建类，跨域，拦截器，Druid连接池的使用，Jedis，token，Mybatis等等。我相信很多人都知道如何使用Spring Boot整合这些框架，但是对于原生的可能就不是太了解，即便是我刚开始使用原生的来整合这些框架的时候也是花了一定的时间的，不妨将这个项目作为夯实自己基础的一个学习项目

## 2.技术栈

前后端分离，前端使用的Vue+anxious

后端技术栈：打包成war部署到云服务器上

|     JDK      | corretto-11(保证是jdk11就行了) |
| :----------: | :----------------------------: |
|   Servlet    |               4                |
|  数据库驱动  |              8.0+              |
|    Tomcat    |             9.0.62             |
|    数据库    |   mysql 8.0.15（上云服务器）   |
|   权限管理   |         Shrio（弃用）          |
|     日志     |            logback             |
|     认证     |              jwt               |
|   依赖管理   |             Maven              |
|     Json     |            Jackson             |
| 数据库连接池 |        Druid（德鲁伊）         |
|   DAO框架    |            Mybatis             |
|    NoSql     |         redis         |

## 3.需求分析

![学校内部工资管理系统](https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/%E5%AD%A6%E6%A0%A1%E5%86%85%E9%83%A8%E5%B7%A5%E8%B5%84%E7%AE%A1%E7%90%86%E7%B3%BB%E7%BB%9F.png)

## 4.快速开始

### 1.克隆

![image-20220524232720142](https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/image-20220524232720142.png)

出现如下界面：

![image-20220524232806719](https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/image-20220524232806719.png)

输入刚才复制的URL，然后点击Clone

### 2.导入为Maven项目

刚克隆下去的项目是这样的：

![image-20220524232942499](https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/image-20220524232942499.png)

**他还不是一个Maven项目，打开core目录，你会看见一个`pom.xml` 现在他还只是一个普通的xml文件样式，证明idea还没有识别到它，需要你右键点击`pom.xml` ，然后点击如图所示（idea会弹出一个提醒页面，直接点击Trust Project就好）：**

![image-20220524233128070](https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/image-20220524233128070.png)

然后idea就会开始导入Maven项目，下载相应的Jar包。

**注意！！！**

**如果你是第一次使用Maven那么你还需要配置一下你的Maven仓库，即`settings.xml` 配置文件，你不必再自己装一个Maven，idea自带Maven，但是，idea自带的Maven没有`settings.xml` ,所以需要你在idea的Maven配置目录下新建一个settings.xml，然后将下面的内容复制进去**

> idea自带的Maven配置文件在用户目录下的.m2文件夹内，即：
>
> C:\Users\你的用户名\\.m2

如图所示：

![image-20220524234804246](https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/image-20220524234804246.png)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
<mirrors>
    <!-- 阿里云仓库 -->
    <mirror>
        <id>alimaven</id>
        <mirrorOf>central</mirrorOf>
        <name>aliyun maven</name>
        <url>http://maven.aliyun.com/nexus/content/repositories/central/</url>
    </mirror>
 
    <!-- 中央仓库1 -->
    <mirror>
        <id>repo1</id>
        <mirrorOf>central</mirrorOf>
        <name>Human Readable Name for this Mirror.</name>
        <url>http://repo1.maven.org/maven2/</url>
    </mirror>
 
    <!-- 中央仓库2 -->
    <mirror>
        <id>repo2</id>
        <mirrorOf>central</mirrorOf>
        <name>Human Readable Name for this Mirror.</name>
        <url>http://repo2.maven.org/maven2/</url>
    </mirror>
</mirrors> 
 
</settings>
```

成功后的Maven项目如图所示：

![image-20220524234924614](https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/image-20220524234924614.png)

如果更换了配置文件后还没刷新那么点击idea右侧的Maven，手动刷新导入

![image-20220524235014557](https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/image-20220524235014557.png)

左上角的符号就是刷新

### 3.配置Tomcat

这一步我相信大家都会了，点击右上角

![image-20220524235106547](https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/image-20220524235106547.png)

![image-20220524235119247](https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/image-20220524235119247.png)

![image-20220524235143011](https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/image-20220524235143011.png)

![image-20220524235234471](https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/image-20220524235234471.png)

![image-20220524235249441](https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/image-20220524235249441.png)

### 

### 4.更改配置文件

#### MySql配置

1. 首先你需要修改数据库的配置信息，先在项目目录中找到`payroll_system.sql`脚本，然后先在数据库里面创建`payroll_system`数据库，然后运行sql脚本
2. 修改`resource`目录下的`druid.properties`配置文件

![image-20220609213722126](https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/image-20220609213722126.png)

```properties
#druid配置文件
#驱动
driverClassName=com.mysql.cj.jdbc.Driver
#url
url=jdbc:mysql://localhost:3306/payroll_system?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8&allowPublicKeyRetrieval=TRUE
#用户名
username=root
#密码
password=root
#连接池初始化大小
initialSize=10
#活动链接
maxActive=20
#最小连接数
minIdle=10
#使用的内置过滤器  若不配置 则不会统计SQL执行
filters=stat
#获取连接的最大等待时间，单位为毫秒
maxWait=60000
poolPreparedStatements=true
maxPoolPreparedStatementPerConnectionSize=200
```

#### Redis配置

修改资源路径下的`redis.settings`配置文件

![image-20220609213859311](https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/image-20220609213859311.png)

```properties
#-------------------------------------------------------------------------------
# Redis客户端配置样例
# 每一个分组代表一个Redis实例
# 无分组的Pool配置为所有分组的共用配置，如果分组自己定义Pool配置，则覆盖共用配置
# 池配置来自于：https://www.cnblogs.com/jklk/p/7095067.html
#-------------------------------------------------------------------------------
#----- 默认（公有）配置
# 地址，默认localhost
host=localhost
# 端口，默认6379
port=6379
# 最大空闲连接数, 默认8个
maxIdle=8
# 最小空闲连接数, 默认0
minIdle=0
# 最大连接数, 默认8个
maxTotal=8
# 超时，默认2000
timeout=2000
# 连接超时，默认timeout
connectionTimeout=2000
# 读取超时，默认timeout
soTimeout=2000
# 密码，默认无
password=
# 数据库序号，默认0
database=2
# 客户端名，默认"Hutool"
clientName=pymjl
# SSL连接，默认false
ssl=false;
```

#### 邮箱配置

修改资源路径下的`mail.settings`

![image-20220609214039431](https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/image-20220609214039431.png)

```properties
# 发件人（必须正确，否则发送失败），“小磊”可以任意变更，<>内的地址必须唯一，以下方式也对
# from = hutool@yeah.net
from=
# 密码（注意，某些邮箱需要为SMTP服务单独设置密码，详情查看相关帮助）
pass=
# 使用SSL安全连接
sslEnable=true
```

### 5.启动项目

#### 后端项目

![image-20220524235352562](https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/image-20220524235352562.png)

出现如下界面

![image-20220524235416288](https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/image-20220524235416288.png)

成功！

#### 前端

1. 打开idea的控制台，左下角的`Terminal`,进入到前端的根目录

```txt
cd payroll
```

2. 拉依赖（确保电脑已配好Vue的环境）

```txt
npm install
```

3. 启动服务

```txt
npm run serve
```

出现如下界面则成功

![image-20220609214512475](https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/image-20220609214512475.png)



## 5.项目结构

```txt
C:.
├─.idea		#idea配置文件，别管
│  ├─artifacts
│  ├─inspectionProfiles
│  └─libraries
└─core 			#项目核心代码
    ├─logs 		#项目日志文件
    ├─src 		#Java代码
    │  ├─main
    │  │  ├─java
    │  │  │  └─cuit
    │  │  │      └─pymjl
    │  │  │          └─core
    │  │  │              ├─config #配置文件
    │  │  │              ├─entity #实体类，对应数据库的表
    │  │  │              │  └─user #各自在这下面创建自己的实体对象，每个人一个包
    │  │  │              ├─mapper #dao层，操作数据库的接口，各自在下面建自己的包
    │  │  │              ├─result #结果集，返回前端的统一格式
    │  │  │              ├─servlet #Servlet包，各自在下面建自己的包
    │  │  │              │  └─user
    │  │  │              └─util 一些工具类
    │  │  ├─resources #项目资源
    │  │  │  └─cuit
    │  │  │      └─pymjl
    │  │  │          └─core
    │  │  │              └─mapper #存放mapper.xml文件，注意你的mapper要个resource下的xml同级
    │  │  └─webapp
    │  │      └─WEB-INF
    │  └─test #单元测试目录
    │      ├─java
    │      │  └─cuit
    │      │      └─pymjl
    │      │          └─core
    │      └─resources
    │          └─cuit
    │              └─pymjl
    │                  └─core
    │                      └─mapper
    └─target #s
        ├─classes
        │  └─cuit
        │      └─pymjl
        │          └─core
        │              ├─config
        │              ├─entity
        │              │  └─user
        │              ├─mapper
        │              ├─result
        │              ├─servlet
        │              │  └─user
        │              └─util
        ├─core-1.0-SNAPSHOT
        │  ├─META-INF
        │  └─WEB-INF
        │      ├─classes
        │      │  └─cuit
        │      │      └─pymjl
        │      │          └─core
        │      │              ├─config
        │      │              ├─entity
        │      │              │  └─user
        │      │              ├─mapper
        │      │              ├─result
        │      │              ├─servlet
        │      │              │  └─user
        │      │              └─util
        │      └─lib
        ├─generated-sources
        │  └─annotations
        ├─generated-test-sources
        │  └─test-annotations
        └─test-classes
            └─cuit
                └─pymjl
                    └─core
                        └─mapper
```

