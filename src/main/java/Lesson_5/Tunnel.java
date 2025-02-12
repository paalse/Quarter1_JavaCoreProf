package Lesson_5;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    Semaphore smp;
    public Tunnel(int count) {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
         smp = new Semaphore(count, true);
    }

    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                smp.acquire();
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                smp.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}