package com.imazipper.lib;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Combiner {
    static boolean verbose = false;

    public static TaskResult Combine(File file1, File file2, File output) {
        TaskResult result;
        try {
            if (!output.exists())
                throw new FileNotFoundException("Output target: " + output.getAbsolutePath() + " is not exist!");

            File finalOutput;
            String fileName = file1.getName();
            if (output.isFile()) finalOutput = output;
            else finalOutput = new File(output.getAbsolutePath()
                    + "/Imazipper_Combine_Output_"
                    + DateTimeFormatter.ofPattern("yyyy-MM-dd(HH:mm:ss)").format(LocalDateTime.now()) + "."
                    + fileName.substring(fileName.lastIndexOf(".") + 1));
            if (finalOutput.exists()) {
                finalOutput.delete();
            }
            finalOutput.createNewFile();

            ArrayList<BufferedInputStream> list = new ArrayList<>();
            list.add(new BufferedInputStream(new FileInputStream(file1)));
            list.add(new BufferedInputStream(new FileInputStream(file2)));

            result = Combine(list, new FileOutputStream(finalOutput));
        } catch (OutOfMemoryError e) {
            result = new TaskResult();
            result.IS_TASK_SUCCESS = false;
            result.RETURN_CODE = ErrorResultCode.Error_Global_OUT_OF_MEMORY;
            result.HAS_EXCEPTION = true;
            result.TASK_EXCEPTION = e;
        } catch (FileNotFoundException e) {
            result = new TaskResult();
            result.IS_TASK_SUCCESS = false;
            result.RETURN_CODE = ErrorResultCode.Error_Global_FILE_NOT_FOUND;
            result.HAS_EXCEPTION = true;
            result.TASK_EXCEPTION = e;
        } catch (IOException e) {
            result = new TaskResult();
            result.IS_TASK_SUCCESS = false;
            result.RETURN_CODE = ErrorResultCode.Error_Global_FILE_IO_EXCEPTION;
            result.HAS_EXCEPTION = true;
            result.TASK_EXCEPTION = e;
        } catch (Exception e) {
            result = new TaskResult();
            result.IS_TASK_SUCCESS = false;
            result.RETURN_CODE = ErrorResultCode.Error_Global_NONE;
            result.HAS_EXCEPTION = true;
            result.TASK_EXCEPTION = e;
        }
        return result;
    }

    public static TaskResult Combine(ArrayList<BufferedInputStream> streams, FileOutputStream output) {
        TaskResult result = new TaskResult();
        try {
            int data;
            for(BufferedInputStream stream : streams) {
                data = stream.read();
                while (data != -1) {
                    output.write(data);
                    data = stream.read();
                }
                stream.close();
            }

            output.flush();
            output.close();
            result.IS_TASK_SUCCESS = true;
        } catch (IOException e) {
            result.IS_TASK_SUCCESS = false;
            result.HAS_EXCEPTION = true;
            result.RETURN_CODE = ErrorResultCode.Error_Global_FILE_IO_EXCEPTION;
            result.TASK_EXCEPTION = e;
        }
        return result;
    }

    public static void setVerbose(boolean verbose) {
        Combiner.verbose = verbose;
    }
}