package com.highrq.api.resources.assemblers;

import com.highrq.api.controllers.impl.AccountControllerImpl;
import com.highrq.api.resources.BlogResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import com.highrq.core.models.entities.Blog;
import com.highrq.api.controllers.impl.BlogControllerImpl;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@Component
public class BlogResourceAssembler extends ResourceAssemblerSupport<Blog, BlogResource> {
    public BlogResourceAssembler() {
        super(BlogControllerImpl.class, BlogResource.class);
    }

    @Override
    public BlogResource toResource(Blog blog) {
        BlogResource blogResource = new BlogResource();
        blogResource.setTitle(blog.getTitle());
        blogResource.add(linkTo(BlogControllerImpl.class).slash(blog.getId()).withSelfRel());
        blogResource.add(linkTo(BlogControllerImpl.class).slash(blog.getId()).slash("blog-entries").withRel("entries"));

        if (blog.getAccount() != null) {
            blogResource.add(linkTo(AccountControllerImpl.class).slash(blog.getAccount().getId()).withRel("owner"));
        }
        return blogResource;
    }
}
