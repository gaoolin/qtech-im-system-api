#FROM maven:3.6-jdk-8 AS builder
FROM registry.cn-shanghai.aliyuncs.com/qtech/maven:3.6-jdk-8 AS builder

WORKDIR /usr/src/app
COPY ./pom.xml /usr/src/app/pom.xml
COPY ./settings.xml /usr/src/app/settings.xml
RUN mvn dependency:go-offline -s /usr/src/app/settings.xml
COPY ./src /usr/src/app/src
RUN mvn -f /usr/src/app/pom.xml clean package -DskipTests  -s /usr/src/app/settings.xml dependency:resolve


FROM registry.cn-shanghai.aliyuncs.com/kolenz/openjdk:8-jre-alpine-font
LABEL maintainer=gaoolin@gmail.com

# 时区设置
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo 'Asia/Shanghai' >/etc/timezone

# 基于Alpine的镜像 此类镜像中并没有包含tzdata，能所以只设置环境变量并不达到我们想要的效果，因此需要安装tzdata
#ENV TZ=Asia/Shanghai
#RUN apk update \
#    && apk add tzdata \
#    && echo "${TZ}" > /etc/timezone \
#    && ln -sf /usr/share/zoneinfo/${TZ} /etc/localtime \
#    && rm /var/cache/apk/*
# 查看调用栈
# java.lang.NullPointerException: null
# 	at sun.awt.FontConfiguration.getVersion(FontConfiguration.java:1264)
# 导出Excel应该是用到了AWT包中的字体相关到服务，这个需要Linux操作系统中存在有FontConfig控件。docker打包镜像默认是没有该字体；或者因为使用了openjdk而没有使用oraclejdk，缺少依赖
#RUN apk --update add curl bash ttf-dejavu fontconfig \
#    && rm -rf /var/cache/apk/*

# 亦可尝试以下方式（未验证）
# RUN yum -y install fontconfig
# 生成字体缓存文件：
# fc-cache
# 强制重建所有字体缓存文件，而不检查缓存是否为最新版本：
# fc-cache -f
# 删除字体缓存文件，然后生成新的字体缓存文件：
# fc-cache -r

# RUN fc-cache --forc

ENV PARAMS="--server.port=8080 --spring.profiles.active=prod"

EXPOSE 8080

COPY --from=builder /usr/src/app/target/*.jar /home/app.jar
COPY --from=builder /usr/src/app/target/libs /home/libs/

WORKDIR /home

# CMD exec java -Dloader.path="/home/libs/" -jar /home/app.jar
# CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]
ENTRYPOINT ["/bin/sh", "-c", "java -Dloader.path=/home/libs/ -Dfile.encoding=utf8 -Djava.security.egd=file:/dev/./urandom -jar /home/app.jar ${PARAMS}"]
