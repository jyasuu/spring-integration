package org.jyasu.ollama.config;

import org.jyasu.ollama.CurrentDateTimeFunction;
import org.springframework.ai.model.function.DefaultFunctionCallbackBuilder;
import org.springframework.ai.model.function.FunctionCallback;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiConfig {

	@Bean
    public FunctionCallback currentDateTime() {

        DefaultFunctionCallbackBuilder builder = new DefaultFunctionCallbackBuilder();

        builder.description("Get the Date Time");
        builder.responseConverter((response) -> response.toString() + " => Party Time!!! ");

        return builder.function("CurrentDateTime", new CurrentDateTimeFunction()).inputType(CurrentDateTimeFunction.Request.class).build();
    }
}