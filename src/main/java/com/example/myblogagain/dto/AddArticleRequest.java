package com.example.myblogagain.dto;

import com.example.myblogagain.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AddArticleRequest {

  private String title;
  private String content;

  public Article toEntity(String author) {
    return Article.builder()
            .title(title)
                .content(content)
            .author(author)
                    .build();
  }

}
