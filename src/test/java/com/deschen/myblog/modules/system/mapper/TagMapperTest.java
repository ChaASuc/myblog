package com.deschen.myblog.modules.system.mapper;

import com.deschen.myblog.core.utils.IdWorker;
import com.deschen.myblog.core.utils.IdWorkerUtil;
import com.deschen.myblog.modules.system.entity.Tag;
import com.deschen.myblog.utils.TestUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@Slf4j
public class TagMapperTest extends TestUtil {

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private IdWorkerUtil idWorkerUtil;

    @Test
    public void insertTagsSelective() {
        List<Tag> tags = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Tag tag = new Tag();
            long id = new IdWorker().nextId();
            tag.setTagId(id);
            tag.setCategoryId(1L);
            tag.setTagName(i + "");
            tags.add(tag);
        }
        int success = tagMapper.insertTagsSelective(tags);
        if (success != 10) {
            log.error("【TagMapper】批量插入失败");
        }
        log.info("【TagMapper】批量插入成功");
//        for (int i = 1; i <= 10; i++) {
//            System.out.println(idWorkerUtil.getId());
//        }
    }
}