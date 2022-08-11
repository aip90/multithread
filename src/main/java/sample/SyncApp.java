package sample;

class Printer{
//    synchronized void printDocument (int numOfCopies, String docName){
    void printDocument (int numOfCopies, String docName){
        for (int x=1;x<=numOfCopies;x++){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Print doc #"+ docName + " " + x);
        }
    }
}

class MyThread extends Thread {
    Printer pRef;

    MyThread(Printer p){
        this.pRef = p;
    }

    @Override
    public void run(){
        synchronized (pRef){
            pRef.printDocument(7, "dayatPrintDoc.pdf");
        }

    }
}

class YourThread extends Thread {
    Printer pRef;

    YourThread(Printer p){
        this.pRef = p;
    }

    @Override
    public void run(){
        synchronized (pRef){
            pRef.printDocument(8, "saripPrintDoc.pdf");
        }

    }
}

public class SyncApp {

    public static void main(String[] args) {

        System.out.println("=====Application Started=====");

        Printer printer = new Printer();
        //printer.printDocument(10, "aipPrintDoc.pdf");

        MyThread myThread =  new MyThread(printer); // reference to printer object
        YourThread yourThread = new YourThread(printer);  // reference to printer object
        myThread.start();
//        try {
//            myThread.join();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        yourThread.start();

        System.out.println("=====Application Finished=====");
    }
}
