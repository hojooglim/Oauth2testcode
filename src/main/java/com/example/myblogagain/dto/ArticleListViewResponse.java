package com.example.myblogagain.dto;

import com.example.myblogagain.domain.Article;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ArticleListViewResponse {
    private  Long id;
    private  String title;
    private  String content;
    private String author;
    private  LocalDateTime createdAt;

    public ArticleListViewResponse(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.author = article.getAuthor();
        this.createdAt=article.getCreatedAt();
    }

}
