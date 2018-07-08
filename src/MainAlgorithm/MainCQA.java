package MainAlgorithm;

import java.io.File;
import java.util.*;

import static MainAlgorithm.DocumentOpt.calculateInvertedFreq;
import static MainAlgorithm.DocumentOpt.calculateWeight;

public class MainCQA {
    private static  HashMap<Integer,String> usersQuery=new HashMap<Integer,String>();
    private static double lamda=0.2;
    private static HashMap<String,Double> frequency[];
    private static HashMap<List<String>,Double> Result=new HashMap<List<String>, Double>();

    public  static void  getCQAUserQuery() throws Exception
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
                double iweight=calculateInvertedCQAFreq(h);
                frequency[usr].put(h,iweight);
            }
            System.out.println(frequency[usr]);
        }
    }

    public static double calculateInvertedCQAFreq(String iquery) throws Exception
    {
        int n=82;
        int i=0;
        int count=0;
        for(i=0;i<82;i++)
        {
            File file=new File("D:\\research 2\\Algorithms\\Yahoo\\AllFiles\\Doc"+i+".txt");
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

    public static void queryCQAProcessing() throws Exception
    {
        for(Integer in:usersQuery.keySet())
        {
            String iquery=usersQuery.get(in);
            calculateCQAOpt(iquery,in);
            getCQAResult(in);
        }

    }


    public static void calculateCQAOpt(String iquery,int qid)  throws Exception
    {
        int p=1;
        String words[]=iquery.split(" ");
        File file=new File("D:\\research 2\\Algorithms\\Yahoo\\Directory\\File"+qid);
        File listf[]=file.listFiles();
        HashMap<String, Double> queries = new HashMap<String, Double>();
            for (String iword : words) {
                double val=0;
                for (File f : listf) {
                    String fname = f.getName();
                    List<String> DocSen = getCQADocumentSentence(fname, qid);
                    int weight = calculateWeight(iword, qid, fname, DocSen);
                    if (weight > 0) {
                        val=val+(weight/Math.log(1+p));
                    }
                    p++;
                }
                if(val>0)
                    queries.put(iword,val);
            }
            System.out.println(queries);
            if(queries.size()>0)
            {
                for(File f:listf) {
                    String fname = f.getName();
                    List<String> DocSen = getCQADocumentSentence(fname, qid);
                    choseCQASentence(queries, f.getName(), DocSen, qid);
                }
            }

        }

    public static void choseCQASentence(HashMap<String,Double> q,String name,List<String> DocSen,int qid)
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

    public static List<String> getCQADocumentSentence(String fname,int qid) throws Exception
    {
        File file=new File("D:\\research 2\\Algorithms\\Yahoo\\Directory\\File"+qid+"\\"+fname);
        Scanner manu=new Scanner(file);
        List<String> ret=new ArrayList<String>();
        while(manu.hasNextLine())
        {
            String h=manu.nextLine();
            if(h.contains("<SENTENCE>")) {
               h=h.replaceAll("<SENTENCE>", "");
            }
            if(h.contains("<answer>"))
               h=h.replaceAll("<answer>","");
            ret.add(h);
        }
        return ret;
    }


    public static void getCQAResult(int in) {
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

