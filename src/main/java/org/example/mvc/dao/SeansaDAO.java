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
        String sql = """
          INSERT INTO seansa (
          psihoterapeut_id,
          klijent_id,
          datum,
          vreme,
          trajanje_min,
          beleske,
          pod_supervizijom,
          vodeci_korisnik
          ) VALUES (?, ?, ?, ?, ?, ?, ?, ?)
          """;
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, s.getPsihoterapeutId());
            ps.setInt(2, s.getKlijentId());
            ps.setDate(3, Date.valueOf(s.getDatum()));
            ps.setTime(4, Time.valueOf(s.getVreme()));
            ps.setInt(5, s.getTrajanje());
            ps.setString(6, s.getBeleske());
            ps.setInt(7, s.getPod_supervizijom());
            ps.setInt(8, s.getVodeci_korisnik());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) s.setId(rs.getInt(1));
            }
        }
    }

    public Seansa findById(int id) throws SQLException {
        String sql = "SELECT * FROM seansa WHERE seansa_id = ?";

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

    public List<Seansa> findAll() throws SQLException {
        String sql = "SELECT * FROM seansa";

        List<Seansa> list = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        }
        return list;
    }

    public void update(Seansa s) throws SQLException {
        String sql = """
          UPDATE seansa SET
          psihoterapeut_id = ?,
           klijent_id        = ?,
          datum             = ?,
          vreme             = ?,
          trajanje_min      = ?,
          beleske           = ?,
          pod_supervizijom  = ?,
          vodeci_korisnik   = ?
          WHERE seansa_id = ?
          """;


        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, s.getPsihoterapeutId());
            ps.setInt(2, s.getKlijentId());
            ps.setDate(3, Date.valueOf(s.getDatum()));
            ps.setTime(4, Time.valueOf(s.getVreme()));
            ps.setInt(5, s.getTrajanje());
            ps.setString(6, s.getBeleske());
            ps.setInt(7, s.getPod_supervizijom());
            ps.setInt(8, s.getVodeci_korisnik());
            ps.setInt(9, s.getId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM seansa WHERE seansa_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
    public List<Seansa> findUpcomingByTherapist(int therapistId) throws SQLException {
        String sql = """
                SELECT
                s.seansa_id    AS id,
                s.psihoterapeut_id    AS psihoterapeutId,
                CONCAT(k.ime,' ',k.prezime)      AS klijentFullName,
                CONCAT(p.ime,' ',p.prezime)      AS terapeutFullName,
                s.vodeci_korisnik         AS vodeci_korisnik,
                s.pod_supervizijom        AS pod_supervizijom,
                s.datum,
                s.vreme,
                s.trajanje_min            AS trajanje,
                s.beleske
                FROM seansa s
                    JOIN klijent k ON s.klijent_id = k.klijent_id
                    JOIN psihoterapeut p ON s.psihoterapeut_id = p.psihoterapeut_id
                WHERE s.psihoterapeut_id = ?
                AND s.datum >= CURDATE()
                ORDER BY s.datum DESC, s.vreme DESC;
                """;

        List<Seansa> list = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, therapistId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapRow(rs));
                }
            }
        }
        return list;
    }
    public List<Seansa> findPastByTherapist(int therapistId) throws SQLException {
        String sql = """
                SELECT
                s.seansa_id    AS id,
                s.psihoterapeut_id    AS psihoterapeutId,
                CONCAT(k.ime,' ',k.prezime)      AS klijentFullName,
                CONCAT(p.ime,' ',p.prezime)      AS terapeutFullName,
                s.vodeci_korisnik         AS vodeci_korisnik,
                s.pod_supervizijom        AS pod_supervizijom,
                s.datum,
                s.vreme,
                s.trajanje_min            AS trajanje,
                s.beleske
                FROM seansa s
                    JOIN klijent k ON s.klijent_id = k.klijent_id
                    JOIN psihoterapeut p ON s.psihoterapeut_id = p.psihoterapeut_id
                WHERE s.psihoterapeut_id = ?
                AND s.datum < CURDATE()
                ORDER BY s.datum DESC, s.vreme DESC;
                """;

        List<Seansa> list = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, therapistId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapRow(rs));
                }
            }
        }
        return list;
    }
    private Seansa mapRow(ResultSet rs) throws SQLException {
        Seansa s = new Seansa();
        s.setId(rs.getInt("id"));
        s.setTerapeutFullName(rs.getString("terapeutFullName"));
        s.setKlijentFullName(rs.getString("klijentFullName"));
        s.setDatum(rs.getDate("datum").toLocalDate());
        s.setVreme(rs.getTime("vreme").toLocalTime());
        s.setTrajanje(rs.getInt("trajanje"));
        s.setBeleske(rs.getString("beleske"));
        s.setPod_supervizijom(rs.getInt("pod_supervizijom"));
        s.setVodeci_korisnik(rs.getInt("vodeci_korisnik"));
        return s;
    }
}