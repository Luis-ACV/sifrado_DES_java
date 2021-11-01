/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cifrado;

import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;
import sun.misc.BASE64Encoder;


public class cifrado_mensaje_des {

    //constructores
    public cifrado_mensaje_des() {
        
    }//algoritmoDes
   
    public static String cifrar(String txt, String clave) throws Exception
    {
                  // El algoritmo DES requiere una fuente confiable de números aleatorios
        SecureRandom sr = new SecureRandom();
                 // Crea un objeto DESKeySpec a partir de los datos clave originales
        DESKeySpec dks = new DESKeySpec(clave.getBytes("UTF-8"));
                 // Cree una fábrica de claves y utilícela para convertir DESKeySpec en un objeto SecretKey
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey key1 = keyFactory.generateSecret(dks);
                 // El objeto de cifrado realmente completa la operación de cifrado
        Cipher cipher = Cipher.getInstance("DES");
                 // Inicializa el objeto Cipher con la clave
        cipher.init(Cipher.ENCRYPT_MODE, key1, sr);
                 // Ahora, obtenga los datos y cifrelos
        byte encryptedData[] = cipher.doFinal(clave.getBytes("UTF-8"));
                 // Codificar en forma de creación de personajes a través de BASE64 bit
        String base64Str = new BASE64Encoder().encode(encryptedData);
        
        System.out.println(base64Str);
        
        return base64Str;

    }
    
    public static String descifrar(byte[] bufferCifrado, String clave) throws Exception
    {
        SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
        DESKeySpec kspec = new DESKeySpec(clave.getBytes());
        SecretKey ks = skf.generateSecret(kspec);

        Cipher cifrado = Cipher.getInstance("DES");
        cifrado.init(Cipher.DECRYPT_MODE, ks);

        String s1 = null;
        String s2 = "";

        try {
        s1 = new String(cifrado.update(bufferCifrado), "ISO-8859-1");
        s2 = new String(cifrado.doFinal(), "ISO-8859-1");
        }
        catch (Exception e) {
        System.err.println("Excepcion controlada descifrando: " + e.toString());
        }

        return s1 + s2;
    }
    

}

