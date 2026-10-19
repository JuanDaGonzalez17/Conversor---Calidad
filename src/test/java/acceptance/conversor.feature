Feature: API REST del conversor de temperatura y peso
  Como cliente de la API
  Quiero convertir valores entre unidades por HTTP
  Para obtener resultados correctos y errores claros cuando el valor no es valido

  Background:
    * def baseUrl = karate.properties['karate.baseUrl'] || 'http://localhost:8080'
    * url baseUrl
    * def cerca = function(a, b) { return Math.abs(a - b) < 0.0001 }

  Scenario Outline: Convertir temperatura de <origen> a <destino>
    Given path 'api', 'temperatura'
    And param origen = '<origen>'
    And param destino = '<destino>'
    And param valor = <valor>
    When method get
    Then status 200
    And match response == { origen: '<origen>', destino: '<destino>', valor: '#number', resultado: '#number' }
    And assert cerca(response.valor, <valor>)
    And assert cerca(response.resultado, <esperado>)

    Examples:
      | origen     | destino    | valor  | esperado |
      | celsius    | fahrenheit | 25     | 77       |
      | fahrenheit | celsius    | 77     | 25       |
      | celsius    | kelvin     | 0      | 273.15   |
      | kelvin     | celsius    | 273.15 | 0        |
      | fahrenheit | kelvin     | 32     | 273.15   |
      | kelvin     | fahrenheit | 273.15 | 32       |
      | celsius    | celsius    | 36.6   | 36.6     |

  Scenario Outline: Convertir peso de <origen> a <destino>
    Given path 'api', 'peso'
    And param origen = '<origen>'
    And param destino = '<destino>'
    And param valor = <valor>
    When method get
    Then status 200
    And match response.origen == '<origen>'
    And match response.destino == '<destino>'
    And assert cerca(response.resultado, <esperado>)

    Examples:
      | origen | destino | valor     | esperado  |
      | gramos | libras  | 453.59237 | 1         |
      | libras | gramos  | 1         | 453.59237 |
      | gramos | libras  | 0         | 0         |

  Scenario: Las escalas no distinguen mayusculas y la respuesta sale normalizada
    Given path 'api', 'temperatura'
    And params { origen: 'CELSIUS', destino: 'Kelvin', valor: 100 }
    When method get
    Then status 200
    And match response.origen == 'celsius'
    And match response.destino == 'kelvin'
    And assert cerca(response.resultado, 373.15)

  Scenario: Ida y vuelta conserva el valor original
    Given path 'api', 'temperatura'
    And params { origen: 'celsius', destino: 'fahrenheit', valor: 36.6 }
    When method get
    Then status 200
    * def fahrenheit = response.resultado

    Given path 'api', 'temperatura'
    And params { origen: 'fahrenheit', destino: 'celsius', valor: '#(fahrenheit)' }
    When method get
    Then status 200
    And assert cerca(response.resultado, 36.6)

  Scenario Outline: Rechazar valores fuera del dominio con 400 y mensaje
    Given path 'api', '<recurso>'
    And params { origen: '<origen>', destino: '<destino>', valor: '<valor>' }
    When method get
    Then status 400
    And match response.error == '#string'

    Examples:
      | recurso     | origen     | destino    | valor |
      | temperatura | celsius    | kelvin     | -274  |
      | temperatura | kelvin     | celsius    | -1    |
      | temperatura | fahrenheit | kelvin     | -460  |
      | temperatura | rankine    | celsius    | 10    |
      | temperatura | celsius    | fahrenheit | NaN   |
      | peso        | gramos     | libras     | -1    |
      | peso        | libras     | gramos     | -1    |
      | peso        | kilos      | libras     | 5     |

  Scenario: Parametros faltantes o no numericos devuelven 400
    Given path 'api', 'peso'
    And params { origen: 'gramos', destino: 'libras' }
    When method get
    Then status 400

    Given path 'api', 'peso'
    And params { origen: 'gramos', destino: 'libras', valor: 'abc' }
    When method get
    Then status 400
