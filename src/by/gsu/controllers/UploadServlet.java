/*
 * Copyright (C) 2014 Francesco Azzola
 *  Surviving with Android (http://www.survivingwithandroid.com)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package by.gsu.controllers;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
@MultipartConfig
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final int IMG_WIDTH = 335;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		upload(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		upload(request, response);
	}

	private void upload(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		try {
			String param1 = request.getParameter("param1");
			System.out.println("Param1 [" + param1 + "]");

			String param2 = request.getParameter("param2");
			System.out.println("Param2 [" + param2 + "]");

			Part p = request.getPart("file");
			String fileName = extractFileName(p);
			System.out.println(fileName);
			// Session session = (Session)
			// request.getSession().getServletContext();
			String realPath = request.getServletContext().getRealPath("photos");
			System.out.println(realPath);

			File f = new File(realPath + "\\" + fileName);

			FileOutputStream fos = new FileOutputStream(f);
			InputStream is = p.getInputStream();
			byte[] buffer = new byte[1024];

			while (is.read(buffer) != -1)
				fos.write(buffer);

			System.out.println("File name [" + fileName + "] - Size ["
					+ f.length() + "]");
			is.close();
			fos.close();

			BufferedImage originalImage = ImageIO.read(f);
			int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB
					: originalImage.getType();

			BufferedImage resizeImageHintJpg = resizeImageWithHint(
					originalImage, type);
			ImageIO.write(resizeImageHintJpg, "jpg", new File(realPath
					+ "\\resize\\" + fileName));
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	private String extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return "";
	}

	private static BufferedImage resizeImageWithHint(
			BufferedImage originalImage, int type) {

		float proportion = (float) IMG_WIDTH / originalImage.getWidth();
		int height = (int) (originalImage.getHeight() * proportion);

		BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, height, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, IMG_WIDTH, height, null);
		g.dispose();
		g.setComposite(AlphaComposite.Src);

		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		return resizedImage;
	}

}
