package org.example.mvc.model;


import java.time.LocalDate;
import java.time.LocalTime;

public class Seansa {
    private int id;
    private int psihoterapeutId;
    private int klijentId;
    private LocalDate datum;
    private LocalTime vreme;
    private int trajanje; // u minutima
    private String beleske;
    private int vodeci_korisnik;
    private int pod_supervizijom;

    public Seansa() {}

    public Seansa(int psihoterapeutId, int klijentId, LocalDate datum,
                  LocalTime vreme, int trajanje, String beleske, int vodeci_korisnik, int pod_supervizijom) {
        this.psihoterapeutId = psihoterapeutId;
        this.klijentId = klijentId;
        this.datum = datum;
        this.vreme = vreme;
        this.trajanje = trajanje;
        this.beleske = beleske;
        this.vodeci_korisnik = vodeci_korisnik;
        this.pod_supervizijom = pod_supervizijom;
    }

    // getters i setters...
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getPsihoterapeutId() { return psihoterapeutId; }
    public void setPsihoterapeutId(int psihoterapeutId) { this.psihoterapeutId = psihoterapeutId; }
    public int getKlijentId() { return klijentId; }
    public void setKlijentId(int klijentId) { this.klijentId = klijentId; }
    public LocalDate getDatum() { return datum; }
    public void setDatum(LocalDate datum) { this.datum = datum; }
    public LocalTime getVreme() { return vreme; }
    public void setVreme(LocalTime vreme) { this.vreme = vreme; }
    public int getTrajanje() { return trajanje; }
    public void setTrajanje(int trajanje) { this.trajanje = trajanje; }
    public String getBeleske() { return beleske; }
    public void setBeleske(String beleske) { this.beleske = beleske; }
    public int getVodeci_korisnik() { return vodeci_korisnik; }
    public int getPod_supervizijom() {return pod_supervizijom;}
    public void setPod_supervizijom(int pod_supervizijom) {this.pod_supervizijom = pod_supervizijom;}
    public void setVodeci_korisnik(int vodeci_korisnik) {this.vodeci_korisnik = vodeci_korisnik;}
}

