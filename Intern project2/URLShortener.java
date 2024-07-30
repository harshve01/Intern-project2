import java.util.HashMap;
import java.util.Map;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class URLShortener {
    private Map<String, String> urlToShortLink;
    private MessageDigest md;

    public URLShortener() {
        urlToShortLink = new HashMap<>();
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public String shortenURL(String longURL) {
        String hash = generateHash(longURL);
        String shortLink = hash.substring(0, 7); // Use the first 7 characters as the short link
        urlToShortLink.put(longURL, shortLink);
        return shortLink;
    }

    public String expandURL(String shortLink) {
        return urlToShortLink.getOrDefault(shortLink, "Invalid short link");
    }

    private String generateHash(String input) {
        byte[] digest = md.digest(input.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        URLShortener shortener = new URLShortener();
        String longURL = "https://www.example.com";
        String shortLink = shortener.shortenURL(longURL);
        System.out.println("Short link for " + longURL + ": " + shortLink);

        String expandedURL = shortener.expandURL(shortLink);
        System.out.println("Expanded URL for " + shortLink + ": " + expandedURL);
    }
}
