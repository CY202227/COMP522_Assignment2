
import java.security.MessageDigest;
import java.util.Scanner;

/**
 *
 */
public class MessageDigestSHA1
{
    public static void main(
            String[]    args)
            throws Exception
    {

        Scanner input = new Scanner(System.in);
        System.out.println("Enter plaintext");
        String Gettext = input.nextLine();

        MessageDigest   hash = MessageDigest.getInstance("SHA1");

        System.out.println("input : " + Gettext);

        hash.update(Utils.toByteArray(Gettext));

        System.out.println("digest : " + Utils.toHex(hash.digest()));


    }
}