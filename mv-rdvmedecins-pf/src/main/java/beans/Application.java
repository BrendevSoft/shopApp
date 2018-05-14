package beans;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import rdvmedecins.metier.service.IMetier;

public class Application {

  // couche métier
  private IMetier metier;
  // erreurs
  private List<Erreur> erreurs = new ArrayList<Erreur>();
  private Boolean erreur = false;

  public Application() {
    try {
      // instanciation couche [métier]
      ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config-metier-dao.xml");
      metier = (IMetier) ctx.getBean("metier");
    } catch (Throwable th) {
      // on note l'erreur
      erreur = true;
      erreurs.add(new Erreur(th.getClass().getName(), th.getMessage()));
      while (th.getCause() != null) {
        th = th.getCause();
        erreurs.add(new Erreur(th.getClass().getName(), th.getMessage()));
      }
      return;
    }
  }

  // getters
  public Boolean getErreur() {
    return erreur;
  }

  public List<Erreur> getErreurs() {
    return erreurs;
  }

  public IMetier getMetier() {
    return metier;
  }
}
