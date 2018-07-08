package MainAlgorithm;

import java.io.File;
import java.util.*;

public class DocumentOpt {
    private static  HashMap<Integer,String> usersQuery=new HashMap<Integer,String>();
    private static double lamda=0.4;
    private static HashMap<String,Double> frequency[];
    private static HashMap<List<String>,Double> Result=new HashMap<List<String>, Double>();

    public  static void  getUserQuery() throws Exception
    {
        Scanner manu=new Scanner(System.in);
        System.out.println("Enter number of Queries");
        int n=manu.nextInt();
        int i;
        String t=manu.nextLine();
        for(i=0;i<n;i++)
            usersQuery.put(i,manu.nextLine());
        System.out.println("Started..");
        frequency=new HashMap[n];
        for(i=0;i<n;i++)
            frequency[i]=new HashMap<String, Double>();
        for(Integer usr:usersQuery.keySet())
        {
            String arr[]=usersQuery.get(usr).split(" ");
            for(String h:arr)
            {
                double iweight=calculateInvertedFreq(h);
                frequency[usr].put(h,iweight);
            }
            System.out.println(frequency[usr]);
        }
    }

    public static void queryProcessing() throws Exception
    {
        for(Integer in:usersQuery.keySet())
        {
            String iquery=usersQuery.get(in);
            calculateDocOpt(iquery,in);
            getResult(in);
        }

    }

    public static void queryOptProcessing() throws Exception
    {
        for(Integer in:usersQuery.keySet())
        {
            String iquery=usersQuery.get(in);
            calculateQueryOpt(iquery,in);
            getResult(in);
        }
    }


    public static void calculateDocOpt(String iquery,int qid)  throws Exception
    {
        String words[]=iquery.split(" ");
        File file=new File("D:\\research 2\\Algorithms\\Queries\\File"+qid+"\\DocQ");
        File listf[]=file.listFiles();
        for(File f:listf) {
            String fname=f.getName();
            System.out.println(fname);
            HashMap<String,Integer> queries=new HashMap<String, Integer>();
            List<String> DocSen=getDocumentSentence(fname,qid);
            for (String iword : words) {
                int weight = calculateWeight(iword, qid,fname,DocSen);
                if(weight>0)
                    queries.put(iword,weight);
            }
            if(queries.size()>0)
            {
                choseSentence(queries,f.getName(),DocSen,qid);
            }

        }

    }

    public static void calculateQueryOpt(String iquery,int qid)  throws Exception
    {
        String words[]=iquery.split(" ");
        File file=new File("D:\\research 2\\Algorithms\\Queries\\File"+qid+"\\DocQ");
        File listf[]=file.listFiles();
        for(File f:listf) {
            String fname=f.getName();
            System.out.println(fname);
            HashMap<String,Integer> queries=new HashMap<String, Integer>();
            List<String> DocSen=getDocumentSentence(fname,qid);
            for (String iword : words) {
                int weight = calculateQueryWeight(iquery,iword);
                if(weight>0)
                    queries.put(iword,weight);
            }
            if(queries.size()>0)
            {
                choseSentence(queries,f.getName(),DocSen,qid);
            }

        }

    }

    public static int calculateQueryWeight(String iquery,String word)
    {
        int count=0;
        String arr[]=iquery.split(" ");
        for(String s:arr)
        {
            if(s.equals(word))
                count++;
        }

        return count;
    }


    public static void choseSentence(HashMap<String,Integer> q,String name,List<String> DocSen,int qid)
    {
        String mainanswer="";
        double totalcost=0;
        int len=0;
        System.out.println("FOR ==>:"+name);
        HashMap<Double,List<String>> status1=new HashMap<Double, List<String>>();
        List<Double> dl=new ArrayList<Double>();
        for(String d:DocSen)
        {
            boolean sel=false;
            double cost=0;
            double val2=0;
            for(String h:q.keySet()) {
                double val = lamda * q.get(h) * frequency[qid].get(h);
                if (d.contains(h)) {
                    sel = true;
                    cost = cost + val;
                }
                val2=val2+(1-lamda)*q.get(h)*frequency[qid].get(h);
            }
            cost=cost+val2;
            if(sel)
            {
                d=d.replaceAll("<SENTENCE>","");
                d=d.replaceAll("</SENTENCE>","");
                if(status1.containsKey(cost))
                {
                    List<String> l2=status1.get(cost);
                    l2.add(d);
                    status1.put(cost,l2);
                }
                else{
                    List<String> l3=new ArrayList<String>();
                    l3.add(d);
                    status1.put(cost,l3);
                    dl.add(cost);
                }
            }
        }
        Collections.sort(dl);
        List<String> answer=new ArrayList<String>();
        boolean over=false;
        for(Double df:dl)
        {
            List<String> ret=status1.get(df);
            for(String t:ret)
            {
                String arr[]=t.split(" ");
                if(len+arr.length>60)
                {
                    over=true;
                    break;
                }
                else if(!answer.contains(t)){

                   answer.add(t);
                   len=len+arr.length;
                   totalcost=totalcost+df;
                }
            }
            if(over)
                break;
        }

        for(String j:answer)
            System.out.println(j);
        System.out.println("<--COST-->:"+totalcost);
        Result.put(answer,totalcost);
    }

    public static List<String> getDocumentSentence(String fname,int qid) throws Exception
    {
        File file=new File("D:\\research 2\\Algorithms\\Queries\\File"+qid+"\\DocQ\\"+fname);
        Scanner manu=new Scanner(file);
        List<String> ret=new ArrayList<String>();
        while(manu.hasNextLine())
        {
            ret.add(manu.nextLine());
        }
      return ret;
    }

    public static int calculateWeight(String word,int qid,String fname,List<String> docq) throws Exception
    {
        int fword=frequencyCalculator(word,qid,fname,docq);
        return fword;
    }

    public static int frequencyCalculator(String w,int qid,String fname,List<String> docq)
    {

        int count=0;
        for(String h:docq) {
            String arr[] = h.split(" ");
            for (String q : arr) {
                if (q.equals(w))
                    count++;
            }
        }

        return count;
    }

    public static double calculateInvertedFreq(String iquery) throws Exception
    {
        int n=1014;
        int i=0;
        int count=0;
        for(i=0;i<n;i++)
        {
            File file=new File("D:\\research 2\\Algorithms\\Queries\\Document"+i+".txt");
            Scanner manu=new Scanner(file);
            while(manu.hasNextLine())
            {
                String h=manu.nextLine();
                if(h.contains(iquery))
                {
                    count++;
                    break;
                }
            }
        }
        if(count==0) {
            return 0;

        }
        double res=n/count;
        res=Math.log(1+res);
        return res;
    }

    public static void getResult(int in) {
        List<String> mainswer=new ArrayList<String>();
        double cost=0;
        int c=0;
        for(List<String> h:Result.keySet())
        {
            if(Result.get(h)>cost)
            {
                cost=Result.get(h);
                mainswer=h;
            }
        }

        System.out.println("====================================>Final Result<==========================================");
        System.out.println("QUESTION :"+usersQuery.get(in));
        System.out.println("BEST ANSWER :");
        for(String g:mainswer)
            System.out.println(g);
        System.out.println("TOTAL COST OF ANSWER :"+cost);

        Result.clear();
    }
}
