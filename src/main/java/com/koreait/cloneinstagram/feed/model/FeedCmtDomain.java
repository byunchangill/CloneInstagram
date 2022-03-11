package com.koreait.cloneinstagram.feed.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedCmtDomain extends FeedCmtEntity {
    private String writer;
    private String writerimg;
    private int isMore; //0: 댓글 더 없음, 1: 댓글 더 있음
}
