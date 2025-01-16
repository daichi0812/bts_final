// 情報テクノロジー学科 15822108 堀田大智
package jp.ac.aoyama.it.it_lab_3.bts_final;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jp.ac.aoyama.it.it_lab_3.business_trip_dsl_sample.BusinessTripModel;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BusinessTripRestController {

    // Drools用 kieSession
    private KieSession ksession;

    // business_trip_dsl_sample-1.0.1.jarに記載のReleaseId
    private static final String GROUP_ID = "jp.ac.aoyama.it.it_lab_3";
    private static final String ARTIFACT_ID = "business_trip_dsl_sample";
    private static final String VERSION = "1.0.1";

    public BusinessTripRestController(){
        if(this.ksession == null){
            initialize();
        }
    }

    @PostConstruct
    public void initialize(){
        KieServices ks = KieServices.Factory.get();
        KieContainer kc = ks.newKieContainer(ks.newReleaseId(GROUP_ID, ARTIFACT_ID, VERSION));
        ksession = kc.newKieSession();
    }

    @PreDestroy
    public void dispose(){
        if(ksession != null){
            ksession.dispose();
        }
    }

    @PostMapping("/calc_daily_allowance2")
    public BusinessTripModel calDailyAllowance(@RequestBody BusinessTripModel btm) {
        // ファクトを挿入
        ksession.insert(btm);
        // ルールを全部実行
        ksession.fireAllRules();

        // dailyAllowance がルールで設定されるはず
        return btm; // JSON で返却
    }
}
