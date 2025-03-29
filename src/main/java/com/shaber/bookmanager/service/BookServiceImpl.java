package com.shaber.bookmanager.service;

import com.shaber.bookmanager.exception.BookServiceException;
import com.shaber.bookmanager.mapper.BookMapper;
import com.shaber.bookmanager.mapper.BorrowInfoMapper;
import com.shaber.bookmanager.pojo.Book;
import com.shaber.bookmanager.pojo.PageResult;
import com.shaber.bookmanager.pojo.ResultValue;
import com.shaber.bookmanager.utils.RandomUUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    BookMapper bookMapper;

    @Autowired
    private BorrowInfoMapper borrowInfoMapper;


    @Override
    public List<Book> getAllBooks() {
        return bookMapper.selectAllBooks();
    }

    @Override
    public PageResult<Book> searchBooks(Map<String, Object> map) {
        PageResult<Book> pageResult = new PageResult<>();
        List<Book> books = bookMapper.searchBooks(map);
        pageResult.setData(books);
        //查询总数传入
        pageResult.setTotal(bookMapper.countAllBooksLike(map));
        return pageResult;
    }

    @Override
    public Book getBookByBooknameAndAuthor(String bookname, String author) {
        return bookMapper.exist(bookname,author);
    }

    @Override
    public ResultValue<Book> selectOneBookByBookid(String bookid) {
        ResultValue<Book> resultValue = new ResultValue<>();
        Book book = bookMapper.existBookById(bookid);
        if (book == null){
            resultValue.setResult(false);
            resultValue.setMessage("书籍不存在！");
            return resultValue;
        }
        resultValue.setResult(true);
        resultValue.setT(book);
        resultValue.setMessage("查询成功");
        return resultValue;
    }

    @Transactional
    @Override
    public ResultValue insertBook(Book book) {
        ResultValue resultValue = new ResultValue();
        book.setBookid(RandomUUIDUtil.getUUID(32));
        int i = bookMapper.insertBook(book);
        if (i <= 0){
            resultValue.setResult(false);
            resultValue.setMessage("添加失败！");
            throw new BookServiceException("添加失败！");
        }
        resultValue.setResult(true);
        resultValue.setMessage("添加成功");
        return resultValue;
    }

    @Transactional
    @Override
    public ResultValue updateBook(Book book) {
        ResultValue resultValue = new ResultValue();
        int i = bookMapper.updateBook(book);
        if (i <= 0){
            resultValue.setResult(false);
            resultValue.setMessage("修改失败！");
            throw new BookServiceException("修改失败！");
        }
        resultValue.setResult(true);
        resultValue.setMessage("修改成功！");
        return resultValue;
    }

    @Transactional
    @Override
    public ResultValue deleteBook(String bookid) {
        ResultValue resultValue = new ResultValue();

        //查询书籍是否存在借阅记录
        if (borrowInfoMapper.countBorrowInfoByBook(bookid) > 0) {
            resultValue.setResult(false);
            resultValue.setMessage("书籍存在未归还的情况，无法删除");
            throw new BookServiceException("书籍存在未归还的情况，无法删除");
        }

        //若不存在借阅情况，则支持删除
        //删除书籍，但不删除借阅记录
        int i = bookMapper.deleteBook(bookid);
        if (i <= 0){
            resultValue.setResult(false);
            resultValue.setMessage("书籍删除失败！");
            throw new BookServiceException("书籍删除失败！");
        }

        resultValue.setResult(true);
        resultValue.setMessage("删除成功");
        return resultValue;
    }


}
