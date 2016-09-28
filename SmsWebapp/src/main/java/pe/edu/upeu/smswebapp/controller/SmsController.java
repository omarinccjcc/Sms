package pe.edu.upeu.smswebapp.controller;

import java.io.IOException;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upeu.smscore.domain.AccessApp;
import pe.edu.upeu.smscore.domain.Campaign;
import pe.edu.upeu.smscore.domain.Sms;
import pe.edu.upeu.smscore.domain.UserSystem;
import pe.edu.upeu.smscore.service.services.SmsServiceImpl;
import pe.edu.upeu.smscore.util.CommonUtils;
import pe.edu.upeu.smscore.util.SmsObject;
import pe.edu.upeu.smscore.util.StatusCampaign;
import pe.edu.upeu.smscore.util.StatusSms;
import pe.edu.upeu.smswebapp.security.SessionDetail;
import pe.edu.upeu.smswebapp.util.UploadItem;
import pe.edu.upeu.smswebapp.util.Util;

@Controller
public class SmsController {

	protected Log logger = LogFactory.getLog(getClass());

	@Autowired
	SmsServiceImpl smsService;

	// uploadFile
	@RequestMapping(value = "/home/uploadFile", method = RequestMethod.POST)
	public String create(HttpServletRequest request, Model model, UploadItem uploadItem, BindingResult result,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				logger.info("Error: " + error.getCode() + " - " + error.getDefaultMessage());
			}
			return "home/sms?id=" + SessionDetail.getUserDetails().getCampaignId();
		}
		// Some type of file processing...
		Util util = new Util();
		String fname = null;

		try {
			fname = util.storage(request, uploadItem.getFileData());
		} catch (IOException e) {
			// model.addAttribute("listSms", smsService.findSmsAll());
			model.addAttribute("message", e.getMessage());
			model.addAttribute("uploadItem", new UploadItem());
			e.printStackTrace();
			logger.info("Error: " + e.getMessage());
			return "home/sms?id=" + SessionDetail.getUserDetails().getCampaignId();
		}
		// System.out.println("fname " + fname);
		if (fname != null) {
			try {

				smsService.processLoadFile(fname, SessionDetail.getUserDetails().getDepartamentId(),
						SessionDetail.getUserDetails().getCampaignId());
			} catch (IOException e) {
				logger.info("Error al momento de cargar archivo. " + e.getMessage());
				// e.printStackTrace();
			}
		}
		return "redirect:sms?id=" + SessionDetail.getUserDetails().getCampaignId();
	}

	@RequestMapping(value = "home/welcome", method = RequestMethod.GET)
	public String welcome(Model model, HttpServletRequest request) {
		return "home/welcome";
	}

	/**
	 * 
	 * @param model
	 * @param request
	 * @param id
	 * @param status
	 * @return
	 */
	@RequestMapping(value = "home/sms", method = RequestMethod.GET)
	public String sms(Model model, HttpServletRequest request) {
		String id = request.getParameter("id") == null ? "0" : request.getParameter("id");
		String status = request.getParameter("status") == null ? "status" : request.getParameter("status");

		logger.info("home/sms id: " + id + " status:" + status);

		if (!id.equals("0")) {
			model.addAttribute("listSms", smsService.findSmsAll(new Long(id),
					SessionDetail.getUserDetails().getDepartamentId(), SessionDetail.getUserDetails().getStatus(),0));

			SessionDetail.getUserDetails().setCampaingMessage(smsService.findCampaignById(new Long(id)).getMessage());
			SessionDetail.getUserDetails().setCampaignId(new Long(id));
			if (!status.equals("status")) {
				SessionDetail.getUserDetails().setStatus("Espera");
			}

		} else if (!status.equals("status")) {

			model.addAttribute("listSms", smsService.findSmsAll(SessionDetail.getUserDetails().getCampaignId(),
					SessionDetail.getUserDetails().getDepartamentId(), status,0));
			SessionDetail.getUserDetails().setStatus(status);
		} else {
			model.addAttribute("listSms", smsService.findSmsAll(SessionDetail.getUserDetails().getCampaignId(),
					SessionDetail.getUserDetails().getDepartamentId(), SessionDetail.getUserDetails().getStatus(),0));
		}

		model.addAttribute("uploadItem", new UploadItem());
		return "home/sms";
	}

	@RequestMapping(value = "home/editSms", method = RequestMethod.GET)
	public String editSms(Model model, HttpServletRequest request, @RequestParam("id") Long id) {
		model.addAttribute("sms", smsService.findSmsById(id));
		return "home/frmSms";
	}

	@RequestMapping(value = "home/frmSms", method = RequestMethod.GET)
	public String newSms(Model model, HttpServletRequest request) {
		model.addAttribute("sms", new Sms());
		return "home/frmSms";
	}

	@RequestMapping(value = "home/saveSms", method = RequestMethod.POST)
	public String saveSms(Model model, HttpServletRequest request, @ModelAttribute("sms") Sms sms) {
		sms.setCampaignId(SessionDetail.getUserDetails().getCampaignId());
		sms.setDepartamentId(SessionDetail.getUserDetails().getDepartamentId());

		// if (sms.getId() == null) {
		// sms.setStatusSms(StatusSms.STATUS_SMS_ESPERA.getStatusCode());
		// }

		smsService.saveSms(sms);
		return "redirect:sms?id=" + SessionDetail.getUserDetails().getCampaignId();
	}

	@RequestMapping(value = "home/deleteSms", method = RequestMethod.GET)
	public String deleteSms(Model model, HttpServletRequest request, @RequestParam("id") Long id) {
		Sms sms = smsService.findSmsById(id);
		smsService.deleteSms(sms);
		return "redirect:sms?id=" + SessionDetail.getUserDetails().getCampaignId();
	}

	/**
	 * for comboBox
	 * 
	 * @return
	 */
	@ModelAttribute("listStatusCampaign")
	public List<StatusCampaign> statusCampaign() {
		List<StatusCampaign> list = new ArrayList<StatusCampaign>();

		StatusCampaign statusUser = null;
		statusUser = StatusCampaign.STATUS_CAMPAIGN_ACT;
		list.add(statusUser);

		statusUser = StatusCampaign.STATUS_CAMPAIGN_INACT;
		list.add(statusUser);

		return list;
	}

	@ModelAttribute("listStatusSms")
	public List<StatusSms> statusSms() {
		List<StatusSms> list = new ArrayList<StatusSms>();

		StatusSms statusSms = null;
		statusSms = StatusSms.STATUS_SMS_ESPERA;
		list.add(statusSms);

		statusSms = StatusSms.STATUS_SMS_ACTIVO;
		list.add(statusSms);

		statusSms = StatusSms.STATUS_SMS_PROCESADO;
		list.add(statusSms);

		statusSms = StatusSms.STATUS_SMS_CANCELADO;
		list.add(statusSms);
		return list;
	}

	/**
	 * maintain departament
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "home/campaign", method = RequestMethod.GET)
	public String campaign(Model model, HttpServletRequest request) {
		model.addAttribute("listCampaign",
				smsService.findCampaignByDepartamentId(SessionDetail.getUserDetails().getDepartamentId()));
		return "home/campaign";
	}

	@RequestMapping(value = "home/frmCampaign", method = RequestMethod.GET)
	public String frmCampaign(Model model, HttpServletRequest request) {
		model.addAttribute("campaign", new Campaign());
		return "home/frmCampaign";
	}

	@RequestMapping(value = "home/editCampaign", method = RequestMethod.GET)
	public String editCampaign(Model model, HttpServletRequest request, @RequestParam("id") Long id) {
		Campaign campaign = smsService.findCampaignById(id);
		model.addAttribute("campaign", campaign);
		return "home/frmCampaign";
	}

	@RequestMapping(value = "home/saveCampaign", method = RequestMethod.POST)
	public String saveCampaign(Model model, HttpServletRequest rq, @ModelAttribute("campaign") Campaign campaign) {
		campaign.setDepartamentId(SessionDetail.getUserDetails().getDepartamentId());
		smsService.saveCampaign(campaign);
		return "redirect:campaign";
	}

	@RequestMapping(value = "home/deleteCampaign", method = RequestMethod.GET)
	public String deleteCampaign(Model model, HttpServletRequest request, @RequestParam("id") Long id) {
		smsService.deleteCampaign(id);
		return "redirect:campaign";
	}

	@RequestMapping(value = "home/updateCampaign", method = RequestMethod.POST)
	public String updateCampaign(Model model, HttpServletRequest rq,
			@RequestParam("campaingMessage") String campaingMessage) {
		Campaign campaign = smsService.findCampaignById(SessionDetail.getUserDetails().getCampaignId());
		campaign.setMessage(campaingMessage);
		smsService.saveCampaign(campaign);
		SessionDetail.getUserDetails().setCampaingMessage(campaingMessage);
		return "redirect:sms";
	}

	@RequestMapping(value = "home/processMessage", method = RequestMethod.GET)
	public String processMessage(Model model, HttpServletRequest request) {
		smsService.processMessage(SessionDetail.getUserDetails().getCampaignId(),
				SessionDetail.getUserDetails().getDepartamentId(), SessionDetail.getUserDetails().getCampaingMessage());
		return "redirect:sms";
	}

	@RequestMapping(value = "home/processActiceSms", method = RequestMethod.GET)
	public String processActiceSms(Model model, HttpServletRequest request) {
		smsService.processChangeStatusSms(SessionDetail.getUserDetails().getCampaignId(),
				SessionDetail.getUserDetails().getDepartamentId(), StatusSms.STATUS_SMS_ESPERA.getStatusCode(),
				StatusSms.STATUS_SMS_ACTIVO.getStatusCode());
		return "redirect:sms";
	}

	@RequestMapping(value = "home/processDesacticeSms", method = RequestMethod.GET)
	public String processDesacticeSms(Model model, HttpServletRequest request, @RequestParam("status") String status) {
		smsService.processChangeStatusSms(SessionDetail.getUserDetails().getCampaignId(),
				SessionDetail.getUserDetails().getDepartamentId(), status, StatusSms.STATUS_SMS_ESPERA.getStatusCode());
		return "redirect:sms";
	}

	@RequestMapping(value = "home/processDeleteSms", method = RequestMethod.GET)
	public String processDeleteSms(Model model, HttpServletRequest request) {
		smsService.processDeleteSms(SessionDetail.getUserDetails().getCampaignId(),
				SessionDetail.getUserDetails().getStatus());
		return "redirect:sms";
	}

	/**
	 * edit information
	 */
	@RequestMapping(value = "home/editUserSystem", method = RequestMethod.GET)
	public String editUserSystem(Model model, HttpServletRequest request) {
		UserSystem userSystem = smsService.findUserSystemById(SessionDetail.getUserDetails().getUserId());
		userSystem.setPassword("");
		model.addAttribute("userSystem", userSystem);
		return "home/editUserSystem";
	}

	@RequestMapping(value = "home/saveUserSystem", method = RequestMethod.POST)
	public String saveUserSystem(Model model, HttpServletRequest request,
			@ModelAttribute("campaign") UserSystem userSystemToChange) {
		UserSystem userSystem = smsService.findUserSystemById(SessionDetail.getUserDetails().getUserId());
		userSystem.setFirstName(userSystemToChange.getFirstName());
		userSystem.setLastNameF(userSystemToChange.getLastNameF());
		userSystem.setLastNameM(userSystemToChange.getLastNameM());
		if (userSystemToChange.getPassword() != null && !userSystemToChange.getPassword().trim().equals("")) {
			userSystem.setPassword(CommonUtils.encrypt(userSystemToChange.getPassword()));
		}

		smsService.saveUserSystem(userSystem);
		model.addAttribute("userSystem", userSystemToChange);
		return "redirect:editUserSystem";
	}

}
