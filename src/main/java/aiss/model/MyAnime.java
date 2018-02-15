package aiss.model;

import java.util.List;

public class MyAnime extends Anime {
	private Integer myScore;
	private String myStatus;

	public MyAnime() {}	
	
	public MyAnime(String id, String name, Integer episodes, String startDate, String endDate, String synonyms,	List<Imagen> imagen, List<Noticia> noticias, List<Producto> productos, List<Video> videos, Integer myScore,	String myStatus) {
		super(id, name, episodes, startDate, endDate, synonyms, imagen, noticias, productos, videos);
		this.myScore = myScore;
		this.myStatus = myStatus;
	}
	
	public MyAnime(String name, Integer episodes, String startDate, String endDate, String synonyms,	List<Imagen> imagen, List<Noticia> noticias, List<Producto> productos, List<Video> videos, Integer myScore,	String myStatus) {
		super(name, episodes, startDate, endDate, synonyms, imagen, noticias, productos, videos);
		this.myScore = myScore;
		this.myStatus = myStatus;
	}

	public Integer getMyScore() {
		return myScore;
	}

	public void setMyScore(Integer myScore) {
		this.myScore = myScore;
	}

	public String getMyStatus() {
		return myStatus;
	}

	public void setMyStatus(String myStatus) {
		this.myStatus = myStatus;
	}
}
