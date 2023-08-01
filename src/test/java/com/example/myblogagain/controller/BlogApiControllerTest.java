package com.example.myblogagain.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.myblogagain.domain.Article;
import com.example.myblogagain.dto.AddArticleRequest;
import com.example.myblogagain.dto.UpdateArticleRequest;
import com.example.myblogagain.repository.BlogRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@SpringBootTest
@AutoConfigureMockMvc
class BlogApiControllerTest {

  @Autowired
  protected MockMvc mockMvc;

  @Autowired
  protected ObjectMapper objectMapper;
  //객체-> json직렬화 및 역직렬화

  @Autowired
  private WebApplicationContext webApplicationContext;

  @Autowired
  BlogRepository blogRepository;

  @BeforeEach
  public void mockMvcSetUp(){
    this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    blogRepository.deleteAll();
  }

  @DisplayName("addArticle : 블로그에 글 추가에 성공한다.")
  @Test
  public void addArticle() throws Exception{
    //given
    final String url = "/api/articles";
    final String title = "title";
    final String content = "content";
    final AddArticleRequest request = new AddArticleRequest(title,content);
    // 저장할 블로그의 객체를 만든 후

    final String requestBody = objectMapper.writeValueAsString(request);
    //객체 -> json 직렬화

    //when
    //mockmvc를 사용해 http 메서드, url, 요청 본문(객체 직렬화 한거) , 요청타입(Json) 을 보내줌.
    mockMvc.perform(post(url)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(requestBody))
        .andExpect(status().isCreated());

    //then
    List<Article> articles = blogRepository.findAll();

    //asserthat을 통한 db의 저장 객체 조회
    //1개인지  (현재 테스트 진행 전부 지우고 1개 생성)
    assertThat(articles.size()).isEqualTo(1);
    //
    assertThat(articles.get(0).getTitle()).isEqualTo("title");
    assertThat(articles.get(0).getContent()).isEqualTo("content");
  }

  @DisplayName("findAllArticles: 블로그 글 목록 조회에 성공하기")
  @Test
  public void findAllArticles() throws Exception {
    //given
    final String url = "/api/articles";
    final String title = "title";
    final String content = "content";

    blogRepository.save(Article.builder().title(title).content(content).build());

    //when then
    mockMvc.perform(get(url).accept(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].content").value(content))
        .andExpect(jsonPath("$[0].title").value(title));
  }

  @DisplayName("findArticle: 블로그 글 한개 조회하기")
  @Test
  public void findArticle() throws Exception {
    //given
    final String url = "/api/articles/{id}";
    final String title = "title";
    final String content = "content";

    Article saveArticle = blogRepository.save(Article.builder().title(title).content(content).build());

    //when then
    mockMvc.perform(get(url,saveArticle.getId()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.content").value(content))
        .andExpect(jsonPath("$.title").value(title));
  }

  @DisplayName("deleteArticle: 블로그 글 삭제하기")
  @Test
  public void deleteAricle() throws Exception {
    //given
    final String url = "/api/articles/{id}";
    final String title = "title";
    final String content = "content";

    Article saveArticle = blogRepository.save(Article.builder().title(title).content(content).build());

    //when
    mockMvc.perform(delete(url,saveArticle.getId()))
        .andExpect(status().isOk());

    //then
    List<Article> articles = blogRepository.findAll();
    assertThat(articles).isEmpty();
  }

  @DisplayName("updateArticle : 블로그 글 수정하기")
  @Test
  public void updateArticle() throws Exception {
    //given
    final String url = "/api/articles/{id}";
    final String title = "title";
    final String content = "content";

    Article saveArticle = blogRepository.save(Article.builder().title(title).content(content).build());

    final String newTitle = "new title";
    final String newContent = "new content";

    UpdateArticleRequest request = new UpdateArticleRequest(newTitle,newContent);

    //when
    mockMvc.perform(put(url,saveArticle.getId())
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isOk());
    //then
    Article article = blogRepository.findById(saveArticle.getId()).get();

    assertThat(article.getTitle()).isEqualTo(newTitle);
    assertThat(article.getContent()).isEqualTo(newContent);

  }

}