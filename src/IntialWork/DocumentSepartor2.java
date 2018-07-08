package IntialWork;



import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DocumentSepartor2 {
    public static void main(String args[]) throws Exception
    {
        int i=0,document_count=0,query_count=0,f=0;
        while(i<=81)
        {
            //System.out.println("Reading File :"+i);
            File file=new File("D:\\research 2\\Algorithms\\Queries\\File"+i+"\\MyFile"+i+".txt");
            Scanner manu=new Scanner(file);
            int j=0,set=0,file_count=0;
            List<String> l4=new ArrayList<String>();
            while(manu.hasNextLine())
            {

                String h=manu.nextLine();
                if(h.equals("<DOC>"))
                {
                  /*  PrintWriter print=new PrintWriter("D:\\research 2\\Algorithms\\Queries\\File"+i+"\\MyDoc"+i+j+".txt","UTF-8");
                    //print.flush();
                    while((!h.equals("</DOC>")) && manu.hasNextLine())
                    {
                        print.println(h);
                        h=manu.nextLine();
                    }
                    print.close();*/

                    List<String> l2=new ArrayList<String>();
                    int found=0;
                    while((!h.equals("</DOC>")) && manu.hasNextLine())
                    {

                        h = manu.nextLine();
                        if(h.equals("<PERFECT>") || h.equals("<EXCEL>")) {
                            found = 1;
                            query_count++;
                            while(!(h.equals("</PERFECT>")) && !h.equals("</EXCEL>") && manu.hasNextLine() && (!h.equals("</DOC>")))
                            {
                                l2.add(h);
                                h=manu.nextLine();
                                if(h.contains("<SENTENCE>"))
                                {
                                    h=h.replaceAll("<SENTENCE>","");
                                    h=h.replaceAll("</SENTENCE>","");
                                    l4.add(h);
                                }
                            }
                            l2.add(h);
                        }

                    }

                    if(found==1)
                    {
                        set=1;
                        file_count++;
                        FileWriter out=new FileWriter("D:\\research 2\\Algorithms\\Queries\\Document"+f+".txt");
                        BufferedWriter bw=new BufferedWriter(out);
                        bw.flush();
                        for(String g:l2)
                        {
                            bw.write(g);
                            bw.write("\n");
                            bw.flush();
                        }
                        bw.close();
                        j++;
                        f++;
                    }


                }
            }
            FileWriter out2=new FileWriter("D:\\research 2\\Algorithms\\Queries\\File"+i+"\\MainDoc"+i+".txt");
            BufferedWriter bw2=new BufferedWriter(out2);
            bw2.flush();
            for(String l:l4)
            {
                bw2.write(l);
                bw2.write("\n");
                bw2.flush();
            }
            bw2.close();
            i++;
            document_count=file_count+document_count;
            System.out.println("For File :"+i+"===>"+file_count);
            if(set==0)
                System.out.println("Null For :"+i);

            manu.close();
        }
        System.out.println("Total Document Found:"+document_count);
        System.out.println("Total Files :"+query_count);
    }
}
