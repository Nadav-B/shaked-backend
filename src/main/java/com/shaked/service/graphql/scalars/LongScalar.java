package com.shaked.service.graphql.scalars;

import com.netflix.graphql.dgs.DgsScalar;
import graphql.language.IntValue;
import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;

@DgsScalar(name = "Long")
public class LongScalar implements Coercing<Long, Float> {
    @Override
    public Float serialize(Object dataFetcherResult) throws CoercingSerializeException {
        if (dataFetcherResult instanceof Long) {
            return Float.valueOf(dataFetcherResult.toString());
        } else {
            throw new CoercingSerializeException("Not a valid Long");
        }
    }

    @Override
    public Long parseValue(Object input) throws CoercingParseValueException {
        return Long.parseLong(input.toString());
    }

    @Override
    public Long parseLiteral(Object input) throws CoercingParseLiteralException {
        if (input instanceof IntValue) {
            return Long.valueOf(((IntValue) input).getValue().toString());
        }

        throw new CoercingParseLiteralException("Value is not a valid Long");
    }
}