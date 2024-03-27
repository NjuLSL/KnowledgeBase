# 搭建SpringBoot项目

## 将新文件交给Git管理

每次修改代码需要点击IDEA右下角git->commit来进行提交

## 将本地项目关联远程Github

~~~java
git remote add origin git@github.com:NjuLSL/KnowledgeBase.git//origin指的是后面的一串地址
git push -u origin
    git push -u origin main
~~~

## 使用HTTPclient测试接口

# 准备数据库

## 集成Mybatis(持久层框架)

~~~
//名词
持久层
ORM

//集成Mybatis
引入依赖
配置数据源

//使用Mybatis
配置mybatis所有Mapper.xml所在的路径
mybatis.mapper-locations=classpath:/mapper/**/*.xml
使用@MapperScan扫描所有的Mapper接口
@MapperScan(“com.jiawa.wiki.mapper”)

//接口和XML的命名
使用@Service或@RestController注解，将Service类或Controller类交给Spring来管理了
使用@Resource或@Autowire将一个类注入到另一个类中
~~~

## 集成Mybatis Generator代码生成器

~~~
如何集成Mybatis Generator
增加插件依赖
增加配置文件generator-config.xml
增加执行命令

RequestMapping注解也可以用在类上面
~~~

在resources文件夹下新建generator-config.xml文件

在controller层中主函数加注解@RequestMapping即可得到主地址

**在generator-config.xml文件中通过添加表来自动编写各级层**

## 电子书列表查询接口开发

~~~
后端接口统一返回参数，方便前端统一处理
CommonResp
~~~

ebook数据库创建代码：

~~~sql
CREATE TABLE `ebook` (
  `id` bigint NOT NULL COMMENT 'id',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `category1_id` bigint DEFAULT NULL COMMENT '分类1',
  `category2_id` bigint DEFAULT NULL COMMENT '分类2',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `cover` varchar(200) DEFAULT NULL COMMENT '封面',
  `doc_count` int NOT NULL DEFAULT '0' COMMENT '文档数',
  `view_count` int NOT NULL DEFAULT '0' COMMENT '阅读数',
  `vote_count` int NOT NULL DEFAULT '0' COMMENT '点赞数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='电子书';
~~~

在包里新建response包用于**建立一个通用类CommonResp**从而让前端更方便地获取数据****

## 封装请求参数和返回参数

~~~
封装请求和返回参数
XXXReq
XXXResp
BeanUtils工具类的使用
扩展一下，IDEA有个插件：GenerateAllSetter，可以快速生成一堆set
~~~

**根据名称模糊查询电子书:**

利用mybatis自动创建的内部类EbookExample中的**criteria**

```java
EbookExample ebookExample = new EbookExample();
EbookExample.Criteria criteria = ebookExample.createCriteria();
```

在包里新建request包用于**关于电子书的请求类参数EbookReq**从而让后端处理数据****

在Response包中建立一个包用于**关于电子书的响应类参数EbookResp**从而让前端更方便地获取数据,数据封装在CommonResp的变量里

~~~java
List<EbookResp> ebookRespList = new ArrayList<>();

for (Ebook ebook : list) {
	EbookResp ebookResp = new EbookResp();
	BeanUtils.copyProperties(ebook, ebookResp);//BeanUtils工具类用于复制属性，左给右
	ebookRespList.add(ebookResp);
}
return ebookRespList;
~~~

## 制作CopyUtil封装BeanUtils

~~~
CopyUtil工具类

如果代码改动了，但是出现莫名的错，可以试一下maven clean，比如:
	代码没错却编译出错
	测试结果跟代码不符
	引入了jar包却没反应
~~~

使用自己编写的CopyUtil来封装Java自带的BeanUtils。包括**单体复制和列表复制**，列表复制传入的第二个参数也是泛型的类，而不是一个列表

# 搭建Vue-CLI项目

## Vue CLI项目搭建运行

利用窗口直接打开vue项目：

web文件夹下package.json文件右键

## Vue CLI项目结构

~~~
index.html, main.ts, app.vue三者关联

public和assets两个静态资源文件的引用方法
	public不参与打包
	assets会被打包

index.html引入外部静态文件用<%= BASE_URL %>

子文件里的.gitignore文件是会生效的，里面配置的文件规则，是相对当前.gitingore文件所在位置

README.md只有根目录下的生效

package.json 作用类似于pom.xml

eslintrc.js 是一把双刃剑

Vue CLI需要编译才能发布
~~~

node_modules存放的是vue  cli依赖的所有js文件

vue项目全部集中在src包中

~~~
src
	assets//静态资源
	components//主键
	router//路由
	store//存储全局数据
	views//存放页面
App.vue//vue初始界面
~~~

前端采用**懒加载**的形式，只有访问对应的页面才会加载相应的内容

## 集成Ant Design Vue

~~~
集成Ant Design Vue
npm install ant-design-vue@2.0.0-rc.3 --save
~~~

## 网站首页布局开发

~~~
Ant Design Vue布局

router-view的用法
相当于一个界面占位符

router-link to的用法
用于页面跳转
~~~

## 制作Vue自定义组件

~~~
将公共的代码块提取成组件，方便维护

怎么制作自定义组件
~~~

将header和footer提取成组件

# 四、前后端交互

## 集成HTTP库axios

~~~
集成axios
Vue3新增了setup初始化方法

跨域
可以这样理解，来自一个IP端口的页面（vue项目），要访问另一个IP端口的资源
（springboot请求接口），会产生跨域访问。

增加CorsConfig配置类，解决跨域问题
~~~

首先通过指令安装axios

vue3新增的**setup()方法**：组件加载后初始就会执行的方法。

在config文件夹中加入CorsConfig文件，运行后成功获取数据

## Vue3数据绑定显示列表数据

~~~
使用Vue3 ref实现数据绑定

使用Vue3 reactive实现数据绑定

使用toRef将某个属性变成一个响应式的变量

补充：使用toRefs可以将所有属性变成响应式的变量
~~~

~~~
Vue2代码结构，目前实际工作中还是以Vue2为主
Vue3生命周期函数
~~~

vue具有不同的**生命周期函数**

~~~vue
export default{
	name
	data
	mounted
	methods
}
在vue3里全部被setup包含
~~~

vue3新增了**ref**，用来定义响应式数据

所有的数据使用**{{xxx}}**来获取变量

vue3新增了**reactive**，用来定义响应式数据(多次获取)**（需要配合toRef)**

