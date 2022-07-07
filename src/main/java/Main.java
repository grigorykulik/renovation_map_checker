import addressChecker.AddressChecker;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@Slf4j
public class Main {
    public static void main(String[] args) {

        try {
            FileReader fr = new FileReader("addresses");
            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {
                AddressChecker.checkAddress(line);
                log.info(line);
            }

        } catch (FileNotFoundException fnfe) {
            log.error("Could not find the file");
        } catch (IOException e) {
            log.error("Could not read line");
        }
    }
}

