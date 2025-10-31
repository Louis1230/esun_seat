# 員工座位安排系統

本專案為人資部門使用的員工座位安排系統，提供座位顯示、員工指派與清除座位等功能。

---

## 系統需求

- Java 17+
- Node.js 20+ / npm
- MySQL 8+

---

## 初始化步驟

### 1️⃣ 匯入資料庫

請依序在 MySQL 執行以下指令：

```sql
SOURCE DB/ddl/schema.sql;
SOURCE DB/dml/seatSql.sql;
SOURCE DB/procedures/procedures.sql;


2️⃣ 設定後端連線

修改 src/main/resources/application.properties：

spring.datasource.url=jdbc:mysql://localhost:3306/seatdb?useSSL=false&serverTimezone=Asia/Taipei&allowPublicKeyRetrieval=true
spring.datasource.username=springuser
spring.datasource.password=123456
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true

3️⃣ 啟動後端
./gradlew bootRun


後端服務啟動於
👉 http://localhost:8080

4️⃣ 啟動前端
cd frontend
npm install
npm run dev


前端服務啟動於
👉 http://localhost:5173

開啟瀏覽器進入上述網址即可使用系統。
若開啟 http://localhost:8080/ 顯示 Whitelabel 404 為正常現象（後端僅提供 API 服務）。

資料庫說明
📁 DB/ddl/schema.sql

建立資料表：

SeatingChart：座位資訊

Employee：員工資料

📁 DB/dml/seatSql.sql

插入範例資料：

每層 4 個座位 × 共 4 層

共 6 位員工

📁 DB/procedures/procedures.sql

建立 Stored Procedure：

sp_getSeatsByFloor(p_floor INT)：取得樓層座位資訊

sp_assignSeat(p_empId CHAR(5), p_seatSeq INT)：指派座位（含 Transaction）

sp_clearSeat(p_seatSeq INT)：清除座位

API 說明
Method	  Path                  功能	                     請求體 / 參數	            回應格式
GET	      /api/seats	          取得全部座位 (含員工資訊)	   —	                      SeatingChart[]
GET	      /api/seats/employees	取得全部員工	               —	                      Employee[]
POST	    /api/seats/assign	    指派座位 (員工 → 座位)      { empId, floorSeatSeq }  { success: true/false }
POST	    /api/seats/clear	    清空座位                   { floorSeatSeq }	        { success: true/false }

後端對應：SeatController → SeatService
使用 JdbcTemplate 呼叫 Stored Procedure。

注意事項

若前端出現 CORS 錯誤，請確認後端 GlobalCorsConfig 或 @CrossOrigin 設定。

若開啟 http://localhost:8080 顯示 Whitelabel Page 屬正常現象。

若 MySQL 無法連線，請確認帳號、密碼與 port 設定是否正確。


🧑‍💻 作者：Louis
📅 建置日期：2025/10/31
📦 專案類型：Spring Boot + Vue.js 三層式架構應用
