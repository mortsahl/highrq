package highrq.core.services;

import highrq.core.repositories.BlogEntryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import highrq.core.models.entities.BlogEntry;
import highrq.core.services.BlogEntryService;


@Service
@Transactional
public class BlogEntryServiceImpl implements BlogEntryService {

    @Autowired
    private BlogEntryDAO entryDAO;

    @Override
    public BlogEntry findBlogEntry(Long id) {
        return entryDAO.findBlogEntry(id);
    }

    @Override
    public BlogEntry deleteBlogEntry(Long id) {
        return entryDAO.deleteBlogEntry(id);
    }

    @Override
    public BlogEntry updateBlogEntry(Long id, BlogEntry data) {
        return entryDAO.updateBlogEntry(id, data);
    }
}
