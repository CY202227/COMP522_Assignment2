import java.security.MessageDigest;

/**
 *
 */
public class MessageDigestExample
{
    public static void main(
            String[]    args)
            throws Exception
    {}
    public static byte[] MessageDigest(String input) throws Exception{

        MessageDigest   hash = MessageDigest.getInstance("SHA1");

        hash.update(Utils.toByteArray(input));

        return(hash.digest());
    }
}