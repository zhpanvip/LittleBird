package project.graduate.zhpan.littlebird.bean;

import org.litepal.crud.DataSupport;

/**
 * Created by zhpan on 2016/11/20.
 */

public class TaskBean extends DataSupport {
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private String taskName;
    private String taskDescribe;
    private int taskType;   //  0 今日任务 1 计划任务
    private float completePercent;
    private float qualityPercent;
    private long startTime;
    private long endTime;
    private boolean isCommit;
    private String checkPerson;

    public int getTaskType() {
        return taskType;
    }

    public void setTaskType(int taskType) {
        this.taskType = taskType;
    }

    public String getTaskDescribe() {
        return taskDescribe;
    }

    public void setTaskDescribe(String taskDescribe) {
        this.taskDescribe = taskDescribe;
    }

    public String getCheckPerson() {
        return checkPerson;
    }

    public void setCheckPerson(String checkPerson) {
        this.checkPerson = checkPerson;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public float getCompletePercent() {
        return completePercent;
    }

    public void setCompletePercent(float completePercent) {
        this.completePercent = completePercent;
    }

    public float getQualityPercent() {
        return qualityPercent;
    }

    public void setQualityPercent(float qualityPercent) {
        this.qualityPercent = qualityPercent;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public boolean isCommit() {
        return isCommit;
    }

    public void setCommit(boolean commit) {
        isCommit = commit;
    }
}
