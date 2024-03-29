package addressChecker;

import lombok.extern.slf4j.Slf4j;
import pages.MapPage;
import utils.TelegramPoster;
import java.io.IOException;

@Slf4j
public class AddressChecker {
    private static final int SLEEP_TIMEOUT = 1000;

    public static void checkAddress(String address) {

        log.info("Opening map page");

        MapPage mp = MapPage.openPage();
        mp.typeInAddress(address);
        mp.clickSearch();

        StringBuilder message = new StringBuilder();

        log.info(String.format("Checking %s", address));
        if (mp.getResults().size() == 0) {
            message.append(String.format("%s: в программе нет", address));
        } else {
            message.append(String.format("%s: в программе. Программа реновации распространяется на следующие адреса.", address) + "%0A");
            mp.getResults()
                    .stream()
                    .forEach(e -> message.append(e.getText() + "%0A"));
        }

        try {
            TelegramPoster.sendToTelegram(message.toString());
            log.info(String.format("Sending message for %s", address));
            log.info(message.toString());
        } catch (IOException e) {
            log.error(String.format("Could not send message to telegram for address %s", address));
        } finally {
            mp.closePage();
        }

        try {
            Thread.sleep(SLEEP_TIMEOUT);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
