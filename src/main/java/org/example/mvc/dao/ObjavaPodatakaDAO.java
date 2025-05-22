package org.example.mvc.dao;




import org.example.mvc.model.ObjavaPodataka;
import org.example.mvc.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ObjavaPodatakaDAO {
    public void save(ObjavaPodataka o) throws SQLException {
        String sql = "INSERT INTO objava_podataka (seansa_id, primaoc, datum, razlog) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, o.getSeansaId());
            ps.setString(2, o.getPrimalac());
            ps.setDate(3, Date.valueOf(o.getDatumObjave()));
            ps.setString(4, o.getRazlog());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    o.setId(rs.getInt(1));
                }
            }
        }
    }

    public ObjavaPodataka findById(int id) throws SQLException {
        String sql = "SELECT * FROM objava_podataka WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ObjavaPodataka o = new ObjavaPodataka();
                    o.setId(rs.getInt("id"));
                    o.setSeansaId(rs.getInt("seansa_id"));
                    o.setPrimalac(rs.getString("primaoc"));
                    o.setDatumObjave(rs.getDate("datum").toLocalDate());
                    o.setRazlog(rs.getString("razlog"));
                    return o;
                }
            }
        }
        return null;
    }

    public List<ObjavaPodataka> findBySeansa(int seansaId) throws SQLException {
        String sql = "SELECT * FROM objava_podataka WHERE seansa_id = ?";
        List<ObjavaPodataka> list = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, seansaId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ObjavaPodataka o = new ObjavaPodataka();
                    o.setId(rs.getInt("id_objava"));
                    o.setSeansaId(rs.getInt("seansa_id"));
                    o.setDatumObjave(rs.getDate("datum").toLocalDate());
                    o.setRazlog(rs.getString("razlog"));
                    o.setPrimalac(rs.getString("primaoc"));
                    list.add(o);
                }
            }
        }
        return list;
    }

}

