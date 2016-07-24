package pe.edu.upeu.smscore.util;

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

//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;

@SuppressWarnings("unchecked")
public class CommonUtils {
	
//	private static final Log LOG = LogFactory.getLog(CommonUtils.class);
	
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
		String result = null;
		if (date == null) {
			return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		result = formatter.format(date);
		return result;
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
		String date=dateToString(new Date(), "yyyyMMddHHmmssSSSZ");
		return id+"_"+date+"_"+random.toString()+"."+fileType;
	}

	public static String generateUrlName() {
		UUID random = UUID.randomUUID();
		String date=dateToString(new Date(), "yyyyMMddHHmmssSSS");
		String value = random.toString().replaceAll("-", "").toLowerCase();
		return date+value;
	}
	
	public static String generateUrlName(String text) {
		text = getFirstWords(text, 80);
		text = unAccent(text);
		text = getOnlyNumbersAndLetters(text);
		String date = dateToString(new Date(), "yyyyMMddHHmmssSSS");
		return text+date;
	}
	
	public static String splitFormat(Object[] values) {
		StringBuilder finalValues = new StringBuilder();
		if(values.length>0){
			finalValues.append("'"+values[0]+"'");
			for (int i=1; i<values.length; i++) {
				finalValues.append(",");
				finalValues.append("'"+values[i]+"'");
	        }
		}
		return finalValues.toString();
	}
	
	public static int getNumberOfGroups(int quantity){
		int groups = 1;
		if(quantity >= 2 && quantity <= 7){
			groups = 1;
		}else if(quantity >= 8 && quantity <= 12){
			groups = 2;
		}else if(quantity >= 13 && quantity <= 15){
			groups = 3;
		}else if(quantity >= 16 && quantity <= 24){
			groups = 4;
		}else if(quantity >= 25 && quantity <= 30){
			groups = 5;
		}else if(quantity >= 31 && quantity <= 50){
			groups = 6;
		}else if(quantity >= 51 && quantity <= 80){
			groups = 7;
		}else if(quantity >= 81 && quantity <= 100){
			groups = 8;
		}else if(quantity >= 101 && quantity <= 120){
			groups = 9;
		}else if(quantity >= 121 && quantity <= 150){
			groups = 10;
		}else if(quantity >= 151){
			groups = 11;
		}
		return groups;
	}
	
	public static String getWordABC(int number){
		String[] words = {"","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","T","U","V","W","X","Y","Z"};
		return words[number];
	}
	
	public static String getPhaseFinals(int number, int phases){
		String[] phasesString = null;
		if(phases == 10){
			String[] words = {"","Ronda 1","Ronda 2","Ronda 3","Ronda 4","Ronda 5","Ronda 6","Octavos de Final","Cuartos de Final","Semi Final","Final"};
			phasesString = words;
		}else if(phases == 9){
			String[] words = {"","Ronda 1","Ronda 2","Ronda 3","Ronda 4","Ronda 5","Octavos de Final","Cuartos de Final","Semi Final","Final"};
			phasesString = words;
		}else if(phases == 8){
			String[] words = {"","Ronda 1","Ronda 2","Ronda 3","Ronda 4","Octavos de Final","Cuartos de Final","Semi Final","Final"};
			phasesString = words;
		}else if(phases == 7){
			String[] words = {"","Ronda 1","Ronda 2","Ronda 3","Octavos de Final","Cuartos de Final","Semi Final","Final"};
			phasesString = words;
		}else if(phases == 6){
			String[] words = {"","Ronda 1","Ronda 2","Octavos de Final","Cuartos de Final","Semi Final","Final"};
			phasesString = words;
		}else if(phases == 5){
			String[] words = {"","Ronda 1","Octavos de Final","Cuartos de Final","Semi Final","Final"};
			phasesString = words;
		}else if(phases == 4){
			String[] words = {"","Octavos de Final","Cuartos de Final","Semi Final","Final"};
			phasesString = words;
		}else if(phases == 3){
			String[] words = {"","Cuartos de Final","Semi Final","Final"};
			phasesString = words;
		}else if(phases == 2){
			String[] words = {"","Semi Final","Final"};
			phasesString = words;
		}else if(phases == 1){
			String[] words = {"","Final"};
			phasesString = words;
		}
		return phasesString[number];
	}
	
	public static int getNumberOfPhases(int numberOfTeams){
		int phases = 1;
		if(numberOfTeams == 2){
			phases = 1;
		}else if(numberOfTeams > 2 && numberOfTeams <= 4){
			phases = 2;
		}else if(numberOfTeams > 4 && numberOfTeams <= 8){
			phases = 3;
		}else if(numberOfTeams > 8 && numberOfTeams <= 16){
			phases = 4;
		}else if(numberOfTeams > 16 && numberOfTeams <= 32){
			phases = 5;
		}else if(numberOfTeams > 32 && numberOfTeams <= 64){
			phases = 6;
		}else if(numberOfTeams > 64 && numberOfTeams <= 128){
			phases = 7;
		}else if(numberOfTeams > 128 && numberOfTeams <= 256){
			phases = 8;
		}else if(numberOfTeams > 256 && numberOfTeams <= 512){
			phases = 9;
		}else if(numberOfTeams > 512 && numberOfTeams <= 1024){
			phases = 10;
		}
		return phases;
	}
	
