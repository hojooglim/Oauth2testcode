package com.example.myblogagain.repository;

import com.example.myblogagain.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {

}
