import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Pattern californiaZipRegex=Pattern.compile("CA 9(([0-5]\\d)|6[01])\\d{2}");
        Matcher zip1=californiaZipRegex.matcher("CA 95131");
        Matcher zip2=californiaZipRegex.matcher(("CA 96231"));
        System.out.println(zip1.matches());
        System.out.println(zip2.matches());

        Pattern coloradoMobileRegex=Pattern.compile("(303|719|720|970)-\\d{3}-\\d{4}");
        Matcher mobile1= coloradoMobileRegex.matcher("303-592-3808");
        Matcher mobile2=coloradoMobileRegex.matcher(("408-592-3808"));
        System.out.println(mobile1.matches());
        System.out.println(mobile2.matches());

        Pattern urlRegex=Pattern.compile("^(https?)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]");
        Matcher url1= urlRegex.matcher("https://www.cnblogs.com/speeding/p/5097790.html");
        Matcher url2=urlRegex.matcher(("github.com"));
        System.out.println(url1.matches());
        System.out.println(url2.matches());

        Pattern emailRegex=Pattern.compile("^[A-Za-z0-9_.]+@[A-Za-z-]+.[A-Za-z]+");
        Matcher email1= emailRegex.matcher("he.shuyao@northeastern.edu");
        Matcher email2=emailRegex.matcher(("lili-123@gmail.com"));
        System.out.println(email1.matches());
        System.out.println(email2.matches());

        Pattern ipv4Regex=Pattern.compile("(((\\d{1,2})|(1\\d{2})|(2[0-4]\\d)|(25[0-5]))\\.){3}((\\d{1,2})|(1\\d{2})|(2[0-4]\\d)|(25[0-5]))");
        Matcher ipv41= ipv4Regex.matcher("192.168.0.0");
        Matcher ipv42=ipv4Regex.matcher(("256.1.202.227"));
        System.out.println(ipv41.matches());
        System.out.println(ipv42.matches());
}}