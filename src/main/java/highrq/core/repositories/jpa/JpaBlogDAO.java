package highrq.core.repositories.jpa;

import org.springframework.stereotype.Repository;
import highrq.core.models.entities.Blog;
import highrq.core.repositories.BlogDAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


@Repository
public class JpaBlogDAO implements BlogDAO {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Blog createBlog(Blog data) {
        em.persist(data);
        return data;
    }

    @Override
    public List<Blog> findAllBlogs() {
        Query query = em.createNamedQuery("Blog.findAllBlogs");
        return query.getResultList();
    }

    @Override
    public Blog findBlog(Long id) {
        return em.find(Blog.class, id);
    }

    @Override
    public Blog findBlogByTitle(String title) {
        Query query = em.createNamedQuery("Blog.findBlogByTitle");
        query.setParameter(1, title);
        List<Blog> blogs = query.getResultList();
        if(blogs.isEmpty()) {
            return null;
        } else {
            return blogs.get(0);
        }
    }

    @Override
    public List<Blog> findBlogsByAccount(Long accountId) {
        Query query = em.createNamedQuery("Blog.findBlogsByAccount");
        query.setParameter(1, accountId);
        return query.getResultList();
    }
}
