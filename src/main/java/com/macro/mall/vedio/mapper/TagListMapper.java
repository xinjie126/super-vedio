package com.macro.mall.vedio.mapper;

import com.macro.mall.vedio.table.TableTagList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TagListMapper {
    List<TableTagList> queryTagList(@Param("tagId") long tagId,
                                    @Param("pageCount") int pageCount,
                                    @Param("userId") long userId,
                                    @Param("tagType") String tagType,
                                    @Param("offset") int offset);
}
