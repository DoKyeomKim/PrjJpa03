package com.green.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.dto.ArticleForm;
import com.green.entity.Article;
import com.green.repository.ArticleRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ArticleService {

	@Autowired
	private ArticleRepository  articleRepository;
	
	// article 목록 조회
	public List<Article> index() {	
		// DB 저장하기전 작업할 코딩 넣는다
		// JPA 함수 : findAll()
		return articleRepository.findAll();
	}

	// article id 로 조회
	public Article show(Long id) {
		Article article = articleRepository.findById(id).orElse(null);
		return  article;
	}

	public Article create(ArticleForm dto) {
		
		// 입력 data dto : {"id":2, "title":"새글", "content":"새글 내용"}
		Article  article = dto.toEntity();
		
		// create 는 생성요청이고 번호 자동증가이므로 번호 필요없다
		// 그래서 id 가 존재하면 안된다
		if( article.getId() != null  )
			return null; // 아이디가 존재하면 error를 발생시킨다
		
		Article  saved = articleRepository.save(article);			
		return   saved;
	}

	public Article update(Long id, ArticleForm dto) {
		// 1. DTO -> Entity로 변환
		Article article = dto.toEntity();
		log.info("id : {}, article :{}", id, article.toString()); //{}가 파라미터

		// 2. 타겟(기존글)의 ID로 조회하기
		Article target = articleRepository.findById(id).orElse(null);
	
		// 3. 잘못된 요청을 처리
		// 조회한 자료가 없거나 id가 틀리면
		if (target==null || id != article.getId()) {
			log.info("id : {}, article :{}", id, article.toString());
			return null; 
		}
		
		// 4. 업데이트 및 정상응답(ok)
		target.patch(article);
		Article updated = articleRepository.save(target);
		
		return updated;
	}


	public Article delete(Long id) {
		// 1. 타겟(기존글)의 ID로 조회하기
		Article target = articleRepository.findById(id).orElse(null);
	
		// 2. 잘못된 요청을 처리
		// 조회한 자료가 없거나 id가 틀리면
		if (target==null) {
			return null; 
		}
		
		// 3. 업데이트 및 정상응답(ok)
		articleRepository.delete(target);
		
		return target;

	}
	
}




