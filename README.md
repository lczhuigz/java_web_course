# JavaWeb 课程实验项目

湖北文理学院理工学院《JavaWeb 程序开发》课程的学习与实践仓库，包含课程实验代码、实验报告，以及教材配套的示例源码。

- 作者：[@lczhuigz](https://github.com/lczhuigz)
- 仓库：[Gitee](https://gitee.com/lczhuigz/java_web_course) · [GitHub](https://github.com/lczhuigz/java_web_course) · [GitCode](https://gitcode.com/lczhuigz/java_web_course)

## 项目简介

仓库按「实验」与「课程资料」两部分组织：

- `labs/` —— 课程实验，从 Servlet 入门、表单处理，到 Session 与 Cookie 的登录状态保持，每个实验都是独立的 Maven Web 工程，可直接打包成 WAR 部署到 Tomcat。
- `labs/lab_report/` —— 实验报告，包含实验要求、空白模板与已完成的报告。
- `course_resources/` —— 教材各章配套源码（黑马程序员《JavaWeb 程序开发》），按章节组织，用于课后对照学习，不参与 `labs` 的构建。

## 技术栈

| 分类 | 技术 / 版本 |
| --- | --- |
| 开发语言 | Java（`labs` 编译级别 17 / 21） |
| Web 规范 | Jakarta Servlet 6.1（Jakarta EE 11） |
| Web 容器 | Apache Tomcat 11.x |
| 构建工具 | Maven 3.9+（WAR 打包） |
| 前端 | 原生 HTML / CSS / JavaScript；课程示例含 Vue 3 + Element Plus |
| 数据访问（课程示例） | JDBC、Druid、DbUtils、MyBatis |
| 其他（课程示例） | Jackson、JWT、Vue CLI |

## 目录结构

```
java_web_course/
├── labs/                          # 课程实验（独立 Maven Web 工程）
│   ├── lab01/                     # 实验：Servlet 入门 —— 用户注册与登录
│   ├── lab02/                     # 实验：Servlet 开发 —— 表单处理与页面输出
│   ├── lab03/                     # 实验：Session 与 Cookie —— 登录状态保持
│   └── lab_report/                # 实验报告
│       ├── report_template/       #   空白报告模板
│       ├── report_requirements/   #   实验一 ~ 实验三 的要求
│       └── finished_report/       #   已完成的实验报告
├── course_resources/              # 课程配套资料（教材示例源码）
│   ├── source_code/               #   教材各章示例（chapter01 ~ chapter11）
│   └── ai_programming_task_source_code/  # AI 编程任务源码
├── license                        # MIT 许可证
├── .gitignore
└── README.md
```

> `target/`、`node_modules/` 等构建产物已被 `.gitignore` 忽略，克隆仓库后需要自行构建。

## 环境准备

1. **JDK 21**（`labs/lab01`、`labs/lab03` 编译级别为 21；`labs/lab02` 为 17，可在 JDK 21 上正常编译运行）
2. **Maven 3.9+**
3. **Apache Tomcat 11.x**

> 三个实验的 `web.xml` 使用 Servlet 6.1 规范，需要 Tomcat 11。若坚持使用 Tomcat 10.1（仅支持到 Servlet 6.0），可把各实验 `web.xml` 的 `version` 改为 `6.0`、schema 改为 `web-app_6_0.xsd`，并将 `pom.xml` 中的 `jakarta.servlet-api` 版本改为 `6.0.0`。

## 快速开始

### 方式一：IntelliJ IDEA（推荐）

1. 打开项目根目录，把 `labs/lab01`、`labs/lab02`、`labs/lab03` 分别作为 Maven 工程导入（三个实验相互独立，没有父 POM）。
2. `Run` → `Edit Configurations` → `+` → `Tomcat Server` → `Local`，Application server 选择本机 Tomcat 11。
3. 在 `Deployment` 页添加对应实验的 `war exploded` 构件，Application context 填写与模块同名的路径（如 `/lab01`）。
4. 启动 Tomcat，浏览器访问下表的地址。

### 方式二：Maven 命令行 + 手动部署

```bash
cd labs/lab01
mvn clean package

# 把 target/lab01.war 复制到 Tomcat 的 webapps 目录后启动 Tomcat
# Windows PowerShell：
Copy-Item .\target\lab01.war $env:CATALINA_HOME\webapps\
```

其他实验同理，只需把 `lab01` 换成 `lab02` / `lab03`。WAR 包名即部署后的访问路径。

### 各实验访问地址

| 实验 | 构建产物 | 访问地址 | 测试账号 |
| --- | --- | --- | --- |
| lab01 | `target/lab01.war` | <http://localhost:8080/lab01/> | 自行注册后登录 |
| lab02 | `target/lab02.war` | <http://localhost:8080/lab02/> | `admin` / `123456` |
| lab03 | `target/lab03.war` | <http://localhost:8080/lab03/> | `admin` / `123456` |

## 实验说明

### lab01：用户注册与登录

练习 Servlet 注解映射、请求参数接收、`ServletContext` 共享数据与页面跳转。

- 首页提供「用户登录」「用户注册」两个入口，页面为静态 HTML。
- 注册：`RegisterServlet`（`/register`）校验用户名非空且不重复，把用户存入 `ServletContext` 中的 `ConcurrentHashMap`，随后重定向回登录页并提示注册成功。
- 登录：`LoginServlet`（`/login`）从 `ServletContext` 取出用户表，校验用户名与密码后输出结果页。
- 两个 Servlet 都重写了 `doGet`，直接访问 `/login`、`/register` 时会重定向回对应页面，避免 405。
- 用户数据仅保存在内存中，重启 Tomcat 后会清空。

### lab02：Servlet 开发

练习用 Servlet 动态输出页面、处理表单 POST 请求。

- `/loginView`、`/regView` 由 Servlet 直接输出登录页和注册页 HTML。
- `/login` 校验固定账号 `admin` / `123456`，输出登录成功或失败结果。
- `/register` 接收用户名与密码，在服务器控制台打印，并提示注册成功。
- 页面入口是 `index.jsp`；`src/main/webapp/login.html` 为早期静态页面，表单地址仍指向 `chapter04`，仅作留档，请从 `index.jsp` 进入。

### lab03：Session 与 Cookie

练习会话跟踪、Cookie 与过滤器的组合使用。

- 登录：`LoginServlet`（`/login`）校验固定账号 `admin` / `123456`，成功后写入 Session（30 分钟不活动失效）。
- 保持登录：勾选「两周不用登录」时写入 `loginUser`、`loginPwd` 两个 Cookie（有效期 14 天、路径覆盖整个应用），下次访问由 `AuthFilter` 读取 Cookie 自动重建会话。
- 访问控制：`AuthFilter` 拦截 `/login.html`、`/welcome.html`、`/info.html`——未登录访问受保护页面会跳回登录页；已登录访问登录页会直接进入主页。
- 个人信息：`UserInfoServlet`（`/userinfo`）返回 JSON，包含用户名、Session ID、创建时间、最后访问时间与最大不活动间隔，主页和个人信息页通过 `fetch` 渲染。
- 退出：`/logout` 销毁 Session 并把两个 Cookie 的 `MaxAge` 置 0 完成清除。
- 仅为教学演示：密码以明文保存在 Cookie 中，实际项目请勿这样实现。

## 实验报告

| 目录 | 说明 |
| --- | --- |
| `labs/lab_report/report_template/` | 实验报告空白模板（`实验报告模板.docx`） |
| `labs/lab_report/report_requirements/` | 实验一 ~ 实验三 的实验要求 |
| `labs/lab_report/finished_report/` | 已完成的实验报告（实验一 ~ 实验三） |

## 课程示例源码（course_resources）

教材各章示例，均为独立工程，仅用于学习参考：

| 目录 | 主题 | 主要内容 |
| --- | --- | --- |
| `source_code/chapter01` | HTML / CSS 基础 | 常用标签、表单、CSS 样式、Java 入门示例 |
| `source_code/chapter02_maven` | Maven 入门 | `pom.xml`、依赖管理、WAR 打包 |
| `source_code/chapter03` | Servlet 基础 | 请求与响应、转发与重定向、Servlet 生命周期 |
| `source_code/chapter04` | Cookie 与 Session | Cookie 读写、会话跟踪、存取款示例 |
| `source_code/chapter05` | JSP | EL、JSTL、`include`、异常处理、JavaBean |
| `source_code/chapter06` | Filter 与 Listener | 过滤器链、监听器、文件上传与下载 |
| `source_code/chapter07` | Vue 3 前端 | Vue CLI 工程、组件与路由 |
| `source_code/chapter08` | Ajax 与 JSON | axios 请求、前后端数据交互 |
| `source_code/chapter09` | JDBC | JDBC、Druid 连接池、DbUtils |
| `source_code/chapter10` | 综合项目：衣橱管理 | Servlet + JDBC + JWT 后端，Vue 3 + Element Plus 前后端分离 |
| `source_code/chapter11` | Spring 生态 | Spring IoC、Spring MVC、MyBatis 入门 |
| `ai_programming_task_source_code` | AI 编程任务 | 与教材章节对应的任务源码（`chapter03` ~ `chapter09`，含 Vue 前端） |

补充说明：

- `source_code/chapter10` 是完整的前后端分离项目，后端为 `wardrobe_back`，前端为 `frontend/wardrobe_user`（用户端）和 `frontend/wardrobe_admin`（管理端）；运行前需先导入 `wardrobe.sql`，并按本机数据库修改连接配置。
- 前端工程进入对应目录后执行 `npm install`，再用 `npm run serve` 启动开发服务器，`npm run build` 打包。
- 数据库相关章节需要本地安装 MySQL 并修改 `druid.properties` 等配置文件中的连接信息。
- 这些示例仅用于课程学习，版权归教材作者及出版社所有。

## 常见问题

**访问返回 404？**
确认 Tomcat 已成功部署 WAR，且访问路径与 WAR 包名一致（如 `/lab01`、`/lab02`、`/lab03`），不要漏掉尾部斜杠。

**直接访问 `/login` 返回 405？**
登录接口只接受 POST。`lab01`、`lab02` 已通过 `doGet` 重定向回登录页；如自行改写代码，请注意区分 GET 与 POST。

**8080 端口被占用？**
修改 `conf/server.xml` 中 `Connector` 的 `port`，或先关闭占用端口的进程。

**中文乱码？**
项目统一使用 UTF-8：`pom.xml` 中已设置编码，Servlet 中调用 `request.setCharacterEncoding("UTF-8")`、`response.setContentType("text/html;charset=UTF-8")`。IDEA 的 `File Encodings` 建议设为 UTF-8。

**Tomcat 启动报 `web.xml` 版本错误？**
`web.xml` 声明的是 Servlet 6.1，请使用 Tomcat 11；或按「环境准备」中的说明降级为 6.0。

**IDEA 提示语言级别不匹配？**
`lab01`、`lab03` 需要 JDK 21，`lab02` 需要 JDK 17+；在 `Project Structure` → `Project` 与 `Modules` 中统一 SDK 与 Language level。

## 版权说明

- 本项目实验代码与实验报告采用 [MIT](license) 许可证。
- `course_resources/` 下的教材示例源码版权归原作者与出版社所有，仅用于个人学习，请勿用于商业用途。

## License

MIT © 2026 [lczhuigz](https://github.com/lczhuigz)
