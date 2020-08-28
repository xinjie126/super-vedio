package com.macro.mall.vedio.mapper;

import com.macro.mall.vedio.table.TableComment;
import com.macro.mall.vedio.table.TableHotFeeds;
import com.macro.mall.vedio.table.TableTagList;
import com.macro.mall.vedio.table.TableUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MockFeedsMapper {

    int insertHotFeeds(TableHotFeeds feeds);


    int insertTag(TableTagList tagList);


    int insertComments(TableComment comment);


    int insertUser(TableUser user);
}
