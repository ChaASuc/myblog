package com.deschen.myblog.modules.system.service.impl;

import com.deschen.myblog.config.BlogConfig;
import com.deschen.myblog.core.constants.BlogConstant;
import com.deschen.myblog.core.enums.BlogEnum;
import com.deschen.myblog.core.exceptions.GlobalException;
import com.deschen.myblog.core.utils.IdWorker;
import com.deschen.myblog.modules.system.dto.GuestBookDto;
import com.deschen.myblog.modules.system.entity.GuestBook;
import com.deschen.myblog.modules.system.entity.GuestBookExample;
import com.deschen.myblog.modules.system.entity.Image;
import com.deschen.myblog.modules.system.entity.User;
import com.deschen.myblog.modules.system.mapper.GuestBookMapper;
import com.deschen.myblog.modules.system.mapper.ImageMapper;
import com.deschen.myblog.modules.system.mapper.UserMapper;
import com.deschen.myblog.modules.system.service.GuestBookDtoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author deschen
 * @Create 2019/6/3
 * @Description 留言板业务层实体类
 * @Since 1.0.0
 */
@Service
@Slf4j
public class GuestBookDtoServiceImpl implements GuestBookDtoService {

    @Autowired
    private GuestBookMapper guestBookMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ImageMapper imageMapper;

    @Autowired
    private BlogConfig blogConfig;

    @Override

    /**
     * @Param: [guestBook]
     * @Return:java.lang.Long
     * @Author: deschen
     * @Date: 2019/6/4 19:54
     * @Description: 添加留言
     */
    @Transactional
    public Long insertGuestBook(GuestBook guestBook) {
        long guestbookId = new IdWorker().nextId();
        guestBook.setGuestbookId(guestbookId);
        int success =
                guestBookMapper.insertSelective(guestBook);
        if (success == 0) {
            throw new GlobalException(BlogEnum.GUESTBOOK_INSERT_ERROR);
        }
        return guestbookId;
    }

    @Override

    /**
     * @Param: [guestBooks]
     * @Return:void
     * @Author: deschen
     * @Date: 2019/6/4 19:54
     * @Description: 更新留言
     */
    @Transactional
    public void updateGuestBook(List<GuestBook> guestBooks) {
        guestBooks.stream().forEach(
                guestBook -> {
                    int success = guestBookMapper.updateByPrimaryKeySelective(guestBook);
                    if (success == 0) {
                        throw new GlobalException(BlogEnum.GUESTBOOK_UPDATE_ERROR);
                    }
                }
        );
    }

    @Override

    /**
     * @Param: [state]
     * @Return:java.util.List<com.deschen.myblog.modules.system.entity.GuestBook>
     * @Author: deschen
     * @Date: 2019/6/4 19:58
     * @Description: 获取留言板
     */
    public PageInfo<GuestBookDto> selectGuestBookDto(Integer state, Integer sort, Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        GuestBookExample guestBookExample = new GuestBookExample();
        if (sort == null || sort.equals(BlogConstant.DESC)) {
            guestBookExample.setOrderByClause("UPDATE_TIME DESC");
        }else {
            guestBookExample.setOrderByClause("UPDATE_TIME ASC");
        }
        if (state != null) {
            guestBookExample.createCriteria().andStateEqualTo(state);
        }
        List<GuestBook> guestBooks =
                guestBookMapper.selectByExample(guestBookExample);
        PageInfo<GuestBook> guestBookPageInfo = new PageInfo<GuestBook>(guestBooks);
        List<GuestBookDto> guestBookDtos = guestBookPageInfo.getList().stream().map(
                guestBook -> {
                    return getGuestBookDto(guestBook);
                }
        ).collect(Collectors.toList());
        PageInfo<GuestBookDto> guestBookDtoPageInfo = new PageInfo<GuestBookDto>();
        BeanUtils.copyProperties(guestBookPageInfo, guestBookDtoPageInfo);
        guestBookDtoPageInfo.setList(guestBookDtos);
        return guestBookDtoPageInfo;
//        return guestBookDtos;
    }

    @Override

    /**
     * @Param: [guestbookId]
     * @Return:com.deschen.myblog.modules.system.entity.GuestBook
     * @Author: deschen
     * @Date: 2019/6/4 19:59
     * @Description: 更具id获取留言板dto
     */
    public GuestBookDto selectGuestBookDtoByGuestBookId(Long guestbookId) {

        GuestBook guestBook =
                guestBookMapper.selectByPrimaryKey(guestbookId);
        return getGuestBookDto(guestBook);
    }

    private GuestBookDto getGuestBookDto(GuestBook guestBook) {
        Long userId = guestBook.getUserId();
        User user =
                userMapper.selectByPrimaryKey(userId);

        Long imageId = user.getImageId();
        GuestBookDto guestBookDto =
                new GuestBookDto();
        BeanUtils.copyProperties(guestBook, guestBookDto);
        guestBookDto.setUserName(user.getUserName());
        guestBookDto.setImageUrl(BlogConstant.IMAGE_USER_URL + imageId);
        guestBookDto.setEmail(user.getEmail());
        return guestBookDto;
    }
}
