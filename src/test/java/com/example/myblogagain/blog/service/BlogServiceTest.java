package com.example.myblogagain.blog.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

import com.example.myblogagain.blog.dto.AddArticleRequest;
import com.example.myblogagain.blog.entity.Article;
import com.example.myblogagain.blog.respository.BlogRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class BlogServiceTest {

    @InjectMocks
    BlogService blogService;

    @Mock
    BlogRepository blogRepository;

    @DisplayName("blog 저장")
    @Test
    void save() {
        //given
        String title = "title";
        String content = "content";
        AddArticleRequest request = new AddArticleRequest(title, content);

        doReturn(new Article(request.getTitle(), request.getContent())).when(blogRepository)
                .save(any(Article.class));
        //when
        Article article = blogService.save(request);
        //then
        assertEquals(content, article.getContent());
        assertEquals(title, article.getTitle());
    }

    @Test
    void findById() {

    }

    @Test
    void update() {
    }
}