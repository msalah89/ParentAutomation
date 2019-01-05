package utilities;


import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class Helper {

	// Method to take screenshot when the test cases fail
	public static void captureScreenshot(WebDriver driver , String screenshotname) 
	{
		Path dest = Paths.get("./Screenshots",screenshotname+".png"); 
		try {
			Files.createDirectories(dest.getParent());
			FileOutputStream out = new FileOutputStream(dest.toString());
			out.write(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
			out.close();
		} catch (IOException e) {
			System.out.println("Excpetion while taking screenshot"+ e.getMessage());
		}
	}
public static String twoDigits(String Number){
		return String.format("%02d", Integer.parseInt(Number));


}
	public static  int getMonthNumber(String month){

        Date date = null;
        try {
            date = new SimpleDateFormat("MMM", Locale.ENGLISH).parse(month);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
       int _month =cal.get(Calendar.MONTH)+1;
       return _month;


    }
	public static String getMonth(int month) {
		Locale locale = new Locale("en", "US");
		return new DateFormatSymbols(locale).getMonths()[month - 1];
	}
}
