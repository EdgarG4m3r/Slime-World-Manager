package com.grinderwolf.smw.api.utils;

/**
 * Class containing some standards of the SRF.
 */
public class SlimeFormat {

    /** First bytes of every SRF file **/
    public static final byte[] SLIME_HEADER = new byte[] { -79, 11 };

    /** Latest version of the SRF that SMW supports **/
    public static final byte SLIME_VERSION = 3;
}
