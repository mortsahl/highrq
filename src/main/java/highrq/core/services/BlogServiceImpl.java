package highrq.core.services;

import highrq.core.repositories.BlogEntryDAO;
import highrq.core.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import highrq.core.models.entities.Blog;
import highrq.core.models.entities.BlogEntry;
import highrq.core.repositories.BlogDAO;
import highrq.core.services.exceptions.BlogNotFoundException;
import highrq.core.services.util.BlogEntryList;
import highrq.core.services.util.BlogList;


@Service
@Transactional
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDAO blogDAO;

    @Autowired
    private BlogEntryDAO entryDAO;

    @Override
    public BlogEntry createBlogEntry(Long blogId, BlogEntry data) {
        Blog blog = blogDAO.findBlog(blogId);
        if(blog == null)
        {
            throw new BlogNotFoundException();
        }
        BlogEntry entry = entryDAO.createBlogEntry(data);
        entry.setBlog(blog);
        return entry;
    }

    @Override
    public BlogList findAllBlogs() {
        return new BlogList(blogDAO.findAllBlogs());
    }

    @Override
    public BlogEntryList findAllBlogEntries(Long blogId) {
        Blog blog = blogDAO.findBlog(blogId);
        if(blog == null)
        {
            throw new BlogNotFoundException();
        }
        return new BlogEntryList(blogId, entryDAO.findByBlogId(blogId));
    }

    @Override
    public Blog findBlog(Long id) {
        return blogDAO.findBlog(id);
    }
}
