package utils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class TelegramPoster {
        public static void sendToTelegram(String text) throws IOException {

            String urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";
            String apiToken = "bot_id:bot_token";
            String chatId = "channel_id";

            urlString = String.format(urlString, apiToken, chatId, text);

            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();

            StringBuilder sb = new StringBuilder();
            InputStream is = new BufferedInputStream(conn.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String inputLine = "";
            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine);
            }
//            String response = sb.toString();
//      no need to handle response in any way at this stage
        }
}
