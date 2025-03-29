package com.shaber.bookmanager.service;

import com.shaber.bookmanager.pojo.BookType;
import com.shaber.bookmanager.pojo.PageResult;
import com.shaber.bookmanager.pojo.ResultValue;

import java.util.List;
import java.util.Map;

public interface BookTypeService {
    //增加书籍类型
    ResultValue addBookType(BookType bookType);

    //删除书籍类型
    ResultValue deleteBookType(String tid);

    //修改书籍类型
    ResultValue updateBookType(BookType bookType);

    //查找书籍类型（模糊查询）
    PageResult<BookType> selectBookTypePage(Map<String , Object> map);
    //查询全部书籍类型
    ResultValue<List<BookType>> selectAllBookType();
    //通过tid查找书籍类型
    ResultValue<BookType> selectBookType(String tid);
}
