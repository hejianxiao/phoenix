package com.phoenix.util;

import com.github.pagehelper.Page;
import com.phoenix.vo.TableVO;

import java.util.List;

/**
 * Created by hjx
 * 2018/2/8 0008.
 */
public class TableVOUtil{


    public static TableVO pageConvertToTable(Page page){
        TableVO tableVO = new TableVO();
        tableVO.setPage(page.getPageNum());
        tableVO.setTotal((int)page.getTotal());
        tableVO.setRows(page.getResult());
        return tableVO;
    }

    public static TableVO listConvertToTable(List list){
        TableVO tableVO = new TableVO();
        tableVO.setRows(list);
        return tableVO;
    }

}
