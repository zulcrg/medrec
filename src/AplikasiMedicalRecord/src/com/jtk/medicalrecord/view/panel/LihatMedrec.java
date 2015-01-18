/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtk.medicalrecord.view.panel;

import com.jtk.medicalrecord.entity.Dosis;
import com.jtk.medicalrecord.entity.MedicalRecord;
import com.jtk.medicalrecord.entity.PemeriksaanPendukung;
import com.jtk.medicalrecord.jpacontroller.AnamnesaJpaController;
import com.jtk.medicalrecord.jpacontroller.DiagnosisJpaController;
import com.jtk.medicalrecord.jpacontroller.DosisJpaController;
import com.jtk.medicalrecord.jpacontroller.MedicalRecordJpaController;
import com.jtk.medicalrecord.jpacontroller.PemeriksaanFisikJpaController;
import com.jtk.medicalrecord.jpacontroller.PemeriksaanPendukungJpaController;
import com.jtk.medicalrecord.jpacontroller.exceptions.IllegalOrphanException;
import com.jtk.medicalrecord.jpacontroller.exceptions.NonexistentEntityException;
import com.jtk.medicalrecord.model.ConfigModel;
import com.jtk.medicalrecord.util.CommonHelper;
import com.jtk.medicalrecord.util.ConfigHelper;
import com.jtk.medicalrecord.util.MessageHelper;
import com.jtk.medicalrecord.view.MainFrame;
import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author M Haska Ash Shiddiq
 */
public class LihatMedrec extends javax.swing.JPanel {

    private final ConfigModel configModel = ConfigHelper.readConfig();
    private boolean first = true;
    private List<MedicalRecord> medicalRecords = new ArrayList<>();
    private final MedicalRecordJpaController medicalRecordJpaController = new MedicalRecordJpaController();
    private final AnamnesaJpaController anamnesaJpaController = new AnamnesaJpaController();
    private final PemeriksaanFisikJpaController pemeriksaanFisikJpaController = new PemeriksaanFisikJpaController();
    private final DiagnosisJpaController diagnosisJpaController = new DiagnosisJpaController();
    private final PemeriksaanPendukungJpaController pemeriksaanPendukungJpaController = new PemeriksaanPendukungJpaController();
    private final DosisJpaController dosisJpaController = new DosisJpaController();

    /**
     * Creates new form LihatMedrec
     */
    public LihatMedrec() {
        initComponents();
        medicalRecords = medicalRecordJpaController.findMedicalRecordEntities();
        createTableValue();
        addDateListener();
    }

    private void addDateListener() {
        dateDari.getDateEditor().addPropertyChangeListener(
                new PropertyChangeListener() {
                    @Override
                    public void propertyChange(PropertyChangeEvent e) {
                        if ("date".equals(e.getPropertyName())) {
                            medicalRecords = medicalRecordJpaController.findByPasNameNikOrTanggal(txtSearch.getText(), dateDari.getDate(), dateSampai.getDate());
                            createTableValue();
                        }
                    }
                });
        dateSampai.getDateEditor().addPropertyChangeListener(
                new PropertyChangeListener() {
                    @Override
                    public void propertyChange(PropertyChangeEvent e) {
                        if ("date".equals(e.getPropertyName())) {
                            medicalRecords = medicalRecordJpaController.findByPasNameNikOrTanggal(txtSearch.getText(), dateDari.getDate(), dateSampai.getDate());
                            createTableValue();
                        }
                    }
                });
    }

    public void preparation() {

    }

    private void createTableValue() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        Object[] columnsName = {"Pasien", "Tanggal Periksa", "Keluhan Utama"};

