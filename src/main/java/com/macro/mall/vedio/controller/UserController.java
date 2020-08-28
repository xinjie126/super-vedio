package com.macro.mall.vedio.controller;

import com.macro.mall.vedio.ApiResponse;
import com.macro.mall.vedio.mapper.UgcMapper;
import com.macro.mall.vedio.mapper.UserMapper;
import com.macro.mall.vedio.table.TableUser;
import com.macro.mall.vedio.table.User;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/user")
@Api(value = "用户信息接口")
public class UserController {
    @Resource
    private UserMapper userMapper;

    @Resource
    UgcMapper ugcMapper;

    @RequestMapping(value = "queryAll", method = RequestMethod.GET)
    @JsonView(value = TableUser.class)
    public ApiResponse queryAll() {
        ApiResponse response = new ApiResponse();
        response.setData("ddddd");
        return response;
    }

    @RequestMapping(value = "query", method = RequestMethod.GET)
    @ApiOperation(value = "查询用户", notes = "根据id来查询用户")
    @JsonView(value = TableUser.class)
    public ApiResponse query(@RequestParam(value = "userId") long userId) {
        ApiResponse response = new ApiResponse();
        TableUser user = userMapper.queryUser(userId);
        if (user != null) {
            response.setData(user);
        } else {
            response.setStatus(ApiResponse.STATUS_FAILED);
            response.setData(null);
        }

        return response;
    }


    @RequestMapping(value = "relation", method = RequestMethod.GET)
    @ApiOperation(value = "查询两个用户的关系", notes = "根据id来查询用户")
    @JsonView(value = TableUser.class)
    public ApiResponse relation(@RequestParam(value = "authorId") long authorId, @RequestParam("userId") long userId) {
        ApiResponse response = new ApiResponse();
        TableUser user = userMapper.queryUser(authorId);
        try {
            user.hasFollow = (boolean) ugcMapper.isUserFollow(userId, authorId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setData(user);
        return response;
    }


    @RequestMapping(value = "delete", method = RequestMethod.GET)
    @ApiOperation(value = "删除用户", notes = "根据id来删除用户")
    @JsonView(value = Boolean.class)
    public ApiResponse delete(@RequestParam(value = "userId") long userId) {
        ApiResponse response = new ApiResponse();
        int result = userMapper.delete(userId);
        response.setData(result >= 1);
        return response;
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ApiOperation(value = "更新用户信息", notes = "根据id来更新用户信息")
    @JsonView(value = Boolean.class)
    public ApiResponse update(@RequestParam @Valid TableUser user, BindingResult binding) {
        ApiResponse response = new ApiResponse();
        int result = userMapper.update(user);
        response.setData(result >= 1);
        return response;

    }

    @RequestMapping(value = "insert", method = RequestMethod.GET)
    @ApiOperation(value = "插入新的用户", notes = "插入新的用户")
    @JsonView(User.class)
    @ResponseBody
    public ApiResponse insert(@RequestParam(value = "qqOpenId", required = true) String qqOpenId,
                              @RequestParam(value = "name", required = true) String name,
                              @RequestParam(value = "avatar", required = true) String avatar,
                              @RequestParam(value = "description", required = false, defaultValue = "") String description,
                              @RequestParam(value = "likeCount", required = false, defaultValue = "0") Integer likeCount,
                              @RequestParam(value = "topCommentCount", required = false, defaultValue = "0") Integer topCommentCount,
                              @RequestParam(value = "followCount", required = false, defaultValue = "0") Integer followCount,
                              @RequestParam(value = "expiresTime", required = true) long expires_time) {
        TableUser exitOne = userMapper.queryUserByQQOpenId(qqOpenId);
        if (exitOne == null) {
            exitOne = new TableUser();
            exitOne.userId = System.currentTimeMillis() / 1000;
        }
        exitOne.qqOpenId = qqOpenId;
        exitOne.name = name;
        exitOne.avatar = avatar;
        if (!StringUtils.isEmpty(description)) {
            exitOne.description = description;
        }

        if (topCommentCount > 0) {
            exitOne.topCommentCount = topCommentCount;
        }

        if (likeCount > 0) {
            exitOne.likeCount = likeCount;
        }

        if (followCount > 0) {
            exitOne.followerCount = followCount;
        }

        if (expires_time > 0) {
            exitOne.expiresTime = expires_time;
        }

        userMapper.insertUser(exitOne);
        TableUser tableUser = userMapper.queryUser(exitOne.userId);
        ApiResponse response = new ApiResponse();
        response.setData(tableUser);
        return response;
    }


    @RequestMapping(value = "queryFans", method = RequestMethod.GET)
    @ApiOperation(value = "查询粉丝列表", notes = "查询粉丝列表")
    @JsonView(User.class)
    public ApiResponse<List<User>> queryFans(@RequestParam(value = "userId", defaultValue = "0") Long userId,
                                             @RequestParam(value = "page", defaultValue = "0") Integer page,
                                             @RequestParam(value = "pageCount", defaultValue = "10", required = false) Integer pageCount) {
        ApiResponse response = new ApiResponse();
        if (userId == 0) {
            response.setData("data", null);
            return response;
        }
        List<User> users = userMapper.queryFans(userId, pageCount * page, pageCount);
        response.setData("data", users);
        return response;
    }


    @RequestMapping(value = "queryFollows", method = RequestMethod.GET)
    @ApiOperation(value = "查询关注列表", notes = "查询关注列表")
    @JsonView(User.class)
    public ApiResponse<List<User>> queryFollows(@RequestParam(value = "userId", defaultValue = "0") Long userId,
                                                @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                @RequestParam(value = "pageCount", defaultValue = "10", required = false) Integer pageCount) {
        ApiResponse response = new ApiResponse();
        if (userId == 0) {
            response.setData("data", null);
            return response;
        }
        List<User> users = userMapper.queryFollows(userId, pageCount * page, pageCount);
        response.setData("data", users);
        return response;
    }
}
