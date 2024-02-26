package at.example.backenddemo.model.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ProductType {
    // FLOWER
    FLOWER("Blume"),
    // CANDLE
    CANDLE("Kerze"),
    // JEWELRY
    JEWELRY("Schmuck"),

    // MEMORIAL PICTURES
    PICTURE("Bild"),
    // MEMENTOS
    MEMENTO("Memento");

    private final String name;
}
