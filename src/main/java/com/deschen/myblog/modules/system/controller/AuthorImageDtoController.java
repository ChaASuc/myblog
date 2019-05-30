package com.deschen.myblog.modules.system.controller;

import com.deschen.myblog.config.ImageConfig;
import com.deschen.myblog.core.constants.BlogConstant;
import com.deschen.myblog.core.enums.BlogEnum;
import com.deschen.myblog.core.exceptions.GlobalException;
import com.deschen.myblog.core.utils.FileUtil;
import com.deschen.myblog.core.utils.IdWorker;
import com.deschen.myblog.core.utils.ResultVOUtil;
import com.deschen.myblog.modules.system.entity.Dir;
import com.deschen.myblog.modules.system.entity.Image;
import com.deschen.myblog.modules.system.service.ImageDtoService;
import com.deschen.myblog.modules.system.vo.ImageVO;
import com.deschen.myblog.modules.system.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author deschen
 * @Create 2019/5/29
 * @Description
 * @Since 1.0.0
 */
@Api(description = "图片管理")
@RestController
@RequestMapping("/author/imageDto")
@Slf4j
public class AuthorImageDtoController {

    @Autowired
    private ImageDtoService imageDtoService;

    @Autowired
    private ImageConfig imageConfig;

    @ApiOperation(value="创建文件夹", notes="已测试")
    @PostMapping("/dir")
    public ResultVO insertDir(
            @ApiParam(value = "文件夹实体类，包含dirName就行了", required = true)
            @RequestBody Dir dir
            ) {
        long dirId = new IdWorker().nextId();
        dir.setDirId(dirId);
        imageDtoService.insertDir(dir);
        ResultVO success = ResultVOUtil.success();
        return success;
    }

    @ApiOperation(value="上传图片", notes="已测试")
    @PostMapping("/image/{dirId}")
    public ResultVO insertImageToDirId(
            @ApiParam(value = "文件夹Id", required = true)
            @PathVariable Long dirId,
            @ApiParam(value = "上传文件", required = true)
            @RequestParam MultipartFile file
            ) {
        return getResultVO(file, dirId);
    }

    @ApiOperation(value="上传博客文章图片", notes="已测试")
    @PostMapping("/image")
    public ResultVO insertImage(
            @ApiParam(value = "上传文件", required = true)
            @RequestParam MultipartFile file
    ) {
        Long dirId = BlogConstant.ARTICLE_DIRID;
        return getResultVO(file, dirId);
    }

    private ResultVO getResultVO(MultipartFile file, Long dirId) {
        if (file == null || file.isEmpty()) {
            throw new GlobalException(BlogEnum.FILE_NOT_EXIST);
        }
        Dir dir = imageDtoService.selectDirByDirId(dirId);
        String uploadDir = dir.getDirUrl();
        FileUtil fileUtil = new FileUtil(uploadDir);
        String imagePath;
        try {
            imagePath = fileUtil.uploadFile(file);
        } catch (IOException e) {
            log.info("【图片上传】失败，exception = {}", e.getMessage());
            throw new GlobalException(BlogEnum.IMAGE_UPLOAD_ERROR);
        }
        Image image = new Image();
        long imageId = new IdWorker().nextId();
        image.setImageId(imageId);
        image.setImageUrl(imagePath);
        image.setDirId(dirId);
        imageDtoService.insertImages(image);
        String imageUrl = BlogConstant.IMAGEURL + image.getImageId();
        ResultVO success = ResultVOUtil.success(imageUrl);
        return success;
    }

    @ApiOperation(value="更新文件夹", notes="已测试")
    @PutMapping("/dir")
    public ResultVO updateDir(
            @ApiParam(value = "state = 0，代表删除", required = true)
            @RequestBody Dir dir
    ) {
        imageDtoService.updateDir(dir);
        if (dir.getState() == BlogConstant.RECORD_VOID) {
            imageDtoService.deleteImage(null, dir.getDirId());
        }
        ResultVO success = ResultVOUtil.success();
        return success;
    }

    @ApiOperation(value="删除图片", notes="已测试")
    @DeleteMapping("/image/{imageId}")
    public ResultVO deleteImage(
            @ApiParam(value = "state = 0，代表删除", required = true)
            @PathVariable Long imageId
    ) {
        imageDtoService.deleteImage(imageId, null);
        ResultVO success = ResultVOUtil.success();
        return success;
    }

    @ApiOperation(value="获取所有状态文件夹", notes="已测试")
    @GetMapping("/dir")
    public ResultVO getDir() {
        List<Dir> dirs = imageDtoService.selectDirs();
        ResultVO success = ResultVOUtil.success(dirs);
        return success;
    }

    @ApiOperation(value="获取文件夹下所有图片", notes="已测试")
    @GetMapping("/image/{dirId}")
    public ResultVO getDir(
            @ApiParam()
            @PathVariable(required = false) Long dirId
    ) {
        List<Image> images =
                imageDtoService.selectImagesByDirId(dirId);
        List<ImageVO> imageVOS = images.stream().map(
                image -> {
                    ImageVO imageVO = new ImageVO();
                    imageVO.setImageId(image.getImageId());
                    imageVO.setState(image.getState());
                    imageVO.setImageUrl(BlogConstant.IMAGEURL + image.getImageId());
                    return imageVO;
                }
        ).collect(Collectors.toList());
        ResultVO success = ResultVOUtil.success(imageVOS);
        return success;
    }


}
