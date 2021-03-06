package de.biobank.dataservice.entity;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

public enum Profession {

    // https://de.wikipedia.org/wiki/Liste_medizinischer_Fachgebiete
    DERMATOLOGIE, KARDIOLOGIE, ALLGEMEINMEDIZIN, RADIOLOGIE;

    private static final List<Profession> VALUES = List.of(values());
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new SecureRandom();

    public static Profession randomProfession() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

}
