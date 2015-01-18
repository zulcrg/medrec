/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtk.medicalrecord.view.panel;

import com.jtk.medicalrecord.entity.Dosis;
import com.jtk.medicalrecord.entity.FollowUp;
import com.jtk.medicalrecord.entity.Log;
import com.jtk.medicalrecord.entity.MedicalRecord;
import com.jtk.medicalrecord.entity.Obat;
import com.jtk.medicalrecord.entity.Pasien;
import com.jtk.medicalrecord.entity.PemeriksaanPendukung;
import com.jtk.medicalrecord.entity.Rujukan;
import com.jtk.medicalrecord.jpacontroller.AnamnesaJpaController;
import com.jtk.medicalrecord.jpacontroller.DiagnosisJpaController;
import com.jtk.medicalrecord.jpacontroller.DosisJpaController;
import com.jtk.medicalrecord.jpacontroller.FollowUpJpaController;
import com.jtk.medicalrecord.jpacontroller.LogJpaController;
import com.jtk.medicalrecord.jpacontroller.MedicalRecordJpaController;
import com.jtk.medicalrecord.jpacontroller.ObatJpaController;
import com.jtk.medicalrecord.jpacontroller.PasienJpaController;
import com.jtk.medicalrecord.jpacontroller.PemeriksaanFisikJpaController;
import com.jtk.medicalrecord.jpacontroller.PemeriksaanPendukungJpaController;
import com.jtk.medicalrecord.jpacontroller.RujukanJpaController;
import com.jtk.medicalrecord.model.ConfigModel;
import com.jtk.medicalrecord.util.AsyncProgress;
import com.jtk.medicalrecord.util.CommonHelper;
import com.jtk.medicalrecord.util.ConfigHelper;
import com.jtk.medicalrecord.util.MessageHelper;
import com.jtk.medicalrecord.view.MainFrame;
import com.jtk.medicalrecord.view.dialog.LoadingDialog;
import com.zlib.io.ZIo;
import com.zlib.util.ZHash;
import java.awt.HeadlessException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

/**
 *
 * @author M Haska Ash Shiddiq
 */
public class PengaturanBR extends javax.swing.JPanel {

    private final MedicalRecordJpaController medicalRecordJpaController = new MedicalRecordJpaController();
    private final PasienJpaController pasienJpaController = new PasienJpaController();
    private final ObatJpaController obatJpaController = new ObatJpaController();
    private final LogJpaController logJpaController = new LogJpaController();
    private final RujukanJpaController rujukanJpaController = new RujukanJpaController();
    private final AnamnesaJpaController anamnesaJpaController = new AnamnesaJpaController();
    private final DiagnosisJpaController diagnosisJpaController = new DiagnosisJpaController();
    private final PemeriksaanFisikJpaController pemeriksaanFisikJpaController = new PemeriksaanFisikJpaController();
    private final PemeriksaanPendukungJpaController pemeriksaanPendukungJpaController = new PemeriksaanPendukungJpaController();
    private final FollowUpJpaController followUpJpaController = new FollowUpJpaController();
    private final DosisJpaController dosisJpaController = new DosisJpaController();
    private ConfigModel configModel;
    private List<Pasien> pasiens;
    private List<MedicalRecord> medicalRecords;
    private List<Obat> obats;
    private List<Log> logs;
    private String password;

    /**
     * Creates new form PengaturanBR
     */
    public PengaturanBR() {
        initComponents();
    }

    public void preparation() {

    }

    private static void addToZipFile(String fileName, ZipOutputStream zos) throws FileNotFoundException, IOException {
        File file = new File(fileName);
        try (FileInputStream fis = new FileInputStream(file)) {
            ZipEntry zipEntry = new ZipEntry(fileName);
            zos.putNextEntry(zipEntry);

            byte[] bytes = new byte[1024];
            int length;
            while ((length = fis.read(bytes)) >= 0) {
                zos.write(bytes, 0, length);
            }

            zos.closeEntry();
        }
    }

    private static void addToZipFile(String fileName, byte[] file, ZipOutputStream zos) throws FileNotFoundException, IOException {
        ZipEntry zipEntry = new ZipEntry(fileName);
        zos.putNextEntry(zipEntry);
        zos.write(file, 0, file.length);
        zos.closeEntry();
    }

