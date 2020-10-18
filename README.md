# cardif
Employee-api de teste para Cardif

## Composição do projeto

- Webservice rest com Spring Boot e Java 14
- APIs REST para Funcionários e Departamentos
- Swagger para navegar pelos webservices
- Banco de dados H2 in-memory (script sql para gerar dados incluso)

O banco de dados será gerado e populado quando a aplicação for executada.

## Requisições implementadas

### Criar funcionário

Permite criar um funcionário, verificando se o documento informado já existe.

### Editar funcionario

Permite alterar os dados de um funcionário e seu departamento.

- Se o departamento for diferente do atual, transfere-o para o novo departamento.

### Deletar funcionario (soft delete)

Desativa o registro do funcionário

### Consultar funcionario

Exibe dados de um funcionário ativo, com histórico de departamentos.

### Listar funcionarios de um departamento

Exibe um lista de funcionários por departamento, com histórico de departamentos.

### Troca de líder de um departamento

Atualiza o líder de um departamento.

- Não permite selecionar um funcionário que não faça parte do departamento escolhido.

## Exemplo de requisição:

### Funcionário

```
{
  "birthDate": "2010-10-18T20:50:05.813Z",
  "departments": [
    {
      "pk": {
        "departmentId": 1
      },
      "startDate": "2020-10-18T20:50:05.813Z"
    }
  ],
  "document": "string",
  "name": "string",
  "position": {
    "id": 1
  }
}
```

Onde **position** representa o cargo e **department** representa o departamento do funcionário.

### Departamento

Permite escolher um funcionário ativo existente para se tornar o chefe de seu departamento.

Os seguintes departamentos estão disponíveis:

-  1 - RH
-  2 - DP
-  3 - Financeiro
-  4 - TI
-  5 - Manutenção
-  6 - Marketing

### Cargos

 Os seguintes cargos estão disponíveis:

-  1 - Assistente
-  2 - Analista
-  3 - Gerente
-  4 - Diretor
-  5 - Estagiario