ly-cloud-demo-sv 介绍
===
> 这是一个按照2018年定的规范开发的demo，可作为一个基础脚手架使用

# 更新日志
## V1.0
>- 项目新建，采用tk.Mybatis

## V2.0
>- 按2018年研究院定的新规范重构
>- 使用Mybatis-Plus

# 开发环境
- jdk1.7
- Tomcat 8.5.28
- Oracle 10g及以上
# 技术框架说明
- Spring boot
> [参考资料](https://spring.io/projects/spring-boot)
- Spring Cloud
> [参考资料](http://projects.spring.io/spring-cloud/)
- Spring MVC
> [参考资料](https://spring.io/projects/spring-framework)
- Mybatis
> [参考资料](http://www.mybatis.org/mybatis-3/)
- Mybatis Plus
> [参考资料](http://mp.baomidou.com/#/)

# 安装运行
1. 代码下载
```
git clone http://192.168.30.59/lygtc/ly-cloud-demo-sv.git
```
2. 运行
> DemoServiceApplication
3. 访问地址
> http://127.0.0.1:1400/swagger-ui.html

# 代码生成使用说明
1. 引入代码生成工具类
> ly-cloud-demo-sv\src\test\java\com\ly\cloud\common\mybatis\generator\MpGenerator.java
2. 引入代码生成模板,不引入则使用默认模板
> ly-cloud-demo-sv\src\main\resources\templates\\*
3. 按照注释修改对应配置，主要有表名、模块名、数据源、包名
4. 数据库对应表添加注释，用#号分隔
```
ex:
#User#对应实体名,注意这里不能带后缀PO，否则生成的文件名都带PO，可以在生成代码后使用IDE的重构功能重新命名
#name#对应属性名，首字母小写
```
5. 运行main方法

[官方文档地址](http://mp.baomidou.com/#/generate-code)