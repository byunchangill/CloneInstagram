package com.koreait.cloneinstagram.feed.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedDto {
    private int page;
    private int limit;
    private long iuserForFav; //내가 feed에 좋아요 했는지 알기 위해 쓰는 로그인 유저 iuser값
    private long iuserForMyFeed;

    public int getStartIdx() {
        return (page - 1) * limit;
    }
}
