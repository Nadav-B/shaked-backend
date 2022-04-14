package com.shaked.service.graphql.scalars;

import com.netflix.graphql.dgs.DgsScalar;
import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@DgsScalar(name = "Date")
public class DateScalar implements Coercing<Date, String> {
    @Override
    public String serialize(@NotNull Object dataFetcherResult) throws CoercingSerializeException {
        if (dataFetcherResult instanceof Date) {
            return dataFetcherResult.toString();
        } else {
            throw new CoercingSerializeException("Not a valid DateTime");
        }
    }

    @Override
    public @NotNull Date parseValue(@NotNull Object input) throws CoercingParseValueException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss a", Locale.ENGLISH);

        try {
            return formatter.parse(input.toString());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public @NotNull Date parseLiteral(@NotNull Object input) throws CoercingParseLiteralException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss a", Locale.ENGLISH);

        try {
            return formatter.parse(input.toString());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


    }
}
