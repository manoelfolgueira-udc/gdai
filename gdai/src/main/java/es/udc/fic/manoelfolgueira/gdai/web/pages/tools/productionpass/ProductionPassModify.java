package es.udc.fic.manoelfolgueira.gdai.web.pages.tools.productionpass;

import java.io.File;
import java.util.List;
import java.util.Locale;

import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.apache.tapestry5.services.SelectModelFactory;
import org.apache.tapestry5.upload.services.UploadedFile;

import es.udc.fic.manoelfolgueira.gdai.model.productionpass.ProductionPass;
import es.udc.fic.manoelfolgueira.gdai.model.productionpassservice.ProductionPassDetails;
import es.udc.fic.manoelfolgueira.gdai.model.productionpassservice.ProductionPassService;
import es.udc.fic.manoelfolgueira.gdai.model.system.System;
import es.udc.fic.manoelfolgueira.gdai.model.systemservice.SystemService;
import es.udc.fic.manoelfolgueira.gdai.model.user.User;
import es.udc.fic.manoelfolgueira.gdai.model.userservice.UserService;
import es.udc.fic.manoelfolgueira.gdai.model.util.Config;
import es.udc.fic.manoelfolgueira.gdai.model.util.ConfigPropertyKeys;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;
import es.udc.fic.manoelfolgueira.gdai.web.encoders.SystemEncoder;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;

/**
 * Web page that allows an Administrator modify a productionPass
 * 
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file ProductionPassModify.java
 */
@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class ProductionPassModify {

	private Long systemId;

	@Property
	private Long productionPassId;

	@Property
	private String productionPassName;

	@Component(id = "productionPassName")
	private TextField productionPassNameField;

	@Property
	private String productionPassResolution;

	@Component(id = "productionPassResolution")
	private TextField productionPassResolutionField;

	@Property
	private String requirementsPath;

	@SessionState(create = false)
	private UserSession userSession;

	@Property
	private UploadedFile productionPassReqs;

	@Inject
	private ProductionPassService productionPassService;

	@Component
	private Form registrationForm;

	@Inject
	private Messages messages;

	@Inject
	private Locale locale;

	@Inject
	private SystemService systemService;

	@Inject
	private UserService userService;

	@Property
	private String result = null;
	
	@Property
	private System system;
	
	@Property
	private SelectModel systemsModel;

	@Inject
	private SelectModelFactory selectModelFactory;

	private ProductionPass productionPass;

	@Inject
	private PageRenderLinkSource pageRenderLS;

	void onActivate(Long productionPassId) {
		this.productionPassId = productionPassId;
	}


	void setupRender() {
		try {

			productionPass = productionPassService.findProductionPass(productionPassId);

			productionPassName = productionPass.getProductionPassName();
			productionPassResolution = productionPass.getProductionPassResolution();
			system = productionPass.getSystem();
			
		} catch (InstanceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	void onValidateFromRegistrationForm() {

		if (!registrationForm.isValid()) {
			return;
		}

		try {
			User createdBy = userService.findUser(userSession.getUserId());
			productionPass = productionPassService.findProductionPass(productionPassId);

			productionPassService.updateProductionPassDetails(productionPassId, new ProductionPassDetails(productionPassName, productionPassResolution,
					productionPass.getCreationDate(), requirementsPath, createdBy, system));
			result = messages.getFormatter("result-ProductionPassRegister-ok").format(productionPassName);
		} catch (Exception e) {
			e.printStackTrace();
			registrationForm.recordError(messages.get("error-unexpectedError"));
		}

	}

	Object onSuccess() {

		if (productionPassReqs != null) {

			try {
				productionPass = productionPassService.findProductionPass(productionPassId);
			} catch (InstanceNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			File copied = new File(
					Config.getInstance().getProperties().getProperty(ConfigPropertyKeys.FOLDER_PROJECT_REQUIREMENTS),
					productionPass.getGDAICode() + "_" + productionPassReqs.getFileName());
			productionPassReqs.write(copied);

		}

		return pageRenderLS.createPageRenderLinkWithContext("tools/productionPass/productionPassmodified", productionPassId);
	}

	public SystemEncoder getSystemEncoder() {
		return new SystemEncoder(systemService);
	}

	void onPrepare() {

		List<System> systems = systemService.findAllOrderedBySystemName();

		if (systemId != null) {
			system = findSystemInList(systemId, systems);
		}

		systemsModel = selectModelFactory.create(systems, "systemName");
	}


	private System findSystemInList(Long systemId, List<System> systems) {
		for (System s : systems) {
			if (s.getSystemId().equals(systemId)) {
				return s;
			}
		}
		return null;
	}

}
