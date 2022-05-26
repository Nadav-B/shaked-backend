package com.shaked.service.excepctions;

import graphql.ErrorClassification;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.List;

public class NotFoundException  extends RuntimeException implements GraphQLError {


    public NotFoundException(String id) {
        super(String.format("The %s you are trying to access with id: %s was not found.", id));
    }


    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorClassification getErrorType() {
        return ErrorType.DataFetchingException;

    }
}
