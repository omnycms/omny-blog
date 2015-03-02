package ca.omny.services.blogs;

import ca.omny.server.OmnyServer;

public class Main {
    public static void main(String[] args) throws Exception {
        OmnyServer server = new OmnyServer();
        int port = 8080;
        if(System.getenv("OMNY_BLOG_PORT")!=null) {
            port = Integer.parseInt(System.getenv("OMNY_BLOG_PORT"));
        }
        server.createServer(port);
    }
}
