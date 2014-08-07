package com.disney.customcraft.materials;

public enum TorchMaterial {
    WOOD(180),
    COAL(360),
    WAX(720),
    OIL(2160);

    private final int maxDura;

    private TorchMaterial(int maxDura) {
        this.maxDura = maxDura;
    }
    
    public int getMaxDura() {
        return this.maxDura;
    }
}
