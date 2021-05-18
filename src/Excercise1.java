class Exercise1 {
    private static final Object monitor = new Object();
    private static int i = 0;

    public static void main(String[] args) {
        SecondPrinter thread1 = new SecondPrinter();
        FiveSecondPrinter thread2 = new FiveSecondPrinter();
        thread1.start();
        thread2.start();
    }


    static class SecondPrinter extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (monitor) {
                    System.out.println(String.format("С начала сессии прошло %d секунд", ++i));
                    monitor.notifyAll();
                }
            }
        }
    }

    static class FiveSecondPrinter extends Thread {
        @Override
        public void run() {
            while (true) {
                synchronized (monitor) {
                    try {
                        if (i % 5 == 0&&i>0) {
                            System.out.println("прошло 5 секунд");
                        }
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
