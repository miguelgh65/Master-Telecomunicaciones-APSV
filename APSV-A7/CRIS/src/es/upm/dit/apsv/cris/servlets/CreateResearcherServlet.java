package es.upm.dit.apsv.cris.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;

import es.upm.dit.apsv.cris.model.Researcher;


@WebServlet("/CreateResearcherServlet")
public class CreateResearcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		getServletContext().getRequestDispatcher("/LoginView.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Client client = ClientBuilder.newClient(new ClientConfig());

		Researcher user = (Researcher) (request.getSession().getAttribute("user"));
		if ((user != null) && (user.getId().equals("root"))) {
			Researcher n = new Researcher();
			n.setId(request.getParameter("id"));
			n.setName(request.getParameter("name"));
			n.setLastname(request.getParameter("lastname"));
			n.setEmail(request.getParameter("email"));
			//n.setPassword(request.getParameter("password"));
			
			Researcher ri = null;
			try {
			ri = client.target("http://localhost:8080/CRISSERVICE/rest/Researchers/" + request.getParameter("id"))
					.request().accept(MediaType.APPLICATION_JSON).get(Researcher.class);		
			request.setAttribute ("ri", ri);
			
			}catch(Exception e) {}
			
			if (null == ri) {
				Response r = client.target("http://localhost:8080/CRISSERVICE/rest/Researchers/")
						.request().post(Entity.entity(n, MediaType.APPLICATION_JSON), Response.class);
				

				//System.out.println(r.readEntity(String.class));
				//ResearcherDAOImplementation.getInstance().create(n);
			
			}
			response.sendRedirect(request.getContextPath() + "/AdminServlet");
			
		}
		else {
			request.setAttribute("message", "Invalid user or password");
			getServletContext().getRequestDispatcher("/LoginView.jsp").forward(request, response);
		}
	}

}
