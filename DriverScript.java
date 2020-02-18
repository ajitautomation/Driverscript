package TestScript;

import java.util.Iterator;
import java.util.Set;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class DriverScript {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\Browsers\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("http://localhost:100");
		driver.manage().window().maximize();
		
		System.out.println(System.getProperty("user.dir"));
		Xls_Reader xr=new Xls_Reader(System.getProperty("user.dir")+"\\src\\Data\\TestCases.xlsx");
		int rowcount=xr.getRowCount("Sheet1");
		System.out.println(rowcount);
		for(int i=2;i<=rowcount;i++)
		{
			String vRun=xr.getCellData("Sheet1", "Run", i);
			if(vRun.equalsIgnoreCase("ON"))
			{
				String vTCName=xr.getCellData("Sheet1", "TCName", i);
				//System.out.println(vTCName);
				switch(vTCName)
				{
				case "vTiger_login_verifyAppUrl_TC001":
					String expectedTitle="vtiger CRM - Commercial Open Source CRM";
					String actualTitle=driver.getTitle();
					if(expectedTitle.equals(actualTitle))
					{
						System.out.println(vTCName+"  PASSED");
						xr.setCellData("Sheet1", "Status", i, "PASSED");
					}
					else
					{
						System.out.println(vTCName+"  FAILED");
						xr.setCellData("Sheet1", "Status", i, "FAILED");
					}
					break;
				case "vTiger_login_verifyAppLogo_TC003":
					if(driver.findElement(By.xpath("//img[@src='include/images/login_left.gif']")).isDisplayed())
					{
						System.out.println(vTCName+"  PASSED");
						xr.setCellData("Sheet1", "Status", i, "PASSED");
					}
					else
					{
						System.out.println(vTCName+"  FAILED");
						xr.setCellData("Sheet1", "Status", i, "FAILED");
					}
					break;
				case "vTiger_login_verifyText_KeyModule_TC004":
					if(driver.findElement(By.xpath("//font[text()='Key Modules']")).isDisplayed())
					{
						System.out.println(vTCName+"  PASSED");
						xr.setCellData("Sheet1", "Status", i, "PASSED");
					}
					else
					{
						System.out.println(vTCName+"  FAILED");
						xr.setCellData("Sheet1", "Status", i, "FAILED");
					}
					break;
				case "Vtiger_login_verifyLink_vtigerCustomerPortal_TC05":
					if(driver.findElements(By.linkText("vtiger Customer Portal")).size()==1)
					{
						System.out.println(vTCName+"  PASSED");
						xr.setCellData("Sheet1", "Status", i, "PASSED");
					}
					else
					{
						System.out.println(vTCName+"  FAILED");
						xr.setCellData("Sheet1", "Status", i, "FAILED");
					}
					break;
				case "vTiger_login_verifyTextBoxEditble_TC006":
					driver.findElement(By.name("user_name")).sendKeys("AQH");
					String getval=driver.findElement(By.name("user_name")).getAttribute("value");
					if(getval.equals("AQH"))
					{
						System.out.println(vTCName+"  PASSED");
						xr.setCellData("Sheet1", "Status", i, "PASSED");
					}
					else
					{
						System.out.println(vTCName+"  FAILED");
						xr.setCellData("Sheet1", "Status", i, "FAILED");
					}
					break;
				case "vTiger_login_verifywithInvalidCredentials_TC007":
					driver.findElement(By.name("user_name")).sendKeys("AQH");
					driver.findElement(By.name("user_password")).sendKeys("AQH123");
					driver.findElement(By.name("Login")).click();
					if(driver.findElement(By.xpath("//td[contains(text(),'You must specify a valid username and password.')]")).isDisplayed())
					{
						System.out.println(vTCName+"  PASSED");
						xr.setCellData("Sheet1", "Status", i, "PASSED");
					}
					else
					{
						System.out.println(vTCName+"  FAILED");
						xr.setCellData("Sheet1", "Status", i, "FAILED");
					}
					break;
				case "vTiger_login_verifywithValidCredentials_TC008":
					driver.findElement(By.name("user_name")).clear();
					driver.findElement(By.name("user_name")).sendKeys("admin");
					driver.findElement(By.name("user_password")).clear();
					driver.findElement(By.name("user_password")).sendKeys("admin");
					driver.findElement(By.name("Login")).click();
					if(driver.findElement(By.linkText("Logout")).isDisplayed())
					{
						System.out.println(vTCName+"  PASSED");
						xr.setCellData("Sheet1", "Status", i, "PASSED");
					}
					else
					{
						System.out.println(vTCName+"  FAILED");
						xr.setCellData("Sheet1", "Status", i, "FAILED");
					}
					break;
				/*case "vTiger_MyContactValidate_TC009":
					driver.findElement(By.linkText("My Account")).click();
					//System.out.println(driver.findElement(By.xpath("//td [@class='moduleTitle']")).getText());
				   if(driver.findElement(By.xpath("//td [@class='moduleTitle']")).getText().equals("User Administrator (admin)"))
					{
						System.out.println(vTCName+"  PASSED");
						xr.setCellData("Sheet1", "Status", i, "PASSED");
					}
					else
					{
						System.out.println(vTCName+"  FAILED");
						xr.setCellData("Sheet1", "Status", i, "FAILED");
					}
					break;*/
				case "vTiger_LeadCreation_TC009":
					driver.findElement(By.linkText("New Lead")).click();
				   if(driver.findElement(By.xpath("//td[@class='moduleTitle']")).getText().equals("Lead:"))
					{
						System.out.println(vTCName+"  PASSED");
						xr.setCellData("Sheet1", "Status", i, "PASSED");
					}
					else
					{
						System.out.println(vTCName+"  FAILED");
						xr.setCellData("Sheet1", "Status", i, "FAILED");
					}
					break;
				case "vTiger_LeadCreationFirstName_TC0010":
					Select sel=new Select(driver.findElement(By.xpath("//select[@name='salutationtype']")));
				    sel.selectByIndex(1);
				    //String str=sel.getFirstSelectedOption().getText();
				    //System.out.println(str);
				   if(sel.getFirstSelectedOption().getText().equals("Mr."))
					{
						System.out.println(vTCName+"  PASSED");
						xr.setCellData("Sheet1", "Status", i, "PASSED");
					}
					else
					{
						System.out.println(vTCName+"  FAILED");
						xr.setCellData("Sheet1", "Status", i, "FAILED");
					}
					break;
				case "vTiger_verifyLead_manadatoryFields_TC0011":
                    driver.findElements(By.name("button")).get(0).click();
					
					Alert alt=driver.switchTo().alert();
					String lnameMsg=alt.getText();
					System.out.println(lnameMsg);
					alt.accept();
					//alt.dismiss();
					String vLname=xr.getCellData("Sheet1", "Lname", i).trim();
					driver.findElement(By.name("lastname")).sendKeys(vLname);
					
					driver.findElements(By.name("button")).get(0).click();
					Alert alt2=driver.switchTo().alert();
					String CompMsg=alt2.getText();
					System.out.println(CompMsg);
					alt2.accept();
					String vComp=xr.getCellData("Sheet1", "Comp", i).trim();
					driver.findElement(By.name("company")).sendKeys(vComp);
					driver.findElements(By.name("button")).get(0).click();
					
					
					
					if(driver.findElement(By.xpath("//td[@class='dataLabel' and text()='Last Name:']/following::td[text()='"+vLname+"']")).isDisplayed() && driver.findElement(By.xpath("//td[@class='dataLabel' and text()='Company:']/following::td[text()='"+vComp+"']")).isDisplayed())
					{
						System.out.println(vTCName+"  PASSED");
						xr.setCellData("Sheet1", "Status", i, "PASSED");
					}
					else
					{
						System.out.println(vTCName+"  FAILED");
						xr.setCellData("Sheet1", "Status", i, "FAILED");
					}
					break;
                    case "vTiger_verifyLead_Delete_TC0012":
					
					driver.findElement(By.name("Delete")).click();
					
					Alert alt3=driver.switchTo().alert();
					String deletemsg=alt3.getText();
					System.out.println(deletemsg);					
					alt3.dismiss();
					
					driver.findElement(By.name("Delete")).click();
					Alert alt4=driver.switchTo().alert();
					String deletemsg2=alt4.getText();
					System.out.println(deletemsg2);					
					alt4.accept();
					
					if(driver.findElement(By.xpath("//td[@class='formHeader' and contains(text(),'Lead Search')]")).isDisplayed())
					{
						System.out.println(vTCName+"  PASSED");
						xr.setCellData("Sheet1", "Status", i, "PASSED");
					}
					else
					{
						System.out.println(vTCName+"  FAILED");
						xr.setCellData("Sheet1", "Status", i, "FAILED");
					}
					break;
                    case "vTiger_verify_PopUp_Window_TC0013":	
                    	driver.findElement(By.linkText("New Potential")).click();
                    	driver.findElement(By.name("btn1")).click();
                    	Thread.sleep(5000);
                    	
                    	Set<String> set=driver.getWindowHandles();
                    	Iterator<String> iter=set.iterator();
                    	String firstwindow=iter.next();
                    	System.out.println(firstwindow);
                    	String secondwindow=iter.next();
                    	System.out.println(secondwindow);
                    	
                    	driver.switchTo().window(secondwindow);	
                    	
                    	driver.findElement(By.linkText("vtiger")).click();
                    	
                    	driver.switchTo().window(firstwindow);	
                    	String vtxt=driver.findElement(By.name("account_name")).getAttribute("value").trim();
                    	
                    	if(vtxt.equals("vtiger"))
                    	{
                    		System.out.println(vTCName+"  PASSED");
                    		xr.setCellData("Sheet1", "Status", i, "PASSED");
                    	}
                    	else
                    	{
                    		System.out.println(vTCName+"  FAILED");
                    		xr.setCellData("Sheet1", "Status", i, "FAILED");
                    	}
                    	break;
                    case "vTiger_verifyLeadSourceField_TC0014":
                    	driver.findElement(By.linkText("New Lead")).click();
    					Select sel1=new Select(driver.findElement(By.xpath("//select[@name='leadsource']")));
    				    sel1.selectByIndex(4);
    				    String str1=sel1.getFirstSelectedOption().getText();
    				    System.out.println(str1);
    				   if(sel1.getFirstSelectedOption().getText().equals(str1))
    					{
    						System.out.println(vTCName+"  PASSED");
    						xr.setCellData("Sheet1", "Status", i, "PASSED");
    					}
    					else
    					{
    						System.out.println(vTCName+"  FAILED");
    						xr.setCellData("Sheet1", "Status", i, "FAILED");
    					}
    					break;
                    case "vTiger_verifyIndustryField_TC0015":
    					Select sel2=new Select(driver.findElement(By.xpath("//select[@name='industry']")));
    				    sel2.selectByIndex(2);
    				    String str2=sel2.getFirstSelectedOption().getText();
    				    System.out.println(str2);
    				   if(sel2.getFirstSelectedOption().getText().equals(str2))
    					{
    						System.out.println(vTCName+"  PASSED");
    						xr.setCellData("Sheet1", "Status", i, "PASSED");
    					}
    					else
    					{
    						System.out.println(vTCName+"  FAILED");
    						xr.setCellData("Sheet1", "Status", i, "FAILED");
    					}
    					break;
                    case "vTiger_verifyLeadMobileNumber_TC0016":
                    	driver.findElement(By.name("mobile")).sendKeys("1234567890123  ");
    				    String str3=driver.findElement(By.name("mobile")).getAttribute("value").trim();
    				    System.out.println(str3);
    				    int len=str3.length();
    				    System.out.println(len);
    				   if(len<=10)
    					{
    						System.out.println(vTCName+"  PASSED");
    						xr.setCellData("Sheet1", "Status", i, "PASSED");
    					}
    					else
    					{
    						System.out.println("Mobile number should be in 10 digit only");
    						System.out.println(vTCName+"  FAILED");
    						xr.setCellData("Sheet1", "Status", i, "FAILED");
    					}
    					break;
                    case "vTiger_verifyLeadStatusField_TC0017":
    					Select sel3=new Select(driver.findElement(By.xpath("//select[@name='leadstatus']")));
    				    sel3.selectByIndex(2);
    				    String str4=sel3.getFirstSelectedOption().getText();
    				    System.out.println(str4);
    				   if(sel3.getFirstSelectedOption().getText().equals(str4))
    					{
    						System.out.println(vTCName+"  PASSED");
    						xr.setCellData("Sheet1", "Status", i, "PASSED");
    					}
    					else
    					{
    						System.out.println(vTCName+"  FAILED");
    						xr.setCellData("Sheet1", "Status", i, "FAILED");
    					}
    					break;
                    case "vTiger_verifyLeadRatingField_TC0018":
    					Select sel4=new Select(driver.findElement(By.xpath("//select[@name='rating']")));
    				    sel4.selectByIndex(2);
    				    String str5=sel4.getFirstSelectedOption().getText();
    				    System.out.println(str5);
    				   if(sel4.getFirstSelectedOption().getText().equals(str5))
    					{
    						System.out.println(vTCName+"  PASSED");
    						xr.setCellData("Sheet1", "Status", i, "PASSED");
    					}
    					else
    					{
    						System.out.println(vTCName+"  FAILED");
    						xr.setCellData("Sheet1", "Status", i, "FAILED");
    					}
    					break;
                    case "vTiger_verifyLeadPostalCode_TC0019":
                    	driver.findElement(By.name("code")).sendKeys("41210513  ");
    				    String pcode=driver.findElement(By.name("code")).getAttribute("value").trim();
    				    System.out.println(pcode);
    				    int len1=pcode.length();
    				    System.out.println(len1);
    				   if(len1<=6)
    					{
    						System.out.println(vTCName+"  PASSED");
    						xr.setCellData("Sheet1", "Status", i, "PASSED");
    					}
    					else
    					{
    						System.out.println("Postal Code should be in 6 digits only");
    						System.out.println(vTCName+"  FAILED");
    						xr.setCellData("Sheet1", "Status", i, "FAILED");
    					}
    					break;
                    case "vTiger_verifyLeadSaveButton1_TC0020":
    					driver.findElement(By.name("lastname")).sendKeys("Gandhi");
    					driver.findElement(By.name("company")).sendKeys("NCP");
    					driver.findElements(By.xpath("(//input[@name='button'])")).get(0).click();
    				   if(driver.findElement(By.xpath("//td[@class='moduleTitle']")).isDisplayed())
    					{
    						System.out.println(vTCName+"  PASSED");
    						xr.setCellData("Sheet1", "Status", i, "PASSED");
    						driver.findElement(By.name("Delete")).click();
    						Alert alt5=driver.switchTo().alert();
    						String deletemsg4=alt5.getText();
    						System.out.println(deletemsg4);					
    						alt5.accept();
    						//driver.findElement(By.linkText("New Lead"));
    					}
    					else
    					{ 
    						System.out.println("Postal Code should be in 6 digits only");
    						System.out.println(vTCName+"  FAILED");
    						xr.setCellData("Sheet1", "Status", i, "FAILED"); 
    					}
    					break;
                    case "vTiger_verifyLeadSaveButton2_TC0021":
                    	driver.findElement(By.linkText("New Lead")).click();  
    					driver.findElement(By.name("lastname")).sendKeys("Ajit");
    					driver.findElement(By.name("company")).sendKeys("NCP");
    					driver.findElements(By.xpath("(//input[@name='button'])")).get(2).click();
    					//driver.findElement(By.xpath("(//input[@value='  Save  '])[2]")).click();
    				   if(driver.findElement(By.xpath("//td[@class='moduleTitle']")).isDisplayed())
    					{
    						System.out.println(vTCName+"  PASSED");
    						xr.setCellData("Sheet1", "Status", i, "PASSED");
    						driver.findElement(By.name("Delete")).click();
    						Alert alt5=driver.switchTo().alert();
    						String deletemsg4=alt5.getText();
    						System.out.println(deletemsg4);					
    						alt5.accept();
    					}
    					else
    					{
    						System.out.println("Postal Code should be in 6 digits only");
    						System.out.println(vTCName+"  FAILED");
    						xr.setCellData("Sheet1", "Status", i, "FAILED");
    					}
    					break;
                    /*case "Vtiger_Contacts_TC0017":
    					
    					driver.findElement(By.linkText("Contacts")).click();
    					//System.out.println(driver.findElement(By.xpath("//td[@class='moduleTitle']")).getText());
    					driver.findElement(By.xpath("(//input[@name='firstname'])[2]")).sendKeys("Mary");
    					driver.findElement(By.xpath("(//input[@name='lastname'])[2]")).sendKeys("Smith");
    					driver.findElement(By.name("accountname")).sendKeys("X-CEED INC 99");
    					driver.findElement(By.xpath("(//input[@value='Search'])[2]")).click();
    					
    					if(driver.findElement(By.xpath("//td[@class='moduleTitle']")).getText().equals(" Contacts: Home"))
    						
    					{
    						System.out.println(vTCName+"  PASSED");
    						xr.setCellData("Sheet1", "Status", i, "PASSED");
    						Thread.sleep(2000);
    						
    					}
    					else
    					{
    						System.out.println(vTCName+"  FAILED");
    						xr.setCellData("Sheet1", "Status", i, "FAILED");
    					}
    					break;*/
				}
			}
		}

	}

}
