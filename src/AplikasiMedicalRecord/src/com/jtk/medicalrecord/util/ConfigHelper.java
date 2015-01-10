/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtk.medicalrecord.util;

import com.jtk.medicalrecord.model.ConfigModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Zulkhair Abdullah D
 */
public class ConfigHelper {

    public static void creaeteConfig(ConfigModel config) {
        try {
            Properties prop = new Properties();
            OutputStream output = new FileOutputStream("MED");

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
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConfigHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ConfigHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static ConfigModel readConfig() {
        try {
            Properties prop = new Properties();
            ConfigModel configModel;
            File file = new File("MED");
            if (file.exists()) {
                // load a properties file
                try (InputStream input = new FileInputStream("MED")) {
                    // load a properties file
                    prop.load(input);
                    configModel = new ConfigModel();
                    configModel.setId(prop.getProperty("id"));
                    configModel.setNamaDokter(prop.getProperty("nama"));
                    configModel.setAlamat(prop.getProperty("alamat"));
                    configModel.setTempatPraktek(prop.getProperty("tempatPraktek"));
                    configModel.setNoTelp(prop.getProperty("noTelp"));
                    configModel.setPassword(prop.getProperty("password"));
                }
                return configModel;
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Config not found");
        } catch (IOException ex) {
            Logger.getLogger(ConfigHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
