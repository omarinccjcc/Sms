package pe.edu.upeu.smswebapp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pe.edu.upeu.smscore.domain.AccessApp;
import pe.edu.upeu.smscore.domain.Departament;
import pe.edu.upeu.smscore.domain.Role;
import pe.edu.upeu.smscore.domain.UserSystem;
import pe.edu.upeu.smscore.domain.UserSystemRole;
import pe.edu.upeu.smscore.service.services.SmsServiceImpl;
import pe.edu.upeu.smscore.util.CommonUtils;
import pe.edu.upeu.smscore.util.StatusAccessApp;
import pe.edu.upeu.smscore.util.StatusDepartament;
import pe.edu.upeu.smscore.util.StatusUser;

@Controller
public class AdminController {

	protected Log logger = LogFactory.getLog(getClass());

	@Autowired
	SmsServiceImpl smsService;

	@RequestMapping(value = "admin/accessApp", method = RequestMethod.GET)
	public String accessApp(Model model, HttpServletRequest request) {
		model.addAttribute("listAccessApp", smsService.findAccessAppAll());
		return "admin/accessApp";
	}

	@RequestMapping(value = "admin/frmAccessApp", method = RequestMethod.GET)
	public String frmAccessApp(Model model, HttpServletRequest request) {
		model.addAttribute("accessApp", new AccessApp());
		return "admin/frmAccessApp";
	}

	@RequestMapping(value = "admin/editAccessApp", method = RequestMethod.GET)
	public String editAccessApp(Model model, HttpServletRequest request, @RequestParam("id") Long id) {
		model.addAttribute("accessApp", smsService.findAccessAppById(id));
		return "admin/frmAccessApp";
	}

	@RequestMapping(value = "admin/saveAccessApp", method = RequestMethod.POST)
	public String saveAccessApp(Model model, HttpServletRequest request,
			@ModelAttribute("accessApp") AccessApp accessApp) {
		smsService.saveAccessApp(accessApp);
		return "redirect:accessApp";
	}

	@RequestMapping(value = "admin/deleteAccessApp", method = RequestMethod.GET)
	public String deleteAccessApp(Model model, HttpServletRequest request, @RequestParam("id") Long id) {
		smsService.deleteAccessApp(id);
		return "redirect:accessApp";
	}

	@ModelAttribute("listStatusAccessApp")
	public List<StatusAccessApp> statusAccessAppList() {
		List<StatusAccessApp> list = new ArrayList<StatusAccessApp>();

		StatusAccessApp statusAccessApp = null;
		statusAccessApp = StatusAccessApp.STATUS_ACCESS_ACT;
		list.add(statusAccessApp);

		statusAccessApp = StatusAccessApp.STATUS_ACCESS_INACT;
		list.add(statusAccessApp);
		return list;
	}

	@ModelAttribute("listStatusUser")
	public List<StatusUser> statusUserList() {
		List<StatusUser> list = new ArrayList<StatusUser>();

		StatusUser statusUser = null;
		statusUser = StatusUser.STATUS_ACCESS_ACT;
		list.add(statusUser);

		statusUser = StatusUser.STATUS_ACCESS_INACT;
		list.add(statusUser);
		return list;
	}

	@ModelAttribute("listStatusDepartament")
	public List<StatusDepartament> statusDepartament() {
		List<StatusDepartament> list = new ArrayList<StatusDepartament>();

		StatusDepartament statusUser = null;
		statusUser = StatusDepartament.STATUS_DEPARTAMENT_ACT;
		list.add(statusUser);

		statusUser = StatusDepartament.STATUS_DEPARTAMENT_INACT;
		list.add(statusUser);
		return list;
	}

	@ModelAttribute("listDepartament")
	public List<Departament> listDepartament() {
		return smsService.findDepartamentByStatus("Activo");
	}

