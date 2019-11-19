package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;

@WebServlet(name = "new", urlPatterns = { "/new" })
public class NewServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	

	public NewServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ____CSRF対策___________________________________________________________
		request.setAttribute("_token", request.getSession().getId());
		
		// ____おまじないとしてのインスタンスを生成______________________________
		request.setAttribute("task", new Task()); //リクエストスコープに(属性,値)を追加
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/tasks/new.jsp"); //new.jspにフォワード
		rd.forward(request, response);
	}
	
}