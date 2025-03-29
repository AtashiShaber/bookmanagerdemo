package com.shaber.bookmanager.mapper;

import com.shaber.bookmanager.pojo.Book;
import com.shaber.bookmanager.pojo.BorrowInfo;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface BorrowInfoMapper {
    //查
    //查询所有借阅记录
    @Select("select * from tb_borrowinfo")
    List<BorrowInfo> getBorrowInfo();
    //查询所有借阅记录（分页）
    @Select("select * from tb_borrowinfo limit #{start},#{size}")
    List<BorrowInfo> getBorrowInfoPage(Map<String ,Integer> map);
    //模糊查询（根据书籍名，手机号）
    @Select("select br.* from tb_borrowinfo br right join tb_books b on br.bookid = b.bookid " +
            "where b.bookname like #{bookname} and br.phone like #{phone}")
    List<BorrowInfo> searchBorrowInfo(String bookname, String phone);

    //模糊查询（根据书籍名，手机号（分页））
    List<BorrowInfo> searchBorrowInfoPage(Map<String ,Object> map);
    //统计模糊查询的数量
    int countBorrowInfoPage(Map<String ,Object> map);

    //通过借阅记录ID获取借阅记录
    @Select("select * from tb_borrowinfo where borrowid = #{borrowid}")
    BorrowInfo getBorrowInfoByBorrowid(String borrowid);
    //通过借阅人与书籍ID查询用户书本是否未还
    @Select("SELECT * FROM `tb_borrowinfo` WHERE borrower = #{borrower} AND bookid = #{bookid} AND isreturn = 0")
    Book exist(@Param("borrower") String username, String bookid);
    //查询借阅人的未还书籍数量
    @Select("select count(*) from tb_borrowinfo where borrower = #{borrower} and isreturn = 0")
    int countBorrowInfoByUser(@Param("borrower") String username);
    //通过借阅记录ID查询该书未被归还的数量
    @Select("select count(*) from tb_borrowinfo where bookid = #{bookid} and isreturn = 0")
    int countBorrowInfoByBook(String bookid);
    //查询该用户借阅记录
    @Select("select * from tb_borrowinfo where borrower = #{borrower}")
    List<BorrowInfo> getBorrowInfoByUsername(String borrower);
    @Select("select * from tb_borrowinfo where borrower = #{borrower} limit #{start},#{size}")
    List<BorrowInfo> getBorrowInfoPageByUsername(Map<String,Object> map);
    //查询该用户的该书籍借阅记录
    @Select("select * from tb_borrowinfo where bookid = #{bookid} and borrower = #{borrower}")
    BorrowInfo getBorrowInfoByUsernameAndBookid(String bookId,@Param("borrower") String username);
    //查询该借阅记录是否已经归还
    @Select("select * from tb_borrowinfo where borrowid = #{borrowid} and isreturn = 0")
    BorrowInfo existReturn(String borrowid);

    //增
    //添加借阅记录
    @Insert("insert into tb_borrowinfo(borrowid,bookid,borrower,phone,borrowtime,isreturn) values(#{borrowid},#{bookid},#{borrower},#{phone},#{borrowtime},#{isreturn})")
    int addBorrowInfo(Map<String ,Object> map);

    //删
    //删除指定borrowid的记录
    @Delete("delete from `tb_borrowinfo` where borrowid = #{borrowid}")
    int deleteBorrowInfoByBorrowid(String borrowid);

    //改
    //还书(通过借阅人、书籍ID、手机号）
    @Update("update tb_borrowinfo set isreturn = 1, returntime = #{returntime} " +
            "where borrower = #{borrowid} and bookid = #{bookid} and isreturn = 0 and phone = #{phone}")
    int returnBorrowInfo(Map<String ,Object> map);
    //还书（书籍ID）
    @Update("update tb_borrowinfo set isreturn = 1, returntime = #{returntime} where borrowid = #{borrowid}")
    int returnBorrowInfoByBorrowid(@Param("returntime") LocalDateTime returntime,@Param("borrowid") String borrowid);
}
