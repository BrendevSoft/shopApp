package beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import rdvmedecins.jpa.Client;
import rdvmedecins.jpa.Creneau;
import rdvmedecins.jpa.Medecin;
import rdvmedecins.jpa.Rv;
import rdvmedecins.metier.entites.AgendaMedecinJour;
import rdvmedecins.metier.entites.CreneauMedecinJour;
import utils.Messages;

public class Form implements Serializable {

  public Form() {
  }
  // bean Application
  private Application application;
  // cache de la session
  private List<Medecin> medecins;
  private List<Client> clients;
  private Map<Long, Medecin> hMedecins = new HashMap<Long, Medecin>();
  private Map<Long, Client> hClients = new HashMap<Long, Client>();
  private Map<String, Client> hIdentitesClients = new HashMap<String, Client>();
  // modèle
  private Long idMedecin;
  private Date jour = new Date();
  private Boolean form1Rendered = true;
  private Boolean form2Rendered = false;
  private Boolean erreurRendered = false;
  private AgendaMedecinJour agendaMedecinJour;
  private Long idCreneauChoisi;
  private Medecin medecin;
  private Long idClient;
  private CreneauMedecinJour creneauChoisi;
  private List<Erreur> erreurs;
  private Boolean erreur = false;
  private String identiteClient;
  private String action;
  private String msgErreur;
  private Boolean erreurAction;
  private String locale = "fr";

  @PostConstruct
  private void init() {

    // l'initialisation s'est-elle bien passée ?
    if (application.getErreur()) {
      // on récupère la liste des erreurs
      erreurs = application.getErreurs();
      // la vue des erreurs est affichée
      setForms(false, false, true);
    }

    // on met les médecins et les clients en cache
    try {
      medecins = application.getMetier().getAllMedecins();
      clients = application.getMetier().getAllClients();
    } catch (Throwable th) {
      // on note l'erreur
      prepareVueErreur(th);
      return;
    }
    // vérification des listes
    if (medecins.size() == 0) {
      // on note l'erreur
      erreur = true;
      erreurs = new ArrayList<Erreur>();
      erreurs.add(new Erreur("", "La liste des médecins est vide"));
    }
    if (clients.size() == 0) {
      // on note l'erreur
      erreur = true;
      erreurs = new ArrayList<Erreur>();
      erreurs.add(new Erreur("", "La liste des clients est vide"));
    }
    // erreur ?
    if (erreur) {
      // la vue des erreurs est affichée
      setForms(true, false, true);
      return;
    }

    // les dictionnaires
    for (Medecin m : medecins) {
      hMedecins.put(m.getId(), m);
    }
    for (Client c : clients) {
      hClients.put(c.getId(), c);
      hIdentitesClients.put(identite(c), c);
    }
  }

  // liste des médecins
  public List<Medecin> getMedecins() {
    return medecins;
  }

  // liste des clients
  public List<Client> getClients() {
    return clients;
  }

  // agenda
  public void getAgenda() {
    try {
      // on récupère le médecin
      medecin = hMedecins.get(idMedecin);
      // l'agenda du médecin pour un jour donné
      agendaMedecinJour = application.getMetier().getAgendaMedecinJour(medecin, jour);
      // on affiche le formulaire 2
      setForms(true, true, false);
    } catch (Throwable th) {
      // vue des erreurs
      prepareVueErreur(th);
    }
    // aucun créneau choisi pour l'instant
    creneauChoisi = null;
  }

  // action sur RV
  public void action() {
    // selon l'action désirée
    if (action.equals("reserver")) {
      reserver();
    }
    if (action.equals("supprimer")) {
      supprimer();
    }
    if (action.equals("valider")) {
      validerResa();
    }
    if (action.equals("annuler")) {
      annulerRv();
    }
  }

  public void supprimer() {
    // faut-il faire qq chose ?
    Rv rv = creneauChoisi.getRv();
    if (rv == null) {
      signalerActionIncorrecte();
      return;
    }
    try {
      // suppression d'un Rdv
      application.getMetier().supprimerRv(rv);
      // on remet à jour l'agenda
      agendaMedecinJour = application.getMetier().getAgendaMedecinJour(medecin, jour);
      // on affiche form2
      setForms(true, true, false);
    } catch (Throwable th) {
      // vue erreurs
      prepareVueErreur(th);
    }
    // raz du créneau choisi
    creneauChoisi = null;
  }

  public void reserver() {
    // faut-il faire qq chose ?
    Rv rv = creneauChoisi.getRv();
    if (rv != null) {
      signalerActionIncorrecte();
      return;
    }
  }

  // validation Rv
  public void validerResa() {
    // validation de la réservation
    try {
      // le client existe-t-il ?
      Boolean erreur = !hIdentitesClients.containsKey(identiteClient);
      if (erreur) {
        msgErreur = Messages.getMessage(null, "form2.erreurClient", new Object[]{identiteClient}).getSummary();
        RequestContext.getCurrentInstance().addCallbackParam("erreur", true);
        return;
      }
      // on ajoute le Rv
      application.getMetier().ajouterRv(jour, creneauChoisi.getCreneau(), hIdentitesClients.get(identiteClient));
      // on remet à jour l'agenda
      agendaMedecinJour = application.getMetier().getAgendaMedecinJour(medecin, jour);
      // on affiche form2
      setForms(true, true, false);
    } catch (Throwable th) {
      // vue erreurs
      prepareVueErreur(th);
    }
    // raz du créneau choisi
    creneauChoisi = null;
    // raz client
    identiteClient = null;
  }

