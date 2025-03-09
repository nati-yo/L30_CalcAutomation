import com.aventstack.extentreports.Status;
import org.junit.*;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HomeTest extends BaseTest {

    final double DELTA = 0.0000001;

    @Before
    public void goToHomePage(){
        driver.activateApp("com.miui.calculator");
        homePage.clickOnBtnByName("AC");
        homePage.clickOnBtnByName("AC"); // twice for hard clear
    }



    @Test
    public void test01_sumTest(){
        String testName = "Test 1 - Calculator Sum Test At Android";
        String calcResult;
        double calcSum;

        test = extent.createTest(testName);
        System.out.println(testName);
        Status testStatus = Status.PASS;
        try{

            String number1 = conf.getValueByKey("num1");
            String number2 = conf.getValueByKey("num2");

            calcSum = Double.parseDouble(number1) + Double.parseDouble(number2);
            reportInfoMessage("Testing the calculation of : " + number1 + " + " + number2 + " = " + calcSum);

            homePage.clickBtnsForNumber(number1);
            homePage.clickOnBtnByName("+");
            homePage.clickBtnsForNumber(number2);
            homePage.clickOnBtnByName("=");

            calcResult = homePage.getResultText().replace("= ","");
            reportInfoMessage( "Test result is : " + calcResult);
            Assert.assertEquals(calcSum , Double.parseDouble(calcResult), DELTA);

            reportPassMessage("Test passed!");
        }
        catch (AssertionError e){
            reportFailMessage("Test failed! " + e.getMessage());
            testStatus = Status.FAIL;
        }
        reportTestImageCapture("test01_sumTest",testStatus );
    }

    @Test
    public void test02_minusTest(){
        String testName = "Test 2 - Calculator Minus Test At Android";
        String calcResult;
        double calcMinus;

        test = extent.createTest(testName);
        System.out.println(testName);
        Status testStatus = Status.PASS;
        try{

            String number1 = conf.getValueByKey("num1");
            String number2 = conf.getValueByKey("num2");

            calcMinus = Double.parseDouble(number1) - Double.parseDouble(number2);
            reportInfoMessage("Testing the calculation of : " + number1 + " - " + number2 + " = " + calcMinus);

            homePage.clickBtnsForNumber(number1);
            homePage.clickOnBtnByName("-");
            homePage.clickBtnsForNumber(number2);
            homePage.clickOnBtnByName("=");

            calcResult = homePage.getResultText().replace("= ","");
            reportInfoMessage( "Test result is : " + calcResult);
            Assert.assertEquals(calcMinus , Double.parseDouble(calcResult), DELTA);

            reportPassMessage("Test passed!");
        }
        catch (AssertionError e){
            reportFailMessage("Test failed! " + e.getMessage());
            testStatus = Status.FAIL;
        }
        reportTestImageCapture("test02_minusTest",testStatus );
    }

    @Test
    public void test03_multiTest(){
        String testName = "Test 3 - Calculator Multi Test At Android";
        String calcResult;
        double calcMulti;

        test = extent.createTest(testName);
        System.out.println(testName);
        Status testStatus = Status.PASS;
        try{

            String number1 = conf.getValueByKey("num1");
            String number2 = conf.getValueByKey("num2");

            calcMulti = Double.parseDouble(number1) * Double.parseDouble(number2);
            reportInfoMessage("Testing the calculation of : " + number1 + " x " + number2 + " = " + calcMulti);

            homePage.clickBtnsForNumber(number1);
            homePage.clickOnBtnByName("x");
            homePage.clickBtnsForNumber(number2);
            homePage.clickOnBtnByName("=");

            calcResult = homePage.getResultText().replace("= ","");
            reportInfoMessage( "Test result is : " + calcResult);
            Assert.assertEquals(calcMulti , Double.parseDouble(calcResult), DELTA);

            reportPassMessage("Test passed!");
        }
        catch (AssertionError e){
            reportFailMessage("Test failed! " + e.getMessage());
            testStatus = Status.FAIL;
        }
        reportTestImageCapture("test03_multiTest",testStatus );
    }

    @Test
    public void test04_divideTest(){
        String testName = "Test 4 - Calculator Divide Test At Android";
        String calcResult;
        double calcDivide;

        test = extent.createTest(testName);
        System.out.println(testName);
        Status testStatus = Status.PASS;
        try{

            String number1 = conf.getValueByKey("num1");
            String number2 = conf.getValueByKey("num2");

            calcDivide = Double.parseDouble(number1) / (double)Double.parseDouble(number2);
            reportInfoMessage("Testing the calculation of : " + number1 + " / " + number2 + " = " + calcDivide);

            homePage.clickBtnsForNumber(number1);
            homePage.clickOnBtnByName("/");
            homePage.clickBtnsForNumber(number2);
            homePage.clickOnBtnByName("=");

            calcResult = homePage.getResultText().replace("= ","");
            reportInfoMessage( "Test result is : " + calcResult);
            Assert.assertEquals(calcDivide , Double.parseDouble(calcResult),DELTA);

            reportPassMessage("Test passed!");
        }
        catch (AssertionError e){
            reportFailMessage("Test failed! " + e.getMessage());
            testStatus = Status.FAIL;
        }
        reportTestImageCapture("test04_divideTest",testStatus );
    }

}
