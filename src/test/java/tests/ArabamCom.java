package tests;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;


public class ArabamCom {





    AndroidDriver<AndroidElement> driver;
    @BeforeTest
    public void arabamTestSetUp() throws MalformedURLException {

        DesiredCapabilities capabilities=new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"PIXEL 2");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,"10.0");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UiAutomator2");
        capabilities.setCapability("appPackage","com.dogan.arabam"); // hangi uygulama uzerinden calismak istiyorsak apk infodan o uygulamanın degerini aliyoruz
        capabilities.setCapability("appActivity", "com.dogan.arabam.presentation.feature.home.HomeActivity"); // uygulamayı actiktan sonra hangi sayfadan baslayacagimizi orn; Anasayfa, Profil, vb.

        driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        }

    @Test
    public void arabamTest() throws InterruptedException {

        // Uygulamanin basarili bir sekilde yuklendigi dogrulanir
        Assert.assertTrue(driver.isAppInstalled("com.dogan.arabam"));

        // Uygulamanin basarili bir sekilde acildigi dogrulanir.
        AndroidElement arabamLogo = driver.findElementById("com.dogan.arabam:id/ivArabamLogo");
        Assert.assertTrue(arabamLogo.isDisplayed());

        // Arabam kac para bolumune tiklayalim
        AndroidElement arabamKacPara = driver.findElementByXPath("//*[@text='Arabam kaç para?']");
        arabamKacPara.click();


        // Aracimin fiyatini merak ediyorum bolumunetiklayalim
        driver.findElementByXPath("//*[@text='Aracımın fiyatını merak ediyorum']").click();

        // Wolkswagen markasini secelim
        TouchAction touchAction = new TouchAction<>(driver);
        touchAction
                .press(PointOption.point(531,1689))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .moveTo(PointOption.point(531,450))
                .release()
                .perform();

        Thread.sleep(1000);

        driver.findElementByXPath("//*[@text='Volkswagen']").click();

        // yil secimi yapalim
        driver.findElementByXPath("//*[@text='2018']").click();

        // model secimi yapalim
        driver.findElementByXPath("//*[@text='Passat']").click();

        // govde tipini secelim
        driver.findElementByXPath("//*[@text='Sedan']").click();

        // yakit tipini secelim
        driver.findElementByXPath("//*[@text='Benzin']").click();
        // vites tipini secelim
        driver.findElementByXPath("//*[@text='Yarı Otomatik']").click();
        Thread.sleep(1000);
        // Versiyon secimi yapalim
        touchAction.press(PointOption.point(428,1138)).release().perform();
        Thread.sleep(1000);
        // aracin km bilgilerini girelim
        if (driver.isKeyboardShown()){
            driver.getKeyboard().pressKey("50000");

        }else {
            driver.findElementById("com.dogan.arabam:id/et_km").sendKeys("100000");
        }

        driver.findElementById("com.dogan.arabam:id/btn_price_prediction_submit").click();

        // aracin rengini secelim
        driver.findElementByXPath("//*[@text='Beyaz']").click();

        // opsiyel donanim (varsa) seecelim
        driver.findElementById("com.dogan.arabam:id/btnNext").click();

        // degisen bilgisi ekleyerek tramer kaydi belirtelim
        driver.findElementById("com.dogan.arabam:id/iv_B0901").click();
        driver.findElementByXPath("//*[@text='Değişmiş']").click();
        Thread.sleep(1000);

        // driver.findElementById("com.dogan.arabam:id/btnNext").click();
        // Eger locate duzgun calismaz ise Koordinatlar uzerinden tiklama islemini devam ettirebiliriz.
        touchAction.press(PointOption.point(529,1686)).release().perform();
        driver.findElementByXPath("//*[@text='Bilmiyorum']").click();
        touchAction.press(PointOption.point(529,1686)).release().perform();
        Thread.sleep(1000);
        // aracimizin fiyatinin 500.000 tl den fazla oldugunu test edelim
        String aracinFiyati = driver.findElementById("com.dogan.arabam:id/tvAveragePrice").getText();
        aracinFiyati = aracinFiyati.replaceAll("\\D","");
        System.out.println(aracinFiyati);

        Assert.assertTrue(Integer.parseInt(aracinFiyati)>500000);
        // uygulamayi kapatalim
        driver.closeApp();

    }

}
