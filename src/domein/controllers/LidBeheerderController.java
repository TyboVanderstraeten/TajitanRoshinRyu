package domein.controllers;

import domein.Activiteit;
import domein.Admin;
import domein.Inschrijving;
import domein.Lid;
import domein.enums.Formule;
import domein.enums.Functie;
import domein.enums.Graad;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

public class LidBeheerderController {

    private DataController dataController;
    private ObservableList<Lid> ledenList;
    private FilteredList<Lid> filteredList;
    private SortedList<Lid> sortedList;

    private final Comparator<Lid> byVoornaam = (p1, p2) -> p1.getVoornaam().compareToIgnoreCase(p2.getVoornaam());

    private final Comparator<Lid> byAchternaam = (p1, p2) -> p1.getAchternaam().compareToIgnoreCase(p2.getAchternaam());

    private final Comparator<Lid> sortOrder = byVoornaam.thenComparing(byAchternaam);

    public LidBeheerderController() {
        dataController = new DataController();
        ledenList = FXCollections.observableArrayList(dataController.geefLeden());
        filteredList = new FilteredList(ledenList, p -> true);
        sortedList = new SortedList(filteredList, sortOrder);
    }

    //
    //Overzichte van leden!
    //
    public ObservableList<Lid> geefAlleLeden() { //voor combobox
        return FXCollections.unmodifiableObservableList(ledenList);
    }

    public ObservableList<Lid> geefObservableListLeden() {
        return FXCollections.unmodifiableObservableList(sortedList);
    }

    public void filterList(String voornaam, String familienaam, Graad graad, Functie functie) {

        filteredList.setPredicate(lid -> {
            boolean voornaamEmpty = voornaam.isEmpty() || voornaam.equals("");
            boolean familienaamEmpty = familienaam.isEmpty() || familienaam.equals("");
            boolean graadEmpty = graad == null || graad.name().equals("");
            boolean functieEmpty = functie == null || functie.name().equals("");

            boolean voornaamFilter = lid.getVoornaam().toLowerCase().equals(voornaam.toLowerCase()) || lid.getVoornaam().toLowerCase().startsWith(voornaam.toLowerCase());
            boolean familieNaamFilter = lid.getAchternaam().toLowerCase().equals(familienaam.toLowerCase()) || lid.getAchternaam().toLowerCase().startsWith(familienaam.toLowerCase());
            boolean graadFilter = lid.getGraad().equals(graad);
            boolean functieFilter = lid.getFunctie().equals(functie);

            //0000
            if (voornaamEmpty && familienaamEmpty && graadEmpty && functieEmpty) {
                return true;
            }
            //0001
            if (voornaamEmpty && familienaamEmpty && graadEmpty && !functieEmpty) {
                return functieFilter;
            }
            //0010
            if (voornaamEmpty && familienaamEmpty && !graadEmpty && functieEmpty) {
                return graadFilter;
            }
            //0011
            if (voornaamEmpty && familienaamEmpty && !graadEmpty && !functieEmpty) {
                return graadFilter && functieFilter;
            }
            //0100
            if (voornaamEmpty && !familienaamEmpty && graadEmpty && functieEmpty) {
                return familieNaamFilter;
            }
            //0101
            if (voornaamEmpty && !familienaamEmpty && graadEmpty && !functieEmpty) {
                return familieNaamFilter && functieFilter;
            }
            //0110
            if (voornaamEmpty && !familienaamEmpty && !graadEmpty && functieEmpty) {
                return familieNaamFilter && graadFilter;
            }
            //0111
            if (voornaamEmpty && !familienaamEmpty && !graadEmpty && !functieEmpty) {
                return familieNaamFilter && graadFilter && functieFilter;
            }
            //1000
            if (!voornaamEmpty && familienaamEmpty && graadEmpty && functieEmpty) {
                return voornaamFilter;
            }
            //1001
            if (!voornaamEmpty && familienaamEmpty && graadEmpty && !functieEmpty) {
                return voornaamFilter && functieFilter;
            }
            //1010
            if (!voornaamEmpty && familienaamEmpty && !graadEmpty && functieEmpty) {
                return voornaamFilter && graadFilter;
            }
            //1011
            if (!voornaamEmpty && familienaamEmpty && !graadEmpty && !functieEmpty) {
                return voornaamFilter && graadFilter && functieFilter;
            }
            //1100
            if (!voornaamEmpty && !familienaamEmpty && graadEmpty && functieEmpty) {
                return voornaamFilter && familieNaamFilter;
            }
            //1101
            if (!voornaamEmpty && !familienaamEmpty && graadEmpty && !functieEmpty) {
                return voornaamFilter && familieNaamFilter && functieFilter;
            }
            //1110
            if (!voornaamEmpty && !familienaamEmpty && !graadEmpty && functieEmpty) {
                return voornaamFilter && familieNaamFilter && graadFilter;
            }
            //1111
            if (!voornaamEmpty && !familienaamEmpty && !graadEmpty && !functieEmpty) {
                return voornaamFilter && familieNaamFilter && graadFilter && functieFilter;
            }

            return true;

        });
    }

