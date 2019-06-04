package com.deschen.myblog.modules.system.controller;

import com.deschen.myblog.core.constants.BlogConstant;
import com.deschen.myblog.core.enums.BlogEnum;
import com.deschen.myblog.core.exceptions.GlobalException;
import com.deschen.myblog.core.utils.EmailUtil;
import com.deschen.myblog.core.utils.IdWorker;
import com.deschen.myblog.core.utils.ResultVOUtil;
import com.deschen.myblog.modules.system.dto.UserDto;
import com.deschen.myblog.modules.system.entity.Image;
import com.deschen.myblog.modules.system.entity.User;
import com.deschen.myblog.modules.system.entity.UserConfig;
import com.deschen.myblog.modules.system.form.AuthorInfoForm;
import com.deschen.myblog.modules.system.form.UserForm;
import com.deschen.myblog.modules.system.form.UserUpdateForm;
import com.deschen.myblog.modules.system.service.ImageDtoService;
import com.deschen.myblog.modules.system.service.UserDtoService;
import com.deschen.myblog.modules.system.vo.ResultVO;
import com.sun.webkit.graphics.WCGlyphBuffer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElementDecl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author deschen
 * @Create 2019/5/31
 * @Description
 * @Since 1.0.0
 */
@RestController
@Slf4j
@RequestMapping("/author/userDto")
@Api(description = "用户信息模块")
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
        if (bindingResult.hasErrors()) {
            log.info("【添加用户】 参数错误");
            throw new GlobalException(BlogEnum.PARROR_EMPTY_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        if (userForm.getEmail() != null) {
            String email = userForm.getEmail().trim();
            if (!email.equals("") && !EmailUtil.checkEmaile(email)) {
                log.info("【添加用户】邮箱校验失败，email = {}", email);
                throw new GlobalException(BlogEnum.PARROR_EMPTY_ERROR.getCode(),
                        "邮箱格式错误");
            }
        }

        User user = new User();
        user.setUserName(userForm.getUserName());
        user.setSalt(user.getUserName());
        user.setUserPassword(userForm.getUserPassword());
        Image image =
                imageDtoService.selectRandomImage(null);
        user.setImageId(image.getImageId());
        Long userId = userDtoService.insertUser(user);
        userDtoService.insertUserConfig(userId);
        ResultVO success = ResultVOUtil.success();
        return success;
    }

    @ApiOperation(value = "更新用户", notes = "已测试")
    @PutMapping("")
    public ResultVO updateUser(
            @RequestBody UserUpdateForm form
    ) {
        if (form.getUserId() == null) {
            log.info("【修改用户】 参数错误");
            throw new GlobalException(BlogEnum.PARROR_EMPTY_ERROR.getCode(),
                    "用户名id不为空");
        }

        if (form.getEmail() != null) {

            String email = form.getEmail();
            if (!email.trim().equals("") && !EmailUtil.checkEmaile(email)) {
                log.info("【添加评论】邮箱校验失败，email = {}", email);
                throw new GlobalException(BlogEnum.PARROR_EMPTY_ERROR.getCode(),
                        "邮箱格式错误");
            }
        }

        User user = new User();
        BeanUtils.copyProperties(form, user);
        userDtoService.updateUser(user);
        ResultVO success = ResultVOUtil.success();
        return success;
    }

    @ApiOperation(value = "查看用户信息", notes = "已测试")
    @GetMapping("/{userId}")
    public ResultVO selectUserByUserId(
            @PathVariable Long userId
    ) {
        UserDto userDto =
                userDtoService.selectUserDto(userId);
        ResultVO success = ResultVOUtil.success(userDto);
        return success;
    }

    @ApiOperation(value = "登入功能", notes = "已测试")
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

    @ApiOperation(value = "根据用户信息获取统计信息", notes = "已测试")
    @GetMapping("/authorInfo/{userId}")
    public ResultVO getAuthorInfoByUserId(
            @PathVariable Long userId) {
        List<UserConfig> userConfigsList = new ArrayList<>();
        Date date = new Date();
        getUserConfig(userId, date, BlogConstant.WEEK, userConfigsList);
        getUserConfig(userId, date, BlogConstant.MONTH, userConfigsList);
        getUserConfig(userId, date, BlogConstant.YEAR, userConfigsList);
        getUserConfig(userId, null, null, userConfigsList);
        ResultVO success = ResultVOUtil.success(userConfigsList);
        return success;
    }

    @ApiOperation(value = "更具用户id，时间，排序，筛选条件获取统计信息", notes = "已测试")
    @GetMapping("/authorInfo")
    public ResultVO getAuhorInfo(
            @RequestParam  Long userId,
            @RequestParam(required = false) Date date,
            @RequestParam(required = false) Integer sort,
            @RequestParam(required = false) Integer condition
    ) {
        if (userId == null) {
            throw new GlobalException(BlogEnum.PARROR_EMPTY_ERROR.getCode(),
                    "用户id不为空");
        }

        if (sort == null) {
            // 默认以创建时间升序
            sort = BlogConstant.ASC;
        } else if (sort < BlogConstant.ASC || sort > BlogConstant.DESC) {
            throw new GlobalException(BlogEnum.PARROR_EMPTY_ERROR.getCode(),
                    "排序规则必须在0和1之间");
        }

        if (condition != null && (condition < 0 && condition > 2)) {
            throw new GlobalException(BlogEnum.PARROR_EMPTY_ERROR.getCode(),
                    "条件规则必须在0到2之间");
        }

        List<UserConfig> userConfigs = new ArrayList<>();
        if ((condition == null && date == null) || (condition != null && date != null)) {
            // 获取全部记录或 规定时间的本周本月本年记录
            // todo 如果程序停用统计记录断层或没数据，如何解决
           userConfigs = userDtoService.selectUserConfigs(userId, date, sort, condition);
        } else if (condition != null && date == null) {
            // 获取当前时间的本周本月本年记录
            date = new Date();
            userConfigs = userDtoService.selectUserConfigs(userId, date, sort, condition);
        }else {
            throw new GlobalException(BlogEnum.PARROR_EMPTY_ERROR.getCode(),
                    "条件必须存在");
        }

        ResultVO success = ResultVOUtil.success(userConfigs);
        log.info("【统计量】date={}, sort={}, condition={}, userConfigSize={}",
                date, sort, condition, userConfigs.size());
        return success;
    }


    private void getUserConfig(Long userId, Date date, Integer condition, List<UserConfig> userConfigList) {
        List<UserConfig> userConfigs =
                userDtoService.selectUserConfigs(userId, date, BlogConstant.DESC, condition);
        UserConfig userConfig = new UserConfig();
        userConfig.setArticleSum(0);
        userConfig.setCommentSum(0);
        userConfig.setThumbupSum(0);
        userConfig.setVisitSum(0);
        if (userConfigs != null && userConfigs.size() != 0) {
            // 最新的记录
            userConfig = userConfigs.get(0);
        }
//        }else {
//            // 如果没有记录，就用目前最新的记录代替
//            List<UserConfig> userConfigNews =
//                    userDtoService.selectUserConfigs(userId, null, BlogConstant.DESC, null);
//            if (userConfigNews == null || userConfigNews.size() == 0) {
//                throw new GlobalException(BlogEnum.USER_CONFIG_NOT_EXIST);
//            }
//            userConfig = userConfigNews.get(0);
//        }
        userConfigList.add(userConfig);
    }

    public static void main(String[] args) {
        System.out.println(new Date());
    }
}
