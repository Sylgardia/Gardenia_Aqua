FROM openjdk:8-oracle
MAINTAINER zc

# 在主机 /var/lib/docker目录下创建一个临时文件，并链接到容器的 /tmp
VOLUME /tmp

# 将jar包添加到容器中，并命名为 springboot_docker.jar
ADD docker_boot-1.0-SNAPSHOT.jar /springboot_docker.jar
# 运行jar包
RUN bash -c 'touch /springboot_docker.jar'
ENTRYPOINT ["java", "-jar", "/springboot_docker.jar"]

# SpringBoot项目配置的端口号为6001，需要将6001暴露出去
EXPOSE 9001
