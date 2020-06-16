# 商品服务

## docker脚本
```docker
docker run -d -p 9092:8080 --rm \
-e JAVA_OPTS='-server -Xmx1g' \
-e PROFILE='default' \
-e SERVER_PORT=8080 \
-e EUREKA_SERVER_HOST=127.0.0.1 \
-e ADMIN_HOST=127.0.0.1 \
-e ADMIN_PORT=9093 \
<<<<<<< HEAD:samples-modules/samples-product/README.md
registry.cn-qingdao.aliyuncs.com/ascrud/product-server:0.2.0
=======
registry.cn-hangzhou.aliyuncs.com/ascrud/product-server:0.2.0
>>>>>>> 2e11de9d18d7ad3bb83a649e4eb7c45daa8958e3:samples-product/README.md
```
