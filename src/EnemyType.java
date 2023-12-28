public enum EnemyType {
    ALAPOT,
    ASCULE,
    BARRIER,
    BIFLER,
    BOASITE,
    BOSS,
    ELBLINK,
    GEALMEA,
    GELPAAR,
    HILUN,
    LUVOGUE,
    PSYBALL,
    ZELNUC;

    public String getEnemyImg() {
        switch (this) {
            case ALAPOT:
                return "alapot";
            case ASCULE:
                return "ascule";
            case BARRIER:
                return "barrier";
            case BIFLER:
                return "bifler";
            case BOASITE:
                return "boasite";
            case BOSS:
                return "boss";
            case ELBLINK:
                return "elblink";
            case GEALMEA:
                return "gealmea";
            case GELPAAR:
                return "gelpaar";
            case HILUN:
                return "hilun";
            case LUVOGUE:
                return "luvogue";
            case PSYBALL:
                return "psyball";
            case ZELNUC:
                return "zelnuc";
            default:
                return "psyball";
        }
    }
}
