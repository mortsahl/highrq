package highrq.core.repositories;

import highrq.core.models.entities.Blog;

import java.util.List;


public interface BlogDAO {
    public Blog createBlog(Blog data);
    public List<Blog> findAllBlogs();
    public Blog findBlog(Long id);
    public Blog findBlogByTitle(String title);
    public List<Blog> findBlogsByAccount(Long accountId);
}
