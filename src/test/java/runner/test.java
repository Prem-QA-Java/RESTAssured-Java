package runner;

import utilities.xmlFileReadAndWrite;

public class test {
    public static void main(String[] args) {
        xmlFileReadAndWrite xmlFileReadAndWrite = new xmlFileReadAndWrite();
        xmlFileReadAndWrite.changeInResponse("<!DOCTYPE html>\r\n" + //
                        "\r\n" + //
                        "<html>\r\n" + //
                        "\r\n" + //
                        "<head>\r\n" + //
                        "    <title>404</title>\r\n" + //
                        "    <link rel=\"icon\" href=\"/img/thinkingTesterIcon.png\">\r\n" + //
                        "    <link rel=\"stylesheet\" href=\"/css/styles.css\">\r\n" + //
                        "</head>\r\n" + //
                        "\r\n" + //
                        "<body>\r\n" + //
                        "    <div>\r\n" + //
                        "        <header>\r\n" + //
                        "            <h1>Page Not Found</h1>\r\n" + //
                        "            <button id='logout' class='logout' onclick=\"location.href='/logout'\">Logout</button>\r\n" + //
                        "        </header>\r\n" + //
                        "\r\n" + //
                        "\r\n" + //
                        "    </div>\r\n" + //
                        "    <footer>\r\n" + //
                        "        <p>Created by Kristin Jackvony, Copyright 2021 </p>\r\n" + //
                        "        <img src = \"/img/thinkingTesterLogo.png\">\r\n" + //
                        "    </footer>\r\n" + //
                        "\r\n" + //
                        "</body>\r\n" + //
                        "\r\n" + //
                        "</html>");
    }
}
