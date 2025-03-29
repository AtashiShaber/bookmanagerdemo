package com.shaber.bookmanager.mapper;

import com.shaber.bookmanager.pojo.Book;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface BookMapper {
//查
    //返回所有Book类
    @Select("select * from tb_books")
    List<Book> selectAllBooks();

    //分页返回所有Book类，可以直接使用下方模糊查询，冗余方法
    @Select("select * from tb_books limit #{start},#{size}")
    List<Book> selectAllBooksByPage(Map<String, Integer> params);

    //查找是否存在该Book（通过作者跟书名）
    @Select("select * from tb_books where bookname = #{bookname} and author = #{author}")
    Book exist(@Param("bookname") String bookname, @Param("author") String author);
    //通过Bookid查找书籍
    @Select("select * from tb_books where bookid = #{bookid}")
    Book existBookById(@Param("bookid") String bookid);

    //通过书籍类型（书籍类型ID）查询该书籍类型总数
    @Select("select sum(remain) from tb_books where booktype = #{booktype}")
    int sumsumBooksByTypeId(@Param("booktype") String tid);

    //模糊查询书籍（支持分页）（以Map形式传参）
    List<Book> searchBooks(Map<String, Object> map);

    //模糊查询书籍，并统计数量
//    @Select("select count(*) from tb_books where bookname like #{bookname} and author like #{author} and publisher like #{publisher} and booktype like #{booktype}")
    int countAllBooksLike(Map<String, Object> map);

    //统计全部书籍的数量
    @Select("select count(*) from tb_books")
    int countAllBooks();

    //通过bookid获取单本书籍
    @Select("select * from tb_books where bookid = ${bookid}")
    Book getBookById(@Param("bookid") String bookid);

    //通过bookname与author获取单本书籍
    @Select("select * from tb_books where bookname = #{bookname} and author = #{author}")
    Book getBookByNameAndAuthor(@Param("bookname") String bookname, @Param("author") String author);

//增
    //输入新书籍
    @Insert("INSERT INTO tb_books VALUES(#{bookid},#{bookname},#{publisher},#{author},#{booktype},#{remain})")
    int insertBook(Book book);

//删
    //删除书籍（根据bookid）
    @Delete("delete from tb_books where bookid = #{bookid}")
    int deleteBook(@Param("bookid") String bookid);

//改
    //修改书籍
    @Update("update tb_books set " +
            "`bookname` = #{bookname}," +
            "`publisher` = #{publisher}," +
            "`author` = #{author}," +
            "`booktype` = #{booktype}," +
            "`remain` = #{remain} " +
            "where `bookid` = #{bookid}")
    int updateBook(Book book);

    //借阅减少库存
    @Update("update tb_books set remain = remain-1 where bookid = #{bookid}")
    int reduceRemain(String bookid);

    //还书归还库存
    @Update("update tb_books set remain = remain+1 where bookid = #{bookid}")
    int addRemain(String bookid);

}
