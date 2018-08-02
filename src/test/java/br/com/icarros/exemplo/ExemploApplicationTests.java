package br.com.icarros.exemplo;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthJSONAccessTokenResponse;
import org.apache.oltu.oauth2.common.OAuthProviderType;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.apache.oltu.oauth2.common.token.OAuthToken;
import org.junit.Ignore;
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
				.setScope("offline_access gruporegional  integracaopa anuciantepj usuariosite")
				.buildQueryMessage();
		String locationUri = request.getLocationUri();
		System.out.println("locationUri = " + locationUri);
	}


	@Test
	public void changeToken() throws OAuthSystemException, OAuthProblemException {
		String url = "https://accounts.icarros.com/auth/realms/icarros/protocol/openid-connect/token";
		String code = "LwPwIGsUH2D_hKyQ5qzmtla-mfdrtA-26U1whTYU_14.b31734af-7a07-46da-9e51-c94016afcdc5";
		OAuthClientRequest request =
										//OAuthClientRequest.tokenLocation("https://accounts.icarros.com/auth/realms/icarros/protocol/openid-connect/token")
										OAuthClientRequest.tokenLocation(url)
										.setClientId(clientID)
										.setClientSecret(secret)
										.setRedirectURI("http://localhost:8089/location")
										.setGrantType(GrantType.AUTHORIZATION_CODE)
										.setCode(code)
										.buildBodyMessage();
		OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
		OAuthJSONAccessTokenResponse tokenResponse = oAuthClient.accessToken(request);
		System.out.println("oAuthJSONAccessTokenResponse = " + tokenResponse.getRefreshToken());
	}

	@Test
	public void refreshToken() throws OAuthSystemException, OAuthProblemException {
		String url = "https://accounts.icarros.com/auth/realms/icarros/protocol/openid-connect/token";
		String refreshToken = "eyJhbGciOiJSUzI1NiJ9.eyJqdGkiOiJmYjFmMmU5MC1kM2M3LTQ0YWMtODY2MS0yMTUyNTFmYjA2M2YiLCJleHAiOjAsIm5iZiI6MCwiaWF0IjoxNTMzMjQ1ODAxLCJpc3MiOiJodHRwczovL2FjY291bnRzLmljYXJyb3MuY29tL2F1dGgvcmVhbG1zL2ljYXJyb3MiLCJhdWQiOiJuYnNpLWludGVncmFjYW8iLCJzdWIiOiIwOWY4NmIyNC1lNDM5LTRlMjYtYmFhNy1hZGU1OTFkOWU0ZDIiLCJ0eXAiOiJPZmZsaW5lIiwiYXpwIjoibmJzaS1pbnRlZ3JhY2FvIiwic2Vzc2lvbl9zdGF0ZSI6IjQwZjZhMDkxLTdiZjgtNGM5Ni1iNWQxLTdhYmFhZDgyYjJkZCIsImNsaWVudF9zZXNzaW9uIjoiYjMxNzM0YWYtN2EwNy00NmRhLTllNTEtYzk0MDE2YWZjZGM1IiwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbIm9mZmxpbmVfYWNjZXNzIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsiaWNhcnJvcy13ZWJhcHAiOnsicm9sZXMiOlsidXN1YXJpb2ludGVybm8iLCJhZHdvcmRzIiwiYWRtaW4iLCJhZ2VuY2lhIiwiYXRlbnRvIiwiZGFkb3N2ZWljdWxvcyIsImNvYnJhbmNhIiwiY29tZXJjaWFsIiwiZXN0b3F1ZSIsIm9maWNpbmEiLCJ1c3Vhcmlvc2l0ZSIsIm9wZXJhY2FvcGoiLCJvcGVyYWNhb3BmIiwiZmVpcmFvIiwiYW51bmNpYW50ZXBqIiwiY29uc29yY2lvIiwiZmVpcmFvYWRtaW4iLCJsaXN0YWxvamFzIiwiaXRhdSIsImNvbnN1bHRhcmV2ZW5kYXMiLCJtYXJrZXRpbmciLCJwbGFuZWphbWVudG9waiIsImVzdGF0aXN0aWNhcyIsIm5vdGljaWFzIiwicmFpb3giLCJhcGlhY2Nlc3MiLCJwdWJsaWNpZGFkZSIsImNhZGFzdHJvIiwicGxhbmVqYW1lbnRvcGYiXX0sImJyb2tlciI6eyJyb2xlcyI6WyJyZWFkLXRva2VuIl19LCJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50Iiwidmlldy1wcm9maWxlIl19fX0.SmTiwoxOXcyIL_jEF3FJfSdVpqZc8QYGCk4Wu__DxqtQ7klj9-mUOnqjwqVM0iU08d-VmD7hg9jy5n6NvtsZvoLIHySZOECllz-DIi0rJU_PkYJYdhO738uWsR8s82F7HtxcN8TR2e1WYP0W-U5pIHZhdyBq6o_QaulP0ehWxP-Ey_r3ifuz7PbjME__O-_pszC1tsvhOwLHH1nE7ycuTZ_jVxV13GxGAy_JudZJOoxnpUJuE1t4jL20vIrJQjySovY1GlBuWMnmUFANSTviR-tx9v8iTduMZquoVAQey-PCR8Zrgi3-xug6Pdah_9bSDL0oorKUGSRavuuAhdg4Hg";

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
