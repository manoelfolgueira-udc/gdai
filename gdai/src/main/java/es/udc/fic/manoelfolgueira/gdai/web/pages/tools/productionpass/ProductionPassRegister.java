package es.udc.fic.manoelfolgueira.gdai.web.pages.tools.productionpass;

import java.io.File;
import java.util.Calendar;
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
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.DuplicateInstanceException;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;
import es.udc.fic.manoelfolgueira.gdai.web.encoders.SystemEncoder;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;

/**
 * Web page that lets Administrator add new ProductionPasss
 * 
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file ProductionPassRegister.java
 */
@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class ProductionPassRegister {

	private Long systemId;

	@Property
	private String productionPassName;

	@Component(id = "productionPassName")
	private TextField productionPassNameField;

	@Property
	private String productionPassResolution;

	@Component(id = "productionPassResolution")
	private TextField productionPassResolutionField;

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

	void onValidateFromRegistrationForm() {

		if (!registrationForm.isValid()) {
			return;
		}

		if (!productionPassReqs.getFileName().endsWith(".pdf")) {
			registrationForm.recordError(messages.get("onlyPDF"));
		}

	}

	Object onSuccess() {

		Calendar calCreationDate = Calendar.getInstance();
		UserDetails createdBy;
		try {
			createdBy = userService.findUser(userSession.getUserId());
			productionPassDetails = productionPassService.createProductionPass(new ProductionPassDetails(null, productionPassName,
					productionPassResolution, calCreationDate, null, createdBy, systemDetails));
		} catch (DuplicateInstanceException e) {
			registrationForm.recordError(messages.get("error-productionPassNameAlreadyExists"));
			e.printStackTrace();
			return null;
		} catch (InstanceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		File copied = new File(
				Config.getInstance().getProperties().getProperty(ConfigPropertyKeys.FOLDER_PROJECT_REQUIREMENTS),
				productionPassDetails.getGDAICode() + "_" + productionPassReqs.getFileName());
		productionPassReqs.write(copied);

		try {

			String passPath = Config.getInstance().getProperties()
					.getProperty(ConfigPropertyKeys.FOLDER_PROJECT_REQUIREMENTS) + "/" + productionPassDetails.getGDAICode()
					+ "_" + productionPassReqs.getFileName();

			productionPassService.updateProductionPassDetails(productionPassDetails.getProductionPassId(),
					new ProductionPassDetails(null, productionPassDetails.getProductionPassName(),
							productionPassDetails.getProductionPassResolution(), productionPassDetails.getCreationDate(), passPath,
							productionPassDetails.getCreatedBy(), productionPassDetails.getSystem()));
			return pageRenderLS.createPageRenderLinkWithContext("tools/productionPass/productionPasscreated",
					productionPassDetails.getProductionPassId());
		} catch (InstanceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		result = messages.getFormatter("result-ProductionPassRegister-ok").format(productionPassName);

		return ProductionPassManagement.class;
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
