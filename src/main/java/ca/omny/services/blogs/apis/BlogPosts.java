package ca.omny.services.blogs.apis;

import ca.omny.request.api.ApiResponse;
import ca.omny.request.api.OmnyApi;
import ca.omny.request.management.RequestResponseManager;
import com.google.gson.Gson;
import ca.omny.services.blogs.mappers.BlogPostMapper;
import ca.omny.services.blogs.models.BlogPost;
import javax.inject.Inject;

public class BlogPosts implements OmnyApi {

    Gson gson;
    
    @Inject
    BlogPostMapper blogPostMapper;

    public BlogPosts() {
        gson = new Gson();
    }

    @Override
    public String getBasePath() {
        return BlogApiConstants.base+"/blogposts/{blogPostId}";
    }

    @Override
    public String[] getVersions() {
        return BlogApiConstants.versions;
    }

    @Override
    public ApiResponse getResponse(RequestResponseManager requestResponseManager) {
        String blogPostId = requestResponseManager.getPathParameter("blogPostId");
        String hostname = requestResponseManager.getRequestHostname();
        if (blogPostId!=null) {
            BlogPost blogPost = blogPostMapper.getBlogPost(hostname, blogPostId);
            if(blogPost==null) {
                return new ApiResponse("", 404);
            }
            return new ApiResponse(blogPost, 200);
        }
        return new ApiResponse(blogPostMapper.getBlogPosts(hostname),200);
    }

    @Override
    public ApiResponse postResponse(RequestResponseManager requestResponseManager) {
        String blogPostId = requestResponseManager.getPathParameter("blogPostId");
        BlogPost post = requestResponseManager.getEntity(BlogPost.class);

        blogPostMapper.createBlogPost(requestResponseManager.getRequestHostname(), post);
        
        return new ApiResponse("", 200);
    }

    @Override
    public ApiResponse putResponse(RequestResponseManager requestResponseManager) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ApiResponse deleteResponse(RequestResponseManager requestResponseManager) {
        String blogPostId = requestResponseManager.getPathParameter("blogPostId");
        BlogPost post = requestResponseManager.getEntity(BlogPost.class);

        blogPostMapper.deleteBlogPost(requestResponseManager.getRequestHostname(), post);
        
        return new ApiResponse("", 200);
    }
    

   
}