	public static int getNumberOfTeamsByPhase(int phase){
		int teams = 1;
		if(phase == 10){
			teams = 1024;
		}else if(phase == 9){
			teams = 512;
		}else if(phase == 8){
			teams = 256;
		}else if(phase == 7){
			teams = 128;
		}else if(phase == 6){
			teams = 64;
		}else if(phase == 5){
			teams = 32;
		}else if(phase == 4){
			teams = 16;
		}else if(phase == 3){
			teams = 8;
		}else if(phase == 2){
			teams = 4;
		}else if(phase == 1){
			teams = 2;
		}
		return teams;
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
		cal.add(Calendar.HOUR,nhours); // add n days
		return cal.getTime();
	}
	
	
	public static int getArraySize(List list){
		if(list!=null){
			return list.size();
		}
		return 0;
	}
	

//	public static void orderList(List list, final String property) {
//		final String getter = "get" + Character.toUpperCase(property.charAt(0)) + property.substring(1);
//		Collections.sort(list, new Comparator() {
//			public int compare(Object obj1, Object obj2) {
//				Class clase = obj1.getClass();
//				try {
//					Method getProperty = clase.getMethod(getter);
//					Object property1 = getProperty.invoke(obj1);
//					Object property2 = getProperty.invoke(obj2);
//					if (property1 instanceof Comparable && property2 instanceof Comparable) {
//						Comparable prop1 = (Comparable) property1;
//						Comparable prop2 = (Comparable) property2;
//						return prop1.compareTo(prop2);
//					} else {
//						if (property1.equals(property2)) {
//							return 0;
//						} else {
//							return 1;
//						}
//					}
//				} catch (NoSuchMethodException e) {
//					LOG.info(e.getMessage(), e);
//				} catch (InvocationTargetException e) {
//					LOG.info(e.getMessage(), e);
//				} catch (IllegalAccessException e) {
//					LOG.info(e.getMessage(), e);
//				}
//				return 0;
//			}
//		});
//	}
	
//	public static void reverseOrderList(List list, final String property) {
//		final String getter = "get" + Character.toUpperCase(property.charAt(0)) + property.substring(1);
//		Collections.sort(list, Collections.reverseOrder(new Comparator() {
//			public int compare(Object obj1, Object obj2) {
//				Class clase = obj1.getClass();
//				try {
//					Method getProperty = clase.getMethod(getter);
//					Object property1 = getProperty.invoke(obj1);
//					Object property2 = getProperty.invoke(obj2);
//					if (property1 instanceof Comparable && property2 instanceof Comparable) {
//						Comparable prop1 = (Comparable) property1;
//						Comparable prop2 = (Comparable) property2;
//						return prop1.compareTo(prop2);
//					} else {
//						if (property1.equals(property2)) {
//							return 0;
//						} else {
//							return 1;
//						}
//					}
//				} catch (NoSuchMethodException e) {
//					LOG.info(e.getMessage(), e);
//				} catch (InvocationTargetException e) {
//					LOG.info(e.getMessage(), e);
//				} catch (IllegalAccessException e) {
//					LOG.info(e.getMessage(), e);
//				}
//				return 0;
//			}
//		}));
//	}
	
	public static int compareTo(Date first ,Date second ){
		return  first.compareTo(second);
	}
	
	public static void main(String[] args) {
	    Date d1 = new Date();
	    try{
	      Thread.sleep(10);

	    }catch(Exception e){

	    }
	    Date d2 = new Date();
	    System.out.println("First Date : " + d1);
	    System.out.println("Second Date : " + d2);
	   
	    int results = d1.compareTo(d2);
	    if(results > 0)
	      System.out.println("First Date is after second");
	    else if (results < 0)
	      System.out.println("First Date is before second");
	    else
	      System.out.println("Both dates are equal");
	  }
	
	public static boolean isValidEmail(String email){
		Pattern p = Pattern.compile(email);
		Matcher m = p.matcher(email);
		return m.matches();
	}
	
	public static String getDateDescription(Date date, Locale locale){
		return CommonUtils.dateToString(date, "EEEE, dd MMMM yyyy", locale);
	}
	
	public static String getDateDescriptionSpanish(Date date){
		Locale newLocale = new Locale("ES");
		String dayDesc = CommonUtils.dateToString(date, "EEEE", newLocale);
		Integer x = dayDesc.length();
		String dayDescUpdated = dayDesc.substring(0,1).toUpperCase().concat(dayDesc.substring(1, x));
		
		String monthDesc = CommonUtils.dateToString(date, "MMMM", newLocale);
		x = monthDesc.length();
		String monthDescUpdated = monthDesc.substring(0,1).toUpperCase().concat(monthDesc.substring(1, x));
		
		return dayDescUpdated +", "+ CommonUtils.dateToString(date, "dd 'de'")+
			" "+monthDescUpdated +" "+CommonUtils.dateToString(date, "'de' yyyy");
	}
	
	public static Map<String, Object> getMatchDayLabel(Date now, Date day) {
		Map<String, Object> data = new HashMap<String, Object>();
		String dayDescUpdated = getDateDescriptionSpanish(day);
		if(CommonUtils.dateToString(day, "yyyyMMdd").equals(CommonUtils.dateToString(now, "yyyyMMdd"))){
			data.put("matchDayLabel", "Hoy");
		}else if(CommonUtils.dateToString(day, "yyyyMMdd").equals(CommonUtils.dateToString(CommonUtils.dateAddDays(now, 1), "yyyyMMdd"))){
			data.put("matchDayLabel", "Ma�ana");
		}else {
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
			String value = i+"";
			hours.add(value.length()==1 ? "0"+value+":00" : value+":00");
		}
		return hours;
	}
	
	public static String unAccent(String str) {
		//
		// JDK1.5
		//   use sun.text.Normalizer.normalize(s, Normalizer.DECOMP, 0);
		//
		String temp = Normalizer.normalize(str, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(temp).replaceAll("");
	}
	
	public static String getFirstWords(String word, int n) {
		String newWord = "";
		if(word != null &&
				word.length() > n){
			newWord = word.substring(0, n);
		}else{ 
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
}
