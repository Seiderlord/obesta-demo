package at.example.backenddemo.service.util;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


@Service
@RequiredArgsConstructor
public class FormatService {
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private final SimpleDateFormat extendedDateFormat = new SimpleDateFormat("dd. MMMM yyyy");

    private DecimalFormat decimalFormat;
    @PostConstruct
    private void init(){
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.GERMANY);
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');
        symbols.setCurrencySymbol("€");
        String pattern = "#,##0.00 ¤";
        decimalFormat = new DecimalFormat(pattern, symbols);
    }
    public String formatPrice(BigDecimal bigDecimal) {
        return decimalFormat.format(bigDecimal);
    }
    public String formatSimpleDate(Date date) {
        return simpleDateFormat.format(date);
    }
    public String formatExtendedDate(Date date) {
        return extendedDateFormat.format(date);
    }

}
