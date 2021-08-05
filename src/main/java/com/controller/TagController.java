package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Tag;
import com.service.TagService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1/tags")
@Api(value = "TagRestAPI")
public class TagController {
	
	private TagService tagService;
	
	@Autowired
	public TagController(TagService tagService) {
		this.tagService = tagService;
	}
	
	@ApiOperation(value = "Get List of Tags", response = Iterable.class, notes = "List of Tags")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful retrieval"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Tag>> allTags(){
		List<Tag> tags = tagService.allTags();
		return ResponseEntity.ok(tags);
	}
	
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful retrieval"),
			@ApiResponse(code = 404, message = "Resource Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	@GetMapping("/{id}")
	public ResponseEntity<?> getTag(@ApiParam(value = "TagId", required = true, defaultValue = "0") @PathVariable("id") int id){
		Tag tag = tagService.getTagById(id);
		if(tag==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
		}
		return ResponseEntity.ok(tag);
	}
}
