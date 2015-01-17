/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtk.medicalrecord.util;

/**
 *
 * @author Zulkhair Abdullah D
 */
public class TextHelper {

    public static boolean genderSimpleStringtoBoolean(String gender) {
        return gender.equalsIgnoreCase("L");
    }

    public static String genderBooleanToSimpleString(boolean gender) {
        if (gender) {
            return "L";
        } else {
            return "P";
        }
    }

    public static String genderSimpleStringToString(String gender) {
        if (gender.equalsIgnoreCase("L")) {
            return "Laki-laki";
        } else {
            return "Perempuan";
        }
    }

    public static String genderStringToSimpleString(String gender) {
        if (gender.equalsIgnoreCase("Laki-laki")) {
            return "L";
        } else {
            return "P";
        }
    }
}
