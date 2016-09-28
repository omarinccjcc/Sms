package pe.edu.upeu.smswebapp.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import pe.edu.upeu.smscore.util.CommonUtils;

public class Util {

	public String storage(HttpServletRequest request, CommonsMultipartFile file) throws IOException {
		String fileName = null;
		InputStream inputStream = null;
		OutputStream outputStream = null;

		Date date = new Date();
		String path = "/files/" + CommonUtils.dateToString(date, "yyyy_MM_dd_HH_mm_ss");

		if (file.getSize() > 0) {
			inputStream = file.getInputStream();
			fileName = request.getRealPath("") + path + file.getOriginalFilename();
			String extension = FilenameUtils.getExtension(fileName);
			if(extension.equals("xlsx") || extension.equals("xls")){
				outputStream = new FileOutputStream(fileName);
				int readBytes = 0;
				byte[] buffer = new byte[10000];
				while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
					outputStream.write(buffer, 0, readBytes);
				}
				outputStream.close();
				inputStream.close();
			}else{
				throw new IOException("El archivo debe ser con extension: .xlsx o .xls");
			}
		}
		return fileName;
	}
}
