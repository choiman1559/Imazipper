package com.imazipper.lib;

import java.io.*;
import java.util.ArrayList;

public class Splitter {
    protected static boolean verbose = false;

    public static TaskResult Split(File input, File output, boolean forceSplit) {
        TaskResult result = new TaskResult();

        try {
            ArrayList<Long> headerIndex = new ArrayList<>();
            ArrayList<MagicNumberObject> fileList = new ArrayList<>();
            int indexCount = 0;

            for (MagicNumberObject object : FileMagicNumberTool.magicNumbers.getLists()) {
                for (byte[] header : object.getHeaderSig()) {
                    long search = FileMagicNumberTool.indexOf(input, header);
                    if (search != -1 && !headerIndex.contains(search)) {
                        headerIndex.add(indexCount, search);
                        fileList.add(indexCount, object);
                        if (verbose) System.out.println(indexCount + "/" + search + "/" + object.FileExtension[0]);
                        indexCount += 1;
                        break;
                    }
                }
            }

            if (indexCount == 1) {
                result.IS_TASK_SUCCESS = false;
                result.RETURN_CODE = ErrorResultCode.Error_Split_JUST_ONE_HEADER;
                result.HAS_EXCEPTION = false;
                return result;
            } else if (indexCount == 0) {
                result.IS_TASK_SUCCESS = false;
                result.RETURN_CODE = ErrorResultCode.Error_Split_HEADER_NOT_FOUND;
                result.HAS_EXCEPTION = false;
                return result;
            }

            File outFolder = null;
            if (output.isFile()) {
                outFolder = new File(output.getParent() + "/Imazipper_output");
            } else if (output.isDirectory()) {
                outFolder = new File(output.getAbsolutePath() + "/Imazipper_output");
            }

            outFolder.mkdirs();

            long lastStartPosition = 0;
            for (int i = 0; i < indexCount; i++) {
                File out = new File(outFolder.getAbsolutePath() + "/split_output_(" + i + ")." + fileList.get(i).FileExtension[0]);
                if (out.exists()) {
                    out.delete();
                    out.createNewFile();
                }

                FileInputStream fileInputStream = new FileInputStream(input);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                FileOutputStream stream = new FileOutputStream(out);

                int data = 0;
                bufferedInputStream.skip(lastStartPosition);
                boolean isNotLastHeader = i + 1 != indexCount;

                if(forceSplit) {
                    long var =  isNotLastHeader ? headerIndex.get(i + 1) : -2;
                    for (long j = lastStartPosition; (isNotLastHeader && j < var) && data != -1; j++) {
                        data = bufferedInputStream.read();
                        stream.write(data);
                    }
                    if (isNotLastHeader) lastStartPosition = var;
                } else {
                    while (data != -1) {
                        data = bufferedInputStream.read();
                        stream.write(data);
                    }
                    if (isNotLastHeader) lastStartPosition = headerIndex.get(i + 1);
                }

                stream.close();
                bufferedInputStream.close();
                fileInputStream.close();
            }
        } catch (IOException e) {
            result.IS_TASK_SUCCESS = false;
            result.RETURN_CODE = ErrorResultCode.Error_Global_FILE_IO_EXCEPTION;
            result.HAS_EXCEPTION = true;
            result.TASK_EXCEPTION = e;
            return result;
        } catch (IndexOutOfBoundsException e) {
            result.IS_TASK_SUCCESS = false;
            result.RETURN_CODE = ErrorResultCode.Error_Split_OUT_OF_INDEX;
            result.HAS_EXCEPTION = true;
            result.TASK_EXCEPTION = e;
            return result;
        } catch (OutOfMemoryError e) {
            result.IS_TASK_SUCCESS = false;
            result.RETURN_CODE = ErrorResultCode.Error_Global_OUT_OF_MEMORY;
            result.HAS_EXCEPTION = true;
            result.TASK_EXCEPTION = e;
            return result;
        }

        result.IS_TASK_SUCCESS = true;
        result.HAS_EXCEPTION = false;
        return result;
    }

    public static void setVerbose(boolean verbose) {
        Splitter.verbose = verbose;
    }
}
