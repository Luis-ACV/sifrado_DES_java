/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cifrado;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
/**
 *
 * @author luisc
 */
public class cifrado_archivos_des {
    public static byte[] cifrar(String txt, String clave) throws Exception
{
SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
DESKeySpec kspec = new DESKeySpec(clave.getBytes());
SecretKey ks = skf.generateSecret(kspec);

Cipher cifrado = Cipher.getInstance("DES");
cifrado.init(Cipher.ENCRYPT_MODE, ks);

String s1 = null;
String s2 = "";

try {
s1 = new String(cifrado.update(txt.getBytes()), "ISO-8859-1");
s2 = new String(cifrado.doFinal(), "ISO-8859-1");
}
catch (Exception e) {
System.err.println("Excepcion controlada cifrando: " + e.toString());
}

return (s1+s2).getBytes("ISO-8859-1");
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



public static void main(String [] args)
{
try {
String texto = "En un lugar de la mancha de cuyo nombre no quiero acordarme...";
String clave1 = "123456";
String clave2 = "123456";

byte[] bb = cifrar(texto+texto+texto, clave1);

 for (byte b: bb) {
 System.out.print(b+" ");
 }
 System.out.println();

String txt = descifrar(bb, clave2);

System.out.println( "jols" + txt);
}
catch (Exception e) {
e.printStackTrace();
}
}

}
