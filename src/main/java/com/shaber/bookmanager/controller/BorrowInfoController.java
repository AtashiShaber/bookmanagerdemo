package com.shaber.bookmanager.controller;

import com.shaber.bookmanager.pojo.BorrowInfo;
import com.shaber.bookmanager.pojo.PageResult;
import com.shaber.bookmanager.pojo.ResultValue;
import com.shaber.bookmanager.service.BorrowInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/borrowInfo")
public class BorrowInfoController {
    @Autowired
    BorrowInfoService borrowInfoServiceImpl;

    //模糊查询，分页
    @GetMapping
    public PageResult<BorrowInfo> selectBorrowInfoPage(String borrower, String bookname,String phone,int start,int size){
        Map<String ,Object> map = new HashMap<>();
        map.put("borrower",borrower);
        map.put("bookname",bookname);
        map.put("phone",phone);
        map.put("start",start);
        map.put("size",size);
        return borrowInfoServiceImpl.selectBorrowInfoByPage(map);
    }

    //借书
    @PostMapping
    public ResultValue insertBorrowInfo(@RequestBody BorrowInfo borrowInfo){
        String username = borrowInfo.getBorrower();
        String bookid = borrowInfo.getBookid();
        return borrowInfoServiceImpl.addBorrowInfo(username, bookid);
    }

    //还书
    @PutMapping
    public ResultValue returnBorrowInfoByBorrowid(@RequestBody BorrowInfo borrowInfo){
        return borrowInfoServiceImpl.returnBorrowInfoByBorrowid(borrowInfo.getBorrowid());
    }

    //删除借阅记录
    @DeleteMapping("/{borrowid}")
    public ResultValue deleteBorrowInfoByBorrowid(String borrowid){
        return borrowInfoServiceImpl.deleteBorrowInfoByBorrowid(borrowid);
    }
}
