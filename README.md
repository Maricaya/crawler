# crawler
这是爬虫与ES数据分析的一个样例实现

## 多线程爬虫和ES数据分析实战

### 清空数据库
```shell script
rm news.mv.db
mvn flyway:clean && mvn flyway:migrate
``` 
### 切换数据库
修改db/mybatis/config.xml
```xml
<!--mac h2-->
<property name="url" value="jdbc:h2:file:/Users/admin/IdeaProjects/crawler/news"/>

<!--docker mysql-->
<property name="url" value="jdbc:mqsql://localhost:3306/news"/>
```

```xml
<configuration>
    <url>jdbc:mqsql://localhost:3306/news</url>
    <user>root</user>
    <password>root</password>
</configuration>
```
### docker
```shell script
docker run --name mysql -e MYSQL_ROOT_PASSWORD=root -p 3306:3306 -v `pwd`/mysql-data:/var/lib/mysql -d mysql:5.7.27

# 杀掉
docker rm -f mysql
```
### 数据库从h2迁移到mysql遇到的问题 
将mysql改为utf-8
```mysql-psql
--创建table需要加上 DEFAULT CHARSET=utf8mb4;
create table LINKS_TO_BE_PROCESSED (link varchar(1000)) DEFAULT CHARSET=utf8mb4;


--ALTER DATABASE news CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;
```

