# 个人博客

## 图片管理
### POST /author/imageDto/dir  创建文件夹
`{
   "dirName": "用户图片"
 }
 `

### POST /author/imageDto/image/{dirId}  上传文件夹图片
`
dirId: 1133556052961378304
file: 
`

### PUT /author/imageDto/dir  更新文件夹
`
更新文件夹名字
{
  "dirId": 1133556052961378304,
  "dirName": "个人图片"
}
`
`
删除文件夹，包含所属图片
{
  "dirId": 1133556052961378304,
  "state": 0
}
`
### DELETE /author/imageDto/image/{imageId} 删除图片
`
imageId: 1133557151588007936
`
### GET /user/image/{imageId} 显示图片
`
imageId: 1133557151588007936
`
### GET /author/imageDto/dir 获取所有状态文件夹

### GET /author/imageDto/image/{dirId} 根据文件夹id获取所有图片
`
dirId: 1133556052961378304
`

## 用户模块
### GET /user/categoryDto 种类及标签 有效的

### GET /user/articleDto 根据种类id，标签id或种类id和标签id对文章进行排序（默认热门）
`
categoryId: 1 // 无代表所有种类 有值根据种类id查所有文章（同上）种类热度hot加1
tagId: 1   // 种类无，标签无代表所有标签  // 种类有，标签无，代表种类下的所有标签  // 有值根据标签id查所有文章（同上） 标签热度hot加1
sort: // 无参数 代表所有文章（sort: 1 热门排序， 2 最新排序 3 评论数）
pageNum: // 无，默认从第一页开始
`
`
categoryId: 1
tagId: 1种类id和标签id对文章(同上)种类标签hot加1
`
### GET /user/article/{articleId} 文章id获取文章实体类(60秒ip访问浏览量+1)
`
articleId: 1133358725193539584
ip: 12
`
### POST /user/thumbup/{articleId} 给文章id点赞，第一次加赞，第二次取消赞，第三次加赞类推
`
ip: 145
articleId: 1133358725193539584
`
### GET /user/review/{articleId} 获取文章评论（有效的）
`
articleId: 1133937524369010688
sort: (无和0 代表最新排序 1最晚排序)
`
### POST /user/review  添加评论或回复 
`
创建评论
{
  "articleId": 1133937524369010688,  // 必填
  "email": "123@qq.com", //选填 后台有校验
  "reviewContent": "xxxxxx",  // 必填
  "reviewUrl": "string",  //选填
  "userName": "张三"  // 必填
}
`
`
对评论回复
{
  "email": "123@qq.com", // 选填
  "reviewContent": "xxxxxx", // 必填
  "reviewUrl": "string", // 选填
  "userName": "张三", // 必填
  "reviewParent": 1134079401529561088 // 必填 回复评论的id
  "articleId": 1133937524369010688  // 必填
}
`
### GET /user/authorInfo 获取博主信息（默认博主为userId =1的用户，需要修改博主联系我）

## 种类和标签模块
### POST /author/categoryDto/categoriea 批量创建种类
`
注意点：第一个花括号的里面参数必须等于或多于其他花括号，否则会有bug
这种对
[
  {
    "categoryName": "前端",
    "state": 1（无和1 有效，0无效）  
  },
  {
    "categoryName": "后端",
    "state": 1  
  },
  {
      "categoryName": "数据库",
      "state": 1  
    }
]
这种错误
[
  {
    "categoryName": "前端",  参数少于后面
  },
  {
    "categoryName": "后端",
    "state": 1  
  },
  {
      "categoryName": "数据库",
      "state": 1  
    }
]
`
### POST /author/categoryDto/tags 批量插入标签
`
数据格式跟批量插入种类一致
[
  {
    "categoryId": 1133693019967582208,
    "state": 1,
    "tagName": "vue.js"
  },
  {
    "categoryId": 1133693019967582208,
    "state": 1,
    "tagName": "node.js"
  },
  {
    "categoryId": 1133693021389451264,
    "state": 1,
    "tagName": "spring"
  }
]
`
### PUT /author/categoryDto  批量更新种类及标签
`
[
  {
    "categoryId": 1133693019967582208,
    "categoryName": "项目笔记",
    "state": 1,  // 为0是代表失效，相应文章失效，标签失效
    "tags": [
      {
        "state": 1,  // 为0是代表失效，与文章的关系失效
        "tagId": 1133695831803817984,
        "tagName": "spring笔记"
      },
     {
        "state": 1,
        "tagId": 1133695831803817984,
        "tagName": "微服务笔记"
      }
    ]
  },
  {
    "categoryId": 1133693021389451264,
    "categoryName": "项目心得",
    "state": 1,
    "tags": [
      {
        "state": 1,
        "tagId": 1133695834643361792,
        "tagName": "vue.js的心得"
      }
    ]
  }
]
`
### GET /author/categoryDto/category 获取状态相应的种类
`
state: 1 (无代表全部， 1有效 2 无效)
sort：（无热门 1热门 2最新）
`
### GET /author/categoryDto/tag 获取种类下的标签
`
categoryId:1133693021389451264
state: 1 (无代表全部， 1有效 2 无效)
sort：（无热门 1热门 2最新）
`

