package Beb_scaner;

public class URLDepthPair {
    String host;
    int depth;

    URLDepthPair(int depth,String host){
        this.depth = depth;
        this.host = host;
    }


    public int getDepth() {
        return depth;
    }

    public String getHost() {
        return host;
    }
    public String toString() {
        return(host + " "+ depth);
    }
}