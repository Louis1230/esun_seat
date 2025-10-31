-- ===============================================
-- 建立資料庫
-- ===============================================
CREATE DATABASE IF NOT EXISTS seatDB
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE seatDB;

-- ===============================================
-- 建立表格
-- ===============================================

CREATE TABLE IF NOT EXISTS SeatingChart (
    FLOOR_SEAT_SEQ INT AUTO_INCREMENT PRIMARY KEY,
    FLOOR_NO INT NOT NULL,
    SEAT_NO VARCHAR(10) NOT NULL
);

CREATE TABLE IF NOT EXISTS Employee (
    EMP_ID CHAR(5) PRIMARY KEY,
    NAME VARCHAR(50) NOT NULL,
    EMAIL VARCHAR(100) NOT NULL,
    FLOOR_SEAT_SEQ INT,
    FOREIGN KEY (FLOOR_SEAT_SEQ) REFERENCES SeatingChart(FLOOR_SEAT_SEQ)
);

-- ===============================================
-- 插入座位資料（每層 4 個座位，共 4 層）
-- ===============================================
INSERT INTO SeatingChart (FLOOR_NO, SEAT_NO) VALUES
(1, '座位1'), (1, '座位2'), (1, '座位3'), (1, '座位4'),
(2, '座位1'), (2, '座位2'), (2, '座位3'), (2, '座位4'),
(3, '座位1'), (3, '座位2'), (3, '座位3'), (3, '座位4'),
(4, '座位1'), (4, '座位2'), (4, '座位3'), (4, '座位4');

-- ===============================================
-- 插入員工資料並指定座位

INSERT INTO Employee (EMP_ID, NAME, EMAIL, FLOOR_SEAT_SEQ) VALUES
('12006', 'Stephen Curry', 'curry@esunbank.com', 3),
('16142', 'LeBron James', 'lebron@esunbank.com', 7),
('13040', 'Kevin Durant', 'kevin@esunbank.com', 9),
('17081', 'Kawhi Leonard', 'kawhi@esunbank.com', 10),
('11221', 'Nikola Jokic', 'jokic@esunbank.com', 12),
('16722', 'Victor Wembanyama', 'wemby@esunbank.com', 15);

-- ===============================================
-- 建立 Stored Procedure
-- ===============================================
DELIMITER $$

-- 取得某樓層座位資訊
CREATE PROCEDURE sp_getSeatsByFloor(IN p_floor INT)
BEGIN
    SELECT sc.FLOOR_SEAT_SEQ, sc.FLOOR_NO, sc.SEAT_NO, e.EMP_ID
    FROM SeatingChart sc
    LEFT JOIN Employee e ON sc.FLOOR_SEAT_SEQ = e.FLOOR_SEAT_SEQ
    WHERE sc.FLOOR_NO = p_floor;
END$$

-- 指派座位給員工
CREATE PROCEDURE sp_assignSeat(IN p_empId CHAR(5), IN p_seatSeq INT)
BEGIN
    START TRANSACTION;

    -- 清除該員工原本座位
    UPDATE Employee
    SET FLOOR_SEAT_SEQ = NULL
    WHERE EMP_ID = p_empId;

    -- 指派新座位
    UPDATE Employee
    SET FLOOR_SEAT_SEQ = p_seatSeq
    WHERE EMP_ID = p_empId;

    COMMIT;
END$$

-- 清空座位
CREATE PROCEDURE sp_clearSeat(IN p_seatSeq INT)
BEGIN
    UPDATE Employee
    SET FLOOR_SEAT_SEQ = NULL
    WHERE FLOOR_SEAT_SEQ = p_seatSeq;
END$$

DELIMITER ;
