package com.koreait.cloneinstagram.dm.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DmDto {
    private long idm;
    private int limit;
    private int page;
    private long fromiuser;
    private long toiuser;

    public int getStartIdx() {
        return (page - 1) * limit;
    }
}
