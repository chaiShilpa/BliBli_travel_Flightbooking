package com.quinbay.Steps;

import com.quinbay.Pages.DetailpageXpath;
import com.quinbay.Pages.Filling_page;
import com.quinbay.Pages.HomepageXpath;
import com.quinbay.cucumber.CucumberHooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.LinkedHashMap;

public class MyStepdefs {
    CucumberHooks action = new CucumberHooks();
    HomepageXpath xpath=new HomepageXpath();
    LinkedHashMap map=new LinkedHashMap();

    @Given("^I am on  Travel page$")
    public void getUrl() {
        action.driver.get("https://www.blibli.com/travel");
    }
    @Then("change the place name")
    public void changeThePlaceName() throws InterruptedException {
        Thread.sleep(3000);
        WebElement place=action.driver.findElement(By.xpath(xpath.Place));
        place.click();
        place.sendKeys("Makassar");
        place.sendKeys(Keys.RETURN);
       // Assert.assertEquals("Makassar",place.getText());
        action.driver.findElement(By.xpath(xpath.calenderbutton)).click();
       // Thread.sleep(3000);
        action.driver.findElement(By.xpath(xpath.date)).click();
    }

    @And("I click on Find ticket")
    public void iClickOnFindTicket() {
        action.driver.findElement(By.xpath(xpath.serach_flight)).click();
    }
    @And("click on detail and do the assert")
    public void clickOnDetailAndDoTheAssert() throws InterruptedException {
        Thread.sleep(10000);
        action.driver.findElement(By.xpath(DetailpageXpath.details)).click();
        Thread.sleep(3000);
        map.put("source",action.driver.findElement(By.xpath(DetailpageXpath.source)).getText());
        map.put("destination",action.driver.findElement(By.xpath(DetailpageXpath.destination)).getText());
        map.put("sourcetime",action.driver.findElement(By.xpath(DetailpageXpath.sourceTime)).getText());
        map.put("destinationtime",action.driver.findElement(By.xpath(DetailpageXpath.destinationTime)).getText());
        map.put("Flight",action.driver.findElement(By.xpath(DetailpageXpath.Flight)).getText());
    }

    @And("click on Select go")
    public void clickOnSelectGo() throws InterruptedException {
        Thread.sleep(1000);
        action.driver.findElement(By.xpath(DetailpageXpath.search)).click();
    }

    @And("click on detials on filling page and assert values")
    public void clickOnDetialsOnFillingPageAndAssertValues() throws InterruptedException {
        Thread.sleep(5000);
        action.driver.findElement(By.xpath("//a[contains(text(),'Detail')]")).click();
        Thread.sleep(3000);
        Assert.assertEquals(map.get("source"),action.driver.findElement(By.xpath(DetailpageXpath.source)).getText());System.out.println("getting source place");
        Assert.assertEquals(map.get("destination"),action.driver.findElement(By.xpath(DetailpageXpath.destination)).getText());System.out.println("getting destination place");
        Assert.assertEquals(map.get("sourcetime"),action.driver.findElement(By.xpath(DetailpageXpath.sourceTime)).getText());System.out.println("getting arriving time");
        Assert.assertEquals(map.get("destinationtime"),action.driver.findElement(By.xpath(DetailpageXpath.destinationTime)).getText());System.out.println("getitng destination time");
        Assert.assertEquals(map.get("Flight"),action.driver.findElement(By.xpath(DetailpageXpath.Flight)).getText());System.out.println("getting flight number");
        action.driver.findElement(By.xpath("//b[contains(text(),'×')]")).click();
    }



    @And("user fills the details and confirm order")
    public void userFillsTheDetailsAndConfirmOrder() throws InterruptedException {
        Thread.sleep(5000);
        WebElement element=action.driver.findElement(By.xpath(Filling_page.details));
        Select sel=new Select(element);
        sel.selectByVisibleText("Nyonya");
        Thread.sleep(1000);
        action.driver.findElement(By.xpath(Filling_page.fullname)).sendKeys("chai");
        action.driver.findElement(By.xpath(Filling_page.phonenumber)).sendKeys("12345678650");
        action.driver.findElement(By.xpath(Filling_page.Email)).sendKeys("chait@gmail.com");
        action.driver.findElement(By.xpath(Filling_page.checkbox)).click();

        action.driver.findElement(By.xpath(Filling_page.Procced_To_Pay)).click();
        action.driver.findElement(By.xpath(Filling_page.confirm)).click();
        Thread.sleep(10000);
        //action.driver.findElement(By.xpath(prop.getProperty("paydetils"))).click();

    }

    @And("Assert details in payment page")
    public void assertDetailsInPaymentPage() throws InterruptedException {
        action.driver.findElement(By.xpath("//a[contains(text(),'Detail')]")).click();
        Thread.sleep(3000);
        Thread.sleep(3000);
        Assert.assertEquals(map.get("source"),action.driver.findElement(By.xpath(DetailpageXpath.source)).getText()); System.out.println("getting source place");
        Assert.assertEquals(map.get("destination"),action.driver.findElement(By.xpath(DetailpageXpath.destination)).getText()); System.out.println("getting destination place");
        Assert.assertEquals(map.get("sourcetime"),action.driver.findElement(By.xpath(DetailpageXpath.sourceTime)).getText()); System.out.println("getting arriving time");
        Assert.assertEquals(map.get("destinationtime"),action.driver.findElement(By.xpath(DetailpageXpath.destinationTime)).getText()); System.out.println("getitng destination time");
        Assert.assertEquals(map.get("Flight"),action.driver.findElement(By.xpath(DetailpageXpath.Flight)).getText()); System.out.println("getting flight number");
        action.driver.findElement(By.xpath("//b[contains(text(),'×')]")).click();
    }


}
