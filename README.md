# Kotlin-Board

---

## Database
### Mysql
```declarative
docker run --platform linux/amd64 -p 3306:3306 --name kotlin-board -e MYSQL_USER=user -e MYSQL_PASSWORD=pwd -e MYSQL_ROOT_PASSWORD=pwd -e MYSQL_DATABASE=kotlin_board -d mysql
```
