package ca.omny.services.blogs.models;

public class BlogPostAssociation {
    private String blogId;
    private String blogPostId;

    public String getBlogId() {
        return blogId;
    }

    public void setBlogId(String blogId) {
        this.blogId = blogId;
    }

    public String getBlogPostId() {
        return blogPostId;
    }

    public void setBlogPostId(String blogPostId) {
        this.blogPostId = blogPostId;
    }
    
}
