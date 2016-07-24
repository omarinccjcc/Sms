package pe.edu.upeu.smswebapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pe.edu.upeu.smswebapp.util.UploadItem;

@Controller
public class UploadController {

//	@RequestMapping(method = RequestMethod.POST)
	@RequestMapping(value = "upload/person", method = RequestMethod.POST)

	public String create(UploadItem uploadItem, BindingResult result) {
		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				System.err.println("Error: " + error.getCode() + " - " + error.getDefaultMessage());
			}
			return "upload/uploadForm";
		}

		// Some type of file processing...
		System.err.println("-------------------------------------------");
		System.err.println("Test upload: " + uploadItem.getName());
		System.err.println("Test upload: " + uploadItem.getFileData().getOriginalFilename());
		System.err.println("-------------------------------------------");

		return "redirect:/app/";
	}
}
