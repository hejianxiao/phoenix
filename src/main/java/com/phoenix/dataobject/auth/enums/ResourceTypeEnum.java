package com.phoenix.dataobject.auth.enums;

import lombok.Getter;

/**
 * Created by hjx
 * 2018/1/26 0026.
 */
@Getter
public enum ResourceTypeEnum {
    URL(0,"链接"),
    BUTTON(1,"按钮"),
    STATIC(2,"静态资源");


    private Integer code;
    private String info;

    ResourceTypeEnum(Integer code, String info) {
        this.code = code;
        this.info = info;
    }
}
