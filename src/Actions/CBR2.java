package Actions;

import model.DiseaseDesc;
import model.Treatment;
import ucm.gaia.jcolibri.casebase.LinealCaseBase;
import ucm.gaia.jcolibri.cbraplications.StandardCBRApplication;
import ucm.gaia.jcolibri.cbrcore.*;
import ucm.gaia.jcolibri.exception.ExecutionException;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.NNConfig;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.NNScoringMethod;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.similarity.global.Average;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.similarity.local.Equal;
import ucm.gaia.jcolibri.method.retrieve.RetrievalResult;
import ucm.gaia.jcolibri.method.retrieve.selection.SelectCases;

import java.util.Collection;

public class CBR2 implements StandardCBRApplication {
    public static  String ttret;
    Connector _connector2;
    /**
     * Connector object
     */
    CBRCaseBase  _caseBase2;
    /**
     * CaseBase object
     */

    NNConfig  simConfig2;
    @Override
    public void configure() throws ExecutionException {
        _connector2 = new CsvConnector2();
        _caseBase2= new LinealCaseBase();// Create a Lineal case base for in-memory organizatiom
        simConfig2= new NNConfig(); // KNN configuration
        simConfig2.setDescriptionSimFunction(new Average()); // global similarity function = average
        simConfig2.addMapping(new Attribute("disease", Treatment.class), new Equal());


    }

    @Override
    public CBRCaseBase preCycle() throws ExecutionException {
        _caseBase2.init(_connector2);
        java.util.Collection<CBRCase> cases = _caseBase2.getCases();
        return _caseBase2;
    }

    @Override
    public void cycle(CBRQuery cbrQuery) throws ExecutionException {
        Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(_caseBase2.getCases(), cbrQuery, simConfig2);
        eval = SelectCases.selectTopKRR(eval, 6);
        System.out.println("RETRIVED\n");

        for (RetrievalResult rr : eval){
            System.out.println(rr.getEval() + rr.get_case().getDescription().toString());
            ttret = rr.get_case().getDescription().toString();
            break;
        }
    }


    @Override
    public void postCycle() throws ExecutionException {

    }

    public void mainC(Treatment t) throws ExecutionException {
        t.setDisease(t.getDisease().trim());
        System.out.println("imaliga: "+t.getDisease());
        StandardCBRApplication recommender = null;
        recommender = new CBR2();
        recommender.configure();
        recommender.preCycle();
        CBRQuery query = new CBRQuery();
        query.setDescription(t);

        recommender.cycle(query);
        recommender.postCycle();
    }
}
