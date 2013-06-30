package be.appletree.log;

import java.security.SecureRandom;

/**
 * Created by jochen on 6/28/13.
 */

public class ContextGenerator {

    public String generateContextId() {
        SecureRandom sr1 = new SecureRandom();

        return String.valueOf(sr1.nextInt());
    }
}
