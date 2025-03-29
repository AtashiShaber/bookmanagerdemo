package com.shaber.bookmanager.controller;

import com.shaber.bookmanager.pojo.Book;
import com.shaber.bookmanager.pojo.PageResult;
import com.shaber.bookmanager.pojo.ResultValue;
import com.shaber.bookmanager.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
//@RequestMapping("/books")
public class BookController {
    @Autowired
    BookService bookServiceImpl;

    @GetMapping("/books")
    public PageResult<Book> searchBooks(
            @RequestParam(required = false) String bookname,
            @RequestParam(required = false) String publisher,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String booktype,
            @RequestParam int start,
            @RequestParam int size) {
        Map<String, Object> map = new HashMap<>();
        map.put("bookname", bookname);
        map.put("publisher", publisher);
        map.put("author", author);
        map.put("booktype", booktype);
        map.put("start", start);
        map.put("size", size);
        return bookServiceImpl.searchBooks(map);
    }

    @GetMapping("/book")
    public ResultValue<Book> searchBook(String bookid) {
        return bookServiceImpl.selectOneBookByBookid(bookid);
    }

    @PostMapping("/book")
    public ResultValue insertBook(@RequestBody Book book) {
        return bookServiceImpl.insertBook(book);  // Spring自动处理JSON转换
    }

    @DeleteMapping("/book/{bookid}")
    public ResultValue deleteBook(@PathVariable("bookid") String bookid) {
        return bookServiceImpl.deleteBook(bookid);  // Spring自动处理JSON转换
    }

    @PutMapping("/book")
    public ResultValue updateBook(@RequestBody Book book) {
        return bookServiceImpl.updateBook(book);  // Spring自动处理JSON转换
    }
}

//@RestController
//public class BookController {
//    @Autowired
//    BookService bookServiceImpl;
//
//
//    @GetMapping("/books")
//    //进行查询操作
//    public String searchBooks(@RequestParam Map<String, Object> map) throws JsonProcessingException {
//        PageResult<Book> bookPageResult = bookServiceImpl.searchBooks(map);
//        ObjectMapper objectMapper = new ObjectMapper();
//        return objectMapper.writeValueAsString(bookPageResult);
//    }
//
//    @PostMapping("/books")
//    //增加书籍信息
//    public String insertBook(Book book) throws JsonProcessingException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        ResultValue resultValue = bookServiceImpl.insertBook(book);
//        return objectMapper.writeValueAsString(resultValue);
//    }
//
//    @DeleteMapping("/books/{bookid}")
//    //删除书籍
//    public String deleteBook(@PathVariable("bookid") String bookid) throws JsonProcessingException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        ResultValue resultValue = bookServiceImpl.deleteBook(bookid);
//        return objectMapper.writeValueAsString(resultValue);
//    }
//
//    @PutMapping("/books")
//    //修改书籍信息
//    public String updateBook(Book book) throws JsonProcessingException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        ResultValue resultValue = bookServiceImpl.updateBook(book);
//        return objectMapper.writeValueAsString(resultValue);
//    }
//}
