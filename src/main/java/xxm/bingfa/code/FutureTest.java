package xxm.bingfa.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class FutureTest {


    public static void main(String[] args) {

        // 建立线程池 与 动态结果数组
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(6);
        List<Future<Long>> resultList = new ArrayList();

        // 定义10个任务分别负责一定范围内的元素累计
        for (int i = 0; i < 10; i++) {
            CalTask calTask = new CalTask(i*100000000+1, (i+1)*100000000);
            Future<Long> result = executor.submit(calTask);
            resultList.add(result);
        }

        // 每隔50毫秒遍历一遍所有动态结果，直到所有任务执行完毕
        do {
            System.out.printf("Main: 已经完成多少个任务: %d\n",executor.getCompletedTaskCount());
            for (int i = 0; i < resultList.size(); i++) {
                Future<Long> result = resultList.get(i);
                System.out.printf("Main: Task %d is %s\n",i,result.isDone());
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } while (executor.getCompletedTaskCount() < resultList.size());

        // 若所有任务执行完毕，则对执行结果进行累计
        long total = 0;
        for (int i = 0; i < resultList.size(); i++) {
            Future<Long> result = resultList.get(i);
            long sum = 0;
            try {
                sum = result.get();
                total += sum;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("total is: %d\n", total);
        executor.shutdown();
    }




}

class CalTask implements Callable<Long> {
    private int startNum;
    private int endNum;

    public CalTask(int startNum, int endNum) {
        this.startNum = startNum;
        this.endNum = endNum;
    }

    @Override
    public Long call() throws Exception {
        long sum = 0;
        for (int i = startNum; i <= endNum; i++) {
            sum += i;
        }
        Thread.sleep(new Random().nextInt(100));
        System.out.printf("%s: %d\n", Thread.currentThread().getName(), sum);
        return sum;
    }
}