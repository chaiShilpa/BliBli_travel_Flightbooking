package com.quinbay.Steps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.junit.Assert.*;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class ReturnFlightwithTransit {
    static WebDriver driver;
    JavascriptExecutor js;
    LinkedHashMap<String, String> map= new LinkedHashMap<>();
    List<WebElement> first=new ArrayList<>();
    LinkedHashMap<String , HashMap<String,String>> hashMap1= new LinkedHashMap<>();
    LinkedHashMap<String ,HashMap<String,String>> hashMap2= new LinkedHashMap<>();

    List<WebElement> elements=new ArrayList<>();
    List<HashMap<String,String>> list=new ArrayList<>();


    @Given("The user should go to the home page of the blibli travel website")
    public void theUserShouldGoToTheHomePageOfTheBlibliTravelWebsite() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        System.setProperty("webdriver.chrome.driver","path/to/driver/exe");
        System.setProperty("webdriver.chrome.driver","src/test/resources/driver/chromedriver");
        driver=new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.blibli.com/travel");

    }

    @Then("Enter the details to book a flight")
    public void enterTheDetailsToBookAFlight() throws InterruptedException {
        Thread.sleep(2000);
        WebElement place=driver.findElement(By.xpath("//input[@placeholder='Pilih kota tujuan']"));
        place.sendKeys("Denpasar");
        place.sendKeys(Keys.ENTER);
        driver.findElement(By.xpath("//button[@class=\"date__text--btn\"]")).click();
        driver.findElement(By.xpath("//button[@data-pika-month='3'][@data-pika-day='30']")).click();

        Thread.sleep(5000);
        driver.findElement(By.xpath("//small[contains(text(),'Pulang Pergi')]")).click();
        Thread.sleep(3000);

    }

    @And("Click book a flight")
    public void clickBookAFlight() throws InterruptedException {
        driver.findElement(By.xpath("//button[@class=\"button button--orange button--big button--full form__button\"]")).click();
        Thread.sleep(3000);
    }

    @And("Scroll the page until the flights are visible")
    public void scrollThePageUntilTheFlightsAreVisible() throws InterruptedException {
        js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1500)");
        Thread.sleep(3000);
    }

    @And("click on the detail link and store details in map")
    public void clickOnTheDetailLink() throws InterruptedException {
        Thread.sleep(10000);
        driver.findElement(By.xpath("//*[@id=\"travel-blibli-app\"]/div/main/div[1]/section/div/div[3]/div[2]/div[2]/div[3]/div[3]/div[6]/div/div/div[1]/div[2]/div/ul/li[1]/a")).click();

        first=driver.findElements(By.xpath("//*[@class=\"hub__flight-detail-right__departure\"]/div[@class=\"route__departure-city\"]"));
        System.out.println(first.size());
        for (int i=0;i<first.size();i++)
        {
            map=new LinkedHashMap<>();
            map.put("source",driver.findElements(By.xpath("//*[@class=\"hub__flight-detail-right__departure\"]/div[@class=\"route__departure-city\"]")).get(i).getText());
            map.put("destination",driver.findElements(By.xpath("//*[@class=\"hub__flight-detail-right__arrival\"]/div[@class=\"route__arrival-city padding-top-10\"]")).get(i).getText());
            map.put("Airline-code",driver.findElements(By.xpath("//div[@class=\"route__departure-airline-code\"]")).get(i).getText());
            map.put("start-time",driver.findElements(By.xpath("//div[@class=\"route__departure-time\"]")).get(i).getText());
            map.put("end-time",driver.findElements(By.xpath("//div[@class=\"route__arrival-time\"]")).get(i).getText());
            hashMap1.put("Transit"+(i+1),map);

        }
    }


    @And("Click on the select go button")
    public void clickOnTheSelectGoButton() {
        driver.findElement(By.xpath("//*[@id=\"travel-blibli-app\"]/div/main/div[1]/section/div/div[3]/div[2]/div[2]/div[3]/div[3]/div[6]/div/div/div[2]/button")).click();
    }

    @And("Click on the detail section for return and verify the values")
    public void clickOnTheDetailSectionForReturnAndVerifyTheValues() throws InterruptedException {
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"travel-blibli-app\"]/div/main/div[1]/section/div/div[3]/div[2]/div[2]/div[3]/div[4]/div[1]/div/div/div[1]/div[2]/div/ul/li[1]/a")).click();
        for (int i=0;i<first.size();i++)
        {
            map= new LinkedHashMap<>();
            map.put("source",driver.findElements(By.xpath("//*[@class=\"hub__flight-detail-right__departure\"]/div[@class=\"route__departure-city\"]")).get(i+2).getText());
            map.put("destination",driver.findElements(By.xpath("//*[@class=\"hub__flight-detail-right__arrival\"]/div[@class=\"route__arrival-city padding-top-10\"]")).get(i+2).getText());
            map.put("Airline-code",driver.findElements(By.xpath("//div[@class=\"route__departure-airline-code\"]")).get(i+2).getText());
            map.put("start-time",driver.findElements(By.xpath("//div[@class=\"route__departure-time\"]")).get(i+2).getText());
            map.put("end-time",driver.findElements(By.xpath("//div[@class=\"route__arrival-time\"]")).get(i+2).getText());
            hashMap2.put("Transit"+(i+1),map);

        }
        System.out.println(hashMap2);
    }

    @Then("Click on the select go button for return")
    public void click_on_the_select_go_button_for_return() throws InterruptedException {
        //System.out.println(hashMap);
       // Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"travel-blibli-app\"]/div/main/div[1]/section/div/div[3]/div[2]/div[2]/div[3]/div[4]/div[1]/div/div/div[2]/button")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//button[contains(text(),'Pesan Tiket')]")).click();
    }
    @And("Enter the details to proceed")
    public void enterTheDetailsToProceed() throws InterruptedException {
        Thread.sleep(3000);
        Select surname = new Select(driver.findElement(By.name("title")));
        surname.selectByVisibleText("Nona");
        driver.findElement(By.name("fullName")).sendKeys("Samyuktha");
        driver.findElement(By.name("phoneNumber")).sendKeys("08594389534");
        driver.findElement(By.name("email")).sendKeys("samyuktha@gmail.com");
        js.executeScript("window.scrollBy(0,700)");
        driver.findElement(By.xpath("//*[@id=\"travel-blibli-app\"]/div/main/div[1]/section/div/div[2]/div[1]/div[4]/div[1]/div[1]/div[2]/label")).click();
        Select s1=new Select(driver.findElement(By.xpath("//*[@id=\"travel-blibli-app\"]/div/main/div[1]/section/div/div[2]/div[1]/div[4]/div[1]/div[2]/div/div[3]/div/div/ul/li[1]/select")));s1.selectByVisibleText("21");
        Select s2=new Select(driver.findElement(By.xpath("//*[@id=\"travel-blibli-app\"]/div/main/div[1]/section/div/div[2]/div[1]/div[4]/div[1]/div[2]/div/div[3]/div/div/ul/li[2]/select")));s2.selectByVisibleText("Mei");
        Select s3=new Select(driver.findElement(By.xpath("//*[@id=\"travel-blibli-app\"]/div/main/div[1]/section/div/div[2]/div[1]/div[4]/div[1]/div[2]/div/div[3]/div/div/ul/li[3]/select")));s3.selectByVisibleText("1999");

    }

    @And("Click on the detail section and verify")
    public void clickOnTheDetailSectionAndVerify() throws InterruptedException {
        Thread.sleep(5000);
        //detail= prop.getProperty("detail1");
        driver.findElement(By.xpath("//*[@id=\"travel-blibli-app\"]/div/main/div[1]/section/div/div[2]/div[2]/div[1]/div/div/div[1]/div[1]/div[2]/a")).click();
        //driver.findElement(By.xpath(detail)).click();
        Thread.sleep(3000);
        for (int i=0;i<first.size();i++) {
            assertEquals(hashMap1.get("Transit"+(i+1)).get("source"), driver.findElements(By.xpath("//*[@class=\"hub__flight-detail-right__departure\"]/div[@class=\"route__departure-city\"]")).get(i).getText());
            assertEquals(hashMap1.get("Transit"+(i+1)).get("destination"), driver.findElements(By.xpath("//*[@class=\"hub__flight-detail-right__arrival\"]/div[@class=\"route__arrival-city padding-top-10\"]")).get(i).getText());
            assertEquals(hashMap1.get("Transit"+(i+1)).get("Airline-code"), driver.findElements(By.xpath("//*[@class=\"route__departure-airline-code\"]")).get(i).getText());
            assertEquals(hashMap1.get("Transit"+(i+1)).get("start-time"), driver.findElements(By.xpath("//*[@class=\"route__departure-time\"]")).get(i).getText());
            assertEquals(hashMap1.get("Transit"+(i+1)).get("end-time"), driver.findElements(By.xpath("//*[@class=\"route__arrival-time\"]")).get(i).getText());

        }

        Thread.sleep(3000);
        driver.findElement(By.xpath("//b[contains(text(),'×')]")).click();


    }

    @And("Click on the detail section for return")
    public void clickOnTheDetailSectionForReturn() throws InterruptedException {
        Thread.sleep(5000);
        // detail= prop.getProperty("detail1");
        driver.findElement(By.xpath("//*[@id=\"travel-blibli-app\"]/div/main/div[1]/section/div/div[2]/div[2]/div[1]/div/div/div[2]/div[1]/div[2]/a")).click();
        //driver.findElement(By.xpath(detail)).click();
        Thread.sleep(3000);
        for (int i=0;i<first.size();i++)
        {
            assertEquals(hashMap2.get("Transit"+(i+1)).get("source"), driver.findElements(By.xpath("//*[@class=\"hub__flight-detail-right__departure\"]/div[@class=\"route__departure-city\"]")).get(i).getText());
            assertEquals(hashMap2.get("Transit"+(i+1)).get("destination"), driver.findElements(By.xpath("//*[@class=\"hub__flight-detail-right__arrival\"]/div[@class=\"route__arrival-city padding-top-10\"]")).get(i).getText());
            assertEquals(hashMap2.get("Transit"+(i+1)).get("Airline-code"), driver.findElements(By.xpath("//*[@class=\"route__departure-airline-code\"]")).get(i).getText());
            assertEquals(hashMap2.get("Transit"+(i+1)).get("start-time"), driver.findElements(By.xpath("//*[@class=\"route__departure-time\"]")).get(i).getText());
            assertEquals(hashMap2.get("Transit"+(i+1)).get("end-time"), driver.findElements(By.xpath("//*[@class=\"route__arrival-time\"]")).get(i).getText());

        }
        driver.findElement(By.xpath("//b[contains(text(),'×')]")).click();
    }

    @And("Click on continue ordering")
    public void clickOnContinueOrdering() throws InterruptedException {
        driver.findElement(By.xpath("//button[contains(text(),'Lanjutkan pemesanan')]")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//button[contains(text(),'Yakin, lanjutkan')]")).click();
        Thread.sleep(5000);
        js.executeScript("window.scrollBy(0,600)");
    }

    @And("Click on the detail section and verify the values")
    public void clickOnTheDetailSectionAndVerifyTheValues() throws InterruptedException {
        Thread.sleep(5000);
        elements=driver.findElements(By.xpath("//*[@class=\"grid__col-5 order-header-right\"]/a[@class=\"order-header-right-value\"]"));

        elements.get(0).click();
        //driver.findElement(By.xpath(elements)).click();
        Thread.sleep(3000);
        for (int i=0;i<first.size();i++) {
            assertEquals(hashMap1.get("Transit"+(i+1)).get("source"), driver.findElements(By.xpath("//*[@class=\"hub__flight-detail-right__departure\"]/div[@class=\"route__departure-city\"]")).get(i).getText());
            assertEquals(hashMap1.get("Transit"+(i+1)).get("destination"), driver.findElements(By.xpath("//*[@class=\"hub__flight-detail-right__arrival\"]/div[@class=\"route__arrival-city padding-top-10\"]")).get(i).getText());
            assertEquals(hashMap1.get("Transit"+(i+1)).get("Airline-code"), driver.findElements(By.xpath("//*[@class=\"route__departure-airline-code\"]")).get(i).getText());
            assertEquals(hashMap1.get("Transit"+(i+1)).get("start-time"), driver.findElements(By.xpath("//*[@class=\"route__departure-time\"]")).get(i).getText());
            assertEquals(hashMap1.get("Transit"+(i+1)).get("end-time"), driver.findElements(By.xpath("//*[@class=\"route__arrival-time\"]")).get(i).getText());

        }
        driver.findElement(By.xpath("//b[contains(text(),'×')]")).click();
    }


    @And("Click on the detail section and verify the values for return")
    public void clickOnTheDetailSectionAndVerifyTheValuesForReturn() throws InterruptedException {
        Thread.sleep(3000);
        elements.get(1).click();
        Thread.sleep(3000);
        for (int i=0;i<first.size();i++)
        {
            assertEquals(hashMap2.get("Transit"+(i+1)).get("source"), driver.findElements(By.xpath("//*[@class=\"hub__flight-detail-right__departure\"]/div[@class=\"route__departure-city\"]")).get(i).getText());
            assertEquals(hashMap2.get("Transit"+(i+1)).get("destination"), driver.findElements(By.xpath("//*[@class=\"hub__flight-detail-right__arrival\"]/div[@class=\"route__arrival-city padding-top-10\"]")).get(i).getText());
            assertEquals(hashMap2.get("Transit"+(i+1)).get("Airline-code"), driver.findElements(By.xpath("//*[@class=\"route__departure-airline-code\"]")).get(i).getText());
            assertEquals(hashMap2.get("Transit"+(i+1)).get("start-time"), driver.findElements(By.xpath("//*[@class=\"route__departure-time\"]")).get(i).getText());
            assertEquals(hashMap2.get("Transit"+(i+1)).get("end-time"), driver.findElements(By.xpath("//*[@class=\"route__arrival-time\"]")).get(i).getText());

        }
        driver.findElement(By.xpath("//b[contains(text(),'×')]")).click();
    }
}
