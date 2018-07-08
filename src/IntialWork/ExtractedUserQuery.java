package IntialWork;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class ExtractedUserQuery {
    public static void main(String args[]) throws Exception
    {
        File file=new File("D:\\research 2\\Algorithms\\gov2.query.json");
        Scanner manu=new Scanner(file);
        int i=0;
        FileWriter out=new FileWriter("D:\\research 2\\Algorithms\\MainQuery.txt");
        BufferedWriter bw=new BufferedWriter(out);
        bw.flush();
        while(manu.hasNextLine()) {
            String h=manu.nextLine();
            if(i>=2)
            {
                String arr[]=h.split("\"text\" : ");
                arr[1]=arr[1].replaceAll("\"","");
                arr[1]=arr[1].replaceAll("}","");
                arr[1]=arr[1].replaceAll(",","");
                String arr2[]=h.split(",");
                String arr3[]=arr2[0].split(": ");
                arr3[1]=arr3[1].replaceAll("\"","");
                 bw.write(arr3[1]+","+arr[1]);
                bw.write("\n");
                bw.flush();
                System.out.println(arr3[1]);
            }
            if(i==83)
                break;
            i++;
        }
    }
}
