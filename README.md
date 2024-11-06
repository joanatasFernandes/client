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
