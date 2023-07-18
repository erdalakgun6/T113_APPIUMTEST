package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class C01_Calculator_Test {
        AndroidDriver<AndroidElement> driver; //android cihazlar icin kullanilmasi gerek driver
    //  AppiumDriver<AndroidElement> driver2; // Hem Android hemde IOS cihazlar icin kullanilabilen driver(Android icin bu driver kullanilabilir)
    //  AppiumDriver<MobileElement> driver3; // ios icin kullanilir (Android cihazlar icin de kullanilabilir)

    @Test
    public void deneme1() throws MalformedURLException {
        // kullanici gerekli kurulumlari yapar
        DesiredCapabilities capabilities=new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"PIXEL 2");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,"10.0");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UiAutomator2");
        // Eger ki kullandigimiz android surumu 6 veya ustuyse UiAutoMator2 kullanilir.

        //capabilities.setCapability(MobileCapabilityType
                       // .APP,"C:\\Users\\erdal\\IdeaProjects\\T113_APPIUMTEST\\Apps\\Calculator_8.4 (503542421)_Apkpure.apk");
        // app capabilitiyType bir uygulamayi yüklemek istedigimizde indirdigimiz apk dosyasinin path uzantisini vererek o uygulamayı calistirir

        driver=new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        // driver.sendSMS("5555555555", "Selam T113");


        driver.activateApp("com.google.android.calculator");
        // uygulamanin yuklendigini dogrular(isInstalled)

        driver.isAppInstalled("com.google.android.calculator");

        Assert.assertTrue(driver.isAppInstalled("com.google.android.calculator"));
        // uygulamanin acildigini dogrular
        AndroidElement acButonu = driver.findElementByAccessibilityId("clear");
        Assert.assertTrue(acButonu.isDisplayed());
        // carpma,bolme,toplama,cikarma islemleri yaparak sonuclari dogrular
        driver.findElementByAccessibilityId("8").click();
        driver.findElementByAccessibilityId("0").click();
        driver.findElementByAccessibilityId("multiply").click();
        driver.findElementByAccessibilityId("2").click();
        driver.findElementByAccessibilityId("0").click();
        driver.findElementByAccessibilityId("equals").click();
        String sonuc = driver.findElementById("com.google.android.calculator:id/result_final").getText();
        System.out.println(sonuc);
        Assert.assertEquals(Integer.parseInt(sonuc), 1600);
        // AC butonuna tiklayarak ana ekrani temizler
    }
}
