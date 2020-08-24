package es.udc.fic.manoelfolgueira.gdai.web.pages.tools.productionpass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

import org.apache.tapestry5.StreamResponse;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.ActionLink;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.apache.tapestry5.services.Response;

import es.udc.fic.manoelfolgueira.gdai.model.productionpassservice.ProductionPassDetails;
import es.udc.fic.manoelfolgueira.gdai.model.productionpassservice.ProductionPassService;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicy;
import es.udc.fic.manoelfolgueira.gdai.web.services.AuthenticationPolicyType;
import es.udc.fic.manoelfolgueira.gdai.web.util.UserSession;

/**
 * Delete a productionPass web page
 * 
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file ProductionPassDelete.java
 */
@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class ProductionPassView {

	@Inject
	private PageRenderLinkSource pageRenderLS;

	@Inject
	private Messages messages;

	@Inject
	private ProductionPassService productionPassService;

	@Property
	private String infoDeleteProductionPass;

	private Long productionPassId;

	@Property
	private ProductionPassDetails productionPassDetails;

	@Component
	private Form deleteForm;

	@SessionState(create = false)
	private UserSession userSession;

	@Inject
	private Locale locale;

	void setupRender() {
		try {
			productionPassDetails = productionPassService.findProductionPass(productionPassId);
		} catch (InstanceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void onActivate(Long productionPassId) {
		this.productionPassId = productionPassId;
	}

	@Component(id = "downloadLink")
	private ActionLink downloadLink;

	@OnEvent(component = "downloadLink")
	private Object handleDownload() throws InstanceNotFoundException {
		final File file = new File(productionPassService.findProductionPass(productionPassId).getPassPath());

		final StreamResponse response = new StreamResponse() {

			public String getContentType() {
				return "application/pdf";
			}

			public void prepareResponse(Response response) {
				response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
			}

			@Override
			public InputStream getStream() throws IOException {
				try {
					return new FileInputStream(file);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		};
		return response;
	}

	public String getPRFileName() {
		return productionPassDetails.getPassPath().substring(productionPassDetails.getPassPath().lastIndexOf("/") + 1);
	}

}
