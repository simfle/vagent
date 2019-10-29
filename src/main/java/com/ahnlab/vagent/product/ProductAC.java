package com.ahnlab.vagent.product;

import lombok.Data;

@Data
public class ProductAC extends Product {

    private Mode mode = Mode.LOCKDOWN;

    public enum Mode {
        LOCKDOWN, MAINTENANCE, SIMULATION, COLLECTING, OFF
    }

    public void changeMode() {
        if (Mode.LOCKDOWN == this.mode) {
            this.mode = Mode.MAINTENANCE;
        } else if (Mode.MAINTENANCE == this.mode) {
            this.mode = Mode.SIMULATION;
        } else if (Mode.SIMULATION == this.mode) {
            this.mode = Mode.COLLECTING;
        } else {
            this.mode = Mode.LOCKDOWN;
        }
    }

    @Override
    public Product updateMode() {
        changeMode();
        return this;
    }
}
