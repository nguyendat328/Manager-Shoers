/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.File;

/**
 *
 * @author choco
 */
public class Option {

    private String themeName;
    private int sellingMethod;

    public Option(String themeName, int sellingMethod) {
        this.themeName = themeName;
        this.sellingMethod = sellingMethod;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public int getSellingMethod() {
        return sellingMethod;
    }

    public void setSellingMethod(int sellingMethod) {
        this.sellingMethod = sellingMethod;
    }

}
