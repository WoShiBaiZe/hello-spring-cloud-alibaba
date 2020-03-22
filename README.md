# <center>hello-spring-cloud-alibaba</center>

## 1. 下载 Nacos 及 Sentinel
### Centos7 安装 Docker 及 Docker-Compose
``` Linux
yum install -y docker
vi /etc/docker/daemon.json
:set paste
{
  "registry-mirrors": [
    "https://registry.docker-cn.com"
  ]
}
systemctl daemon-reload
systemctl restart docker 
systemctl enable docker
curl -L https://github.com/docker/compose/releases/download/1.24.0/docker-compose-`uname -s`-`uname -m` > /usr/local/bin/docker-compose
chmod +x /usr/local/bin/docker-compose



```
### Ubuntu 安装 Docker 及 Docker-Compose
``` Linux
#!/bin/bash
#############################
#	1.安装Dokcer			#
#	2.安装DockerCompose		#
#############################
echo "docker安装"
apt-get remove docker docker-engine docker.io containerd runc
apt-get update
apt-get -y install apt-transport-https ca-certificates curl software-properties-common
curl -fsSL http://mirrors.aliyun.com/docker-ce/linux/ubuntu/gpg | sudo apt-key add -
add-apt-repository "deb [arch=amd64] http://mirrors.aliyun.com/docker-ce/linux/ubuntu $(lsb_release -cs) stable"
apt-get update && apt-get install -y docker-ce
docker version
tee /etc/docker/daemon.json <<-'EOF'
{
  "registry-mirrors": [
    "https://registry.docker-cn.com"
  ]
}
EOF
sudo systemctl daemon-reload
sudo systemctl restart docker
docker info
echo -e "\n"
echo "docker compose安装"
curl -L https://github.com/docker/compose/releases/download/1.24.0/docker-compose-`uname -s`-`uname -m` > /usr/local/bin/docker-compose
chmod +x /usr/local/bin/docker-compose
docker-compose version
```

## 2. 安装 Nacos
### Clone项目
```
git clone https://github.com/nacos-group/nacos-docker.git
cd nacos-docker
```
### 单机模式
```
docker-compose -f example/standalone-mysql.yaml up -d
```
### 查看日志
```
docker-compose -f example/standalone-mysql.yaml logs -f
```
### Nacos 控制台
http://192.168.141.132:8848/nacos

### 登录

![](https://note.youdao.com/yws/public/resource/72f95635f8363518385d47482029c09d/xmlnote/FD767F6C780B4817BEF4287A75C68A51/5053)

- **账号：** nacos
- **密码：** nacos

### 访问

![](https://note.youdao.com/yws/public/resource/72f95635f8363518385d47482029c09d/xmlnote/64D38CD88A9A451C95CBF25B97D57F1B/5055)

### 安装 Sentinel
[官方 GitHub Release 页面](https://github.com/alibaba/Sentinel/releases)

#### 启动
**注意：** 启动 Sentinel 控制台需要 JDK 版本为 1.8 及以上版本
```
java -Dserver.port=8080 -Dcsp.sentinel.dashboard.server=localhost:8080 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard.jar
```
其中 `-Dserver.port=8080` 用于指定 Sentinel 控制台端口为 `8080`<br/>
**注意：** 从 Sentinel 1.6.0 起，Sentinel 控制台引入基本的 **登录** 功能，**默认用户名和密码都是 sentinel**

### 验证安装是否成功

通过浏览器访问 [http://localhost:8080/#/login](http://localhost:8080/#/login)

![](https://note.youdao.com/yws/public/resource/72f95635f8363518385d47482029c09d/xmlnote/3BBAE7AB785742E49C94EB19542C8850/5077)

- **账号：** sentinel
- **密码：** sentinel

![](https://note.youdao.com/yws/public/resource/72f95635f8363518385d47482029c09d/xmlnote/AC4D7C8026C6411ABD70F778A1808685/5079)

详细说明请参考 [官方 Sentinel Wiki](https://github.com/alibaba/Sentinel/wiki)

### 3.代码实现下图
![](https://note.youdao.com/yws/public/resource/72f95635f8363518385d47482029c09d/xmlnote/369960AADEA24BF6A9F0D0823EA3562E/5088)

## 微服务架构中的四个问题：
### 1.众多服务间，如何访问？
### 2.服务间如何通信?
### 3.服务间如何管理?
### 4.单点故障可能导致雪崩，如何解决？