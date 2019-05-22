package com.deschen.myblog.core.utils;


import com.deschen.myblog.modules.system.vo.ResultVO;

/**
 * Created by 廖师兄
 * 2017-05-15 00:22
 */
public class ResultUtil{

    public static ResultVO success(Object data) {
        ResultVO result = new ResultVO();
        result.setCode(0);
        result.setMsg("成功");
        if (data == null) {
            return result;
        }
        result.setData(data);
        return result;
    }


    public static ResultVO error(Integer code, String message) {
        ResultVO result = new ResultVO();
        result.setCode(code);
        result.setMsg(message);
        return result;
    }
}
