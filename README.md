# RBAC 权限管理系统

## 项目简介

这是一个基于 Spring Boot + MyBatis + Shiro 的 RBAC (Role-Based Access Control，基于角色的访问控制) 权限管理系统。系统实现了用户、角色、权限的完整管理功能，适用于企业级应用的权限控制需求。

## 技术栈

### 后端技术
- **Spring Boot 2.6.3** - 核心框架
- **MyBatis** - 持久层框架
- **Apache Shiro 1.4.0** - 安全框架，实现认证和授权
- **MySQL** - 关系型数据库
- **Druid 1.2.8** - 阿里巴巴数据库连接池
- **Redis** - 缓存中间件
- **PageHelper 1.3.0** - MyBatis 分页插件
- **Lombok** - 简化 Java 代码

### 前端技术
- **Thymeleaf** - 模板引擎
- **Bootstrap** - 前端 UI 框架
- **jQuery** - JavaScript 库
- **AdminLTE** - 后台管理模板
- **ECharts** - 数据可视化图表库
- **Font Awesome & Ionicons** - 图标库

### 工具依赖
- **FastJSON 1.2.76** - JSON 解析器
- **Thymeleaf Extras Shiro** - Thymeleaf 与 Shiro 集成

## 项目结构

```
rbac/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── huike/
│       │           ├── RbacApplication.java          # 启动类
│       │           ├── advice/                       # 全局通知/切面
│       │           ├── config/                       # 配置类
│       │           │   ├── ShiroConfig.java          # Shiro 安全配置
│       │           │   └── UserRealm.java            # 自定义 Realm
│       │           ├── controller/                   # 控制器层
│       │           │   ├── DepartmentController.java # 部门管理
│       │           │   ├── EmployeeController.java   # 员工管理
│       │           │   ├── PermissionController.java # 权限管理
│       │           │   ├── RoleController.java       # 角色管理
│       │           │   └── UserController.java       # 用户管理
│       │           ├── entity/                       # 实体类
│       │           │   ├── Customer.java             # 客户实体
│       │           │   ├── CustomerTraceHistory.java # 客户跟进记录
│       │           │   ├── CustomerTransfer.java     # 客户转移记录
│       │           │   ├── Department.java           # 部门实体
│       │           │   ├── Employee.java             # 员工实体
│       │           │   ├── Permission.java           # 权限实体
│       │           │   ├── Role.java                 # 角色实体
│       │           │   └── SystemDictionaryItem.java # 数据字典
│       │           ├── exception/                    # 异常处理
│       │           ├── mapper/                       # MyBatis Mapper 接口
│       │           │   ├── DepartmentMapper.java
│       │           │   ├── EmployeeMapper.java
│       │           │   ├── PermissionMapper.java
│       │           │   ├── RoleMapper.java
│       │           │   └── UserMapper.java
│       │           ├── query/                        # 查询对象（DTO）
│       │           ├── service/                      # 业务逻辑层
│       │           │   ├── impl/                     # Service 实现类
│       │           │   ├── DepartmentService.java
│       │           │   ├── EmployeeService.java
│       │           │   ├── PermissionService.java
│       │           │   ├── RoleService.java
│       │           │   └── UserService.java
│       │           └── util/                         # 工具类
│       └── resources/
│           ├── application.yml                       # 应用配置文件
│           ├── generatorConfig.xml                   # MyBatis Generator 配置
│           ├── com/huike/mapper/                     # MyBatis XML 映射文件
│           ├── static/                               # 静态资源
│           │   ├── login.html                        # 登录页面
│           │   ├── css/                              # 样式文件
│           │   ├── js/                               # JavaScript 文件
│           │   └── img/                              # 图片资源
│           └── templates/                            # Thymeleaf 模板
│               ├── error.html                        # 错误页面
│               ├── include.html                      # 公共包含页
│               ├── nopermission.html                 # 无权限提示页
│               ├── customer/                         # 客户管理页面
│               ├── department/                       # 部门管理页面
│               ├── employee/                         # 员工管理页面
│               ├── history/                          # 历史记录页面
│               ├── permission/                       # 权限管理页面
│               └── role/                             # 角色管理页面
├── target/                                           # 编译输出目录
├── pom.xml                                           # Maven 项目配置
└── README.md                                         # 项目说明文档
```

## 核心功能模块

