package jp.ac.aoyama.it.it_lab_3.bts_final;

import jp.ac.aoyama.it.it_lab_3.business_trip_dsl_sample.BusinessTripModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BusinessTripRestControllerTests {

    private BusinessTripModel btm;
    private BusinessTripRestController controller;

    @BeforeEach
    public void initialize() {
        controller = new BusinessTripRestController();
        btm = new BusinessTripModel();
        btm.setName("山田太郎");
    }

    @Test
    public void dailyAllowanceTest1() {
        btm.setTravelHours(2);
        controller.calDailyAllowance(btm);
        int actual = btm.getDailyAllowance();
        int expected = 0;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void dailyAllowanceTest2() {
        btm.setTravelHours(5);
        controller.calDailyAllowance(btm);
        int actual = btm.getDailyAllowance();
        int expected = 1000;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void dailyAllowanceTest3() {
        btm.setTravelHours(9);
        controller.calDailyAllowance(btm);
        int actual = btm.getDailyAllowance();
        int expected = 2000;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void dailyAllowanceTest4() {
        btm.setTravelHours(12);
        controller.calDailyAllowance(btm);
        int actual = btm.getDailyAllowance();
        int expected = 3000;
        Assertions.assertEquals(expected, actual);
    }
}
