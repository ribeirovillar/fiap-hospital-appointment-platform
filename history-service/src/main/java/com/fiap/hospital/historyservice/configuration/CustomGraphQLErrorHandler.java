package com.fiap.hospital.historyservice.configuration;

import com.fiap.hospital.historyservice.exception.AppointmentPermissionException;
import graphql.GraphQLError;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class CustomGraphQLErrorHandler extends DataFetcherExceptionResolverAdapter {

    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        if (ex instanceof AppointmentPermissionException) {
            return GraphQLError.newError()
                    .message(ex.getMessage())
                    .locations(Collections.singletonList(env.getField().getSourceLocation()))
                    .path(env.getExecutionStepInfo().getPath())
                    .build();
        }
        return null;
    }
}