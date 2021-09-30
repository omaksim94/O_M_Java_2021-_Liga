## Команда для запуска контейнера на Postgres:alpine

**Docker run –-name postgresdb –e POSTGRES_PASSWORD=password –d –p 5432:5432 -e TZ=Europe/Moscow postgres:alpine**

* ***--name postgresdb*** - указание имени создаваемого контейнера как postgresdb
* ***-e POSTGRES_PASSWORD=password*** - задание обязательной переменной окружения пароль для суперпользователя
* ***-d*** - для запуска контейнера в фоне
* ***-p 5432:5432*** - указание какие порты необходимо открыть
* ***-e TZ=Europe/Moscow*** - указание часового пояса для контейнера
* ***postgres:alpine*** - указание образа на основе, которого будет создан контейнер