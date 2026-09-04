Feature: Criar Venda

  Scenario: Autorizar venda com funcionario vendedor
      Given que estou autenticado como "vendedor"
      When o sistema criar a venda
      Then a venda deve ser "criada"

  Scenario: Autorizar venda com funcionario gerente
       Given que estou autenticado como "gerente"
       When o sistema criar a venda
       Then a venda deve ser "criada"

  Scenario: Autorizar venda com funcionario rh
         Given que estou autenticado como "rh"
         When o sistema criar a venda
         Then a venda deve ser "recusada"

   Scenario: Autorizar venda com funcionario admin
           Given que estou autenticado como "admin"
           When o sistema criar a venda
           Then a venda deve ser "criada"


  Scenario: Adicionar item em venda em andamento
        Given que estou autenticado como "vendedor"
        And tento adicionar item a venda "em_andamento"
        When o funcionário tentar adicionar o item à venda
        Then o item deve ser "adicionado"

    Scenario: Não adicionar item em venda finalizada
          Given que estou autenticado como "vendedor"
          And tento adicionar item a venda "finalizada"
          When o funcionário tentar adicionar o item à venda
          Then o item deve ser "recusado"

    Scenario: Não adicionar item em venda entregue
          Given que estou autenticado como "vendedor"
          And tento adicionar item a venda "entregue"
          When o funcionário tentar adicionar o item à venda
          Then o item deve ser "recusado"


    Scenario: Adicionar item a mais que estoque
          Given que estou autenticado como "vendedor"
          And tento adicionar item a venda "em_andamento"
          And item no estoque seja de 10 e item adicionado seja de 12
          When o funcionário tentar adicionar o item à venda
          Then o item deve ser "recusado"


    Scenario: Adicionar item disponivel no estoque
          Given que estou autenticado como "vendedor"
          And tento adicionar item a venda "em_andamento"
           And item no estoque seja de 12 e item adicionado seja de 10
          When o funcionário tentar adicionar o item à venda
          Then o item deve ser "adicionado"




