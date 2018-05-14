package rdvmedecins.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import rdvmedecins.exceptions.RdvMedecinsException;
import rdvmedecins.jpa.Client;
import rdvmedecins.jpa.Creneau;
import rdvmedecins.jpa.Medecin;
import rdvmedecins.jpa.Rv;

@Repository
@Transactional
public class DaoJpa implements IDao, Serializable {

    private static final long serialVersionUID = 1L;
    @PersistenceContext
    private EntityManager em;

    // liste des clients
    @Transactional
    public List<Client> getAllClients() {
        try {
            return em.createQuery("select rc from Client rc").getResultList();
        } catch (Throwable th) {
            throw new RdvMedecinsException(th, 1);
        }
    }

    // liste des médecins
    @Transactional
    public List<Medecin> getAllMedecins() {
        try {
            return em.createQuery("select rm from Medecin rm").getResultList();
        } catch (Throwable th) {
            throw new RdvMedecinsException(th, 2);
        }
    }

    // liste des créneaux horaires d'un médecin donné
    // medecin : le médecin
    @Transactional
    public List<Creneau> getAllCreneaux(Medecin medecin) {
        try {
            return em.createQuery("select rc from Creneau rc join rc.medecin m where m.id=:idMedecin").setParameter("idMedecin", medecin.getId()).getResultList();
        } catch (Throwable th) {
            throw new RdvMedecinsException(th, 3);
        }
    }

    // liste des Rv d'un médecin donné, un jour donné
    // medecin : le médecin
    // jour : le jour
    @Transactional
    public List<Rv> getRvMedecinJour(Medecin medecin, Date jour) {
        try {
            return em.createQuery("select rv from Rv rv join rv.creneau c join c.medecin m where m.id=:idMedecin and rv.jour=:jour").setParameter("idMedecin", medecin.getId()).setParameter("jour", jour).getResultList();
        } catch (Throwable th) {
            throw new RdvMedecinsException(th, 3);
        }
    }

    // ajout d'un Rv
    // jour : jour du Rv
    // creneau : créneau horaire du Rv
    // client : client pour lequel est pris le Rv
    @Transactional
    public Rv ajouterRv(Date jour, Creneau creneau, Client client) {
        try {
            Rv rv = new Rv(null, jour);
            rv.setClient(client);
            rv.setCreneau(creneau);
            System.out.println(String.format("avant persist : %s", rv));
            em.persist(rv);
            System.out.println(String.format("après persist : %s", rv));
            return rv;
        } catch (Throwable th) {
            throw new RdvMedecinsException(th, 4);
        }
    }

    // suppression d'un Rv
    // rv : le Rv supprimé
    @Transactional
    public void supprimerRv(Rv rv) {
        try {
            em.remove(em.merge(rv));
        } catch (Throwable th) {
            throw new RdvMedecinsException(th, 5);
        }
    }

    // récupérer un client donné
    @Transactional
    public Client getClientById(Long id) {
        try {
            return (Client) em.find(Client.class, id);
        } catch (Throwable th) {
            throw new RdvMedecinsException(th, 6);
        }
    }

    // récupérer un médecin donné
    @Transactional
    public Medecin getMedecinById(Long id) {
        try {
            return (Medecin) em.find(Medecin.class, id);
        } catch (Throwable th) {
            throw new RdvMedecinsException(th, 6);
        }
    }

    // récupérer un Rv donné
    @Transactional
    public Rv getRvById(Long id) {
        try {
            return (Rv) em.find(Rv.class, id);
        } catch (Throwable th) {
            throw new RdvMedecinsException(th, 6);
        }
    }

    // récupérer un créneau donné
    @Transactional
    public Creneau getCreneauById(Long id) {
        try {
            return (Creneau) em.find(Creneau.class, id);
        } catch (Throwable th) {
            throw new RdvMedecinsException(th, 6);
        }
    }
}
