package com.highrq.api.resources.assemblers;

import com.highrq.core.models.entities.BlogEntry;
import com.highrq.api.controllers.impl.BlogEntryControllerImpl;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import com.highrq.api.controllers.impl.BlogControllerImpl;
import com.highrq.api.resources.BlogEntryResource;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@Component
public class BlogEntryResourceAssembler extends ResourceAssemblerSupport<BlogEntry, BlogEntryResource> {

    public BlogEntryResourceAssembler() {
        super(BlogEntryControllerImpl.class, BlogEntryResource.class);
    }

    @Override
    public BlogEntryResource toResource(BlogEntry blogEntry) {
        BlogEntryResource blogEntryResource = new BlogEntryResource();
        blogEntryResource.setTitle(blogEntry.getTitle());
        blogEntryResource.setContent(blogEntry.getContent());
        Link self = linkTo(BlogEntryControllerImpl.class).slash(blogEntry.getId()).withSelfRel();
        blogEntryResource.add(self);

        if (blogEntry.getBlog() != null) {
            blogEntryResource.add((linkTo(BlogControllerImpl.class).slash(blogEntry.getBlog().getId()).withRel("blog")));
        }
        return blogEntryResource;
    }
}
