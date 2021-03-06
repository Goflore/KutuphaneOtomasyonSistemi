package Kitap;

import Kitap.*;

import KutuphaneOtomasyon.DbHelper;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JComboBox;

public class KitapIslemler implements IKitapIslemler {

    @Override
    public void Ekle(Kitap kitap) {
        Connection connection = null;
        DbHelper DbHelper = new DbHelper();
        PreparedStatement statement = null;
        try {
            connection = DbHelper.getConnection();
            String sql = "insert into kitap (kitap_adi, kitap_yazari, kitap_yayinevi,kitap_turu,kitap_barkod,kitap_rafno) values(?,?,?,?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, kitap.getAd());
            statement.setString(2, kitap.getYazar());
            statement.setString(3, kitap.getYayinevi());
            statement.setString(4, kitap.getTur());
            statement.setString(5, kitap.getBarkod());
            statement.setString(6, kitap.getRafNo());
            int result = statement.executeUpdate();
        } catch (SQLException exception) {
            DbHelper.showErrorMessage(exception);
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException ex) {

            }
        }
    }

    @Override
    public void Sil(int id) {
        Connection connection = null;
        DbHelper DbHelper = new DbHelper();
        PreparedStatement statement = null;
        try {
            connection = DbHelper.getConnection();
            String sql = "DELETE FROM kitap WHERE kitap_id = " + id;
            statement = connection.prepareStatement(sql);
            int result = statement.executeUpdate();
        } catch (SQLException exception) {
            DbHelper.showErrorMessage(exception);
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException ex) {

            }
        }
    }

    @Override
    public void Güncelle(Kitap kitap, int id) {

        Connection connection = null;
        DbHelper DbHelper = new DbHelper();
        PreparedStatement statement = null;
        //Personel Güncelle
        try {
            connection = DbHelper.getConnection();
            System.out.println(id);
            String query = "UPDATE kitap SET kitap_adi=?, kitap_yazari=?, kitap_yayinevi=?, kitap_turu=?, kitap_barkod=?, kitap_rafno=? WHERE kitap_id=" + id;
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, kitap.getAd());
            pst.setString(2, kitap.getYazar());
            pst.setString(3, kitap.getYayinevi());
            pst.setString(4, kitap.getTur());
            pst.setString(5, kitap.getBarkod());
            pst.setString(6, kitap.getRafNo());
            pst.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public ArrayList<Kitap> Ara(String text) {
        Connection connection = null;
        DbHelper DbHelper = new DbHelper();
        Statement statement = null;
        ArrayList<Kitap> kitaplar = null;
        try {
            connection = DbHelper.getConnection();
            String sql = "SELECT * FROM kitap WHERE kitap_adi LIKE '%" + text + "%' or kitap_yazari LIKE '%" + text + "%' or kitap_yayinevi LIKE '%" + text + "%' or kitap_turu LIKE '%" + text + "%' or kitap_barkod LIKE '%" + text + "%'  or kitap_rafno LIKE '%" + text + "%'";
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            kitaplar = new ArrayList<Kitap>();
            while (resultSet.next()) {
                kitaplar.add(new Kitap(
                        resultSet.getInt("kitap_id"),
                        resultSet.getString("kitap_adi"),
                        resultSet.getString("kitap_yazari"),
                        resultSet.getString("kitap_yayinevi"),
                        resultSet.getString("kitap_turu"),
                        resultSet.getString("kitap_barkod"),
                        resultSet.getString("kitap_rafno")
                ));
            }
        } catch (SQLException exception) {
            DbHelper.showErrorMessage(exception);
        }
        return kitaplar;
    }

    @Override
    public ArrayList<Kitap> Listele() {
        Connection connection = null;
        DbHelper DbHelper = new DbHelper();
        Statement statement = null;
        ArrayList<Kitap> kitaplar = null;
        try {
            connection = DbHelper.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from kitap");
            kitaplar = new ArrayList<Kitap>();
            while (resultSet.next()) {
                kitaplar.add(new Kitap(
                        resultSet.getInt("kitap_id"),
                        resultSet.getString("kitap_adi"),
                        resultSet.getString("kitap_yazari"),
                        resultSet.getString("kitap_yayinevi"),
                        resultSet.getString("kitap_turu"),
                        resultSet.getString("kitap_barkod"),
                        resultSet.getString("kitap_rafno")
                ));
            }
        } catch (SQLException exception) {
            DbHelper.showErrorMessage(exception);
        }
        return kitaplar;
    }
}
