package com.koreait.cloneinstagram.user;

import com.koreait.cloneinstagram.common.MyConst;
import com.koreait.cloneinstagram.common.MyFileUtils;
import com.koreait.cloneinstagram.feed.FeedMapper;
import com.koreait.cloneinstagram.feed.model.FeedDto;
import com.koreait.cloneinstagram.feed.model.FeedDomain;
import com.koreait.cloneinstagram.security.AuthenticationFacade;
import com.koreait.cloneinstagram.security.ProviderType;
import com.koreait.cloneinstagram.user.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper mapper;
    private final FeedMapper feedMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationFacade auth;
    private final MyFileUtils myFileUtils;
    private final MyConst myConst;

    public int signUp(UserEntity userEntity) {
        String hashedPw = passwordEncoder.encode(userEntity.getPw());
        userEntity.setPw(hashedPw);
        userEntity.setProvider(ProviderType.LOCAL.toString());
        return mapper.insUser(userEntity);
    }

    public void profileImg(MultipartFile[] imgArr) {
        UserEntity loginUser = auth.getLoginUser();
        long iuser = loginUser.getIuser(); //11

        System.out.println("iuser : " + iuser);
        String target = "profile/" + iuser;

        UserImgEntity param = new UserImgEntity();
        param.setIuser(iuser); //11

        for(MultipartFile img : imgArr) {
            String saveFileNm = myFileUtils.transferTo(img, target); //"weioj435lknsio.jpg"
            if(saveFileNm != null) {
                param.setImg(saveFileNm);
                if(mapper.insUserImg(param) == 1 && loginUser.getMainimg() == null) {
                    UserEntity param2 = new UserEntity();
                    param2.setIuser(iuser); //11
                    param2.setMainimg(saveFileNm);

                    if(mapper.updUser(param2) == 1) {
                        loginUser.setMainimg(saveFileNm);
                    }
                }
            }
        }
    }

    public UserDomain selUserProfile(UserDto param) {
        param.setFromiuser(auth.getLoginUserPk());
        return mapper.selUserProfile(param);
    }

    public List<UserImgEntity> selUserImgList(UserEntity param) {
        return mapper.selUserImgList(param);
    }

    //메인 이미지 변경
    public int updUserMainImg(UserEntity entity) {
        UserEntity loginUser = auth.getLoginUser();

        entity.setIuser(loginUser.getIuser());
        int result = mapper.updUser(entity);
        if(result == 1) { //시큐리티 세션에 있는 loginUser에 있는 mainProfile값도 변경해주어야 한다.
            System.out.println("img : " + entity.getMainimg());
            loginUser.setMainimg(entity.getMainimg());
        }
        return result;
    }

    //팔로우 하기
    public int insUserFollow(UserFollowEntity entity) {
        entity.setFromiuser(auth.getLoginUserPk());
        return mapper.insUserFollow(entity);
    }

    public List<FeedDomain> selFeedList(FeedDto dto) {
        return feedMapper.selFeedList(dto);
    }
    public List<UserDomain> selUserFollowList(UserFollowEntity entity) {
        entity.setFromiuser(auth.getLoginUserPk());
        return mapper.selUserFollowList(entity);
    }

    public List<UserDomain> selUserFollowerList(UserFollowEntity entity) {
        entity.setFromiuser(auth.getLoginUserPk());
        return mapper.selUserFollowerList(entity);
    }

    //팔로우 취소
    public Map<String, Object> delUserFollow(UserFollowEntity entity) {
        entity.setFromiuser(auth.getLoginUserPk());
        int result = mapper.delUserFollow(entity);

        Map<String, Object> res = new HashMap();
        res.put(myConst.RESULT, result);
        if(result == 1) {
            UserFollowEntity param2 = new UserFollowEntity();
            param2.setFromiuser(entity.getToiuser());
            param2.setToiuser(entity.getFromiuser());

            UserFollowEntity result2 = mapper.selUserFollow(param2);
            res.put(myConst.YOU_FOLLOW_ME, result2);
        }
        return res;
    }
}
