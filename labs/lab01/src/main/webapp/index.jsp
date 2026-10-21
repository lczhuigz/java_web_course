<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>lab01</title>
    <style>
        * { box-sizing: border-box; }

        body {
            margin: 0;
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 24px;
            font-family: "Segoe UI", "PingFang SC", "Microsoft YaHei", system-ui, sans-serif;
            color: #1e293b;
            background:
                radial-gradient(1200px 600px at 10% -10%, #e0e7ff 0%, transparent 60%),
                radial-gradient(1000px 500px at 110% 110%, #cffafe 0%, transparent 55%),
                #f8fafc;
        }

        .card {
            width: 100%;
            max-width: 420px;
            padding: 40px 32px 32px;
            text-align: center;
            background: #fff;
            border-radius: 18px;
            border: 1px solid rgba(148, 163, 184, .18);
            box-shadow: 0 24px 50px -24px rgba(15, 23, 42, .35);
        }

        .brand {
            display: inline-flex;
            align-items: center;
            gap: 8px;
            font-size: 12px;
            font-weight: 600;
            letter-spacing: .08em;
            text-transform: uppercase;
            color: #4f46e5;
            background: #eef2ff;
            padding: 5px 10px;
            border-radius: 999px;
            margin-bottom: 18px;
        }

        .brand::before {
            content: "";
            width: 7px;
            height: 7px;
            border-radius: 50%;
            background: #4f46e5;
        }

        h1 { margin: 0 0 8px; font-size: 26px; }

        .subtitle { margin: 0 0 28px; font-size: 14px; color: #64748b; }

        .actions { display: grid; gap: 12px; }

        .btn {
            display: block;
            padding: 13px 16px;
            font-size: 15px;
            font-weight: 600;
            text-decoration: none;
            border-radius: 12px;
            transition: transform .15s ease, box-shadow .15s ease, filter .15s ease, background .15s ease;
        }

        .btn-primary {
            color: #fff;
            background: linear-gradient(135deg, #6366f1, #4f46e5);
            box-shadow: 0 10px 20px -10px rgba(79, 70, 229, .8);
        }

        .btn-primary:hover {
            transform: translateY(-1px);
            filter: brightness(1.05);
            box-shadow: 0 14px 26px -12px rgba(79, 70, 229, .9);
        }

        .btn-ghost {
            color: #0f766e;
            background: #f0fdfa;
            border: 1px solid #99f6e4;
        }

        .btn-ghost:hover { background: #ccfbf1; transform: translateY(-1px); }
    </style>
</head>
<body>
<main class="card">
    <span class="brand">lab01</span>
    <h1>Hello World!</h1>
    <p class="subtitle">请选择要进行的操作</p>
    <div class="actions">
        <a class="btn btn-primary" href="login.html">用户登录</a>
        <a class="btn btn-ghost" href="register.html">用户注册</a>
    </div>
</main>
</body>
</html>
