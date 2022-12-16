package com.example.springbootaws.web.domain.posts;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @Test
    public void 게시글저장_불러오기() {
        String title = "title";
        String content = "content";

        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .build());

        List<Posts> postsList = postsRepository.findAll();

        Posts result = postsList.get(0);
        assertThat(result.getTitle()).isEqualTo(title);
        assertThat(result.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록() {
        LocalDateTime now = LocalDateTime.of(2022, 12, 16, 0, 0, 0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());
        List<Posts> postsList = postsRepository.findAll();
        Posts result = postsList.get(0);

        System.out.println(">>>>> createDate = " + result.getCreatedDate() + "\n" +
                ">>>>> modifiedDate = " + result.getModifiedDate());
        assertThat(result.getCreatedDate()).isAfter(now);
        assertThat(result.getModifiedDate()).isAfter(now);
    }
}