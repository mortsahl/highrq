package highrq.core.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "blogentry")
@NamedQueries({
        @NamedQuery(name = "BlogEntry.findByBlogId", query = "SELECT b FROM BlogEntry b WHERE b.blog.id=?1")
})
public class BlogEntry {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String content;

    @ManyToOne
    private Blog blog;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
