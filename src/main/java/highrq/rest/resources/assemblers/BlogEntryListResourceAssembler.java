package highrq.rest.resources.assemblers;

import highrq.core.services.util.BlogEntryList;
import highrq.rest.mvc.BlogController;
import highrq.rest.resources.BlogEntryListResource;
import highrq.rest.resources.BlogEntryResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@Component
public class BlogEntryListResourceAssembler extends ResourceAssemblerSupport<BlogEntryList, BlogEntryListResource> {
    public BlogEntryListResourceAssembler() {
        super(BlogController.class, BlogEntryListResource.class);
    }

    @Override
    public BlogEntryListResource toResource(BlogEntryList list) {
        List<BlogEntryResource> resources = new BlogEntryResourceAssembler().toResources(list.getEntries());
        BlogEntryListResource blogEntryListResource = new BlogEntryListResource();
        blogEntryListResource.setEntries(resources);
        blogEntryListResource.add(linkTo(methodOn(BlogController.class).findAllBlogEntries(list.getBlogId())).withSelfRel());
        return blogEntryListResource;
    }
}
