package com.xun.urlmappingdemo;

/**
 * Created by xunwang on 2017/6/20.
 */

public class SoMapping {
    //多应用公用的URL
    //提交选择题
    public static final String COMM_SUBMIT_CHOICE_URL = "2001"; // @encrypt "{user_id}/{type}/{id}/choice"
    //上传笔迹文件
    public static final String COMM_UPLOAD_DRAWS_URL = "2002"; // @encrypt "{user_id}/{type}/{id}/upload"
    //作业结束
    public static final String COMM_HOMEWORK_FINISH_URL = "2003"; // @encrypt "{user_id}/{type}/{id}/finish"
    //作业详情
    public static final String COMM_HOMEWORK_DETAIL_URL = "2004"; // @encrypt "{user_id}/{type}/{id}"
    //错题反馈
    public static final String COMM_QUESTION_FEEDBACK_URL = "2005"; // @encrypt "question/feedback"

    //homework内公用
    public static final String HOMEWORK_UPDATE = "1000001"; // @encrypt "{user_id}/updated-homework"
    public static final String HOMEWORK = "1000002"; // @encrypt "{user_id}/homework"
    public static final String HOMEWORK_CHOICE = "1000003"; // @encrypt "{user_id}/homework/{homework_id}/choice"
    public static final String HOMEWORK_FINISH = "1000004"; // @encrypt "{user_id}/homework/{homework_id}/finish"
    public static final String HOMEWORK_DONE = "1000005"; // @encrypt "{user_id}/homework/done"

    public static final String EXERCISE_EID_FINISH = "3001"; // @encrypt "{user_id}/exercise/{eid}/finish"
    public static final String EXERCISE = "3002"; // @encrypt "{user_id}/exercise"
    public static final String EXERCISE_HOMEWORK = "3003"; // @encrypt "{user_id}/homework/{homework_id}/exercise"
    public static final String EXERCISE_EID_SUSPEND = "3004"; // @encrypt "{user_id}/exercise/{eid}/suspend"
    public static final String EXERCISE_HOMEWORK_HISTORY = "3005"; // @encrypt
    // "{user_id}/homework/{homework_id}/exercise-history"
    public static final String EXERCISE_HISTORY = "3006"; // @encrypt "{user_id}/exercise-history"
    public static final String EXERCISE_CORRECT = "3007"; // @encrypt "{user_id}/exercise/{source_id}/correct"

    //作业
    public static final String HOMEWORK_ID = "4001"; // @encrypt "{user_id}/homework/{homework_id}"
    public static final String HOMEWORK_MISTAKE_POINTS = "4002"; // @encrypt
    // "{user_id}/homework/{homework_id}/mistake-points"
    public static final String HOMEWORK_CLAIM_LIST = "4003"; // @encrypt "{user_id}/homework/claim/list"
    public static final String HOMEWORK_CLAIM = "4004"; // @encrypt "{user_id}/homework/{homework_id}/claim"
    public static final String HOMEWORK_TASK_DETAIL = "4005"; // @encrypt "{user_id}/homework/correct/task/{task_id}"
    public static final String HOMEWORK_CLAIM_STU_LIST = "4006"; // @encrypt
    // "{user_id}/homework/{homework_id}/claim/stu-list"
    public static final String HOMEWORK_CORRECT_TASK_RESULT = "4007";// @encrypt
    // "{user_id}/homework/correct/task/{task_id}/result"

    //错题本
    public static final String MISTAKE_NOTE = "5001"; // @encrypt "{user_id}/mistake-note"

    //app更新
    public static final String APP_UPDATE_URL = "6001"; // @encrypt "app-version"
}
