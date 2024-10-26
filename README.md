# All Integration

## init

```sh


spring init openai --build=maven --java-version=17  \
--name openai --packaging jar  \
--description 'openai application'  --artifact-id openai \
--boot-version 3.2.2  --dependencies=web,lombok,docker-compose,spring-ai-openai,spring-ai-vectordb-elasticsearch,spring-ai-vectordb-cassandra,spring-ai-vectordb-redis,spring-ai-vectordb-mongodb-atlas,spring-ai-vectordb-pgvector  \
--group-id org.jyasu --extract --force

spring init ollama --build=maven --java-version=17  \
--name ollama --packaging jar  \
--description 'ollama application'  --artifact-id ollama \
--boot-version 3.2.2  --dependencies=web,lombok,docker-compose,spring-ai-ollama  \
--group-id org.jyasu --extract --force


spring init postgresql --build=maven --java-version=17  \
--name postgresql --packaging jar  \
--description 'postgresql application'  --artifact-id postgresql \
--boot-version 3.2.2  --dependencies=web,lombok,docker-compose,postgresql,data-jpa,actuator  \
--group-id org.jyasu --extract --force



spring init cassandra --build=maven --java-version=17  \
--name cassandra --packaging jar  \
--description 'cassandra application'  --artifact-id cassandra \
--boot-version 3.2.2  --dependencies=web,lombok,docker-compose,data-cassandra,data-cassandra-reactive  \
--group-id org.jyasu --extract --force



spring init mongodb --build=maven --java-version=17  \
--name mongodb --packaging jar  \
--description 'mongodb application'  --artifact-id mongodb \
--boot-version 3.2.2  --dependencies=web,lombok,docker-compose,data-mongodb,data-mongodb-reactive,graphql  \
--group-id org.jyasu --extract --force


spring init elasticsearch --build=maven --java-version=17  \
--name elasticsearch --packaging jar  \
--description 'elasticsearch application'  --artifact-id elasticsearch \
--boot-version 3.2.2  --dependencies=web,lombok,docker-compose,data-elasticsearch  \
--group-id org.jyasu --extract --force


spring init redis --build=maven --java-version=17  \
--name redis --packaging jar  \
--description 'redis application'  --artifact-id redis \
--boot-version 3.2.2  --dependencies=web,lombok,docker-compose,data-redis,data-redis-reactive  \
--group-id org.jyasu --extract --force


spring init kafka --build=maven --java-version=17  \
--name kafka --packaging jar  \
--description 'kafka application'  --artifact-id kafka \
--boot-version 3.2.2  --dependencies=web,lombok,docker-compose,kafka,kafka-streams  \
--group-id org.jyasu --extract --force

spring init amqp --build=maven --java-version=17  \
--name amqp --packaging jar  \
--description 'amqp application'  --artifact-id amqp \
--boot-version 3.2.2  --dependencies=web,lombok,docker-compose,amqp-streams,amqp  \
--group-id org.jyasu --extract --force


spring init vault --build=maven --java-version=17  \
--name vault --packaging jar  \
--description 'vault application'  --artifact-id vault \
--boot-version 3.2.2  --dependencies=web,lombok,docker-compose,cloud-starter-vault-config  \
--group-id org.jyasu --extract --force

spring init shell --build=maven --java-version=17  \
--name shell --packaging jar  \
--description 'shell application'  --artifact-id shell \
--boot-version 3.2.2  --dependencies=lombok,docker-compose,native,spring-shell  \
--group-id org.jyasu --extract --force


```