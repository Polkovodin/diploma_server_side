package by.gsu.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.gsu.ifaces.IPhotosDAO;
import by.gsu.listeners.ConstantsContextListener;
import by.gsu.model.factories.PhotosFactory;
import by.gsu.model.impl.DaoException;

/**
 * Servlet implementation class MainController
 */
public class MainController extends AbstractController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void performTask(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		HttpSession session = req.getSession();
		String photosFolderPath = (String) req.getServletContext()
				.getAttribute(ConstantsContextListener.KEY_PHOTOS_FOLDER_PATH);
		List<String> listNamesPhotos = new ArrayList<String>();
		IPhotosDAO photosDAO = PhotosFactory.getClassFromFactory();

		try {
			listNamesPhotos = photosDAO.getListNamesPhotos(photosFolderPath);
			session.setAttribute(Constants.KEY_LIST_NAMES_PHOTOS,
					listNamesPhotos);

		} catch (DaoException e) {
			jump(Constants.JUMP_MAIN, e.getMessage(), req, resp);
		}
		jump(Constants.JUMP_MAIN, req, resp);
	}
}
