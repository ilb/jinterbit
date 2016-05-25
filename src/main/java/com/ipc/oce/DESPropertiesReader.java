/**
 * 
 */
package com.ipc.oce;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Properties;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jcifs.util.Base64;

/**
 * @author Konovalov
 * 
 */
public class DESPropertiesReader extends PropertiesReader {

	private static final transient Log LOG = LogFactory.getLog(DESPropertiesReader.class);
	
	/**
	 * @param file
	 * @throws IOException
	 */
	public DESPropertiesReader(final File file) throws IOException {
		super(file);
	}

	public static byte[] generateBytes() {
		byte[] bytes = null;
		try {
			InetAddress address = InetAddress.getLocalHost();
			NetworkInterface ni = NetworkInterface.getByInetAddress(address);
			if (ni != null) {
				bytes = ni.getHardwareAddress();
			} else {
				LOG.error("Network Interface for the specified address is not found.");
			}
		} catch (UnknownHostException e) {
			throw new IllegalStateException(e);
		} catch (SocketException e) {
			throw new IllegalStateException(e);
		}
		return bytes;
	}

	public static void main(String[] args) {
		try {
			String sMode = args[0];
			if (sMode.trim().equals("-enc")) {

				if (args.length < 3) {
					throw new Exception(
							"\nWrong parameters. \n\tUse: java -cp oce-core.jar;jcifs.jar com.ipc.oce.DESPropertiesReader <mode> <source_file_path> <dest_file_path> [<DES key>]");
				}

				File fs = new File(args[1]);
				File fd = new File(args[2]);

				System.out.println("Source file: " + fs.getAbsolutePath());
				System.out.println("Destination file: " + fd.getAbsolutePath());
				if (args.length == 4)
					encode(fs, fd, args[3].getBytes());
				else
					encode(fs, fd);
				System.out.println("Encoding succecced");
			} else {
				System.out.println("Unknown mode.");
			}
		} catch (Exception e) {
			System.out.println("ERROR: " + e.toString());
		}
	}

	public static void encode(File sourceFile, File destFile)
			throws IOException, InvalidKeyException, NoSuchAlgorithmException,
			InvalidKeySpecException, NoSuchPaddingException,
			IllegalBlockSizeException, BadPaddingException {
		encode(sourceFile, destFile, generateBytes());
	}

	protected static void encode(File sourceFile, File destFile, byte[] bkey)
			throws IOException, InvalidKeyException, NoSuchAlgorithmException,
			InvalidKeySpecException, NoSuchPaddingException,
			IllegalBlockSizeException, BadPaddingException {
		FileReader fReaderSource = new FileReader(sourceFile);
		StringBuffer fileData = new StringBuffer(2048);

		char[] buf = new char[1024];
		int numRead = 0;
		while ((numRead = fReaderSource.read(buf)) != -1) {
			String readData = String.valueOf(buf, 0, numRead);
			fileData.append(readData);
			buf = new char[1024];
		}

		fReaderSource.close();

		DESKeySpec keySpec = null;
		keySpec = new DESKeySpec(bkey);

		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey key = keyFactory.generateSecret(keySpec);

		byte[] cleartext = fileData.toString().getBytes("UTF8");
		Cipher cipher1 = Cipher.getInstance("DES"); // cipher is not thread safe
		String encrypedData = null;
		synchronized (cipher1) {
			cipher1.init(Cipher.ENCRYPT_MODE, key);
			encrypedData = Base64.encode(cipher1.doFinal(cleartext));
		}
		/*System.out
				.println("\n>>>>>>>>>>>>>> ENCRYPTED DATA START >>>>>>>>>>>>>>>>>>>>");
		System.out.println(encrypedData);
		System.out
				.println("<<<<<<<<<<<<<< ENCRYPTED DATA END <<<<<<<<<<<<<<<<<<<<<<<\n ");*/

	}
	
	@Override
	protected Properties readProperties(InputStream stream) throws IOException {
		throw new RuntimeException("Encripted files not yet implemented over InputStream");
	}

	@Override
	protected Properties readProperties(Reader reader) throws IOException {

		StringBuffer fileData = new StringBuffer(2048);

		char[] buf = new char[1024];
		int numRead = 0;
		while ((numRead = reader.read(buf)) != -1) {
			String readData = String.valueOf(buf, 0, numRead);
			fileData.append(readData);
			buf = new char[1024];
		}

		reader.close();

		try {

			DESKeySpec keySpec = null;
			keySpec = new DESKeySpec(generateBytes());

			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey key = keyFactory.generateSecret(keySpec);

			String encrypedContentFile = fileData.toString();

			// DECODE encrypted String

			byte[] encrypedBytes = Base64.decode(encrypedContentFile);
			Cipher cipher = Cipher.getInstance("DES"); // cipher is not thread
			// safe
			synchronized (cipher) {
				cipher.init(Cipher.DECRYPT_MODE, key);
				byte[] plainTextPwdBytes = cipher.doFinal(encrypedBytes);
				
				reader = new StringReader(new String(plainTextPwdBytes));
			}
		} catch (InvalidKeyException e) {
			throw new IOException(e);
		} catch (InvalidKeySpecException e) {
			throw new IOException(e);
		} catch (NoSuchAlgorithmException e) {
			throw new IOException(e);
		} catch (NoSuchPaddingException e) {
			throw new IOException(e);
		} catch (IllegalBlockSizeException e) {
			throw new IOException(e);
		} catch (BadPaddingException e) {
			throw new IOException(e);
		}

		return super.readProperties(reader);
	}

}
