package com.shaber.bookmanager.service;

import com.shaber.bookmanager.exception.BorrowInfoServiceException;
import com.shaber.bookmanager.mapper.BookMapper;
import com.shaber.bookmanager.mapper.BorrowInfoMapper;
import com.shaber.bookmanager.pojo.Book;
import com.shaber.bookmanager.pojo.BorrowInfo;
import com.shaber.bookmanager.pojo.PageResult;
import com.shaber.bookmanager.pojo.ResultValue;
import com.shaber.bookmanager.utils.RandomUUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BorrowInfoServiceImpl implements BorrowInfoService{
    @Autowired
    BorrowInfoMapper borrowInfoMapper;

    @Autowired
    BookMapper bookMapper;

    @Override
    public PageResult<BorrowInfo> selectBorrowInfoByPage(Map<String, Object> params) {
        PageResult<BorrowInfo> pageResult = new PageResult<>();
        //处理Map的空值问题
        if (params.get("bookname") == ""){
            params.put("bookname",null);
        }
        if (params.get("borrower") == ""){
            params.put("borrower",null);
        }
        if (params.get("phone") == ""){
            params.put("phone",null);
        }
        //进行查询
        List<BorrowInfo> borrowInfos = borrowInfoMapper.searchBorrowInfoPage(params);
        pageResult.setTotal(borrowInfoMapper.countBorrowInfoPage(params));
        pageResult.setData(borrowInfos);
        return pageResult;
    }

    @Override
    public PageResult<BorrowInfo> getBorrowInfoByBorrower(Map<String,Object> map) {
        PageResult<BorrowInfo> pageResult = new PageResult<>();
        List<BorrowInfo> borrowInfos = borrowInfoMapper.getBorrowInfoPageByUsername(map);
        pageResult.setTotal(borrowInfoMapper.getBorrowInfoByUsername((String) map.get("borrower")).size());
        pageResult.setData(borrowInfos);
        return pageResult;
    }

    @Override
    public ResultValue addBorrowInfo(String username, String bookid) {
        ResultValue resultValue = new ResultValue();
        Map<String, Object> params = new HashMap<>();

        //通过bookid获取书籍信息
        Book book = bookMapper.existBookById(bookid);
        //如果书籍并不存在
        if (book == null){
            resultValue.setResult(false);
            resultValue.setMessage("书籍不存在！");
            throw new BorrowInfoServiceException("书籍不存在！");
        }

        params.put("borrowid", RandomUUIDUtil.getUUID(32));
        params.put("borrower", username);
        params.put("bookid", bookid);
        //随机生成一个手机号
        params.put("phone", RandomUUIDUtil.getPhone());
        params.put("borrowtime", LocalDateTime.now());
        params.put("isreturn", 0);
        if (borrowInfoMapper.addBorrowInfo(params) <= 0){
            resultValue.setResult(false);
            resultValue.setMessage("借阅失败！");
            throw new BorrowInfoServiceException("借阅失败！");
        }
        //添加借阅记录成功需要对tb_books中的remain进行-1操作
        if (bookMapper.reduceRemain(bookid) <= 0){
            resultValue.setResult(false);
            resultValue.setMessage("从图书馆获取书籍失败！");
            throw new BorrowInfoServiceException("从图书馆获取书籍失败！");
        }
        resultValue.setResult(true);
        resultValue.setMessage("借阅成功！");
        return resultValue;
    }

    @Transactional
    @Override
    public ResultValue returnBorrowInfoByBorrowid(String borrowid) {
        ResultValue resultValue = new ResultValue();
        BorrowInfo borrowInfoByBorrowid = borrowInfoMapper.getBorrowInfoByBorrowid(borrowid);
        LocalDateTime returntime = LocalDateTime.now();
        if (borrowInfoMapper.returnBorrowInfoByBorrowid(returntime, borrowid) <= 0){
            resultValue.setResult(false);
            resultValue.setMessage("更新还书数据失败！");
            throw new BorrowInfoServiceException("更新还书数据失败！");
        }
        if (bookMapper.addRemain(borrowInfoByBorrowid.getBookid()) <= 0){
            resultValue.setResult(false);
            resultValue.setMessage("书籍归还失败！");
            throw new BorrowInfoServiceException("书籍归还失败！");
        }
        resultValue.setResult(true);
        resultValue.setMessage("还书成功");
        return resultValue;
    }

    @Transactional
    @Override
    public ResultValue deleteBorrowInfoByBorrowid(String borrowid) {
        ResultValue resultValue = new ResultValue();
        //删除前得先判断书本是否归还
        if (borrowInfoMapper.existReturn(borrowid) != null){
            resultValue.setResult(false);
            resultValue.setMessage("本次借阅尚未归还，不能删除！");
            return resultValue;
        }
        if (borrowInfoMapper.deleteBorrowInfoByBorrowid(borrowid) <= 0){
            resultValue.setResult(false);
            resultValue.setMessage("借阅记录删除失败！");
            throw new BorrowInfoServiceException("借阅记录删除失败！");
        }
        resultValue.setResult(true);
        resultValue.setMessage("借阅记录删除成功");
        return resultValue;
    }
}
