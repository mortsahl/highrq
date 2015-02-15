package com.highrq.api.resources.assemblers;

import com.highrq.api.resources.BlogListResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import com.highrq.core.services.util.BlogList;
import com.highrq.api.controllers.impl.BlogControllerImpl;
import org.springframework.stereotype.Component;

@Component
public class BlogListResourceAssembler extends ResourceAssemblerSupport<BlogList, BlogListResource> {

    public BlogListResourceAssembler()
    {
        super(BlogControllerImpl.class, BlogListResource.class);
    }

    @Override
    public BlogListResource toResource(BlogList blogList) {
        BlogListResource blogListResource = new BlogListResource();
        blogListResource.setBlogs(new BlogResourceAssembler().toResources(blogList.getBlogs()));
        return blogListResource;
    }
}
