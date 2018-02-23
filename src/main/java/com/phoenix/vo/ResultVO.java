package com.phoenix.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * http请求返回最外层
 * Created by hjx
 * 2017/12/25 0025.
 */
@Data
public class ResultVO<T> implements Serializable{

    private static final long serialVersionUID = -4527268355000357507L;

    /** 状态码. */
    private Integer code;
    
    /** 提示信息. */
    private String msg;
    
    /** 具体内容. */
    private T data;
}
