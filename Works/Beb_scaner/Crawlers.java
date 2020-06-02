package Beb_scaner;


import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.util.*;

public class Crawlers {

    public static final String URL_PREFIX = "https://";
    LinkedList<URLDepthPair> check = new LinkedList<>();
    LinkedList<URLDepthPair> uncheck = new LinkedList<>();

    Crawlers(String host, int depth) throws IOException {
        Socket socket = new Socket(host, 80);
        URLDepthPair urlpair = new URLDepthPair(depth,"https://" + host + "/");
        String host1 = host;
        uncheck.add(urlpair);
        socket.setSoTimeout(4000);
        while ((uncheck.size()>0)) {
            try {
                URL url = new URL(uncheck.getFirst().getHost());
                try {
                    LineNumberReader reader = new LineNumberReader(new InputStreamReader(url.openStream()));
                    String str = reader.readLine();
                    while (str != null) {
                        for (String content : str.split("href=\""))
                            try {
                                if (str.contains("href=\"" + URL_PREFIX) && content.startsWith(URL_PREFIX)) {
                                    urlpair = new URLDepthPair((content.substring(0, content.indexOf("\"")).split("/").length - 3), content.substring(0, content.indexOf("\"")));
                                    if (urlpair.getDepth() <= depth&& urlpair.getHost().contains(host1)) {
                                        int size_uncheck = uncheck.size();
                                        int size_check = check.size();
                                        boolean uncheck_state = false;
                                        boolean check_state = false;
                                        int i = 0;
                                        int j = 0;
                                        while ((uncheck_state == false && i < size_uncheck) || (check_state == false && j < size_check)) {
                                            if (i < size_uncheck) {
                                                if (uncheck.get(i).getHost().contains(urlpair.getHost())) {
                                                    uncheck_state = true;
                                                }
                                                i++;
                                            }
                                            if (j < size_check) {
                                                if (check.get(j).getHost().contains(urlpair.getHost())) {
                                                    check_state = true;
                                                }
                                                j++;
                                            }
                                        }
                                        if (uncheck_state == false && check_state == false) {
                                            uncheck.add(urlpair);
                                        }
                                    }
                                }
                            } catch (StringIndexOutOfBoundsException e) {
                            }

                        str = reader.readLine();
                    }
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
            socket.close();
            check.add(uncheck.getFirst());
            uncheck.removeFirst();
            System.out.println("Просмотренная cсылка: " +check.getLast().getHost());
            System.out.println("Всего проверено ссылок: " + check.size());
        }
    }
    public static void main(String args[]) throws Exception {

        new Crawlers("wikipedia.org", 6);
    }
}