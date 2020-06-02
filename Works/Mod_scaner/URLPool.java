package Mod_scaner;

import java.util.LinkedList;


public class URLPool {
    int depth;
    int count_Thread;
    LinkedList<URLDepthPair> uncheck = new LinkedList<>();
    LinkedList<URLDepthPair> check = new LinkedList<>();

    public URLPool(int depth) {
        this.depth = depth;
        count_Thread = 0;
    }

    public synchronized URLDepthPair getPair() {
        while (uncheck.size() == 0) {
            count_Thread++;
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("InterruptedException");
            }
            count_Thread--;
        }

        return uncheck.removeFirst();

    }

    public synchronized void addPair(URLDepthPair pair) {
        if (check.contains(pair) == false) {
            check.add(pair);
            if (pair.get_depth() < depth) {
               uncheck.add(pair);
                notify();
            }
        }
    }

    public synchronized int getWait() {
        return count_Thread;
    }

    public LinkedList<URLDepthPair> getChecked() {
        return check;
    }
}
