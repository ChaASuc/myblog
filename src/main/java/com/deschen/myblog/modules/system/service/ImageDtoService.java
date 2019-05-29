package com.deschen.myblog.modules.system.service;

import com.deschen.myblog.modules.system.entity.Dir;
import com.deschen.myblog.modules.system.entity.Image;
import org.apache.catalina.LifecycleState;

import java.util.List;

/**
 * @Author deschen
 * @Create 2019/5/29
 * @Description
 * @Since 1.0.0
 */
public interface ImageDtoService {

    void insertDir(Dir dir);

    void insertImages(Image image);

    void updateDir(Dir dir);


    void deleteImage(Long imageId, Long dirId);

    Dir selectDirByDirId(Long dirId);

    Image selectImageByImageId(Long imageId);

    List<Dir> selectDirs();

    List<Image> selectImagesByDirId(Long dirId);

    Image selectRandomImage(Long dirId);
}
