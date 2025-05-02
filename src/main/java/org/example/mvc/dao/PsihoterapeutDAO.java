package org.example.mvc.dao;



import org.example.mvc.model.Psihoterapeut;
import org.example.mvc.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PsihoterapeutDAO {
    public void save(Psihoterapeut p) throws SQLException {
        String sql = "INSERT INTO psihoterapeut (ime, prezime, email, lozinka) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, p.getIme());
            ps.setString(2, p.getPrezime());
            ps.setString(3, p.getEmail());
            ps.setString(4, p.getLozinka());
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
                    Psihoterapeut p = new Psihoterapeut();
                    p.setId(rs.getInt("id"));
                    p.setIme(rs.getString("ime"));
                    p.setPrezime(rs.getString("prezime"));
                    p.setEmail(rs.getString("email"));
                    p.setLozinka(rs.getString("lozinka"));
                    return p;
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
                Psihoterapeut p = new Psihoterapeut();
                p.setId(rs.getInt("id"));
                p.setIme(rs.getString("ime"));
                p.setPrezime(rs.getString("prezime"));
                p.setEmail(rs.getString("email"));
                p.setLozinka(rs.getString("lozinka"));
                lista.add(p);
            }
        }
        return lista;
    }

    public void update(Psihoterapeut p) throws SQLException {
        String sql = "UPDATE psihoterapeut SET ime = ?, prezime = ?, email = ?, lozinka = ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getIme());
            ps.setString(2, p.getPrezime());
            ps.setString(3, p.getEmail());
            ps.setString(4, p.getLozinka());
            ps.setInt(5, p.getId());
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
    public Psihoterapeut findByEmailAndPassword(String email, String lozinka) throws SQLException {
        String sql = "SELECT * FROM psihoterapeut WHERE email = ? AND lozinka = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, lozinka);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Psihoterapeut p = new Psihoterapeut();
                    p.setId(rs.getInt("id"));
                    p.setIme(rs.getString("ime"));
                    p.setPrezime(rs.getString("prezime"));
                    p.setEmail(rs.getString("email"));
                    p.setLozinka(rs.getString("lozinka"));
                    return p;
                }
            }
        }
        return null;
    }
}

