import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class Test1 {
    private static AndroidDriver driver;

    @Before
    public void setup() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","Android Emulator");
        capabilities.setCapability("app","C:\\APKs\\com.fti.surefleet.fuel2.android_3.5.8.apk");
        capabilities.setCapability("automationName","UiAutomator2");
        capabilities.setCapability("platformVersion","7.1");
        capabilities.setCapability("appPackage","com.fti.surefleet.fuel2.android");
        capabilities.setCapability("appActivity","com.surefleet.view.DashboardActivity");

        driver = new AndroidDriver(new URL("http://127.0.1.1:4723/wd/hub"), capabilities);

    }

    @Test
    public void test1(){
        WebElement username=driver.findElement(By.id("com.fti.surefleet.fuel2.android:id/usernameET"));
        username.sendKeys("Gabriel200");

        WebElement password=driver.findElement(By.id("com.fti.surefleet.fuel2.android:id/passwordET"));
        password.sendKeys("Gabriel200");

        WebElement signin=driver.findElement(By.id("com.fti.surefleet.fuel2.android:id/signInBtn"));
        signin.click();
        allowAppPermission();

        WebElement message=driver.findElement(By.id("com.fti.surefleet.fuel2.android:id/dialogMessageId"));
        String messageText = message.getText();
        String expectedMessage = "Username or Password are incorrect";
        Assert.assertEquals(messageText, expectedMessage);
    }

    @After
    public void tearDown(){
        driver.quit();
    }

    public void allowAppPermission(){
        while (driver.findElements(MobileBy.id("com.android.packageinstaller:id/dialog_container")).size()>0){
            driver.findElement(MobileBy.id("com.android.packageinstaller:id/permission_allow_button")).click();
        }
    }
}
