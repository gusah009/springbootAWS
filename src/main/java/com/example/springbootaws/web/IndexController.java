package com.example.springbootaws.web;

import com.example.springbootaws.web.config.auth.LoginUser;
import com.example.springbootaws.web.config.auth.dto.SessionUser;
import com.example.springbootaws.web.dto.PostsResponseDto;
import com.example.springbootaws.web.service.PostsService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final PostsService postsService;
//    private final HttpSession httpSession; // @LoginUser 사용으로 이제 안쓰임

    @GetMapping("/")
    public String index(Model model,
                        @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());

        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id,
                              Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }

}
