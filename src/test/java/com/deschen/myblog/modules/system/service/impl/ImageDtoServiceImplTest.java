package com.deschen.myblog.modules.system.service.impl;

import com.deschen.myblog.modules.system.entity.Image;
import com.deschen.myblog.modules.system.service.ImageDtoService;
import com.deschen.myblog.utils.TestUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

@Slf4j
public class ImageDtoServiceImplTest extends TestUtil {

    @Autowired
    private ImageDtoService imageDtoService;

    @Test
    public void selectRandomImage() {
        for (int i = 1; i < 10; i++) {

            Image image =
                    imageDtoService.selectRandomImage(null);
            log.info("【图片模块】 imageId={}", image.getImageId());
        }
    }
}