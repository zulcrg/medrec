/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtk.medicalrecord.util;

/**
 *
 * @author Zulkhair
 */
public interface AsyncProgress {
    public void done();
    public void doInBackground() throws Exception;
}
