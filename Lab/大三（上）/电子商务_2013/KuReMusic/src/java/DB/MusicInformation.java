/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "music_information")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MusicInformation.findAll", query = "SELECT m FROM MusicInformation m"),
    @NamedQuery(name = "MusicInformation.findByMusicId", query = "SELECT m FROM MusicInformation m WHERE m.musicId = :musicId"),
    @NamedQuery(name = "MusicInformation.findByMUSICname", query = "SELECT m FROM MusicInformation m WHERE m.mUSICname = :mUSICname"),
    @NamedQuery(name = "MusicInformation.findByMUSICurl", query = "SELECT m FROM MusicInformation m WHERE m.mUSICurl = :mUSICurl"),
    @NamedQuery(name = "MusicInformation.findByMUSICvalue", query = "SELECT m FROM MusicInformation m WHERE m.mUSICvalue = :mUSICvalue"),
    @NamedQuery(name = "MusicInformation.findByMUSIClyricurl", query = "SELECT m FROM MusicInformation m WHERE m.mUSIClyricurl = :mUSIClyricurl")})
public class MusicInformation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "MUSIC_ID")
    private Integer musicId;
    @Basic(optional = false)
    @Column(name = "MUSIC_name")
    private String mUSICname;
    @Basic(optional = false)
    @Column(name = "MUSIC_url")
    private String mUSICurl;
    @Basic(optional = false)
    @Column(name = "MUSIC_value")
    private int mUSICvalue;
    @Column(name = "MUSIC_lyric_url")
    private String mUSIClyricurl;

    public MusicInformation() {
    }

    public MusicInformation(Integer musicId) {
        this.musicId = musicId;
    }

    public MusicInformation(Integer musicId, String mUSICname, String mUSICurl, int mUSICvalue) {
        this.musicId = musicId;
        this.mUSICname = mUSICname;
        this.mUSICurl = mUSICurl;
        this.mUSICvalue = mUSICvalue;
    }

    public Integer getMusicId() {
        return musicId;
    }

    public void setMusicId(Integer musicId) {
        this.musicId = musicId;
    }

    public String getMUSICname() {
        return mUSICname;
    }

    public void setMUSICname(String mUSICname) {
        this.mUSICname = mUSICname;
    }

    public String getMUSICurl() {
        return mUSICurl;
    }

    public void setMUSICurl(String mUSICurl) {
        this.mUSICurl = mUSICurl;
    }

    public int getMUSICvalue() {
        return mUSICvalue;
    }

    public void setMUSICvalue(int mUSICvalue) {
        this.mUSICvalue = mUSICvalue;
    }

    public String getMUSIClyricurl() {
        return mUSIClyricurl;
    }

    public void setMUSIClyricurl(String mUSIClyricurl) {
        this.mUSIClyricurl = mUSIClyricurl;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (musicId != null ? musicId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MusicInformation)) {
            return false;
        }
        MusicInformation other = (MusicInformation) object;
        if ((this.musicId == null && other.musicId != null) || (this.musicId != null && !this.musicId.equals(other.musicId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DB.MusicInformation[ musicId=" + musicId + " ]";
    }
    
}
