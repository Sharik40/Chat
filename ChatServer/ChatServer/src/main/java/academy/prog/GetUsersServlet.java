package academy.prog;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.nio.charset.StandardCharsets;
public class GetUsersServlet extends HttpServlet {
    private final UsersList users = UsersList.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        users.add(new Users("Vlad"));
        resp.setContentType("application/json");

        String json = users.toJSON();
        PrintWriter pw = resp.getWriter();
        pw.print(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        InputStream is = req.getInputStream();
        try {
            byte[] buf = responseBodyToArray(is);
            String strBuf = new String(buf, StandardCharsets.UTF_8);

            users.add(strBuf);
        } finally {
            is.close();
        }
    }

    private byte[] responseBodyToArray(InputStream is) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[10240];
        int r;

        do {
            r = is.read(buf);
            if (r > 0) bos.write(buf, 0, r);
        } while (r != -1);

        return bos.toByteArray();
    }
}
