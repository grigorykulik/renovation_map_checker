package utils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.MalformedInputException;
import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

@Slf4j
public class TelegramPoster {
    private static final PropertiesConfiguration config = new PropertiesConfiguration();
    private static String urlString;
    private static String apiToken;
    private static String chatId;

    public TelegramPoster() {
        try {
            config.load("src/main/resources/application.properties");
            urlString = config.getString("BOT_ENDPOINT_URL");
            apiToken = config.getString("BOT_API_TOKEN");
            chatId = config.getString("CHAT_ID");
        } catch (ConfigurationException e) {
            log.error(Arrays.toString(e.getStackTrace()));
        }
    }

    public boolean sendToTelegram(String text) {
        urlString = String.format(urlString, apiToken, chatId, text);

        try {
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();

            try (InputStream is = new BufferedInputStream(conn.getInputStream());
                 BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

                StringBuilder sb = new StringBuilder();
                String inputLine = "";

                while ((inputLine = br.readLine()) != null) {
                    sb.append(inputLine);
                }

                String response = sb.toString();
                log.info(response);

                return true;
            }

        } catch (IOException e) {
            log.error(Arrays.toString(e.getStackTrace()));
        }

        return false;
    }
}
