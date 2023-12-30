public enum BulletType {
    NORMAL,
    CANNON,
    BEAM,
    SWORD_FIRE,
    DIFFUSION_BEAM;

    public BulletType getNext() {
        return values()[(ordinal() + 1) % values().length];
    }

}
