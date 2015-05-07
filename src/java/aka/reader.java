/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aka;

import java.io.File;

/**
 *
 * @author Athinodoros
 */
public class reader {
    public static void main(String[] args) {
        File folder = new File("C:\\Users\\Athinodoros\\Desktop\\testttttttttttttttttt");
        File[] files = folder.listFiles();
        for (File file : files) {
            System.out.println();
            System.out.println(file.getAbsolutePath());
            System.out.println(System.getProperty("user.dir"));
        }
    }
    
}
