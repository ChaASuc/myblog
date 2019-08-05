package com.deschen.myblog.core.handler;


import com.deschen.myblog.core.exceptions.GlobalException;

import com.deschen.myblog.core.utils.ResultVOUtil;
import com.deschen.myblog.modules.system.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author deschen
 * @Create 2019/5/14
 * @Description 全局异常处理
 * @Since 1.0.0
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // 拦截异常
    @ExceptionHandler({GlobalException.class})
    public ResultVO handGlobalException(GlobalException e, HttpServletRequest request) {
        ResultVO error = ResultVOUtil.error(e.getCode(), e.getMessage());
        log.error("【GlobalException】 error url = {}", request.getRequestURL());
        return error;
    }


    //拦截全局异常
    @ExceptionHandler({Exception.class})
    public ResultVO handlerException(Exception e, HttpServletRequest request) {
        ResultVO error = ResultVOUtil.error(420, e.toString());
        log.error("【系统异常】 error url = {}", request.getRequestURL());
        return error;
    }
}
