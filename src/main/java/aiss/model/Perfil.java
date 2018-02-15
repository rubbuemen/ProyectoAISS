package aiss.model;

import java.util.ArrayList;
import java.util.List;

public class Perfil {
	private String id;
	private String nick;
	private List<MyAnime> myAnimeList;

	public Perfil(String id, String nick, List<MyAnime> myAnimeList) {
		this.id = id;
		this.nick = nick;
		this.myAnimeList = myAnimeList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public List<MyAnime> getMyAnimeList() {
		return myAnimeList;
	}

	public void setMyAnimeList(List<MyAnime> myAnimeList) {
		this.myAnimeList = myAnimeList;
	}
	
	public void addAnime(MyAnime a) {
		if (myAnimeList == null) {
			myAnimeList = new ArrayList<MyAnime>();
		}
		myAnimeList.add(a);
	}
	
	public void updateAnime(MyAnime a) {
		if(myAnimeList.contains(a)) {
			myAnimeList.remove(a);
		}
		myAnimeList.add(a);
	}
	
	public void deleteAnime(MyAnime a) {
		myAnimeList.remove(a);
	}

}
