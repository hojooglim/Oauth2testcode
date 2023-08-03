package com.example.myblogagain.blog.respository;

import com.example.myblogagain.blog.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {

}
