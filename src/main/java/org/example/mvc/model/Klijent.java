package org.example.mvc.model;


import java.time.LocalDate;

public class Klijent {
    private int id;
    private String ime;
    private String prezime;
    private String pol;
    private String datumRodjenja;
    private String telefon;
    private String email;
    private int imao_psihoterapiju;
    private String opis_problema;

    public Klijent() {}

    public Klijent(String ime, String prezime, String pol, String datumRodjenja,
                   String prebivaliste, String telefon, String email) {
        this.ime = ime;
        this.prezime = prezime;
        this.pol = pol;
        this.datumRodjenja = datumRodjenja;
        this.telefon = telefon;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getJmbg() {
        return pol;
    }

    public void setJmbg(String jmbg) {
        this.pol = pol;
    }

    public String getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(String datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPol() {return pol;}

    public void setPol(String pol) {this.pol = pol;}

    public int getImao_psihoterapiju() {return imao_psihoterapiju;}

    public void setImao_psihoterapiju(int imao_psihoterapiju) {this.imao_psihoterapiju = imao_psihoterapiju;}

    public String getOpis_problema() {return opis_problema;}

    public void setOpis_problema(String opis_problema) {this.opis_problema = opis_problema;}
}
