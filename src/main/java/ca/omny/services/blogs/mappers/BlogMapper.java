package ca.omny.services.blogs.mappers;

import ca.omny.documentdb.IDocumentQuerier;
import ca.omny.services.blogs.models.Blog;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import javax.inject.Inject;

public class BlogMapper {
    
    @Inject
    IDocumentQuerier querier;
    
    public Blog getBlog(String host, String blogId) {
        String key = querier.getKey("site_data", host, "blogs", blogId);
        return querier.get(key, Blog.class);
    }
    
    public Collection<Blog> getBlogs(String host) {
        String key = querier.getKey("site_data",host,"blogs");
        Collection<Blog> blogs = querier.getRange(key, Blog.class);
        return blogs;
    }
    
    public String createBlog(String host,String blogName) {
        Blog blog = new Blog();
        blog.setBlogId(blogName);
        blog.setName(blogName);
        this.createBlog(host, blog);
        return blog.getBlogId();
    }
    
    public void createBlog(String host, Blog blog) {
        String key = querier.getKey("site_data",host,"blogs",blog.getBlogId());
        querier.set(key, blog);
    }
    
    public void deleteBlog(String host, String blogId) {
        String key = querier.getKey("site_data",host,"blogs",blogId);
        querier.delete(key);
    }
    
}
