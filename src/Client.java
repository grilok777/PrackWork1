public class Client implements Runnable {

    @Override
    public void run() {
        try {
            System.err.printf("Клієнти з потоку %s прийшли в кав'ярню і шукають місце \n", Thread.currentThread().getName());
            Thread.sleep(20);
            Main.chairs.acquire();
            System.out.printf("Клієнти з потоку %s сіли за вільні місця і позвали баристу \n", Thread.currentThread().getName());
            Thread.sleep(20);
            Main.barista.acquire();

            System.out.printf("Бариста підійшов до клієнтів з потоку %s \n", Thread.currentThread().getName());

            Thread.sleep(2000);

            System.out.printf("Бариста прийняв замовлення від клієнтів з потоку %s \n", Thread.currentThread().getName());
            Thread.sleep(20);
            Main.barista.release();


            Thread.sleep(4000);

            Main.barista.acquire();
            System.out.printf("Бариста приніс замовлення клієнтам з потоку %s \n", Thread.currentThread().getName());
            Thread.sleep(20);
            Main.barista.release();

            Thread.sleep(4000);

            System.err.printf("Клієнти з потоку %s попили кави і пішли \n", Thread.currentThread().getName());
            Thread.sleep(20);
            Main.chairs.release();
            //}

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
