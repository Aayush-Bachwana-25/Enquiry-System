package enquirysystemwebapp.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import enquirysystemwebapp.models.Album;

public interface GalleryDAO {
	public boolean addAlbum(Album album);
	public Album getAlbumById(int albumId);
	public List<Album> getAllAlbums();
	public boolean deleteAlbumById(int albumId);
	

	//Actions for album
	public boolean addImageToAlbum(MultipartFile file,int albumId,HttpSession session);
	public List<String> getImagesByAlbum(int albumId);
}