## 电子书列表界面展示

~~~
any类型是一把双刃剑

集成图标库
npm install @ant-design/icons-vue@5.1.8 --save

Example可以实现动态SQL

样式调整可以先在浏览器开发者工具里面调试，再加到页面上
~~~

## Vue CLI多环境配置

~~~
增加开发和生产配置文件

修改编译和启动支持多环境

修改axios请求地址支持多环境
~~~

~~~
多环境配置文件要放在web根目录下

.env.xxx，后缀xxx和package.json里的指令的–mode xxx对应

增加–port参数来修改启动端口

自定义变量必须以VUE_APP_开头

通过设置axios.defaults.baseURL，来统一设置后端的IP端口或域名
~~~

get请求得到不同ip地址的资源。在web文件夹下创建文件.env.dev，修改package.json的路径

**使用axios配置不用每次访问都写路径（全局），在main.js里修改**

## 使用axios拦截器打印前端日志

配置axios拦截器打印请求参数和返回参数（main.js）

## SpringBoot过滤器的使用

~~~
配置过滤器，打印接口耗时
~~~

## SpringBoot拦截器的使用

~~~
拦截器的使用
返回true会往后执行
返回false会结束，可以利用这点来做权限拦截
addPathPatterns()，要拦截请求
excludePathPatterns()，排除请求，不拦截

拦截器和过滤器的相同与不同
都可以用来统一处理请求，比如：打印日志、权限控制
过滤器依赖于servlet容器，拦截器依赖Spring框架
过滤器不用注入其它类，拦截器可注入其它类，基于这一点，建议能用拦截器的都用拦截器

过滤器拦截器作用范围图解
~~~

## SpringBoot AOP的使用

~~~
配置AOP，打印接口耗时、请求参数、返回参数
~~~

~~~
AOP的使用
需要加入AOP依赖

重要概念
切点
切面
前置通知
后置通知
环绕通知

