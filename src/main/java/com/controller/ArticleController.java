package com.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1/articles")
@Api(value = "ArticlesRestAPI")
public class ArticleController {
	
	private static final Logger logger = Logger.getLogger(ArticleController.class);
	private ArticleService articleService;
	
	@Autowired
	public ArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}
	
	// get all articles
	@ApiOperation(value = "Get List of Articles", response = Iterable.class, notes = "List of Articles")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful retrieval"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Article>> allArticles() {
		List<Article> articles = articleService.getArticleList();
		return ResponseEntity.ok(articles);
	}
	
	// get single article
	@ApiOperation(value = "Get Specific Article")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful retrieval"),
			@ApiResponse(code = 404, message = "Resource Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	@GetMapping("/{id}")
	public ResponseEntity<?> showArticle(@ApiParam(value = "ArticleId", required = true, defaultValue = "0") @PathVariable("id") int id) {
		Article article = articleService.getArticle(id);
		if(article==null) {
			return new ResponseEntity<>("No article found for " + id, HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(article);
	}
	
	// add article
	@ApiOperation(value = "Save New Article")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Resource Created"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addArticle(@Valid @RequestBody ArticleDTO articleDTO, BindingResult result) {
		//HttpHeaders responseHeaders = new HttpHeaders();
		logger.debug("method[addArticle] = " + articleDTO);
		if(result.hasErrors()) {
			List<String> errors = result.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
		}
		articleService.saveArticle(articleDTO);
		return ResponseEntity.created(null).body(articleDTO);
	}
	
	// edit article
	@ApiOperation(value = "Update specific Article")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Resource Updated"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	@PutMapping(value = "/edit", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> editArticle(@Valid @RequestBody ArticleDTO articleDTO, BindingResult result) {
		//HttpHeaders responseHeaders = new HttpHeaders();
		logger.debug("method[editArticle] = " + articleDTO);
		if(result.hasErrors()) {
			List<String> errors = result.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
		}
		articleService.updateArticle(articleDTO);
		return ResponseEntity.ok(articleDTO);
	}
	
	// get articles by tag
	@ApiOperation(value = "Get Articles filtering by Tag")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful retrieval"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	@GetMapping(value = "/byTag", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getArticlesByTag(@RequestParam("tag") String name) {
		List<Article> articles = articleService.getArticlesByTagName(name);
		return ResponseEntity.ok(articles);
	}
	
	// delete article
	@ApiOperation(value = "Delete Specific Article")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful deletion"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteArticle(@PathVariable("id") int id) {
		articleService.deleteArticle(id);
		return ResponseEntity.ok("Article is deleted with id " + id);
	}
}
