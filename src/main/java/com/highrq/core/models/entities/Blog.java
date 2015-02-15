package com.highrq.core.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "blog")
@NamedQueries({
        @NamedQuery(name = "Blog.findAllBlogs", query = "SELECT b from Blog b"),
        @NamedQuery(name = "Blog.findBlogByTitle", query = "SELECT b from Blog b where b.title=?1"),
        @NamedQuery(name = "Blog.findBlogsByAccount", query = "SELECT b from Blog b where b.account.id=?1")
})
public class Blog {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @OneToOne
    private Account account;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
