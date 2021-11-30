package com.peteryu.filestorage.constant;

public final class ConsMsg {
    private ConsMsg() { //can't be instantiated
    }

    //signup messages
    public static final String SIGNUP_FAILED_ERR = "Sign Failed, Please try again later";
    public static final String SIGNUP_USER_EXISTING_ERR ="User name exists, Please try a different user name";
    public static final String SIGNUP_SUCCESS ="Sign up is successful! Welcome ";

    public static final String FILE_NOT_SELECTED_ERR="Please select a file before upload";
    public static final String FILE_SIZE_LIMIT_EXCEED="ERROR: File size exceeds the limit";
    public static final String FILE_DUPLICATE_ERR="Duplicated file with the same file name";
    public static final String FILE_UPLOAD_SUCCESS="File successfully uploaded";
    public static final String FILE_UPLOAD_FAILED_ERR="Upload Failed, Please try again later";

    public static final String FILE_UNKNOWN_ERR="Unexpected exception";

    public static final String FILE_DELETE_FAILURE="Deleting File failed, please try later";
    public static final String FILE_DELETE_SUCCESS="Deleting File is successful";

}