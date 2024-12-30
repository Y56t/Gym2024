package com.milotnt.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageHelper<T> {
        //当前页码
        private Long pageNumber;
        //页面大小
        private Long pageSize;
        //总页数
        private Long pageCount;
        //总条数
        private Long totalCount;
        //通过sql查询出来的数据
        private List<T> records;

}
