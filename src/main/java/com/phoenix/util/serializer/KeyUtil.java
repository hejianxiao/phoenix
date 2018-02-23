package com.phoenix.util.serializer;

import java.util.Random;

/**
 * Created by hjx
 * 2017/12/25 0025.
 */
public class KeyUtil {

    /**
     * 生成主键
     * 格式:时间+随机数
     * @return 格式化后的时间戳
     */
    public static synchronized String genUniqueKey(){
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + number.toString();
    }
}
