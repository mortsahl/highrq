package com.highrq.core.services;

import com.highrq.core.models.entities.Blog;
import com.highrq.core.services.util.BlogEntryList;
import com.highrq.core.models.entities.BlogEntry;
import com.highrq.core.services.util.BlogList;


public interface BlogService {
    /**
     * @param blogId the id of the blog to add this BlogEntry to
     * @param data the BlogEntry containing the data to be used for creating the new entity
     * @return the created BlogEntry with a generated ID
     * @throws highrq.core.services.exceptions.BlogNotFoundException if the blog to add to cannot be found
     */
    public BlogEntry createBlogEntry(Long blogId, BlogEntry data);

    public BlogList findAllBlogs();

    public BlogEntryList findAllBlogEntries(Long blogId); // findBlog all associated blog entries

    public Blog findBlog(Long id);
}
