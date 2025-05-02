package org.example.mvc.dao;




import org.example.mvc.model.Uplata;
import org.example.mvc.util.DBUtil;

import java.sql.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class UplataDAO {
    public void save(Uplata u) throws SQLException {
        String sql = "INSERT INTO uplata (klijent_id, svrha, rata, valuta, nacin, iznos) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, u.getKlijentId());
            ps.setString(2, u.getSvrha());
            if (u.getRata() != null) ps.setInt(3, u.getRata()); else ps.setNull(3, Types.INTEGER);
            ps.setString(4, u.getValuta());
            ps.setString(5, u.getNacin());
            ps.setBigDecimal(6, u.getIznos());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) { if (rs.next()) u.setId(rs.getInt(1)); }
        }
    }

    public Uplata findById(int id) throws SQLException {
        String sql = "SELECT * FROM uplata WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Uplata u = new Uplata();
                    u.setId(rs.getInt("id"));
                    u.setKlijentId(rs.getInt("klijent_id"));
                    u.setSvrha(rs.getString("svrha"));
                    int r = rs.getInt("rata"); if (!rs.wasNull()) u.setRata(r);
                    u.setValuta(rs.getString("valuta"));
                    u.setNacin(rs.getString("nacin"));
                    u.setIznos(rs.getBigDecimal("iznos"));
                    return u;
                }
            }
        }
        return null;
    }

    public List<Uplata> findAll() throws SQLException {
        String sql = "SELECT * FROM uplata";
        List<Uplata> list = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Uplata u = new Uplata();
                u.setId(rs.getInt("id"));
                u.setKlijentId(rs.getInt("klijent_id"));
                u.setSvrha(rs.getString("svrha"));
                int r2 = rs.getInt("rata"); if (!rs.wasNull()) u.setRata(r2);
                u.setValuta(rs.getString("valuta"));
                u.setNacin(rs.getString("nacin"));
                u.setIznos(rs.getBigDecimal("iznos"));
                list.add(u);
            }
        }
        return list;
    }

    public void update(Uplata u) throws SQLException {
        String sql = "UPDATE uplata SET klijent_id=?, svrha=?, rata=?, valuta=?, nacin=?, iznos=? WHERE id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, u.getKlijentId());
            ps.setString(2, u.getSvrha());
            if (u.getRata() != null) ps.setInt(3, u.getRata()); else ps.setNull(3, Types.INTEGER);
            ps.setString(4, u.getValuta());
            ps.setString(5, u.getNacin());
            ps.setBigDecimal(6, u.getIznos());
            ps.setInt(7, u.getId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM uplata WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}

