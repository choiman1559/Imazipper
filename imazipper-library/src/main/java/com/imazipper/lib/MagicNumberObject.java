package com.imazipper.lib;

import java.util.Locale;

public class MagicNumberObject {
    protected String[] FileExtension;
    protected String[] HeaderSig;

    protected MagicNumberObject(String[] FileExtension, String[] HeaderSig) {
        this.FileExtension = FileExtension;
        this.HeaderSig = HeaderSig;
    }

    public boolean equals(String extension) {
        for (String s : FileExtension)
            if (extension.toLowerCase(Locale.ROOT).equals(s.toLowerCase(Locale.ROOT))) return true;
        return false;
    }

    public byte[][] getHeaderSig() {
        if (HeaderSig == null) return null;
        byte[][] array = new byte[HeaderSig.length][];
        for (int i = 0; i < HeaderSig.length; i++) {
            array[i] = hexStringToByteArray(HeaderSig[i].replace(" ", ""));
        }
        return array;
    }

    protected static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }
}
