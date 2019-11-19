package controllers;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import utils.DBUtil;

@WebServlet(name = "index", urlPatterns = { "/index" })
public class IndexServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public IndexServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManager em = DBUtil.createEntityManager();                            //EntityManagerの生成
		
		List<Task> tasks = em.createNamedQuery("getAllTasks", Task.class) //getAllTasksの実行結果をリスト名tasksに代入
			.getResultList();                                                                  //（各データは自動的にTaskクラスのオブジェクトになっている）
		response.getWriter().append(Integer.valueOf(tasks.size()).toString());
		
		em.close();
	}
	
}
