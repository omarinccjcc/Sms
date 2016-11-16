package pe.edu.upeu.smscore.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchAlgorithmException;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.BadPaddingException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;

@SuppressWarnings("unchecked")
public class CommonUtils {

	// private static final Log LOG = LogFactory.getLog(CommonUtils.class);

	public static java.util.Date stringToDate(String dateStr, String pattern) {
		Date date = null;
		if (dateStr == null || dateStr.trim().equals("")) {
			return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		try {
			date = formatter.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static String dateToString(java.util.Date date, String pattern) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.format(date);
	}

	public static Date datePattern(java.util.Date date, String pattern) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return stringToDate(formatter.format(date), pattern);
	}

	public static String dateToString(java.util.Date date, String pattern, Locale locale) {
		String result = null;
		if (date == null) {
			return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat(pattern, locale);
		result = formatter.format(date);
		return result;
	}

	public static String generateFileName(String id, String fileType) {
		UUID random = UUID.randomUUID();
		String date = dateToString(new Date(), "yyyyMMddHHmmssSSSZ");
		return id + "_" + date + "_" + random.toString() + "." + fileType;
	}

	public static String generateUrlName() {
		UUID random = UUID.randomUUID();
		String date = dateToString(new Date(), "yyyyMMddHHmmssSSS");
		String value = random.toString().replaceAll("-", "").toLowerCase();
		return date + value;
	}

	public static String generateUrlName(String text) {
		text = getFirstWords(text, 80);
		text = unAccent(text);
		text = getOnlyNumbersAndLetters(text);
		String date = dateToString(new Date(), "yyyyMMddHHmmssSSS");
		return text + date;
	}

	public static String splitFormat(Object[] values) {
		StringBuilder finalValues = new StringBuilder();
		if (values.length > 0) {
			finalValues.append("'" + values[0] + "'");
			for (int i = 1; i < values.length; i++) {
				finalValues.append(",");
				finalValues.append("'" + values[i] + "'");
			}
		}
		return finalValues.toString();
	}

	public static int getNumberOfGroups(int quantity) {
		int groups = 1;
		if (quantity >= 2 && quantity <= 7) {
			groups = 1;
		} else if (quantity >= 8 && quantity <= 12) {
			groups = 2;
		} else if (quantity >= 13 && quantity <= 15) {
			groups = 3;
		} else if (quantity >= 16 && quantity <= 24) {
			groups = 4;
		} else if (quantity >= 25 && quantity <= 30) {
			groups = 5;
		} else if (quantity >= 31 && quantity <= 50) {
			groups = 6;
		} else if (quantity >= 51 && quantity <= 80) {
			groups = 7;
		} else if (quantity >= 81 && quantity <= 100) {
			groups = 8;
		} else if (quantity >= 101 && quantity <= 120) {
			groups = 9;
		} else if (quantity >= 121 && quantity <= 150) {
			groups = 10;
		} else if (quantity >= 151) {
			groups = 11;
		}
		return groups;
	}

	public static String getWordABC(int number) {
		String[] words = { "", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
				"T", "U", "V", "W", "X", "Y", "Z" };
		return words[number];
	}

	public static Date dateAddDays(Date date, int nDays) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, nDays); // add n days
		return cal.getTime();
	}

