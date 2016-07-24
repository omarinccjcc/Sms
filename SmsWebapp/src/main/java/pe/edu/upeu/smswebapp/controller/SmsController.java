package pe.edu.upeu.smswebapp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upeu.smscore.domain.Person;
import pe.edu.upeu.smscore.service.services.SmsServiceImpl;
import pe.edu.upeu.smswebapp.util.UploadItem;
import pe.edu.upeu.smswebapp.util.Util;

@Controller
public class SmsController {

	protected Log logger = LogFactory.getLog(getClass());

	@Autowired
	SmsServiceImpl smsService;

	@RequestMapping(value = "/sms/allmessage", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Person> allmessage() {
		return smsService.findPersonAll();
	}

	// uploadFile
	@RequestMapping(value = "/home/uploadFile", method = RequestMethod.POST)
	public String create(HttpServletRequest request, Model model, UploadItem uploadItem, BindingResult result,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				System.err.println("Error: " + error.getCode() + " - " + error.getDefaultMessage());
			}
			return "home/person";
		}
		// Some type of file processing...
		Util util = new Util();
		String fname = null;

		
		try {
			fname = util.storageImages(request, uploadItem.getFileData());
		} catch (IOException e) {
			model.addAttribute("listPerson", smsService.findPersonAll());
			model.addAttribute("message", e.getMessage());
			model.addAttribute("uploadItem", new UploadItem());
			e.printStackTrace();
			System.out.println(e.getMessage());
			return "home/person";
		}
		// System.out.println("fname " + fname);
		if (fname != null) {
			try {
				smsService.process(fname);
			} catch (IOException e) {
				System.out.println("Error al momento de cargar archivo. "+e.getMessage());
//				e.printStackTrace();
			}
		}
		return "redirect:person";
	}

	@RequestMapping(value = "home/person", method = RequestMethod.GET)
	public String person(Model model, HttpServletRequest request) {
		model.addAttribute("listPerson", smsService.findPersonAll());
		model.addAttribute("uploadItem", new UploadItem());
		return "home/person";
	}

	@RequestMapping(value = "home/editPerson", method = RequestMethod.GET)
	public String editPerson(Model model, HttpServletRequest request, @RequestParam("id") Long id) {
		model.addAttribute("person", smsService.findPersonById(id));
		return "home/frmPerson";
	}

	@RequestMapping(value = "home/frmPerson", method = RequestMethod.GET)
	public String newPerson(Model model, HttpServletRequest request) {
		model.addAttribute("person", new Person());
		return "home/frmPerson";
	}

	@RequestMapping(value = "home/savePerson", method = RequestMethod.POST)
	public String savePerson(Model model, HttpServletRequest request, @ModelAttribute("person") Person person) {
		smsService.savePerson(person);
		return "redirect:person";
	}

	@RequestMapping(value = "home/deletePerson", method = RequestMethod.GET)
	public String deletePerson(Model model, HttpServletRequest request, @RequestParam("id") Long id) {
		smsService.deletePerson(id);
		return "redirect:person";
	}

	// @RequestMapping(value = "/home/searchJsonById/{id}", method =
	// RequestMethod.GET, headers = "Accept=application/json")
	// public @ResponseBody String searchJsonById(@PathVariable("id") Long id) {
	// return "Return value::: " + id;
	// }
	//
	// @RequestMapping(value = "/home/searchXMLById/{id}", method =
	// RequestMethod.GET, headers = "Accept=application/xml")
	// public @ResponseBody String searchXMLById(@PathVariable("id") Long id) {
	//
	// return "Return value::: " + id;
	// }
}
