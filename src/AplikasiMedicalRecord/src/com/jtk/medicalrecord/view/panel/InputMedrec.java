/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtk.medicalrecord.view.panel;

import com.jtk.medicalrecord.entity.Anamnesa;
import com.jtk.medicalrecord.entity.AnamnesaPK;
import com.jtk.medicalrecord.entity.Diagnosis;
import com.jtk.medicalrecord.entity.DiagnosisPK;
import com.jtk.medicalrecord.entity.Dosis;
import com.jtk.medicalrecord.entity.MedicalRecord;
import com.jtk.medicalrecord.entity.MedicalRecordPK;
import com.jtk.medicalrecord.entity.Pasien;
import com.jtk.medicalrecord.entity.PemeriksaanFisik;
import com.jtk.medicalrecord.entity.PemeriksaanFisikPK;
import com.jtk.medicalrecord.entity.PemeriksaanPendukung;
import com.jtk.medicalrecord.jpacontroller.AnamnesaJpaController;
import com.jtk.medicalrecord.jpacontroller.DiagnosisJpaController;
import com.jtk.medicalrecord.jpacontroller.DosisJpaController;
import com.jtk.medicalrecord.jpacontroller.MedicalRecordJpaController;
import com.jtk.medicalrecord.jpacontroller.PemeriksaanFisikJpaController;
import com.jtk.medicalrecord.jpacontroller.PemeriksaanPendukungJpaController;
import com.jtk.medicalrecord.util.MessageHelper;
import com.jtk.medicalrecord.view.MainFrame;
import com.jtk.medicalrecord.view.dialog.SearchPasienDialog;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author M Haska Ash Shiddiq
 */
public class InputMedrec extends javax.swing.JPanel {

    private final MedicalRecordJpaController medicalRecordJpaController = new MedicalRecordJpaController();
    private final AnamnesaJpaController anamnesaJpaController = new AnamnesaJpaController();
    private final PemeriksaanFisikJpaController pemeriksaanFisikJpaController = new PemeriksaanFisikJpaController();
    private final DiagnosisJpaController diagnosisJpaController = new DiagnosisJpaController();
    private final PemeriksaanPendukungJpaController pemeriksaanPendukungJpaController = new PemeriksaanPendukungJpaController();
    private final DosisJpaController dosisJpaController = new DosisJpaController();
    private Pasien pasien;
    private MedicalRecord medicalRecord = new MedicalRecord();
    private final Diagnosis diagnosis = new Diagnosis();
    private final Anamnesa anamnesa = new Anamnesa();
    private final PemeriksaanFisik pemeriksaanFisik = new PemeriksaanFisik();
    private final boolean view;

    public InputMedrec() {
        this.view = false;
        initComponents();
    }

    /**
     * Creates new form InputMedrec
     *
     * @param view
     * @param medicalRecord1
     */
    public InputMedrec(boolean view, MedicalRecord medicalRecord1) {
        initComponents();
        this.view = view;
        medicalRecord = medicalRecord1;
        if (view) {
            pnlAnamnesa.viewState();
            pnlDiagnosa.viewState();
            pnlPemeriksaanFisik.viewState();
            pnlPemeriksaanPendukung.viewState();
            lblTitle.setText("Detail Rekam Medis");
            txtPasien.setEditable(false);
            btnCari.setVisible(false);
            btnSubmit.setVisible(false);
        }
    }

    public void initView() {
        txtPasien.setText(medicalRecord.getPasien().getPasNama());

        pnlAnamnesa.getTxtAnamnesaKhusus().setText(medicalRecord.getAnamnesa().getAnKhusus());
        pnlAnamnesa.getTxtKeluhanUtama().setText(medicalRecord.getAnamnesa().getAnKeluhan());
        pnlAnamnesa.getTxtRiwayatPenyakitDahulu().setText(medicalRecord.getAnamnesa().getAnRiwayat());
        pnlAnamnesa.getTxtRiwayatPenyakitKeluarga().setText(medicalRecord.getAnamnesa().getAnRiwayatKel());

        pnlPemeriksaanFisik.getTxtKesadaran().setText(medicalRecord.getPemeriksaanFisik().getPfKesadaran());
        pnlPemeriksaanFisik.getTxtLain().setText(medicalRecord.getPemeriksaanFisik().getPfLainLain());
        pnlPemeriksaanFisik.getTxtLajuNadi().setText(medicalRecord.getPemeriksaanFisik().getPfLajuNadi());
        pnlPemeriksaanFisik.getTxtLajuNafas().setText(medicalRecord.getPemeriksaanFisik().getPfLajuNafas());
        pnlPemeriksaanFisik.getTxtSuhuTubuh().setText(medicalRecord.getPemeriksaanFisik().getPfSuhu());
        pnlPemeriksaanFisik.getTxtTekananDarah().setText(medicalRecord.getPemeriksaanFisik().getPfTekananDarah());

        pnlPemeriksaanPendukung.getPemeriksaanPendukungs().addAll(medicalRecord.getPemeriksaanPendukungList());
        pnlPemeriksaanPendukung.createTableValue();

        pnlDiagnosa.getTxtDiagnosisBanding().setText(medicalRecord.getDiagnosis().getDgBanding());
        pnlDiagnosa.getTxtDiagnosisKerja().setText(medicalRecord.getDiagnosis().getDgKerja());
        pnlDiagnosa.getTxtPengobatan().setText(medicalRecord.getDiagnosis().getDgPengobatan());
        pnlDiagnosa.getTxtPrognosis().setText(medicalRecord.getDiagnosis().getDgPrognosis());
        pnlDiagnosa.getDosises().addAll(medicalRecord.getDiagnosis().getDosisList());
        pnlDiagnosa.createTableValue();
    }

