import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import config.Conf;
import io.appium.java_client.android.AndroidDriver;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import pageobject.HomePage;


import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    //Attributes
    static HomePage homePage;

    public static AndroidDriver driver;
    public static Conf conf;
    // for report
    public static ExtentReports extent;
    public static ExtentTest test;
    public static int reportCounter=0;

    @BeforeClass
    public static void setUp() throws Exception {

        conf = new Conf("src/testData/conf.xml");

        // https://appium.io/docs/en/latest/quickstart/

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "android");
        capabilities.setCapability("appium:deviceName", "Android Device");

        // as a UiAutomator2 mode
        // how to get them ? run : adb shell "dumpsys activity activities | grep mResumedActivity"
        // or : adb shell dumpsys window | grep -E 'mCurrentFocus|mFocusedApp'
        capabilities.setCapability("appium:appPackage", "com.miui.calculator");//
        capabilities.setCapability("appium:appActivity", "com.miui.calculator.cal.CalculatorActivity");

//        // as a real WebDriver mode
//        capabilities.setCapability("appium:browserName", "Chrome");

        capabilities.setCapability("appium:noReset", true);
        capabilities.setCapability("appium:newCommandTimeout", 120);
        capabilities.setCapability("appium:hideKeyboard", true);
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        driver = new AndroidDriver(new URL("http://localhost:4723"), capabilities);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        // Initialize Extent Reports
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("test-output/"+(++reportCounter)+"-extentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        homePage = new HomePage(driver);
    }


    @Before
    public void startTestWithUrl(){

    }


    @AfterClass
    public static void close(){
        extent.flush();
        driver.terminateApp("com.miui.calculator");
    }


    public void reportInfoMessage(String msg){
        System.out.println(msg);
        test.info(msg);
    }

    public void reportPassMessage(String msg){
        System.out.println(msg);
        test.pass(msg);
    }

    public void reportFailMessage(String msg){
        System.out.println(msg);
        test.fail(msg);
    }

    public String createScreenCapture(String name){
        File img = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        img.renameTo(new File("test-output/" + name+".png"));
        return name+".png";
    }

    public void reportTestImageCapture(String testFileName, Status testStatus){
        String relativeImgPath = createScreenCapture(testFileName);
        test.log(testStatus, MediaEntityBuilder.createScreenCaptureFromPath(relativeImgPath).build());
    }

}
