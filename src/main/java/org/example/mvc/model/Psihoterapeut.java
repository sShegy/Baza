package org.example.mvc.model;

import java.time.LocalDate;

public class Psihoterapeut {
    private int id;
    private String ime;
    private String prezime;
    private String jmbg;
    private LocalDate datumRodjenja;
    private String prebivaliste;
    private String telefon;
    private String email;
    private int oblastId;
    private LocalDate datumSertifikacije;
    private String password;
    private String oblastNaziv;

    public Psihoterapeut() {}

    public Psihoterapeut(String ime, String prezime, String jmbg, LocalDate datumRodjenja,
                         String prebivaliste, String telefon, String email,
                         int oblastId, LocalDate datumSertifikacije, String password) {
        this.ime = ime;
        this.prezime = prezime;
        this.jmbg = jmbg;
        this.datumRodjenja = datumRodjenja;
        this.prebivaliste = prebivaliste;
        this.telefon = telefon;
        this.email = email;
        this.oblastId = oblastId;
        this.datumSertifikacije = datumSertifikacije;
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJmbg() {
        return jmbg;
    }

    public LocalDate getDatumRodjenja() {
        return datumRodjenja;
    }

    public String getPrebivaliste() {
        return prebivaliste;
    }

    public String getTelefon() {
        return telefon;
    }

    public int getOblastId() {
        return oblastId;
    }

    public LocalDate getDatumSertifikacije() {
        return datumSertifikacije;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public void setDatumRodjenja(LocalDate datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public void setPrebivaliste(String prebivaliste) {
        this.prebivaliste = prebivaliste;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public void setOblastId(int oblastId) {
        this.oblastId = oblastId;
    }

    public void setDatumSertifikacije(LocalDate datumSertifikacije) {
        this.datumSertifikacije = datumSertifikacije;
    }

    public String getOblastNaziv() {return oblastNaziv;}

    public void setOblastNaziv(String oblastNaziv) {this.oblastNaziv = oblastNaziv;}
}
