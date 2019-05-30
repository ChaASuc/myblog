package com.deschen.myblog.modules.system.form;

import com.sun.org.apache.xml.internal.serializer.utils.SerializerMessages_zh_CN;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author deschen
 * @Create 2019/5/29
 * @Description
 * @Since 1.0.0
 */
@Data
public class TagForm implements Serializable {

    private Long tagId;

    private Long newTagId;

    private Integer state;

}
