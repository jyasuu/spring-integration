package org.jyasu.ollama;

import java.util.Date;
import java.util.function.Function;

public class CurrentDateTimeFunction implements Function<CurrentDateTimeFunction.Request, CurrentDateTimeFunction.Response>{
	@Override
	public Response apply(Request request) { //這個函式就是 Function 被呼叫後執行的內容
		return new Response(new Date());
	}
	public record Request(String State){ //其實可以不用請求參數，不過 Function 最少一定要有一個參數
	}
	public record Response(Date currDateTime) { //回傳的型態
	}
}