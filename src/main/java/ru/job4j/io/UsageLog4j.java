package ru.job4j.io;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte wheels = 4;
        short colors = 3;
        int age = 6;
        long friends = 2;
        float length = 5.0f;
        double maxSpeed = 60.0;
        boolean isMainCharacter = true;
        char favouriteLetter = 'U';
        LOG.debug("Hero info wheels : {}, colors : {}, age : {}, friends : {}, length : {}, maxSpeed : {}, "
                + "is main character : {}, favourite letter : {}", wheels, colors, age, friends, length, maxSpeed,
                 isMainCharacter, favouriteLetter);
    }
}
