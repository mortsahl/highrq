package highrq.api.resources;

import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;


public class BlogListResource extends ResourceSupport {
    private List<BlogResource> blogs = new ArrayList<>();

    public List<BlogResource> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<BlogResource> blogs) {
        this.blogs = blogs;
    }
}
