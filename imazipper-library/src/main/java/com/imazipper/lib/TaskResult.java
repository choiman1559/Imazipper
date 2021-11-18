package com.imazipper.lib;

public class TaskResult {
    protected boolean IS_TASK_SUCCESS = false;
    protected boolean HAS_EXCEPTION = false;
    protected String RETURN_CODE = ErrorResultCode.Error_Global_NONE;
    protected Throwable TASK_EXCEPTION = null;

    public boolean isTaskSuccess() {
        return IS_TASK_SUCCESS;
    }

    public boolean hasException() {
        return !IS_TASK_SUCCESS && HAS_EXCEPTION;
    }

    public String getErrorCode() {
        if(RETURN_CODE == null) return ErrorResultCode.Error_Global_NONE;
        else return RETURN_CODE;
    }

    public Throwable getTaskException() {
        return TASK_EXCEPTION;
    }
}