package aiss.model;

public class Video {
	private String id;
	private String title;
	private String url;

	public Video() {}
	
	public Video(String id, String title, String url) {
		this.id = id;
		this.title = title;
		this.url = url;
	}
	
	public Video(String title, String url) {
		this.title = title;
		this.url = url;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}