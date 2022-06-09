/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.FileSevice;

import DTOService.DBConnectionDTO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.File.Theme;

/**
 *
 * @author khai
 */
public class ThemeService {

    public List<Theme> getTheme(String nameTheme) {

        List<Theme> listTheme = new ArrayList<>();
        String sql = "SELECT backgroundTextField, backgroundButton, backgroundMainPanel, backgroundTittleTable, backgroundTaskPanel, backgroundTaskPanelClick, backgroundMenuBar, TxtColorTitle, TxtColorTitleClick FROM theme WHERE nameTheme = '" + nameTheme + "'";
        Connection conn = DBConnectionDTO.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String backgroundTextField = rs.getString("backgroundTextField");
                String backgroundButton = rs.getString("backgroundButton");
                String backgroundMainPanel = rs.getString("backgroundMainPanel");
                String backgroundTittleTable = rs.getString("backgroundTittleTable");
                String backgroundTaskPanel = rs.getString("backgroundTaskPanel");
                String backgroundTaskPanelClick = rs.getString("backgroundTaskPanelClick");
                String backgroundMenuBar = rs.getString("backgroundMenuBar");
                String TxtColorTitle = rs.getString("TxtColorTitle");
                String TxtColorTitleClick = rs.getString("TxtColorTitleClick");

                Theme themee = new Theme(backgroundTextField, backgroundButton, backgroundMainPanel, backgroundTittleTable, backgroundTaskPanel, backgroundTaskPanelClick, backgroundMenuBar, TxtColorTitle, TxtColorTitleClick);
                listTheme.add(themee);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTheme;
    }

    public List<Theme> getAllTheme() {

        List<Theme> listTheme = new ArrayList<>();
        String sql = "SELECT * FROM theme ";
        Connection conn = DBConnectionDTO.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String nameTheme = rs.getString("nameTheme");
                String backgroundTextField = rs.getString("backgroundTextField");
                String backgroundButton = rs.getString("backgroundButton");
                String backgroundMainPanel = rs.getString("backgroundMainPanel");
                String backgroundTittleTable = rs.getString("backgroundTittleTable");
                String backgroundTaskPanel = rs.getString("backgroundTaskPanel");
                String backgroundTaskPanelClick = rs.getString("backgroundTaskPanelClick");
                String backgroundMenuBar = rs.getString("backgroundMenuBar");
                String TxtColorTitle = rs.getString("TxtColorTitle");
                String TxtColorTitleClick = rs.getString("TxtColorTitleClick");

                Theme themee = new Theme(nameTheme, backgroundTextField, backgroundButton, backgroundMainPanel, backgroundTittleTable, backgroundTaskPanel, backgroundTaskPanelClick, backgroundMenuBar, TxtColorTitle, TxtColorTitleClick);
                listTheme.add(themee);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTheme;
    }

    public List<String> getNameTheme() {
        List<String> listNameTheme = new ArrayList<>();
        String sql = "SELECT nameTheme FROM theme";
        Connection conn = DBConnectionDTO.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String nameTheme = rs.getString("nameTheme");

                listNameTheme.add(nameTheme);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNameTheme;
    }
}
