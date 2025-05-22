package org.example.mvc.dao;



import org.example.mvc.model.Klijent;
import org.example.mvc.util.DBUtil;


import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class KlijentDAO {
    public void save(Klijent k) throws SQLException {
        String sql = "INSERT INTO klijent (ime, prezime, jmbg, datum_rodjenja, prebivaliste, telefon, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, k.getIme());
            ps.setString(2, k.getPrezime());
            ps.setString(3, k.getDatumRodjenja());
            ps.setString(4, k.getPol());
            ps.setString(5, k.getTelefon());
            ps.setString(6, k.getEmail());
            ps.setInt(7, k.getImao_psihoterapiju());
            ps.setString(8, k.getOpis_problema());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    k.setId(rs.getInt(1));
                }
            }
        }
    }

    public Klijent findById(int id) throws SQLException {
        String sql = "SELECT * FROM klijent WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Klijent k = new Klijent();
                    k.setId(rs.getInt("id"));
                    k.setIme(rs.getString("ime"));
                    k.setPrezime(rs.getString("prezime"));
                    k.setDatumRodjenja(rs.getString("datum_rodjenja"));
                    k.setPol(rs.getString("pol"));
                    k.setTelefon(rs.getString("telefon"));
                    k.setEmail(rs.getString("email"));
                    k.setImao_psihoterapiju(rs.getInt("imeo_psihoterapiju"));
                    k.setOpis_problema(rs.getString("opis_problema"));
                    return k;
                }
            }
        }
        return null;
    }

    public List<Klijent> findAll() throws SQLException {
        String sql = "SELECT * FROM klijent";
        List<Klijent> lista = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Klijent k = new Klijent();
                k.setId(rs.getInt("klijent_id"));
                k.setIme(rs.getString("ime"));
                k.setPrezime(rs.getString("prezime"));
                k.setDatumRodjenja(rs.getString("datum_rodjenja"));
                k.setPol(rs.getString("pol"));
                k.setTelefon(rs.getString("telefon"));
                k.setEmail(rs.getString("email"));
                k.setImao_psihoterapiju(rs.getInt("imao_psihoterapiju"));
                k.setOpis_problema(rs.getString("opis_problema"));
                lista.add(k);
            }
        }
        return lista;
    }
    public void update(Klijent k) throws SQLException {
        String sql = "UPDATE klijent SET ime = ?, prezime = ?, jmbg = ?, datum_rodjenja = ?, prebivaliste = ?, telefon = ?, email = ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, k.getIme());
            ps.setString(2, k.getPrezime());
            ps.setString(3, k.getJmbg());
            ps.setString(4, k.getDatumRodjenja());
            ps.setString(6, k.getTelefon());
            ps.setString(7, k.getEmail());
            ps.setInt(8, k.getId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM klijent WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}

