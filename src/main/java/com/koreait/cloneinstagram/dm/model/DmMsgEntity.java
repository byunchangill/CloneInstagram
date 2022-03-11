package com.koreait.cloneinstagram.dm.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DmMsgEntity {
    private long idm;
    private long seq;
    private long iuser;
    private String msg;
    private String regdt;
}
