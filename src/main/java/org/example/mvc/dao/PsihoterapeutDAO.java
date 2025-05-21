// src/main/java/org/example/mvc/dao/PsihoterapeutDAO.java
package org.example.mvc.dao;

import org.example.mvc.model.Psihoterapeut;
import org.example.mvc.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PsihoterapeutDAO {

    public void save(Psihoterapeut p) throws SQLException {
        String sql = "INSERT INTO psihoterapeut "
                + "(ime, prezime, jmbg, datum_rodjenja, prebivaliste, telefon, email, oblast_id, datum_sertifikacije, password) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, p.getIme());
            ps.setString(2, p.getPrezime());
            ps.setString(3, p.getJmbg());
            ps.setDate(4, Date.valueOf(p.getDatumRodjenja()));
            ps.setString(5, p.getPrebivaliste());
            ps.setString(6, p.getTelefon());
            ps.setString(7, p.getEmail());
            ps.setInt(8, p.getOblastId());
            ps.setDate(9, Date.valueOf(p.getDatumSertifikacije()));
            ps.setString(10, p.getPassword());

            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    p.setId(rs.getInt(1));
                }
            }
        }
    }

    public Psihoterapeut findById(int id) throws SQLException {
        String sql = "SELECT * FROM psihoterapeut WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        }
        return null;
    }

    public Psihoterapeut findByEmailAndPassword(String email, String password) throws SQLException {
        String sql = "SELECT * FROM psihoterapeut WHERE email = ? AND password = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        }
        return null;
    }

    public List<Psihoterapeut> findAll() throws SQLException {
        String sql = "SELECT * FROM psihoterapeut";
        List<Psihoterapeut> lista = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(mapRow(rs));
            }
        }
        return lista;
    }

    public void update(Psihoterapeut p) throws SQLException {
        String sql = "UPDATE psihoterapeut SET "
                + "ime = ?, prezime = ?, jmbg = ?, datum_rodjenja = ?, prebivaliste = ?, "
                + "telefon = ?, email = ?, oblast_id = ?, datum_sertifikacije = ?, password = ? "
                + "WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getIme());
            ps.setString(2, p.getPrezime());
            ps.setString(3, p.getJmbg());
            ps.setDate(4, Date.valueOf(p.getDatumRodjenja()));
            ps.setString(5, p.getPrebivaliste());
            ps.setString(6, p.getTelefon());
            ps.setString(7, p.getEmail());
            ps.setInt(8, p.getOblastId());
            ps.setDate(9, Date.valueOf(p.getDatumSertifikacije()));
            ps.setString(10, p.getPassword());
            ps.setInt(11, p.getId());

            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM psihoterapeut WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    private Psihoterapeut mapRow(ResultSet rs) throws SQLException {
        Psihoterapeut p = new Psihoterapeut();
        p.setId(rs.getInt("id"));
        p.setIme(rs.getString("ime"));
        p.setPrezime(rs.getString("prezime"));
        p.setJmbg(rs.getString("jmbg"));
        p.setDatumRodjenja(rs.getDate("datum_rodjenja").toLocalDate());
        p.setPrebivaliste(rs.getString("prebivaliste"));
        p.setTelefon(rs.getString("telefon"));
        p.setEmail(rs.getString("email"));
        p.setOblastId(rs.getInt("oblast_id"));
        p.setDatumSertifikacije(rs.getDate("datum_sertifikacije").toLocalDate());
        p.setPassword(rs.getString("password"));
        return p;
    }
}
