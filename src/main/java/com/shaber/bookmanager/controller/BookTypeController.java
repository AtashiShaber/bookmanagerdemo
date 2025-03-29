package com.shaber.bookmanager.controller;

import com.shaber.bookmanager.pojo.BookType;
import com.shaber.bookmanager.pojo.PageResult;
import com.shaber.bookmanager.pojo.ResultValue;
import com.shaber.bookmanager.service.BookTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BookTypeController {
    @Autowired
    BookTypeService bookTypeServiceImpl;

    @GetMapping("/bookTypes")
    //模糊查询，分页
    public PageResult<BookType> selectBookTypePage(String tid,String tname,int start,int size){
        Map<String ,Object> map = new HashMap<>();
        map.put("tid",tid);
        map.put("tname",tname);
        map.put("start",start);
        map.put("size",size);
        return bookTypeServiceImpl.selectBookTypePage(map);
    }

    @GetMapping("/allBookTypes")
    //查询全部书籍类型
    public ResultValue<List<BookType>> selectAllBookType(){
        return bookTypeServiceImpl.selectAllBookType();
    }

    @GetMapping("/bookType")
    //通过tid查询书籍类型
    public ResultValue<BookType> selectBookTypeById(String tid){
        return bookTypeServiceImpl.selectBookType(tid);
    }

    @PostMapping("/bookType")
    //增加书籍
    public ResultValue insertBookType(@RequestBody BookType bookType){
        return bookTypeServiceImpl.addBookType(bookType);
    }

    @DeleteMapping("/bookType/{bookid}")
    //删除书籍
    public ResultValue deleteBookType(@PathVariable("bookid") String bookid){
        return bookTypeServiceImpl.deleteBookType(bookid);
    }

    @PutMapping("/bookType")
    //修改书籍信息
    public ResultValue updateBookType(@RequestBody BookType bookType){
        return bookTypeServiceImpl.updateBookType(bookType);
    }
}
