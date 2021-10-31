/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cifrado;

import java.security.*;
import java.security.interfaces.*;
import java.security.spec.*;

import javax.crypto.*;
import javax.crypto.interfaces.*;
import javax.crypto.spec.*;
import java.io.*;

public class algoritmoDes {
   
    public void generadorClaves(){
        try {
            KeyGenerator llavepriva = KeyGenerator.getInstance("DES");
            llavepriva.init(56);
            SecretKey clave = llavepriva.generateKey();
            
            
            
        } catch (Exception e) {
            
        }
        
    }
    


}

