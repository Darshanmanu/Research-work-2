package IntialWork;

import java.io.File;
import java.util.Scanner;

public class ExtractionChecking {
    public static void main(String args[]) throws Exception
    {
        File file=new File("D:\\research 2\\dataset\\grade.txt");
        Scanner manu=new Scanner(file);
        long start=System.nanoTime();
        int count=0;
        while(manu.hasNextLine()) {
            String h=manu.nextLine();
            if(h.equals("<PERFECT>") || h.equals("<EXCEL>"))
                count++;

        }
        long end=System.nanoTime();
        System.out.println("count :"+count);
    }
}
