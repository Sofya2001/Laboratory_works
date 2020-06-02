package Mod_scaner;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

    public class CrawlerTask implements Runnable {

        public void searchURL() throws IOException {
            Socket socket;
            URLDepthPair pair = pool.getPair();
            int depth = pair.get_depth();
            try {
                socket = new Socket(pair.get_host(), 80);
            } catch (UnknownHostException e) {
                System.err.println(e);
                return;
            }
            socket.setSoTimeout(4000);
            PrintWriter myWriter = new PrintWriter(socket.getOutputStream(), true);
            myWriter.println("GET " + pair.get_path() + " HTTP/1.1");
            myWriter.println("Host: " + pair.get_host());
            myWriter.println("Connection: close");
            myWriter.println();

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String str;
            while ((str = reader.readLine()) != null) {
                while (str.contains("href=\"")) {
                    String content;
                    try {
                        str = str.substring(str.indexOf("href=\"") + 6);
                        content = str.substring(0, str.indexOf('\"'));
                        if (!content.startsWith("http"))
                            content = content.startsWith("/") ? pair.get_link() + content : pair.get_link() + "/" + content;
                    } catch (StringIndexOutOfBoundsException e) {
                        break;
                    }
                    pool.addPair(new URLDepthPair(content, depth + 1));
                }
            }
            reader.close();
            socket.close();
        }

        @Override
        public void run() {
            while (true) {
                try {
                    searchURL();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        public static void main(String[] args) {

            String host = "https://www.google.com/";
            int depth = 2;
            int countThread = 6;

            URLPool pool = new URLPool(depth);
            pool.addPair(new URLDepthPair(host, 0));

            for (int m = 0; m < countThread; m++) {
                Thread t = new Thread(new CrawlerTask(pool));
                t.start();
            }

            while (pool.getWait() != countThread) {
            }
            int count = 0;
            for (URLDepthPair pair : pool.getChecked()) {
                count++;

                System.out.printf("%s%-90s%s%n", count + " " + "просмотренная ссылка:", pair.get_url(), "глубина поиска: " + pair.get_depth());
            }
            System.exit(0);
        }


        URLPool pool;

        public CrawlerTask(URLPool pool) { // Конструктор класса
            this.pool = pool;
        }

    }