打印请求参数和返回参数很重要，用于开发调试和生产运维，特别是和第三方接口之间的交互。

安全规范：敏感字段不能明文打印

内容太长的字段不要打印，或截取固定长度打印文件、富文本
~~~

# 电子书管理功能开发

## 增加电子书管理页面

~~~
增加电子书页面

增加电子书菜单

增加电子书路由
~~~

每次新添加.vue界面都要在index.js文件里面添加路由

## 电子书表格展示

~~~
熟悉表格组件的各种应用场景

表格渲染
slots: 自定义渲染
title: 表头渲染
customRender: 值渲染
~~~

## 使用PageHelper实现后端分页

~~~
集成PageHelper插件

修改电子书列表接口，支持分页（假分页数据）
~~~

在依赖里添加组件后修改EbookService层的代码，让后端得到的列表数据进行分页

## 封装分页请求参数和返回参数

~~~
请求参数封装，PageReq

返回结果封装，PageResp
~~~

## 前后端分页功能整合

~~~
前端修改列表查询分页参数

前端修改接收列表查询结果

电子书管理页面和首页都需要改
~~~

~~~
axios发送get请求参数的两种写法

拼接url

使用固定的params参数
~~~

axios写请求参数固定属性为params

## 制作电子书表单

~~~
点击每一行编辑按钮，弹出编辑框

编辑框显示电子书表单
~~~

~~~
弹出框组件、表单组件的使用
~~~

## 完成电子书编辑功能

~~~
增加后端保存接口

点击保存时，调用保存接口

保存成功刷新列表
~~~

~~~
保存接口请求参数用XXXSaveReq

查询接口请求参数XXXQueryReq

查询接口返回参数用XXXQueryResp

POST请求，如果是以json方式提交，后端参数需要增加@RequestBody，如果是以form方式提交，则不需要加任何注解
~~~

POST请求，如果是以json方式提交，后端参数需要增加@RequestBody，如果是以form方式提交，则不需要加任何注解

## 雪花算法与新增功能

~~~
时间戳概念

雪花算法工具类

完成新增功能
~~~

~~~
时间戳：当前时间（北京时间）与1970-01-01 08:00:00的毫秒时间差

雪花算法
~~~

时间戳概念：当前时间（北京时间）与1970-01-01 08:00:00的毫秒时间差，是**数值**，其他是日期格式化

**雪花算法**：用于生成全局唯一、有序、分布式的 ID 的算法。根据当前**时间戳、（数据中心/机器中心）机器 ID 和序列号**生成唯一的 ID。它的设计目的是解决分布式系统中生成唯一 ID 的需求，同时保证 ID 可以按照时间顺序递增，且具有足够的性能和容量。

**id新增的三种方式：id自增/uuid/雪花算法**

## 增加删除电子书功能

~~~
电子书管理页面，点击某一行的删除按钮时，删除该行电子书

	1、后端增加删除接口

	2、前端点击删除按钮时调用后端删除接口

	3、删除时需要有一个确认框
~~~

~~~
@DeleteMapping

使用@PathVariable获取url上的参数

气泡确认框
~~~

`@PathVariable` 是 Spring 框架中用于处理 RESTful URL 中的参数的注解。通过 `@PathVariable` 注解，可以将 URL 中的模板变量（例如 `/users/{userId}` 中的 `{userId}`）映射到方法的参数上。

## 集成Validation做参数校验

~~~
对电子书查询和保存做参数校验
集成spring-boot-starter-validation
对保存接口和查询接口增加参数校验
校验不通过时，前端弹出错误提示
~~~

写完校验规则后还要开启校验。

异常需要进行统一拦截处理。

## 电子书管理优化

~~~
增加名字查询
编辑时复制对象
~~~

~~~
js对象复制：将json对象转为json字符串，再转回json对象
~~~

对象复制是为了防止在修改时直接影响页面上数据的展示

# 分类管理功能开发

~~~
分类基本增删改查功能：表设计、生成持久层代码、从电子书管理拷贝出一套分类管理代码

分类表格显示优化：不需要分页、树型表格

分类编辑功能优化：新增/修改分类时，支持选中某一分类作为父分类，或无父分类

