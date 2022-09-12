import dao.AddressDaoImpl;
import lombok.extern.slf4j.Slf4j;
import service.AddressService;

@Slf4j
public class Main {
    public static void main(String[] args) {
        AddressService service = new AddressService(new AddressDaoImpl());
        service.checkAddresses();
    }
}

