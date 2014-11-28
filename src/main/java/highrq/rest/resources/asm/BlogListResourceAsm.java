package highrq.rest.resources.asm;

import highrq.rest.resources.BlogListResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import highrq.core.services.util.BlogList;
import highrq.rest.mvc.BlogController;


public class BlogListResourceAsm extends ResourceAssemblerSupport<BlogList, BlogListResource> {

    public BlogListResourceAsm()
    {
        super(BlogController.class, BlogListResource.class);
    }

    @Override
    public BlogListResource toResource(BlogList blogList) {
        BlogListResource res = new BlogListResource();
        res.setBlogs(new BlogResourceAsm().toResources(blogList.getBlogs()));
        return res;
    }
}
