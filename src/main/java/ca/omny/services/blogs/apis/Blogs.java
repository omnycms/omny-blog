package ca.omny.services.blogs.apis;

import ca.omny.request.api.ApiResponse;
import ca.omny.request.api.OmnyApi;
import ca.omny.request.management.RequestResponseManager;
import com.google.gson.Gson;
import ca.omny.services.blogs.mappers.BlogMapper;
import ca.omny.services.blogs.mappers.BlogPostAssociationMapper;
import ca.omny.services.blogs.models.Blog;
import javax.inject.Inject;

public class Blogs implements OmnyApi {

    Gson gson;
    
    @Inject
    BlogMapper blogMapper;
    
    @Inject
    BlogPostAssociationMapper blogPostAssociationMapper;

    public Blogs() {
        gson = new Gson();
    }

    @Override
    public String getBasePath() {
        return BlogApiConstants.base+"/{blogId}";
    }

    @Override
    public String[] getVersions() {
        return BlogApiConstants.versions;
    }

    @Override
    public ApiResponse getResponse(RequestResponseManager requestResponseManager) {
        String blogId = requestResponseManager.getPathParameter("blogId");
        String hostname = requestResponseManager.getRequestHostname();
        if (blogId!=null) {
            Blog blog = blogMapper.getBlog(hostname, blogId);
            if(blog==null) {
                return new ApiResponse("", 404);
            }
            return new ApiResponse(blog, 200);
        }
        return new ApiResponse(blogMapper.getBlogs(hostname),200);
    }

    @Override
    public ApiResponse postResponse(RequestResponseManager requestResponseManager) {
        String blogId = requestResponseManager.getPathParameter("blogId");
        String hostname = requestResponseManager.getRequestHostname();
        Blog blog = requestResponseManager.getEntity(Blog.class);
        blog.setBlogId(blog.getName());
        blogMapper.createBlog(hostname, blog);
        return new ApiResponse("", 200);
    }

    @Override
    public ApiResponse putResponse(RequestResponseManager requestResponseManager) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ApiResponse deleteResponse(RequestResponseManager requestResponseManager) {
        String blogId = requestResponseManager.getPathParameter("blogId");
        String hostname = requestResponseManager.getRequestHostname();
        blogMapper.deleteBlog(hostname, blogId);
        return new ApiResponse("", 200);
    }
    

   
}
