package com.koreait.cloneinstagram.feed.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedCmtEntity {
    private long icmt;
    private long ifeed;
    private long iuser;
    private String cmt;
    private String regdt;
}
