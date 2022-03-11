package com.koreait.cloneinstagram.feed.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedEntity {
    private long ifeed;
    private String location;
    private String ctnt;
    private long iuser;
    private String regdt;
}