电子书管理功能优化：编辑电子书时，可以选择分类一、分类二；表格显示分类名称

首页显示分类菜单

点击某一分类时，显示该分类下所有的电子书
~~~

## 分类表设计与代码生成

~~~
分类表设计：项目设计两级分类，表设计支持无限级分类

生成持久层代码
~~~

~~~
简单的树型结构的设计，使用parent列，标识父节点
~~~

**基本无限极树：id+parent(parent也是id)**

## 完成分类基本增删改查功能

## 分类表格显示优化

~~~
不需要分页，一次查出全部数据

改为树形表格展示
~~~

~~~
学会看日志查问题

vue，给属性绑定值时，加冒号和不加冒号的区别
不加冒号的话，属性值全部是字符串
加冒号的话，可以使用字符串、数字、布尔值，以及响应式变量

递归算法的套路：
自己调用自己，直到某个条件不再调用自己
~~~

## 分类编辑功能优化

~~~
编辑（新增/修改）分类时，支持选中某一分类作为父分类，或无父分类
~~~

## 电子书管理增加分类选择

~~~
电子书管理页面，使用级联选择组件Cascader，选择分类

电子书列表应该显示分类名称，而不是分类ID
~~~

~~~
对a-table某一列有自定义的显示方式，可以定义一个template，添加一种渲染效果

在setup里面可以定义普通的变量，不需要所有的变量都是响应式变量

第三方组件会提供内置的变量参数，如果不知道是什么值，可以打印到界面或日志看看。
~~~

## 首页显示分类菜单

~~~
加载所有分类列表，并转成树形结构

将菜单数据改成使用分类树
~~~

~~~
JS定时器

setTimeout：只执行一次

setInterval：重复执行
~~~

## 点击分类菜单显示电子书

~~~
首页默认显示欢迎页面

点击欢迎时，显示欢迎组件，点击分类时，显示电子书

点击某分类时，显示该分类下的电子书

点击分类时，重新查询电子书

电子书后端接口增加分类参数
~~~

# 文档管理功能开发

~~~
重点学习无限级树的管理功能设计&富文本编辑框的使用

文档表设计与代码生成

按照分类管理的代码，复制出一套文档树管理

关于无限级树的增删改查功能开发

文档内容保存与显示：富文本框的使用

首页点击某个电子书时，跳到文档页面，显示文档树

点击某个文档时，加载文档内容
~~~

## 文档表设计与代码生成

~~~
简单的树型结构的设计，使用parent列，标识父节点
~~~

## 完成文档表基本增删改查功能

按照分类管理，复制出一套文档管理的代码

## 使用树形选择组件选择父节点

~~~
编辑表单中的父文档选择框改为树形选择组件，完成编辑功能
~~~

~~~
a-tree-select树形选择组件的使用

递归算法

数组操作：unshift, shift, push, pop
~~~

## Vue页面参数传递完成新增文档功能

~~~
在电子书管理页面点击【文档管理】，跳到文档管理页面时，带上当前电子书id参数ebookId

新增文档时，读取电子书id参数ebookId
~~~

## 增加删除文档功能

~~~
树枝的删除，可以将递归算法放到前端

重要的操作增加二次确认
~~~

需要将文档的所有子文档全部删除

## 集成富文本插件wangEditor

~~~
概念：富文本

在Vue中集成富文本框插件wangeditor

v-modal在第一次显示的时候才会创建modal元素，所以初始要去找modal里的元素是找不到的
~~~

## 文档内容表设计与代码生成

## 文档管理页面布局修改

将文档列表和表单变成左右布局 **栅格系统**

~~~
概念：响应式设计

如果要做前端，建议学习Bootstrap

栅格系统的使用

默认展开表格树需要配合上v-if
~~~

**响应式设计：同一套代码根据屏幕变化展示不同页面**

## 文档内容的保存

~~~
文档内容的保存

前端获取富文本框的html字符串

保存文档接口里，增加内容参数，保存时同时保存文档和内容
~~~

## 文档内容的显示

~~~
文档内容的显示

增加单独获取内容的接口

前端得到html字符串后，放入富文本框中
~~~

## 文档页面功能开发-1

~~~
增加文档页面，首页点击电子书时，进到该电子书的文档页面

左边显示文档树

