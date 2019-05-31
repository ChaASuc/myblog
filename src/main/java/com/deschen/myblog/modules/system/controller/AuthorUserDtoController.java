package com.deschen.myblog.modules.system.controller;

import com.deschen.myblog.core.enums.BlogEnum;
import com.deschen.myblog.core.exceptions.GlobalException;
import com.deschen.myblog.core.utils.EmailUtil;
import com.deschen.myblog.core.utils.IdWorker;
import com.deschen.myblog.core.utils.ResultVOUtil;
import com.deschen.myblog.modules.system.dto.UserDto;
import com.deschen.myblog.modules.system.entity.Image;
import com.deschen.myblog.modules.system.entity.User;
import com.deschen.myblog.modules.system.form.UserForm;
import com.deschen.myblog.modules.system.form.UserUpdateForm;
import com.deschen.myblog.modules.system.service.ImageDtoService;
import com.deschen.myblog.modules.system.service.UserDtoService;
import com.deschen.myblog.modules.system.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author deschen
 * @Create 2019/5/31
 * @Description
 * @Since 1.0.0
 */
@RestController
@Slf4j
@RequestMapping("/author/userDto")
@Api("用户信息模块")
public class AuthorUserDtoController {

    @Autowired
    private UserDtoService userDtoService;

    @Autowired
    private ImageDtoService imageDtoService;

    @ApiOperation(value = "创建用户", notes = "已测试")
    @PostMapping("")
    public ResultVO insertUserDto(
            @Valid @RequestBody UserForm userForm,
            BindingResult bindingResult
    ) {
        if (userForm.getEmail() != null && !EmailUtil.checkEmaile(userForm.getEmail())) {
            log.info("【添加用户】邮箱校验失败，email = {}", userForm.getEmail());
            throw new GlobalException(BlogEnum.PARROR_EMPTY_ERROR.getCode(),
                    "邮箱格式错误");
        }
        if (bindingResult.hasErrors()) {
            log.info("【添加用户】 参数错误");
            throw new GlobalException(BlogEnum.PARROR_EMPTY_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        User user = new User();
        long userId = new IdWorker().nextId();
        user.setUserId(userId);
        user.setUserName(userForm.getUserName());
        user.setSalt(user.getUserName());
        user.setUserPassword(userForm.getUserPassword());
        Image image =
                imageDtoService.selectRandomImage(null);
        user.setImageId(image.getImageId());
        userDtoService.insertUser(user);
        userDtoService.insertUserConfig(userId);
        ResultVO success = ResultVOUtil.success();
        return success;
    }

    @PutMapping("")
    public ResultVO updateUser(
            @RequestBody UserUpdateForm form
    ) {
        if (form.getUserId() == null) {
            log.info("【修改用户】 参数错误");
            throw new GlobalException(BlogEnum.PARROR_EMPTY_ERROR.getCode(),
                    "用户名id不为空");
        }

        if (form.getEmail() != null && !EmailUtil.checkEmaile(form.getEmail())) {
            log.info("【添加评论】邮箱校验失败，email = {}", form.getEmail());
            throw new GlobalException(BlogEnum.PARROR_EMPTY_ERROR.getCode(),
                    "邮箱格式错误");
        }

        User user = new User();
        BeanUtils.copyProperties(form, user);
        userDtoService.updateUser(user);
        ResultVO success = ResultVOUtil.success();
        return success;
    }

    @GetMapping("/{userId}")
    public ResultVO selectUserByUserId(
            @PathVariable Long userId
    ) {
        UserDto userDto =
                userDtoService.selectUserDto(userId);
        ResultVO success = ResultVOUtil.success(userDto);
        return success;
    }

    @GetMapping("")
    public ResultVO selectUserDto(
            @RequestParam String userName,
            @RequestParam String userPassword
    ) {
        if (userName.trim().equals("") || userPassword.trim().equals("")) {
            throw new GlobalException(BlogEnum.PARROR_EMPTY_ERROR.getCode(),
                    "用户名或密码不为空");
        }
        userDtoService.checkUserByUserNameAndUserPasswordAndSalt(
                userName, userPassword, userName
        );
        ResultVO success = ResultVOUtil.success();
        return success;
    }
}
