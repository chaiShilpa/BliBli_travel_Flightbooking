package com.quinbay.Steps;

import com.quinbay.Pages.DetailpageXpath;
import com.quinbay.Pages.HomepageXpath;
import com.quinbay.cucumber.CucumberHooks;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.omg.PortableInterceptor.ACTIVE;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.LinkedHashMap;
import java.util.List;

import static com.quinbay.Utils.Propertypage.prop;

public class StepsPage {
    CucumberHooks actions = new CucumberHooks();
    LinkedHashMap map=new LinkedHashMap();
    List<WebElement> Source,destination,sourcetime,destinationTime,Flightnumber;
    @Given("User is on  Travel page")
    public void userIsOnTravelPage() {
        actions.driver.get("https://www.blibli.com/travel");
    }

    @Then("change the date")
    public void changeTheDate() throws InterruptedException {
        Thread.sleep(2000);
        actions.driver.findElement(By.xpath(HomepageXpath.Date)).click();
        actions.driver.findElement(By.xpath("//button[@data-pika-month='3'][@data-pika-day='30']")).click();
    }

    @And("I click on Find tickets")
    public void iClickOnFindTickets() {
        actions.driver.findElement(By.xpath(HomepageXpath.Search_flight)).click();
    }

    @And("click on detail on flight list page and do the assert")
    public void clickOnDetailOnFlightListPageAndDoTheAssert() throws InterruptedException {Thread.sleep(5000);
        actions.driver.findElement(By.xpath(DetailpageXpath.Details)).click();
             Source=actions.driver.findElements(By.xpath(DetailpageXpath.Source));
             for(int i=0;i<Source.size();i++){
                String s= Source.get(0).getText();
                String s2=Source.get(1).getText();
                map.put("source",s);
                map.put("source2",s2); }
        Thread.sleep(5000);
         destination=actions.driver.findElements(By.xpath(DetailpageXpath.Destination));
        for(int i=0;i<destination.size();i++){
            String d=destination.get(1).getText();
            map.put("destination2",d); }
        sourcetime= actions.driver.findElements(By.xpath(DetailpageXpath.SourceTime));
        for(int i=0;i<sourcetime.size();i++){
            String t=sourcetime.get(0).getText();
            String t1=sourcetime.get(1).getText();
            map.put("sourcetime",t);
            map.put("sourcetime2",t1);
        }
         destinationTime=actions.driver.findElements(By.xpath(DetailpageXpath.DestinationTime));
        for(int i=0;i<destinationTime.size();i++){
            String desTime=destinationTime.get(0).getText();
            String desTime1=destinationTime.get(1).getText();
            map.put("Destinationtime",desTime);
            map.put("Destinationtime1",desTime1);
        }
         Flightnumber=actions.driver.findElements(By.xpath(DetailpageXpath.Flight1));
        for (int i=0;i<Flightnumber.size();i++){
            String flight1=Flightnumber.get(0).getText();
            String flight2=Flightnumber.get(1).getText() ;
            map.put("Flight1",flight1);
            map.put("Flight2",flight2);
        }
//        Thread.sleep(1000);
//        actions.driver.findElement(By.xpath(prop.getProperty("closeicon"))).click();
    }

    @And("click on Select_go")
    public void clickOnSelect_go() throws InterruptedException {
        Thread.sleep(5000);
        actions.driver.findElement(By.xpath(DetailpageXpath.Search)).click();
    }

    @And("click on detials in filling page and assert values")
    public void clickOnDetialsInFillingPageAndAssertValues() throws InterruptedException {
        Thread.sleep(5000);
        actions.driver.findElement(By.xpath(DetailpageXpath.Detail_Fillpage)).click();
        Thread.sleep(3000);
        Source=actions.driver.findElements(By.xpath(DetailpageXpath.Source));
        destination=actions.driver.findElements(By.xpath(DetailpageXpath.Destination));
        sourcetime= actions.driver.findElements(By.xpath(DetailpageXpath.SourceTime));
        destinationTime=actions.driver.findElements(By.xpath(DetailpageXpath.DestinationTime));
        Flightnumber=actions.driver.findElements(By.xpath(DetailpageXpath.Flight1));

        Assert.assertEquals(map.get("source"),Source.get(0).getText());
        Assert.assertEquals(map.get("source2"),Source.get(1).getText());
        Assert.assertEquals(map.get("destination2"),destination.get(1).getText());
        Assert.assertEquals(map.get("sourcetime"),sourcetime.get(0).getText());
        Assert.assertEquals(map.get("sourcetime2"),sourcetime.get(1).getText());
        Assert.assertEquals(map.get("Destinationtime"),destinationTime.get(0).getText());
        Assert.assertEquals(map.get("Destinationtime1"),destinationTime.get(1).getText());
        Assert.assertEquals(map.get("Flight1"),Flightnumber.get(0).getText());
        Assert.assertEquals(map.get("Flight2"),Flightnumber.get(1).getText());
        actions.driver.findElement(By.xpath("//b[contains(text(),'×')]")).click();
        Thread.sleep(3000);
        System.out.println("details on filling page");
    }

