package dev.joseluisgs.locale;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class LocaleEs {
    private static final Locale localeEs = Locale.forLanguageTag("es-ES");


    public static String getPrecioFormatted(double precio) {
        // Formateamos el precio a un formato localizado
        NumberFormat nf = NumberFormat.getCurrencyInstance(localeEs);
        return nf.format(precio);
    }

    public static String getFechaFormatted(LocalDate date) {
        // Formateamos la fecha LocalDate a un formato localizado
        // return date.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return date.format(
                DateTimeFormatter
                        .ofLocalizedDate(FormatStyle.SHORT).withLocale(localeEs)
        );
    }
}
