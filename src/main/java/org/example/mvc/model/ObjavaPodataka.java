package org.example.mvc.model;


import java.time.LocalDate;

public class ObjavaPodataka {
    private int id;
    private int seansaId;
    private String primalac;
    private LocalDate datumObjave;
    private String razlog;

    public ObjavaPodataka() {}

    public ObjavaPodataka(int seansaId, String primalac, LocalDate datumObjave, String razlog) {
        this.seansaId = seansaId;
        this.primalac = primalac;
        this.datumObjave = datumObjave;
        this.razlog = razlog;
    }

    // getters i setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeansaId() {
        return seansaId;
    }

    public void setSeansaId(int seansaId) {
        this.seansaId = seansaId;
    }

    public String getPrimalac() {
        return primalac;
    }

    public void setPrimalac(String primalac) {
        this.primalac = primalac;
    }

    public LocalDate getDatumObjave() {
        return datumObjave;
    }

    public void setDatumObjave(LocalDate datumObjave) {
        this.datumObjave = datumObjave;
    }

    public String getRazlog() {
        return razlog;
    }

    public void setRazlog(String razlog) {
        this.razlog = razlog;
    }
}
