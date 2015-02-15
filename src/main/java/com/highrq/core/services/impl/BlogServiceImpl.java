package com.highrq.core.services.impl;

import com.highrq.core.repositories.BlogEntryDAO;
import com.highrq.core.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.highrq.core.models.entities.Blog;
import com.highrq.core.models.entities.BlogEntry;
import com.highrq.core.repositories.BlogDAO;
import com.highrq.core.services.exceptions.BlogNotFoundException;
import com.highrq.core.services.util.BlogEntryList;
import com.highrq.core.services.util.BlogList;


@Service
@Transactional
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDAO blogDAO;

    @Autowired
    private BlogEntryDAO blogEntryDAO;

    @Override
    public BlogEntry createBlogEntry(Long blogId, BlogEntry data) {
        Blog blog = blogDAO.findBlog(blogId);
        if(blog == null)
        {
            throw new BlogNotFoundException();
        }
        BlogEntry entry = blogEntryDAO.createBlogEntry(data);
        entry.setBlog(blog);
        return entry;
    }

    @Override
    public BlogList findAllBlogs() {
        return new BlogList(blogDAO.findAllBlogs());
    }

    @Override
    public BlogEntryList findAllBlogEntries(Long blogId) {
        Blog blog = blogDAO.findBlog(blogId);
        if(blog == null)
        {
            throw new BlogNotFoundException();
        }
        return new BlogEntryList(blogId, blogEntryDAO.findByBlogId(blogId));
    }

    @Override
    public Blog findBlog(Long id) {
        return blogDAO.findBlog(id);
    }
}
