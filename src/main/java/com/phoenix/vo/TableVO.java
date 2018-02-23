package com.phoenix.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hjx
 * 2018/2/8 0008.
 */
@Data
public class TableVO<T> implements Serializable {

    private Integer page;
    private Integer total;
    private T rows;

    public TableVO() {
    }

    public TableVO(Integer page, Integer total, T rows) {
        this.page = page;
        this.total = total;
        this.rows = rows;
    }
}