        DefaultTableModel dtm = new DefaultTableModel(null, columnsName) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        for (MedicalRecord p : medicalRecords) {
            Object[] o = new Object[3];
            o[0] = p.getPasien().getPasNama();
            o[1] = sdf.format(p.getMedTanggal());
            o[2] = p.getAnamnesa().getAnKeluhan();

            dtm.addRow(o);
        }
        tblPasien.setModel(dtm);
        CommonHelper.resizeColumnWidth(tblPasien);
    }

    private void select() {
        if (tblPasien.isRowSelected(tblPasien.getSelectedRow())) {
            MainFrame.instance.showViewMedrec(medicalRecords.get(tblPasien.getSelectedRow()));
        } else {
            MessageHelper.addWarnMessage("Perhatian", "Harap pilih medical record terlebih dahulu");
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

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        btnLihat = new javax.swing.JButton();
        dateSampai = new com.toedter.calendar.JDateChooser();
        btnHapus = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        dateDari = new com.toedter.calendar.JDateChooser();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPasien = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1024, 768));

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

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Lihat Rekam Medis");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel4.setText("Cari");

        btnLihat.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        btnLihat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jtk/medicalrecord/image/view.png"))); // NOI18N
        btnLihat.setText("Lihat");
        btnLihat.setMaximumSize(new java.awt.Dimension(93, 25));
        btnLihat.setMinimumSize(new java.awt.Dimension(93, 25));
        btnLihat.setPreferredSize(new java.awt.Dimension(93, 25));
        btnLihat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLihatActionPerformed(evt);
            }
        });

        btnHapus.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        btnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jtk/medicalrecord/image/trash.png"))); // NOI18N
        btnHapus.setText("Hapus");
        btnHapus.setEnabled(false);
        btnHapus.setMaximumSize(new java.awt.Dimension(93, 25));
        btnHapus.setMinimumSize(new java.awt.Dimension(93, 25));
        btnHapus.setPreferredSize(new java.awt.Dimension(93, 25));
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel2.setText("Dari");

        txtSearch.setForeground(new java.awt.Color(153, 153, 153));
        txtSearch.setText("Cari berdasarkan NIK atau Nama Pasien");
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });
        txtSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSearchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSearchFocusLost(evt);
            }
        });
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel3.setText("Sampai");

        tblPasien.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        tblPasien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Pasien", "Tanggal Periksa", "Keluhan Utama"
            }
        ));
        tblPasien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPasienMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblPasien);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 477, Short.MAX_VALUE)
                        .addComponent(btnLihat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(38, 38, 38)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                            .addComponent(dateDari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel3)
                            .addGap(18, 18, 18)
                            .addComponent(dateSampai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel4))
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLihat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4)
                                .addComponent(jLabel2))
                            .addComponent(dateSampai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(dateDari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(589, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(330, 330, 330)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(180, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(157, 157, 157))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        MainFrame.instance.showMainMenu();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtSearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSearchFocusGained
        if (first) {
            txtSearch.setForeground(Color.black);
            txtSearch.setText("");
            first = false;
        }
    }//GEN-LAST:event_txtSearchFocusGained

    private void txtSearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSearchFocusLost
        if (txtSearch.getText().isEmpty()) {
            txtSearch.setForeground(Color.getHSBColor(153, 153, 153));
            txtSearch.setText("Cari berdasarkan Kode, Nama, atau Jenis Obat");
            first = true;
        }
    }//GEN-LAST:event_txtSearchFocusLost

    private void tblPasienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPasienMouseClicked
        if (evt.getClickCount() == 2) {
            select();
        } else {
            if (medicalRecords.get(tblPasien.getSelectedRow()).getMedTanggal().getYear() - configModel.getWaktu() < 1 | configModel.getWaktu() == 0) {
                btnHapus.setEnabled(true);
            } else {
                btnHapus.setEnabled(false);
            }
        }
    }//GEN-LAST:event_tblPasienMouseClicked

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        medicalRecords = medicalRecordJpaController.findByPasNameNikOrTanggal(txtSearch.getText(), dateDari.getDate(), dateSampai.getDate());
        createTableValue();
    }//GEN-LAST:event_txtSearchKeyReleased

    private void btnLihatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLihatActionPerformed
        select();
    }//GEN-LAST:event_btnLihatActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        if (MessageHelper.addConfimationMessage("Perhatian", "Apakah anda yakin akan menghapus data ini ?")) {
            try {
                MedicalRecord medicalRecord = medicalRecords.get(tblPasien.getSelectedRow());
                if (medicalRecord.getDiagnosis() != null) {
                    for (Dosis d : medicalRecord.getDiagnosis().getDosisList()) {
                        dosisJpaController.destroy(d.getDosisPK());
                    }
                    diagnosisJpaController.destroy(medicalRecord.getDiagnosis().getDiagnosisPK());
                }

                for (PemeriksaanPendukung p : medicalRecord.getPemeriksaanPendukungList()) {
                    pemeriksaanPendukungJpaController.destroy(p.getPemId());
                }

                if (medicalRecord.getPemeriksaanFisik() != null) {
                    pemeriksaanFisikJpaController.destroy(medicalRecord.getPemeriksaanFisik().getPemeriksaanFisikPK());
                }
                if (medicalRecord.getAnamnesa() != null) {
                    anamnesaJpaController.destroy(medicalRecord.getAnamnesa().getAnamnesaPK());
                }
                medicalRecordJpaController.destroy(medicalRecord.getMedicalRecordPK());

                medicalRecords.remove(medicalRecord);
                createTableValue();
                MessageHelper.addInfoMessage("Informasi", "Data berhasil di hapus");
            } catch (IllegalOrphanException | NonexistentEntityException ex) {
                MessageHelper.addErrorMessage("Error", ex.getMessage());
                Logger.getLogger(LihatMedrec.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnHapusActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnLihat;
    private com.toedter.calendar.JDateChooser dateDari;
    private com.toedter.calendar.JDateChooser dateSampai;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblPasien;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
