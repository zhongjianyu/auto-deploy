自动部署系统-websocket模块
jsch配置说明：
session.setConfig("userauth.gssapi-with-mic", "no");
session.setConfig("StrictHostKeyChecking", "no");
在路径 /etc/ssh/sshd_config 文件中，PermitRootLogin yes 把这一行通过注释取消掉,也就是允许root远程登录