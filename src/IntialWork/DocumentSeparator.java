package IntialWork;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DocumentSeparator {
    public static void main(String args[]) throws Exception
    {
        List<Integer> l1=getListHere();
        File file=new File("D:\\research 2\\dataset\\grade.txt");
        Scanner manu=new Scanner(file);
        int i=0,l=1;
        String h="";
        String change="701";
        int c=701;
        String next="702";
        int c2=702;
        while(manu.hasNextLine())
        {
             h=manu.nextLine();
           if(h.equals("<TARGET_QID>"+change+"</TARGET_QID>"))
           {
               System.out.println("For Change :"+change);
               new File("D:\\research 2\\Algorithms\\Queries\\File"+i+"").mkdirs();
               PrintWriter print=new PrintWriter("D:\\research 2\\Algorithms\\Queries\\File"+i+"\\MyFile"+i+".txt","UTF-8");
               while(!h.equals("<TARGET_QID>"+next+"</TARGET_QID>"))
               {
                  // System.out.println("Adding");
                   print.println(h);
                   h=manu.nextLine();
               }
               print.close();
               i++;
               c=l1.get(l);
               l++;
               change=Integer.toString(c);
               c2=c+1;
               next=Integer.toString(c2);
               if(l>l1.size())
                   break;

           }

        }

    }

    public static List<Integer> getListHere() throws Exception
    {
        List<Integer> l1=new ArrayList<Integer>();
        File file=new File("D:\\research 2\\Algorithms\\MainQuery.txt");
        Scanner manu=new Scanner(file);
        while(manu.hasNextLine())
        {
            String h[]=manu.nextLine().split(",");
            l1.add(Integer.parseInt(h[0]));
        }
        return l1;
    }
}
