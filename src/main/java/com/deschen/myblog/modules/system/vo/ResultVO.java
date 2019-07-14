package com.deschen.myblog.modules.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * http请求返回的最外层对象
 * 2017-05-12 14:13
 */
@ApiModel(value = "结果集", description = "返回json对象")
@Data
public class ResultVO implements Serializable {


    /**
     * 错误码.
     */
    @ApiModelProperty(value = "状态码", name = "code", required = true)
    private Integer code;

    /**
     * 提示信息.
     */
    @ApiModelProperty(value = "信息", name = "msg", required = true)
    private String msg;

    /**
     * 返回成功的数据
     */
    @ApiModelProperty(value = "返回成功的数据", name = "data")
    private Object data;

/*    *//**
     * 返回失败的数据
     *//*
    @ApiModelProperty(value = "返回失败的数据", name = "extra")
    private Map<String, Object> extra;*/




}
