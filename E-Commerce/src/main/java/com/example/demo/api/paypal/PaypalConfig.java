package com.example.demo.api.paypal;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;

@Configuration
public class PaypalConfig 
{
	@Value("${paypal.client.id}")
	private String clientId="AdiIgyxmIWFxOUKKcIyVVtB5Hd9FWZVls6XnJ5PyqfCYldfSLj1K-4FT9rYDKtFlHBPhjd2TSvDTlATU";
	@Value("${paypal.client.secret}")
	private String clientSecret="EDD88AKg0Nb490Gv32wHYg-NQP5kpM9y4zV1JgF_ltHojz3jQsdd2o35GESWq_K2frcMNbpT7rxsESHX";
	@Value("${paypal.mode}")
	private String mode="sandbox";

	@Bean
	public Map<String,String> paypalSdkConfig()
	{
		Map<String,String> configMap=new HashMap<String,String>();
		configMap.put("mode", mode);
		return configMap;
	}

	
	@Bean
	public OAuthTokenCredential oAuthTokenCredential()
	{
		return new OAuthTokenCredential(clientId,clientSecret,paypalSdkConfig());
	}

	
	@Bean
	public APIContext apiContext() throws PayPalRESTException
	{
		APIContext context=new APIContext(oAuthTokenCredential().getAccessToken());
		context.setConfigurationMap(paypalSdkConfig());
		return context;
	}
}
