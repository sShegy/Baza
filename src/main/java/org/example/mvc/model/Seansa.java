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
    private String beleške;

    public Seansa() {}

    public Seansa(int psihoterapeutId, int klijentId, LocalDate datum,
                  LocalTime vreme, int trajanje, String beleške) {
        this.psihoterapeutId = psihoterapeutId;
        this.klijentId = klijentId;
        this.datum = datum;
        this.vreme = vreme;
        this.trajanje = trajanje;
        this.beleške = beleške;
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
    public String getBeleške() { return beleške; }
    public void setBeleške(String beleške) { this.beleške = beleške; }
}

