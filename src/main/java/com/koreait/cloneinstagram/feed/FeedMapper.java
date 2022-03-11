package com.koreait.cloneinstagram.feed;

import com.koreait.cloneinstagram.feed.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FeedMapper {
    /********************************************    feed   *********/
    int insFeed(FeedEntity entity);
    List<FeedDomain> selFeedList(FeedDto dto);

    /********************************************    img   *********/
    int insFeedImg(FeedImgEntity entity);

    /********************************************    fav   *********/
    int insFeedFav(FeedFavEntity param);
    int delFeedFav(FeedFavEntity param);

    /********************************************    cmt   *********/
    int insFeedCmt(FeedCmtEntity param);
    List<FeedCmtDomain> selFeedCmtList(FeedCmtEntity param);
}