### GET /author/categoryDto 获取所有种类和标签
`
state: 1 (无代表全部， 1有效 2 无效)
sort：（无热门 1热门 2最新）
`

## 文章模块

### GET /author/articleDto 根据状态吗，排序规则，页码获取文章标签
`
"states": 集合 (无或1 代表有效 0无效 2 草稿)
"sort'：（无热门 1热门 2最新 3评论数）
"pageNum": (无代表1 )
`

### GET /author/articleDto/{articleId} 根据文章id获取文章
`
articleId: 1133937524369010688
` 
### POST /author/articleDto 创建文章
`
{
  "articleContent": "string",  
  "articleHtml": "string",
  "articleTitle": "火影",
  "categoryId": 1133936971316473856,
  "state": 1,  // 可有可无(无和2是草稿，1是有效， 0是无效)
  "tagForms": [
    {
      "tagId": 1133937262048849921
    }
  ]
}
`
### PUT /author/articleDto 更新文章

`
更新文章、种类和标签
{
  "articleContent": "string", //可有可无
  "articleHtml": "string", //可有可无
  "articleId": 1133937524369010688, // 必须有
  "articleTitle": "string", //可有可无
  "state": 1,  // 可有可无 (2是草稿，1是有效， 0是无效）
  "categoryId": 1133935877207216100,  //新的种类 可有可无
  "tagForms": [   // categoryId 有的话，这个必须有，新的标签集合
      {
        "tagId": 1133990004901670912  
      }
    ]
}
`
`
更新文章和标签
{
  "articleContent": "string", //可有可无
  "articleHtml": "string", //可有可无
  "articleId": 1133937524369010688, // 必须有
  "articleTitle": "string", //可有可无
  "state": 1,  // 可有可无 (2是草稿，1是有效， 0是无效）
  "tagForms": [
    {
      "newTagId": 1133937262048849921,  // 更新的标签
      "tagId": 1133937262048849920  // 原标签
    }
  ]
}
`
### GET /author/articleDto/{articleId} 点击文章
`
articleId: 1133937524369010688
`
## 评论模块
### POST /author/review 创建评论或回复(reviewParent为null是评论，非空为回复)
`
创建评论
{
  "articleId": 1134055451403509760,  // 必填
  "reviewContent": "string", //必填内容
  "reviewUrl": "string",  //选填
  "state": 1,  //选填（0无效 1有效）
  "userId": 1  //必填（博主id）
}
`
`
对评论回复
{
  "articleId": 1134055451403509760,  // 必填
  "reviewContent": "string", //必填内容
  "reviewParent": 1134081330242506752,  //回复该评论的id
  "reviewUrl": "string",  //选填
  "state": 1,  //选填（0无效 1有效）
  "userId": 1  //必填（博主id）
}
`
### GET /author/review/{articleId} 根据文章id和状态吗获取评论和回复 (reviewParent无值为评论，有值为回复)
`
articleId: 1133937524369010688
state: (无代表所有状态，1 有效 2 无效)
sort: (无和0 代表最新排序 1最晚排序)
`
### PUT /author/review/{reviewId} 修改评论或回复
`
reviewId: 1134079401529561088 // 评论id或回复id
state: (0无效 1有效)  // 评论id为无效，相应的回复会无效
`

## 用户信息模块
### GET /author/userDto 登入
`
userName: admin
userPassword: 123456
`

### POST /author/userDto 添加用户（用于创建博主信息，用该用户为个人博客博主的话联系我）
`
{
  "email": // 选填
  "userName": "王五",  //必填
  "userPassword": "123456"  //必填
}
`

### PUT /author/userDto 更新用户信息（用于修改博主信息，或评论和回复的普通用户删除，删除后的评论回复不会删掉）
`
{
  "email":  // 选填
  "imageId": 0, // 选填
  "userId": 1,  // 必填
  "state": 0,   // 选填 （0无效 1有效）
  "userName": "string",  // 选填
  "userPassword": "string",  // 选填
  "userSignature": "string"  // 选填
}
`
### GET /author/userDto/{userId} 获取个人信息
`
userId: 1
`
