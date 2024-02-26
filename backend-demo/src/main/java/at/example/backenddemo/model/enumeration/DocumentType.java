package at.example.backenddemo.model.enumeration;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DocumentType {

    IDENTITY("Ausweis"),
    PORTRAIT("Portrait"),
    IMAGE("Bild"),
    INVOICE("Rechnung");
    private final String name;
}
