package com.elo7.space_probe.app.exceptions;

public class ProbeCollisionException extends RuntimeException {

    public ProbeCollisionException() {
        super("Movimentacao invalida: a sonda iria colidir com outra sonda");
    }
}
