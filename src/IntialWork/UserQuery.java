package IntialWork;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserQuery {
    public static void main(String args[]) throws Exception
    {
        File file=new File("D:\\research 2\\dataset\\gov2.queriesAllRawFile.txt");
        boolean tof=true;
        Scanner manu=new Scanner(file);
        List<String> l1=new ArrayList<String>();
        while(manu.hasNextLine())
        {
            String src=manu.nextLine();
            if(src.contains("<tag>"))
            {
                if(tof==true)
                    tof=false;
                else {
                    src = src.replaceAll("<tag>", "");
                    src = src.replaceAll("</tag>", "");
                    src.replaceAll("\\s+", "");
                    l1.add(src);
                    tof = true;
                }
            }
        }
        PrintWriter writer = new PrintWriter("the-file-name1.txt", "UTF-8");
        for(String h:l1)
        {
            writer.println(h);
        }
        writer.close();
    }
}
