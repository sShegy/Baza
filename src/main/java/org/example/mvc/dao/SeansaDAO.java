package org.example.mvc.dao;




import org.example.mvc.model.Seansa;
import org.example.mvc.util.DBUtil;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SeansaDAO {
    public void save(Seansa s) throws SQLException {
        String sql = "INSERT INTO seansa (psihoterapeut_id, klijent_id, datum, vreme, trajanje, beleške) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, s.getPsihoterapeutId());
            ps.setInt(2, s.getKlijentId());
            ps.setDate(3, Date.valueOf(s.getDatum()));
            ps.setTime(4, Time.valueOf(s.getVreme()));
            ps.setInt(5, s.getTrajanje());
            ps.setString(6, s.getBeleške());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) s.setId(rs.getInt(1));
            }
        }
    }

    public Seansa findById(int id) throws SQLException {
        String sql = "SELECT * FROM seansa WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Seansa s = new Seansa();
                    s.setId(rs.getInt("id"));
                    s.setPsihoterapeutId(rs.getInt("psihoterapeut_id"));
                    s.setKlijentId(rs.getInt("klijent_id"));
                    s.setDatum(rs.getDate("datum").toLocalDate());
                    s.setVreme(rs.getTime("vreme").toLocalTime());
                    s.setTrajanje(rs.getInt("trajanje"));
                    s.setBeleške(rs.getString("beleške"));
                    return s;
                }
            }
        }
        return null;
    }

    public List<Seansa> findAll() throws SQLException {
        String sql = "SELECT * FROM seansa";
        List<Seansa> list = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Seansa s = new Seansa();
                s.setId(rs.getInt("id"));
                s.setPsihoterapeutId(rs.getInt("psihoterapeut_id"));
                s.setKlijentId(rs.getInt("klijent_id"));
                s.setDatum(rs.getDate("datum").toLocalDate());
                s.setVreme(rs.getTime("vreme").toLocalTime());
                s.setTrajanje(rs.getInt("trajanje"));
                s.setBeleške(rs.getString("beleške"));
                list.add(s);
            }
        }
        return list;
    }

    public void update(Seansa s) throws SQLException {
        String sql = "UPDATE seansa SET psihoterapeut_id=?, klijent_id=?, datum=?, vreme=?, trajanje=?, beleške=? WHERE id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, s.getPsihoterapeutId());
            ps.setInt(2, s.getKlijentId());
            ps.setDate(3, Date.valueOf(s.getDatum()));
            ps.setTime(4, Time.valueOf(s.getVreme()));
            ps.setInt(5, s.getTrajanje());
            ps.setString(6, s.getBeleške());
            ps.setInt(7, s.getId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM seansa WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}