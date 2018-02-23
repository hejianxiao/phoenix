package com.phoenix.enums;

import lombok.Getter;

/**
 * Created by hjx
 * 2017/12/25 0025.
 */
@Getter
public enum ResultEnum {

    SUCCESS(0,"成功"),
    FORM_ERROR(10,"表单提交失败"),


    DELETE_EMPTY_ERROR(1,"删除失败，数据不存在"),

    USER_EMPTY(101,"用户名或密码不能为空"),
    USER_ERROR(102,"用户名或密码错误");


    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
