package com.course.manager;

import org.springframework.stereotype.Component;

@Component
public class BaseManager {
    //    计算分页的总页数 recordCount（记录总数）和 pageSize（每页的记录数）
    protected int calcPageCount(int recordCount, int pageSize) {
    //     如果可以整除，返回 recordCount 除以 pageSize 的结果，这表示没有剩余记录，页数正好是整数
        if (recordCount % pageSize == 0) {
            return recordCount / pageSize;
        }

    //      如果不能整除，返回 recordCount 除以 pageSize 的结果加 1，以计算出总页数。
        return (recordCount / pageSize) + 1;
    }
}