右边显示第一个节点的内容
~~~

# 用户管理&单点登录

用户管理登录/登录校验

用户名不能重复

**密码两层加密：加密传输、加密存储**

**密码重置**



登录功能：

**登录校验**：接口登录和界面登录

## 用户表基本增删改查

## 用户名重复校验与自定义查询

校验重复时抛出自定义异常

**修改时用户名不能修改**

前端的校验存在危险可以被绕过

## 密码两层加密处理

密码加密存储

密码加密传输：添加js工具包里的md5.js代码	**加盐让原始字符串更复杂**

## 增加重置密码功能

~~~
修改用户时不能修改密码

单独开发重置密码表单
~~~

## 单点登录token

登录

1. 输入用户名密码
2. 校验用户名密码
3. 生成登录标识**（token唯一字符串）**表面是否已经登陆过
4. 前端后端都要保存token

校验

1. 前端请求时带上token（放在header里）
2. 登录拦截器校验token，校验token（**到redis获取token**）
3. 校验成功继续业务
4. 校验失败回到登录页面

### 单点登录系统

多个系统用一个登录接口或者界面

## token与JWT

token即token和redis组合	token无意义，保证唯一即可

**JWT：token有意义，加密的，包含业务信息（一般是用户信息）**不用存储redis

## 登录功能开发

~~~
后端增加登录接口

前端增加登录模态框
~~~

## 登录成功处理

后端保存用户信息

​	集成redis

​	登录成功后生成token，以token为key，用户信息为value，放入redis中**（利用雪花算法生成token）**

前端显示登录用户

​	header显示登录昵称

​	使用vuex+sessionStorage保存登录信息

**vuex全局访问变量**：在store的index.js文件里进行添加

**sessionStorage解决vue页面刷新登录失效问题**

## 增加退出登录功能

目标：将token置为失效

后端增加退出登录接口，清除redis

前端添加退出登录按钮，清除信息

## 后端接口增加登录校验

后端增加拦截器，校验token有效性

前端请求增加token参数

## 前端页面增加登录校验

~~~
未登录时，管理菜单要隐藏

对路由判断，防止用户手敲url访问页面
~~~

在路由index.js中对每个路由增加meta：LoginRequire从而进行判断处理

## 用户密码初始化

# 阅读数点赞数功能开发

有文档被点赞时，前端可以收到通知

**SpringBoot异步化、WebSocket、RocketMQ**

## 文档阅读数更新

**对于一些复杂的sql需要自己写sql语句**

## 文档点赞功能开发

前端在文档下方增加点赞按钮

根据远程IP进行过滤点赞（使用redis存入key）****

在redis里放的值也要考虑过期时间

**用户访问会先进入nginx再进入SpringBoot，因此需要使用nginx反向代理得到真实ip**

## 电子书信息更新方案

实时更新

定时批量更新

## SpringBoot定时任务实例

启用定时器，不需要引入依赖

两种定时器写法

```
@EnableScheduling启用定时任务
```

## 完成电子书信息定时更新功能

编写一个job

## 日志流水号的使用

日志统一写一起显得杂乱无章，同一个业务的日志整合在一起

## WebSocket使用

点赞时前端收到通知

**定时轮询&被动通知**

被动通知：与服务器建立连接，实时

## 完成点赞通知功能

点赞时组装消息内容往websocket推送

前端收到websocket消息进行推送

## 使用异步化解耦点赞通知功能

**SpringBoot异步化使用**（添加线程）

- 添加注解@EnableAsync
- 在想异步的方法上添加注解@Async（java会重新创建一个代理类）

**需要加事务的方法上加入注解@Transactional**

## 使用MQ解耦点赞通知功能

SpringBoot异步化线程太多，占用资源

使用**RocketMQ解耦**

# 知识库功能开发

## 统计数据收集与Echarts报表

复杂sql编写，前端Echarts使用

## 电子书快照表设计

## 电子书快照收集脚本编写

快照分为两部分：

总阅读数、总点赞数

今日阅读数、今日点赞数

## 完成电子书快照功能

增加定时任务，定时手机数据

## 首页统计数值功能开发

## Echarts集成使用

## 30天趋势图功能开发

# 项目部署发布
