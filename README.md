![N|Solid](logo_ioasys.png)

# README #

Estes documento README tem como objetivo fornecer as informações necessárias para realização do projeto Empresas.

### O QUE FAZER ? ###

* Você deve realizar um fork deste repositório e, ao finalizar, enviar o link do seu repositório para a nossa equipe. Lembre-se, NÃO é necessário criar um Pull Request para isso.

### ESCOPO DO PROJETO ###

* Deve ser criado um aplicativo Android utilizando linguagem Java ou Kotlin com as seguintes especificações:
* Login e acesso de Usuário já registrado
	* Para o login usamos padrões OAuth 2.0. Na resposta de sucesso do login a api retornará 3 custom headers (access-token, client, uid);
	* Para ter acesso as demais APIS precisamos enviar esses 3 custom headers para a API autorizar a requisição;
* Listagem de Empresas
* Detalhamento de Empresas

### Informações Importantes ###

* Layout e recortes disponíveis no Zeplin (http://zeplin.io)
Login - teste_ioasys
Senha - ioasys123

* Integração disponível a partir de uma collection para Postman (https://www.getpostman.com/apps) disponível neste repositório.
* O `README.md` deve conter uma pequena justificativa de cada biblioteca adicionada ao projeto como dependência.
* O `README.md` deve conter tambem o que você faria se tivesse mais tempo.
* O `README.md` do projeto deve conter instruções de como executar a aplicação
* Independente de onde conseguiu chegar no teste é importante disponibilizar seu fonte para analisarmos.

### Dados para Teste ###

* Servidor: https://empresas.ioasys.com.br
* Versão da API: v1
* Usuário de Teste: testeapple@ioasys.com.br
* Senha de Teste : 12341234

### Dicas ###

* Para requisição sugerimos usar a biblioteca Retrofit
* Para download e cache de imagens use a biblioteca Glide
* Para parse de Json use a biblioteca GSON

### Bônus ###

* Testes unitários, pode usar a ferramenta que você tem mais experiência, só nos explique o que ele tem de bom.
* Usar uma arquitetura testável. Ex: MVP, MVVM, Clean, etc.
* Material Design
* Utilizar alguma ferramenta de Injeção de Dependência, Dagger, Koin e etc..
* Utilizar Rx, LiveData, Coroutines.
* Padrões de projetos
