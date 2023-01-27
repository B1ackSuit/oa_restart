package cn.ean.oaemp.exception;

import cn.ean.oaemp.model.bo.ResponseBO;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @FileName GlobalException
 * @Author ean
 * @Date 2023/1/27 22:15
 **/
@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(SQLException.class)
    public ResponseBO sqlException(SQLException e) {
        if (e instanceof SQLIntegrityConstraintViolationException) {
            return ResponseBO.error("该数据有关联数据，操作失败");
        }
        return ResponseBO.error("数据库异常，操作失败");
    }
}
