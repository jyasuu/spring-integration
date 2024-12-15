package org.jyasu.ollama.config;

import org.jyasu.ollama.CurrentDateTimeFunction;
import org.jyasu.ollama.function.ProductFunction.Request;
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

    
	@Bean
    public FunctionCallback productSalesInfo() {
        DefaultFunctionCallbackBuilder builder =  new DefaultFunctionCallbackBuilder();
        builder.description("Get the products sales volume at year");
        builder.responseConverter((response) -> ((org.jyasu.ollama.function.ProductFunction.Response)response).products().toString());

        return builder.function("ProductSalesInfo", new org.jyasu.ollama.function.ProductFunction()).inputType(org.jyasu.ollama.function.ProductFunction.Request.class).build();
        
    }
}