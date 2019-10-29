package com.ahnlab.vagent.product;

public class ProductHips extends Product {

    private Mode mode = Mode.ON;

    public enum Mode {
        ON, OFF, EOFF
    }

    public void changeMode() {
        if (Mode.ON == this.mode) {
            this.mode = Mode.OFF;
        } else if (Mode.OFF == this.mode) {
            this.mode = Mode.EOFF;
        } else {
            this.mode = Mode.ON;
        }
    }

    @Override
    public Product updateMode() {
        changeMode();
        return this;
    }
}
