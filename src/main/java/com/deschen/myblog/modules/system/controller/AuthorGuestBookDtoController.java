package com.deschen.myblog.modules.system.controller;

import com.deschen.myblog.core.constants.BlogConstant;
import com.deschen.myblog.core.enums.BlogEnum;
import com.deschen.myblog.core.exceptions.GlobalException;
import com.deschen.myblog.core.utils.ResultVOUtil;
import com.deschen.myblog.modules.system.dto.GuestBookDto;
import com.deschen.myblog.modules.system.dto.UserDto;
import com.deschen.myblog.modules.system.entity.GuestBook;
import com.deschen.myblog.modules.system.service.GuestBookDtoService;
import com.deschen.myblog.modules.system.service.ImageDtoService;
import com.deschen.myblog.modules.system.service.UserDtoService;
import com.deschen.myblog.modules.system.vo.ResultVO;
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


    @ApiOperation(value = "创建留言 ", notes = "已测试")
    @PostMapping("")
    public ResultVO insertGuestBookDto(
            @RequestBody GuestBook guestBook
            ) {
        if (guestBook.getUserId() == null || guestBook.getGuestbookContent() == null) {
            throw new GlobalException(BlogEnum.PARROR_EMPTY_ERROR.getCode(), "用户id不为空或留言内容不为空");
        }
        Long userId = guestBook.getUserId();
        Long guestBookId = guestBookDtoService.insertGuestBook(guestBook);
        GuestBook guestBook1 =
                guestBookDtoService.selectGuestBookDtoByGuestBookId(guestBookId);
        GuestBookDto guestBookDto = new GuestBookDto();
        BeanUtils.copyProperties(guestBook1, guestBookDto);

        UserDto userDto = userDtoService.selectUserDto(userId);
        guestBookDto.setUserName(userDto.getUserName());
        guestBookDto.setEmail(userDto.getEmail());
        guestBookDto.setImageUrl(userDto.getImageUrl());

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
            @RequestParam(required = false) Integer state,
            @RequestParam(defaultValue = "1" ,required = false) Integer sort
    ) {
        List<GuestBook> guestBooks =
                guestBookDtoService.selectGuestBookDto(state, sort);
        List<GuestBookDto> guestBookDtos = guestBooks.stream().map(
                guestBook -> {
                    GuestBookDto guestBookDto = new GuestBookDto();
                    BeanUtils.copyProperties(guestBook, guestBookDto);
                    UserDto userDto =
                            userDtoService.selectUserDto(guestBook.getUserId());
                    guestBookDto.setUserName(userDto.getUserName());
                    guestBookDto.setImageUrl(userDto.getImageUrl());
                    guestBookDto.setEmail(userDto.getEmail());
                    return guestBookDto;
                }
        ).collect(Collectors.toList());
        ResultVO success = ResultVOUtil.success(guestBookDtos);
        return success;
    }


}
