//package com.deschen.myblog.modules.system.controller;
//
//import com.deschen.myblog.core.constants.BlogConstant;
//import com.deschen.myblog.core.enums.BlogEnum;
//import com.deschen.myblog.core.exceptions.GlobalException;
//import com.deschen.myblog.core.utils.ResultVOUtil;
//import com.deschen.myblog.modules.system.dto.ArticleDto;
//import com.deschen.myblog.modules.system.service.ArticleDtoService;
//import com.deschen.myblog.modules.system.service.CategoryDtoService;
//import com.deschen.myblog.modules.system.vo.ResultVO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.util.CollectionUtils;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @Author deschen
// * @Create 2019/7/19
// * @Description
// * @Since 1.0.0
// */
//@RestController
//@RequestMapping("/user/search")
//public class UserSearchController {
//
//
//    @Autowired
//    private ArticleDtoService articleDtoService;
//
//    @Autowired
//    private CategoryDtoService categoryDtoService;
//
//
//    @GetMapping("")
//    public ResultVO selectArticleDto(
//            @RequestParam(value = "keyword") String keyword,
//            @RequestParam(value = "flag") Integer flag
//    ) {
//        keyword = "%" + keyword + "%";
//        List<ArticleDto> articleDtos = new ArrayList<>();
//        List<Integer> states = new ArrayList<>();
//        states.add(BlogConstant.RECORD_VALID);
//        if (flag.equals(0)) {
//            // 代表种类搜索文章
//            List<Long> categoryIds =
//                    categoryDtoService.selectCategoryIdLikeCatetoryKeyWord(keyword, BlogConstant.RECORD_VALID);
//            if (CollectionUtils.isEmpty(categoryIds)) {
//                return ResultVOUtil.success();
//            }
//            articleDtos = articleDtoService.selectArticleDtoByCategoryIdsAndState(
//                    categoryIds, states);
//        } else if (flag.equals(1)) {
//            // 代表标签搜索
//            List<Long> categoryIds =
//                    categoryDtoService.selectCategoryIdLikeTagKeyWord(keyword, BlogConstant.RECORD_VALID);
//            if (CollectionUtils.isEmpty(categoryIds)) {
//                return ResultVOUtil.success();
//            }
//            articleDtos = articleDtoService.selectArticleDtoByCategoryIdsAndState(
//                    categoryIds, states
//            );
//        } else if (flag.equals(2)) {
//            // 代表文章内容搜索
//            articleDtos = articleDtoService.selectArticleDtoByArticleTitleOrContentKeyWord(
//                    keyword, states
//            );
//        } else {
//            throw new GlobalException(BlogEnum.PARROR_ERROR);
//        }
//
//        ResultVO success = ResultVOUtil.success(articleDtos);
//        return success;
//    }
//
//}
