package com.example.myblogagain.service;

import com.example.myblogagain.domain.Article;
import com.example.myblogagain.dto.AddArticleRequest;
import com.example.myblogagain.dto.UpdateArticleRequest;
import com.example.myblogagain.repository.BlogRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlogService {

  private final BlogRepository blogRepository;

  public Article save(AddArticleRequest request, String userName) {
    return blogRepository.save(request.toEntity(userName));
  }

  public List<Article> findAll() {
    return blogRepository.findAll();
  }

  public Article findById(long id) {
    return blogRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
  }

  public void delete(long id) {
    Article article = blogRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("not found : " + id));

    authorizeArticleAuthor(article);
    blogRepository.delete(article);
  }

  @Transactional
  public Article update(long id, UpdateArticleRequest request) {
    Article article = blogRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("not found : " + id));

    authorizeArticleAuthor(article);
    article.update(request.getTitle(), request.getContent());

    return article;
  }

  // 게시글을 작성한 유저인지 확인
  private static void authorizeArticleAuthor(Article article) {
    String userName = SecurityContextHolder.getContext().getAuthentication().getName();
    if (!article.getAuthor().equals(userName)) {
      throw new IllegalArgumentException("not authorized");
    }
  }


}

