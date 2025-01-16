package jp.ac.aoyama.it.it_lab_3.bts_final;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jp.ac.aoyama.it.it_lab_3.business_trip_dsl_sample.BusinessTripModel;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

@Service
public class BusinessTripService {

    private KieSession ksession;

    // business_trip_dsl_sample-1.0.1.jarに記載のReleaseId
    private static final String GROUP_ID = "jp.ac.aoyama.it.it_lab_3";
    private static final String ARTIFACT_ID = "business_trip_dsl_sample";
    private static final String VERSION = "1.0.1";

    @PostConstruct
    public void initialize(){
        KieServices ks = KieServices.Factory.get();
        KieContainer kc = ks.newKieContainer(ks.newReleaseId(GROUP_ID, ARTIFACT_ID, VERSION));
        ksession = kc.newKieSession();
    }

    @PreDestroy
    public void dispose(){
        if (ksession != null){
            ksession.dispose();
        }
    }

    /**
     * Droolsを使ってBusinessTripModelの日当を計算する
     */
    public BusinessTripModel calcDailyAllowance(BusinessTripModel model) {
        ksession.insert(model);
        ksession.fireAllRules();
        return model;
    }
}