package com.udea.conversor.api;

import com.udea.conversor.domain.EscalaTemperatura;
import com.udea.conversor.domain.TemperatureConversor;
import com.udea.conversor.domain.UnidadPeso;
import com.udea.conversor.domain.WeightConversor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * API REST del conversor de temperatura y peso.
 *
 * <pre>
 * GET /api/temperatura?origen=celsius&amp;destino=fahrenheit&amp;valor=25
 * GET /api/peso?origen=gramos&amp;destino=libras&amp;valor=453.59237
 * </pre>
 */
@RestController
@RequestMapping("/api")
public class ConversorController {

    private final TemperatureConversor temperatureConversor;
    private final WeightConversor weightConversor;

    public ConversorController(TemperatureConversor temperatureConversor, WeightConversor weightConversor) {
        this.temperatureConversor = temperatureConversor;
        this.weightConversor = weightConversor;
    }

    @GetMapping("/temperatura")
    public RespuestaConversion convertirTemperatura(@RequestParam("origen") String origen,
                                                    @RequestParam("destino") String destino,
                                                    @RequestParam("valor") double valor) {
        EscalaTemperatura escalaOrigen = EscalaTemperatura.desdeTexto(origen);
        EscalaTemperatura escalaDestino = EscalaTemperatura.desdeTexto(destino);
        double resultado = temperatureConversor.convertir(escalaOrigen, escalaDestino, valor);
        return new RespuestaConversion(escalaOrigen.nombre(), escalaDestino.nombre(), valor, resultado);
    }

    @GetMapping("/peso")
    public RespuestaConversion convertirPeso(@RequestParam("origen") String origen,
                                             @RequestParam("destino") String destino,
                                             @RequestParam("valor") double valor) {
        UnidadPeso unidadOrigen = UnidadPeso.desdeTexto(origen);
        UnidadPeso unidadDestino = UnidadPeso.desdeTexto(destino);
        double resultado = weightConversor.convertir(unidadOrigen, unidadDestino, valor);
        return new RespuestaConversion(unidadOrigen.nombre(), unidadDestino.nombre(), valor, resultado);
    }
}
