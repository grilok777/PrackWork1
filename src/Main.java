import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Main {

    static final Semaphore chairs = new Semaphore(5);
    static final Semaphore barista = new Semaphore(2);

    static List<Thread> guestThreads = new ArrayList<>();

    private static boolean isAvailableHours = true;

    public static synchronized boolean isOpen() {
        return isAvailableHours;
    }

    public static synchronized void closeCafe() {
        isAvailableHours = false;
        System.err.println("=============Кав'ярню закрили================");
    }

    public static void main(String[] args) throws InterruptedException {

        Runnable cafe = () -> {
            int i = 0;
            while (isOpen()) {
                Thread guestThread = new Thread(new Client(), String.valueOf(i));
                guestThreads.add(guestThread);
                guestThread.start();

                i++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            /*System.err.println("=============Баристи обслоговують кавомашини================");
            try {
                Thread.sleep(25000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.err.println("=============Баристи закінчили обслоговування================");*/
        };

        Thread cafeThread = new Thread(cafe, "Кав'ярня");
        cafeThread.start();
        Thread.sleep(10000);
        closeCafe();
        cafeThread.join();
        for (Thread guestThread : guestThreads) {
            guestThread.join();
        }

        System.err.println("=============Баристи пішли додому================");
    }

}
