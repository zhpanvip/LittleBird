package project.graduate.zhpan.littlebird.bean;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

/**
 * Created by zhpan on 2016/11/20.
 */

public class TaskBean extends DataSupport {
    private long id;
    @Column(unique = true, defaultValue = "unknown")
    private String taskName;
    private String taskDescribe;
    private int taskType;   //  0 今日任务 1 计划任务
    private String createDate; //   创建日期
    private int completePercent;
    private int qualityPercent;
    private long startTime;
    private long realStartTime;
    private long commitTime;
    private long endTime;
    private int taskState;  //  0待开始 1待提交 2已提交\

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCommitTime() {
        return commitTime;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public void setCommitTime(long commitTime) {
        this.commitTime = commitTime;
    }

    public long getRealStartTime() {
        return realStartTime;
    }

    public void setRealStartTime(long realStartTime) {
        this.realStartTime = realStartTime;
    }


    public int getTaskState() {
        return taskState;
    }

    public void setTaskState(int taskState) {
        this.taskState = taskState;
    }

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

    public int getCompletePercent() {
        return completePercent;
    }

    public void setCompletePercent(int completePercent) {
        this.completePercent = completePercent;
    }

    public int getQualityPercent() {
        return qualityPercent;
    }

    public void setQualityPercent(int qualityPercent) {
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

}
