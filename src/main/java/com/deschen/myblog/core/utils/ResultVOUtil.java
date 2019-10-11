package com.deschen.myblog.core.utils;


import com.deschen.myblog.core.enums.IEnum;
import com.deschen.myblog.modules.system.vo.ResultVO;
import org.springframework.stereotype.Component;

/**
 * @Author deschen
 * @Create 2019/5/14
 * @Description result返回工具，与前端交互
 * @Since 1.0.0
 */
public class ResultVOUtil{

    public static ResultVO success(Object data) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(data);
        return resultVO;
    }

    public static ResultVO success() {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }
    public static ResultVO error(Integer code, String message) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(message);
        return resultVO;
    }

    public static ResultVO error(IEnum iEnum) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(iEnum.getCode());
        resultVO.setMsg(iEnum.getMessage());
        return resultVO;
    }
}
