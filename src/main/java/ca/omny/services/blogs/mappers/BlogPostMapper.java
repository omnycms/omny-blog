package ca.omny.services.blogs.mappers;

import ca.omny.documentdb.IDocumentQuerier;
import ca.omny.services.blogs.models.BlogPost;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import javax.inject.Inject;

public class BlogPostMapper {
    
    @Inject
    IDocumentQuerier querier;
    
    public BlogPost getBlogPost(String host, String blogPostId) {
        String key = querier.getKey("site_data",host,"blog_posts",blogPostId);
        return querier.get(key, BlogPost.class);
    }
    
    public Collection<BlogPost> getBlogPosts(String host) {
        String key = querier.getKey("site_data",host,"blog_posts");
        Collection<BlogPost> blogPosts = querier.getRange(key, BlogPost.class);
        return blogPosts;
    }
    
    public String createBlogPost(String host, String title, String content) {
        BlogPost blogPost = new BlogPost();
        blogPost.setBlogPostId(UUID.randomUUID().toString());
        blogPost.setTitle(title);
        blogPost.setContent(content);
        this.createBlogPost(host, blogPost);
        return blogPost.getBlogPostId();
    }
    
    public void createBlogPost(String host, BlogPost blogPost) {
        String key = querier.getKey("site_data",host,"blog_posts",blogPost.getBlogPostId());
        querier.set(key, blogPost);
    }
    
    public void deleteBlogPost(String host, BlogPost blogPost) {
        String key = querier.getKey("site_data",host,"blog_posts",blogPost.getBlogPostId());
        querier.delete(key);
    }
    
}
