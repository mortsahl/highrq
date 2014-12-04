package highrq.rest.resources.assemblers;

import highrq.rest.mvc.AccountController;
import highrq.rest.resources.BlogResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import highrq.core.models.entities.Blog;
import highrq.rest.mvc.BlogController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

public class BlogResourceAssembler extends ResourceAssemblerSupport<Blog, BlogResource> {
    public BlogResourceAssembler() {
        super(BlogController.class, BlogResource.class);
    }

    @Override
    public BlogResource toResource(Blog blog) {
        BlogResource blogResource = new BlogResource();
        blogResource.setTitle(blog.getTitle());
        blogResource.add(linkTo(BlogController.class).slash(blog.getId()).withSelfRel());
        blogResource.add(linkTo(BlogController.class).slash(blog.getId()).slash("blog-entries").withRel("entries"));

        if (blog.getAccount() != null) {
            blogResource.add(linkTo(AccountController.class).slash(blog.getAccount().getId()).withRel("owner"));
        }
        return blogResource;
    }
}
