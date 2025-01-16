// 情報テクノロジー学科 15822108 堀田大智
package jp.ac.aoyama.it.it_lab_3.bts_final;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostTestRestController {

    @PostMapping("/calc_daily_allowance")
    public DailyAllowanceModel calcDailyAllowance(@RequestBody DailyAllowanceModel model) {
        System.out.println(model.getName());
        System.out.println(model.getTravelHours());
        int travelHours = model.getTravelHours();
        if (travelHours < 4) {
            model.setDailyAllowance(0);
        } else if (4 <= travelHours && travelHours < 8) {
            model.setDailyAllowance(1000);
        } else if (8 <= travelHours && travelHours < 12) {
            model.setDailyAllowance(2000);
        } else if (12 <= travelHours) {
            model.setDailyAllowance(3000);
        }
        System.out.println(model.getDailyAllowance());
        return model;
    }

    @PostMapping("/calc_accommodation_fee")
    public AccommodationFeeModel calcAccommodationFee(@RequestBody AccommodationFeeModel model){
        // 国内の場合
        if("国内".equals(model.getTravelCategory())){
            // 国内は一泊当たり12000円
            model.setAccommodationFee(model.getNumberOfNights() * 12000);
        }else if("国外".equals(model.getTravelCategory())){
            // 国外場合
            int feePerNights = 0;
            if("教授".equals(model.getJobTitle())){
                if("指定都市".equals(model.getCityType())){
                    // 教授 & 指定都市 => 21000円 / 伯
                    feePerNights = 21000;
                }else {
                    // 教授 & 指定都市以外 => 19000円 / 伯
                    feePerNights = 19000;
                }
            }else if("准教授".equals(model.getJobTitle())){
                if("指定都市".equals(model.getCityType())){
                    // 准教授 & 指定都市 => 19000円 / 伯
                    feePerNights = 19000;
                }else {
                    // 准教授 & 指定都市以外 => 17000円 / 伯
                    feePerNights = 17000;
                }
            }
            model.setAccommodationFee(model.getNumberOfNights() * feePerNights);
        }
        return model;
    }
}
