/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtk.medicalrecord.util;

import com.google.gson.Gson;
import java.awt.Component;
import java.nio.charset.Charset;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Zulkhair Abdullah D
 */
public class CommonHelper {

    public static void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 50; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width, width);
            }
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }

    public static void printToJson(Object object) {
        Gson gson = new Gson();
        System.out.println(gson.toJson(object));
    }

    public static byte[] classToByteJson(Object object) {
        return stringToByte(classToStringJson(object));
    }

    public static String classToStringJson(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    public static byte[] stringToByte(String s) {
        byte[] b = s.getBytes(Charset.forName("UTF-8"));
        return b;
    }

    public static String byteToString(byte[] b) {
        String s = new String(b, Charset.forName("UTF-8"));
        return s;
    }
}
