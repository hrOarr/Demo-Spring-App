package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Tag;
import com.service.TagService;

@RestController
@RequestMapping("/api/v1/tags")
public class TagController {
	
	private TagService tagService;
	
	@Autowired
	public TagController(TagService tagService) {
		this.tagService = tagService;
	}
	
	@GetMapping
	public ResponseEntity<List<Tag>> allTags(){
		List<Tag> tags = tagService.allTags();
		return ResponseEntity.ok(tags);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getTag(@PathVariable("id") int id){
		Tag tag = tagService.getTagById(id);
		if(tag==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
		}
		return ResponseEntity.ok(tag);
	}
}
