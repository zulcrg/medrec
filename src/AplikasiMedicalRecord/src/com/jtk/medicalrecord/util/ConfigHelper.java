/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtk.medicalrecord.util;

import com.jtk.medicalrecord.model.ConfigModel;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 *
 * @author Zulkhair Abdullah D
 */
public class ConfigHelper {

    public static void creaeteConfig(ConfigModel config) {
        Properties prop = new Properties();
        OutputStream output = null;

        try {

            output = new FileOutputStream("MED");

            // set the properties value
            prop.setProperty("id", config.getId());
            prop.setProperty("nama", config.getNamaDokter());
            prop.setProperty("alamat", config.getAlamat());
            prop.setProperty("tempatPraktek", config.getTempatPraktek());
            prop.setProperty("noTelp", config.getNoTelp());
            prop.setProperty("password", config.getPassword());

            // save properties to project root folder
            prop.store(output, null);
            Runtime.getRuntime().exec("attrib +H MED");

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static ConfigModel reaadConfig() {
        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream("MED");

            // load a properties file
            prop.load(input);

            ConfigModel configModel = new ConfigModel();
            configModel.setId(prop.getProperty("id"));
            configModel.setNamaDokter(prop.getProperty("nama"));
            configModel.setAlamat(prop.getProperty("alamat"));
            configModel.setTempatPraktek(prop.getProperty("tempatPraktek"));
            configModel.setNoTelp(prop.getProperty("noTelp"));
            configModel.setPassword(prop.getProperty("password"));

            return configModel;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
