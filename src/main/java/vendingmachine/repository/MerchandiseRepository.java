package vendingmachine.repository;

import vendingmachine.domain.Merchandise;
import vendingmachine.message.ErrorMessage;

import java.util.ArrayList;
import java.util.List;

public class MerchandiseRepository {

    private static MerchandiseRepository merchandiseRepository = new MerchandiseRepository();
    private List<Merchandise> merchandises = new ArrayList<>();

    private MerchandiseRepository() {
    }

    public static MerchandiseRepository getInstance() {
        return merchandiseRepository;
    }

    public void save(Merchandise merchandise) {
        merchandises.add(merchandise);
    }

    public int getMinPrice() {
        return merchandises.stream()
                .min((merchandise1, merchandise2) -> merchandise1.compareTo(merchandise2))
                .get().getPrice();
    }

    public boolean isSoldOut() {
        boolean isExist = merchandises.stream()
                .anyMatch(merchandise -> merchandise.getQuantity() != 0);
        return !isExist;
    }

    public Merchandise findByName(String buyMerchandiseName) {
        return merchandises.stream()
                .filter(merchandise -> merchandise.getName().equals(buyMerchandiseName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.NOT_FOUND_MERCHANDISE.getErrorMessage()));
    }
}
