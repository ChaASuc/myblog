package com.deschen.myblog.modules.system.service.impl;

import com.deschen.myblog.config.ImageConfig;
import com.deschen.myblog.core.constants.BlogConstant;
import com.deschen.myblog.core.enums.BlogEnum;
import com.deschen.myblog.core.exceptions.GlobalException;
import com.deschen.myblog.core.utils.IdWorker;
import com.deschen.myblog.modules.system.entity.Dir;
import com.deschen.myblog.modules.system.entity.DirExample;
import com.deschen.myblog.modules.system.entity.Image;
import com.deschen.myblog.modules.system.entity.ImageExample;
import com.deschen.myblog.modules.system.mapper.DirMapper;
import com.deschen.myblog.modules.system.mapper.ImageMapper;
import com.deschen.myblog.modules.system.service.ImageDtoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

/**
 * @Author deschen
 * @Create 2019/5/29
 * @Description
 * @Since 1.0.0
 */
@Service
@Slf4j
public class ImageDtoServiceImpl implements ImageDtoService {

    @Autowired
    private DirMapper dirMapper;

    @Autowired
    private ImageMapper imageMapper;

    @Autowired
    private ImageConfig imageConfig;

    @Override

    /**
     * @Param: [dir]
     * @Return:void
     * @Author: deschen
     * @Date: 2019/5/29 9:10
     * @Description: 插入文件类
     */
    @Transactional
    public void insertDir(Dir dir) {
//        long dirId = new IdWorker().nextId();
        String dirUrl
                = imageConfig.getDir() + dir.getDirName();
        dir.setDirUrl(dirUrl);
//        dir.setDirId(dirId);
        int success = dirMapper.insertSelective(dir);
        if (success == 0) {
            throw new GlobalException(BlogEnum.DIR_INSERT_ERROR);
        }
    }

    @Override

    /**
     * @Param: [image]
     * @Return:void
     * @Author: deschen
     * @Date: 2019/5/29 9:23
     * @Description: 插入图片
     */
    public Long insertImages(Image image) {
        long imageId = new IdWorker().nextId();
        image.setImageId(imageId);
        int success = imageMapper.insertSelective(image);
        if (success == 0) {
            throw new GlobalException(BlogEnum.IMAGE_INSERT_ERROR);
        }
        return imageId;
    }

    @Override

    /**
     * @Param: [dir]
     * @Return:void
     * @Author: deschen
     * @Date: 2019/5/29 9:26
     * @Description: 文件夹更新(state = 0 代表删除)
     */
    @Transactional
    public void updateDir(Dir dir) {
        int success = dirMapper.updateByPrimaryKeySelective(dir);
        if (success == 0) {
            throw new GlobalException(BlogEnum.DIR_UPDATE_ERROR);
        }
    }

    @Override

    /**
     * @Param: [imageId, dirName]
     * @Return:void
     * @Author: deschen
     * @Date: 2019/5/29 9:29
     * @Description: 删除图片
     */
    @Transactional
    public void deleteImage(Long imageId, Long dirId) {
        Image image = new Image();
        image.setState(BlogConstant.RECORD_VOID);
        ImageExample imageExample = new ImageExample();
        ImageExample.Criteria criteria = imageExample.createCriteria();
        // dirId不为空时,代表删除dir文件夹下的图片
        if (dirId != null) {
            criteria.andDirIdEqualTo(dirId);
        }
        if (imageId != null) {
            criteria.andImageIdEqualTo(imageId);
        }
        int success =
                imageMapper.updateByExampleSelective(image, imageExample);
        if (success == 0) {
            throw new GlobalException(BlogEnum.IMAGE_UPDATE_ERROR);
        }
    }

    @Override

    /**
     * @Param: [dirId]
     * @Return:com.deschen.myblog.modules.system.entity.Dir
     * @Author: deschen
     * @Date: 2019/5/29 9:42
     * @Description: 根据文件夹id获取文件夹实体类
     */
    public Dir selectDirByDirId(Long dirId) {
        Dir dir = dirMapper.selectByPrimaryKey(dirId);
        if (dir == null) {
            throw new GlobalException(BlogEnum.DIR_NOT_EXIST);
        }
        return dir;
    }

    @Override

    /**
     * @Param: [imageId]
     * @Return:com.deschen.myblog.modules.system.entity.Image
     * @Author: deschen
     * @Date: 2019/5/29 10:26
     * @Description: 根据imageId获取图片
     */
    public Image selectImageByImageId(Long imageId) {
        Image image = imageMapper.selectByPrimaryKey(imageId);
        if (image == null) {
            throw new GlobalException(BlogEnum.IMAGE_NOT_EXIST);
        }
        return image;
    }

    @Override

    /**
     * @Param: []
     * @Return:java.util.List<com.deschen.myblog.modules.system.entity.Dir>
     * @Author: deschen
     * @Date: 2019/5/29 10:58
     * @Description: 获取所有文件夹
     */
    public List<Dir> selectDirs() {
        DirExample dirExample = new DirExample();
        List<Dir> dirs = dirMapper.selectByExample(dirExample);
        return dirs;
    }

    @Override

    /**
     * @Param: [dirId]
     * @Return:java.util.List<com.deschen.myblog.modules.system.entity.Image>
     * @Author: deschen
     * @Date: 2019/5/29 11:16
     * @Description: 根据文件夹id获取图片集合
     */
    public List<Image> selectImagesByDirId(Long dirId) {
        ImageExample imageExample = new ImageExample();
        imageExample.createCriteria().andDirIdEqualTo(dirId);
        List<Image> images = imageMapper.selectByExample(imageExample);
        return images;
    }

    @Override

    /**
     * @Param: []
     * @Return:com.deschen.myblog.modules.system.entity.Image
     * @Author: deschen
     * @Date: 2019/5/30 0:33
     * @Description: 根据文件夹id随机生成图片（无代表默认用户文件夹的图片）
     */
    public Image selectRandomImage(Long dirId) {
        if (dirId == null) {
            // 默认是用户文件夹
            dirId = BlogConstant.DEFAULT_DIRID;
        }
        ImageExample imageExample = new ImageExample();
        imageExample.createCriteria()
                .andDirIdEqualTo(dirId)
                .andStateEqualTo(BlogConstant.RECORD_VALID);
        List<Image> images =
                imageMapper.selectByExample(imageExample);
        int imageIndex = new Random().nextInt(images.size());
        return images.get(imageIndex);
    }
}
