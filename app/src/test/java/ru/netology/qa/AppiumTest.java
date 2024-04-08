package ru.netology.qa;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import java.net.URL;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

    public class AppiumTest {

        private AndroidDriver driver;

        @BeforeEach
        public void setUp() throws MalformedURLException {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability("platformName", "Android");
            desiredCapabilities.setCapability("appium:deviceName", "emulator-5554");
            desiredCapabilities.setCapability("appium:appPackage", "ru.netology.testing.uiautomator");
            desiredCapabilities.setCapability("appium:appActivity", "ru.netology.testing.uiautomator.MainActivity");
            desiredCapabilities.setCapability("appium:automationName", "uiautomator2");

            URL remoteURL = new URL("http://127.0.0.1:4723");

            driver = new AndroidDriver(remoteURL, desiredCapabilities);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }


        @Test
        public void testAttemptSetEmptyString() {
            MobileElement el1 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/textToBeChanged");
            String text = el1.getText();
            MobileElement el2 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/userInput");
            el2.sendKeys("     ");
            MobileElement el3 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/buttonChange");
            el3.click();
            Assertions.assertEquals(text, el1.getText());
        }

        @Test
        public void testOpeningTextNewActivity() {
            String text = "New Activity";
            MobileElement el1 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/userInput");
            el1.sendKeys(text);
            MobileElement el2 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/buttonActivity");
            el2.click();
            MobileElement el3 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/text");
            el3.isEnabled();
            Assertions.assertEquals(text, el3.getText());
        }

        @AfterEach
        public void tearDown() {
            driver.quit();
        }
    }


