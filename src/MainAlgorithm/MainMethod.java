package MainAlgorithm;

import static MainAlgorithm.DocumentOpt.getUserQuery;
import static MainAlgorithm.DocumentOpt.queryOptProcessing;
import static MainAlgorithm.DocumentOpt.queryProcessing;
import static MainAlgorithm.MainCQA.getCQAUserQuery;
import static MainAlgorithm.MainCQA.queryCQAProcessing;
import static MainAlgorithm.PreprocessingQA.processing;

public class MainMethod {

    public static void main(String args[])  throws Exception
    {
        getUserQuery();
        queryProcessing();
        //queryOptProcessing();
        //getCQAUserQuery();
        //queryCQAProcessing();
    }
}
