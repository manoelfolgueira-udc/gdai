package es.udc.fic.manoelfolgueira.gdai.web.rest.controllers;

import java.util.Calendar;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import es.udc.fic.manoelfolgueira.gdai.model.entities.user.User;
import es.udc.fic.manoelfolgueira.gdai.model.services.gdaicaseservice.GDAICaseService;
import es.udc.fic.manoelfolgueira.gdai.model.services.systemservice.SystemService;
import es.udc.fic.manoelfolgueira.gdai.model.services.userservice.UserService;
import es.udc.fic.manoelfolgueira.gdai.model.util.EmailUtil;
import es.udc.fic.manoelfolgueira.gdai.model.util.dtos.GDAICaseDetails;
import es.udc.fic.manoelfolgueira.gdai.model.util.dtos.GroupDetails;
import es.udc.fic.manoelfolgueira.gdai.model.util.dtos.SystemDetails;
import es.udc.fic.manoelfolgueira.gdai.model.util.dtos.UserDetails;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.DuplicateInstanceException;
import es.udc.fic.manoelfolgueira.gdai.web.rest.controllers.dto.GDAICaseDTO;

@Controller
@Path("/gdaicase")
public class GDAICaseController {
	
	@Autowired
	private GDAICaseService gdaiCaseService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SystemService systemService;
	
	@Context
    SecurityContext securityContext;
	
	private final String gdaiCaseEmailTemplate = "<h1><span style=\"color: #777777;\">GDAI - Troubleshooting</span></h1>\n<h2><span style=\"color: #ff0000;\">Hubo un problema grave en un sistema</span></h2>\n<p>El automatismo GDAI API ha creado un nuevo caso con identificador ##id## asociado al sistema ##systemName## agregando la siguiente informaci&oacute;n del problema:</p>\n<table style=\"border-collapse: collapse; width: 100%;\" border=\"1\">\n<tbody>\n<tr>\n<td style=\"width: 100%;\">##trouble_body##</td>\n</tr>\n</tbody>\n</table>\n<p>Por favor, no responda a este correo electr&oacute;nico. Ha sido enviado por un sistema autom&aacute;tico.</p>";
	
	@POST
	@Path("/create")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response createGDAICase(GDAICaseDTO gdaiCaseDTO) {
		try {
			
			UserDetails userDetails = userService.findUser(Long.valueOf(gdaiCaseDTO.getCreatedBy()));
			SystemDetails systemDetails = systemService.findSystem(Long.valueOf(gdaiCaseDTO.getSystemId()));
						
			GDAICaseDetails gdaiCaseDetails = gdaiCaseService.createGDAICase(new GDAICaseDetails(null, gdaiCaseDTO.getGdaiCaseDescription(), "",
					Calendar.getInstance(),	userDetails, systemDetails));
			
			UserDetails userDetailsEmail = null;
			
			GroupDetails groupDetailsEmail = systemDetails.getGroup();
			
			for(User user: groupDetailsEmail.getUsers()) {
				if (user.getIsManager()) {
					userDetailsEmail = new UserDetails(user);
				}
			}
			
			if (userDetailsEmail == null) {
				userDetailsEmail = new UserDetails(groupDetailsEmail.getUsers().get(0));
				for(User user: groupDetailsEmail.getUsers()) {
					if (user.getHireDate().before(userDetailsEmail.getHireDate())) {
						userDetailsEmail = new UserDetails(user);
					}
				}
			}
						
			String toEmail = userDetailsEmail.getEmail();
			
			String subject = "Incidencia grave en " + systemDetails.getSystemName() + ".";
			String body = gdaiCaseEmailTemplate
					.replace("##id##", String.valueOf(gdaiCaseDetails.getGDAICaseId()))
					.replace("##systemName##", systemDetails.getSystemName())
					.replace("##trouble_body##", gdaiCaseDTO.getGdaiCaseDescription());
			
			EmailUtil.send(toEmail, subject, body);
			
		    return Response
		      .status(Response.Status.OK)
		      .entity(new ResponseJSON("0", gdaiCaseDetails.getGDAICode()))
		      .build();
		} catch (DuplicateInstanceException d) {
			return Response
				      .status(Response.Status.BAD_REQUEST)
				      .entity(new ResponseJSON("-1", "BAD_REQUEST"))
				      .build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response
				      .status(Response.Status.SERVICE_UNAVAILABLE)
				      .entity(new ResponseJSON("-1", "SERVICE_UNAVAILABLE"))
				      .build();
		}
			
	}
	
}
