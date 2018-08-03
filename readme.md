## Exemplo de autenticação  - API ICARROS


#### Introdução

Nesse tutorial tentamos mostrar um exemplo simples usando o Apache Oltu. Sendo assim segue um passo a passo de como utilizamos essa dependência para realizar a autenticação.
Por favor, rode a aplicação que foi escrita usando Spring Boot e troque as configurações no arquivo application.properties.
Existe também um serviço REST para receber o token do callback. 
Depois desses procedimentos, você estará apto a consumir os serviços da API do [iCarros](https://www.icarros.com.br/apidocs/)



#### Importante

Por favor, valide as informações de autorização com a nossa equipe (CLIENT_ID, SECRET e URL de CALLBACK). Essas informaçoes devem
ser configuradas no nosso servidor e serão disponibilizadas.
Esse exemplo não visa ser um guia definitivo ou algo do tipo. Visamos somente exemplificar usando a linguagem java o fluxo de autorização.
Para mais informações, por favor veja a documentação ([iCarros](https://www.icarros.com.br/apidocs/apiOauth.html), [OAuth2](https://oauth.net/2/) e [Oltu](https://oltu.apache.org/))





#### Passo 1 

Por favor, veja o caso de teste intitulado getToken. Nesse caso de teste conseguiremos o endereço para enviarmos o usuário para a página do login/aceite.

Podemos também usar o caso de teste getOfflineToken, esse método nos ajuda a pegar um token com o escopo offline.


#### Passo 2

Depois do token de acesso, precisamos trocar esse token pela autorização de fato. Sendo assim você pode acompanhar no caso de teste changeToken. Depois de realizar essa troca teremos um objeto de retorno com todos os dados necessários para consumir os serviços da API do iCarros.


#### Passo 3 

Conforme o seu access_token expirar, você precisará atualizar o seu token de acesso usando o seu refresh_token. No caso de teste refreshToken mostra como isso seria feito. Lembrando que temos o token offline no qual tem uma duração maior de sua validade.



### Conclusão

O simples tutorial aqui descrito é apenas uma forma de exemplificar o que está escrito no https://paginasegura.icarros.com.br/apidocs/apiOauth.html




