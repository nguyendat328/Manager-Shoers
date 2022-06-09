/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOService;

import java.awt.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Pattern;
import javax.swing.*;

/**
 *
 * @author choco
 */
public class GeneralMethod {

    //in ra icon cho label theo kich thuoc label
    public void setLabelIcon(JLabel jlb, String filePath) {
        int x = (int) jlb.getPreferredSize().getWidth();
        int y = (int) jlb.getPreferredSize().getHeight();

        ImageIcon imageIcon; // load the image to a imageIcon
        imageIcon = new ImageIcon(filePath);

        Image image = imageIcon.getImage(); // transform it

        Image newimg = image.getScaledInstance(x, y, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way

        imageIcon = new ImageIcon(newimg);  // transform it back

        jlb.setIcon(imageIcon);
    }

    public void setButtonIcon(JButton jbt, String filePath, int kichThuoc) {
        int x = kichThuoc;
        int y = kichThuoc;

        ImageIcon imageIcon; // load the image to a imageIcon
        imageIcon = new ImageIcon(filePath);

        Image image = imageIcon.getImage(); // transform it

        Image newimg = image.getScaledInstance(x, y, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way

        imageIcon = new ImageIcon(newimg);  // transform it back
        jbt.setIcon(imageIcon);
    }
    //in ra panel

    public void showPanel(JPanel mainPanel, JPanel panel) {

        mainPanel.removeAll();
        mainPanel.add(panel);
        mainPanel.validate();

    }

    //---------------------------dat code 27/07/20----------------------------------------------//
    //nhap data vao combobox
    public void formatClassCombobx(JComboBox box, java.util.List<String> boxContent) {
        box.removeAllItems();
        //   box.addItem("Select");
        for (String lts : boxContent) {
            box.addItem(lts);
        }

    }

    public boolean isDouble(String a) {
        String regex_number = "^[+-]?(?:0|[1-9][0-9]*).?[0-9]+$";
        if (a.trim().length() > 1 && Pattern.matches(regex_number, a)) {
            return true;
        }
        return false;
    }

    //kiem tra ngay thang
    public boolean checkDate(String dateCheck, String dateFromat) {
        if (dateCheck.trim().length() != 10) {
            return false;
        }
        if (dateCheck == null) {
            return false;
        }
        SimpleDateFormat check = new SimpleDateFormat(dateFromat);
        check.setLenient(false);
        try {
            Date date = check.parse(dateCheck);
            return true;
        } catch (ParseException e) {
            return false;
        }

    }
    //xu ly ngay thang

    public LocalDate strToDate(String date, String pattern) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(pattern));
    }

    public String dateToStr(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
    //tao ngau nhien uuid

    public String randIdCreate() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replace("-", "");
    }
    // xoa dau tieng viet

    public String removeSign(String vnString) {
        String temp = Normalizer.normalize(vnString, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("").replace('đ', 'd').replace('Đ', 'D');
    }
    //them dau phay hang trăm cho so

    public String doubleToDecimal(double num) {
        NumberFormat currentLocale = NumberFormat.getInstance();
        return currentLocale.format(num);

    }

    public String getPassWordMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            return convertByteToHex(messageDigest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String convertByteToHex(byte[] data) {
        BigInteger number = new BigInteger(1, data);
        String hashtext = number.toString(16);
        // Now we need to zero pad it if you actually want the full 32 chars.
        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }
        return hashtext;
    }
    //h

}
