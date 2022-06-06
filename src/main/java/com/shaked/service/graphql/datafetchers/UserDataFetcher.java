package com.shaked.service.graphql.datafetchers;


import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;

import org.springframework.security.access.annotation.Secured;


@DgsComponent
public class UserDataFetcher {

    @DgsQuery
    @Secured("ROLE_ADMIN")
    public Boolean isAuthenticated() {
        return Boolean.TRUE;
    }
}
