package MayBatchJava.Jun3A;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;

public class StockP {
    public static void main(String[] args) throws Exception {
        String csvFile="stocks.csv";
        List<String> symbolsToFetch=Arrays.asList("AAPL","GOOGL","MSFT","AMZN","TSLA");

        Map<String,Double> allPrices=readStockPricesFromCSV(csvFile);
        Map<String,Double> result=fetchPricesConcurrently(allPrices,symbolsToFetch,4);

        for (Map.Entry<String,Double> entry : result.entrySet()) {
            System.out.println(entry.getKey()+": "+entry.getValue());
        }
    }

    static Map<String,Double> readStockPricesFromCSV(String filePath) throws IOException {
        Map<String,Double> stockPrices=new HashMap<>();
        try (BufferedReader br=new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line=br.readLine()) != null) {
                String[] parts=line.split(",");
                if (parts.length ==2) {
                    stockPrices.put(parts[0].trim(),Double.parseDouble(parts[1].trim()));
                }
            }
        }
        return stockPrices;
    }

    static Map<String,Double> fetchPricesConcurrently(Map<String,Double> allPrices,List<String> symbols,int numThreads) throws InterruptedException,ExecutionException {
        ExecutorService executor=Executors.newFixedThreadPool(numThreads);
        List<Future<Map<String,Double>>> futures=new ArrayList<>();

        int size=symbols.size();
        int chunkSize=(int) Math.ceil(size / (double) numThreads);
        for (int i=0; i<size; i += chunkSize) {
            int start=i;
            int end=Math.min(i+chunkSize,size);
            List<String> subList=symbols.subList(start,end);
            Callable<Map<String,Double>> task=() -> {
                Map<String,Double> partialResult=new HashMap<>();
                for (String symbol : subList) {
                    if (allPrices.containsKey(symbol)) {
                        partialResult.put(symbol,allPrices.get(symbol));
                    }
                }
                return partialResult;
            };
            futures.add(executor.submit(task));
        }

        Map<String,Double> finalResult=new HashMap<>();
        for (Future<Map<String,Double>> future : futures) {
            finalResult.putAll(future.get());
        }

        executor.shutdown();
        return finalResult;
    }
}
