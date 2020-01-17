# crawler
这是爬虫与ES数据分析的一个样例实现

## 多线程爬虫和ES数据分析实战

### 清空数据库
```bash
rm news.mv.db
mvn flyway:migrate
``` 

### 切换数据库
修改db/mybatis/config.xml
```xml
<!--mac-->
<property name="url" value="jdbc:h2:file:/Users/admin/IdeaProjects/crawler/news"/>
```
