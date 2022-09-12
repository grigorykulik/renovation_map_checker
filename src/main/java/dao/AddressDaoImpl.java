package dao;

import addressChecker.AddressChecker;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class AddressDaoImpl implements AddressDao {

    @Override
    public List<String> getAddresses() {
        List<String> addresses = new ArrayList<>();

        try (FileReader fr = new FileReader("addresses");
             BufferedReader br = new BufferedReader(fr)) {

            String line;

            while ((line = br.readLine()) != null) {
                addresses.add(line);
                log.info(line);
            }

        } catch (FileNotFoundException fnfe) {
            log.error("Could not find the address file.");
        } catch (IOException e) {
            log.error("Could not read line from the address file.");
        }

        return addresses;
    }
}
