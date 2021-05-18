public class Exercise2 {
    final static int NUMBER = 15;
    static Integer i = 1;
    static StringBuilder builder = new StringBuilder();

    public static void main(String[] args) {

        Thread a = new Thread(() ->
        {
            while (true) {
                synchronized (i) {
                    fizz();
                    try {
                        i.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread b = new Thread(() ->
        {
            while (true) {
                synchronized (i) {
                    buzz();
                    try {
                        i.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread c = new Thread(() ->
        {
            while (true) {
                synchronized (i) {
                    fizzbuzz();
                    try {
                        i.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread d = new Thread(() ->
        {
            while (true) {
                synchronized (i) {
                    number();
                    try {
                        i.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        a.start();
        b.start();
        c.start();
        d.start();
        while (i <= NUMBER) {
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (i) {

                i.notifyAll();
                i++;
            }
        }
        System.out.println(builder);
        a.stop();
        b.stop();
        c.stop();
        d.stop();
    }

    private static void fizz() {
        if (i % 3 == 0 && i % 5 != 0) {
            builder.append("fizz, ");
        }
    }

    private static void buzz() {

        if (i % 5 == 0 && i % 3 != 0) {
            builder.append("buzz, ");
        }
    }

    private static void fizzbuzz() {

        if (i % 3 == 0 && i % 5 == 0) {
            builder.append("fizzbuzz, ");
        }
    }

    private static void number() {

        if (i % 3 != 0 && i % 5 != 0) {
            builder.append(String.format("%d, ", i));
        }
    }
}