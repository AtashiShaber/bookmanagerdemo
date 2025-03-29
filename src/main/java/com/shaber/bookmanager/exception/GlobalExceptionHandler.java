package com.shaber.bookmanager.exception;

import com.shaber.bookmanager.pojo.ResultValue;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookServiceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultValue handleBookServiceException(BookServiceException ex) {
        ResultValue resultValue = new ResultValue();
        resultValue.setResult(false);
        resultValue.setMessage(ex.getMessage());
        return resultValue;
    }

    @ExceptionHandler(BorrowInfoServiceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultValue handleBorrowInfoServiceException(BorrowInfoServiceException ex) {
        ResultValue resultValue = new ResultValue();
        resultValue.setResult(false);
        resultValue.setMessage(ex.getMessage());
        return resultValue;
    }

    @ExceptionHandler(UserServiceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultValue handleBookTypeServiceException(BookTypeServiceException ex) {
        ResultValue resultValue = new ResultValue();
        resultValue.setResult(false);
        resultValue.setMessage(ex.getMessage());
        return resultValue;
    }

    @ExceptionHandler(BookTypeServiceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultValue handleUserServiceException(BookTypeServiceException ex) {
        ResultValue resultValue = new ResultValue();
        resultValue.setResult(false);
        resultValue.setMessage(ex.getMessage());
        return resultValue;
    }

    // 处理其他类型的异常
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultValue handleException(Exception ex) {
        ResultValue resultValue = new ResultValue();
        resultValue.setResult(false);
        resultValue.setMessage("系统内部错误，请稍后再试");
        return resultValue;
    }
}
