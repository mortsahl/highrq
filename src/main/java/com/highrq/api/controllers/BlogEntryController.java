package com.highrq.api.controllers;

import com.highrq.api.resources.BlogEntryResource;
import com.highrq.api.resources.assemblers.BlogEntryResourceAssembler;
import com.highrq.core.models.entities.BlogEntry;
import com.highrq.core.services.BlogEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/blog-entries")
public class BlogEntryController {

    private BlogEntryService service;

    @Autowired
    public BlogEntryController(BlogEntryService service)
    {
        this.service = service;
    }

    @RequestMapping(value="/{blogEntryId}",
            method = RequestMethod.GET)
    public ResponseEntity<BlogEntryResource> getBlogEntry(
            @PathVariable Long blogEntryId) {
        BlogEntry entry = service.findBlogEntry(blogEntryId);
        if(entry != null)
        {
            BlogEntryResource res = new BlogEntryResourceAssembler().toResource(entry);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/{blogEntryId}",
            method = RequestMethod.DELETE)
    public ResponseEntity<BlogEntryResource> deleteBlogEntry(
            @PathVariable Long blogEntryId) {
        BlogEntry entry = service.deleteBlogEntry(blogEntryId);
        if(entry != null)
        {
            BlogEntryResource res = new BlogEntryResourceAssembler().toResource(entry);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/{blogEntryId}",
            method = RequestMethod.PUT)
    public ResponseEntity<BlogEntryResource> updateBlogEntry(
            @PathVariable Long blogEntryId, @RequestBody BlogEntryResource sentBlogEntry) {
        BlogEntry updatedEntry = service.updateBlogEntry(blogEntryId, sentBlogEntry.toBlogEntry());
        if(updatedEntry != null)
        {
            BlogEntryResource res = new BlogEntryResourceAssembler().toResource(updatedEntry);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
