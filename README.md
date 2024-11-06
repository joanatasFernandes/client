Este repositório contém um projeto de CRUD de clientes desenvolvido em Java, utilizando Maven. Este guia fornece instruções para execução, bem como soluções para possíveis problemas que podem surgir durante a configuração no Windows e Linux.

## Pré-requisitos

- **Java 17 ou superior** (Certifique-se de ter o Java configurado no `PATH`)
- **Maven** (se não estiver utilizando o Maven Wrapper incluído no projeto)
- **Git** (para clonar o repositório)
- **Docker** (para o uso do banco de dados em container)
- **Make** (opcional para execução de comandos no Linux)

## Configuração

1. **Clone o repositório:**

   ```bash
   git clone https://github.com/seu-usuario/client-crud.git
   cd client-crud
2. ** Configure seu banco de dados:**

O projeto inclui um **docker-compose** para iniciar o banco de dados. 
Execute o comando abaixo para criar e inicializar o container:
  ```bash
  docker-compose -f db-docker-compose.yaml up -d
  ```
3. **Construindo e Executando o Projeto**.

**Passo a Passo**
Para compilar e empacotar o projeto, execute:

  ```bash
  ./mvnw clean package 
   ```

Nota: No Windows, use **mvnw.cmd clean package** se **/mvnw** não for reconhecido.

**Após o build, execute o projeto:**

   ```bash
   java -jar target/client-crud.jar
   ```

Usando o Makefile
No Linux, você pode utilizar o Makefile para construir o projeto. Execute:

   ```bash
   make build
   ```
**Nota:** No Windows, o comando make pode não estar disponível por padrão. Siga as instruções abaixo para resolver esse problema.

   ***Solução de Problemas***
Erro: Cannot run program "\usr\bin\make"
Esse erro ocorre porque o comando make não está instalado no Windows. Para resolver, siga estas etapas:

***Instale o Chocolatey:***

powershell
Copiar código
Set-ExecutionPolicy Bypass -Scope Process -Force; [System.Net.ServicePointManager]::SecurityProtocol = [System.Net.ServicePointManager]::SecurityProtocol -bor 3072; iex ((New-Object System.Net.WebClient).DownloadString('https://chocolatey.org/install.ps1'))
Com o Chocolatey instalado, execute o seguinte comando no terminal com permissões de administrador:

   ```bash
  Copiar código
  choco install make
   ```
Adicione o caminho de instalação (C:\ProgramData\chocolatey\lib\make\tools\install\bin) às Variáveis de Ambiente.
O caminho deve ser de acordo com seu sistema. Ex: 
variavel PATH
valor C:\ProgramData\chocolatey\lib\make\tools\install\bin
Verifique se o make está funcionando corretamente:

   ```bash

   make --version
   ```
Erro: '.' não é reconhecido como um comando interno ou externo
Esse erro ocorre ao tentar executar ./mvnw no Windows. Para resolver:

No Windows, substitua **./mvnw** por **mvnw.cmd** no comando ou no Makefile.

No Makefile, altere:
   ```bash
   
  makefile
  build:
  mvnw.cmd clean package
   ```

Erro: O acesso ao caminho 'C:\ProgramData\chocolatey\lib-bad' foi negado
Esse erro ocorre durante a instalação de pacotes pelo Chocolatey, devido a permissões de acesso. Para resolver:

Execute o terminal como Administrador.

Se o erro persistir, remova o arquivo de bloqueio manualmente:

Navegue até C:\ProgramData\chocolatey\lib e remova o arquivo de bloqueio mencionado (995c915eb7cf3c8b25f2235e513ef8ca0c75c3e7) ou o diretório lib-bad.
Reinicie o processo de instalação do make.


## Mudança para Uso de Geradores

Recentemente, o projeto foi reestruturado para utilizar **geradores** em vez da abordagem anterior. Isso melhora a 
eficiência no processamento de grandes volumes de dados, simplificando o fluxo de execução e garantindo maior controle 
sobre a memória durante o ciclo de vida do programa.

A utilização de geradores permite um processamento mais eficiente e escalável, especialmente em cenários de grandes 
conjuntos de dados ou operações de I/O, garantindo que os recursos sejam utilizados de forma otimizada, sem 
sobrecarregar a memória.

Se você está atualizando seu ambiente de desenvolvimento, certifique-se de seguir as novas práticas e 
ajustar qualquer código que dependa de funções ou métodos anteriormente utilizados sem geradores.

Este ajuste está em conformidade com a necessidade de melhorar o desempenho e a manutenção do código em longo prazo.

## **Teste**

No pasta **collection** contem as coleçoes de teste para o Postam.
Extraia o arquivo e faça o import para o sua workspace. 
OBS: Já contém as rotas dos endipoints.

## Acessando a Documentação

A documentação do projeto está disponível por meio da integração com **Swagger**. Ela oferece uma visão geral interativa da API, permitindo visualizar os endpoints disponíveis, os parâmetros necessários e as respostas esperadas.

### Como acessar a documentação

1. **Após iniciar o projeto**, acesse a URL da documentação em seu navegador:
   - Se estiver rodando localmente, a URL geralmente será:

     ```
     http://localhost:8080/swagger-ui.html
     ```

2. **Autenticação e Acessos**: Se a aplicação exigir autenticação, você precisará fornecer um token ou realizar login conforme configurado no sistema.

3. **Visualizando a API**: Na interface do Swagger, você pode explorar os endpoints da API, fazer chamadas diretamente e verificar a estrutura de resposta de cada um dos recursos da aplicação.

### Documentação do Código

Além da documentação da API, o código-fonte também contém comentários detalhados sobre a implementação e o uso das classes e métodos principais. Para visualizá-los, você pode:

1. **Explorar os arquivos diretamente no repositório**, ou
2. **Utilizar ferramentas de navegação de código** (como IDEs, exemplo: IntelliJ IDEA, Eclipse) para acessar os comentários inline e as anotações de documentação.

Se precisar de mais informações sobre a estrutura interna do código ou sobre como contribuir, consulte o arquivo [CONTRIBUTING.md](CONTRIBUTING.md) para guias adicionais.

### OBS: 
    
É necessário alterar o nome do Banco.