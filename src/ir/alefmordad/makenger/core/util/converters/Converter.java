package ir.alefmordad.makenger.core.util.converters;

import java.sql.SQLException;

public interface Converter<F, T> {

    T convert(F from) throws SQLException;

}
