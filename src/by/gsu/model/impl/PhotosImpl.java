package by.gsu.model.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import by.gsu.ifaces.IPhotosDAO;
import by.gsu.listeners.ConstantsContextListener;

public class PhotosImpl implements IPhotosDAO {

	@Override
	public List<String> getListNamesPhotos(String photosFolderPath)
			throws DaoException {
		File folder = new File(photosFolderPath);

		File[] listOfFiles = folder.listFiles();
		List<String> listNamesPhotos = new ArrayList<String>();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				listNamesPhotos.add(listOfFiles[i].getName());
			} else if (listOfFiles[i].isDirectory()) {
			}
		}
		return listNamesPhotos;
	}
}
