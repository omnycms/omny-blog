package ca.omny.services.blogs.models;

public class BlogPost {
    private String blogPostId;
    private String title;
    private String content;

    public String getBlogPostId() {
        return blogPostId;
    }

    public void setBlogPostId(String blogPostId) {
        this.blogPostId = blogPostId;
    }

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
}
