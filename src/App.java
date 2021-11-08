import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class TUPLE< String,BigDecimal>
    {
        java.lang.String region;
        java.math.BigDecimal totalSum;

        TUPLE()
        {}

        TUPLE( java.lang.String region, java.math.BigDecimal totalSum)        
        {
            this.region = region;
            this.totalSum = totalSum;
        }
        public java.lang.String getRegion()
        {
            return this.region;
        }
        public java.math.BigDecimal getTotalSum()
        {
            return this.totalSum;
        }
        public void setTotalSum(java.math.BigDecimal sum)
        {
            this.totalSum = sum;
        }
        public void setRegion(java.lang.String region)
        {
            this.region = region;
        }
    }
    

public class App {

    public static void main(String[] args) throws Exception {
        

        // Starting the timer
        long startTime = System.currentTimeMillis();

        CompletableFuture<String[]> Country = CompletableFuture.supplyAsync(
            () -> readFromFiles.readFromCountry()
        );

        CompletableFuture<String[]> Region = CompletableFuture.supplyAsync(
            () -> readFromFiles.readFromRegion()
        );

        CompletableFuture<BigDecimal[]> arrayFirst = CompletableFuture.supplyAsync(
            () -> readFromFiles.readFromFirstArray()
        );

        CompletableFuture<BigDecimal[]> arraySecond = CompletableFuture.supplyAsync(
            () -> readFromFiles.readFromSecondArray()
        );

        CompletableFuture<BigDecimal[]> arrayThird = CompletableFuture.supplyAsync(
            () -> readFromFiles.readFromThirdArray()
        );

        CompletableFuture<BigDecimal[]> arrayFourth = CompletableFuture.supplyAsync(
            () -> readFromFiles.readFromFourthArray()
        ); 
       

        String[] country = Country.join();
        String[] region = Region.join();
        BigDecimal[] arr1 = arrayFirst.join();
        BigDecimal[] arr2 = arraySecond.join();
        BigDecimal[] arr3 = arrayThird.join();
        BigDecimal[] arr4 = arrayFourth.join();

        // Stopping the timer
        long stopTime = System.currentTimeMillis();

        // expression which we want to perform on all arrays
        String expression = "a1 + a2 + a3 + a4";

        ConcurrentHashMap<String, TUPLE> map = new ConcurrentHashMap<>();

        int size = arr1.length;
        System.out.println("Size : " + size);
        
        List<BigDecimal> list = new ArrayList(size);
        
        CompletableFuture<?>[] futures = new CompletableFuture<?>[size];

        for( int i = 0 ; i < size; i++)
        {
            BigDecimal a1 = arr1[i];
            BigDecimal a2 = arr2[i];
            BigDecimal a3 = arr3[i];
            BigDecimal a4 = arr4[i];
            String c = country[i];
            String r = region[i];
            final int k = i;
            
            futures[k] = CompletableFuture.supplyAsync( 
                () -> calculate.calculation(expression,a1,a2,a3,a4,c,r,list,k,map)
            ); 
        }
      
        for(int i = 0 ; i < size; i++)
        {
            BigDecimal big = (BigDecimal) futures[i].get();
            
            String c = country[i];
            String r = region[i];
            
             // If map already contains the country as key
             if(map.containsKey(c))
             {
                 // add already contains sum + newly calculated sum.
                 BigDecimal finalResult =  map.get(c).getTotalSum().add(big);

                 TUPLE re = map.get(c);

                 // Setting total sum.
                 re.setTotalSum(finalResult);

                 // storing newly calculated sum into map.
                 map.put(c, re);
             }
             else
             {
                 // If map does not contains country as key.
                 TUPLE re = new TUPLE();

                 // set newly calculated sum into value part.
                 re.setTotalSum(big);

                 // setting region.
                 re.setRegion(r);

                 // storing newly calculated sum into map.
                 map.put(c, re);
             }
        }
        
        // printing the map
        for (Map.Entry<String, TUPLE> e : map.entrySet())
        {
            System.out.println(e.getValue().getRegion() + "\t\t" +  e.getKey() + "\t\t" + e.getValue().getTotalSum());
        }

        System.out.println();
        
        // Stopping the timer
        long stopTime1 = System.currentTimeMillis();    

        System.out.println("Total Time : " + (stopTime1 - startTime) + " " + "ms");   
    } 

    
}
