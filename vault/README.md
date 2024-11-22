


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
      default_ttl=1h \
      max_ttl=24h

vault read database/creds/readonly



docker compose exec -i \
    postgres \
    psql -d mydatabase -U myuser -c "CREATE ROLE \"ro\" NOINHERIT;"

docker compose exec -i \
    postgres \
    psql -d mydatabase -U myuser -c "GRANT SELECT ON ALL TABLES IN SCHEMA public TO \"ro\";"
    

docker compose exec -i \
    postgres \
    psql -d mydatabase -U myuser -c "SELECT usename, valuntil FROM pg_user;"
```
