package com.phoenix.util;


import com.phoenix.enums.ResultEnum;
import com.phoenix.vo.ResultVO;
import org.springframework.util.StringUtils;

/**
 * Created by hjx
 * 2017/12/25 0025.
 */
public class ResultVOUtil {

    public static ResultVO success(Object object,String msg) {
        ResultVO<Object> resultVO = new ResultVO<>();
        resultVO.setData(object);
        resultVO.setMsg(StringUtils.isEmpty(msg)? ResultEnum.SUCCESS.getMsg() : msg);
        resultVO.setCode(0);
        return resultVO;
    }

    public static ResultVO success() {
        return success(null,null);
    }

    public static ResultVO error(Integer code,String msg) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
}
