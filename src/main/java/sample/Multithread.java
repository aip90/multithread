package sample;

//class MyTask extends  Thread{
//    @Override
//    public void run(){
//        for (int x=1;x<=5;x++){
//            System.out.println("@@ Print doc #"+ x + " worker thread");
//        }
//    }
//}

class CA {

}

class MyTask extends CA implements Runnable{
    @Override
    public void run(){
        for (int x=1;x<=5;x++){
            System.out.println("@@ Print doc #"+ x + " two");
        }
    }
}

class YourTask extends CA implements Runnable{
    @Override
    public void run(){
        for (int x=1;x<=5;x++){
            System.out.println("** Print doc #"+ x + " three");
        }
    }
}

public class Multithread {

    public static void main(String[] args) {

        System.out.println("==App Started==");

        //extend Thread
//        MyTask myTask = new MyTask();
//        myTask.start();

        Runnable runnable = new MyTask();
        Thread thread = new Thread(runnable);
        thread.start();

        //option1 define thread runnable
//        Thread thread1 = new Thread(new YourTask());
//        thread1.start();

        //option2 define thread runnable
        new Thread(new YourTask()).start();

        for (int x=1;x<=5;x++){
            System.out.println("Print doc #"+ x + " one");
        }

        System.out.println("==app finished==");
    }
}
