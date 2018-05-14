package rdvmedecins.tests.dao;

import java.util.Date;
import java.util.List;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import junit.framework.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import rdvmedecins.dao.IDao;
import rdvmedecins.jpa.Client;
import rdvmedecins.jpa.Creneau;
import rdvmedecins.jpa.Medecin;
import rdvmedecins.jpa.Rv;

public class JUnitTestDao {

  // couche [dao] testée
  private static IDao dao;
  // date du jour
  Date jour = new Date();

  @BeforeClass
  public static void init() throws NamingException {
    // instanciation couche [dao]
    ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config-dao.xml");
    dao = (IDao) ctx.getBean("dao");
  }

  @Test
  public void test1() {
    // affichage clients
    List<Client> clients =dao.getAllClients();
    display("Liste des clients :", clients);
    // affichage médecins
    List<Medecin> medecins =dao.getAllMedecins();
    display("Liste des médecins :", medecins);
    // affichage créneaux d'un médecin
    Medecin medecin = medecins.get(0);
    List<Creneau> creneaux = dao.getAllCreneaux(medecin);
    display(String.format("Liste des créneaux du médecin %s", medecin), creneaux);
    // liste des Rv d'un médecin, un jour donné
    display(String.format("Liste des créneaux du médecin %s, le [%s]", medecin, jour), dao.getRvMedecinJour(medecin, jour));
    // ajouter un RV
    Rv rv = null;
    Creneau creneau = creneaux.get(2);
    Client client = clients.get(0);
    System.out.println(String.format("Ajout d'un Rv le [%s] dans le créneau %s pour le client %s", jour, creneau, client));
    rv = dao.ajouterRv(jour, creneau, client);
    System.out.println("Rv ajouté");
    display(String.format("Liste des Rv du médecin %s, le [%s]", medecin, jour), dao.getRvMedecinJour(medecin, jour));
    // ajouter un RV dans le même créneau du même jour
    // doit provoquer une exception
    System.out.println(String.format("Ajout d'un Rv le [%s] dans le créneau %s pour le client %s", jour, creneau, client));
    Boolean erreur = false;
    try {
      rv = dao.ajouterRv(jour, creneau, client);
      System.out.println("Rv ajouté");
    } catch (Exception ex) {
      Throwable th = ex;
      while (th != null) {
        System.out.println(ex.getMessage());
        th = th.getCause();
      }
      // on note l'erreur
      erreur=true;
    }
    // on vérifie qu'il y a eu une erreur
    Assert.assertTrue(erreur);
    // liste des RV
    display(String.format("Liste des Rv du médecin %s, le [%s]", medecin, jour), dao.getRvMedecinJour(medecin, jour));
    // supprimer un RV
    System.out.println("Suppression du Rv ajouté");
    dao.supprimerRv(rv);
    System.out.println("Rv supprimé");
    display(String.format("Liste des Rv du médecin %s, le [%s]", medecin, jour), dao.getRvMedecinJour(medecin, jour));
  }

  // méthode utilitaire - affiche les éléments d'une collection
  private static void display(String message, List elements) {
    System.out.println(message);
    for (Object element : elements) {
      System.out.println(element);
    }
  }
}
