package com.green.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.green.entity.Article;

// extends CrudRepository<Article, Long> 
// JPA의 CRUD 기능을 동작시키는 클래스
public interface ArticleRepository 
    extends CrudRepository<Article, Long> {
	
	// alt+shift+s : Override/Impliment Methods로 사용가능한 함수 확인

	@Override
	ArrayList<Article> findAll();

	// ArticleController 83 line 형변환 오류 해결 2번째 방법
	// 상속관계를 이용하여 List를 Iterable 로 UpCasting 하여 형변하지 않음
	// Iterable(I) <- Collection(C) <- List(I) <- ArrayList(C)
	// 아래 내용 추가
	
}
