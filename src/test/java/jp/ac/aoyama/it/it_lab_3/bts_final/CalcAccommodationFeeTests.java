// 情報テクノロジー学科 15822108 堀田大智
package jp.ac.aoyama.it.it_lab_3.bts_final;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalcAccommodationFeeTests {
    private AccommodationFeeModel model;
    private PostTestRestController controller;

    @BeforeEach
    public void initialize() {
        model = new AccommodationFeeModel();
        model.setName("森田");
        controller = new PostTestRestController();
    }

    @Test
    public void domesticBusinessTripCalcAccommodationFeeTest1() {
        model.setTravelCategory("国内");
        model.setNumberOfNights(1);
        controller.calcAccommodationFee(model);
        int actual = model.getAccommodationFee();
        int expected = 12000;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void domesticBusinessTripCalcAccommodationFeeTest2() {
        model.setTravelCategory("国内");
        model.setNumberOfNights(2);
        controller.calcAccommodationFee(model);
        int actual = model.getAccommodationFee();
        int expected = model.getNumberOfNights() * 12000;
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void overseasBusinessTripCalcDailyAllowanceTest1() {
        model.setTravelCategory("国外");
        model.setJobTitle("教授");
        model.setCityType("指定都市");
        model.setNumberOfNights(4);
        controller.calcAccommodationFee(model);
        int actual = model.getAccommodationFee();
        int expected = model.getNumberOfNights() * 21000;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void overseasBusinessTripCalcDailyAllowanceTest2() {
        model.setTravelCategory("国外");
        model.setCityType("指定都市以外");
        model.setJobTitle("教授");
        model.setNumberOfNights(4);
        controller.calcAccommodationFee(model);
        int actual = model.getAccommodationFee();
        int expected = model.getNumberOfNights() * 19000;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void overseasBusinessTripCalcDailyAllowanceTest3() {
        model.setTravelCategory("国外");
        model.setCityType("指定都市");
        model.setJobTitle("准教授");
        model.setNumberOfNights(4);
        controller.calcAccommodationFee(model);
        int actual = model.getAccommodationFee();
        int expected = model.getNumberOfNights() * 19000;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void overseasBusinessTripCalcDailyAllowanceTest4() {
        model.setTravelCategory("国外");
        model.setCityType("指定都市以外");
        model.setJobTitle("准教授");
        model.setNumberOfNights(4);
        controller.calcAccommodationFee(model);
        int actual = model.getAccommodationFee();
        int expected = model.getNumberOfNights() * 17000;
        Assertions.assertEquals(expected, actual);
    }
}
