


```sh
docker exec -it guide-vault sh
export VAULT_TOKEN="00000000-0000-0000-0000-000000000000"
export VAULT_ADDR="http://127.0.0.1:8200"

vault kv put secret/gs-vault-config example.username=demouser example.password=demopassword
vault kv put secret/gs-vault-config/cloud example.username=clouduser example.password=cloudpassword

vault kv put secret/vault example.username=demouser example.password=demopassword
vault kv put secret/vault/cloud example.username=clouduser example.password=cloudpassword

export VAULT_NAMESPACE=admin
vault status
vault secrets enable database

vault write database/config/postgresql \
     plugin_name=postgresql-database-plugin \
     connection_url="postgresql://{{username}}:{{password}}@postgres:5432/mydatabase?sslmode=disable" \
     allowed_roles=readonly \
     username="myuser" \
     password="secret" 

vault write database/roles/readonly \
      db_name=postgresql \
      creation_statements="CREATE ROLE \"{{name}}\" WITH LOGIN PASSWORD '{{password}}' VALID UNTIL '{{expiration}}'; \
        GRANT SELECT ON ALL TABLES IN SCHEMA public TO \"{{name}}\";" \
      default_ttl=30 \
      max_ttl=24h

vault read database/creds/readonly


vault list sys/leases/lookup/database/creds/readonly
vault list -format=json sys/leases/lookup/database/creds/readonly 

vault lease renew database/creds/readonly/F7tGb4hZHvOw19iqj3ZDT2iR
vault lease revoke database/creds/readonly/F7tGb4hZHvOw19iqj3ZDT2iR

vault lease revoke -prefix database/creds/readonly

docker compose exec -i \
    postgres \
    psql -d mydatabase -U myuser -c "CREATE ROLE \"ro\" NOINHERIT;"

docker compose exec -i \
    postgres \
    psql -d mydatabase -U myuser -c "GRANT SELECT ON ALL TABLES IN SCHEMA public TO \"ro\";"
    

docker compose exec -i \
    postgres \
    psql -d mydatabase -U myuser -c "SELECT usename, valuntil FROM pg_user;"

docker compose exec -i \
    postgres \
    psql -d mydatabase -U myuser -c "CREATE ROLE staticuser_pg WITH LOGIN PASSWORD 'staticpassrootless';"

docker compose exec -i \
    postgres \
    psql -d mydatabase -U myuser -c "select * from pg_roles;"

docker compose exec -i \
    postgres \
    psql -d mydatabase -U myuser -c "CREATE TABLE cities (name varchar(80),location point);"


docker compose exec -i \
    postgres \
    psql -d mydatabase -U myuser -c "GRANT SELECT ON cities TO \"staticuser_pg\";"

docker compose exec -i \
    postgres \
    psql -d mydatabase -U myuser -c "SELECT grantee, table_name, privilege_type FROM information_schema.role_table_grants WHERE grantee = 'staticuser_pg';"


# only enterprise
# error creating database object: error initializing static account cache: self-managed static roles only available in Vault Enterprise
vault write database/config/postgres-db-rootless \
   plugin_name=postgresql-database-plugin \
   allowed_roles=staticuser \
   connection_url="postgresql://{{username}}:{{password}}@postgres:5432/mydatabase?sslmode=disable" \
   verify_connection=false \
   self_managed=true

vault write database/static-roles/staticuser \
   db_name=postgres-db-rootless \
   username="staticuser_pg" \
   self_managed_password="staticpassrootless" \
   rotation_period=5m

vault read database/static-creds/staticuser



sudo gpg -k
sudo gpg --no-default-keyring --keyring /usr/share/keyrings/k6-archive-keyring.gpg --keyserver hkp://keyserver.ubuntu.com:80 --recv-keys C5AD17C747E3415A3642D57D77C6C491D6AC1D69
echo "deb [signed-by=/usr/share/keyrings/k6-archive-keyring.gpg] https://dl.k6.io/deb stable main" | sudo tee /etc/apt/sources.list.d/k6.list
sudo apt-get update
sudo apt-get install k6
```
