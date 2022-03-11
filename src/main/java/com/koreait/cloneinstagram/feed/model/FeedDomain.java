package com.koreait.cloneinstagram.feed.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FeedDomain extends FeedEntity {
    private String writer;
    private String mainimg;
    private int favCnt;
    private int isFav;
    private List<FeedImgEntity> imgList;
    private FeedCmtDomain cmt;
}
