package service;

import dao.AddressDao;
import lombok.extern.slf4j.Slf4j;
import pages.MapPage;
import utils.TelegramPoster;

@Slf4j
public class AddressService {
    private AddressDao dao;

    public AddressService(AddressDao dao) {
        this.dao = dao;
    }

    public void checkAddresses() {
        dao.getAddresses().stream().forEach(
                address -> {
                    log.info("Opening map page");

                    try (MapPage mp = new MapPage()) {

                        mp.typeInAddress(address);
                        mp.clickSearch();

                        StringBuilder message = new StringBuilder();

                        log.info(String.format("Checking %s", address));
                        if (mp.getResults().isEmpty()) {
                            message.append(String.format("%s: в программе нет", address));
                        } else {
                            message.append(String.format("%s: в программе. Программа реновации распространяется на следующие адреса.", address) + "%0A");
                            mp.getResults()
                                    .forEach(e -> message.append(e.getText()).append("%0A"));
                        }

                        if (new TelegramPoster().sendToTelegram(message.toString())) {
                            log.info(String.format("Sending message for %s", address));
                            log.info(message.toString());
                        } else {
                            log.error(String.format("Could not send message for %s", address));
                        }
                    }
                }
        );

    }
}
