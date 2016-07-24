package pe.edu.upeu.smscore.service.services;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.util.NumberUtils;
//import org.springframework.stereotype.Service;

import pe.edu.upeu.smscore.dao.SmsDAO;
import pe.edu.upeu.smscore.domain.Person;
import pe.edu.upeu.smscore.util.CommonUtils;

@Service("smsService")
public class SmsServiceImpl {

	public static void main_(String[] args) throws IOException {
		Date date = new Date();
		System.out.println("DATE" + CommonUtils.dateToString(date, "yyyy_MM_dd_HH_mm_ss"));

		String fname = "D:\\www\\apache-tomcat-6.0.18\\webapps\\Sms\\files\\Presentacion.xlsx"; // or
																								// "C:\\Test.xls"
		// C:\\SDI-XL.xls
		InputStream inp = new FileInputStream(fname);
		XSSFWorkbook wb = new XSSFWorkbook(inp); // Declare XSSF WorkBook
		XSSFSheet sheet = wb.getSheetAt(0); // sheet can be used as common for
											// XSSF and HSSF
		Iterator<org.apache.poi.ss.usermodel.Row> rows = sheet.rowIterator();
		while (rows.hasNext()) {
			org.apache.poi.ss.usermodel.Row row = rows.next();
			System.out.println("row#=" + row.getRowNum() + "");
			if (row.getRowNum() != 0) {
				// Iterator cells = row.cellIterator();
				System.out.println(row.getCell(0));
				System.out.println(row.getCell(1));
				System.out.println(row.getCell(2));

				// System.out.println(row.getCell(3)); 995590753
				// String cell3 = row.getCell(3).toString();
				try {
					Long cell3 = Long.valueOf(row.getCell(3).toString());
					if (cell3.toString().length() == 9) {
						System.out.println("Numerico " + cell3);
					} else {
						System.err.println("ERROR en TAMAÑO " + cell3);
					}
				} catch (NumberFormatException e) {
					System.err.println("No Integer " + row.getCell(3));
					// TODO: handle exception
				}
				// try {
				//
				// Integer.parseInt(cell3);
				// System.out.println("Integer " + row.getCell(3));
				// if(cell3.length()!=9){
				// System.err.println("Integer TAMAÑo ERROR " + row.getCell(3));
				// }
				// } catch (NumberFormatException nfe) {
				// System.err.println("No Integer " + row.getCell(3)+" >> ");
				// }

				System.out.println(row.getCell(4));

				// while (cells.hasNext()) {
				// Cell cell = (Cell) cells.next();
				//
				// switch (cell.getCellType()) {
				// case Cell.CELL_TYPE_STRING:
				// System.out.println(cell.getRichStringCellValue().getString());
				// break;
				// case Cell.CELL_TYPE_NUMERIC:
				// if (DateUtil.isCellDateFormatted(cell)) {
				// System.out.println(cell.getDateCellValue() + "");
				// } else {
				// System.out.println(cell.getNumericCellValue()+"NUMEROOO");
				// }
				// break;
				// case Cell.CELL_TYPE_BOOLEAN:
				// System.out.println(cell.getBooleanCellValue() + "");
				// break;
				// case Cell.CELL_TYPE_FORMULA:
				// System.out.println(cell.getCellFormula());
				// break;
				// default:
				// }
				// }
			}

		}
		inp.close();
	}

	@Autowired
	private SmsDAO smsDAO;

	public List<Person> findPersonAll() {
		return smsDAO.findPersonAll();
	}

	public void savePerson(Person person) {
		smsDAO.savePerson(person);
	}

	public Person findPersonById(Long id) {
		return smsDAO.findPersonById(id);
	}

	public void deletePerson(Long id) {
		Person person = smsDAO.findPersonById(id);
		smsDAO.deletePerson(person);
	}

	public void process(String fname) throws IOException {
		List<Person> listPerson = new ArrayList<Person>();

		InputStream inp = new FileInputStream(fname);
		XSSFWorkbook wb = new XSSFWorkbook(inp); // Declare XSSF WorkBook
		XSSFSheet sheet = wb.getSheetAt(0); // sheet can be used as common
											// for
		// XSSF and HSSF
		Iterator<org.apache.poi.ss.usermodel.Row> rows = sheet.rowIterator();
		while (rows.hasNext()) {
			org.apache.poi.ss.usermodel.Row row = rows.next();
			if (row.getRowNum() != 0) {
				// Iterator cells = row.cellIterator();
				Person person = new Person();

				person.setFirstName(row.getCell(0).toString());
				person.setLastNameF(row.getCell(1).toString());
				person.setLastNameM(row.getCell(2).toString());

				try {
					if (row.getCell(3) == null) {
						throw new IOException("Favor de ingresar numero de ceclular");
					}
					Long cell3 = Long.valueOf(row.getCell(3).toString());
					if (cell3.toString().length() == 9) {
						System.out.println("Numerico " + cell3);
						person.setNumPhone(cell3.toString());
					} else {
						System.err.println("ERROR en TAMAÑO " + cell3);
						throw new IOException("El numero de Celular debe de ser de nueve digitos: " + cell3);
					}
				} catch (NumberFormatException e) {
					// TODO: handle exception
					System.err.println("No Integer " + row.getCell(3));
					throw new IOException("El numero de Celular debe ser numero: " + row.getCell(3));
				}
				System.out.println(row.getCell(4));
				person.setMessage(row.getCell(4).toString());

				listPerson.add(person);
			}
		}
		inp.close();

		smsDAO.deleteAllPerson();
		smsDAO.saveObjectAllPerson(listPerson);
	}

	// public static void main(String[] args) {
	// SmsServiceImpl s = new SmsServiceImpl();
	// try {
	// s.process("D:\\\\Presentacion.xlsx");
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

}
