### 前言
---
`Jingying` 起初是一个[毕设项目](https://blog.csdn.net/fanfan4569/article/details/80464517)，但同时是一个不断发展（学习）的项目。


### 项目简介
---
  前端采用Wepack + JQuery，后端采用JFinal + Beetl，图片FTP + Nginx，项目管理 Maven
  功能：
  - 用户模块（微信第三方登录，测试账号）
  - 商品模块
  - 分类模块
  - 支付模块
  - 购物车模块
  - 订单模块
  - 地址模块
  
  
#### 组织结构
```
Jingying
├─.git 
├─.idea 
├─fronEnd # Webpack脚手架 （前端）
├─src
| ├─ main/java/cn/jingying 
| |          └─ address # 地址模块（举个单个栗子）
| |                 ├─ controller
| |                 ├─ dao
| |                 ├─ model
| |                 └─ service
| └─ test/java
├─target
├─.gitignore
├─Jinying.iml
└─pom.xml 
```

### 还要做
---

#### 后端：
- Redis 
- Nginx（静态化页面）
- MongoDB
- ActiveMQ
- Quartz
- 日志
- Jenkins
- 事务处理
- 并发（JMeter）
- 前后端职责划分


#### 前端：
- 尝试Vue
- Waves
