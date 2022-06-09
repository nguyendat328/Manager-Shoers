/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.File;

/**
 *
 * @author khai
 */
public class Theme {

    private String nameTheme;
    private String backgroundTextField;
    private String backgroundButton;
    private String backgroundMainPanel;
    private String backgroundTittleTable;
    private String backgroundTaskPanel;
    private String backgroundTaskPanelClick;
    private String backgroundMenuBar;
    private String TxtColorTitle;
    private String TxtColorTitleClick;

    public Theme(String nameTheme, String backgroundTextField, String backgroundButton, String backgroundMainPanel, String backgroundTittleTable, String backgroundTaskPanel, String backgroundTaskPanelClick, String backgroundMenuBar, String TxtColorTitle, String TxtColorTitleClick) {
        this.nameTheme = nameTheme;
        this.backgroundTextField = backgroundTextField;
        this.backgroundButton = backgroundButton;
        this.backgroundMainPanel = backgroundMainPanel;
        this.backgroundTittleTable = backgroundTittleTable;
        this.backgroundTaskPanel = backgroundTaskPanel;
        this.backgroundTaskPanelClick = backgroundTaskPanelClick;
        this.backgroundMenuBar = backgroundMenuBar;
        this.TxtColorTitle = TxtColorTitle;
        this.TxtColorTitleClick = TxtColorTitleClick;
    }

    public Theme() {
    }

    public Theme(String backgroundTextField, String backgroundButton, String backgroundMainPanel, String backgroundTittleTable, String backgroundTaskPanel, String backgroundTaskPanelClick, String backgroundMenuBar, String TxtColorTitle, String TxtColorTitleClick) {
        this.backgroundTextField = backgroundTextField;
        this.backgroundButton = backgroundButton;
        this.backgroundMainPanel = backgroundMainPanel;
        this.backgroundTittleTable = backgroundTittleTable;
        this.backgroundTaskPanel = backgroundTaskPanel;
        this.backgroundTaskPanelClick = backgroundTaskPanelClick;
        this.backgroundMenuBar = backgroundMenuBar;
        this.TxtColorTitle = TxtColorTitle;
        this.TxtColorTitleClick = TxtColorTitleClick;
    }

    public Theme(String nameTheme) {
        this.nameTheme = nameTheme;
    }

    public String getNameTheme() {
        return nameTheme;
    }

    public void setNameTheme(String nameTheme) {
        this.nameTheme = nameTheme;
    }

    public String getBackgroundTextField() {
        return backgroundTextField;
    }

    public void setBackgroundTextField(String backgroundTextField) {
        this.backgroundTextField = backgroundTextField;
    }

    public String getBackgroundButton() {
        return backgroundButton;
    }

    public void setBackgroundButton(String backgroundButton) {
        this.backgroundButton = backgroundButton;
    }

    public String getBackgroundMainPanel() {
        return backgroundMainPanel;
    }

    public void setBackgroundMainPanel(String backgroundMainPanel) {
        this.backgroundMainPanel = backgroundMainPanel;
    }

    public String getBackgroundTittleTable() {
        return backgroundTittleTable;
    }

    public void setBackgroundTittleTable(String backgroundTittleTable) {
        this.backgroundTittleTable = backgroundTittleTable;
    }

    public String getBackgroundTaskPanel() {
        return backgroundTaskPanel;
    }

    public void setBackgroundTaskPanel(String backgroundTaskPanel) {
        this.backgroundTaskPanel = backgroundTaskPanel;
    }

    public String getBackgroundTaskPanelClick() {
        return backgroundTaskPanelClick;
    }

    public void setBackgroundTaskPanelClick(String backgroundTaskPanelClick) {
        this.backgroundTaskPanelClick = backgroundTaskPanelClick;
    }

    public String getBackgroundMenuBar() {
        return backgroundMenuBar;
    }

    public void setBackgroundMenuBar(String backgroundMenuBar) {
        this.backgroundMenuBar = backgroundMenuBar;
    }

    public String getTxtColorTitle() {
        return TxtColorTitle;
    }

    public void setTxtColorTitle(String TxtColorTitle) {
        this.TxtColorTitle = TxtColorTitle;
    }

    public String getTxtColorTitleClick() {
        return TxtColorTitleClick;
    }

    public void setTxtColorTitleClick(String TxtColorTitleClick) {
        this.TxtColorTitleClick = TxtColorTitleClick;
    }

}
