# 主数据服务

## docker脚本
```docker
docker run -d -p 9095:8080 --rm \
-e JAVA_OPTS='-server -Xmx1g' \
-e PROFILE='default' \
-e SERVER_PORT=8080 \
-e EUREKA_SERVER_HOST=127.0.0.1 \
-e ADMIN_HOST=127.0.0.1 \
-e ADMIN_PORT=9093 \
<<<<<<< HEAD:samples-modules/samples-mdm/README.md
registry.cn-qingdao.aliyuncs.com/ascrud/mdm-server:0.2.0
=======
registry.cn-hangzhou.aliyuncs.com/ascrud/mdm-server:0.2.0
>>>>>>> 2e11de9d18d7ad3bb83a649e4eb7c45daa8958e3:samples-mdm/README.md
```
