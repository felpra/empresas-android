![N|Solid](logo_ioasys.png)

# README #

Estes documento README tem como objetivo fornecer as informações solicitadas para o processo seletivo

### ESCOPO DO PROJETO ###

Foi criado um aplicativo como solicitado, sendo que após o login temos uma listagem de empresas, onde é possível clicar para ver seu detalhamento.
Também foi adicionado uma filtragem por nome, via barra de busca.

### Informações Importantes ###

Justificativa das bibliotecas:

1 implementation "android.arch.lifecycle:extensions:1.1.1" - Adiciona o provider de viewModel, para vincular facilmente o componente viewModel correspondente a sua View (activity)

2 kapt 'com.android.databinding:compiler:3.1.4' - Adiciona o componente de databinding que é mais fácil de testar, usar e tem melhor desempenho no geral

3 implementation 'com.github.bumptech.glide:glide:4.11.0'
annotationProcessor 'com.github.bumptech.glide:compiler:4.11.0' - adiciona o glide para carregar fotos diretamente de uma URL valida

4 implementation 'com.squareup.retrofit2:converter-gson:2.4.0' - adiciona o parseador de json do retrofit para enviar e receber informações serializaveis em formato json

5 implementation 'com.squareup.okhttp3:okhttp:4.2.1' - biblioteca que auxilia o retrofit a fazer as conexões de rede

6 implementation 'com.squareup.retrofit2:retrofit:2.7.1' - adiciona o retrofit para montar a APi REST com as requisições post/get etc

7 implementation 'com.google.android.material:material:1.3.0' - adiciona os componentes visuais usados na implementação do layout

8 implementation 'androidx.constraintlayout:constraintlayout:2.0.4' adiciona o componente constraintlayot, que é mais moderno e combina os poderes dos antigos linear e relative layout.

9 testImplementation 'org.mockito:mockito-core:3.7.0' - biblioteca para mockar em kotlin, semelhante ao Mockito, com funções parecidas, porém mais fácil de usar e combinar com um código em kotlin

10 testImplementation 'com.google.truth:truth:1.1' - biblioteca de assert para testes, verifica que determinado valor ou estado foi atingido, mais fácil de utilizar do que a AssertJ

11 testImplementation 'org.jetbrains.kotlinx:kotlinx-coroutines-test:1.4.2' testImplementation "androidx.arch.core:core-testing:2.1.0" - bibliotecas utilizadas no teste de coroutinas, provendo alguns elementos necessários para rodar o teste e o código dentro de uma coroutine


TODO (caso tivesse mais tempo) :
1 Tratar exibição de descriao muito grande - Quando a descrição é muito grande para caber na tela, parte da descrição é cortada. O ideal seria adicionar uma funcionalidade de scroll para exibir o restante dos conteudos nesse caso (ocorreu apenas com 1 dos itens obtidos na requisição)

2 Pela falta de tempo, as  Strings do código acabaram ficando Hardcoded. Com mais tempo elas seriam adicionadas em um strings.xml, e caso necessário, teriamos um strings.xml para cada linguagem que o aplicativo deveria suportar

3 Otimização das requisições - Otimizar para que as requisições e os endpoints fiquem mais dinamicos, de forma que também seja adicionada uma filtragem por mais parametros, e não somente o nome da empresa.

4 Otimização de memoria - Para a requisição de listagem da index, poderia ter sido armazenado apenas os itens necessários da lista para serem colocados no RecyclerView, ao invés do objeto enterprise por completo. Isso economizaria uma quantia razoavel de memoria, e quando necessário visualizar os detalhes, poderiamos utilizar a requisição "show".

5 Testes de integração e mais testes unitarios - Para esse tipo de aplicação o recomendado seria a escrita de testes de integração, dado que a funcionalidade principal do app envolve integração com um database e uma operação de rede baseada em um framework externo (retrofit)

6 - Uso de fragment - O ideal, dada a natureza simples da tela de detalhamento de empresa, era que tivesse sido utilizado um fragment ao invés de uma activity. 


Instruções: Após executar a aplicação, inserir um login valido. Caso contrário uma mensagem de erro será exibida. Caso não haja rede, outra mensagem de erro será exibida. Após logar com sucesso, será exibida uma pagina com uma lista de enterprises vinda do database. Ao clicar no botão de search (uma lupa no canto direito da tela) É possível realizar uma filtragem por nome ou partes do nome da empresa. Ao realizar a filtragem a lista será populada de acordo. Caso um dos itens seja clicado, uma tela com seu detalhamento e uma versão maior da imagem irá aparecer. 


### Dicas ###

* Para requisição sugerimos usar a biblioteca Retrofit - Utilizado
* Para download e cache de imagens use a biblioteca Glide - Utilizado
* Para parse de Json use a biblioteca GSON - Utilizado

### Bônus ###

* Testes unitários: Foi feito um teste unitário para a activity de login. O teste unitário foi feito utilizado as bibliotecas, Jnunit, Mocck e GoogleTruth. As ferramentas de Junit foram utilizadas pois já são facilmente integradas ao java/kotlin. A biblioteca Mocck foi escolhida porque, assim como a linguagem Kotlin ela é simples e evita o código desnecessário (produz menos código em relação a biblioteca Mockquito por exemplo), o que torna o teste mais simples de atender, além de realizar um melhor "isolamento" dos componentes a serem testados. A biblioteca Truth também foi utilizada por motivos de simplicidade de funcinamento. Por falta de tempo não foi possível fazer mais, visto que nao dispus de mais de algumas horas para fazer o teste. O ideal, dado que a funcionalidade principal do app envolve integração com um database e uma operação de rede, seria que testes de integração fossem realizados. 

* Usar uma arquitetura testável. Ex: MVP, MVVM, Clean, etc: Foi utilizado no código a arquitetura MVVM, bem como alguns principios de arquitetura Clean

* Material Design : Muitos dos componentes utilizados são provenientes de Material Design

* Utilizar alguma ferramenta de Injeção de Dependência, Dagger, Koin e etc: Não implementado/não houve tempo

* Utilizar Rx, LiveData, Coroutines: Coroutines foram utilizadas e também LiveData e outros componentes do architecture components e também algumas vantagens da linguagem Kotlin (como por exemplo lambda e inline functions)

* Padrões de projetos: Foram utilizados os seguintes padrões de projeto, sendo que alguns deles já são facilitados pelos componentes nativos do  Android e do Kotlin: Adapter/ViewHolder, Facade, Observer
