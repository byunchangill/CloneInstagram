package com.koreait.cloneinstagram.user.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserEntity {
    private long iuser;
    private String provider;
    private String email;
    private String pw;
    private String nm;
    private String cmt;
    private String mainimg;
    private String regdt;
}
