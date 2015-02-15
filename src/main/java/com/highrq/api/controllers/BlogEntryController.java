package com.highrq.api.controllers;

import com.highrq.api.resources.BlogEntryResource;
import org.springframework.http.ResponseEntity;

public interface BlogEntryController {

    public ResponseEntity<BlogEntryResource> getBlogEntry(Long blogEntryId);
    public ResponseEntity<BlogEntryResource> deleteBlogEntry(Long blogEntryId);
    public ResponseEntity<BlogEntryResource> updateBlogEntry(
            Long blogEntryId, BlogEntryResource sentBlogEntry);
}
