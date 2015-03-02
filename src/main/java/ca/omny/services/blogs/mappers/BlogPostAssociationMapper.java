package ca.omny.services.blogs.mappers;

import ca.omny.documentdb.IDocumentQuerier;
import ca.omny.services.blogs.models.BlogPost;
import ca.omny.services.blogs.models.BlogPostAssociation;
import java.util.Collection;
import java.util.LinkedList;
import javax.inject.Inject;

public class BlogPostAssociationMapper {
    
    @Inject
    IDocumentQuerier querier;
    
    public Collection<BlogPostAssociation> getAssociations(String host, String blogId) {
        String key = querier.getKey("site_data",host,"blog_post_associations",blogId);
        Collection<BlogPostAssociation> blogs = querier.getRange(key, BlogPostAssociation.class);
        return blogs;
    }
    
    public Collection<BlogPost> getBlogPosts(String host, String blogId) {
        Collection<BlogPostAssociation> associations = this.getAssociations(host, blogId);
        Collection<String> blogPostIds = new LinkedList<String>();
        for(BlogPostAssociation association: associations) {
            String key = querier.getKey("site_data",host,"blog_posts",association.getBlogPostId());
            blogPostIds.add(key);
        }
        Collection<BlogPost> result = querier.multiGetCollection(blogPostIds, BlogPost.class);
        return result;
    }
    
    public void createBlogPostAssociation(String host, BlogPostAssociation blogPostAssociation) {
        String key = querier.getKey("site_data",host,"blog_post_associations",blogPostAssociation.getBlogId(),blogPostAssociation.getBlogPostId());
        querier.set(key, blogPostAssociation);
    }
    
    public void deleteBlogPostAssociation(String host, BlogPostAssociation blogPostAssociation) {
        String key = querier.getKey("site_data",host,"blog_post_associations",blogPostAssociation.getBlogId(),blogPostAssociation.getBlogPostId());
        querier.delete(key);
    }
    
}
