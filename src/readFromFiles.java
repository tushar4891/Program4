import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;

public class readFromFiles {
    
    static String[] country = new String[500000];
    static String[] region = new String[500000];
    static BigDecimal[] arr1 = new BigDecimal[500000];
    static BigDecimal[] arr2 = new BigDecimal[500000];
    static BigDecimal[] arr3 = new BigDecimal[500000];
    static BigDecimal[] arr4 = new BigDecimal[500000];
    
    public static String[] readFromCountry() 
    {
        //System.out.println(" In Country");

        BufferedReader countryReader;
        try {
            countryReader = new BufferedReader(new FileReader("Country.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
            return null;
        }
        
        String line;
        int index = 0;
        try {
            while((line = countryReader.readLine()) != null)
            {
                country[index] = line;
                index++;
            }
        } catch (IOException e) {
            System.out.println("Problem while reading a line");
        }
        return country;
    }

    public static String[] readFromRegion() 
    {
        //System.out.println(" In Region");

        BufferedReader regionReader;
        try {
            regionReader = new BufferedReader(new FileReader("Region.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
            return null;
        }
        int index = 0;
        String line;

        try {
            while((line = regionReader.readLine()) != null)
            {
                region[index] = line;
                index++;
            }
        } catch (IOException e) {
            System.out.println("Problem while reading a line");
        }
        return region;
    }

    public static BigDecimal[] readFromFirstArray() 
    {
        //System.out.println(" In arr1");

        BufferedReader array1Reader;
        try {
            array1Reader = new BufferedReader(new FileReader("Array1.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
            return null;
        }
        int index = 0;
        String line;
        try {
            while((line = array1Reader.readLine()) != null)
            {
                BigDecimal bigDecimal = new BigDecimal(line);
                arr1[index] = bigDecimal;
                index++;
            }
        } catch (IOException e) {
            System.out.println("Problem while reading a line");
        }
        return arr1;
    }

    public static BigDecimal[] readFromSecondArray() 
    {        
        //System.out.println(" In arr2");

        BufferedReader array2Reader;
        try {
            array2Reader = new BufferedReader(new FileReader("Array2.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
            return null;
        }
        int index = 0;
        String line;
        try {
            while((line = array2Reader.readLine()) != null)
            {
                BigDecimal bigDecimal = new BigDecimal(line);
                arr2[index] = bigDecimal;
                index++;
            }
        } catch (IOException e) {
            System.out.println("Problem while reading a line");
        }
        return arr2;
    }

    public static BigDecimal[] readFromThirdArray() 
    {
        //System.out.println(" In arr3");

        BufferedReader array3Reader;
        try {
            array3Reader = new BufferedReader(new FileReader("Array3.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
            return null;
        }
        int index = 0;
        String line;
        try {
            while((line = array3Reader.readLine()) != null)
            {
                BigDecimal bigDecimal = new BigDecimal(line);
                arr3[index] = bigDecimal;
                index++;
            }
        } catch (IOException e) {
            System.out.println("Problem while reading a line");
        }
        return arr3;
    }

    public static BigDecimal[] readFromFourthArray() 
    {
        //System.out.println(" In arr4");

        BufferedReader array4Reader;
        try {
            array4Reader = new BufferedReader(new FileReader("Array4.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found" );
            return null;
        }
        int index = 0;
        String line;
        try {
            while((line = array4Reader.readLine()) != null)
            {
                BigDecimal bigDecimal = new BigDecimal(line);
                arr4[index] = bigDecimal;
                index++;
            }
        } catch (IOException e) {
            System.out.println("Problem while reading a line");
        }
        return arr4;
    }
}
