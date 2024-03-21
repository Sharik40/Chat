package academy.prog;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Users {
    public void addUser(String login) throws IOException {
        URL url = new URL(Utils.getURL() + "/getUsers");
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        try (OutputStream os = http.getOutputStream()) {
            os.write(login.getBytes(StandardCharsets.UTF_8));
            http.getResponseCode();
        }
    }

    public String getUsers() throws IOException {

        URL url = new URL(Utils.getURL() + "/getUsers");
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("GET");

        InputStream is = new BufferedInputStream(http.getInputStream());
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[10240];
        int r;

        do {
            r = is.read(buf);
            if (r > 0) bos.write(buf, 0, r);
        } while (r != -1);
        return bos.toString();
    }
}
