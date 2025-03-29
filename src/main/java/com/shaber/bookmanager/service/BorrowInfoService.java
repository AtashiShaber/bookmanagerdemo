package com.shaber.bookmanager.service;

import com.shaber.bookmanager.pojo.BorrowInfo;
import com.shaber.bookmanager.pojo.PageResult;
import com.shaber.bookmanager.pojo.ResultValue;

import java.util.Map;

public interface BorrowInfoService {
    //模糊查询借阅信息（分页）
    PageResult<BorrowInfo> selectBorrowInfoByPage(Map<String, Object> params);
    //返回当前用户的借阅记录
    PageResult<BorrowInfo> getBorrowInfoByBorrower(Map<String, Object> map);
    //借书
    ResultValue addBorrowInfo(String username, String bookid);
    //还书
    ResultValue returnBorrowInfoByBorrowid(String borrowid);
    //删除借阅记录
    ResultValue deleteBorrowInfoByBorrowid(String borrowid);
}
