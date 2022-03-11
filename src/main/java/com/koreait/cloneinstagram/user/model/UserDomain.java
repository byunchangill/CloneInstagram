package com.koreait.cloneinstagram.user.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDomain extends UserEntity {
    private int cntFeed; //피드 카운트
    private int cntFollower; //팔로워 카운트
    private int cntFollow; //팔로우 카운트
    private int isYouFollowMe; //너는 나를 팔로우 했니
    private int isMeFollowYou; //나는 너를 팔로우 했니
}
