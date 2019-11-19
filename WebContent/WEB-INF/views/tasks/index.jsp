<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../layout/app.jsp"><%--<c:import> を使うことで、url属性に指定したファイルの内容をその位置で読み込める --%>
	<c:param name="content">
		
		<h2>タスク一覧</h2>

		<ul>
			<c:forEach var="task" items="${tasks}">
				<li>
					<a href="${pageContext.request.contextPath}/show?id=${task.id}">
						<%-- ↑この部分が自動的にコンテキストパスの文字列に置換される --%>
						<c:out value="${task.id}" />
					</a>
					：<c:out value="${task.content}"></c:out>
				</li>
			</c:forEach>
		</ul>

		<p><a href="${pageContext.request.contextPath}/new">新規タスクの投稿</a></p>
		
	</c:param>
</c:import>

