Feature: Conversiones de temperatura y peso

  Background:
    * def TemperatureConversor = Java.type('Conversor.TemperatureConversor')
    * def WeightConversor = Java.type('Conversor.WeightConversor')
    * def temperature = new TemperatureConversor()
    * def weight = new WeightConversor()

  Scenario: Convertir temperaturas entre escalas
    * assert temperature.convertirCelsius_Farenheit(25) == 77
    * assert temperature.convertirFahrenheit_Celsius(77) == 25
    * assert temperature.convertirCelsius_Kelvin(0) == 273.15
    * assert temperature.convertirKelvin_Celsius(273.15) == 0
    * assert temperature.convertirFahrenheit_Kelvin(32) == 273.15
    * assert Math.abs(temperature.convertirKelvin_Fahrenheit(273.15) - 32) < 0.0001

  Scenario: Convertir pesos en ambas direcciones
    * assert weight.convertirGramos_Libras(453.59237) == 1
    * assert weight.convertirLibras_Gramos(1) == 453.59237
    * assert weight.convertirGramos_Libras(0) == 0
    * assert weight.convertirLibras_Gramos(0) == 0

  Scenario: Conservar el valor en conversiones de ida y vuelta
    * def celsius = 36.6
    * def celsiusRoundTrip = temperature.convertirFahrenheit_Celsius(temperature.convertirCelsius_Farenheit(celsius))
    * match celsiusRoundTrip == celsius
    * def grams = 750
    * def gramsRoundTrip = weight.convertirLibras_Gramos(weight.convertirGramos_Libras(grams))
    * match gramsRoundTrip == grams

  Scenario Outline: Rechazar valores fuera del dominio
    * def rejected = function(value) { try { <conversion>(value); return false } catch (e) { return true } }
    * assert rejected(<value>)

    Examples:
      | conversion                              | value     |
      | temperature.convertirCelsius_Kelvin     | -274      |
      | temperature.convertirKelvin_Celsius     | -1        |
      | temperature.convertirFahrenheit_Kelvin  | -460      |
      | weight.convertirGramos_Libras           | -1        |
      | weight.convertirLibras_Gramos           | -1        |
