package MainAlgorithm;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.PrimitiveIterator;
import java.util.Scanner;

public class PreprocessingQA {

    public static void processing() throws  Exception
    {

        File file=new File("D:\\research 2\\Algorithms\\Yahoo\\queries.txt");
        Scanner manu=new Scanner(file);
        int i=1;
        int fileid=0;
        int k=0;
        while(manu.hasNext())
        {
          String h=manu.next();
          int j=0;
          while(h.contains("<UVCE>")) {
              System.out.println(fileid);
              h=manu.next();
              while(!h.equals("<UVCE>"))
              {
                  if(h.equals("<answer>"))
                  {
                      System.out.println("Getting ere");
                      PrintWriter print=new PrintWriter("D:\\research 2\\Algorithms\\Yahoo\\AllFiles\\Doc"+k+".txt","UTF-8");
                      h=manu.next();
                      print.print("<SENTENCE> ");
                      print.print(h+" ");
                      while(!h.equals("<answer>") && !h.contains("<UVCE>"))
                      {
                          h=manu.next();
                          if(h.contains(".") && h.endsWith("."))
                          {
                              print.print(h);
                              print.println(" <SENTENCE>");
                              print.print("<SENTENCE> ");
                          }
                          else print.print(h+" ");
                          print.flush();
                      }
                      k++;
                      System.out.println("End :"+h);
                  }
                  if(h.contains("<UVCE>"))
                      break;
                  h=manu.next();
              }
              fileid++;
          }

        }
    }
}
