package persistentie;

import domein.Aanwezigheid;
import domein.Activiteit;
import domein.Admin;
import domein.Inschrijving;
import domein.Lid;
import domein.Oefening;
import domein.Raadpleging;
import domein.Thema;
import domein.enums.Formule;
import domein.enums.Functie;
import domein.enums.Graad;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class PopulateDb {

    public static void main(String[] args) {

        //
        //LEDEN
        //
        EntityManager em = Persistence.createEntityManagerFactory("PROJ2_G20_JAVAPU").createEntityManager();
        em.getTransaction().begin();

        Lid lid1 = new Lid("Tim", "Geldof", LocalDate.of(1997, Month.JULY, 17),
                "97.07.17-001.23",
                "0479330959", "051303050", "Izegem", "Winkelhoekstraat",
                "52", "8870", "tim.geldof@outlook.com",
                "Wachtwoord", "Izegem", "Man",
                "Belg", Graad.DAN5, Functie.BEHEERDER);

        Lid lid2 = new Lid("Tybo", "Vanderstraeten", LocalDate.of(1999, Month.DECEMBER, 8),
                "99.12.08.173.04",
                "0479365887", "098556880", "Kortrijk", "Prinses Clementinalaan",
                "11", "9980", "tybo.vanderstraeten@outlook.com",
                "TomatoSoup", "Gent", "Man",
                "Belg", Graad.KYU3, Functie.BEHEERDER);

        Lid lid3 = new Lid("Mark", "Witthaker", LocalDate.of(1975, Month.JUNE, 6),
                "75.06.06-001.78",
                "0478365887", "018556880", "Gent", "Prinses Mandarijnalaan",
                "45", "9000", "mark.witthaker@outlook.com",
                "MyMusicSucks4", "Gent", "Man",
                "Belg", Graad.DAN1, Functie.LID);

        Lid lid4 = new Lid("Florian", "Landuyt", LocalDate.of(1995, Month.DECEMBER, 12),
                "95.12.12-001.69",
                "0479865887", "088556880", "Deinze", "Kerkstraat",
                "141", "8770", "florian.landuyt@outlook.com",
                "TurnenIsLeuk8", "Gent", "Man",
                "Belg", Graad.KYU3, Functie.LID);
        Lid lid5 = new Lid("Rob", "De Putter", LocalDate.of(1999, Month.MARCH, 12),
                "99.03.12-002.89",
                "0478899964", "054556880", "Waregem", "Schoolstraat",
                "110", "9600", "rob.deputter@hotmail.com",
                "TurnenIsLeuk8", "Gent", "Vrouw",
                "Belg", Graad.KYU5, Functie.LID);

        Lid lid6 = new Lid("Bert", "Janssens", LocalDate.of(1995, Month.MARCH, 20),
                "95.03.20-001.57",
                "0477452187", "056321478", "Kortrijk", "Briekstraat",
                "2", "4500", "bert.janssens@outlook.com",
                "MyPassword", "Gent", "Man",
                "Belg", Graad.DAN5, Functie.TRAINER);

        Lid lid7 = new Lid("Piet", "Pieters", LocalDate.of(1990, Month.NOVEMBER, 18),
                "90.11.18.001.68",
                "0476214589", "091264853", "Waregem", "Aalbeeksesteenweg",
                "58", "8740", "piet.pieters@skynet.be",
                "Balletjessoep", "Sint-Amandsberg", "Man",
                "Belg", Graad.KYU3, Functie.LID);

        Lid lid8 = new Lid("Keo", "Yunh", LocalDate.of(1998, Month.MAY, 16),
                "98.05.16-001.60",
                "0471235698", "092156358", "Gent", "Overpoortstraat",
                "5", "9000", "keo.yunh@outlook.com",
                "senpai8", "Ho-Chi-Minh", "Man",
                "Vietnamees", Graad.DAN1, Functie.LID);

        em.persist(lid1);
        em.persist(lid2);
        em.persist(lid3);
        em.persist(lid4);
        em.persist(lid5);
        em.persist(lid6);
        em.persist(lid7);
        em.persist(lid8);

        //
        //ADMINS
        //
        Admin tybo = new Admin("Tybo", "admin");
        Admin rob = new Admin("Rob", "admin");
        Admin florian = new Admin("Florian", "admin");
        Admin tim = new Admin("Tim", "admin");

        em.persist(tybo);
        em.persist(rob);
        em.persist(florian);
        em.persist(tim);
        //
        //ACTIVITEITEN
        //
        //--Stages
        Activiteit s1 = new Activiteit("Hoogtestage Ardennen", Formule.STAGE, 50, LocalDate.of(2020, Month.MARCH, 12),
                LocalDate.of(2020, Month.MARCH, 16), LocalDate.of(2020, Month.FEBRUARY, 20));
        s1.setNaamLocatie("D'arde");
        s1.setGsmnummer("0476456851");
        s1.setEmail("darde@ardennes.be");
        s1.setStad("Houyet");
        s1.setPostcode("6500");
        s1.setStraat("Rue de la Foret");
        s1.setHuisnummer("1");

        Activiteit s2 = new Activiteit("Hoogtestage Vogezen", Formule.STAGE, 50, LocalDate.of(2020, Month.AUGUST, 28),
                LocalDate.of(2020, Month.FEBRUARY, 20));
        s2.setNaamLocatie("The Voge");
        s2.setGsmnummer("0476456851");
        s2.setEmail("thevoge@vogueze.fr");
        s2.setStad("Vogueze");
        s2.setPostcode("4400");
        s2.setStraat("Vogezenstraat");
        s2.setHuisnummer("8");

        Activiteit s3 = new Activiteit("Driedaagse stage", Formule.STAGE, 100, LocalDate.of(2020, Month.SEPTEMBER, 3),
                LocalDate.of(2020, Month.SEPTEMBER, 5), LocalDate.of(2020, Month.AUGUST, 1));
        s3.setNaamLocatie("The Whale");
        s3.setGsmnummer("0476456851");
        s3.setEmail("thewhale@hotmail.be");
        s3.setStad("Gent");
        s3.setPostcode("9000");
        s3.setStraat("Sleepstraat");
        s3.setHuisnummer("18");

        //--Examen
        Activiteit e1 = new Activiteit("Ingangsexamen", Formule.EXAMEN, 35, LocalDate.of(2020, Month.JUNE, 20),
                LocalDate.of(2020, Month.JUNE, 19));
        e1.setNaamLocatie("Examencentrum");
        e1.setGsmnummer("0476456851");
        e1.setEmail("examencentrum@gmail.be");
        e1.setStad("Gent");
        e1.setPostcode("9000");
        e1.setStraat("Drongensestraat");
        e1.setHuisnummer("13");

        //--Proef
        Activiteit p1 = new Activiteit("Dan1-proef", Formule.PROEF, 15, LocalDate.of(2020, Month.JULY, 5),
                LocalDate.of(2020, Month.JULY, 3));
        p1.setNaamLocatie("Turnzaal D2");
        p1.setGsmnummer("0476456851");
        p1.setEmail("sporthaldrongen@gmail.be");
        p1.setStad("Gent");
        p1.setPostcode("9000");
        p1.setStraat("Drongensestraat");
        p1.setHuisnummer("13");

        //--Uitstap
        Activiteit u1 = new Activiteit("Teambuilding", Formule.UITSTAP, 40, LocalDate.of(2020, Month.NOVEMBER, 10),
                LocalDate.of(2020, Month.NOVEMBER, 13), LocalDate.of(2020, Month.NOVEMBER, 1));
        u1.setNaamLocatie("Teambuilds");
        u1.setGsmnummer("0476456851");
        u1.setEmail("teambuilds@gmail.be");
        u1.setStad("Kortrijk");
        u1.setPostcode("8000");
        u1.setStraat("Stationstraat");
        u1.setHuisnummer("72");

        //--Lessen
        Activiteit l1 = new Activiteit("Les 1", Formule.ZA, 20, LocalDate.of(2020, Month.MARCH, 23),
                LocalDate.of(2020, Month.FEBRUARY, 13));
        l1.setNaamLocatie("Turnzaal D2");
        l1.setGsmnummer("0476456851");
        l1.setEmail("sporthaldrongen@gmail.be");
        l1.setStad("Gent");
        l1.setPostcode("9000");
        l1.setStraat("Drongensestraat");
        l1.setHuisnummer("13");

        Activiteit l2 = new Activiteit("Les 2", Formule.WO_ZA, 27, LocalDate.of(2020, Month.MARCH, 30),
                LocalDate.of(2020, Month.FEBRUARY, 20));
        l2.setNaamLocatie("Turnzaal D2");
        l2.setGsmnummer("0476456851");
        l2.setEmail("sporthaldrongen@gmail.be");
        l2.setStad("Gent");
        l2.setPostcode("9000");
        l2.setStraat("Drongensestraat");
        l2.setHuisnummer("13");

        Activiteit l3 = new Activiteit("Les 3", Formule.DI_ZA, 19, LocalDate.of(2020, Month.MARCH, 31),
                LocalDate.of(2020, Month.FEBRUARY, 20));
        l3.setNaamLocatie("Turnzaal D2");
        l3.setGsmnummer("0476456851");
        l3.setEmail("sporthaldrongen@gmail.be");
        l3.setStad("Gent");
        l3.setPostcode("9000");
        l3.setStraat("Drongensestraat");
        l3.setHuisnummer("13");

        em.persist(s1);
        em.persist(s2);
        em.persist(s3);
        em.persist(e1);
        em.persist(p1);
        em.persist(u1);
        em.persist(l1);
        em.persist(l2);
        em.persist(l3);

        //
        //INSCHRIJVINGEN
        //
        //--Stages
        Inschrijving is1 = new Inschrijving(Formule.STAGE, lid1, LocalDate.now());
        Inschrijving is2 = new Inschrijving(Formule.STAGE, lid2, LocalDate.now());
        Inschrijving is3 = new Inschrijving(Formule.STAGE, lid3, LocalDate.now());

        //--Examen
        Inschrijving ie1 = new Inschrijving(Formule.EXAMEN, lid1, LocalDate.now());
        Inschrijving ie2 = new Inschrijving(Formule.EXAMEN, lid2, LocalDate.now());
        Inschrijving ie3 = new Inschrijving(Formule.EXAMEN, lid3, LocalDate.now());
        Inschrijving ie4 = new Inschrijving(Formule.EXAMEN, lid4, LocalDate.now());
        Inschrijving ie5 = new Inschrijving(Formule.EXAMEN, lid5, LocalDate.now());

        //--Proef
        Inschrijving ip1 = new Inschrijving(Formule.PROEF, lid1, LocalDate.now());
        Inschrijving ip2 = new Inschrijving(Formule.PROEF, lid2, LocalDate.now());
        Inschrijving ip3 = new Inschrijving(Formule.PROEF, lid3, LocalDate.now());
        Inschrijving ip4 = new Inschrijving(Formule.PROEF, lid4, LocalDate.now());
        Inschrijving ip5 = new Inschrijving(Formule.PROEF, lid5, LocalDate.now());
        Inschrijving ip6 = new Inschrijving(Formule.PROEF, lid7, LocalDate.now());

        //--Uitstap
        Inschrijving iu1 = new Inschrijving(Formule.UITSTAP, lid1, LocalDate.now());
        Inschrijving iu2 = new Inschrijving(Formule.UITSTAP, lid2, LocalDate.now());
        Inschrijving iu3 = new Inschrijving(Formule.UITSTAP, lid3, LocalDate.now());

        //--Lessen
        Inschrijving il1 = new Inschrijving(Formule.ZA, lid1, LocalDate.now());
        Inschrijving il2 = new Inschrijving(Formule.ZA, lid2, LocalDate.now());
        Inschrijving il3 = new Inschrijving(Formule.ZA, lid3, LocalDate.now());
        Inschrijving il4 = new Inschrijving(Formule.WO_ZA, lid4, LocalDate.now());
        Inschrijving il5 = new Inschrijving(Formule.DI_ZA, lid5, LocalDate.now());
        Inschrijving il6 = new Inschrijving(Formule.DI_ZA, lid7, LocalDate.now());
        Inschrijving il7 = new Inschrijving(Formule.DI_ZA, lid8, LocalDate.now());
        Inschrijving il8 = new Inschrijving(Formule.DI_ZA, lid1, LocalDate.now());
        Inschrijving il9 = new Inschrijving(Formule.DI_ZA, lid3, LocalDate.now());
        Inschrijving il10 = new Inschrijving(Formule.DI_ZA, lid7, LocalDate.now());

        em.persist(is1);
        em.persist(is2);
        em.persist(is3);
        em.persist(ie1);
        em.persist(ie2);
        em.persist(ie3);
        em.persist(ie4);
        em.persist(ie5);
        em.persist(ip1);
        em.persist(ip2);
        em.persist(ip3);
        em.persist(ip4);
        em.persist(ip5);
        em.persist(ip6);
        em.persist(iu1);
        em.persist(iu2);
        em.persist(iu3);
        em.persist(il1);
        em.persist(il2);
        em.persist(il3);
        em.persist(il4);
        em.persist(il5);
        em.persist(il6);
        em.persist(il7);
        em.persist(il8);
        em.persist(il9);
        em.persist(il10);

        s1.voegInschrijvingToe(is1);
        s1.voegInschrijvingToe(is2);
        s1.voegInschrijvingToe(is3);
        e1.voegInschrijvingToe(ie1);
        e1.voegInschrijvingToe(ie2);
        e1.voegInschrijvingToe(ie3);
        e1.voegInschrijvingToe(ie4);
        e1.voegInschrijvingToe(ie5);
        p1.voegInschrijvingToe(ip1);
        p1.voegInschrijvingToe(ip2);
        p1.voegInschrijvingToe(ip3);
        p1.voegInschrijvingToe(ip4);
        p1.voegInschrijvingToe(ip5);
        p1.voegInschrijvingToe(ip6);
        u1.voegInschrijvingToe(iu1);
        u1.voegInschrijvingToe(iu2);
        u1.voegInschrijvingToe(iu3);
        l1.voegInschrijvingToe(il1);
        l1.voegInschrijvingToe(il2);
        l1.voegInschrijvingToe(il3);
        l2.voegInschrijvingToe(il4);
        l3.voegInschrijvingToe(il5);
        l3.voegInschrijvingToe(il6);
        l3.voegInschrijvingToe(il7);
        l3.voegInschrijvingToe(il8);
        l3.voegInschrijvingToe(il9);
        l3.voegInschrijvingToe(il10);

        //
        //AANWEZIGHEDEN
        //
        Aanwezigheid a1 = new Aanwezigheid(lid1, s1);
        Aanwezigheid a2 = new Aanwezigheid(lid2, s1);
        Aanwezigheid a3 = new Aanwezigheid(lid3, s3);
        Aanwezigheid a4 = new Aanwezigheid(lid1, u1);
        Aanwezigheid a5 = new Aanwezigheid(lid2, u1);
        Aanwezigheid a6 = new Aanwezigheid(lid1, l1);
        Aanwezigheid a7 = new Aanwezigheid(lid1, l3);
        Aanwezigheid a8 = new Aanwezigheid(lid1, l2);
        Aanwezigheid a9 = new Aanwezigheid(lid5, l2);
        Aanwezigheid a10 = new Aanwezigheid(lid7, l2);
        Aanwezigheid a11 = new Aanwezigheid(lid8, l2);
        Aanwezigheid a12 = new Aanwezigheid(lid6, l3);
        Aanwezigheid a13 = new Aanwezigheid(lid6, l1);
        Aanwezigheid a14 = new Aanwezigheid(lid5, u1);
        Aanwezigheid a15 = new Aanwezigheid(lid7, e1);

        em.persist(a1);
        em.persist(a2);
        em.persist(a3);
        em.persist(a4);
        em.persist(a5);
        em.persist(a6);
        em.persist(a7);
        em.persist(a8);
        em.persist(a9);
        em.persist(a10);
        em.persist(a11);
        em.persist(a12);
        em.persist(a13);
        em.persist(a14);
        em.persist(a15);

        //
        //THEMA
        //
        Thema techniek = new Thema("Techniek");
        Thema snelheid = new Thema("Snelheid");
        Thema finesse = new Thema("Finesse");
        Thema verdediging = new Thema("Verdediging");
        Thema algemeen = new Thema("Algemeen");
        em.persist(techniek);
        em.persist(snelheid);
        em.persist(finesse);
        em.persist(verdediging);
        em.persist(algemeen);

        //
        //OEFENINGEN
        //
        Oefening oef1 = new Oefening("Schoppen", "www.youtube.com/schop", "schop.jpg",
                "maak een snelle voorwaartse beweging met je voet", Graad.KYU1, techniek);
        Oefening oef2 = new Oefening("Slaan", "www.youtube.com/sla", "sla.jpg",
                "maak een snelle voorwaartse beweging met je arm", Graad.KYU2, techniek);
        Oefening oef3 = new Oefening("Handsnelheid", "www.youtube.com/handsnelheid", "handsnelheid.jpg",
                "sla 10x in 10 seconden", Graad.DAN5, snelheid);
        Oefening oef4 = new Oefening("Voetsnelheid", "www.youtube.com/voetsnelheid", "voetsnelheid.jpg",
                "schop 10x in 10 seconden", Graad.KYU5, snelheid);
        Oefening oef5 = new Oefening("Draaisnelheid", "www.youtube.com/draaisnelheid", "draaisnelheid.jpg",
                "draai 5x in 5 seconden", Graad.KYU1, snelheid);
        Oefening oef6 = new Oefening("Salto", "www.youtube.com/salto", "salto.jpg",
                "land een salto perfect", Graad.KYU4, finesse);
        Oefening oef7 = new Oefening("Koprol", "www.youtube.com/koprol", "koprol.jpg",
                "doe een perfecte koprol", Graad.KYU5, finesse);
        Oefening oef8 = new Oefening("Afblokken", "www.youtube.com/afblokken", "afblokken.jpg",
                "blok de aanval van uw tegenstander af", Graad.DAN3, verdediging);
        Oefening oef9 = new Oefening("Lopen", "www.youtube.com/lopen", "lopen.jpg",
                "loop 2km", Graad.DAN4, algemeen);

        em.persist(oef1);
        em.persist(oef2);
        em.persist(oef3);
        em.persist(oef4);
        em.persist(oef5);
        em.persist(oef6);
        em.persist(oef7);
        em.persist(oef8);
        em.persist(oef9);

        //
        //RAADPLEGINGEN
        //
        Raadpleging r1 = new Raadpleging(lid1, oef1);
        Raadpleging r2 = new Raadpleging(lid1, oef2);
        Raadpleging r3 = new Raadpleging(lid1, oef3);
        Raadpleging r4 = new Raadpleging(lid1, oef4);
        Raadpleging r5 = new Raadpleging(lid2, oef1);
        Raadpleging r6 = new Raadpleging(lid2, oef2);
        Raadpleging r7 = new Raadpleging(lid2, oef5);
        Raadpleging r8 = new Raadpleging(lid3, oef6);
        Raadpleging r9 = new Raadpleging(lid3, oef7);
        Raadpleging r10 = new Raadpleging(lid3, oef8);
        Raadpleging r11 = new Raadpleging(lid4, oef9);
        Raadpleging r12 = new Raadpleging(lid4, oef8);
        Raadpleging r13 = new Raadpleging(lid5, oef7);
        Raadpleging r14 = new Raadpleging(lid5, oef5);
        Raadpleging r15 = new Raadpleging(lid5, oef4);
        Raadpleging r16 = new Raadpleging(lid5, oef3);
        Raadpleging r17 = new Raadpleging(lid5, oef2);
        Raadpleging r18 = new Raadpleging(lid7, oef1);
        Raadpleging r19 = new Raadpleging(lid7, oef2);
        Raadpleging r20 = new Raadpleging(lid7, oef3);
        Raadpleging r21 = new Raadpleging(lid8, oef1);
        Raadpleging r22 = new Raadpleging(lid8, oef4);
        Raadpleging r23 = new Raadpleging(lid8, oef9);
        Raadpleging r24 = new Raadpleging(lid8, oef8);

        em.persist(r1);
        em.persist(r2);
        em.persist(r3);
        em.persist(r4);
        em.persist(r5);
        em.persist(r6);
        em.persist(r7);
        em.persist(r8);
        em.persist(r9);
        em.persist(r10);
        em.persist(r11);
        em.persist(r12);
        em.persist(r13);
        em.persist(r14);
        em.persist(r15);
        em.persist(r16);
        em.persist(r17);
        em.persist(r18);
        em.persist(r19);
        em.persist(r20);
        em.persist(r21);
        em.persist(r22);
        em.persist(r23);
        em.persist(r24);

        for (int i = 0; i < new Random().nextInt(25); i++) {
            r1.verhoogAantalRaadplegingen();
        }
        for (int i = 0; i < new Random().nextInt(25); i++) {
            r2.verhoogAantalRaadplegingen();
        }
        for (int i = 0; i < new Random().nextInt(25); i++) {
            r3.verhoogAantalRaadplegingen();
        }
        for (int i = 0; i < new Random().nextInt(25); i++) {
            r4.verhoogAantalRaadplegingen();
        }
        for (int i = 0; i < new Random().nextInt(25); i++) {
            r5.verhoogAantalRaadplegingen();
        }
        for (int i = 0; i < new Random().nextInt(25); i++) {
            r6.verhoogAantalRaadplegingen();
        }
        for (int i = 0; i < new Random().nextInt(25); i++) {
            r7.verhoogAantalRaadplegingen();
        }
        for (int i = 0; i < new Random().nextInt(25); i++) {
            r8.verhoogAantalRaadplegingen();
        }
        for (int i = 0; i < new Random().nextInt(25); i++) {
            r9.verhoogAantalRaadplegingen();
        }
        for (int i = 0; i < new Random().nextInt(25); i++) {
            r10.verhoogAantalRaadplegingen();
        }
        for (int i = 0; i < new Random().nextInt(25); i++) {
            r11.verhoogAantalRaadplegingen();
        }
        for (int i = 0; i < new Random().nextInt(25); i++) {
            r12.verhoogAantalRaadplegingen();
        }
        for (int i = 0; i < new Random().nextInt(25); i++) {
            r13.verhoogAantalRaadplegingen();
        }
        for (int i = 0; i < new Random().nextInt(25); i++) {
            r14.verhoogAantalRaadplegingen();
        }
        for (int i = 0; i < new Random().nextInt(25); i++) {
            r15.verhoogAantalRaadplegingen();
        }
        for (int i = 0; i < new Random().nextInt(25); i++) {
            r16.verhoogAantalRaadplegingen();
        }
        for (int i = 0; i < new Random().nextInt(25); i++) {
            r17.verhoogAantalRaadplegingen();
        }
        for (int i = 0; i < new Random().nextInt(25); i++) {
            r18.verhoogAantalRaadplegingen();
        }
        for (int i = 0; i < new Random().nextInt(25); i++) {
            r19.verhoogAantalRaadplegingen();
        }
        for (int i = 0; i < new Random().nextInt(25); i++) {
            r20.verhoogAantalRaadplegingen();
        }
        for (int i = 0; i < new Random().nextInt(25); i++) {
            r21.verhoogAantalRaadplegingen();
        }
        for (int i = 0; i < new Random().nextInt(25); i++) {
            r22.verhoogAantalRaadplegingen();
        }
        for (int i = 0; i < new Random().nextInt(25); i++) {
            r23.verhoogAantalRaadplegingen();
        }
        for (int i = 0; i < new Random().nextInt(25); i++) {
            r24.verhoogAantalRaadplegingen();
        }
        em.getTransaction().commit();
        em.close();
    }
}
