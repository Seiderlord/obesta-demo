package at.example.backenddemo.model.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public enum TaxCategory {
    STANDARD(BigDecimal.valueOf(0.20), "USt 20%"), // 20% tax rate
    REDUCED_10(BigDecimal.valueOf(0.10), "USt 10%"), // 10% tax rate
    REDUCED_13(BigDecimal.valueOf(0.13), "USt 13%"), // 10% tax rate
    ZERO(BigDecimal.ZERO, "USt 0%"), // 0% tax rate
    EXEMPT(BigDecimal.ZERO, "USt befreit"); // tax exempt

    private final BigDecimal rate;
    private final String name;
}

