package ReCaptcha;


import java.io.File;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Registration {

	

	private static String baseURL = "https://qawww3.ziprealty.com/";
	private static String listViewURL = "/for-sale-homes/Dallas-TX-2419c";
	private static String DetailPageURL = "/property/8601-Wingate-Dr-Dallas-TX-75209/66250013/detail";
	private static String logoutURL = "/login/logout.jsp";
	private static String newSignupURL = "/registration/register_all.jsp?metro=phoenix";
	private static String c21Check = "//input[@class= 'form-std__cb-highlight']";
	private static String iframe = "//iframe[not(contains(@style,'width: 100%; height: 100%;'))][@title='recaptcha challenge']";
	private static String test;
	private static String featureBranch;
	private static String github;
	public static String sprint2;
	public static String sprint3_11;
	
	public void sprint2Method(){
		//Sprint 2 Method
	}
	

	public static void main(String[] args)  throws Exception {

		Registration test = new Registration();
		
//		 test.requestShowing();
//		 test.saveHomeBtnDetailView();
		//test.shareEmailIconDetailView();
//		 test.requestMoreInfo();
//		 test.goSeeThisHome();
//		 test.heartIconDetailView();
//		test.tourThisHomeInlineFormDetailView();
//		test.heartIconListView();
//		test.saveSearchListView();
//		test.globalSignUp();
		/*
		 * String baseUrl = "https://www.google.com"; driver.get(baseUrl);
		 * Thread.sleep(20000); driver.close();
		 * //driver.findElement(By.cssSelector("#head .signin")).click();
		 */
//		 test.addNotesSignup();
//		 test.addPlacesSignup();
	}

	private WebDriver driver;
	Long timeAppender = System.currentTimeMillis();
	
	public WebDriver acceptCookies() {
		driver.findElement(By.xpath("//button[@id = 'truste-consent-required']")).click();
		return driver;
	}

	public void openBrowser(String listURL, String detailURL, String signUpURL) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\Eclipse\\Eclipse lib\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		Thread.sleep(2000);
		if (listURL.equals("") && detailURL.equals("") && signUpURL.equals(""))
			driver.get(baseURL);
		else if (listURL.equals("") && signUpURL.equals(""))
			driver.get(baseURL + DetailPageURL);
		else if (detailURL.equals("") && signUpURL.equals(""))
			driver.get(baseURL + listViewURL);
		else
			driver.get(baseURL + newSignupURL);
		Thread.sleep(2000);
		waitForTitleToBePresent(15);
	}

	public void waitForTitleToBePresent(int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.titleContains(driver.getTitle()));
		String title = driver.getTitle();
	}
	
	public void checkBox() {
		List<WebElement> list = driver.findElements(By.xpath(c21Check));
		for (WebElement e : list) {
			if (e.isEnabled() && e.isDisplayed())
				e.click();
		}
	}
	
	public void clickCheckbox() {
        WebElement c21CheckBox = driver.findElement(By.cssSelector(".font-disclaimer.mt-10> input"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        if(!c21CheckBox.isSelected()) {
            js.executeScript("arguments[0].click();", c21CheckBox);
        }
    }
	
	public static void takeSnapShot(WebDriver webdriver, String fileWithPath) throws Exception {
		TakesScreenshot scrShot = ((TakesScreenshot) webdriver);
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		File DestFile = new File(fileWithPath);
		FileUtils.copyFile(SrcFile, DestFile);
	}

	public void switchFrameByClassName(String locatorElement) {
		WebElement frame = driver.findElement(By.className(locatorElement));
		driver.switchTo().frame(frame);
	}

	public void switchFrameByName(String frameName) {
		WebElement frame = driver.findElement(By.id(frameName));
		driver.switchTo().frame(frame);
	}

	public void doRegistration(String firstName, String lastName, String email, String phoneNum, String password)
			throws Exception {
		/***
		 * For Request a showing, Request More Info, Go See this home, TourThisHome
		 * Inline Form
		 ***/
		try {
			driver.findElement(By.id("first_name")).sendKeys(firstName);
			driver.findElement(By.id("last_name")).sendKeys(lastName);
			driver.findElement(By.id("email")).sendKeys(email);
			driver.findElement(By.id("phone")).sendKeys(phoneNum);
			driver.findElement(By.id("password")).sendKeys(password);
			Thread.sleep(2000);
			driver.findElement(By.id("password")).sendKeys(Keys.ENTER);
			if (driver.getCurrentUrl().contains("qawww7"))
				clickCheckbox();
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void doRegistration(String firstName, String lastName, String email, String password) throws Exception {
		/*** For save home, heart icon in list and detail page **/
		try {
			driver.findElement(By.id("first_name")).sendKeys(firstName);
			driver.findElement(By.id("last_name")).sendKeys(lastName);
			driver.findElement(By.id("email")).sendKeys(email);
			driver.findElement(By.id("password")).sendKeys(password);
			Thread.sleep(2000);
			driver.findElement(By.id("password")).sendKeys(Keys.ENTER);
			if (driver.getCurrentUrl().contains("qawww7"))
				clickCheckbox();
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}

	
	public void doNewSignup(String firstName, String lastName, String email, String confirmEmail, String password, String confirmPassword){
		try {
			driver.findElement(By.name("first_name")).sendKeys(firstName);
			driver.findElement(By.name("last_name")).sendKeys(lastName);
			driver.findElement(By.name("email_address")).sendKeys(email);
			driver.findElement(By.name("confirm_email_address")).sendKeys(confirmEmail);
			driver.findElement(By.name("password")).sendKeys(password);
			driver.findElement(By.name("confirm_password")).sendKeys(confirmPassword);
			Thread.sleep(2000);
			driver.findElement(By.name("confirm_password")).sendKeys(Keys.ENTER);
			if (driver.getCurrentUrl().contains("qawww7"))
				clickCheckbox();
			Thread.sleep(3000);
		} catch (InterruptedException e) {		
			e.printStackTrace();
		}
	}
	
	
	//----------------------------------------------------------------------------------------
	@Test
	public void requestShowing() throws Exception {
		try {
			
			openBrowser("", DetailPageURL,"");
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement requestShowingBtn = driver.findElement(By.xpath("//div[@id='propInfoWrapper']//a[@data-title='Tour Home']"));
			js.executeScript("arguments[0].click();", requestShowingBtn);
			
			Thread.sleep(10000);
			switchFrameByClassName("modalfx-iframe");
			doRegistration("qatest" + timeAppender, "test", "qatest" + timeAppender + "@test.com", "5467895493",
					"test15082021");
			Thread.sleep(5000);

			List<WebElement> isCaptchaDisplayed = driver.findElements(By.xpath(
					"//iframe[not(contains(@style,'width: 100%; height: 100%;'))][@title='recaptcha challenge']"));
			/*
			 * List<WebElement> closeBtn =
			 * driver.findElements(By.xpath("//div[text()='Close']/.."));
			 */
			if (isCaptchaDisplayed.size() > 0) {
				takeSnapShot(driver, "E:\\MediaFiles\\Recaptcha\\requestShowing.jpg");
				driver.close();
			} else {
				driver.get(baseURL + logoutURL);
				driver.close();
				System.out.println("Recaptcha Not Displayed For Request For Showing");
				requestShowing();
			}
		} catch (InterruptedException e) {		
			e.printStackTrace();
		}

	}

	//----------------------------------------------------------------------------------------
	public void requestMoreInfo() throws Exception {

		try {
			openBrowser("", DetailPageURL,"");
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement requestMoreInfoBtn = driver
					.findElement(By.xpath("//div[@class='actionBoxStripOld']//a[@data-title='Get More Details']"));
			js.executeScript("arguments[0].click();", requestMoreInfoBtn);

			Thread.sleep(10000);
			switchFrameByClassName("modalfx-iframe");
			doRegistration("qatest" + timeAppender, "test", "qatest" + timeAppender + "@test.com", "5467895493",
					"test15082021");
			List<WebElement> isCaptchaDisplayed = driver.findElements(By.xpath(
					"//iframe[not(contains(@style,'width: 100%; height: 100%;'))][@title='recaptcha challenge']"));//
			/*
			 * List<WebElement> closeBtn =
			 * driver.findElements(By.xpath("//div[text()='Close']/.."));
			 */
			if (isCaptchaDisplayed.size() > 0) {
				takeSnapShot(driver, "E:\\MediaFiles\\Recaptcha\\requestMoreInfo.jpg");
				driver.close();
			} else {
				driver.get(baseURL + logoutURL);
				driver.close();
				System.out.println("Recaptcha Not Displayed For Request More Info");
				requestMoreInfo();
			}
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}

	}

	//----------------------------------------------------------------------------------------
	public void goSeeThisHome() throws Exception {

		try {
			openBrowser("", DetailPageURL,"");
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement goSeeThisHomeBtn = driver
					.findElement(By.xpath("//div[@class='actionBoxStripOld']//a[@data-title='Tour Home']"));
			js.executeScript("arguments[0].click();", goSeeThisHomeBtn);
			Thread.sleep(10000);
			switchFrameByClassName("modalfx-iframe");
			doRegistration("qatest" + timeAppender, "test", "qatest" + timeAppender + "@test.com", "5467895493",
					"test15082021");
			List<WebElement> isCaptchaDisplayed = driver.findElements(By.xpath(iframe));//
			if (isCaptchaDisplayed.size() > 0) {
				takeSnapShot(driver, "E:\\MediaFiles\\Recaptcha\\goSeeThisHome.jpg");
				driver.close();
			} else {
				driver.get(baseURL + logoutURL);
				driver.close();
				System.out.println("Recaptcha Not Displayed For Go See This Home");
				goSeeThisHome();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	
	//----------------------------------------------------------------------------------------
	public void saveHomeBtnDetailView() throws Exception {
		try {
			openBrowser("", DetailPageURL,"");
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement requestShowingBtn = driver
					.findElement(By.xpath("//div[@class='actionBoxStripOld']//a[@data-title='save this home']"));
			js.executeScript("arguments[0].click();", requestShowingBtn);
			Thread.sleep(10000);
			switchFrameByClassName("modalfx-iframe");
			doRegistration("qatest" + timeAppender, "test", "qatest" + timeAppender + "@test.com", "test15082021");
			List<WebElement> isCaptchaDisplayed = driver.findElements(By.xpath(
					"//iframe[not(contains(@style,'width: 100%; height: 100%;'))][@title='recaptcha challenge']"));
			/*
			 * List<WebElement> closeBtn =
			 * driver.findElements(By.xpath("//div[text()='Close']/.."));
			 */
			if (isCaptchaDisplayed.size() > 0) {
				takeSnapShot(driver, "E:\\MediaFiles\\Recaptcha\\saveHomeBtnDetailView.jpg");
				driver.close();
			} else {
				driver.get(baseURL + logoutURL);
				driver.close();
				System.out.println("Recaptcha Not Displayed For Save Home Button in Detail View");
				saveHomeBtnDetailView();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	//----------------------------------------------------------------------------------------
	public void shareEmailIconDetailView() throws Exception {
		try {
			openBrowser("", DetailPageURL,"");
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement shareEmailIcon = driver.findElement(By.xpath("//div[@id='bd']//a[@title='Email']"));
			js.executeScript("arguments[0].click();", shareEmailIcon);
			Thread.sleep(10000);
			switchFrameByClassName("modalfx-iframe");
			doRegistration("qatest" + timeAppender, "test", "qatest" + timeAppender + "@test.com", "test15082021");
			List<WebElement> isCaptchaDisplayed = driver.findElements(By.xpath(
					"//iframe[not(contains(@style,'width: 100%; height: 100%;'))][@title='recaptcha challenge']"));
			/*
			 * List<WebElement> closeBtn =
			 * driver.findElements(By.xpath("//div[text()='Close']/.."));
			 */
			if (isCaptchaDisplayed.size() > 0) {
				takeSnapShot(driver, "E:\\MediaFiles\\Recaptcha\\shareEmailIconDetailView.jpg");
				driver.close();
			} else {
				driver.get(baseURL + logoutURL);
				driver.close();
				System.out.println("Recaptcha Not Displayed For Save Home Button in Detail View");
				shareEmailIconDetailView();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	//----------------------------------------------------------------------------------------
	public void heartIconDetailView() throws Exception {
		try {
			openBrowser("", DetailPageURL,"");
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement heartIcon = driver.findElement(By.xpath("//div[@id='pvCont']/a[@data-title='save this home']"));
			js.executeScript("arguments[0].click();", heartIcon);
			Thread.sleep(10000);
			switchFrameByClassName("modalfx-iframe");
			doRegistration("qatest" + timeAppender, "test", "qatest" + timeAppender + "@test.com", "test15082021");
			List<WebElement> isCaptchaDisplayed = driver.findElements(By.xpath(
					"//iframe[not(contains(@style,'width: 100%; height: 100%;'))][@title='recaptcha challenge']"));
			/*
			 * List<WebElement> closeBtn =
			 * driver.findElements(By.xpath("//div[text()='Close']/.."));
			 */
			if (isCaptchaDisplayed.size() > 0) {
				takeSnapShot(driver, "E:\\MediaFiles\\Recaptcha\\heartIconDetailView.jpg");
				driver.close();
			} else {
				driver.get(baseURL + logoutURL);
				driver.close();
				System.out.println("Recaptcha Not Displayed For Heart Icon in Detail View");
				heartIconDetailView();
			}
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}

	//----------------------------------------------------------------------------------------
	//@Test(priority = 5)
	public void heartIconListView() throws Exception {
		try {
			openBrowser(listViewURL, "","");
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement heartIconListView = driver.findElement(By.id("saveLink0"));
			js.executeScript("arguments[0].click();", heartIconListView);
			Thread.sleep(10000);
			switchFrameByClassName("modalfx-iframe");
			doRegistration("qatest" + timeAppender, "test", "qatest" + timeAppender + "@test.com", "test15082021");
			List<WebElement> isCaptchaDisplayed = driver.findElements(By.xpath(
					"//iframe[not(contains(@style,'width: 100%; height: 100%;'))][@title='recaptcha challenge']"));
			/*
			 * List<WebElement> closeBtn = driver.findElements(By.
			 * xpath("//button[@aria-label='Close save this home modal']"));
			 */
			if (isCaptchaDisplayed.size() > 0) {
				takeSnapShot(driver, "E:\\MediaFiles\\Recaptcha\\heartIconListView.jpg");
				driver.close();
			} else {
				driver.get(baseURL + logoutURL);
				driver.close();
				System.out.println("Recaptcha Not Displayed For Heart Icon in List View");
				heartIconListView();
			}
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}

	
	//----------------------------------------------------------------------------------------
	public void tourThisHomeInlineFormDetailView() throws Exception {

		try {
			openBrowser("", DetailPageURL,"");
			switchFrameByName("embedded_visit_iframe");
			doRegistration("qatest" + timeAppender, "test", "qatest" + timeAppender + "@test.com", "5467895493",
					"test15082021");
			WebElement isCaptchaDisplayed = driver.findElement(By.xpath(
					"//iframe[not(contains(@style,'width: 100%; height: 100%;'))][@title='recaptcha challenge']"));//

			if (isCaptchaDisplayed.isDisplayed()) {
				takeSnapShot(driver, "E:\\MediaFiles\\Recaptcha\\tourThisHomeInLineForm.jpg");
				driver.close();
			} else {
				driver.get(baseURL + logoutURL);
				driver.close();
				System.out.println("Recaptcha Not Displayed For Tour This Home Inline Form");
				tourThisHomeInlineFormDetailView();
			}
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}

	}

	//----------------------------------------------------------------------------------------
	public void saveSearchListView() throws Exception {
		try {
			openBrowser(listViewURL, "","");
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement saveSearchBtnListView = driver.findElement(By.id("saveSearchLink"));
			js.executeScript("arguments[0].click();", saveSearchBtnListView);
			Thread.sleep(10000);
			switchFrameByClassName("modalfx-iframe");
			doRegistration("qatest" + timeAppender, "test", "qatest" + timeAppender + "@test.com", "test15082021");
			List<WebElement> isCaptchaDisplayed = driver.findElements(By.xpath(
					"//iframe[not(contains(@style,'width: 100%; height: 100%;'))][@title='recaptcha challenge']"));
			/*
			 * List<WebElement> closeBtn = driver.findElements(By.
			 * xpath("//button[@aria-label='Close save this home modal']"));
			 */
			if (isCaptchaDisplayed.size() > 0) {
				takeSnapShot(driver, "E:\\MediaFiles\\Recaptcha\\saveSearchListView.jpg");
				driver.close();
			} else {
				driver.get(baseURL + logoutURL);
				driver.close();
				System.out.println("Recaptcha Not Displayed For Save Search in List View");
				saveSearchListView();
			}
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}

	
	public void globalSignUp() throws Exception {
		try {
			openBrowser("", "",newSignupURL);
			doNewSignup("qatest" + timeAppender, "test", "qatest" + timeAppender + "@test.com", "qatest" + timeAppender + "@test.com", "test15082021", "test15082021");
			List<WebElement> isCaptchaDisplayed = driver.findElements(By.xpath(
					"//iframe[not(contains(@style,'width: 100%; height: 100%;'))][@title='recaptcha challenge']"));
			if (isCaptchaDisplayed.size() > 0) {
				takeSnapShot(driver, "E:\\MediaFiles\\Recaptcha\\globalSingup.jpg");
				driver.close();
			} else {
				driver.get(baseURL + logoutURL);
				driver.close();
				System.out.println("Recaptcha Not Displayed For Global Signup");
				globalSignUp();
			}
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}
	
	
	public void addPlacesSignup() throws Exception{
        try {
            openBrowser("", DetailPageURL,"");
            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement addPlacesLink = driver.findElement(By.xpath("//a[@data-title='Save places']"));
            js.executeScript("arguments[0].scrollIntoView(true);", addPlacesLink);
            js.executeScript("arguments[0].click();", addPlacesLink);
            Thread.sleep(10000);
            switchFrameByClassName("modalfx-iframe");
            savePlacesRegistration("testlocation", "testaddress","qatest" + timeAppender, "test", "qatest" + timeAppender + "@test.com", "test15082021");
            List<WebElement> isCaptchaDisplayed = driver.findElements(By.xpath(
                    "//iframe[not(contains(@style,'width: 100%; height: 100%;'))][@title='recaptcha challenge']"));
			Thread.sleep(5000);

            if (isCaptchaDisplayed.size() > 0) {
                takeSnapShot(driver, "E:\\MediaFiles\\Recaptcha\\addPlacesDetailView.jpg");
                driver.close();
            } else {
                driver.get(baseURL + logoutURL);
                driver.close();
                System.out.println("Recaptcha Not Displayed For Add Places in Detail View");
                addNotesSignup();
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
	
	public void savePlacesRegistration(String placeName, String placeAddress, String firstName, String lastName, String email, String password)
            throws Exception {
        try {
            driver.findElement(By.id("place_name")).sendKeys(placeName);
            driver.findElement(By.id("place_address")).sendKeys(placeAddress);
            driver.findElement(By.id("first_name")).sendKeys(firstName);
            driver.findElement(By.id("last_name")).sendKeys(lastName);
            driver.findElement(By.id("email")).sendKeys(email);
            driver.findElement(By.id("password")).sendKeys(password);
            Thread.sleep(2000);
            driver.findElement(By.id("password")).sendKeys(Keys.ENTER);
            if (driver.getCurrentUrl().contains("qawww7"))
				clickCheckbox();
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

	public void saveNoteRegistration(String firstName, String lastName, String email, String notes, String password)
			throws Exception {
		try {
			driver.findElement(By.id("first_name")).sendKeys(firstName);
			driver.findElement(By.id("last_name")).sendKeys(lastName);
			driver.findElement(By.id("email")).sendKeys(email);
			driver.findElement(By.id("note")).sendKeys(notes);
			driver.findElement(By.id("password")).sendKeys(password);
			Thread.sleep(2000);
			driver.findElement(By.id("password")).sendKeys(Keys.ENTER);
			if (driver.getCurrentUrl().contains("qawww7"))
				clickCheckbox();
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void addNotesSignup() throws Exception {
		try {
			openBrowser("", DetailPageURL,"");
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement addNotesLink = driver.findElement(By.id("addNoteLink"));
			js.executeScript("arguments[0].click();", addNotesLink);
			Thread.sleep(10000);
			switchFrameByClassName("modalfx-iframe");
			saveNoteRegistration("qatest" + timeAppender, "test", "qatest" + timeAppender + "@test.com", "test notes" , "test15082021");
			List<WebElement> isCaptchaDisplayed = driver.findElements(By.xpath(
					"//iframe[not(contains(@style,'width: 100%; height: 100%;'))][@title='recaptcha challenge']"));
			Thread.sleep(5000);
			if (isCaptchaDisplayed.size() > 0) {
				takeSnapShot(driver, "E:\\MediaFiles\\Recaptcha\\addNotesDetailView.jpg");
				driver.close();
			} else {
				driver.get(baseURL + logoutURL);
				driver.close();
				System.out.println("Recaptcha Not Displayed For Add Notes in Detail View");
				addNotesSignup();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
