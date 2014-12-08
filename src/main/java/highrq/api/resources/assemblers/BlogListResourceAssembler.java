package highrq.api.resources.assemblers;

import highrq.api.resources.BlogListResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import highrq.core.services.util.BlogList;
import highrq.api.controllers.BlogController;
import org.springframework.stereotype.Component;

@Component
public class BlogListResourceAssembler extends ResourceAssemblerSupport<BlogList, BlogListResource> {

    public BlogListResourceAssembler()
    {
        super(BlogController.class, BlogListResource.class);
    }

    @Override
    public BlogListResource toResource(BlogList blogList) {
        BlogListResource blogListResource = new BlogListResource();
        blogListResource.setBlogs(new BlogResourceAssembler().toResources(blogList.getBlogs()));
        return blogListResource;
    }
}
