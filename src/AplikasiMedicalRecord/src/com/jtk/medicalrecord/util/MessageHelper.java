/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtk.medicalrecord.util;

import com.jtk.medicalrecord.view.dialog.PasswordDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Zulkhair Abdullah D
 */
public class MessageHelper {

    public static String retval;

    public static void addInfoMessage(String title, String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void addWarnMessage(String title, String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
    }

    public static void addErrorMessage(String title, String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }

    public static boolean addConfimationMessage(String title, String message) {
        int a = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
        return a == JOptionPane.YES_OPTION;
    }

    public static String addInputPasswordDialog(String title, String message) {
        PasswordDialog passwordDialog = new PasswordDialog(null, true, title, message);
        passwordDialog.show();
        return retval;
    }
}
