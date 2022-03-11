package com.koreait.cloneinstagram.user;

import com.koreait.cloneinstagram.user.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    int insUser(UserEntity entity);
    UserEntity findByEmail(UserEntity entity);
    UserDomain selUserProfile(UserDto param);
    int updUser(UserEntity entity);

    /********************************************    user img   *********/
    int insUserImg(UserImgEntity param);
    List<UserImgEntity> selUserImgList(UserEntity entity);


    /********************************************    follow   *********/
    int insUserFollow(UserFollowEntity param);
    UserFollowEntity selUserFollow(UserFollowEntity param);
    List<UserDomain> selUserFollowList(UserFollowEntity param);
    List<UserDomain> selUserFollowerList(UserFollowEntity param);
    int delUserFollow(UserFollowEntity param);
}
