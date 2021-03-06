package com.highrq.api.resources.assemblers;

import com.highrq.core.services.util.BlogEntryList;
import com.highrq.api.controllers.impl.BlogControllerImpl;
import com.highrq.api.resources.BlogEntryListResource;
import com.highrq.api.resources.BlogEntryResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@Component
public class BlogEntryListResourceAssembler extends ResourceAssemblerSupport<BlogEntryList, BlogEntryListResource> {
    public BlogEntryListResourceAssembler() {
        super(BlogControllerImpl.class, BlogEntryListResource.class);
    }

    @Override
    public BlogEntryListResource toResource(BlogEntryList list) {
        List<BlogEntryResource> resources = new BlogEntryResourceAssembler().toResources(list.getEntries());
        BlogEntryListResource blogEntryListResource = new BlogEntryListResource();
        blogEntryListResource.setEntries(resources);
        blogEntryListResource.add(linkTo(methodOn(BlogControllerImpl.class).findAllBlogEntries(list.getBlogId())).withSelfRel());
        return blogEntryListResource;
    }
}
