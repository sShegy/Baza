package org.example.mvc.model;


import java.math.BigDecimal;

public class Uplata {
    private int id;
    private int klijentId;
    private String svrha;
    private Integer rata;
    private String valuta;
    private String nacin; // gotovina ili kartica
    private BigDecimal iznos;

    public Uplata() {}

    public Uplata(int klijentId, String svrha, Integer rata,
                  String valuta, String nacin, BigDecimal iznos) {
        this.klijentId = klijentId;
        this.svrha = svrha;
        this.rata = rata;
        this.valuta = valuta;
        this.nacin = nacin;
        this.iznos = iznos;
    }

    // getters i setters...
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getKlijentId() { return klijentId; }
    public void setKlijentId(int klijentId) { this.klijentId = klijentId; }
    public String getSvrha() { return svrha; }
    public void setSvrha(String svrha) { this.svrha = svrha; }
    public Integer getRata() { return rata; }
    public void setRata(Integer rata) { this.rata = rata; }
    public String getValuta() { return valuta; }
    public void setValuta(String valuta) { this.valuta = valuta; }
    public String getNacin() { return nacin; }
    public void setNacin(String nacin) { this.nacin = nacin; }
    public BigDecimal getIznos() { return iznos; }
    public void setIznos(BigDecimal iznos) { this.iznos = iznos; }
}

