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

import es.udc.fic.manoelfolgueira.gdai.model.productionpassservice.ProductionPassDetails;
import es.udc.fic.manoelfolgueira.gdai.model.productionpassservice.ProductionPassService;
import es.udc.fic.manoelfolgueira.gdai.model.systemservice.SystemDetails;
import es.udc.fic.manoelfolgueira.gdai.model.systemservice.SystemService;
import es.udc.fic.manoelfolgueira.gdai.model.userservice.UserDetails;
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
	private SystemDetails systemDetails;

	@Property
	private SelectModel systemsModel;

	@Inject
	private SelectModelFactory selectModelFactory;

	private ProductionPassDetails productionPassDetails;

	@Inject
	private PageRenderLinkSource pageRenderLS;

	void onActivate(Long productionPassId) {
		this.productionPassId = productionPassId;
	}

	void setupRender() {
		try {

			productionPassDetails = productionPassService.findProductionPass(productionPassId);

			productionPassName = productionPassDetails.getProductionPassName();
			productionPassResolution = productionPassDetails.getProductionPassResolution();
			systemDetails = productionPassDetails.getSystem();

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
			UserDetails createdBy = userService.findUser(userSession.getUserId());
			productionPassDetails = productionPassService.findProductionPass(productionPassId);

			productionPassService.updateProductionPassDetails(productionPassId,
					new ProductionPassDetails(null, productionPassName, productionPassResolution,
							productionPassDetails.getCreationDate(), requirementsPath, createdBy, systemDetails));
			result = messages.getFormatter("result-ProductionPassRegister-ok").format(productionPassName);
		} catch (Exception e) {
			e.printStackTrace();
			registrationForm.recordError(messages.get("error-unexpectedError"));
		}

	}

	Object onSuccess() {

		if (productionPassReqs != null) {

			try {
				productionPassDetails = productionPassService.findProductionPass(productionPassId);
			} catch (InstanceNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			File copied = new File(
					Config.getInstance().getProperties().getProperty(ConfigPropertyKeys.FOLDER_PROJECT_REQUIREMENTS),
					productionPassDetails.getGDAICode() + "_" + productionPassReqs.getFileName());
			productionPassReqs.write(copied);

		}

		return pageRenderLS.createPageRenderLinkWithContext("tools/productionPass/productionPassmodified",
				productionPassId);
	}

	public SystemEncoder getSystemEncoder() {
		return new SystemEncoder(systemService);
	}

	void onPrepare() {

		List<SystemDetails> systemsDetails = systemService.findAllOrderedBySystemName();

		if (systemId != null) {
			systemDetails = findSystemInList(systemId, systemsDetails);
		}

		systemsModel = selectModelFactory.create(systemsDetails, "systemName");
	}

	private SystemDetails findSystemInList(Long systemId, List<SystemDetails> systemsDetails) {
		for (SystemDetails s : systemsDetails) {
			if (s.getSystemId().equals(systemId)) {
				return s;
			}
		}
		return null;
	}

}
