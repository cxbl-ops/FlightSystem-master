# Docker image for springboot application
# VERSION 0.0.1
# Author: chen

### 基础镜像; 直接基于java8运行
FROM openjdk

#作者
#MAINTAINER chen <1254181605@qq.com>

#系统编码
ENV LANG=C.UTF-8 LC_ALL=C.UTF-8

#设置时区
ENV TZ=Asia/Shanghai
RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

#声明一个挂载点，容器内此路径会对应宿主机的某个文件夹
VOLUME /tmp

#应用构建成功后的jar文件被复制到镜像内，名字也改成了app.jar
ADD target/Plane-0.0.1-SNAPSHOT.jar app.jar

#暴露8080端口；5005为远程调试端口，根据需要选择是否暴露
EXPOSE 8080 5005

#启动容器时的进程
#ENTRYPOINT ["java","-jar","/app.jar"]

#远程调试
ENTRYPOINT ["java","-jar","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005","app.jar"]