    //
    //CRUD-operaties
    //
    public void wijzigLid(Lid lid, String voornaam, String achternaam, LocalDate geboorteDatum, String rijksregisterNr,
            String gsmNr, String vasteTelefoonNr,
            String straat, String stad, String huisNr, String bus, String postcode, String email,
            String emailVader, String emailMoeder, String geboorteplaats, String wachtwoord, String nationaliteit,
            String beroep, Graad graad, Functie functie, String geslacht) {
        if (!lid.getRijksregisterNr().equals(rijksregisterNr)) {
            if (isRijksregisterNummerUniek(rijksregisterNr)) {
                lid.setVoornaam(voornaam);
                lid.setAchternaam(achternaam);
                lid.setGeboortedatum(geboorteDatum);
                lid.setGeboorteplaats(geboorteplaats);
                lid.setRijksregisterNr(rijksregisterNr);
                lid.setGeslacht(geslacht);
                lid.setNationaliteit(nationaliteit);
                lid.setGsmNr(gsmNr);
                lid.setVasteTelefoonNr(vasteTelefoonNr);
                lid.setStraat(straat);
                lid.setHuisNr(huisNr);
                lid.setBus(bus);
                lid.setStad(stad);
                lid.setPostcode(postcode);
                lid.setBeroep(beroep);
                lid.setEmail(email);
                lid.setEmailVader(emailVader);
                lid.setEmailMoeder(emailMoeder);
                lid.setGraad(graad);
                lid.setFunctie(functie);
                lid.setWachtwoord(wachtwoord);
            } else {
                throw new IllegalArgumentException("Rijksregisternummer is niet uniek.");
            }
        } else {
            lid.setVoornaam(voornaam);
            lid.setAchternaam(achternaam);
            lid.setGeboortedatum(geboorteDatum);
            lid.setGeboorteplaats(geboorteplaats);
            lid.setRijksregisterNr(rijksregisterNr);
            lid.setGeslacht(geslacht);
            lid.setNationaliteit(nationaliteit);
            lid.setGsmNr(gsmNr);
            lid.setVasteTelefoonNr(vasteTelefoonNr);
            lid.setStraat(straat);
            lid.setHuisNr(huisNr);
            lid.setBus(bus);
            lid.setStad(stad);
            lid.setPostcode(postcode);
            lid.setBeroep(beroep);
            lid.setEmail(email);
            lid.setEmailVader(emailVader);
            lid.setEmailMoeder(emailMoeder);
            lid.setGraad(graad);
            lid.setFunctie(functie);
            lid.setWachtwoord(wachtwoord);
        }
    }

