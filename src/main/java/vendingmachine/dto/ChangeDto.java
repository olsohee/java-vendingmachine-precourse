package vendingmachine.dto;

import java.util.Map;

public class ChangeDto {

    private final Map<Integer, Integer> changes;

    public ChangeDto(Map<Integer, Integer> changes) {
        this.changes = changes;
    }

    public Map<Integer, Integer> getChanges() {
        return changes;
    }
}