    public void preparation() {
        pnlAnamnesa.clear();
        pnlDiagnosa.clear();
        pnlPemeriksaanFisik.clear();
        pnlPemeriksaanPendukung.clear();
        pasien = null;
        txtPasien.setText("");
        medicalRecord = new MedicalRecord();
    }

    private void createMedrec() {
        if (pasien == null) {
            MessageHelper.addWarnMessage("Perhatian", "Harap pilih pasien terlebih dahulu");
        } else {
            try {
                MedicalRecordPK medicalRecordPK = new MedicalRecordPK();
                medicalRecordPK.setMedId(new Date().getTime());
                medicalRecordPK.setPasId(pasien.getPasId());
                medicalRecord.setMedicalRecordPK(medicalRecordPK);

                constructAnamnesa();
                constructPemFisik();
                constructDiagnosis();

                medicalRecord.setMedTanggal(new Date(medicalRecordPK.getMedId()));
                medicalRecord.setPasien(pasien);

                medicalRecordJpaController.create(medicalRecord);
                anamnesaJpaController.create(anamnesa);
                pemeriksaanFisikJpaController.create(pemeriksaanFisik);
                diagnosisJpaController.create(diagnosis);
                for (PemeriksaanPendukung object : pnlPemeriksaanPendukung.getPemeriksaanPendukungs()) {
                    object.setMedicalRecord(medicalRecord);
                    pemeriksaanPendukungJpaController.create(object);
                }
                for (Dosis object : pnlDiagnosa.getDosises()) {
                    object.getDosisPK().setMedId(medicalRecordPK.getMedId());
                    object.getDosisPK().setPasId(pasien.getPasId());
                    object.setDiagnosis(diagnosis);
                    dosisJpaController.create(object);
                }
                MessageHelper.addInfoMessage("Informasi", "Data medical record berhasil ditambahkan");
                preparation();
            } catch (Exception ex) {
                MessageHelper.addErrorMessage("Error create Medrec", ex.getMessage());
                Logger.getLogger(InputMedrec.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void constructAnamnesa() {
        AnamnesaPK anamnesaPK = new AnamnesaPK();
        anamnesaPK.setMedId(medicalRecord.getMedicalRecordPK().getMedId());
        anamnesaPK.setPasId(pasien.getPasId());

        anamnesa.setAnamnesaPK(anamnesaPK);
        anamnesa.setAnKeluhan(pnlAnamnesa.getTxtKeluhanUtama().getText());
        anamnesa.setAnKhusus(pnlAnamnesa.getTxtAnamnesaKhusus().getText());
        anamnesa.setAnRiwayat(pnlAnamnesa.getTxtRiwayatPenyakitDahulu().getText());
        anamnesa.setAnRiwayatKel(pnlAnamnesa.getTxtRiwayatPenyakitKeluarga().getText());
        anamnesa.setMedicalRecord(medicalRecord);
    }

    private void constructPemFisik() {
        PemeriksaanFisikPK pemeriksaanFisikPK = new PemeriksaanFisikPK();
        pemeriksaanFisikPK.setMedId(medicalRecord.getMedicalRecordPK().getMedId());
        pemeriksaanFisikPK.setPasId(pasien.getPasId());

        pemeriksaanFisik.setPemeriksaanFisikPK(pemeriksaanFisikPK);
        pemeriksaanFisik.setMedicalRecord(medicalRecord);
        pemeriksaanFisik.setPfKesadaran(pnlPemeriksaanFisik.getTxtKesadaran().getText());
        pemeriksaanFisik.setPfLainLain(pnlPemeriksaanFisik.getTxtLain().getText());
        pemeriksaanFisik.setPfLajuNadi(pnlPemeriksaanFisik.getTxtLajuNadi().getText());
        pemeriksaanFisik.setPfLajuNafas(pnlPemeriksaanFisik.getTxtLajuNafas().getText());
        pemeriksaanFisik.setPfSuhu(pnlPemeriksaanFisik.getTxtSuhuTubuh().getText());
        pemeriksaanFisik.setPfTekananDarah(pnlPemeriksaanFisik.getTxtTekananDarah().getText());
    }

    private void constructDiagnosis() {
        DiagnosisPK diagnosisPK = new DiagnosisPK();
        diagnosisPK.setPasId(pasien.getPasId());
        diagnosisPK.setMedId(medicalRecord.getMedicalRecordPK().getMedId());

        diagnosis.setDgBanding(pnlDiagnosa.getTxtDiagnosisBanding().getText());
        diagnosis.setDgKerja(pnlDiagnosa.getTxtDiagnosisBanding().getText());
        diagnosis.setDgPengobatan(pnlDiagnosa.getTxtDiagnosisBanding().getText());
        diagnosis.setDgPrognosis(pnlDiagnosa.getTxtDiagnosisBanding().getText());
        diagnosis.setMedicalRecord(medicalRecord);
        diagnosis.setDiagnosisPK(diagnosisPK);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnKembali = new javax.swing.JButton();
        lblPasien = new javax.swing.JLabel();
        txtPasien = new javax.swing.JTextField();
        btnCari = new javax.swing.JButton();
        btnSubmit = new javax.swing.JButton();
        pnlAnamnesa = new com.jtk.medicalrecord.view.panel.InputMedrecAnamnesa();
        pnlDiagnosa = new com.jtk.medicalrecord.view.panel.InputMedrecDiagnosa();
        pnlPemeriksaanFisik = new com.jtk.medicalrecord.view.panel.InputMedrecPemeriksaanfisik();
        pnlPemeriksaanPendukung = new com.jtk.medicalrecord.view.panel.InputMedrecPemeriksaanpendukung();
        lblTitle = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1024, 700));
        setPreferredSize(new java.awt.Dimension(1024, 768));

        btnKembali.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        btnKembali.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jtk/medicalrecord/image/back-blue.png"))); // NOI18N
        btnKembali.setMaximumSize(new java.awt.Dimension(100, 30));
        btnKembali.setMinimumSize(new java.awt.Dimension(100, 30));
        btnKembali.setPreferredSize(new java.awt.Dimension(100, 30));
        btnKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKembaliActionPerformed(evt);
            }
        });

