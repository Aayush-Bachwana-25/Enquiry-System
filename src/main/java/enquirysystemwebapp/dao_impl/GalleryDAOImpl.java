package enquirysystemwebapp.dao_impl;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import enquirysystemwebapp.dao.GalleryDAO;
import enquirysystemwebapp.helper.ConnectionProvider;
import enquirysystemwebapp.models.Album;

public class GalleryDAOImpl implements GalleryDAO {
	private static Connection con;
		
	static
	{
		con=ConnectionProvider.getConnection();
	}
	
	public boolean addAlbum(Album album) {
		// TODO Auto-generated method stub
		try {
			String query="insert into albums(title,description,validity) values(?,?,?)";
			PreparedStatement pst=con.prepareStatement(query);
			pst.setString(1, album.getAlbumName());
			pst.setString(2, album.getAlbumDescription());
			pst.setString(3, album.getValidityDate());
			int affectedRows=pst.executeUpdate();
			
			if(affectedRows>0) {
				return true;
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	public Album getAlbumById(int albumId) {
		// TODO Auto-generated method stub
		try {
			String query="select id,title,description,cover,validity from albums where id=?";
			PreparedStatement pst=con.prepareStatement(query);
			pst.setInt(1, albumId);
			ResultSet rs=pst.executeQuery();
			
			if(rs.next()) {
				Album album=new Album();
				album.setAlbumId(rs.getInt(1));
				album.setAlbumName(rs.getString(2));
				album.setAlbumDescription(rs.getString(3));
				album.setCoverImage(rs.getString(4));
				album.setValidityDate(rs.getString(5));
				return album;
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	public List<Album> getAllAlbums() {
		// TODO Auto-generated method stub
		List<Album> albums=new ArrayList<Album>();
		try {
			String query="select id,title,description,cover,validity from albums";
			PreparedStatement pst=con.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			
			
			while(rs.next()) {
				Album album=new Album();
				album.setAlbumId(rs.getInt(1));
				album.setAlbumName(rs.getString(2));
				album.setAlbumDescription(rs.getString(3));
				album.setCoverImage(rs.getString(4));
				album.setValidityDate(rs.getString(5));
				albums.add(album);
			}
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if(albums.size()>0) {
			return albums;
		}
		return null;
	}
	
	public List<Album> getAllValidAlbums() {
		// TODO Auto-generated method stub
		List<Album> albums=new ArrayList<Album>();
		try {
			String query="select id,title,description,cover,validity from albums where validity>=curdate()";
			PreparedStatement pst=con.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			
			
			while(rs.next()) {
				Album album=new Album();
				album.setAlbumId(rs.getInt(1));
				album.setAlbumName(rs.getString(2));
				album.setAlbumDescription(rs.getString(3));
				album.setCoverImage(rs.getString(4));
				album.setValidityDate(rs.getString(5));
				albums.add(album);
			}
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if(albums.size()>0) {
			return albums;
		}
		return null;
	}

	public boolean deleteAlbumById(int albumId) {
		// TODO Auto-generated method stub
		try {
			String query="delete from albums where id=?";
			PreparedStatement pst=con.prepareStatement(query);
			pst.setInt(1, albumId);
			int affectedRows=pst.executeUpdate();
			
			if(affectedRows>0) {
				return true;
			}
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean addImageToAlbum(MultipartFile file,int albumId,HttpSession session) {
		try {
			byte[] data=file.getBytes();
			String path=session.getServletContext().getRealPath("/")+"resources"+File.separator+"images"+File.separator+file.getOriginalFilename();
		  
			FileOutputStream fos=new FileOutputStream(path); 
			fos.write(data);
			fos.close(); 
			System.out.println(path);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		
		try {
			String filename=file.getOriginalFilename();
			String query="insert into gallery(album_id,image_name) values(?,?)";
			PreparedStatement pst=con.prepareStatement(query);
			pst.setInt(1, albumId);
			pst.setString(2, filename);
			
			int affectedRows=pst.executeUpdate();
			
			if(affectedRows>0) {
				return true;
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
		
	}
	
	public boolean deleteImageFromAlbum(int albumId,String fileName,String fileFormat,HttpSession session) {
		try {
			String path=session.getServletContext().getRealPath("/")+"resources"+File.separator+"images"+File.separator+fileName+"."+fileFormat;
			System.out.println(path);
			File file=new File(path);
			
			if(file.exists()) {
				boolean isDeleted=file.delete();
				
				if (isDeleted) {
					System.out.println("File deleted successfully.");
			    } 
				else {
					System.out.println("Failed to delete the file.");
					return false;
			    }
			}
			else {
				System.out.println("File not found!");
				return false;
			}
		}
		catch(Exception ee) {
			ee.printStackTrace();
			return false;
		}
		
		try {
			String query="delete from gallery where album_id=? and image_name=?";
			PreparedStatement pst=con.prepareStatement(query);
			pst.setInt(1, albumId);
			pst.setString(2, fileName+"."+fileFormat);
			int affectedRows=pst.executeUpdate();
			
			System.out.println(albumId+"\t"+fileName+"\t"+fileFormat);
			
			if(affectedRows>0) {
				System.out.println("image deleted!");
				return true;
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	public List<String> getImagesByAlbum(int albumId) {
		// TODO Auto-generated method stub
		try {
			List<String> images=new ArrayList<String>();
			
			String query="select image_name from gallery where album_id=?";
			PreparedStatement pst=con.prepareStatement(query);
			pst.setInt(1, albumId);
			ResultSet rs=pst.executeQuery();
			
			while(rs.next()) {
				images.add(rs.getString(1));
			}

			if(images.size()>0) {
				return images;
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean setImageAsAlbumCover(int albumId,String fileName,String fileFormat) {
		try {
			String file=fileName+"."+fileFormat;
			
			String query="update albums set cover=? where id=?";
			PreparedStatement pst=con.prepareStatement(query);
			pst.setString(1, file);
			pst.setInt(2, albumId);
			
			int affectedRows=pst.executeUpdate();
			
			if(affectedRows>0) {
				return true;
			}
			
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

}
