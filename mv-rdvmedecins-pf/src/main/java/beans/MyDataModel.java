package beans;

import javax.faces.model.ArrayDataModel;
import org.primefaces.model.SelectableDataModel;
import rdvmedecins.metier.entites.CreneauMedecinJour;

public class MyDataModel extends ArrayDataModel<CreneauMedecinJour> implements SelectableDataModel<CreneauMedecinJour> {

  // constructeurs
  public MyDataModel() {
  }

  public MyDataModel(CreneauMedecinJour[] creneauxMedecinJour) {
    super(creneauxMedecinJour);
  }

  @Override
  public Object getRowKey(CreneauMedecinJour creneauMedecinJour) {
    return creneauMedecinJour.getCreneau().getId();
  }

  @Override
  public CreneauMedecinJour getRowData(String rowKey) {
    // liste des créneaux
    CreneauMedecinJour[] creneauxMedecinJour = (CreneauMedecinJour[]) getWrappedData();
    // la clé est un entier long
    long key = Long.parseLong(rowKey);
    // on recherche le créneau sélectionné
    for (CreneauMedecinJour creneauMedecinJour : creneauxMedecinJour) {
      if (creneauMedecinJour.getCreneau().getId().longValue() == key) {
        return creneauMedecinJour;
      }
    }
    // rien
    return null;
  }
}
