package com.elo7.space_probe.app.exceptions;

/**
 * Exceção personalizada para indicar que um recurso não foi encontrado.
 */
public class PositionOutOfPlanetBoundariesException extends RuntimeException {

    public PositionOutOfPlanetBoundariesException() {
        super("Movimentação inválida: fora dos limites do planeta");
    }

}
