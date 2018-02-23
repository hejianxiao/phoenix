package com.phoenix.exception;

import com.phoenix.enums.ResultEnum;
import lombok.Getter;

/**
 * Created by hjx
 * 2018/1/23 0023.
 */
@Getter
public class PhoenixException extends RuntimeException{

    private Integer code;

    public PhoenixException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public PhoenixException(ResultEnum resultEnum,String msg) {
        super(msg);
        this.code = resultEnum.getCode();
    }

}
