package com.koreait.cloneinstagram.user.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserFollowEntity {
    private long fromiuser;
    private long toiuser;
    private String regdt;
}
