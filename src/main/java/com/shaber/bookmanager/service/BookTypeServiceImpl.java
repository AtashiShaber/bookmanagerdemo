package com.shaber.bookmanager.service;

import com.shaber.bookmanager.mapper.BookTypeMapper;
import com.shaber.bookmanager.pojo.BookType;
import com.shaber.bookmanager.pojo.PageResult;
import com.shaber.bookmanager.pojo.ResultValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookTypeServiceImpl implements BookTypeService{
    @Autowired
    BookTypeMapper bookTypeMapper;

    @Override
    public ResultValue addBookType(BookType bookType) {
        ResultValue resultValue = new ResultValue();
        Map<String ,Object> map = new HashMap<>();
        map.put("tname", bookType.getTname());
        map.put("tid", bookType.getTid());
        if (bookTypeMapper.addBookType(map) <= 0){
            resultValue.setResult(false);
            resultValue.setMessage("添加失败！");
            return resultValue;
        }
        resultValue.setResult(true);
        resultValue.setMessage("添加成功");
        return resultValue;
    }

    @Transactional
    @Override
    public ResultValue deleteBookType(String tid) {
        ResultValue resultValue = new ResultValue();
        if (bookTypeMapper.deleteBookType(tid) <= 0){
            resultValue.setResult(false);
            resultValue.setMessage("删除失败！");
            return resultValue;
        }
        resultValue.setResult(true);
        resultValue.setMessage("删除成功");
        return resultValue;
    }

    @Transactional
    @Override
    public ResultValue updateBookType(BookType bookType) {
        ResultValue resultValue = new ResultValue();
        Map<String ,Object> map = new HashMap<>();
        map.put("tname", bookType.getTname());
        map.put("tid", bookType.getTid());
        if (bookTypeMapper.updateBookType(map) <= 0){
            resultValue.setResult(false);
            resultValue.setMessage("修改异常！");
            return resultValue;
        }
        resultValue.setResult(true);
        resultValue.setMessage("修改成功");
        return resultValue;
    }

    @Override
    public PageResult<BookType> selectBookTypePage(Map<String, Object> map) {
        PageResult<BookType> pageResult = new PageResult<>();
        List<BookType> bookTypes = bookTypeMapper.selectBookTypePage(map);
        pageResult.setData(bookTypes);
        pageResult.setTotal(bookTypeMapper.countBookTypePage(map));
        return pageResult;
    }

    @Override
    public ResultValue<List<BookType>> selectAllBookType() {
        ResultValue<List<BookType>> result = new ResultValue<>();
        List<BookType> bookTypes = bookTypeMapper.selectAllType();
        result.setResult(true);
        result.setMessage("查询完毕");
        result.setT(bookTypes);
        return result;
    }

    @Override
    public ResultValue<BookType> selectBookType(String tid) {
        ResultValue<BookType> resultValue =  new ResultValue<>();
        resultValue.setResult(true);
        resultValue.setMessage("查询完毕");
        resultValue.setT(bookTypeMapper.selectByTid(tid));
        return resultValue;
    }
}
