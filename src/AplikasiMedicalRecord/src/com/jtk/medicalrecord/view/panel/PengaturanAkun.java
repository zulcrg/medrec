/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtk.medicalrecord.view.panel;

import com.jtk.medicalrecord.model.ConfigModel;
import com.jtk.medicalrecord.util.ConfigHelper;
import com.jtk.medicalrecord.util.MessageHelper;
import com.jtk.medicalrecord.view.MainFrame;
import com.zlib.util.ZClass;
import com.zlib.util.ZHash;
import java.util.UUID;
import javax.swing.JOptionPane;

/**
 *
 * @author M Haska Ash Shiddiq
 */
public class PengaturanAkun extends javax.swing.JPanel {

    private ConfigModel configModel;

    /**
     * Creates new form PengaturanAkun
     */
    public PengaturanAkun() {
        initComponents();
    }

    public void preparation() {
        configModel = ConfigHelper.readConfig();
        txtNamaDokter.setText(configModel.getNamaDokter());
        txtAlamt.setText(configModel.getNamaDokter());
        txtTmpPraktek.setText(configModel.getNamaDokter());
        txtNoTelp.setText(configModel.getNamaDokter());
        txtPassword.setText("");
        txtPassword1.setText("");
        txtPassword2.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField5 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNamaDokter = new javax.swing.JTextField();
        txtAlamt = new javax.swing.JTextField();
        txtTmpPraktek = new javax.swing.JTextField();
        txtNoTelp = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        btnSubmit = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        txtPassword1 = new javax.swing.JPasswordField();
        txtPassword2 = new javax.swing.JPasswordField();

        jTextField5.setText("jTextField5");

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1024, 768));
        setPreferredSize(new java.awt.Dimension(1024, 768));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Akun Dokter");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel2.setText("Nama Dokter");
        jLabel2.setPreferredSize(new java.awt.Dimension(120, 25));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel3.setText("Alamat");
        jLabel3.setPreferredSize(new java.awt.Dimension(120, 25));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel4.setText("Tempat Praktek");
        jLabel4.setPreferredSize(new java.awt.Dimension(120, 25));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel5.setText("Nomor Telepon");
        jLabel5.setPreferredSize(new java.awt.Dimension(120, 25));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel6.setText("Password Lama");
        jLabel6.setPreferredSize(new java.awt.Dimension(120, 25));

        txtNamaDokter.setPreferredSize(new java.awt.Dimension(300, 25));

        txtAlamt.setPreferredSize(new java.awt.Dimension(300, 25));

        txtTmpPraktek.setPreferredSize(new java.awt.Dimension(300, 25));

        txtNoTelp.setPreferredSize(new java.awt.Dimension(300, 25));

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jtk/medicalrecord/image/back-blue.png"))); // NOI18N
        jButton1.setMaximumSize(new java.awt.Dimension(100, 30));
        jButton1.setMinimumSize(new java.awt.Dimension(100, 30));
        jButton1.setPreferredSize(new java.awt.Dimension(100, 30));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnSubmit.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        btnSubmit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jtk/medicalrecord/image/check.png"))); // NOI18N
        btnSubmit.setText("Submit");
        btnSubmit.setMaximumSize(new java.awt.Dimension(93, 25));
        btnSubmit.setMinimumSize(new java.awt.Dimension(93, 25));
        btnSubmit.setPreferredSize(new java.awt.Dimension(93, 25));
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel7.setText("Password Baru");
        jLabel7.setPreferredSize(new java.awt.Dimension(120, 25));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel8.setText("Ulangi Password Baru");
        jLabel8.setPreferredSize(new java.awt.Dimension(120, 25));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(304, 304, 304)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(27, 27, 27)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtNoTelp, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                                        .addComponent(txtTmpPraktek, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                                        .addComponent(txtAlamt, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                                        .addComponent(txtNamaDokter, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                                        .addComponent(txtPassword)))
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtPassword1)
                                    .addComponent(txtPassword2, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE))))))
                .addContainerGap(266, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(439, 439, 439))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNamaDokter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAlamt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTmpPraktek, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNoTelp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPassword1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPassword2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        if (txtNamaDokter.getText().isEmpty()) {
            MessageHelper.addWarnMessage("Perhatian", "Harap Isi Nama");
        } else if (txtAlamt.getText().isEmpty()) {
            MessageHelper.addWarnMessage("Perhatian", "Harap isi Alamat");
        } else if (txtTmpPraktek.getText().isEmpty()) {
            MessageHelper.addWarnMessage("Perhatian", "Harap isi Tempat Praktek");
        } else if (txtNoTelp.getText().isEmpty()) {
            MessageHelper.addWarnMessage("Perhatian", "Harap isi No Telp");
        } else if (txtPassword.getText().isEmpty()) {
            MessageHelper.addWarnMessage("Perhatian", "Harap isi Password");
        } else {
            if (configModel.getPassword().equals(ZHash.hashSHA256(txtPassword.getText()))) {
                if (txtPassword1.getText().equals(txtPassword2.getText())) {
                    ConfigModel cm = new ConfigModel();
                    ZClass.copyClass(configModel, cm);
                    cm.setId(UUID.randomUUID().toString().replace("-", ""));
                    cm.setAlamat(txtAlamt.getText());
                    cm.setNamaDokter(txtAlamt.getText());
                    cm.setNoTelp(txtNoTelp.getText());
                    cm.setPassword(txtPassword1.getText());
                    cm.setTempatPraktek(txtTmpPraktek.getText());

                    ConfigHelper.editConfig(cm);
                    MessageHelper.addInfoMessage("Informasi", "Berhasil mengubah data");
                    MainFrame.instance.showPengaturan();
                } else {
                    MessageHelper.addWarnMessage("Perhatian", "Password tidak cocok");
                }
            } else {
                MessageHelper.addWarnMessage("Perhatian", "Password lama anda salah");
            }
        }
    }//GEN-LAST:event_btnSubmitActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        MainFrame.instance.showPengaturan();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSubmit;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField txtAlamt;
    private javax.swing.JTextField txtNamaDokter;
    private javax.swing.JTextField txtNoTelp;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JPasswordField txtPassword1;
    private javax.swing.JPasswordField txtPassword2;
    private javax.swing.JTextField txtTmpPraktek;
    // End of variables declaration//GEN-END:variables
}
