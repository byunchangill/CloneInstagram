package com.koreait.cloneinstagram.user;

import com.koreait.cloneinstagram.ResultVo;
import com.koreait.cloneinstagram.common.MyConst;
import com.koreait.cloneinstagram.feed.model.FeedDomain;
import com.koreait.cloneinstagram.feed.model.FeedDto;
import com.koreait.cloneinstagram.security.model.CustomUserPrincipal;
import com.koreait.cloneinstagram.user.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService service;
    private final MyConst myConst;

    @GetMapping("/signin")
    public void signIn(UserEntity userEntity) {}

    @GetMapping("/signup")
    public void singUp(UserEntity userEntity) {}

    @PostMapping("/signup")
    public String singUpProc(UserEntity userEntity) {
        int result = service.signUp(userEntity);
        return "redirect:/user/signin";
    }

    @GetMapping("/profile")
    public void profile(Model model, UserEntity param, @AuthenticationPrincipal CustomUserPrincipal userDetails) {
        System.out.println(param);

        UserDto param2 = new UserDto();
        param2.setToiuser(param.getIuser());
        if(param2.getToiuser() == 0) {
            param2.setToiuser(userDetails.getUser().getIuser());
            param.setIuser(userDetails.getUser().getIuser());
        }
        model.addAttribute(myConst.PROFILE, service.selUserProfile(param2));
        model.addAttribute(myConst.USER_IMG_LIST, service.selUserImgList(param));
    }

    @ResponseBody
    @GetMapping("/feed/list")
    public List<FeedDomain> selFeedList(FeedDto dto) {
        return service.selFeedList(dto);
    }

    @PostMapping("/profileImg")
    public String profileImg(MultipartFile[] imgArr, @AuthenticationPrincipal CustomUserPrincipal userDetails) {
        service.profileImg(imgArr);
        return "redirect:/user/profile?iuser=" + userDetails.getUser().getIuser();
    }

    @ResponseBody
    @PutMapping("/mainImg")
    public ResultVo mainProfile(UserEntity entity) {
        System.out.println(entity);
        return new ResultVo(service.updUserMainImg(entity));
    }

    @ResponseBody
    @PostMapping("/follow")
    public ResultVo doFollow(@RequestBody UserFollowEntity param) {
        int result = service.insUserFollow(param);
        return new ResultVo(result);
    }

    @ResponseBody
    @DeleteMapping("/follow")
    public Map<String, Object> cancelFollow(UserFollowEntity entity) {
        return service.delUserFollow(entity);
    }

    @ResponseBody
    @GetMapping("/getFollowList")
    public List<UserDomain> getFollowList(UserFollowEntity entity) {
        return service.selUserFollowList(entity);
    }

    @ResponseBody
    @GetMapping("/getFollowerList")
    public List<UserDomain> getFollowerList(UserFollowEntity entity) {
        return service.selUserFollowerList(entity);
    }
}
