## 多线程爬虫和ES数据分析实战

### TODO
-   完善前端页面
-   部署上线

### 清空数据库
```shell script
rm news.mv.db
mvn flyway:clean && mvn flyway:migrate
``` 
### 切换数据库
全局搜索 jdbc:mqsql://localhost:3306/news，改为本地的数据库

db/mybatis/config.xml
```xml
<!--mac h2-->
<!--docker mysql-->
<property name="url" value="jdbc:mqsql://localhost:3306/news"/>
```
pom.xml
```xml
<configuration>
    <url>jdbc:mqsql://localhost:3306/news</url>
    <user>root</user>
    <password>root</password>
</configuration>
```
### docker启动mysql 和 ElasticSearch
1. 启动mysql
-   run             运行一个容器
-   --name          后面是这个镜像的名称
-   -e              MYSQL_ROOT_PASSWORD=root 环境变量，设置 MySQL 服务 root 用户的密码为root。
-   -p              将容器的3306端口映射到主机的3306端口，外部主机可以直接通过 宿主机ip:3306 访问到 MySQL 的服务。
-   -v \`pwd\`/mysql-data:/var/lib/mysql  将主机\`pwd\`/mysql-data目录挂载到容器的/var/lib/mysql.保证数据持久化
-   -d              后台运行容器，并返回容器ID
-   mysql:5.7.27    设置mysql版本号
```shell script
docker run --name mysql -e MYSQL_ROOT_PASSWORD=root -p 3306:3306 -v `pwd`/mysql-data:/var/lib/mysql -d mysql:5.7.27
```

2. 启动ElasticSearch
-   9300端口： ES节点之间通讯使用
-   9200端口： ES节点 和 外部 通讯使用
-   -e "discovery.type=single-node" 环境变量 单节点
```shell script
docker run -d -v `pwd`/esdata:/usr/share/elasticsearch/data  --name elasticsearch -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" elasticsearch:7.4.0
```

### 启动爬虫项目
使用Idea启动 `Main`

1. 制造mysql假数据
启动`MockDataGenerator`

2. 制造ElasticSearch假数据
启动`ElasticSearchDataGenerator`

3. 在命令行中使用
启动`ElasticSearchEngine`，输入搜索字段

### 项目中遇到的问题
1. 数据库从h2迁移到mysql 
将mysql改为utf-8
```mysql-psql
--创建table需要加上 DEFAULT CHARSET=utf8mb4;
create table LINKS_TO_BE_PROCESSED (link varchar(1000)) DEFAULT CHARSET=utf8mb4;
```

2. mysql建立索引
```mysql-psql
-- 联合索引created_at + modified_at
create index create_at_modified_at_index
on NEWS(created_at,modified_at)

-- 1s
select * from NEWS where created_at='2019-01-01' and modified_at<'2019-02-01'
-- 10s 很慢
select * from NEWS where created_at>'2019-01-01' and modified_at='2019-02-01'
```




