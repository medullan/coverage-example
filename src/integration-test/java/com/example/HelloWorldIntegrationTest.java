/**
   Copyright 2010 David Green

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package com.example;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
//import org.junit.runners.JUnit4;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author GreenD
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@RunWith(JUnit4.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/conf/applicationContext.xml"})
public class HelloWorldIntegrationTest {
	
	// The webdriver
	private static WebDriver driver;

	@BeforeClass
	public static void initWebDriver() {
		driver = new FirefoxDriver();
	}
	
	@AfterClass
	public static void stopSeleniumClient() {
		try {
			driver.close();
			driver.quit();
		} catch( Throwable t ) {
			// Catch error & log, not critical for tests
			System.err.println("Error stopping driver: "+t.getMessage());
			t.printStackTrace(System.err);
		}
	}
	
	@Test
	public void testHelloWorld() {
		// Start from the homepage
		driver.get("http://localhost:" + System.getProperty("test.server.port") + "/helloworld/");
		HomePage homePage = new HomePage(driver);
		
		HelloWorldPage helloWorldPage = homePage.clickMessageLink();
		
		assertEquals("Hello world",helloWorldPage.getMessage());
	}
	
}
