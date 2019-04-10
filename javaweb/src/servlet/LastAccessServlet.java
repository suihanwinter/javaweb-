package servlet;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/LastAccessServlet")

public class LastAccessServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		// ��ȡ��ǰʱ��
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String curTime = format.format(new Date());
		// ȡ��Cookie
		Cookie[] cookies = request.getCookies();
		String lastTime = null;
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("lastTime")) {
					// ��lastTime��cookie���Ѿ��ǵ�n�η���
					lastTime = cookie.getValue();// �ϴη���ʱ��
					// ��n�η���
					// 1.���ϴα����ʱ����ʾ�������
					response.getWriter().write("��ӭ���������ϴη��ʵ�ʱ��Ϊ��" + lastTime + ",��ǰʱ��Ϊ��" + curTime);
					// 2.����cookie
					cookie.setValue(curTime);
					cookie.setMaxAge(1 * 30 * 24 * 60 * 60);
					// 3.�Ѹ��º��cookie���͵������
					response.addCookie(cookie);
					break;
				}
			}
		}
		/*
		 * ��һ�η���(û��cookie��������cookie����û����ΪlastTime��cookie)
		 */
		if (cookies == null || lastTime == null) {
			// 1.��ʾ��ǰʱ�䵽�����
			response.getWriter().write("�����״η��ʱ���վ����ǰʱ��Ϊ��" + curTime);
			// 2.����Cookie����
			Cookie cookie = new Cookie("lastTime", curTime);
			cookie.setMaxAge(1 * 30 * 24 * 60 * 60);// ��cookie����һ���¡�
			// 3.��cookie���͵����������
			response.addCookie(cookie);
		}
	}
}

