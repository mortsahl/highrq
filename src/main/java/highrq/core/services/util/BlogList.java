package highrq.core.services.util;

import highrq.core.models.entities.Blog;

import java.util.ArrayList;
import java.util.List;

public class BlogList {

    private List<Blog> blogs = new ArrayList<>();

    public BlogList(List blogs) {
        this.blogs = blogs;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }
}
