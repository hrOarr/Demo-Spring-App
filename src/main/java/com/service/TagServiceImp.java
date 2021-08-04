package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.TagDao;
import com.model.Tag;

@Service
public class TagServiceImp implements TagService {

	private TagDao tagDao;
	
	@Autowired
	public TagServiceImp(TagDao tagDao) {
		this.tagDao = tagDao;
	}
	
	@Override
	public void saveTag(Tag tag) {
		tagDao.saveTag(tag);
	}

	@Override
	public List<Tag> allTags() {
		return tagDao.allTags();
	}

	@Override
	public Tag getTagById(int id) {
		return tagDao.getTagById(id);
	}

	@Override
	public Tag getTagByName(String name) {
		return tagDao.getTagByName(name);
	}

	@Override
	public void update(Tag tag) {
		tagDao.update(tag);
	}

}