    public void voegLidToe(String voornaam, String achternaam, LocalDate geboorteDatum, String rijksregisterNr,
            String gsmNr, String vasteTelefoonNr,
            String straat, String stad, String huisNr, String bus, String postcode, String email,
            String emailVader, String emailMoeder, String geboorteplaats, String wachtwoord, String nationaliteit,
            String beroep, Graad graad, Functie functie, String geslacht, Formule les) {
        Lid lid;
        if (isRijksregisterNummerUniek(rijksregisterNr)) {
            if (functie.equals(Functie.PROEFLID)) {
                lid = new Lid(voornaam, achternaam, geboorteDatum, rijksregisterNr, geslacht, functie);
            } else {
                lid = new Lid(voornaam, achternaam, geboorteDatum, rijksregisterNr,
                        gsmNr, vasteTelefoonNr, stad, straat, huisNr, postcode, email, wachtwoord,
                        geboorteplaats, geslacht, nationaliteit, graad, functie);
            }
            lid.setEmailMoeder(emailMoeder);
            lid.setEmailVader(emailVader);
            lid.setBus(bus);
            lid.setBeroep(beroep);

            //lid toevoegen aan lessenactiviteit
            if (les == null) {
                throw new IllegalArgumentException("Inschrijving les mag niet leeg zijn!");
            } else {
                Inschrijving inschrijving = new Inschrijving(les, lid, LocalDate.now());
                dataController.geefInschrijvingen().add(inschrijving);
                List<Activiteit> activiteiten = dataController
                        .geefActiviteiten()
                        .stream()
                        .filter(activiteit -> activiteit.getFormule().equals(les))
                        .collect(Collectors.toList());

                activiteiten.stream().forEach(activiteit -> {
                    activiteit.voegInschrijvingToe(inschrijving);
                    activiteit.setAantalDeelnemers();
                });
            }

            this.ledenList.add(lid);
            dataController.geefLeden().add(lid);
        } else {
            throw new IllegalArgumentException("Rijksregisternummer is niet uniek.");
        }
    }

    public void verwijderLid(Lid lid) {
        this.ledenList.remove(lid);
        dataController.geefLeden().remove(lid);
    }

    //Hulpmethode checken of rijksregisternr uniek is
    private boolean isRijksregisterNummerUniek(String rijksregisterNr) {
        return dataController.geefLeden()
                .stream()
                .filter(lid -> lid.getRijksregisterNr().replaceAll("\\.", "").replaceAll("-", "")
                .equals(rijksregisterNr.replaceAll("\\.", "").replaceAll("-", "")))
                .count() == 0;
    }

    //
    //ENUMS
    //
    public ObservableList<Formule> geefFormules() {
        ObservableList<Formule> formules = FXCollections.observableArrayList(dataController.geefFormules());
        return FXCollections.unmodifiableObservableList(formules);
    }

    public ObservableList<Functie> geefFuncties() {
        ObservableList<Functie> functies = FXCollections.observableArrayList(dataController.geefFuncties());
        return FXCollections.unmodifiableObservableList(functies);
    }

    public ObservableList<String> geefFunctiesFilter() {
        ObservableList<String> functies = FXCollections.observableArrayList(dataController
                .geefFuncties().stream().map(functie -> functie.name())
                .collect(Collectors.toList()));
        functies.add(0, "Alle types");
        return functies;
    }

    public ObservableList<Graad> geefGraden() {
        ObservableList<Graad> graden = FXCollections.observableArrayList(dataController.geefGraden());
        return graden;
    }

    public ObservableList<String> geefGradenFilter() {
        ObservableList<String> graden = FXCollections.observableArrayList(dataController.geefGraden()
                .stream().map(graad -> graad.name())
                .collect(Collectors.toList()));
        graden.add(0, "Alle graden");
        return graden;
    }

    public ObservableList<String> geefGeslachten() {
        ObservableList<String> geslachten = FXCollections.observableArrayList(dataController.geefGeslachten());
        return geslachten;
    }

    public ObservableList<Formule> geefLessen() {
        ObservableList<Formule> lessen
                = FXCollections.observableArrayList(
                        dataController
                                .geefFormules()
                                .stream()
                                .filter(formule -> formule.isLes())
                                .collect(Collectors.toList()));
        return lessen;

    }

    //
    //Observer
    //
    public void addObserver(ListChangeListener<Lid> listener) {
        ledenList.addListener(listener);
    }

    public void removeObserver(ListChangeListener<Lid> listener) {
        ledenList.removeListener(listener);
    }

}