	/**
	 * maintain for userSystem
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "admin/userSystem", method = RequestMethod.GET)
	public String userSystem(Model model, HttpServletRequest request) {
		model.addAttribute("listUserSystem", smsService.findUserSystem());
		return "admin/userSystem";
	}

	@RequestMapping(value = "admin/frmUserSystem", method = RequestMethod.GET)
	public String frmUserSystem(Model model, HttpServletRequest request) {
		List<Role> list = smsService.findRoleByUserId(null);
		model.addAttribute("listRole", list);
		// model.addAttribute("listDepartament",
		// smsService.findDepartamentByStatus("Activo"));
		model.addAttribute("userSystem", new UserSystem());
		model.addAttribute("required", "true");
		
		return "admin/frmUserSystem";
	}

	@RequestMapping(value = "admin/editUserSystem", method = RequestMethod.GET)
	public String editUserSystem(Model model, HttpServletRequest request, @RequestParam("id") Long id) {
		UserSystem userSystem = smsService.findUserSystemById(id);
		userSystem.setPassword("");
		List<Role> list = smsService.findRoleByUserId(id);
		model.addAttribute("listRole", list);
		model.addAttribute("userSystem", userSystem);
		// model.addAttribute("listDepartament",
		// smsService.findDepartamentByStatus("Activo"));
		return "admin/frmUserSystem";
	}

	@RequestMapping(value = "admin/saveUserSystem", method = RequestMethod.POST)
	public String saveUserSystem(Model model, HttpServletRequest rq,
			@ModelAttribute("userSystem") UserSystem userSystem) {

		if ("".equals(userSystem.getPassword().trim())) {
			UserSystem userSystemPass = smsService.findUserSystemById(userSystem.getId());
			userSystem.setPassword(userSystemPass.getPassword());
		} else {
			userSystem.setPassword(CommonUtils.encrypt(userSystem.getPassword()));
		}

		String roleIds[] = rq.getParameterValues("roleId") != null ? rq.getParameterValues("roleId") : new String[0];

		List<UserSystemRole> userSystemRoleLista = new ArrayList<UserSystemRole>();
		for (String roleId : roleIds) {
			UserSystemRole userSystemRole = new UserSystemRole();
			userSystemRole.setRoleId(new Long(roleId));
			userSystemRole.setUserId(userSystem.getId());
			userSystemRoleLista.add(userSystemRole);
		}

		smsService.processSaveUserSystem(userSystem, userSystemRoleLista);
		return "redirect:userSystem";
	}

	@RequestMapping(value = "admin/deleteUserSystem", method = RequestMethod.GET)
	public String deleteUserSystem(Model model, HttpServletRequest request, @RequestParam("id") Long id) {
		smsService.deleteUserSystem(id);
		return "redirect:userSystem";
	}

	/**
	 * maintain departament
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "admin/departament", method = RequestMethod.GET)
	public String departament(Model model, HttpServletRequest request) {
		model.addAttribute("listDepartaments", smsService.findDepartamentByStatus(null));
		return "admin/departament";
	}

	@RequestMapping(value = "admin/frmDepartament", method = RequestMethod.GET)
	public String frmDepartament(Model model, HttpServletRequest request) {
		model.addAttribute("departament", new Departament());
		return "admin/frmDepartament";
	}

	@RequestMapping(value = "admin/editDepartament", method = RequestMethod.GET)
	public String editDepartament(Model model, HttpServletRequest request, @RequestParam("id") Long id) {
		Departament departament = smsService.findDepartamentById(id);
		model.addAttribute("departament", departament);
		return "admin/frmDepartament";
	}

	@RequestMapping(value = "admin/saveDepartament", method = RequestMethod.POST)
	public String saveDepartament(Model model, HttpServletRequest rq,
			@ModelAttribute("departament") Departament departament) {
		smsService.saveDepartament(departament);
		return "redirect:departament";
	}

	@RequestMapping(value = "admin/deleteDepartament", method = RequestMethod.GET)
	public String deleteDepartament(Model model, HttpServletRequest request, @RequestParam("id") Long id) {
		smsService.deleteDepartament(id);
		return "redirect:departament";
	}

}