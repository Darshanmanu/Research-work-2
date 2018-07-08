package IntialWork;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Extracter {

    public static void main(String args[]) throws Exception
    {
        File file=new File("D:\\research 2\\Algorithms\\Queries\\File10\\MyFile10.txt");
        Scanner manu=new Scanner(file);
        int count=getCount(file);
        int start=0;
        while(manu.hasNextLine())
        {
            String h=manu.nextLine();
            if(h.contains("<NONE>"))
            {
                PrintWriter print=new PrintWriter("D:\\research 2\\python work\\Testing10.txt");
                while(!h.contains("</NONE>"))
                {
                    h=manu.nextLine();
                    h=h.replaceAll("<SENTENCE>","");
                    h=h.replaceAll("</SENTENCE>","");
                    print.write(h  );
                    print.print("\n");
                    print.flush();
                }
                int i=0;
                start++;
            }
            double per=((double)start/count)*100;
            System.out.print("\rCompleted...."+per+"%");
        }

    }

     public static int getCount(File file) throws  Exception
     {
         Scanner manu=new Scanner(file);
         int count=0;
         while(manu.hasNextLine())
         {
             if(manu.nextLine().contains("<NONE>"))
             {
                 count++;
             }
         }

         return count;
     }
}
