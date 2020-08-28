package com.macro.mall.vedio.service;

import com.macro.mall.vedio.table.TableUser;
import com.macro.mall.vedio.table.User;

import java.util.List;

public interface IUserService {

    TableUser queryUser(long userId);

    int delete(long userId);

    int update(TableUser user);

    int insertUser(TableUser user);

    List<User> queryFans(long userId, int offset, int pageCount);

    List<User> queryFollows(long userId, int offset, int pageCount);


}
