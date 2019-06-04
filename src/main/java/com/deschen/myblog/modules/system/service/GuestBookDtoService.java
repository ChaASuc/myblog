package com.deschen.myblog.modules.system.service;

import com.deschen.myblog.modules.system.dto.GuestBookDto;
import com.deschen.myblog.modules.system.entity.GuestBook;

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

    List<GuestBook> selectGuestBookDto(Integer state, Integer sort);

    GuestBook selectGuestBookDtoByGuestBookId(Long guestbookId);
}
