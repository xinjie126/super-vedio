package com.macro.mall.vedio.service;

import com.macro.mall.vedio.mapper.UserMapper;
import com.macro.mall.vedio.table.TableUser;
import com.macro.mall.vedio.table.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public TableUser queryUser(long userId) {
        return userMapper.queryUser(userId);
    }

    @Override
    public int delete(long userId) {
        return userMapper.delete(userId);
    }

    @Override
    public int update(TableUser user) {
        return userMapper.update(user);
    }

    @Override
    public int insertUser(TableUser user) {
        return userMapper.insertUser(user);
    }

    @Override
    public List<User> queryFans(long userId, int offset, int pageCount) {
        return userMapper.queryFans(userId, offset, pageCount);
    }

    @Override
    public List<User> queryFollows(long userId, int offset, int pageCount) {
        return userMapper.queryFollows(userId, offset, pageCount);
    }
}
