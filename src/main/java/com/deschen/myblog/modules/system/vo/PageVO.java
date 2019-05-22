package com.deschen.myblog.modules.system.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author deschen
 * @Create 2019/4/27
 * @Description 分页信息
 * @Since 1.0.0
 */
@ApiModel(value = "分页结果集", description = "数据进行分页存储")
@Data
public class PageVO<T> implements Serializable {

    /*总页数*/
    @ApiModelProperty(value = "总页数", name = "total", required = true)
    @JsonProperty(value = "total", required = true)
    private Integer totalPages;

    /*页码*/
    @ApiModelProperty(value = "页码", name = "number", required = true)
    @JsonProperty(value = "number", required = true)
    private Integer number;

    /*页码*/
    @ApiModelProperty(value = "总元素", name = "totalElements", required = true)
    @JsonProperty(value = "totalElements", required = true)
    private long totalElements;

    /*内容*/
    @ApiModelProperty(value = "内容", name = "content", required = true)
    @JsonProperty(value = "content", required = true)
    private List<T> content;


}
