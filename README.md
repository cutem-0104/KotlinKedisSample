# Kedis Sample

## ライブラリ

- https://github.com/KDatabases/Kedis
- https://github.com/square/moshi
- https://github.com/gwhalin/Memcached-Java-Client
- https://dev.mysql.com/downloads/connector/j/5.1.html

## requirement

- docker

## 事前準備

キャッシュサーバーの起動
```
docker-compose build --no-cache
docker-compose up -d
```

## mysqlの構成 


```mysql構成
dir
├ docker-compose.yml
└ mysql/
    ├ Dockerfile
    ├ my.cnf
    └ sqls/
        └ initialize.sql
```