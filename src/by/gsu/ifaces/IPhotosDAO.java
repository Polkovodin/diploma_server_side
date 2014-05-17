package by.gsu.ifaces;

import java.util.List;

import by.gsu.model.impl.DaoException;

public interface IPhotosDAO {
	public List<String> getListNamesPhotos(String photosFolderPath) throws DaoException;
}
