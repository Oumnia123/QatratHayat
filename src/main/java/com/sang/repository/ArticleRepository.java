package com.sang.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sang.model.Annonce;
import com.sang.model.Article;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Integer> {

	@Query("SELECT a FROM Article a ORDER BY a.id_article DESC")
	public List<Article> findAllByIdArticle();
}
