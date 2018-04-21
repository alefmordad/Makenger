package ir.alefmordad.makenger.core.util.converters.rowmapper;

import ir.alefmordad.makenger.core.util.converters.Converter;

import java.sql.ResultSet;

public interface RowMapper<T> extends Converter<ResultSet, T> {
}
