package com.shaber.bookmanager.service;

import com.shaber.bookmanager.pojo.Book;
import com.shaber.bookmanager.pojo.PageResult;
import com.shaber.bookmanager.pojo.ResultValue;

import java.util.List;
import java.util.Map;

public interface BookService {
    //获取全部书籍
    List<Book> getAllBooks();

    //分页返回（模糊查询）
    PageResult<Book> searchBooks(Map<String, Object> map);

    //根据书籍名跟作者名查询书籍
    Book getBookByBooknameAndAuthor(String bookname, String author);

    //根据Bookid查询书籍
    ResultValue<Book> selectOneBookByBookid(String bookid);

    //增加书籍
    ResultValue insertBook(Book book);

    //修改书籍信息
    ResultValue updateBook(Book book);

    //删除书籍
    ResultValue deleteBook(String bookid);
}
