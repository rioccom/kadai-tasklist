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

@WebServlet(name = "new", urlPatterns = { "/new" })
public class NewServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	

	public NewServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManager em = DBUtil.createEntityManager();
		em.getTransaction().begin();
		
		//____ Taskのインスタンスを生成____________________________
		Task m = new Task();
		
		//____mのプロパティにデータを代入__________________________
		String content = "hello";
		m.setContent(content);
		
		//____現在の日時を取得_____________________________________
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());     
		m.setCreated_at(currentTime);
		m.setUpdated_at(currentTime);
		
		//____データベースに保存_____________________________________
		em.persist(m);                      //DBに保存
		em.getTransaction().commit();  //確定
		
		//____自動採番されたIDの値を表示________________________
		response.getWriter().append(Integer.valueOf(m.getId()).toString());  //getWriter...サーブレットの処理結果として文字を出力
		
		em.close();
	}
	
}