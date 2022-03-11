package com.koreait.cloneinstagram.feed;

import com.koreait.cloneinstagram.ResultVo;
import com.koreait.cloneinstagram.feed.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/feed")
public class FeedController {

    private final FeedService service;

    @GetMapping
    public String index() {
        return "feed/index";
    }

    @ResponseBody
    @PostMapping("/reg")
    public ResultVo reg(MultipartFile[] imgs, FeedEntity entity) {
        return new ResultVo(service.reg(imgs, entity));
    }

    @ResponseBody
    @GetMapping("/list")
    public List<FeedDomain> selFeedList(FeedDto dto) {
        return service.selFeedList(dto);
    }

    @ResponseBody
    @GetMapping("/fav")
    public int feedFavProc(FeedFavEntity param, int type) { //type: 1 - ins(등록), 0 - del(취소)
        System.out.println(param);
        System.out.println("type: " + type);
        return service.feedFavProc(param, type);
    }

    @ResponseBody
    @PostMapping("/cmt")
    public int insFeedCmt(@RequestBody FeedCmtEntity entity) {
        return service.insFeedCmt(entity);
    }

    @ResponseBody
    @GetMapping("/cmt")
    public List<FeedCmtDomain> cmtList(FeedCmtEntity entity) {
        return service.selFeedCmtList(entity);
    }
}
