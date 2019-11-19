package controllers;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import utils.DBUtil;

@WebServlet(name = "create", urlPatterns = { "/create" })
public class CreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	public CreateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String _token = (String)request.getParameter("_token");
		
		if(_token != null && _token.equals(request.getSession().getId())) {   //CSRF対策
			
			EntityManager em = DBUtil.createEntityManager();
			
			Task m = new Task();
			
			String content = request.getParameter("content");
			m.setContent(content);
			
			Timestamp currentTime = new Timestamp(System.currentTimeMillis());
			m.setCreated_at(currentTime);
			m.setUpdated_at(currentTime);
			
			em.getTransaction().begin(); 
			em.persist(m);
			em.getTransaction().commit();   //DBに保存
			em.close();
			
			response.sendRedirect(request.getContextPath() + "/index");   //indexにリダイレクト
		}
		
	}
	
}
