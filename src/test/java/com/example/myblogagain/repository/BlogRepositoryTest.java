package com.example.myblogagain.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.myblogagain.blog.entity.Article;
import com.example.myblogagain.blog.respository.BlogRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BlogRepositoryTest {

    @Autowired
    private BlogRepository blogRepository;

    @DisplayName("블로그 추가")
    @Test
    void addArticle() {
        // given
        String title = "title";
        String content = "content";

        // when
        Article article = blogRepository.save(new Article(title, content));

        // then
        assertThat(title).isEqualTo(article.getTitle());
        assertThat(content).isEqualTo(article.getContent());

    }
}
