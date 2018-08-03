package br.com.icarros.exemplo;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthJSONAccessTokenResponse;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExemploApplicationTests {

	@Value("${clientID}")
	private String clientID;
	@Value("${secret}")
	private String secret;

	@Test
	public void getToken() throws OAuthSystemException, OAuthProblemException {
		OAuthClientRequest request = OAuthClientRequest
				.authorizationLocation("https://accounts.icarros.com/auth/realms/icarros/protocol/openid-connect/auth")
				.setClientId(clientID)
				.setResponseType("code")
				.setRedirectURI("http://localhost:8089/location")
				.setScope("anuciantepj usuariosite")
				.buildQueryMessage();
		String locationUri = request.getLocationUri();
		System.out.println("locationUri = " + locationUri);
	}

	@Test
	public void getOfflineToken() throws OAuthSystemException, OAuthProblemException {
		OAuthClientRequest request = OAuthClientRequest
				.authorizationLocation("https://accounts.icarros.com/auth/realms/icarros/protocol/openid-connect/auth")
				.setClientId(clientID)
				.setResponseType("code")
				.setRedirectURI("http://localhost:8089/location")
				.setScope("offline_access anuciantepj")
				.buildQueryMessage();
		String locationUri = request.getLocationUri();
		System.out.println("locationUri = " + locationUri);
	}


	@Test
	public void changeToken() throws OAuthSystemException, OAuthProblemException {
		String url = "https://accounts.icarros.com/auth/realms/icarros/protocol/openid-connect/token";
		String code = "CODE_RETORNADO_AQUI";
		OAuthClientRequest request =	OAuthClientRequest.tokenLocation(url)
										.setClientId(clientID)
										.setClientSecret(secret)
										.setRedirectURI("http://localhost:8089/location")
										.setGrantType(GrantType.AUTHORIZATION_CODE)
										.setCode(code)
										.buildBodyMessage();
		OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
		OAuthJSONAccessTokenResponse tokenResponse = oAuthClient.accessToken(request);
		System.out.println("ACCESS_TOKEN = " + tokenResponse.getAccessToken());
		System.out.println("REFRESH_TOKEN  = " + tokenResponse.getRefreshToken());
	}

	@Test
	public void refreshToken() throws OAuthSystemException, OAuthProblemException {
		String url = "https://accounts.icarros.com/auth/realms/icarros/protocol/openid-connect/token";
		String refreshToken = "REFRESH_TOKEN_AQUI";

		OAuthClientRequest request =
										OAuthClientRequest.tokenLocation(url)
										.setClientId(clientID)
										.setClientSecret(secret)
										.setGrantType(GrantType.REFRESH_TOKEN)
										.setRefreshToken(refreshToken)
										.buildBodyMessage();
		OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
		OAuthJSONAccessTokenResponse tokenResponse = oAuthClient.accessToken(request);
		System.out.println("oAuthJSONAccessTokenResponse = " + tokenResponse.getAccessToken());
	}







}
