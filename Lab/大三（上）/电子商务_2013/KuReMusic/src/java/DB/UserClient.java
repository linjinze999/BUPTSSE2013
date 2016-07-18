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
@Table(name = "user_client")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserClient.findAll", query = "SELECT u FROM UserClient u"),
    @NamedQuery(name = "UserClient.findByClientId", query = "SELECT u FROM UserClient u WHERE u.clientId = :clientId"),
    @NamedQuery(name = "UserClient.findByCLIENTpassword", query = "SELECT u FROM UserClient u WHERE u.cLIENTpassword = :cLIENTpassword"),
    @NamedQuery(name = "UserClient.findByCLIENTname", query = "SELECT u FROM UserClient u WHERE u.cLIENTname = :cLIENTname"),
    @NamedQuery(name = "UserClient.findByCLIENTmoney", query = "SELECT u FROM UserClient u WHERE u.cLIENTmoney = :cLIENTmoney")})
public class UserClient implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CLIENT_ID")
    private Integer clientId;
    @Basic(optional = false)
    @Column(name = "CLIENT_password")
    private String cLIENTpassword;
    @Basic(optional = false)
    @Column(name = "CLIENT_name")
    private String cLIENTname;
    @Basic(optional = false)
    @Column(name = "CLIENT_money")
    private int cLIENTmoney;

    public UserClient() {
    }

    public UserClient(Integer clientId) {
        this.clientId = clientId;
    }

    public UserClient(Integer clientId, String cLIENTpassword, String cLIENTname, int cLIENTmoney) {
        this.clientId = clientId;
        this.cLIENTpassword = cLIENTpassword;
        this.cLIENTname = cLIENTname;
        this.cLIENTmoney = cLIENTmoney;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getCLIENTpassword() {
        return cLIENTpassword;
    }

    public void setCLIENTpassword(String cLIENTpassword) {
        this.cLIENTpassword = cLIENTpassword;
    }

    public String getCLIENTname() {
        return cLIENTname;
    }

    public void setCLIENTname(String cLIENTname) {
        this.cLIENTname = cLIENTname;
    }

    public int getCLIENTmoney() {
        return cLIENTmoney;
    }

    public void setCLIENTmoney(int cLIENTmoney) {
        this.cLIENTmoney = cLIENTmoney;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clientId != null ? clientId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserClient)) {
            return false;
        }
        UserClient other = (UserClient) object;
        if ((this.clientId == null && other.clientId != null) || (this.clientId != null && !this.clientId.equals(other.clientId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DB.UserClient[ clientId=" + clientId + " ]";
    }
    
}
