package uk.co.yene.core;



public class PlayList {

	private String description; 	
	private String duration; 	
	private String title;
	private String image; 		
	private String videoid;
	private String fav;
	private String vcount;
	
	public PlayList(String d , String time , String title, String image , String vid, String fav, String viewcount){
		this.description 	= d;
		this.duration 		= time;
		this.title 			= title;
		this.image 			= image;
		this.videoid 		= vid;
		this.fav 			= fav;
		this.vcount 		= viewcount;
		
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getVideoid() {
		return videoid;
	}

	public void setVideoid(String videoid) {
		this.videoid = videoid;
	}

	public String getFav() {
		return fav;
	}

	public void setFav(String fav) {
		this.fav = fav;
	}

	public String getVcount() {
		return vcount;
	}

	public void setVcount(String vcount) {
		this.vcount = vcount;
	}

}
