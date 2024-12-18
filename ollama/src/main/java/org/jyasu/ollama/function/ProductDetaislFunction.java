package org.jyasu.ollama.function;


import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


public class ProductDetaislFunction implements Function<ProductDetaislFunction.Request, ProductDetaislFunction.Response>{
	public record ProductDetail(String product, List<String> models) {}
	@Override
	public Response apply(Request request) {
		//模擬資料，企業通常會透過DB或是其他API查詢內容
		List<ProductDetail> productModels= List.of(
				new ProductDetail("PD-1405", List.of("1405-001","1405-002","1405-003")),
				new ProductDetail("PD-1234", List.of("1234-1","1234-2","1234-3","1234-4")), 
				new ProductDetail("PD-1235", List.of("1235-4","1235-5")), 
				new ProductDetail("PD-1385", List.of("1385-1","1385-2","1385-3")),
				new ProductDetail("PD-1255", List.of("1255-1")),
				new ProductDetail("PD-1300", List.of("1300-1","1300-1","1300-1"))
			);
		//模擬查詢後回傳的結果
		Optional<ProductDetail> models = productModels.stream().filter(pd -> pd.product.equals(request.product())).findFirst();
		if(models.isEmpty())
		{
			return new Response(null);
		}

		return new Response(models.get());
	}
	
	@JsonInclude(Include.NON_NULL)
	@JsonClassDescription("product detail list")
	public record Request(
		//參數只需帶入產品
		@JsonProperty(required = false, value = "product") @JsonPropertyDescription("product name") String product
		) {
	}
	//回傳的結果最好包含產品一起回傳，若只回傳型號清單 AI 比較容易失誤
	public record Response(ProductDetail models) {
	}
}