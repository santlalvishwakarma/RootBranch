package com.web.util;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/WeightFetchingServlet")
public class WeightFetchingServlet extends HttpServlet {

	private static final long serialVersionUID = -4246210437348413456L;

	public WeightFetchingServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		try {
			String itemWeightStr = request.getParameter("item_weight");
			String userLogin = request.getParameter("user_login");
			System.out.println("itemWeightStr :  " + itemWeightStr + ", userLogin : " + userLogin);

			Float itemWeight = null;
			try {
				itemWeight = Float.parseFloat(itemWeightStr);
			} catch (Exception e) {
			}

			// HttpSession session = request.getSession(true);
			// System.out.println("itemWeightStr before :  " +
			// session.getAttribute("ITEM_WEIGHT_KEY"));
			// System.out.println(" user login :  " +
			// session.getAttribute("LOGGED_USER_KEY"));
			ServletContext servCont = getServletContext();
			if (servCont != null && itemWeight != null && userLogin != null) {
				// session.setAttribute("ITEM_WEIGHT_KEY", itemWeight);
				// servCont.setInitParameter("ITEM_WEIGHT_KEY",
				// itemWeight.toString());
				servCont.setAttribute("ITEM_WEIGHT_KEY" + userLogin, itemWeight);
			}

			System.out.println("context 23 :  " + getServletContext().getAttribute("ITEM_WEIGHT_KEY"));
			// System.out.println("context 23 init :  " +
			// getServletContext().getInitParameter("ITEM_WEIGHT_KEY"));
			// System.out.println("itemWeightStr 23 :  " +
			// session.getAttribute("ITEM_WEIGHT_KEY"));

		} catch (Exception e) {

		}
	}

}
