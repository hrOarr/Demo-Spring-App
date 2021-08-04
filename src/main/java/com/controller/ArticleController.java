package com.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model.Article;
import com.model.ArticleDTO;
import com.service.ArticleService;

@RestController
@RequestMapping("/api/v1/articles")
public class ArticleController {
	
	private ArticleService articleService;
	
	@Autowired
	public ArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}
	
	// get all articles
	@GetMapping()
	public ResponseEntity<List<Article>> allArticles() {
		List<Article> articles = articleService.getArticleList();
		return ResponseEntity.ok(articles);
	}
	
	// get single article
	@GetMapping("/{id}")
	public ResponseEntity<?> showArticle(@PathVariable("id") int id) {
		Article article = articleService.getArticle(id);
		if(article==null) {
			return new ResponseEntity("No article found for " + id, HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(article);
	}
	
	// add article
	@PostMapping("/add")
	public ResponseEntity<?> addArticle(@Valid @RequestBody ArticleDTO articleDTO, BindingResult result) {
		//HttpHeaders responseHeaders = new HttpHeaders();
		System.out.println("hello there " + articleDTO);
		if(result.hasErrors()) {
			List<String> errors = result.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
		}
		articleService.saveArticle(articleDTO);
		return ResponseEntity.created(null).body(articleDTO);
	}
	
	// edit article
	@PutMapping("/edit")
	public ResponseEntity<?> editArticle(@Valid @RequestBody ArticleDTO articleDTO, BindingResult result) {
		//HttpHeaders responseHeaders = new HttpHeaders();
		System.out.println("update " + articleDTO);
		if(result.hasErrors()) {
			List<String> errors = result.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
		}
		articleService.updateArticle(articleDTO);
		return ResponseEntity.ok(articleDTO);
	}
	
	// get articles by tag
	@GetMapping("/byTag")
	public ResponseEntity<?> getArticlesByTag(@RequestParam("tag") String name) {
		List<Article> articles = articleService.getArticlesByTagName(name);
		return ResponseEntity.ok(articles);
	}
	
	// delete article
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteArticle(@PathVariable("id") int id) {
		articleService.deleteArticle(id);
		return ResponseEntity.ok("Article is deleted with id " + id);
	}
}
