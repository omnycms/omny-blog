package ca.omny.services.blogs.apis;

import ca.omny.request.api.ApiResponse;
import ca.omny.request.api.OmnyApi;
import ca.omny.request.management.RequestResponseManager;
import com.google.gson.Gson;
import ca.omny.services.blogs.mappers.BlogPostAssociationMapper;
import ca.omny.services.blogs.models.BlogPostAssociation;
import javax.inject.Inject;

public class BlogPostAssociations implements OmnyApi {

    Gson gson;
    
    @Inject
    BlogPostAssociationMapper blogPostAssociationMapper;

    public BlogPostAssociations() {
        gson = new Gson();
    }

    @Override
    public String getBasePath() {
        return BlogApiConstants.base+"/associations/{blogId}/{blogPostId}";
    }

    @Override
    public String[] getVersions() {
        return BlogApiConstants.versions;
    }

    @Override
    public ApiResponse getResponse(RequestResponseManager requestResponseManager) {
        String blogId = requestResponseManager.getPathParameter("blogId");
        String hostname = requestResponseManager.getRequestHostname();
        return new ApiResponse(blogPostAssociationMapper.getBlogPosts(hostname, blogId), 200);
    }

    @Override
    public ApiResponse postResponse(RequestResponseManager requestResponseManager) {
        String blogId = requestResponseManager.getPathParameter("blogId");
        String blogPostId = requestResponseManager.getPathParameter("blogPostId");
        BlogPostAssociation blogPostAssociation = new BlogPostAssociation();
        blogPostAssociation.setBlogId(blogId);
        blogPostAssociation.setBlogPostId(blogPostId);
        blogPostAssociationMapper.createBlogPostAssociation(requestResponseManager.getRequestHostname(), blogPostAssociation);
        return new ApiResponse("", 200);
    }

    @Override
    public ApiResponse putResponse(RequestResponseManager requestResponseManager) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ApiResponse deleteResponse(RequestResponseManager requestResponseManager) {
        String blogId = requestResponseManager.getPathParameter("blogId");
        String blogPostId = requestResponseManager.getPathParameter("blogPostId");
        BlogPostAssociation blogPostAssociation = new BlogPostAssociation();
        blogPostAssociation.setBlogId(blogId);
        blogPostAssociation.setBlogPostId(blogPostId);
        blogPostAssociationMapper.deleteBlogPostAssociation(requestResponseManager.getRequestHostname(), blogPostAssociation);
        return new ApiResponse("", 200);
    }
    

   
}
