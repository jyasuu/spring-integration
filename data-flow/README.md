


```sh
wget -O docker-compose.yml https://raw.githubusercontent.com/spring-cloud/spring-cloud-dataflow/main/src/docker-compose/docker-compose.yml
wget -O docker-compose-rabbitmq.yml https://raw.githubusercontent.com/spring-cloud/spring-cloud-dataflow/main/src/docker-compose/docker-compose-rabbitmq.yml
wget -O docker-compose-kafka.yml https://raw.githubusercontent.com/spring-cloud/spring-cloud-dataflow/main/src/docker-compose/docker-compose-kafka.yml
wget -O docker-compose-postgres.yml https://raw.githubusercontent.com/spring-cloud/spring-cloud-dataflow/main/src/docker-compose/docker-compose-postgres.yml


export DATAFLOW_VERSION=2.11.5
export SKIPPER_VERSION=2.11.5
docker-compose -f docker-compose.yml -f docker-compose-rabbitmq.yml -f docker-compose-kafka.yml -f docker-compose-postgres.yml up -d


docker stop $(docker ps -a -q)
docker rm $(docker ps -a -q)


wget -O spring-cloud-dataflow-shell-2.11.5.jar https://repo.maven.apache.org/maven2/org/springframework/cloud/spring-cloud-dataflow-shell/2.11.5/spring-cloud-dataflow-shell-2.11.5.jar
java -jar spring-cloud-dataflow-shell-2.11.5.jar


```