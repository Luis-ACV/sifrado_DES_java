/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cifrado;

import java.security.*;

import javax.crypto.*;
import javax.crypto.interfaces.*;
import javax.crypto.spec.*;

import java.io.*;

public class EjemploDES {
	/*  Ejemplo de uso de funciones de resumen Hash
	 *    carga el fichero que recibe como parametro, lo cifra y lo descifra
	 */
	public static void main(String[] args) throws Exception {
		// Comprobar argumentos
		if (args.length != 1) {
			mensajeAyuda();
			System.exit(1);
		}
		
		/* Cargar "provider" (sÃ³lo si no se usa el que viene por defecto) */
		// Security.addProvider(new BouncyCastleProvider());  // Usa provider BC
		// 
		
		/* PASO 1: Crear e inicializar clave */
		
		System.out.println("1. Generar clave DES");
		KeyGenerator generadorDES = KeyGenerator.getInstance("DES");
		generadorDES.init(56); // clave de 56 bits
		SecretKey clave = generadorDES.generateKey();

		System.out.println("CLAVE:");
		mostrarBytes(clave.getEncoded());
		System.out.println();

		/* PASO 2: Crear cifrador */
		Cipher cifrador = Cipher.getInstance("DES/ECB/PKCS5Padding");
		// Algoritmo DES
		// Modo : ECB (Electronic Code Book)
		// Relleno : PKCS5Padding
		// 
		
		/*****************************************************************/
		System.out.println("2. Cifrar con DES el fichero "+args[0]+
				           ", dejar el resultado en "+args[0]+".cifrado");

		/* PASO 3a: Inicializar cifrador en modo CIFRADO */
		cifrador.init(Cipher.ENCRYPT_MODE, clave);
		
		/* Leer fichero de 1k en 1k y pasar fragmentos leidos al cifrador */
		byte[] buffer = new byte[1000];
		byte[] bufferCifrado;

		FileInputStream in = new FileInputStream(args[0]);
		FileOutputStream out = new FileOutputStream(args[0]+".cifrado");
		
		int bytesLeidos = in.read(buffer, 0, 1000);
		while (bytesLeidos != -1) { // Mientras no se llegue al final del fichero
			bufferCifrado = cifrador.update(buffer, 0, bytesLeidos); // Pasa texto claro leido al cifrador
			out.write(bufferCifrado); // Escribir texto cifrado
			bytesLeidos = in.read(buffer, 0, 1000);
		}
		bufferCifrado = cifrador.doFinal(); // Completar cifrado (procesa relleno, puede devolver texto)
		out.write(bufferCifrado); // Escribir final del texto cifrado (si lo hay) 
		
		in.close();
		out.close();
		
		/*****************************************************************/
		System.out.println("3. Descifrar con DES el fichero "+args[0]+".cifrado"+
		           ", dejar el resultado en "+args[0]+".descifrado");
		
		/* PASO 3b: Poner cifrador en modo DESCIFRADO */
		cifrador.init(Cipher.DECRYPT_MODE, clave);

		in = new FileInputStream(args[0]+".cifrado");
		out = new FileOutputStream(args[0]+".descifrado");
		byte[] bufferPlano;
		
		bytesLeidos = in.read(buffer, 0, 1000);
		while (bytesLeidos != -1) { // Mientras no se llegue al final del fichero
			bufferPlano = cifrador.update(buffer, 0, bytesLeidos); // Pasa texto claro leido al cifrador
			out.write(bufferPlano); // Escribir texto descifrado
			bytesLeidos = in.read(buffer, 0, 1000);
		}
		bufferPlano = cifrador.doFinal(); // Completar descifrado (procesa relleno, puede devolver texto)
		out.write(bufferPlano); // Escribir final del texto descifrado (si lo hay)
		
		in.close();
		out.close();
	} // Fin main()

	public static void mostrarBytes(byte [] buffer) {
		System.out.write(buffer, 0, buffer.length);
	} 
	
	public static void mensajeAyuda() {
		System.out.println("Ejemplo cifrado DES");
		System.out.println("\tSintaxis:   java EjemploDES fichero");
		System.out.println();
	}

}