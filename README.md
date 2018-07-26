# Kedis Sample

## ライブラリ

- https://github.com/KDatabases/Kedis
- https://github.com/square/moshi
- https://github.com/gwhalin/Memcached-Java-Client

## requirement

- docker

## 事前準備

下記コマンドでredisサーバーを起動させる

```bash
docker run --name some-redis -d -p 6379:6379 redis redis-server ¥
--appendonly yes --requirepass redis

docker ps
```

memcachedサーバーの起動
```bash
docker run --name some-memcached -d -p 11211:11211 memcached

docker ps
```