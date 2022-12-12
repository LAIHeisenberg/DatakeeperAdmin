#!/usr/bin/bash
docker_dir=`pwd`
container_name='datakeeper-admin'
echo '打包前端代码: '
cd $docker_dir/../src/main/resources/datakeeper-web-root/webapp
npm config set registry https://registry.npm.taobao.org
npm install
npm run build:prod
echo '打包后端代码: '
cd $docker_dir/../../
mvn clean package

cp $docker_dir/../target/datakeeper-admin-web-1.0.0-SNAPSHOT-exec.jar  $docker_dir
echo '设置DOCKER_HOST=tcp://192.168.0.161:2375'
export DOCKER_HOST="tcp://192.168.0.161:2375"

echo '停止容器: datakeeper-admin'
container_id=`docker container ps -a | grep $container_name | tr -s ' ' | cut -d ' ' -f 1`
docker container stop $container_id
docker container rm $container_id
echo '执行docker build: '
docker build --no-cache -f $docker_dir/Dockerfile -t $container_name $docker_dir

rm -f $docker_dir/datakeeper-admin-web-1.0.0-SNAPSHOT-exec.jar

echo '执行docker run: '
docker run -d -p 8070:8070 --name $container_name -v /data/sqlite:/data/sqlite --link cipher_redis:cipher_redis --link cipher_mysql:cipher_mysql -d $container_name
