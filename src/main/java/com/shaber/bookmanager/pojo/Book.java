package com.shaber.bookmanager.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Value("0")
    private String bookid;
    private String bookname;
    private String publisher;
    private String author;
    private String booktype;
    private int remain;

}
