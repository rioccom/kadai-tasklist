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


@WebServlet(name = "update", urlPatterns = { "/update" })
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String _token = (String)request.getParameter("_token");
	
		if(_token != null && _token.equals(request.getSession().getId())) {
			EntityManager em = DBUtil.createEntityManager();
			
			//    セッションスコープからメッセージのIDを取得して
			//____該当のIDのメッセージ1件のみをデータベースから取得__________________
			Task m = em.find(Task.class, (Integer)(request.getSession().getAttribute("task_id")));
		
			//____フォームの内容を各プロパティに上書き__________________________________
			String content = request.getParameter("content");
			m.setContent(content);
			
			Timestamp currentTime = new Timestamp(System.currentTimeMillis());
			m.setUpdated_at(currentTime);       // 更新日時のみ上書き
		
			//____データベースを更新________________________________________________________
			em.getTransaction().begin();
			em.getTransaction().commit();
			em.close();
		
			//____セッションスコープ上の不要になったデータを削除_______________________
			request.getSession().removeAttribute("task_id");
		
			//____indexページへリダイレクト________________________________________________
			response.sendRedirect(request.getContextPath() + "/index");
		}
		
	}
	
}

