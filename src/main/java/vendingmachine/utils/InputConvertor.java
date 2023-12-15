package vendingmachine.utils;

import vendingmachine.dto.MerchandiseDto;
import vendingmachine.message.ErrorMessage;

import java.util.ArrayList;
import java.util.List;

public class InputConvertor {

    private static InputConvertor inputConvertor = new InputConvertor();

    private InputConvertor() {
    }

    public static InputConvertor getInstance() {
        return inputConvertor;
    }

    public int convertStringToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.NOT_NUMBER_AMOUNT.getErrorMessage());
        }
    }

    public List<MerchandiseDto> convertStringToMerchandiseDtos(String input) {
        List<MerchandiseDto> merchandiseDtos = new ArrayList<>();
        String[] splitInput = input.split(";");
        for (String str : splitInput) {
            String merchandiseInfo = str.replace("[", "").replace("]", "");
            String name = merchandiseInfo.split(",")[0].trim();
            int price = convertStringToInt(merchandiseInfo.split(",")[1].trim());
            int quantity = convertStringToInt(merchandiseInfo.split(",")[2].trim());
            merchandiseDtos.add(new MerchandiseDto(name, price, quantity));
        }
        return merchandiseDtos;
    }
}
