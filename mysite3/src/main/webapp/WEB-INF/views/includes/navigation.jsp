<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
		<div id="navigation">
			<ul>
				<li><a href="${pageContext.servletContext.contextPath }">윤종진</a></li>
				<li><a href="${pageContext.servletContext.contextPath }/guestbook/list">방명록</a></li>
				<li><a href="${pageContext.servletContext.contextPath }/board/list/${1}">게시판</a></li>
			</ul>
		</div>