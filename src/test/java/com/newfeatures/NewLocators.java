package com.newfeatures;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;


public class NewLocators
{
    public static WebDriver driver;
    
    @BeforeClass
    public void createDriver(){

        WebDriverManager.safaridriver().setup();
        driver = new SafariDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        

    }

    @BeforeMethod
    public void openUrl(){
        driver.get("https://www.leafground.com/button.xhtml");
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void rightToLocatorStrategy() throws InterruptedException
    {
        Assert.assertEquals(driver.findElement(with(By.tagName("button")).toRightOf(By.xpath("//*[contains(text(),'Success')]"))).getText(), "Secondary");
    }

    @Test(priority = 2)
    public void leftToLocatorStrategy() throws InterruptedException
    {
        Assert.assertEquals(driver.findElement(with(By.tagName("button")).toLeftOf(By.xpath("//*[contains(text(),'Primary')]"))).getText(),"Save");
    }

    @Test(priority = 3)
    public void aboveLocatorStrategy() throws InterruptedException
    {
        Assert.assertEquals(driver.findElement(with(By.tagName("button")).above(By.xpath("//*[contains(text(),'Image')]"))).getText(), "Success");
    }

    @Test(priority = 3)
    public void belowLocatorStrategy() throws InterruptedException
    {
        Assert.assertEquals(driver.findElement(with(By.tagName("button")).below(By.xpath("//*[contains(text(),'Save')]"))).getText(), "Save");
    }


    @AfterClass
    public void closeDriver(){
        driver.quit();
    }
}
