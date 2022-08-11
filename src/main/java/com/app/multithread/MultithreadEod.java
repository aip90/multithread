package com.app.multithread;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class MultithreadEod implements Runnable {

    private static int threadNumber;

    private static List<Eod> eods;

    public MultithreadEod(
            int threadNumber,
            List<Eod> eods){
        this.threadNumber = threadNumber;
        this.eods = eods;
    }

    @Override
    public void run() {

        //synchronized (eods){
            endOfDay(eods);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
      // }
    }

    private static void endOfDay(List<Eod> eods){

        writeCsv(eods, threadNumber);
    }

    public static List<Eod> readCsv() {

        String filePath =
                "C:\\Users\\Useradmin\\Documents\\learning\\aip90\\" +
                        "multithread\\src\\main\\resources\\csv\\Before Eod.csv";

        System.out.println("Starting read Before Eod.csv file: " + filePath);

        BufferedReader reader = null;
        List<Eod> eods = null;
        try {
            eods = new ArrayList<Eod>();
            String line = "";
            reader = new BufferedReader(new FileReader(filePath));
            reader.readLine();

            while((line = reader.readLine()) != null) {
                String[] fields = line.split(";");

                if(fields.length > 0) {
                    Eod eod = new Eod();
                    eod.setId(fields[0]);
                    Integer idParse = Integer.valueOf(eod.getId());
                    eod.setNama(fields[1]);
                    eod.setAge(fields[2]);
                    eod.setBalancedOld(fields[3]);
                    eod.setBalanced(fields[3]);
                    Integer balancedParse = Integer.valueOf(eod.getBalanced());
                    if (idParse >= 1 && idParse <= 100) {
                        balancedParse += 10;
                        eod.setBalanced(Integer.toString(balancedParse));
                    }
                    eod.setPreviousBalanced(fields[4]);
                    Integer previousBalancedParse = Integer.valueOf(eod.getPreviousBalanced());
                    Integer averageBalanced =
                            (balancedParse + previousBalancedParse) / 2;
                    eod.setAverageBalanced(Integer.toString(averageBalanced));
                    Integer freeTransfer =
                            balancedParse >= 100 && balancedParse <= 150 ? 5 :
                                    balancedParse > 150 ? 25 : 0;
                    eod.setFreeTransfer(freeTransfer.toString());
                    eods.add(eod);
                }
            }

            for(Eod eodData: eods) {
                log.info("{} {} {} {} {} {} {} {}",
                        eodData.getId(), eodData.getNama(), eodData.getAge(),
                        eodData.getBalancedOld(), eodData.getBalanced(),
                        eodData.getPreviousBalanced(), eodData.getAverageBalanced(),
                        eodData.getFreeTransfer());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                reader.close();
                System.out.println("Finished read Before Eod.csv file: " + filePath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return eods;
    }

    public static void writeCsv(
            List<Eod> eods,
            int threadNumber) {

        FileWriter fileWriter = null;
        String filePath =
                "C:\\Users\\Useradmin\\Documents\\learning\\aip90\\" +
                        "multithread\\src\\main\\resources\\csv\\After Eod.csv";
        try {

            System.out.println("Starting write After Eod.csv file: " + filePath);

            fileWriter = new FileWriter(filePath);
            fileWriter.append(
                    "id;Nama;Age;Balanced;No 2b Thread-No;" +
                            "No 3 Thread-No;Previous Balanced;Average Balanced;" +
                            "No 1 Thread-No;Free Transfer;No 2a Thread-No\n");

            for(Eod eod: eods) {
                fileWriter.append(eod.getId());
                fileWriter.append(";");
                fileWriter.append(eod.getNama());
                fileWriter.append(";");
                fileWriter.append(eod.getAge());
                fileWriter.append(";");
                fileWriter.append(eod.getBalanced());
                fileWriter.append(";");
                fileWriter.append(Thread.currentThread().getName()+"#No2b#"+threadNumber);
                fileWriter.append(";");
                fileWriter.append(Thread.currentThread().getName()+"#No3#"+threadNumber);
                fileWriter.append(";");
                fileWriter.append(eod.getPreviousBalanced());
                fileWriter.append(";");
                fileWriter.append(eod.getAverageBalanced());
                fileWriter.append(";");
                fileWriter.append(Thread.currentThread().getName()+"#No1#"+threadNumber);
                fileWriter.append(";");
                fileWriter.append(eod.getFreeTransfer());
                fileWriter.append(";");
                fileWriter.append(Thread.currentThread().getName()+"#No2a#"+threadNumber);
                fileWriter.append("\n");

               log.info("{} {} {} {} {} {} {} {} {} {} {} {}",
                        eod.getId(), eod.getNama(), eod.getAge(),
                        eod.getBalancedOld(), eod.getBalanced(),
                        Thread.currentThread().getName()+"#No2b#"+threadNumber,
                        Thread.currentThread().getName()+"#No3#"+threadNumber,
                        eod.getPreviousBalanced(), eod.getAverageBalanced(),
                        Thread.currentThread().getName()+"#No1#"+threadNumber,
                        eod.getFreeTransfer(), Thread.currentThread().getName()+"#No2a#"+threadNumber);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
                System.out.println("Finished write After Eod.csv file: " + filePath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
