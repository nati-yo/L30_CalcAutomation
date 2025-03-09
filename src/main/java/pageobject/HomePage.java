package pageobject;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class HomePage extends BasePage {

    //Elements
    By searchText = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"tsf\").childSelector(new UiSelector().className(\"android.widget.EditText\"))");
    By btn_1 = AppiumBy.id("com.miui.calculator:id/btn_1_s");
    By btn_2 = AppiumBy.id("com.miui.calculator:id/btn_2_s");
    By btn_3 = AppiumBy.id("com.miui.calculator:id/btn_3_s");
    By btn_4 = AppiumBy.id("com.miui.calculator:id/btn_4_s");
    By btn_5 = AppiumBy.id("com.miui.calculator:id/btn_5_s");
    By btn_6 = AppiumBy.id("com.miui.calculator:id/btn_6_s");
    By btn_7 = AppiumBy.id("com.miui.calculator:id/btn_7_s");
    By btn_8 = AppiumBy.id("com.miui.calculator:id/btn_8_s");
    By btn_9 = AppiumBy.id("com.miui.calculator:id/btn_9_s");
    By btn_0 = AppiumBy.id("com.miui.calculator:id/btn_0_s");
    By btn_dot = AppiumBy.id("com.miui.calculator:id/btn_dot_s");
    By btn_plus = AppiumBy.id("com.miui.calculator:id/btn_plus_s");
    By btn_minus = AppiumBy.id("com.miui.calculator:id/btn_minus_s");
    By btn_multi = AppiumBy.id("com.miui.calculator:id/btn_mul_s");
    By btn_div = AppiumBy.id("com.miui.calculator:id/btn_div_s");
    By btn_equal = AppiumBy.id("com.miui.calculator:id/btn_equal_s");
    By btn_clear = AppiumBy.id("com.miui.calculator:id/btn_c_s");
    By resultText = AppiumBy.id("com.miui.calculator:id/result");


    //Constructor
    public HomePage(AndroidDriver driver) {
        super(driver);
    }

    //Functions
    public void setSearchText(String text){
        writeText(searchText,text);
    }


    public void clickOnBtn1(){
        click(btn_1);
    }
    public void clickOnBtn2(){
        click(btn_2);
    }
    public void clickOnBtn3(){
        click(btn_3);
    }
    public void clickOnBtn4(){
        click(btn_4);
    }
    public void clickOnBtn5(){
        click(btn_5);
    }
    public void clickOnBtn6(){
        click(btn_6);
    }
    public void clickOnBtn7(){
        click(btn_7);
    }
    public void clickOnBtn8(){
        click(btn_8);
    }
    public void clickOnBtn9(){
        click(btn_9);
    }
    public void clickOnBtn0(){
        click(btn_0);
    }
    public void clickOnBtnDot(){
        click(btn_dot);
    }
    public void clickOnBtnPlus(){
        click(btn_plus);
    }
    public void clickOnBtnMinus(){
        click(btn_minus);
    }
    public void clickOnBtnMulti(){
        click(btn_multi);
    }
    public void clickOnBtnDiv(){
        click(btn_div);
    }
    public void clickOnBtnEqual(){
        click(btn_equal);
    }
    public void clickOnBtnClear(){
        click(btn_clear);
    }

    public void clickOnBtnByName(String btnCode){
        switch (btnCode){
            case "1"-> clickOnBtn1();
            case "2"-> clickOnBtn2();
            case "3"-> clickOnBtn3();
            case "4"-> clickOnBtn4();
            case "5"-> clickOnBtn5();
            case "6"-> clickOnBtn6();
            case "7"-> clickOnBtn7();
            case "8"-> clickOnBtn8();
            case "9"-> clickOnBtn9();
            case "0"-> clickOnBtn0();
            case "."-> clickOnBtnDot();
            case "+"-> clickOnBtnPlus();
            case "-"-> clickOnBtnMinus();
            case "x"-> clickOnBtnMulti();
            case "/"-> clickOnBtnDiv();
            case "="-> clickOnBtnEqual();
            case "AC"-> clickOnBtnClear();
            default -> System.out.println("clickOnBtnByName error : unsuported btnCode - " + btnCode);
        }
    }

    public void clickBtnsForNumber(String number){
        for(int i=0; i<number.length(); i++){
            clickOnBtnByName(number.charAt(i)+"");
        }
    }

    public String getResultText(){
        return getText(resultText);
    }



}
