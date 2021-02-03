package socketserver.cpuutils;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.ThreadMXBean;

public class CPUMonitorCalc {

    private static CPUMonitorCalc instance = new CPUMonitorCalc();

    private OperatingSystemMXBean osMxBean;
    private ThreadMXBean threadBean;
    private long preTime = System.nanoTime();
    private long preUsedTime = 0;

    private CPUMonitorCalc() {
        osMxBean = ManagementFactory.getOperatingSystemMXBean();
        threadBean = ManagementFactory.getThreadMXBean();
    }

    public static CPUMonitorCalc getInstance() {
        return instance;
    }

    public double getProcessCpu() {
        long totalTime = 0;
        for (long id : threadBean.getAllThreadIds()) {
            totalTime += threadBean.getThreadCpuTime(id);
        }
        long curtime = System.nanoTime();
        long usedTime = totalTime - preUsedTime;
        long totalPassedTime = curtime - preTime;
        preTime = curtime;
        preUsedTime = totalTime;
        return (((double) usedTime) / totalPassedTime / osMxBean.getAvailableProcessors()) * 100;
    }

    public static int getCpuPercent() throws InterruptedException {

            Thread th1 = new Thread(() -> {
                while (true) {
                    long bac = 1000000;
                    bac = bac >> 1;
                }
            });
            th1.start();

            Thread th2 = new Thread(() -> {
                while (true) {
                    long bac = 1000000;
                    bac = bac >> 1;
                }
            });
            th2.start();

        int percent = 0;
        for (int i = 0; i < 2; i++) {
            Thread.sleep(5000);
            double processCpu = CPUMonitorCalc.getInstance().getProcessCpu();
            System.out.println(processCpu);
            percent += processCpu;
        }

        th1.stop();
        th2.stop();
        return percent / 2;

    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(getCpuPercent());
    }
}
