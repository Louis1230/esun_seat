USE seatDB;

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