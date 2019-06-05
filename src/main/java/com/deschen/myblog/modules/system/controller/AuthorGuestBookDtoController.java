package com.deschen.myblog.modules.system.controller;

import com.deschen.myblog.config.BlogConfig;
import com.deschen.myblog.core.constants.BlogConstant;
import com.deschen.myblog.core.enums.BlogEnum;
import com.deschen.myblog.core.exceptions.GlobalException;
import com.deschen.myblog.core.utils.ResultVOUtil;
import com.deschen.myblog.modules.system.dto.GuestBookDto;
import com.deschen.myblog.modules.system.dto.UserDto;
import com.deschen.myblog.modules.system.entity.GuestBook;
import com.deschen.myblog.modules.system.entity.User;
import com.deschen.myblog.modules.system.service.GuestBookDtoService;
import com.deschen.myblog.modules.system.service.ImageDtoService;
import com.deschen.myblog.modules.system.service.UserDtoService;
import com.deschen.myblog.modules.system.vo.ResultVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.javafx.iio.ImageStorage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author deschen
 * @Create 2019/6/4
 * @Description
 * @Since 1.0.0
 */
@RestController
@RequestMapping("/author/guestbookDto")
@Slf4j
@Api(description = "留言板模块")
public class AuthorGuestBookDtoController {

    @Autowired
    private GuestBookDtoService guestBookDtoService;

    @Autowired
    private UserDtoService userDtoService;

    @Autowired
    private ImageDtoService imageDtoService;

    @Autowired
    private BlogConfig blogConfig;


    @ApiOperation(value = "创建留言 ", notes = "已测试")
    @PostMapping("")
    public ResultVO insertGuestBookDto(
            @RequestBody GuestBook guestBook
            ) {
        if (guestBook.getUserId() == null || guestBook.getGuestbookContent() == null) {
            throw new GlobalException(BlogEnum.PARROR_EMPTY_ERROR.getCode(), "用户id不为空或留言内容不为空");
        }
        Long guestBookId = guestBookDtoService.insertGuestBook(guestBook);
        GuestBookDto guestBookDto =
                guestBookDtoService.selectGuestBookDtoByGuestBookId(guestBookId);

        ResultVO success = ResultVOUtil.success(guestBookDto);
        return success;
    }

    @ApiOperation(value = "批量更新留言 ", notes = "已测试")
    @PutMapping("")
    public ResultVO updateGuestBook(
            @RequestBody List<GuestBook> guestBooks
    ) {
        guestBookDtoService.updateGuestBook(guestBooks);
        ResultVO success = ResultVOUtil.success();
        return success;
    }

    @ApiOperation(value = "获取留言", notes = "已测试")
    @GetMapping("")
    public ResultVO selectGuestBookDto(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(required = false) Integer state,
            @RequestParam(defaultValue = "1" ,required = false) Integer sort
    ) {

        Integer gPageSize = blogConfig.getGPageSize();
        PageInfo<GuestBookDto> guestBookDtoPageInfo =
                guestBookDtoService.selectGuestBookDto(state, sort, pageNum, gPageSize);
        ResultVO success = ResultVOUtil.success(guestBookDtoPageInfo);
        return success;
    }


}
