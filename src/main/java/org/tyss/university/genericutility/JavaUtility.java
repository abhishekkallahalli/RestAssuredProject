package org.tyss.university.genericutility;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * This Java Utility contains java methods for setting time format, get random number, fetch and store project directory. 
 * @author Abhishek K H
 */
public class JavaUtility {

	public String getDateAndTimeInSpecifiedFormat(String format) {
		String dateTime = new SimpleDateFormat(format).format(new Date()).toString();
		return dateTime;
	}

	public int getRandomNumber(int range) {
		int randomNumber = new Random().nextInt(range);
		return randomNumber;
	}

	public String getCurrentProjectDirectory() {
		String projectDir = System.getProperty("user.dir");
		return projectDir;
	}
	
	public String convertToTitleCase(String input) {
		StringBuilder titleCase = new StringBuilder();
		boolean nextTitleCase = true;

		for (char c : input.toCharArray()) {
			if (Character.isUpperCase(c)) {
				titleCase.append(" ");
			}
			if (nextTitleCase) {
				c = Character.toTitleCase(c);
				nextTitleCase = false;
			} else {
				c = Character.toLowerCase(c);
			}
			titleCase.append(c);
		}
		return titleCase.toString().trim();
	}

}
