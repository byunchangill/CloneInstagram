package com.koreait.cloneinstagram.dm.model;

import com.koreait.cloneinstagram.user.model.UserEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DmDomain extends DmEntity {
    private UserEntity opponent;
}
