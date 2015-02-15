package com.highrq.api.controllers;

import com.highrq.api.resources.BlogEntryListResource;
import com.highrq.api.resources.BlogEntryResource;
import com.highrq.api.resources.BlogListResource;
import com.highrq.api.resources.BlogResource;
import org.springframework.http.ResponseEntity;

public interface BlogController {

    public ResponseEntity<BlogListResource> findAllBlogs();
    public ResponseEntity<BlogResource> getBlog(Long blogId);
    public ResponseEntity<BlogEntryResource> createBlogEntry(
            Long blogId, BlogEntryResource sentBlogEntry);

    public ResponseEntity<BlogEntryListResource> findAllBlogEntries(Long blogId);

}
