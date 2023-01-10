#!/usr/bin/bash

docker_dir=`pwd`
echo '打包后端代码'
cd $docker_dir/../../
mvn clean package
cp $docker_dir/../target/datakeeper-rest-service-1.0.0-SNAPSHOT-exec.jar $docker_dir/
container_name='datakeeper-rest-service'
#echo '设置DOCKER_HOST=tcp://192.168.0.161:2375'
#export DOCKER_HOST="tcp://192.168.0.161:2375"
echo '停止容器: datakeeper-rest-service'
container_id=`docker container ps -a | grep $container_name | tr -s ' ' | cut -d ' ' -f 1`
docker container stop $container_id
echo '移除容器: '
docker container rm $container_id
echo '执行docker build: '
docker build --no-cache -f $docker_dir/Dockerfile -t $container_name  $docker_dir
rm -f $docker_dir/datakeeper-rest-service-1.0.0-SNAPSHOT-exec.jar
echo '执行docker run: '
docker run -d -p 8060:8060 --name $container_name -v /data/sqlite:/data/sqlite -d $container_name
