package pageobject;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    // Attributes
    static AndroidDriver driver;
    WebDriverWait wait;

    // Constructor
    public BasePage(AndroidDriver driver) {
        this.driver= driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    // Functions
    public String getWindowTitle(){
        return driver.getTitle();
    }

    public void click(By by){
        scrollToElement(by).click();
    }
    public void submit(){
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
    }


    // \p{Cf} – Removes formatting control characters (e.g., \u200e, \u202b, \u202c).
    // \p{C}  – Removes other invisible control characters.
    public String getText(By by){
        return scrollToElement(by).getText().replaceAll("[\\p{Cf}\\p{C}]", "");
    }


    public void writeText(By textField, String text){
        WebElement element = scrollToElement(textField);
        element.clear();
        element.sendKeys(text);
    }

    public boolean isElementDisplayed(By by) {
       WebElement el = findByOrNull(by);
       return el != null && el.isDisplayed();
    }

    public boolean isElementDisplayed(WebElement element) {
        try {  return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public WebElement findByOrNull(By by) {
        try { return waitVisibility(by);} catch (Exception ignore) {
            System.out.println("findByOrNull info : element not found.  By is - " +by.toString());
        } return null;
    }

    public WebElement waitVisibility(By by){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public WebElement scrollToElement(WebElement element){
        int i =0;
//        (new Actions(driver)).scrollByAmount(0,element.getRect().y+100).perform(); // for the element to be in the view
        while (!isElementDisplayed(element) && i<20){  // ממשיך לגלול עד שהאלמנט מוצג
            System.out.println("scrollToElement loop " + i++);
            new TouchAction<>(driver)
                    .press(PointOption.point(500, 1500))  // נקודת התחלה (למטה)
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                    .moveTo(PointOption.point(500, 500))  // נקודת סיום (למעלה)
                    .release()
                    .perform();
        }
        return element;
    }

    public WebElement scrollToElement(By locator) {
        WebElement element = findByOrNull(locator);
        String locatorExpression = "";
        String locatorString = locator.toString(); // הופך את ה-locator למחרוזת

        if(element==null){
            if (locator instanceof AppiumBy.ById) {
                locatorExpression = "new UiSelector().resourceId(\"" + locatorString.split(": ")[1] + "\")";
            } else if (locator instanceof AppiumBy.ByAccessibilityId) {
                locatorExpression = "new UiSelector().text(\"" + locatorString.split(": ")[1] + "\")";
            } else if (locator instanceof AppiumBy.ByXPath) {
                locatorExpression = "new UiSelector().xpath(\"" + locatorString.split(": ")[1] + "\")";
            } else if (locator instanceof AppiumBy.ByAndroidUIAutomator) {
                locatorExpression = locatorString.split(": ")[1]; // במקרה של UiAutomator, שומרים את הביטוי AS-IS
            } else {
                System.out.println("Unsupported locator type " + locatorString);
                element = driver.findElement(locator);
            }

            try {
                System.out.println("scrollToElement info : scroll to - " + locatorExpression);
                element = element!=null ? element : driver.findElement(AppiumBy.androidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(" + locatorExpression + ")"
                ));
            } catch (Exception e) {
                System.out.println("Element not found after scrolling: " + e.getMessage());
            }
        }
        return element;
    }


}
