package com.shaked.service.graphql.scalars;

import com.netflix.graphql.dgs.DgsScalar;
import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;

@DgsScalar(name = "Long")
public class LongScalar implements Coercing<Long, String> {
    @Override
    public String serialize(Object dataFetcherResult) throws CoercingSerializeException {
        if (dataFetcherResult instanceof Long) {
            return dataFetcherResult.toString();
        } else {
            throw new CoercingSerializeException("Not a valid Long");
        }
    }

    @Override
    public Long parseValue(Object input) throws CoercingParseValueException {
        return Long.valueOf(input.toString());
    }

    @Override
    public Long parseLiteral(Object input) throws CoercingParseLiteralException {
        if (input instanceof StringValue) {
            return Long.valueOf(input.toString());
        }

        throw new CoercingParseLiteralException("Value is not a valid ISO date time");
    }
}