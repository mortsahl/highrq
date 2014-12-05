package highrq.api.resources.assemblers;

import highrq.core.models.entities.BlogEntry;
import highrq.api.mvc.BlogEntryController;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import highrq.api.mvc.BlogController;
import highrq.api.resources.BlogEntryResource;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@Component
public class BlogEntryResourceAssembler extends ResourceAssemblerSupport<BlogEntry, BlogEntryResource> {

    public BlogEntryResourceAssembler() {
        super(BlogEntryController.class, BlogEntryResource.class);
    }

    @Override
    public BlogEntryResource toResource(BlogEntry blogEntry) {
        BlogEntryResource blogEntryResource = new BlogEntryResource();
        blogEntryResource.setTitle(blogEntry.getTitle());
        blogEntryResource.setContent(blogEntry.getContent());
        Link self = linkTo(BlogEntryController.class).slash(blogEntry.getId()).withSelfRel();
        blogEntryResource.add(self);

        if (blogEntry.getBlog() != null) {
            blogEntryResource.add((linkTo(BlogController.class).slash(blogEntry.getBlog().getId()).withRel("blog")));
        }
        return blogEntryResource;
    }
}
