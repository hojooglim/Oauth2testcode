package com.example.myblogagain.service;

import com.example.myblogagain.domain.Article;
import com.example.myblogagain.dto.AddArticleRequest;
import com.example.myblogagain.dto.UpdateArticleRequest;
import com.example.myblogagain.repository.BlogRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlogService {

  private final BlogRepository blogRepository;

  public Article save(AddArticleRequest request){
    return blogRepository.save(request.toEntity());
  }

  public List<Article> findAll(){return blogRepository.findAll();}

  public Article findById(Long id){
    return blogRepository.findById(id).orElseThrow(()->new IllegalArgumentException("not found"+id));
  }

  public void delete(Long id){
    blogRepository.deleteById(id);
  }

  @Transactional
  public Article update(Long id, UpdateArticleRequest request){
    Article article = blogRepository.findById(id).orElseThrow(()->new IllegalArgumentException("not found"+id));
    article.update(request.getTitle(),request.getContent());
    return article;
  }

}

