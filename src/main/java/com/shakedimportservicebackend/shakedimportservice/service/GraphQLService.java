package com.shakedimportservicebackend.shakedimportservice.service;


import com.shakedimportservicebackend.shakedimportservice.repo.ArticleRepository;
import com.shakedimportservicebackend.shakedimportservice.service.datafetcher.ArticleDataFetcher;
import com.shakedimportservicebackend.shakedimportservice.service.datafetcher.ArticlesDataFetcher;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Service
public class GraphQLService {

    @Autowired
    ArticleRepository articleRepository;

    @Value("classpath:articles.graphql")
    Resource resource;

    private GraphQL graphQL;
    @Autowired
    private ArticlesDataFetcher articlesDataFetcher;
    @Autowired
    private ArticleDataFetcher articleDataFetcher;

    // load schema at application start up
    @PostConstruct
    private void loadSchema() throws IOException {

        File schemaFile = resource.getFile();
        // parse schema
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("articles", articlesDataFetcher)
                        .dataFetcher("article", articleDataFetcher))
                .build();
    }


    public GraphQL getGraphQL() {
        return graphQL;
    }
}