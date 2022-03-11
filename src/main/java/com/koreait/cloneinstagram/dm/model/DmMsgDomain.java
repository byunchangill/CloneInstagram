package com.koreait.cloneinstagram.dm.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DmMsgDomain extends DmMsgEntity {
    private String writer;
}
