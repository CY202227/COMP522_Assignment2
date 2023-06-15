import javax.crypto.Cipher;
import java.security.*;
import java.util.Scanner;

public class DSA {
    public static void main( String[]    args) throws Exception
    {

        //Sender
        //input_text from user
        Scanner input = new Scanner(System.in);
        System.out.println("Enter plaintext");
        String cleartext  = input.nextLine();
        String Wrong = "wrong";


        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        SecureRandom random = new SecureRandom();

        //SHA1
        byte[] hashed = MessageDigestExample.MessageDigest(cleartext);
        System.out.println("digest : " + Utils.toHex(hashed));

        // create the keys
        KeyPairGenerator generator = KeyPairGenerator.getInstance("DSA");
        Signature DSA = Signature.getInstance("SHA256withDSA");
        Signature Wrong_DSA = Signature.getInstance("SHA256withDSA");

        generator.initialize(2048,random);
        KeyPair pair = generator.generateKeyPair();
        PublicKey pubKey = pair.getPublic();
        PrivateKey privKey = pair.getPrivate();


        //Sign the hash using the private key
        DSA.initSign(privKey);
        DSA.update(hashed);
        byte[] signed = DSA.sign();

        System.out.println("DSA signature hash: \n" + Utils.toHex(signed));


        //Verifier
        String Message = "This is a message";

        //received plain text to verifier
        System.out.println("Plain text: \n" + cleartext);
        //System.out.println("Plain text: "+ Wrong);
        System.out.println("origin signature: \n" + Utils.toHex(signed));

        byte[] verier = MessageDigestExample.MessageDigest(cleartext);
        //byte[] wrong_sign = Wrong_DSA.sign();
        //start verify progress
        DSA.initVerify(pubKey);

        DSA.update(verier);

        if (DSA.verify(signed))
        {
            System.out.println("key match");
        }
        else
        {
            System.out.println("do not match");
        }
    }
}
