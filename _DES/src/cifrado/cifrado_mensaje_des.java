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
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


public class cifrado_mensaje_des {

    //constructores
    public cifrado_mensaje_des() {
        
    }//algoritmoDes
    
    
    public static String cifrar(String txtmensaje, String clave) throws Exception
    {
        
        // generacion de numeros aleatorios
        SecureRandom sr = new SecureRandom();
        // creacion de objeto des
        DESKeySpec dks = new DESKeySpec(clave.getBytes("UTF-8"));
        // general clave
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey key1 = keyFactory.generateSecret(dks);
        
            // inicializamos el cifrado
        Cipher cipher = Cipher.getInstance("DES");
            // Inicializa el objeto Cipher con la clave
        cipher.init(Cipher.ENCRYPT_MODE, key1, sr);
            // Ahora, obtenga los datos y cifrados
        byte encryptedData[] = cipher.doFinal(txtmensaje.getBytes("UTF-8"));
        
        String base64Str = new BASE64Encoder().encode(encryptedData);
      
        return base64Str;

    }//cifrar
    
    public static String descifrar(String mensaje, String clave) throws Exception
    {
        //genera secuencia de numeros
        SecureRandom sr = new SecureRandom();
        //utiliza la llave para el desepaquetado
        DESKeySpec dks = new DESKeySpec(clave.getBytes());
        //crear los objetos des
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey key1 = keyFactory.generateSecret(dks);
        
        //inciamos el decifrado
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, key1, sr);
        //convierte el mensaje sifrado en una matrz byte
        byte[] encryptedData = new BASE64Decoder().decodeBuffer(mensaje);
        //usamos des para el desifrado
        byte decryptedData[] = cipher.doFinal(encryptedData);
        return new String(decryptedData,"UTF-8");
        
    }//descifrar
    

}

 
