import java.util.Random;

public enum EnemyType {
    ALAPOT,
    ASCULE,
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

    public static EnemyType getRandomEnemyType() {
        Random random = new Random();
        int randomNumber = random.nextInt(11);
        //without boss
        switch (randomNumber) {
            case 0:
                return EnemyType.ASCULE;
            case 1:
                return EnemyType.BIFLER;
            case 2:
                return EnemyType.BOASITE;
            case 3:
                return EnemyType.ELBLINK;
            case 4:
                return EnemyType.GEALMEA;
            case 5:
                return EnemyType.GELPAAR;
            case 6:
                return EnemyType.HILUN;
            case 7:
                return EnemyType.LUVOGUE;
            case 8:
                return EnemyType.PSYBALL;
            case 9:
                return EnemyType.ZELNUC;
            default:
                return EnemyType.PSYBALL;
        }
    }
}
