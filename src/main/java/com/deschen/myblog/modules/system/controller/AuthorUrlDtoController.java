package com.deschen.myblog.modules.system.controller;

import com.deschen.myblog.config.BlogConfig;
import com.deschen.myblog.core.constants.BlogConstant;
import com.deschen.myblog.core.enums.BlogEnum;
import com.deschen.myblog.core.exceptions.GlobalException;
import com.deschen.myblog.core.utils.FileUtil;
import com.deschen.myblog.core.utils.ResultVOUtil;
import com.deschen.myblog.modules.system.dto.UrlDto;
import com.deschen.myblog.modules.system.entity.Dir;
import com.deschen.myblog.modules.system.entity.Image;
import com.deschen.myblog.modules.system.entity.Url;
import com.deschen.myblog.modules.system.service.ImageDtoService;
import com.deschen.myblog.modules.system.service.UrlDtoService;
import com.deschen.myblog.modules.system.vo.ImageVO;
import com.deschen.myblog.modules.system.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author deschen
 * @Create 2019/6/5
 * @Description
 * @Since 1.0.0
 */
@RestController
@RequestMapping("/author/urlDto")
@Slf4j
@Api(description = "链接模块")
public class AuthorUrlDtoController {

    @Autowired
    private UrlDtoService urlDtoService;

    @Autowired
    private ImageDtoService imageDtoService;

    @Autowired
    private BlogConfig blogConfig;

    @ApiOperation(value = "上传链接的图片", notes = "已测试")
    @PostMapping("/image")
    public ResultVO uploadImage(
            @RequestParam(required = false) Long dirId,
            @RequestParam(value = "file")MultipartFile file
            ) {

        if (dirId == null) {
            dirId = BlogConstant.ARTICLE_DIRID;
        }
        Dir dir =
                imageDtoService.selectDirByDirId(dirId);
        FileUtil fileUtil =
                new FileUtil(dir.getDirUrl());
        String filePath = null;
        try {
            filePath = fileUtil.uploadFile(file);
            log.info("【链接模块】图片成功");
        } catch (IOException e) {
            throw new GlobalException(BlogEnum.FILE_UPLOAD_ERROR);
        }

        Image image = new Image();
        image.setImageUrl(filePath);
        image.setDirId(dir.getDirId());
        Long imageId = imageDtoService.insertImages(image);

        ImageVO imageVO = new ImageVO();
        imageVO.setImageId(imageId);
        imageVO.setImageUrl(BlogConstant.IMAGE_USER_URL + imageId);
        imageVO.setState(BlogConstant.RECORD_VALID);
        ResultVO success = ResultVOUtil.success(imageVO);
        return success;
    }

    @ApiOperation(value = "添加链接", notes = "已测试")
    @PostMapping("")
    public ResultVO insertUrl(
            @RequestBody Url url
            ) {
        Long urlId = urlDtoService.insertUrl(url);
        Url url1 =
                urlDtoService.selectUrlById(urlId);
        UrlDto urlDto = new UrlDto();
        BeanUtils.copyProperties(url1, urlDto);
        String imageUrl = BlogConstant.IMAGE_USER_URL + url1.getImageId();
        urlDto.setImageUrl(imageUrl);
        ResultVO success = ResultVOUtil.success(urlDto);
        return success;
    }

    @ApiOperation(value = "更新链接", notes = "已测试")
    @PutMapping("")
    public ResultVO updateUrl(
            @RequestBody List<Url> urls
    ) {
        urlDtoService.updateUrl(urls);
        ResultVO success = ResultVOUtil.success();
        return success;
    }

    @ApiOperation(value = "获取链接", notes = "已测试")
    @GetMapping("")
    public ResultVO selectUrlDto(
            @RequestParam(required = false) Integer state,
            @RequestParam(defaultValue = "1" ,required = false) Integer sort
    ) {
        List<Url> urls =
                urlDtoService.selectUrlDto(state, sort);

        List<UrlDto> urlDtos = urls.stream().map(
                url -> {
                    UrlDto urlDto = new UrlDto();
                    BeanUtils.copyProperties(url, urlDto);
                    urlDto.setImageUrl(BlogConstant.IMAGE_USER_URL + url.getImageId());
                    return urlDto;
                }
        ).collect(Collectors.toList());

        ResultVO success = ResultVOUtil.success(urlDtos);
        return success;
    }


}
