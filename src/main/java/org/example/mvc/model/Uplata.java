package org.example.mvc.model;


import java.math.BigDecimal;

public class Uplata {
    private int id;
    private int klijentId;
    private String svrha;
    private Integer rata;
    private int valuta_id;
    private String nacin; // gotovina ili kartica
    private BigDecimal iznos;
    private String datum;
    private int seansa_id;
    private String klijentFullName;
    private String valutaNaziv;

    public Uplata() {}

    public Uplata(int klijentId, String svrha, Integer rata,
                  int valuta_id, String nacin, BigDecimal iznos, String datum, int seansa_id) {
        this.klijentId = klijentId;
        this.svrha = svrha;
        this.rata = rata;
        this.valuta_id = valuta_id;
        this.nacin = nacin;
        this.iznos = iznos;
        this.datum = datum;
        this.seansa_id = seansa_id;
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
    public int getValuta_id() { return valuta_id; }
    public void setValuta_id(int valuta_id) {this.valuta_id = valuta_id;}
    public String getNacin() { return nacin; }
    public void setNacin(String nacin) { this.nacin = nacin; }
    public BigDecimal getIznos() { return iznos; }
    public void setIznos(BigDecimal iznos) { this.iznos = iznos; }
    public String getDatum() {return datum;}
    public void setDatum(String datum) {this.datum = datum;}
    public int getSeansa_id() {return seansa_id;}
    public void setSeansa_id(int seansa_id) {this.seansa_id = seansa_id;}
    public String getKlijentFullName() {
        return klijentFullName;
    }
    public void setKlijentFullName(String klijentFullName) {
        this.klijentFullName = klijentFullName;
    }

    public String getValutaNaziv() {
        return valutaNaziv;
    }
    public void setValutaNaziv(String valutaNaziv) {
        this.valutaNaziv = valutaNaziv;
    }
}

