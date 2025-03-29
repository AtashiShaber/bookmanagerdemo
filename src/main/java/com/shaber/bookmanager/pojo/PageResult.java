package com.shaber.bookmanager.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> {
    private int total;
    private List<T> data;

}
