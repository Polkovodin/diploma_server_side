package by.gsu.model.factories;

import by.gsu.ifaces.IPhotosDAO;
import by.gsu.model.impl.PhotosImpl;

public class PhotosFactory {
	public static IPhotosDAO getClassFromFactory() {
		return new PhotosImpl();
	}
}
