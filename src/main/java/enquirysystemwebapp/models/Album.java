package enquirysystemwebapp.models;

public class Album {
	int albumId;
	String albumName;
	String albumDescription;
	String coverImage;
	String validityDate;
	
	
	public Album() {
		super();
	}
	public Album(String albumName, String albumDescription) {
		super();
		this.albumName = albumName;
		this.albumDescription = albumDescription;
	}
	@Override
	public String toString() {
		return "Album [albumId=" + albumId + ", albumName=" + albumName + ", albumDescription=" + albumDescription
				+ "]";
	}
	public int getAlbumId() {
		return albumId;
	}
	public void setAlbumId(int albumId) {
		this.albumId = albumId;
	}
	public String getAlbumName() {
		return albumName;
	}
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}
	public String getAlbumDescription() {
		return albumDescription;
	}
	public void setAlbumDescription(String albumDescription) {
		this.albumDescription = albumDescription;
	}
	public String getCoverImage() {
		return coverImage;
	}
	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}
	public String getValidityDate() {
		return validityDate;
	}
	public void setValidityDate(String validityDate) {
		this.validityDate = validityDate;
	}
	
	
	
}
