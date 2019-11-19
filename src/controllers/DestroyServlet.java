package controllers;


import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import utils.DBUtil;


@WebServlet(name = "destroy", urlPatterns = { "/destroy" })
public class DestroyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	public DestroyServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String _token = (String)request.getParameter("_token");
		
		if(_token != null && _token.equals(request.getSession().getId())) {
			EntityManager em = DBUtil.createEntityManager();
		
			//    セッションスコープからタスクのIDを取得して
			//____該当のIDのタスク1件のみをデータベースから取得_______________
			Task m = em.find(Task.class, (Integer)(request.getSession().getAttribute("task_id")));
		
			em.getTransaction().begin();
			em.remove(m);       // データ削除
			em.getTransaction().commit();
			em.close();
		
			//____セッションスコープ上の不要になったデータを削除____________________
			request.getSession().removeAttribute("task_id");
		
			//____indexページへリダイレクト______________________________________________
			response.sendRedirect(request.getContextPath() + "/index");
		}
		
	}
	
}