  // annulation prise de Rdv
  public void annulerRv() {
    // on affiche form2
    setForms(true, true, false);
    // raz du créneau choisi
    creneauChoisi = null;
    // raz client
    identiteClient = null;
  }

  public void accueil() {
    // on affiche la page d'accueil
    setForms(true, false, false);
  }

  public void refresh() {
    // on rafraîchit les listes
    init();
  }

  // la méthode du texte autocomplete
  public List<String> completeClients(String query) {
    List<String> identites = new ArrayList<String>();
    // on recherche les clients qui correspondent
    for (Client c : clients) {
      String identite = identite(c);
      if (identite.toLowerCase().startsWith(query.toLowerCase())) {
        identites.add(identite);
      }
    }
    return identites;
  }

  public void getPreviousAgenda() {
    // on passe au jour précédent
    Calendar cal = Calendar.getInstance();
    cal.setTime(jour);
    cal.add(Calendar.DAY_OF_YEAR, -1);
    jour = cal.getTime();
    // agenda
    if (form2Rendered) {
      getAgenda();
    }
  }

  public void getNextAgenda() {
    // on passe au jour suivant
    Calendar cal = Calendar.getInstance();
    cal.setTime(jour);
    cal.add(Calendar.DAY_OF_YEAR, 1);
    jour = cal.getTime();
    // agenda
    if (form2Rendered) {
      getAgenda();
    }
  }

  // le modèle du dataTable
  public MyDataModel getMyDataModel() {
    return new MyDataModel(agendaMedecinJour.getCreneauxMedecinJour());
  }

  // agenda aujourd'hui
  public void today() {
    jour = new Date();
    // agenda
    if (form2Rendered) {
      getAgenda();
    }
  }

  public void setFrenchLocale() {
    locale = "fr";
    // on recharge la page
    redirect();
  }

  public void setEnglishLocale() {
    locale = "en";
    // on recharge la page
    redirect();
  }

  private void redirect() {
    // on redirige le client vers la servlet
    ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
    try {
      ctx.redirect(ctx.getRequestContextPath());
    } catch (IOException ex) {
      Logger.getLogger(Form.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  // identité d'un client

  private String identite(Client c) {
    return c.getNom() + " " + c.getPrenom() + " " + c.getTitre();
  }

  // affichage vue
  private void setForms(Boolean form1Rendered, Boolean form2Rendered, Boolean erreurRendered) {
    this.form1Rendered = form1Rendered;
    this.form2Rendered = form2Rendered;
    this.erreurRendered = erreurRendered;
  }

  // préparation vueErreur
  private void prepareVueErreur(Throwable th) {
    // on crée la liste des erreurs
    erreurs = new ArrayList<Erreur>();
    erreurs.add(new Erreur(th.getClass().getName(), th.getMessage()));
    while (th.getCause() != null) {
      th = th.getCause();
      erreurs.add(new Erreur(th.getClass().getName(), th.getMessage()));
    }
// la vue des erreurs est affichée
    setForms(true, false, true);
  }

  // signaler une action incorrecte
  private void signalerActionIncorrecte() {
    // raz créneau choisi
    creneauChoisi = null;
    // erreur
    msgErreur = Messages.getMessage(null, "form2.erreurAction", null).getSummary();
    RequestContext.getCurrentInstance().addCallbackParam("erreur", true);
  }

  // cacher l'agenda
  public void hideAgenda() {
    setForms(true, false, false);
  }

  // getters et setters
  public Date getJour() {
    return jour;
  }

  public void setJour(Date jour) {
    this.jour = jour;
  }

  public Long getIdMedecin() {
    return idMedecin;
  }

  public void setIdMedecin(Long idMedecin) {
    this.idMedecin = idMedecin;
  }

  public Boolean getForm1Rendered() {
    return form1Rendered;
  }

  public Boolean getForm2Rendered() {
    return form2Rendered;
  }

  public AgendaMedecinJour getAgendaMedecinJour() {
    return agendaMedecinJour;
  }

  public Long getIdClient() {
    return idClient;
  }

  public void setIdClient(Long idClient) {
    this.idClient = idClient;
  }

  public Boolean getErreurRendered() {
    return erreurRendered;
  }

  public List<Erreur> getErreurs() {
    return erreurs;
  }

  public String getIdentiteClient() {
    return identiteClient;
  }

  public void setIdentiteClient(String identiteClient) {
    this.identiteClient = identiteClient;
  }

  public void setAction(String action) {
    this.action = action;
  }

  public void setCreneauChoisi(CreneauMedecinJour creneauChoisi) {
    this.creneauChoisi = creneauChoisi;
  }

  public CreneauMedecinJour getCreneauChoisi() {
    return creneauChoisi;
  }

  public Boolean getErreurAction() {
    return erreurAction;
  }

  public void setIdCreneauChoisi(Long idCreneauChoisi) {
    this.idCreneauChoisi = idCreneauChoisi;
  }

  public String getLocale() {
    return locale;
  }

  public String getMsgErreur() {
    return msgErreur;
  }

  public Application getApplication() {
    return application;
  }

  public void setApplication(Application application) {
    this.application = application;
  }
}