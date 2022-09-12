import addressChecker.AddressChecker;
import dao.AddressDaoImpl;
import lombok.extern.slf4j.Slf4j;
import service.AddressService;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@Slf4j
public class Main {
    public static void main(String[] args) {
        AddressService service = new AddressService(new AddressDaoImpl());
        service.checkAddresses();
    }
}