### 1. 用户管理
- 用户的增删改查
- 用户登录认证
- 用户角色分配

### 2. 角色管理
- 角色的增删改查
- 角色权限分配
- 角色与用户的关联

### 3. 权限管理
- 权限的增删改查
- 权限树形结构展示
- 动态权限控制

### 4. 部门管理
- 部门的增删改查
- 部门层级结构管理

### 5. 员工管理
- 员工信息管理
- 员工与部门的关联
- 员工角色分配

### 6. 客户管理
- 客户信息管理
- 客户池管理
- 潜在客户管理
- 正式客户管理
- 流失客户管理
- 失败客户管理
- 客户跟进记录
- 客户转移记录

### 7. 数据可视化
- 使用 ECharts 展示业务数据统计图表

## 配置说明

### 数据库配置

编辑 `src/main/resources/application.yml`:

```yaml
spring:
  datasource:
    druid:
      url: jdbc:mysql://localhost:3306/rbac?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai
      username: root
      password: your_password
      driver-class-name: com.mysql.cj.jdbc.Driver
```

### 服务器端口

```yaml
server:
  port: 9090
```

## 安装运行

### 环境要求
- JDK 1.8+
- Maven 3.x+
- MySQL 5.7+
- Redis (可选)

### 步骤

1. **克隆项目**
   ```bash
   git clone https://github.com/shuzi030/rbac.git
   cd rbac
   ```

2. **创建数据库**
   ```sql
   CREATE DATABASE rbac CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
   ```

3. **导入数据库脚本**
   执行项目中的 SQL 脚本文件，初始化数据库表结构和基础数据。

4. **修改配置**
   根据实际环境修改 `application.yml` 中的数据库连接配置。

5. **编译项目**
   ```bash
   mvn clean install
   ```

6. **运行项目**
   ```bash
   mvn spring-boot:run
   ```
   或者直接运行 `RbacApplication.java` 主类。

7. **访问系统**
   浏览器访问：`http://localhost:9090/login.html`

## Shiro 安全配置

系统使用 Apache Shiro 实现安全认证和授权：

- **认证（Authentication）**：验证用户身份
- **授权（Authorization）**：控制用户访问权限
- **会话管理（Session Management）**：管理用户会话
- **加密（Cryptography）**：密码加密存储

### 过滤器配置

- `anon`：匿名访问，无需认证
- `authc`：需要认证后才能访问
- `perms`：需要特定权限才能访问

## MyBatis 配置

- 启用驼峰命名自动映射（`map-underscore-to-camel-case: true`）
- 启用懒加载（`lazy-loading-enabled: true`）
- 日志输出（`log-impl: org.apache.ibatis.logging.stdout.StdOutImpl`）

## 分页功能

使用 PageHelper 插件实现分页功能，配置如下：

```yaml
pagehelper:
  helper-dialect: mysql
  reasonable: true
  page-size-zero: true
```

## 项目特点

1. **标准的三层架构**：Controller → Service → Mapper，职责清晰
2. **完善的权限控制**：基于 RBAC 模型，实现细粒度的权限管理
3. **前后端集成**：使用 Thymeleaf 模板引擎，实现服务端渲染
4. **响应式设计**：基于 Bootstrap 框架，支持多设备访问
5. **数据可视化**：集成 ECharts，展示业务数据分析
6. **代码简洁**：使用 Lombok 减少样板代码
7. **高性能**：使用 Druid 连接池和 Redis 缓存

## 开发说明

### 代码规范
- 遵循阿里巴巴 Java 开发手册
- 使用 Lombok 注解简化实体类
- Service 层使用接口 + 实现类的方式
- Controller 层统一异常处理

### 数据库规范
- 表名使用下划线命名法
- 主键统一使用 `id`
- 创建时间字段：`create_time`
- 更新时间字段：`update_time`

## 注意事项

1. `templates` 文件夹下的资源是受保护的，无法通过浏览器直接访问
2. 静态资源（CSS、JS、图片）放在 `static` 目录下
3. 生产环境请修改数据库密码和 Shiro 加密盐值
4. 建议开启 HTTPS 加密传输

## 许可证

本项目仅供学习交流使用。

## 联系方式

- 项目地址：https://github.com/shuzi030/rbac
- 问题反馈：通过 GitHub Issues 提交

---

*最后更新：2025年12月24日*