    private void processBackup(final ZipOutputStream zos) {
        LoadingDialog dialog = new LoadingDialog(new AsyncProgress() {
            @Override
            public void done() {
            }

            @Override
            public void doInBackground() throws Exception {
                addToZipFile("MED", zos);
                List<MedicalRecord> medicalRecords = medicalRecordJpaController.findMedicalRecordEntities();
                List<Pasien> pasiens = pasienJpaController.findPasienEntities();
                List<Obat> obats = obatJpaController.findObatEntities();
                List<Log> logs = logJpaController.findLogEntities();

                ObjectMapper mapper = new ObjectMapper();
                byte[] pasien = CommonHelper.stringToByte(mapper.writeValueAsString(pasiens));
                byte[] medrec = CommonHelper.stringToByte(mapper.writeValueAsString(medicalRecords));
                byte[] obat = CommonHelper.stringToByte(mapper.writeValueAsString(obats));
                byte[] log = CommonHelper.stringToByte(mapper.writeValueAsString(logs));
                addToZipFile("a", pasien, zos);
                addToZipFile("b", medrec, zos);
                addToZipFile("c", obat, zos);
                addToZipFile("d", log, zos);

            }
        }, null, true);
        dialog.start();
    }

    private boolean convertZipJson(File file) {
        ObjectMapper objectMapper = new ObjectMapper();

        try (FileInputStream fis = new FileInputStream(file); ZipInputStream zipInputStream = new ZipInputStream(fis)) {
            ZipEntry zipEntry;
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
                    int data;
                    while ((data = zipInputStream.read()) != - 1) {
                        output.write(data);
                    }
                    switch (zipEntry.getName()) {
                        case "MED":
                            configModel = ConfigHelper.readConfig(new ByteArrayInputStream(output.toByteArray()));
                            if (!ZHash.hashSHA256(password).equals(configModel.getPassword())) {
                                return false;
                            }
                            break;
                        case "a":
                            pasiens = objectMapper.readValue(output.toByteArray(), new TypeReference<List<Pasien>>() {
                            });
                            break;
                        case "b":
                            medicalRecords = objectMapper.readValue(output.toByteArray(), new TypeReference<List<MedicalRecord>>() {
                            });
                            break;
                        case "c":
                            obats = objectMapper.readValue(output.toByteArray(), new TypeReference<List<Obat>>() {
                            });
                            break;
                        case "d":
                            logs = objectMapper.readValue(output.toByteArray(), new TypeReference<List<Log>>() {
                            });
                            break;
                    }
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(PengaturanBR.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    private void insertToDatabase() {
        try {
            for (Obat obat : obats) {
                if (obatJpaController.findObat(obat.getObatId()) == null) {
                    obatJpaController.create(obat);
                } else {
                    System.out.println("Obat dengan id " + obat.getObatId() + " sudah ada");
                }
            }
            for (Pasien pasien : pasiens) {
                if (pasienJpaController.findPasien(pasien.getPasId()) == null) {
                    pasienJpaController.create(pasien);
                    for (Rujukan rujukan : pasien.getRujukanList()) {
                        if (rujukanJpaController.findRujukan(rujukan.getRujId()) == null) {
                            rujukanJpaController.create(rujukan);
                        } else {
                            System.out.println("Rujukan dengan id " + rujukan.getRujId() + " sudah ada");
                        }
                    }
                } else {
                    System.out.println("Pasien dengan id " + pasien.getPasId() + " sudah ada");
                }
            }
            for (MedicalRecord medicalRecord : medicalRecords) {
                if (medicalRecordJpaController.findMedicalRecord(medicalRecord.getMedicalRecordPK()) == null) {
                    medicalRecordJpaController.create(medicalRecord);
                    anamnesaJpaController.create(medicalRecord.getAnamnesa());
                    pemeriksaanFisikJpaController.create(medicalRecord.getPemeriksaanFisik());
                    diagnosisJpaController.create(medicalRecord.getDiagnosis());
                    for (PemeriksaanPendukung p : medicalRecord.getPemeriksaanPendukungList()) {
                        pemeriksaanPendukungJpaController.create(p);
                    }
                    for (Dosis d : medicalRecord.getDiagnosis().getDosisList()) {
                        dosisJpaController.create(d);
                    }
                    for (FollowUp f : medicalRecord.getFollowUpList()) {
                        followUpJpaController.create(f);
                    }
                } else {
                    System.out.println("Medrec dengan id " + medicalRecord.getMedicalRecordPK().getMedId() + " sudah ada");
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(PengaturanBR.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void processRestore(final File file) {
        password = MessageHelper.addInputPasswordDialog("Pemeriksaan keamanan", "Masukan password ketika membackup file");
        System.out.println(password);
        LoadingDialog dialog = new LoadingDialog(new AsyncProgress() {
            @Override
            public void done() {
            }

            @Override
            public void doInBackground() throws Exception {
                if (convertZipJson(file)) {
                    insertToDatabase();
                } else {
                    MessageHelper.addErrorMessage("Error", "Password yang anda masukan salah");
                }
            }
        }, null, true);
        dialog.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1024, 720));
        setMinimumSize(new java.awt.Dimension(1024, 720));
        setPreferredSize(new java.awt.Dimension(1024, 720));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Backup & Restore");

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jtk/medicalrecord/image/back-blue.png"))); // NOI18N
        jButton1.setMaximumSize(new java.awt.Dimension(100, 30));
        jButton1.setMinimumSize(new java.awt.Dimension(100, 30));
        jButton1.setPreferredSize(new java.awt.Dimension(100, 30));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Backup");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Restore");

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jtk/medicalrecord/image/settings - backup-blue.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jtk/medicalrecord/image/settings - restore-blue.png"))); // NOI18N
        jButton3.setMaximumSize(new java.awt.Dimension(183, 159));
        jButton3.setMinimumSize(new java.awt.Dimension(183, 159));
        jButton3.setPreferredSize(new java.awt.Dimension(183, 159));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(268, 268, 268)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(319, 319, 319))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(286, 286, 286)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(204, 204, 204)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(203, 203, 203)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(251, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(735, 735, 735))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addGap(381, 381, 381))))
            .addGroup(layout.createSequentialGroup()
                .addGap(202, 202, 202)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addGap(332, 332, 332))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        MainFrame.instance.showPengaturan();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        JFileChooser chooser = new JFileChooser();
        // tampilkan file xls saja
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Medrec Backup File", "mb");
        chooser.setDialogTitle("Save Medrec Backup File");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.addChoosableFileFilter(filter);
        chooser.setApproveButtonText("Save");
        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            try {
                OutputStream os = null;

                //jika save dengan nama file di akhiri .xls
                if (chooser.getSelectedFile().getPath().substring(chooser.getSelectedFile().getPath().length() - 3).equalsIgnoreCase(".mb")) {
                    File file = new File(chooser.getSelectedFile().getPath());
                    if (file.exists()) {
                        int result = JOptionPane.showConfirmDialog(chooser, "File sudah ada, apakah anda ingin menimpanya ?", "Perhatian", JOptionPane.YES_NO_OPTION);
                        if (result == JOptionPane.YES_OPTION) {
                            os = new FileOutputStream(file);
                            try (ZipOutputStream zos = new ZipOutputStream(os)) {
                                processBackup(zos);
                            }
                        }
                    } else {
                        os = new FileOutputStream(file);
                        try (ZipOutputStream zos = new ZipOutputStream(os)) {
                            processBackup(zos);
                        }
                    }
                } else {
                    File file = new File(chooser.getSelectedFile().getPath() + ".mb");
                    if (file.exists()) {
                        int result = JOptionPane.showConfirmDialog(chooser, "File sudah ada, apakah anda ingin menimpanya ?", "Perhatian", JOptionPane.YES_NO_OPTION);
                        if (result == JOptionPane.YES_OPTION) {
                            os = new FileOutputStream(file);
                            try (ZipOutputStream zos = new ZipOutputStream(os)) {
                                processBackup(zos);
                            }
                        }
                    } else {
                        os = new FileOutputStream(file);
                        try (ZipOutputStream zos = new ZipOutputStream(os)) {
                            processBackup(zos);
                        }
                    }
                }
                if (os != null) {
                    os.close();
                }
                MessageHelper.addInfoMessage("Informasi", "Backup data berhasil");
            } catch (HeadlessException | FileNotFoundException ex) {
                JOptionPane.showMessageDialog(chooser, "Periksa kembali file, Mungkin file sedang digunakan oleh aplikasi lain");
                Logger.getLogger(ZIo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(chooser, "Periksa kembali file, Mungkin file sedang digunakan oleh aplikasi lain");
                Logger.getLogger(ZIo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        JFileChooser chooser = new JFileChooser();
        // tampilkan file xls saja
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Medrec Backup File", "mb");
        chooser.setDialogTitle("Open Medrec Backup File");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.addChoosableFileFilter(filter);
        chooser.setApproveButtonText("Open");
        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();
            processRestore(f);
        }
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    // End of variables declaration//GEN-END:variables
}
