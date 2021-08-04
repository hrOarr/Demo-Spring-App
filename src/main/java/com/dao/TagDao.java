package com.dao;

import java.util.List;

import com.model.Tag;

public interface TagDao {
	public void saveTag(Tag tag);
	public List<Tag> allTags();
	public Tag getTagById(int id);
	public Tag getTagByName(String name);
	public void update(Tag tag);
}
