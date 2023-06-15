import javax.crypto.Cipher;
        import java.security.*;
        import java.util.Scanner;


public class RSA_SHA1 {
    public static void main( String[]    args) throws Exception
    {

        //Sender
        Scanner input = new Scanner(System.in);
        System.out.println("Enter plaintext");
        String Gettext = input.nextLine();
        //String Wrong = "wrong";
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

        //SHA1
        byte[] hashed = MessageDigestExample.MessageDigest(Gettext);
        SecureRandom random = new SecureRandom();


        // create the keys
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");

        generator.initialize(512,random);

        KeyPair pair = generator.generateKeyPair();
        Key pubKey = pair.getPublic();
        Key privKey = pair.getPrivate();

        // encryption step

        cipher.init(Cipher.ENCRYPT_MODE, pubKey);

        byte[] cipherText = cipher.doFinal(hashed);

        System.out.println("cipher : " + Utils.toHex(cipherText));
        System.out.println("Message sent was : " + Gettext);

        //Verifier
        cipher.init(Cipher.DECRYPT_MODE, privKey);

        byte[] plainText = cipher.doFinal(cipherText);
        String Sender_digest = Utils.toHex(plainText) ;
        System.out.println("Message received: "+ Gettext);
        //System.out.println("Message received: "+ Wrong);
        byte[] Verifier_Input = MessageDigestExample.MessageDigest(Gettext);
        String Verifier_digest = Utils.toHex(Verifier_Input);

        if (Verifier_digest.equals(Sender_digest))
        {
            System.out.println("Value match\n"+ "Sender hash :" + Sender_digest +"\nVerifier has : "+Verifier_digest);
        }
        else
        {
            System.out.println("Value not match\n"+ "Sender hash :" + Sender_digest +"\nVerifier has : "+Verifier_digest);
        }
    }
}
