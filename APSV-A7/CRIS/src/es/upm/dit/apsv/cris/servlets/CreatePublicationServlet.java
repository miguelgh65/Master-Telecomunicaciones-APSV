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

import es.upm.dit.apsv.cris.model.Publication;
import es.upm.dit.apsv.cris.model.Researcher;

@WebServlet("/CreatePublicationServlet")
public class CreatePublicationServlet extends HttpServlet {
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
			Publication p = new Publication();
			p.setId(request.getParameter("id"));
			p.setTitle(request.getParameter("title"));
			p.setPublicationName(request.getParameter("publicationName"));
			p.setPublicationDate(request.getParameter("publicationDate"));
			p.setAuthors(request.getParameter("authors"));
			
			Publication pi = null;
			try {
			pi = client.target("http://localhost:8080/CRISSERVICE/rest/Publications/" + request.getParameter("id"))
					.request().accept(MediaType.APPLICATION_JSON).get(Publication.class);		
			request.setAttribute ("pi", pi);
			
			}catch(Exception e) {}
			
			if (null == pi) {
				Response r = client.target("http://localhost:8080/CRISSERVICE/rest/Publications/")
						.request().post(Entity.entity(p, MediaType.APPLICATION_JSON), Response.class);
				

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