        lblPasien.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblPasien.setText("Pasien");

        txtPasien.setPreferredSize(new java.awt.Dimension(200, 23));

        btnCari.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        btnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jtk/medicalrecord/image/search-mini.png"))); // NOI18N
        btnCari.setText("Cari");
        btnCari.setMaximumSize(new java.awt.Dimension(93, 25));
        btnCari.setMinimumSize(new java.awt.Dimension(93, 25));
        btnCari.setPreferredSize(new java.awt.Dimension(93, 25));
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
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

        lblTitle.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTitle.setText("Input Rekam Medis");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(btnKembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(327, 327, 327)
                        .addComponent(lblTitle))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlAnamnesa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnlPemeriksaanFisik, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pnlPemeriksaanPendukung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pnlDiagnosa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblPasien)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPasien, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnKembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitle))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPasien)
                    .addComponent(txtPasien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCari, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlPemeriksaanPendukung, javax.swing.GroupLayout.PREFERRED_SIZE, 626, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlDiagnosa, javax.swing.GroupLayout.PREFERRED_SIZE, 626, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlPemeriksaanFisik, javax.swing.GroupLayout.PREFERRED_SIZE, 626, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlAnamnesa, javax.swing.GroupLayout.PREFERRED_SIZE, 626, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        createMedrec();
    }//GEN-LAST:event_btnSubmitActionPerformed

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        Pasien temp = pasien;
        pasien = new Pasien();
        SearchPasienDialog dialog = new SearchPasienDialog(null, true, pasien);
        dialog.show();
        if (pasien != null) {
            txtPasien.setText(pasien.getPasNama());
        } else {
            pasien = temp;
            if (pasien != null) {
                txtPasien.setText(pasien.getPasNama());
            }
        }
    }//GEN-LAST:event_btnCariActionPerformed

    private void btnKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembaliActionPerformed
        if (view) {
            MainFrame.instance.showLihatMedrec();
        } else {
            MainFrame.instance.showMainMenu();
        }
    }//GEN-LAST:event_btnKembaliActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnKembali;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JLabel lblPasien;
    private javax.swing.JLabel lblTitle;
    private com.jtk.medicalrecord.view.panel.InputMedrecAnamnesa pnlAnamnesa;
    private com.jtk.medicalrecord.view.panel.InputMedrecDiagnosa pnlDiagnosa;
    private com.jtk.medicalrecord.view.panel.InputMedrecPemeriksaanfisik pnlPemeriksaanFisik;
    private com.jtk.medicalrecord.view.panel.InputMedrecPemeriksaanpendukung pnlPemeriksaanPendukung;
    private javax.swing.JTextField txtPasien;
    // End of variables declaration//GEN-END:variables
}
