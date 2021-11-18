package com.imazipper.lib;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Locale;

@SuppressWarnings("unused")
class FileMagicNumberTool {
    protected static MagicNumbers magicNumbers = new MagicNumbers() {
        @Override
        public void init() {
            MagicNumbers.super.init();
        }
    };

    public static int indexOfMagicNumber(File file) throws IOException {
        String fileName = file.getName();
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase(Locale.ROOT);

        for (MagicNumberObject object : magicNumbers.lists) {
            if (object.equals(ext)) {
                for (String header : object.HeaderSig) {
                    int index = indexOfMagicNumber(file, header);
                    if (index != -1) return index;
                }
            }
        }
        return -1;
    }

    public static int indexOfMagicNumber(File file, String PossibleHexHeader) throws IOException {
        return indexOf(file, MagicNumberObject.hexStringToByteArray(PossibleHexHeader));
    }

    public static int indexOf(File file, byte[] pattern) throws IOException {
        return indexOf(Files.readAllBytes(file.toPath()), pattern);
    }

    public static int indexOf(byte[] data, byte[] pattern) {
        return indexOf(data, pattern, 0);
    }

    public static int indexOf(byte[] data, byte[] pattern, int startPosition) {
        if (data.length == 0) return -1;

        int[] failure = computeFailure(pattern);
        int j = 0;

        for (int i = startPosition; i < data.length; i++) {
            while (j > 0 && pattern[j] != data[i]) {
                j = failure[j - 1];
            }
            if (pattern[j] == data[i]) {
                j++;
            }
            if (j == pattern.length) {
                return i - pattern.length + 1;
            }
        }
        return -1;
    }

    private static int[] computeFailure(byte[] pattern) {
        int[] failure = new int[pattern.length];

        int j = 0;
        for (int i = 1; i < pattern.length; i++) {
            while (j > 0 && pattern[j] != pattern[i]) {
                j = failure[j - 1];
            }
            if (pattern[j] == pattern[i]) {
                j++;
            }
            failure[i] = j;
        }

        return failure;
    }

    public interface MagicNumbers {
        ArrayList<MagicNumberObject> lists = new ArrayList<>();

        default void init() {
            lists.add(new MagicNumberObject(new String[]{"jpg", "jpeg", "jp2", "j2k", "jpf", "jpm", "jpg2", "j2c", "jpc", "jpx", "mj2"}, new String[]{"FF D8 FF E0", "FF D8 FF E8", "FF D8 FF DB", "FF D8 FF E1", "00 00 00 0C 6A 50 20 20 0D 0A 87 0A", "FF 4F FF 51"}));
            lists.add(new MagicNumberObject(new String[]{"gif"}, new String[]{"47 49 46 38 37 61", "47 49 46 38 39 61"}));
            lists.add(new MagicNumberObject(new String[]{"png"}, new String[]{"89 50 4E 47 0D 0A 1A 0A"}));
            lists.add(new MagicNumberObject(new String[]{"pdf"}, new String[]{"25 50 44 46 2D 31 2E"}));
            lists.add(new MagicNumberObject(new String[]{"zip", "aar", "apk", "docx", "epub", "ipa", "jar", "kmz", "maff", "odp", "ods", "odt", "pk3", "pk4", "pptx", "usdz", "vsdx", "xlsx", "xpi"}, new String[]{"50 4B 03 04", "50 4B 05 06", "50 4B 07 08"}));
            lists.add(new MagicNumberObject(new String[]{"alz"}, new String[]{"41 4C 5A 01"}));
            lists.add(new MagicNumberObject(new String[]{"rar"}, new String[]{"52 61 72 21 1A 07"}));
            lists.add(new MagicNumberObject(new String[]{"txt"}, new String[]{"EF BB BF", "FF FE", "FE FF", "00 00 FE FF", "0E FE FF"}));
            lists.add(new MagicNumberObject(new String[]{"tar"}, new String[]{"75 73 74 61 72 00 30 30", "75 73 74 61 72 20 20 00"}));
            lists.add(new MagicNumberObject(new String[]{"7z"}, new String[]{"37 7A BC AF 27 1C"}));
            lists.add(new MagicNumberObject(new String[]{"gz", "tar.gz"}, new String[]{"1F 8B"}));
            lists.add(new MagicNumberObject(new String[]{"lz4"}, new String[]{"04 22 4D 18"}));
            lists.add(new MagicNumberObject(new String[]{"cab"}, new String[]{"4D 53 43 46"}));
            lists.add(new MagicNumberObject(new String[]{"mkv", "mka", "mks", "mk3d", "webm"}, new String[]{"1A 45 DF A3"}));
            lists.add(new MagicNumberObject(new String[]{"xml"}, new String[]{"3C 00 3F 00 78 00 6D 00 6C 00 20", "00 3C 00 3F 00 78 00 6D 00 6C 00 20", "3C 00 00 00 3F 00 00 00 78 00 00 00 6D 00 00 00 6C 00 00 00 20 00 00 00", "00 00 00 3C 00 00 00 3F 00 00 00 78 00 00 00 6D 00 00 00 6C 00 00 00 20"}));
            lists.add(new MagicNumberObject(new String[]{"m2p", "vob", "mpg", "mpeg"}, new String[]{"00 00 01 BA", "00 00 01 B3"}));
            lists.add(new MagicNumberObject(new String[]{"mp4"}, new String[]{"66 74 79 70 69 73 6F 6D"}));
            lists.add(new MagicNumberObject(new String[]{"zlib"}, new String[]{"78 01", "78 5E", "78 9C", "78 DA", "78 20", "78 7D", "78 BB", "78 F9"}));
            lists.add(new MagicNumberObject(new String[]{"rs"}, new String[]{"52 53 56 4B 44 41 54 41"}));
            lists.add(new MagicNumberObject(new String[]{"ico"}, new String[]{"00 00 01 00"}));
            lists.add(new MagicNumberObject(new String[]{"tif", "tiff"}, new String[]{"49 49 2A 00", "4D 4D 00 2A"}));
            lists.add(new MagicNumberObject(new String[]{"iso"}, new String[]{"43 44 30 30 31"}));
            lists.add(new MagicNumberObject(new String[]{"flif"}, new String[]{"46 4C 49 46"}));
            lists.add(new MagicNumberObject(new String[]{"lep"}, new String[]{"CF 84 01"}));
            lists.add(new MagicNumberObject(new String[]{"jxl"}, new String[]{"00 00 00 0C 4A 58 4C 20 0D 0A 87 0A"}));
            lists.add(new MagicNumberObject(new String[]{"mp3"}, new String[]{"FF FB","FF F3","FF F2"}));
            //lists.add(new MagicNumberObject(new String[]{""}, new String[]{""}));
        }

        default ArrayList<MagicNumberObject> getLists() {
            if (lists.isEmpty()) init();
            return lists;
        }
    }
}
