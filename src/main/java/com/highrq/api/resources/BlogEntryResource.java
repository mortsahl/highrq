package com.highrq.api.resources;

import com.highrq.core.models.entities.BlogEntry;
import org.springframework.hateoas.ResourceSupport;


public class BlogEntryResource extends ResourceSupport {
    private String title;

    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BlogEntry toBlogEntry() {
        BlogEntry entry = new BlogEntry();
        entry.setTitle(title);
        entry.setContent(content);
        return entry;
    }
}
