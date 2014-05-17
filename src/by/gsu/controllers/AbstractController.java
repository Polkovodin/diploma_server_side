package by.gsu.controllers;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		performTask(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		performTask(req, resp);
	}

	protected abstract void performTask(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException;

	protected void jump(String url, String message, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute(Constants.KEY_ERROR_MESSAGE, message);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}

	protected void jumpSuccess(String url, String message,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute(Constants.KEY_SUCCESS_MESSAGE, message);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}

	protected void jump(String url, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		jump(url, Constants.KEY_EMPTY, request, response);
	}

	protected String checkParameters(Map<String, String> params) {
		for (Entry<String, String> entry : params.entrySet()) {
			if (entry.getValue() == null) {
				return entry.getKey()
						+ Constants.CHECK_PARAMETERS_NULL_PARAM_ERROR;
			}
			if (Constants.KEY_EMPTY.equals(entry.getValue().trim())) {
				return entry.getKey()
						+ Constants.CHECK_PARAMETERS_EMPTY_PARAM_ERROR;
			}
		}
		return Constants.CHECK_PARAMETERS_NO_ERRORS;
	}
}
