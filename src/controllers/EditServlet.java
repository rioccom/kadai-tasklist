package controllers;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import utils.DBUtil;

@WebServlet(name = "edit", urlPatterns = { "/edit" })
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public EditServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManager em = DBUtil.createEntityManager();
	
		//____該当のIDのメッセージ1件のみをデータベースから取得______________________
		Task m = em.find(Task.class, Integer.parseInt(request.getParameter("id")));
		
		em.close();
	
		//____メッセージ情報とセッションIDをリクエストスコープに登録____________________
		request.setAttribute("task", m);
		request.setAttribute("_token", request.getSession().getId());
	
		//____メッセージIDをセッションスコープに登録______________________________________
		request.getSession().setAttribute("task_id", m.getId());
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/tasks/edit.jsp");
		rd.forward(request, response);
	}
	
}