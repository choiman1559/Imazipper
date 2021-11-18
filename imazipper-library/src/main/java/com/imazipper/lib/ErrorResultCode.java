package com.imazipper.lib;

public interface ErrorResultCode {
    String Error_Global_NONE = "ERROR_GLOBAL_NONE";
    String Error_Global_FILE_NOT_FOUND = "ERROR_GLOBAL_FILE_NOT_FOUND";
    String Error_Global_FILE_IO_EXCEPTION = "ERROR_GLOBAL_FILE_IO_EXCEPTION";
    String Error_Global_OUT_OF_MEMORY = "ERROR_GLOBAL_OUT_OF_MEMORY";

    String Error_Split_HEADER_NOT_FOUND = "ERROR_SPLIT_HEADER_NOT_FOUND";
    String Error_Split_JUST_ONE_HEADER = "ERROR_SPLIT_JUST_ONE_HEADER";
    String Error_Split_OUT_OF_INDEX = "ERROR_SPLIT_OUT_OF_INDEX";
}