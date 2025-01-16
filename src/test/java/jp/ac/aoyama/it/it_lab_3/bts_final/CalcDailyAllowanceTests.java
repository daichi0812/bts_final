// 情報テクノロジー学科　15822108 堀田大智
package jp.ac.aoyama.it.it_lab_3.bts_final;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalcDailyAllowanceTests {
    private DailyAllowanceModel model;
    private PostTestRestController controller;

    @BeforeEach
    public void initialize() {
        model = new DailyAllowanceModel();
        model.setName("森田");
        model.setAffiliation("青山学院大学");
        controller = new PostTestRestController();
    }

    @Test
    public void calcDailyAllowanceTest1() {
        model.setTravelHours(0);
        controller.calcDailyAllowance(model);
        int expected = 0;
        int actual = model.getDailyAllowance();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void calcDailyAllowanceTest2() {
        model.setTravelHours(3);
        controller.calcDailyAllowance(model);
        int expected = 0;
        int actual = model.getDailyAllowance();
        Assertions.assertEquals(expected, actual);
    }


    @Test
    public void calcDailyAllowanceTest3() {
        model.setTravelHours(4);
        controller.calcDailyAllowance(model);
        int expected = 1000;
        int actual = model.getDailyAllowance();
        Assertions.assertEquals(expected, actual);
    }


    @Test
    public void calcDailyAllowanceTest4() {
        model.setTravelHours(8);
        controller.calcDailyAllowance(model);
        int expected = 2000;
        int actual = model.getDailyAllowance();
        Assertions.assertEquals(expected, actual);
    }


    @Test
    public void calcDailyAllowanceTest5() {
        model.setTravelHours(12);
        controller.calcDailyAllowance(model);
        int expected = 3000;
        int actual = model.getDailyAllowance();
        Assertions.assertEquals(expected, actual);
    }

}
