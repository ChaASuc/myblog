package com.deschen.myblog.modules.system.service;

import com.deschen.myblog.modules.system.dto.GuestBookDto;
import com.deschen.myblog.modules.system.entity.GuestBook;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Author deschen
 * @Create 2019/6/3
 * @Description 留言板业务层接口
 * @Since 1.0.0
 */
public interface GuestBookDtoService {

    Long insertGuestBook(GuestBook guestBook);

    void updateGuestBook(List<GuestBook> guestBooks);

    PageInfo<GuestBookDto> selectGuestBookDto(Integer state, Integer sort, Integer pageNum, Integer pageSize);

    GuestBookDto selectGuestBookDtoByGuestBookId(Long guestbookId);
}
