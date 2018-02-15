package aiss.myAnimeList;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "entry")
@XmlAccessorType(XmlAccessType.FIELD)
public class AnimeListEntryValues {

    @XmlElement
    private String episode;

    @XmlElement
    private String status;

    @XmlElement
    private String score;

    @XmlElement(name = "storage_type")
    private String storageType;

    @XmlElement(name = "storage_value")
    private String storageValue;

    @XmlElement(name = "times_rewatched")
    private String timesRewatched;

    @XmlElement(name = "rewatch_value")
    private String rewatchValue;

    @XmlElement(name = "date_start")
    private String dateStart;

    @XmlElement(name = "date_finish")
    private String dateFinish;

    @XmlElement
    private String priority;

    @XmlElement(name = "enable_discussion")
    private String enableDiscussion;

    @XmlElement(name = "enable_rewatching")
    private String enableRewatching;

    @XmlElement
    private String comments;

    @XmlElement(name = "fansub_group")
    private String fansubGroup;

    @XmlElement
    private String tags;
    
    public AnimeListEntryValues() {
	}
    
    public AnimeListEntryValues(String status, String score, String episode) {
    	this.status = status;
    	this.score = score;
    	this.episode = episode;
	}
    
    public String getEpisode(){
    	return episode;
    }

    public String getStatus() {
    	return status;
    }

    public String getScore() {
    	return score;
    }

    public String getStorageType() {
    	return storageType;
    }
    
    public String getStorageValue() {
    	return storageValue;
    }

    public String getTimesRewatched() {
    	return timesRewatched;
    }
    
    public String getRewatchValue() {
    	return rewatchValue;
    }

    public String getDateStart() {
    	return dateStart;
    }

    public String getDateFinish() {
    	return dateFinish;
    }
    
    public String getPriority() {
    	return priority;
    }
    
    public String getEnableDiscussion() {
    	return enableDiscussion;
    }

    public String getEnableRewatching(){
    	return enableRewatching;
    }

    public String getComments() {
    	return comments;
    }

    public String getFansubGroup() {
    	return fansubGroup;
    }

    public String getTags() {
    	return tags;
    }

}
