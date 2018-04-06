package ir.alefmordad.makenger.core.converters;

import java.io.IOException;
import java.sql.SQLException;

public interface Converter<F, T> {

    T convert(F from) throws SQLException, IOException, ClassNotFoundException;

}
