package com.highrq.api.controllers.impl;

import com.highrq.api.controllers.BlogController;
import com.highrq.api.exceptions.NotFoundException;
import com.highrq.api.resources.BlogEntryListResource;
import com.highrq.api.resources.BlogEntryResource;
import com.highrq.api.resources.BlogListResource;
import com.highrq.api.resources.BlogResource;
import com.highrq.api.resources.assemblers.BlogEntryListResourceAssembler;
import com.highrq.api.resources.assemblers.BlogEntryResourceAssembler;
import com.highrq.api.resources.assemblers.BlogListResourceAssembler;
import com.highrq.api.resources.assemblers.BlogResourceAssembler;
import com.highrq.core.models.entities.Blog;
import com.highrq.core.models.entities.BlogEntry;
import com.highrq.core.services.BlogService;
import com.highrq.core.services.exceptions.BlogNotFoundException;
import com.highrq.core.services.util.BlogEntryList;
import com.highrq.core.services.util.BlogList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/blogs")
public class BlogControllerImpl implements BlogController {

    private BlogService blogService;

    @Autowired
    public BlogControllerImpl(BlogService blogService) {
        this.blogService = blogService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<BlogListResource> findAllBlogs() {
        BlogList blogList = blogService.findAllBlogs();
        BlogListResource blogListRes = new BlogListResourceAssembler().toResource(blogList);
        return new ResponseEntity<>(blogListRes, HttpStatus.OK);
    }

    @RequestMapping(value = "/{blogId}", method = RequestMethod.GET)
    public ResponseEntity<BlogResource> getBlog(@PathVariable Long blogId) {
        Blog blog = blogService.findBlog(blogId);
        if (blog != null) {
            BlogResource res = new BlogResourceAssembler().toResource(blog);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{blogId}/blog-entries", method = RequestMethod.POST)
    public ResponseEntity<BlogEntryResource> createBlogEntry(
            @PathVariable Long blogId,
            @RequestBody BlogEntryResource sentBlogEntry) {
        BlogEntry createdBlogEntry;
        try {
            createdBlogEntry = blogService.createBlogEntry(blogId, sentBlogEntry.toBlogEntry());
            BlogEntryResource createdResource = new BlogEntryResourceAssembler().toResource(createdBlogEntry);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(createdResource.getLink("self").getHref()));
            return new ResponseEntity<>(createdResource, headers, HttpStatus.CREATED);
        }
        catch (BlogNotFoundException e) {
            throw new NotFoundException(e);
        }
    }

    @RequestMapping(value = "/{blogId}/blog-entries")
    public ResponseEntity<BlogEntryListResource> findAllBlogEntries(@PathVariable Long blogId) {
        try {
            BlogEntryList list = blogService.findAllBlogEntries(blogId);
            BlogEntryListResource res = new BlogEntryListResourceAssembler().toResource(list);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        catch (BlogNotFoundException exception) {
            throw new NotFoundException(exception);
        }
    }

}
