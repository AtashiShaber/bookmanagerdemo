package com.shaber.bookmanager.mapper;

import com.shaber.bookmanager.pojo.BookType;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface BookTypeMapper {
    //查
    //查询所有书籍类型
    @Select("select * from tb_booktype")
    List<BookType> selectAllType();
    //查询所有书籍类型（分页）
    @Select("select * from tb_booktype limit #{start},#{size}")
    List<BookType> selectAllTypePage(Map<String,  Integer> map);
    //通过书籍类型名查询
    @Select("select * from tb_booktype where tname=#{tname}")
    BookType selectByTname(String tname);
    //通过书籍类型ID查询
    @Select("select * from tb_booktype where tid=#{tid}")
    BookType selectByTid(String tid);
    //模糊查询（分页）
    List<BookType> selectBookTypePage(Map<String,  Object> map);
    //模糊查询统计数量
    int countBookTypePage(Map<String,  Object> map);

    //增
    //添加书籍类型
    @Insert("insert into tb_booktype values(#{tid},#{tname})")
    int addBookType(Map<String ,Object> map);

    //删
    //删除指定（tid）书籍类型
    @Delete("delete from tb_booktype where tid=#{tid}")
    int deleteBookType(String tid);

    //改
    //修改书籍类型信息
    @Update("update tb_booktype set tname=#{tname} where tid=#{tid}")
    int updateBookType(Map<String ,Object> map);
}
