# 理工科艺基于GPS的班车实时定位系统开发

## 各阶段任务

1. 2019.03.25-2019.04.01

   完成系统开发的需求分析说明文档，将包含功能模块图、用例图、数据流图。详细见：/doc/需求分析/需求分析文档.docx

2. 2019.04.02-2019.04.30

   出系统Demo，完成显示地图、班车选择、班车路线站点显示、班车到达各站点时间等各班车路线信息展示。

## 开发进度

### 需求分析

1. 已完成

   数据流图、用例图、用例清单、用例描述、功能模块

2. 待完成

   数据字典

3. 待修改

   数据流图、用例图

### 概念设计

1. 已完成

   

2. 待完成

   E-R图、概念模型、逻辑模型、物理模型

3. 待修改

## 本阶段学习任务和开发任务

### 需学习内容（4月10日前学习并掌握）

1. 多线程：陆宇豪、许鑫磊、唐倩倩
2. 微信导入系统接口(url)：陆宇豪
3. 服务器拦截报文：陆宇豪、许鑫磊

### 开发任务（4月30日前出Demo）

1. 数据库设计：陆宇豪、许鑫磊、唐倩倩、王一帆
2. 报文解析存入数据库：许鑫磊
3. 多线程接收/处理报文：许鑫磊、唐倩倩
4. 教师登录、教师查看指定路线的校车定位；管理员登录、管理员指定对应路线的校车（管理员部分页面可先简化）：陆宇豪、王一帆

## 项目重点知识

1. GPS信号发送/接收形式

   GPS信号以TCP/UDP协议报文形式发送到指定服务器IP，因此需要监控服务器指定端口，获取并解析GPS信号。GPS信号中带有的信息主要包含：经纬度（具体的坐标系标准暂定）、方向角、时间等等

2. 多线程（线程池）

   由于班车数量较多，因此需要考虑多线程问题。采用线程池的方式，用一个线程抓取监听端口获取到的GPS报文进行解析。

3. 高并发

   由于查看班车信息的大多为教师，且访问时间较为固定，大致在6:00~8:00这个个时间段存在多用户访问，因此需要考虑高并发问题。

## 隐藏问题

1. 校车行驶时间段不统一
2. 路线行驶校车不确定
3. 意见反馈和回复需要审核