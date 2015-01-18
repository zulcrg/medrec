///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.jtk.medicalrecord;
//
//import com.jtk.medicalrecord.entity.MedicalRecord;
//import com.jtk.medicalrecord.entity.MedicalRecordPK;
//import com.jtk.medicalrecord.jpacontroller.MedicalRecordJpaController;
//import java.io.IOException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import org.codehaus.jackson.map.ObjectMapper;
//
///**
// *
// * @author Zulkhair Abdullah D
// */
//public class Test {
//
//    private static final MedicalRecordJpaController medicalRecordJpaController = new MedicalRecordJpaController();
//
//    public static void main(String[] args) {
//        try {
//            MedicalRecord mr = medicalRecordJpaController.findMedicalRecord(new MedicalRecordPK("131511062", new Long("1421572511215")));
//            ObjectMapper mapper = new ObjectMapper();
//            System.out.println(mapper.writeValueAsString(mr));
//        } catch (IOException ex) {
//            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//}
