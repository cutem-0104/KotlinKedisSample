# Kedis Sample

## ライブラリ

- https://github.com/KDatabases/Kedis
- https://github.com/square/moshi

## requirement

- docker

## 事前準備

下記コマンドでredisサーバーを起動させる

```bash
docker run --name some-redis -d -p 6379:6379 redis redis-server ¥
--appendonly yes --requirepass redis

docker ps
```