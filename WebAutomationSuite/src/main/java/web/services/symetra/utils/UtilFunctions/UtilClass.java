package web.services.symetra.utils.UtilFunctions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class UtilClass {
	
	private static String os = System.getProperty("os.name").toLowerCase();
	
	public static String getSystemOS() {
		if (isWindows()) {
			//System.out.println("This is Windows");
			return "WINDOWS";
		} else if (isMac()) {
			System.out.println("This is Mac");
			return "MAC OS";
		} else if (isUnix()) {
			System.out.println("This is Unix or Linux");
			return "LINUX";
		} else {
			System.out.println("Your OS is not support!!");
		}
       return os;
	}

    private static boolean isWindows() {

	   return (os.indexOf("win") >= 0);

    }

    private static boolean isMac() {

	  return (os.indexOf("mac") >= 0);

    }

    private static boolean isUnix() {

	  return (os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0 || os.indexOf("aix") > 0 );
	
    }
     
    
    private static  String dateName;
	private static TakesScreenshot screenshot;
	private static File source;
	private static String destination;
	private static File finalDestination;
	private static String sourcepath = System.getProperty("user.dir");
	private static String fileseparator = System.getProperty("file.separator");
	private static String filepath = "TestReport";
	private static String failedpath = "FailedTestsScreenshots";
	private static String passedpath = "PassedTestsScreenshots";
	
	public static String getScreenshot(WebDriver driver, String classname,String screenshotName, String result) throws Exception {
		 dateName = new SimpleDateFormat("yyyy-MM-dd-hhmmss").format(new Date());
		 screenshot = (TakesScreenshot) driver;
		 source = screenshot.getScreenshotAs(OutputType.FILE);
		
		 if(result.equalsIgnoreCase("fail")) {
			 String path = sourcepath+fileseparator+failedpath+fileseparator+classname;
			 try {
				 File folderpath = new File(path);
				 if(!folderpath.exists()) {
					 if (folderpath.mkdir()) {
						 System.out.println("Directory : "+path+"  is created!" );

					 } else {
						 System.out.println("Failed to create directory: " + path);

					 }
				 }else {
					 System.out.println("Directory already exists: " + path);
				 }

			 }catch (Exception e) {
				 // TODO: handle exception
			 }

			 destination = path+fileseparator+screenshotName+dateName+".png";

		 } 
		
		 else if(result.equalsIgnoreCase("pass")){
			 String path = sourcepath+fileseparator+passedpath+fileseparator+classname;
			 try {
				 File folderpath = new File(path);
				 if(!folderpath.exists()) {
					 if (folderpath.mkdir()) {
						 System.out.println("Directory : "+path+"  is created!" );

					 } else {
						 System.out.println("Failed to create directory: " + path);

					 }
				 }else {
					 System.out.println("Directory already exists: " + path);
				 }

			 }catch (Exception e) {
				 // TODO: handle exception
			 }

			 destination = path+fileseparator+screenshotName+dateName+".png";
	     }
		   
		 finalDestination = new File(destination);
		 FileUtils.copyFile(source, finalDestination);
		 return finalDestination.getAbsolutePath();
   }
   
	public static String getBase64Screenshot(WebDriver driver, String classname ,String screenshotName, String result) throws IOException {
		if(driver==null) {
			System.out.println("Driver object null");
		}
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		dateName = new SimpleDateFormat("yyyy-MM-dd-hhmmss").format(new Date());
		if(result.equalsIgnoreCase("fail")) {
			 String path = sourcepath+fileseparator+failedpath+fileseparator+classname;
			 try {
				 File folderpath = new File(path);
				 if(!folderpath.exists()) {
					 if (folderpath.mkdir()) {
						 System.out.println("Directory : "+path+"  is created!" );

					 } else {
						 System.out.println("Failed to create directory: " + path);

					 }
				 }else {
					 System.out.println("Directory already exists: " + path);
				 }

			 }catch (Exception e) {
				 // TODO: handle exception
			 }

			 destination = path+fileseparator+screenshotName+dateName+".png";


		}else{
			 String path = sourcepath+fileseparator+passedpath+fileseparator+classname;
			 try {
				 File folderpath = new File(path);
				 if(!folderpath.exists()) {
					 if (folderpath.mkdir()) {
						 System.out.println("Directory : "+path+"  is created!" );

					 } else {
						 System.out.println("Failed to create directory: " + path);

					 }
				 }else {
					 System.out.println("Directory already exists: " + path);
				 }

			 }catch (Exception e) {
				 // TODO: handle exception
			 }

			 destination = path+fileseparator+screenshotName+dateName+".png";
		}
		finalDestination = new File(destination);
		FileUtils.copyFile(scrFile, finalDestination);
		
		String encodedBase64 = null;
		FileInputStream fileInputStreamReader = null;
		try {
			fileInputStreamReader = new FileInputStream(scrFile);
			byte[] bytes = new byte[(int)scrFile.length()];
			fileInputStreamReader.read(bytes);
			encodedBase64 = new String(Base64.getEncoder().encodeToString(bytes));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "data:image/png;base64,"+encodedBase64;
	}
   
	
 }	
	


