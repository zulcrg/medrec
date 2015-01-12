/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtk.medicalrecord.view.panel;

import com.jtk.medicalrecord.model.ConfigModel;
import com.jtk.medicalrecord.util.ConfigHelper;
import com.jtk.medicalrecord.view.MainFrame;
import com.zlib.util.ZHash;
import javax.swing.JOptionPane;

/**
 *
 * @author M Haska Ash Shiddiq
 */
public class LoginPanel2 extends javax.swing.JPanel {

    /**
     * Creates new form LoginPanel2
     */
    public LoginPanel2() {
        initComponents();
    }

    public void preparation() {
        ConfigModel config = ConfigHelper.readConfig();
        if (config != null) {
            lblWelcome.setText("Welcome " + config.getNamaDokter());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblWelcome = new javax.swing.JLabel();
        btnMasuk = new javax.swing.JButton();
        txtPassword = new javax.swing.JPasswordField();

        setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel1.setText("Aplikasi Medical Record");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(14, 1, 14, 1);
        add(jLabel1, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel3.setText("Masukkan Password Anda");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(7, 0, 7, 0);
        add(jLabel3, gridBagConstraints);

        lblWelcome.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        lblWelcome.setText("Welcome Dr. Seno");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weighty = 6.4;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 20, 0);
        add(lblWelcome, gridBagConstraints);

        btnMasuk.setText("Masuk");
        btnMasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMasukActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weighty = 39.4;
        add(btnMasuk, gridBagConstraints);

        txtPassword.setPreferredSize(new java.awt.Dimension(180, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 9, 0);
        add(txtPassword, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void btnMasukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMasukActionPerformed
        if (txtPassword.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Harap isi password");
        } else {
            if (ZHash.hashSHA256(txtPassword.getText()).equals(ConfigHelper.readConfig().getPassword())) {
                MainFrame.instance.showMainMenu();
            } else {
                JOptionPane.showMessageDialog(null, "Password Salah");
            }
        }
    }//GEN-LAST:event_btnMasukActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMasuk;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblWelcome;
    private javax.swing.JPasswordField txtPassword;
    // End of variables declaration//GEN-END:variables
}
