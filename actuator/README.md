# Actuator


## Scripts

```sh

curl localhost:8080/log


curl -i -X POST -H 'Content-Type: application/json' -d '{"configuredLevel":"INFO","effectiveLevel":"INFO"}' http://localhost:8080/actuator/loggers/root
curl -i -X POST -H 'Content-Type: application/json' -d '{"configuredLevel":"DEBUG","effectiveLevel":"DEBUG"}' http://localhost:8080/actuator/loggers/org.jyasu

```