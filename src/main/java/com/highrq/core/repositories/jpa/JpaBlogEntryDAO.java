package com.highrq.core.repositories.jpa;

import com.highrq.core.repositories.BlogEntryDAO;
import org.springframework.stereotype.Repository;
import com.highrq.core.models.entities.BlogEntry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class JpaBlogEntryDAO implements BlogEntryDAO {
    @PersistenceContext
    private EntityManager em;

    @Override
    public BlogEntry findBlogEntry(Long id) {
        return em.find(BlogEntry.class, id);
    }

    @Override
    public BlogEntry deleteBlogEntry(Long id) {
        BlogEntry entry = em.find(BlogEntry.class, id);
        em.remove(entry);
        return entry;
    }

    @Override
    public BlogEntry updateBlogEntry(Long id, BlogEntry data) {
        BlogEntry entry = em.find(BlogEntry.class, id);
        entry.setTitle(data.getTitle());
        entry.setContent(data.getContent());
        return entry;
    }

    @Override
    public BlogEntry createBlogEntry(BlogEntry data) {
        em.persist(data);
        return data;
    }

    @Override
    public List<BlogEntry> findByBlogId(Long blogId) {
        Query query = em.createNamedQuery("BlogEntry.findByBlogId");
        query.setParameter(1, blogId);
        return query.getResultList();
    }
}
