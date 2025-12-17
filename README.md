Spring 道場 Blog API
==

## セットアップ

``` shell
$ docker compose up -d

$ ./gradlew flywayMigrate
$ ./gradlew bootRun
```
## Tips: localstack のコンテナに入りたい時
```shell
$ docker exec -it localstack /bin/bash
```