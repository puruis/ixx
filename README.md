# ixx(爱学习)快速开发脚手架

### 简单,简单,简单 重要的事 说三遍 :smile: 


#### 项目介绍
平常经常写一些demo项目,需要用到后台,找不到就习惯到码云上去寻找一个开源项目来改,前期这种方式也让我学习了别人很多代码优秀的地方
但自己也想写一个来造福别人,不止是做一个伸手党,项目前期并不完善,陆续跟新修改中,大家有什么建议,可以给我提

>ixx 系统是我想边学习边编写的一个java快速开发脚手架项目

> 1.已经支持 用户 角色 菜单 相关权限控制

> 2.支持多数据源

> 3.支持又拍云储存

> 4.支持邮件发送

> 5.创建时间 创建人 修改时间 修改人 相关字段会自动填充

> 脚手架基本功能都已实现，后续还会一直更新下去！   

> 后续会把功能当中 用到的 学到的技术扩展到其中 学习    

![JDK](https://img.shields.io/badge/jdk-1.8-green.svg?style=flat-square) [![LICENSE](https://img.shields.io/github/license/gujiniCY/mayday.svg?style=flat-square)](https://gitee.com/zprsss/ixx)[![star](https://gitee.com/zprsss/ixx/badge/star.svg?theme=white)](https://gitee.com/zprsss/ixx) 

## 快速开始
`https://gitee.com/zprsss/ixx.git`

本地下载 redis 双击运行(找不到下载链接的 854554762@qq.com 联系我 邮件发你)

下载代码以后，运行sql文件夹下的sql文件，并且到src/main/resources下的application-dev.yml下修改你的数据库链接
保存好以后 mvn package打包运行 进入target文件夹运行java -jar ixx-web.jar

如果使用又拍云储存的话请到 ixx-core模块的 PaiFileUploadUtils 设置 参数
 
## 图片演示
![输入图片说明](https://images.gitee.com/uploads/images/2019/0123/143841_26e304d5_1289853.png "QQ截图20190123095856.png")
![输入图片说明](https://images.gitee.com/uploads/images/2019/0123/143852_34f8513c_1289853.png "QQ截图20190123095804.png")
![输入图片说明](https://images.gitee.com/uploads/images/2019/0123/143859_96514b6b_1289853.png "QQ截图20190123100754.png")
![输入图片说明](https://images.gitee.com/uploads/images/2019/0123/143908_38afabf0_1289853.png "QQ截图20190123100855.png")
![输入图片说明](https://images.gitee.com/uploads/images/2019/0123/143913_643c778a_1289853.png "QQ截图20190123100909.png")
![输入图片说明](https://images.gitee.com/uploads/images/2019/0123/143918_3ed0fd8b_1289853.png "QQ截图20190123100924.png")

## 开源协议
[MIT](https://gitee.com/song_haozhi/mayday/blob/master/LICENSE)
## 致谢：
 1. [springboot](http://spring.io/projects/spring-boot) 版本2.0.1.RELEASE
 2. [mybatis-plus](https://github.com/baomidou/mybatis-plus/) MyBatis 的增强工具
 3. [mysql](https://www.mysql.com/) 数据库，版本5.6
 4. [maven](http://maven.apache.org/)  依赖管理
 5. [druid](https://github.com/alibaba/druid/) 阿里连接池
 6. [thymeleaf](https://www.thymeleaf.org/) spring官方推荐的模板引擎
 7. [dynamic-datasource-spring-boot-starter](https://github.com/baomidou/dynamic-datasource-spring-boot-starter) 多数据源
 12.[pagehelper](https://pagehelper.github.io/) mybatis分页插件
 14. [layui](https://gitee.com/sentsin/layui)  layui前端框架



