# payroll-management-system

## 1.介绍
JSP课设：学校内部工资管理系统

## 2.技术栈

前后端分离

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
|    NoSql     |         redis（弃用）          |

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

### 4.启动项目

![image-20220524235352562](https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/image-20220524235352562.png)

出现如下界面

![image-20220524235416288](https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/image-20220524235416288.png)

成功！接下来就可以开始愉快的编码了

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

