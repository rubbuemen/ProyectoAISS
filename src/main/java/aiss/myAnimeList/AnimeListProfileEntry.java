package aiss.myAnimeList;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "anime")
@XmlAccessorType(XmlAccessType.FIELD)
public class AnimeListProfileEntry {

    @XmlElement(name = "series_animedb_id")
    private String seriesId;

    @XmlElement(name = "series_title")
    private String seriesTitle;

    @XmlElement(name = "series_synonyms")
    private String seriesSynonyms;

    @XmlElement(name = "series_type")
    private String seriesType;

    @XmlElement(name = "series_episodes")
    private String seriesEpisodes;

    @XmlElement(name = "series_status")
    private String seriesStatus;

    @XmlElement(name = "series_start")
    private String seriesStart;

    @XmlElement(name = "series_end")
    private String seriesEnd;

    @XmlElement(name = "series_image")
    private String seriesImage;

    @XmlElement(name = "my_id")
    private String myId;

    @XmlElement(name = "my_watched_episodes")
    private String myWatchedEpisodes;

    @XmlElement(name = "my_start_date")
    private String myStartDate;

    @XmlElement(name = "my_finish_date")
    private String myFinishDate;

    @XmlElement(name = "my_score")
    private String myScore;

    @XmlElement(name = "my_status")
    private String myStatus;

    @XmlElement(name = "my_rewatching")
    private String myRewatching;

    @XmlElement(name = "my_rewatching_ep")
    private String myRewatchingEp;

    @XmlElement(name = "my_last_updated")
    private String myLastUpdated;

    @XmlElement(name = "my_tags")
    private String myTags;
    
    public String getSeriesId() {
        return seriesId;
    }

    public String getSeriesTitle() {
        return seriesTitle;
    }

    public String getSeriesSynonyms() {
        return seriesSynonyms;
    }
    
    public String getSeriesType() {
        return seriesType;
    }
    
    public String getSeriesEpisodes() {
        return seriesEpisodes;
    }
    
    public String getSeriesStatus() {
        return seriesStatus;
    }

    public String getSeriesStart() {
        return seriesStart;
    }
    
    public String getSeriesEnd() {
        return seriesEnd;
    }
    
    public String getSeriesImage() {
        return seriesImage;
    }
    
    public String getMyId() {
        return myId;
    }
    
    public String getMyWatchedEpisodes() {
        return myWatchedEpisodes;
    }
    
    public String getMyStartDate() {
        return myStartDate;
    }
    
    public String getMyFinishDate() {
        return myFinishDate;
    }
    
    public String getMyScore() {
        return myScore;
    }
    
    public String getMyStatus() {
        return myStatus;
    }
    
    public String getMyRewatching() {
        return myRewatching;
    }
    
    public String getMyRewatchingEp() {
        return myRewatchingEp;
    }
    
    public String getMyLastUpdated() {
        return myLastUpdated;
    }
    
    public String getMyTags() {
        return myTags;
    }

    @XmlRootElement(name = "myanimelist")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Perfil {

        @XmlElement(name = "anime")
        private List<AnimeListProfileEntry> records;
        
        public List<AnimeListProfileEntry> getRecords() {
            return records;
        }

        @XmlAnyElement(lax = true)
        private List<Object> anything;

    }
}
