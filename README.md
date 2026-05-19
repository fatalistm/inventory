# 家电出入库管理系统

基于 Spring Boot 的企业级进销存管理平台，实现商品管理、入库/出库/销售出库、库存预警、利润统计、用户认证等功能。

## 技术栈

- **后端**：Spring Boot 2.7、Spring Data JPA、Spring Security、MySQL、Maven
- **前端**：Thymeleaf、Bootstrap、jQuery、AJAX
- **工具**：IntelliJ IDEA、Navicat、Git

## 主要功能

### 库存管理
- 商品列表展示（支持库存预警高亮）
- 模拟扫码枪输入（SKU 回车自动查询）
- 入库操作（增加库存、更新成本价、记录流水）
- 出库操作（扣减库存、校验库存充足）

### 销售出库
- 选择商品、填写销售单价和数量
- 自动计算净利润并扣减库存
- 生成销售订单

### 利润统计
- 基于商品最新入库成本价计算销售利润
- 展示每笔销售的成本价、销售价、利润
- 汇总总利润（净利润而非营业额）

### 商品管理
- 商品增删改查
- 设置进货价、销售价、预警值

### 用户认证
- Spring Security 表单登录
- BCrypt 密码加密
- 登录后访问受保护页面

### 入库/出库记录
- 按时间倒序列出入库单据
- 支持查看明细

## 快速开始

### 环境要求
- JDK 11
- MySQL 8.0
- Maven 3.6+

### 数据库配置
1. 创建数据库 `inventory`
2. 执行项目中的 `schema.sql`（或使用 JPA 自动建表，需修改 `ddl-auto`）
3. 修改 `application.yml` 中的数据库用户名和密码

### 运行项目
```bash
git clone https://github.com/fatalist/inventory.git
cd inventory
mvn clean install
mvn spring-boot:run
```
访问 http://localhost:8080/login
默认账号：admin / 123456

## 项目结构
```
src/main/java/com/example/inventory/
├── config/              # Spring Security 配置
├── controller/          # REST API 控制器
├── service/             # 业务逻辑层
├── repository/          # JPA 数据访问层
├── entity/              # 数据库实体类
└── dto/                 # 数据传输对象
```
```
src/main/resources/
├── application.yml      # 配置文件
├── templates/           # Thymeleaf 页面
│   ├── login.html
│   ├── dashboard.html
│   └── fragments/       # 页面片段
└── static/              # 静态资源
```
## 主要接口示例
| 功能 | 方法 | URL |
|------|------|-----|
| 商品列表 | GET | /api/product/list |
| 入库 | POST | /api/inbound/create?orderNo=...&supplier=...&inboundDate=...&operator=... + JSON body |
| 销售出库 | POST | /api/outbound/sale (JSON body) |
| 利润统计 | GET | /api/profit/statistics |
| 库存查询 | GET | /api/inventory/byProductId/{id} |
## 项目亮点
成本利润算法：使用最新入库成本价计算利润，避免简单营业额统计
扫码枪模拟：前端监听回车事件，实现扫码枪输入体验
库存预警：库存低于预警值时行高亮显示
动态页面加载：通过 AJAX 加载 Thymeleaf 片段，实现单页面式交互
## 作者
fatalistm - GitHub
## 许可证
本项目仅用于学习交流，未经许可不得用于商业用途。
