package org.example.mvc.dao;




import org.example.mvc.model.Uplata;
import org.example.mvc.util.DBUtil;

import java.sql.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class UplataDAO {
    public void save(Uplata u) throws SQLException {
        String sql = "INSERT INTO placanje (klijent_id, svrha, rata, valuta, nacin, iznos) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, u.getKlijentId());
            ps.setString(2, u.getSvrha());
            if (u.getRata() != null) ps.setInt(3, u.getRata()); else ps.setNull(3, Types.INTEGER);
            ps.setInt(4, u.getValuta_id());
            ps.setString(5, u.getNacin());
            ps.setBigDecimal(6, u.getIznos());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) { if (rs.next()) u.setId(rs.getInt(1)); }
        }
    }

    public Uplata findById(int id) throws SQLException {
        String sql = "SELECT * FROM placanje WHERE id = ?";
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
                    u.setValuta_id(rs.getInt("valuta_id"));
                    u.setNacin(rs.getString("nacin"));
                    u.setIznos(rs.getBigDecimal("iznos"));
                    return u;
                }
            }
        }
        return null;
    }
    public List<Uplata> findAll() throws SQLException {
        String sql = """
        SELECT
          p.placanje_id   AS id,
          p.klijent_id,
          k.ime            AS kime,
          k.prezime        AS kprezime,
          p.svrha,
          p.rata,
          p.valuta_id,
          v.puni_naziv          AS vnaziv,
          p.nacin_placanja AS nacin,
          p.iznos,
          p.datum,
          p.seansa_id
        FROM placanje p
        JOIN klijent k ON p.klijent_id = k.klijent_id
        JOIN valuta  v ON p.valuta_id  = v.valuta_id
        ORDER BY p.datum DESC, p.placanje_id
        """;

        List<Uplata> list = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Uplata u = new Uplata();
                u.setId(rs.getInt("id"));
                u.setKlijentId(rs.getInt("klijent_id"));
                // склапамо пуно име
                String fullName = rs.getString("kime") + " " + rs.getString("kprezime");
                u.setKlijentFullName(fullName);

                u.setSvrha(rs.getString("svrha"));
                int r = rs.getInt("rata");
                if (!rs.wasNull()) u.setRata(r);

                u.setValuta_id(rs.getInt("valuta_id"));
                u.setValutaNaziv(rs.getString("vnaziv"));

                u.setNacin(rs.getString("nacin"));
                u.setIznos(rs.getBigDecimal("iznos"));
                u.setDatum(rs.getString("datum"));
                u.setSeansa_id(rs.getInt("seansa_id"));

                list.add(u);
            }
        }
        return list;
    }


    public void update(Uplata u) throws SQLException {
        String sql = "UPDATE placanje SET klijent_id=?, svrha=?, rata=?, valuta=?, nacin=?, iznos=? WHERE id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, u.getKlijentId());
            ps.setString(2, u.getSvrha());
            if (u.getRata() != null) ps.setInt(3, u.getRata()); else ps.setNull(3, Types.INTEGER);
            ps.setInt(4, u.getValuta_id());
            ps.setString(5, u.getNacin());
            ps.setBigDecimal(6, u.getIznos());
            ps.setInt(7, u.getId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM placanje WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}