    @And("after filling the details do confirm order")
    public void afterFillingTheDetailsDoConfirmOrder() throws InterruptedException {
        Thread.sleep(3000);
        WebElement element=actions.driver.findElement(By.xpath("//*[@id=\"travel-blibli-app\"]/div/main/div[1]/section/div/div[2]/div[1]/div[2]/div/div/div[1]/div[1]/select"));
        Select sel=new Select(element);
        sel.selectByVisibleText("Nyonya");
        Thread.sleep(1000);
        actions.driver.findElement(By.xpath("//input[@name='fullName']")).sendKeys("chai");
        actions.driver.findElement(By.xpath("//input[@name='phoneNumber']")).sendKeys("1234567890");
        actions.driver.findElement(By.xpath("//input[@name='email']")).sendKeys("chai@gmail.com");
        actions.driver.findElement(By.xpath("//label[@for='copy_contact']")).click();
        //action.driver.findElement(By.xpath("Details")).click();
        Select s1=new Select(actions.driver.findElement(By.xpath("//*[@id=\"travel-blibli-app\"]/div/main/div[1]/section/div/div[2]/div[1]/div[4]/div[1]/div[2]/div/div[3]/div/div/ul/li[1]/select")));s1.selectByVisibleText("21");
        Select s2=new Select(actions.driver.findElement(By.xpath("//*[@id=\"travel-blibli-app\"]/div/main/div[1]/section/div/div[2]/div[1]/div[4]/div[1]/div[2]/div/div[3]/div/div/ul/li[2]/select")));s2.selectByVisibleText("Mei");
        Select s3=new Select(actions.driver.findElement(By.xpath("//*[@id=\"travel-blibli-app\"]/div/main/div[1]/section/div/div[2]/div[1]/div[4]/div[1]/div[2]/div/div[3]/div/div/ul/li[3]/select")));s3.selectByVisibleText("1999");
        actions.driver.findElement(By.xpath("//button[contains(text(),'Lanjutkan pemesanan')]")).click();
        actions.driver.findElement(By.xpath("//button[contains(text(),'Yakin, lanjutkan')]")).click();
        Thread.sleep(10000);
    }

    @And("Assert details in payment page for transit")
    public void assertDetailsInPaymentPageForTransit() throws InterruptedException {
        actions.driver.findElement(By.xpath("//a[contains(text(),'Detail')]")).click();
        Thread.sleep(3000);
        Source=actions.driver.findElements(By.xpath(DetailpageXpath.Source));
        destination=actions.driver.findElements(By.xpath(DetailpageXpath.Destination));
        sourcetime= actions.driver.findElements(By.xpath(DetailpageXpath.SourceTime));
        destinationTime=actions.driver.findElements(By.xpath(DetailpageXpath.DestinationTime));
        Flightnumber=actions.driver.findElements(By.xpath(DetailpageXpath.Flight1));

        Assert.assertEquals(map.get("source"),Source.get(0).getText());
        Assert.assertEquals(map.get("source2"),Source.get(1).getText());
        Assert.assertEquals(map.get("destination2"),destination.get(1).getText());
        Assert.assertEquals(map.get("sourcetime"),sourcetime.get(0).getText());
        Assert.assertEquals(map.get("sourcetime2"),sourcetime.get(1).getText());
        Assert.assertEquals(map.get("Destinationtime"),destinationTime.get(0).getText());
        Assert.assertEquals(map.get("Destinationtime1"),destinationTime.get(1).getText());
        Assert.assertEquals(map.get("Flight1"),Flightnumber.get(0).getText());
        Assert.assertEquals(map.get("Flight2"),Flightnumber.get(1).getText());
        actions.driver.findElement(By.xpath("//b[contains(text(),'×')]")).click();
        Thread.sleep(3000);

        System.out.println("fight is present");

    }
}
