package rdvmedecins.jpa;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "clients")
public class Client extends Personne implements Serializable {
  private static final long serialVersionUID = 1L;

  public Client() {
    super();
  }

  public Client(Long id) {
    super(id);
  }

  public Client(Long id, String titre, String nom, int version, String prenom) {
    super(id, titre, nom, version, prenom);
  }

  @Override
  public int hashCode() {
    Long id=getId();
    int hash = 0;
    hash += (id != null ? id.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Client)) {
      return false;
    }
    Client other = (Client) object;
    if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getNom().equals(other.getId()))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return String.format("Client[%s,%s,%s,%s]", getId(), getTitre(), getPrenom(), getNom());
  }
  
}
