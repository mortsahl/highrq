package highrq.core.repositories;

import highrq.core.models.entities.BlogEntry;

import java.util.List;


public interface BlogEntryDAO {
    public BlogEntry findBlogEntry(Long id); // Returns the BlogEntry or null if it can't be found
    public BlogEntry deleteBlogEntry(Long id); // Deletes the found BlogEntry or returns null if it can't be found

    public BlogEntry updateBlogEntry(Long id, BlogEntry data);

    public BlogEntry createBlogEntry(BlogEntry data);

    public List<BlogEntry> findByBlogId(Long blogId);
}