	public static Date dateAddMonth(Date date, int nMonth) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, nMonth); // add n Month
		return cal.getTime();
	}

	public static Date dateAddHour(Date date, int nhours) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR, nhours); // add n days
		return cal.getTime();
	}

	public static int getArraySize(List list) {
		if (list != null) {
			return list.size();
		}
		return 0;
	}

	public static int compareTo(Date first, Date second) {
		return first.compareTo(second);
	}

	public static void main(String[] args) {
		Date d1 = new Date();
		try {
			Thread.sleep(10);

		} catch (Exception e) {

		}
		Date d2 = new Date();
		System.out.println("First Date : " + d1);
		System.out.println("Second Date : " + d2);

		int results = d1.compareTo(d2);
		if (results > 0)
			System.out.println("First Date is after second");
		else if (results < 0)
			System.out.println("First Date is before second");
		else
			System.out.println("Both dates are equal");
	}

	public static boolean isValidEmail(String email) {
		Pattern p = Pattern.compile(email);
		Matcher m = p.matcher(email);
		return m.matches();
	}

	public static String getDateDescription(Date date, Locale locale) {
		return CommonUtils.dateToString(date, "EEEE, dd MMMM yyyy", locale);
	}

	public static String getDateDescriptionSpanish(Date date) {
		Locale newLocale = new Locale("ES");
		String dayDesc = CommonUtils.dateToString(date, "EEEE", newLocale);
		Integer x = dayDesc.length();
		String dayDescUpdated = dayDesc.substring(0, 1).toUpperCase().concat(dayDesc.substring(1, x));

		String monthDesc = CommonUtils.dateToString(date, "MMMM", newLocale);
		x = monthDesc.length();
		String monthDescUpdated = monthDesc.substring(0, 1).toUpperCase().concat(monthDesc.substring(1, x));

		return dayDescUpdated + ", " + CommonUtils.dateToString(date, "dd 'de'") + " " + monthDescUpdated + " "
				+ CommonUtils.dateToString(date, "'de' yyyy");
	}

	public static Map<String, Object> getMatchDayLabel(Date now, Date day) {
		Map<String, Object> data = new HashMap<String, Object>();
		String dayDescUpdated = getDateDescriptionSpanish(day);
		if (CommonUtils.dateToString(day, "yyyyMMdd").equals(CommonUtils.dateToString(now, "yyyyMMdd"))) {
			data.put("matchDayLabel", "Hoy");
		} else if (CommonUtils.dateToString(day, "yyyyMMdd")
				.equals(CommonUtils.dateToString(CommonUtils.dateAddDays(now, 1), "yyyyMMdd"))) {
			data.put("matchDayLabel", "Ma�ana");
		} else {
			data.put("matchDayLabel", dayDescUpdated);
		}
		data.put("matchDay", day);
		data.put("matchDateShortLabel", CommonUtils.dateToString(day, "dd/MM/yyyy"));
		data.put("matchDateLongLabel", dayDescUpdated);
		return data;
	}

	public static List<String> getHours() {
		List<String> hours = new ArrayList<String>();
		for (int i = 0; i < 24; i++) {
			String value = i + "";
			hours.add(value.length() == 1 ? "0" + value + ":00" : value + ":00");
		}
		return hours;
	}

	public static String unAccent(String str) {
		//
		// JDK1.5
		// use sun.text.Normalizer.normalize(s, Normalizer.DECOMP, 0);
		//
		String temp = Normalizer.normalize(str, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(temp).replaceAll("");
	}

	public static String getFirstWords(String word, int n) {
		String newWord = "";
		if (word != null && word.length() > n) {
			newWord = word.substring(0, n);
		} else {
			newWord = word;
		}
		return newWord;
	}

	public static String getOnlyNumbersAndLetters(String text) {
		return text.replaceAll("[^A-Za-z0-9]", "");
	}

	public static boolean validateImage(String text) {
		List<String> list = new ArrayList<String>();
		list.add("image/jpeg");
		list.add("image/png");
		list.add("image/gif");
		return list.contains(text);
	}

	public static String generateUrlNameFromText(String text) {
		text = unAccent(text);
		text = getOnlyNumbersAndLetters(text);
		return text;
	}

	private static final String ALGO = "AES";
	private static final byte[] keyValue = new byte[] { 'T', 'h', 'e', 'B', 'e', 's', 't', 'S', 'e', 'c', 'r', 'e', 't',
			'K', 'e', 'y' };

	public static String encrypt(String Data) {
		String encryptedValue = "";
		try {
			Key key = generateKey();
			Cipher c = Cipher.getInstance(ALGO);
			c.init(Cipher.ENCRYPT_MODE, key);
			byte[] encVal = c.doFinal(Data.getBytes());
			encryptedValue = new BASE64Encoder().encode(encVal);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return encryptedValue;
	}

	public static String decrypt(String encryptedData) {
		String decryptedValue = "";
		try {
			Key key = generateKey();
			Cipher c = Cipher.getInstance(ALGO);
			c.init(Cipher.DECRYPT_MODE, key);
			byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
			byte[] decValue = c.doFinal(decordedValue);
			decryptedValue = new String(decValue);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return decryptedValue;
	}

	private static Key generateKey() throws Exception {
		Key key = new SecretKeySpec(keyValue, ALGO);
		return key;
	}

	// public static void main(String[] args) throws Exception {
	// AESencrp mm = new AESencrp();
	//
	// System.out.println(":::::::::::::::"+mm.encrypt("omariccjcc"));
	// }

}
