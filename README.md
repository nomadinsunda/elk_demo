## PostgreSQL용 데비지움(Debezium) 커넥터 등록 커맨드 라인
터미널(Git Bash)에 아래 커맨드 라인를 복사해서 붙여넣고 **딱 한번**  실행시켜야 합니다. <br>
docker-compose.yml 설정(ID: dev_user, DB: board_db)에 딱 맞춰 수정

```
curl -i -X POST -H "Accept:application/json" -H "Content-Type:application/json" \
localhost:8083/connectors/ -d '{
"name": "board-connector",
"config": {
"connector.class": "io.debezium.connector.postgresql.PostgresConnector",
"database.hostname": "postgres-db",
"database.port": "5432",
"database.user": "dev_user",
"database.password": "dev_password",
"database.dbname": "board_db",
"database.server.name": "asgard",
"table.include.list": "public.posts",
"plugin.name": "pgoutput",
"topic.prefix": "cdc"
}
}'
```
*(참고: `public.posts` 부분은 실제 감시하고 싶은 테이블 명으로 수정하세요!)*

---

### 2. 등록 확인 루틴 🔍

등록 커맨드 라인을 실행한 후, 다시 한번 확인해 보세요.

1.  **커넥터 목록 확인:**
    `curl localhost:8083/connectors` -> 결과로 `["board-connector"]`가 나와야 합니다.
2.  **커넥터 상태 확인:**
    `curl localhost:8083/connectors/board-connector/status` -> `"state": "RUNNING"` 인지 확인하세요.

---

### 🚀 다음 액션 플랜

1.  위의 **`curl` 명령어를 실행**하여 커넥터를 등록합니다.
2.  **데이터 발생:** `board-service`를 통해 게시글을 하나 작성하거나, DB에 직접 `INSERT` 쿼리를 날리세요. (중요: 변화가 있어야 로그가 생깁니다!)
3.  **키바나 재접속:** `localhost:5601`의 `Index Management` 화면에서 **`Reload indices`** 버튼을 눌러보세요.

**이제 `cdc.public.posts`와 같은 이름의 인덱스가 짠! 하고 나타날 겁니다.