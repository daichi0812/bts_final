/*
 * 青山学院大学 理工学部 情報テクノロジー学科
 * 学生番号: 15822125
 * 氏名: 森下 剛 Go Morishita
 */

package jp.ac.aoyama.it.it_lab_3.bts_final;

import jp.ac.aoyama.it.it_lab_3.business_trip_dsl_sample.*;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BusinessTripController {

    private KieSession kieSession;

    public BusinessTripController() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.newKieContainer(
                kieServices.newReleaseId("jp.ac.aoyama.it.it_lab_3", "business_trip_dsl_sample", "1.0.1")
        );
        this.kieSession = kieContainer.newKieSession();
    }

    @GetMapping("/business_trip_input")
    public String businessTripInput() {
        return "business_trip_input";
    }

    @PostMapping("/business_trip_output")
    public String businessTripOutput(
            @RequestParam("name") String name,
            @RequestParam("travel_hours") int travelHours,
            Model model) {

        // BusinessTripModelのインスタンスを作成
        BusinessTripModel businessTripModel = new BusinessTripModel();
        businessTripModel.setName(name);
        businessTripModel.setTravelHours(travelHours);

        // ルールエンジンにデータを挿入して実行
        kieSession.insert(businessTripModel);
        kieSession.fireAllRules();

        // モデルにデータを追加
        model.addAttribute("name", businessTripModel.getName());
        model.addAttribute("travel_hours", businessTripModel.getTravelHours());
        model.addAttribute("daily_allowance", businessTripModel.getDailyAllowance());

        return "business_trip_output";
    }
}
