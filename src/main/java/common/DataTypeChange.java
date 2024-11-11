package common;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataTypeChange {
    /**
     * SQLite DATETIME타입의 데이터 -> JAVA의 LacalDateTime 타입으로 변환
     * @param dateTime Timestamp타입의 일시
     * @return LocalDateTime타입의 일시
     */
    public static LocalDateTime changeSqlDtToJavaDt(Timestamp dateTime) {
        if(dateTime == null) {
            return null;
        }
        String sDateTime = String.valueOf(dateTime); //2024-11-08 11:13:14.0
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        return LocalDateTime.parse(sDateTime, formatter);
    }
}
