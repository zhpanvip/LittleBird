package project.graduate.zhpan.littlebird.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhpan on 2016/12/25.
 */

public class ColleagueBean extends LittleBirdBean {


    /**
     * employeeList : [{"actualEmployeeId":null,"actualPositiveTime":"2015-08-06T00:00:00","applicationId":null,"applicationKey":null,"beginWorkTime":"2014-01-01T00:00:00","beyondCompany":null,"birthTime":"1989-05-12T00:00:00","boardingTime":"2015-05-06T00:00:00","cardid":"410823198905127913","checkType":null,"company":null,"contractno":null,"degree":"","departmentId":"0181","departmentInfo":null,"departmentName":null,"educationBackground":"3","email":"chenzg@i2finance.net","employeeAdress":"上海市浦东新区源深路乳山路505弄22栋302室","employeeDuty":"0049","employeeGrade":"1","employeeId":"00000102","employeeName":"陈志刚","employeeNation":"汉","employmentWay":"","expectedPositiveTime":"2015-08-06T00:00:00","grade":null,"gradename":null,"gradutedSchool":"河南工业大学","gradutedTime":"2014-07-01T00:00:00","grassLevelId":null,"householdAddress":"河南省武陟县龙源镇任徐店村八号院","hukou":"河南","hukouType":"2","imageId":"http://210.14.78.246:82/images/4/00000279/1446108016885.jpg","integration":553,"isCheckImsi":null,"isFixedPlaceWorking":null,"isLeave":null,"isOnDuty":"01","laborContractBeginTime":"2015-05-06T00:00:00","leaveReason":null,"leaveTime":null,"level":null,"major":"通讯工程","marriedStatus":"0","mobile":"13612381239","politicsStatus":"6","projectName":null,"projectNameSignStat":null,"properties":{},"sex":"1","shortProjectName":null,"stock":200,"taskCount":0,"taskCountFinish":null,"taskcountPercentage":null,"totalTaskCounts":null,"workLife":null},{"actualEmployeeId":null,"actualPositiveTime":"2012-01-07T00:00:00","applicationId":null,"applicationKey":null,"beginWorkTime":"2011-07-08T00:00:00","beyondCompany":null,"birthTime":"1989-11-09T00:00:00","boardingTime":"2011-07-08T00:00:00","cardid":"410782198911092416","checkType":null,"company":null,"contractno":null,"degree":"","departmentId":"0181","departmentInfo":null,"departmentName":null,"educationBackground":"3","email":"liupeng@i2finance.net","employeeAdress":"上海市浦东新区创新中路180弄3号楼402室","employeeDuty":"0051","employeeGrade":"3","employeeId":"00000447","employeeName":"刘鹏","employeeNation":"","employmentWay":"招聘","expectedPositiveTime":"2014-10-31T00:00:00","grade":null,"gradename":null,"gradutedSchool":"中原工学院","gradutedTime":"2011-07-01T00:00:00","grassLevelId":null,"householdAddress":"河南省辉县市欲河镇丰城村","hukou":"河南","hukouType":"6","imageId":"http://210.14.78.246:82/images/3/00000202/1447741914998.JPG","integration":1819,"isCheckImsi":null,"isFixedPlaceWorking":null,"isLeave":null,"isOnDuty":"01","laborContractBeginTime":"2014-07-31T00:00:00","leaveReason":null,"leaveTime":null,"level":null,"major":"计算机科学与技术","marriedStatus":"1","mobile":"13917296540","politicsStatus":"5","projectName":null,"projectNameSignStat":null,"properties":{},"sex":"1","shortProjectName":null,"stock":20000,"taskCount":0,"taskCountFinish":null,"taskcountPercentage":null,"totalTaskCounts":null,"workLife":4.3},{"actualEmployeeId":null,"actualPositiveTime":null,"applicationId":null,"applicationKey":null,"beginWorkTime":"2013-07-01T00:00:00","beyondCompany":null,"birthTime":"1990-03-12T00:00:00","boardingTime":"2015-05-04T00:00:00","cardid":"413026199003120352","checkType":null,"company":null,"contractno":null,"degree":"","departmentId":"0181","departmentInfo":null,"departmentName":null,"educationBackground":"3","email":"zhengwei@i2finance.net","employeeAdress":"上海市浦东新区湘南路香楠小区","employeeDuty":"0049","employeeGrade":"1","employeeId":"00000460","employeeName":"郑伟","employeeNation":"汉","employmentWay":"招聘","expectedPositiveTime":null,"grade":null,"gradename":null,"gradutedSchool":"郑州大学西亚斯国际学院","gradutedTime":"2013-07-01T00:00:00","grassLevelId":null,"householdAddress":"河南省固始县城关镇南后街109号","hukou":"河南","hukouType":"5","imageId":"http://210.14.78.246:82/images/3/00000202/1447743257603.JPG","integration":533,"isCheckImsi":null,"isFixedPlaceWorking":null,"isLeave":null,"isOnDuty":"01","laborContractBeginTime":"2015-05-04T00:00:00","leaveReason":null,"leaveTime":null,"level":null,"major":"软件工程","marriedStatus":"0","mobile":"17317247236","politicsStatus":"5","projectName":null,"projectNameSignStat":null,"properties":{},"sex":"1","shortProjectName":null,"stock":200,"taskCount":0,"taskCountFinish":null,"taskcountPercentage":null,"totalTaskCounts":null,"workLife":0.4},{"actualEmployeeId":null,"actualPositiveTime":null,"applicationId":null,"applicationKey":null,"beginWorkTime":"2014-06-23T00:00:00","beyondCompany":null,"birthTime":"1991-02-14T00:00:00","boardingTime":"2015-11-23T00:00:00","cardid":"410122199102147419","checkType":null,"company":null,"contractno":null,"degree":"学士","departmentId":"0181","departmentInfo":null,"departmentName":null,"educationBackground":"3","email":"xusl@i2finance.net","employeeAdress":"上海市浦东新区菏泽路825弄金泽苑2栋6号101","employeeDuty":"0049","employeeGrade":"1","employeeId":"00000652","employeeName":"徐胜利","employeeNation":"汉","employmentWay":"招聘","expectedPositiveTime":"2016-05-23T00:00:00","grade":null,"gradename":null,"gradutedSchool":"河南师范大学","gradutedTime":"2015-05-23T00:00:00","grassLevelId":null,"householdAddress":"河南郑州中牟县大孟镇阎堂村006号","hukou":"河南","hukouType":"6","imageId":"http://210.14.78.246:82/images/3/00000202/1448246915194.jpg","integration":151,"isCheckImsi":null,"isFixedPlaceWorking":null,"isLeave":null,"isOnDuty":"01","laborContractBeginTime":"2015-11-23T00:00:00","leaveReason":null,"leaveTime":null,"level":null,"major":"电子信息工程","marriedStatus":"0","mobile":"18521323214","politicsStatus":"6","projectName":null,"projectNameSignStat":null,"properties":{},"sex":"1","shortProjectName":null,"stock":0,"taskCount":0,"taskCountFinish":null,"taskcountPercentage":null,"totalTaskCounts":null,"workLife":null},{"actualEmployeeId":null,"actualPositiveTime":null,"applicationId":null,"applicationKey":null,"beginWorkTime":"2014-03-07T00:00:00","beyondCompany":null,"birthTime":"1992-05-23T00:00:00","boardingTime":"2015-12-07T00:00:00","cardid":"410482199205239351","checkType":null,"company":null,"contractno":null,"degree":"学士","departmentId":"0181","departmentInfo":null,"departmentName":null,"educationBackground":"3","email":"lipp@i2finance.net","employeeAdress":"上海市宝山区牡丹江路永靖新村67","employeeDuty":"0048","employeeGrade":"2","employeeId":"00000669","employeeName":"李朋朋","employeeNation":"汉","employmentWay":"招聘","expectedPositiveTime":"2016-06-07T00:00:00","grade":null,"gradename":null,"gradutedSchool":"郑州大学","gradutedTime":"2015-06-07T00:00:00","grassLevelId":null,"householdAddress":"河南省汝州市陵头乡李爻村4组","hukou":"河南","hukouType":"6","imageId":"http://210.14.78.246:82/images/3/00000202/1449539703457.jpg","integration":685,"isCheckImsi":null,"isFixedPlaceWorking":null,"isLeave":null,"isOnDuty":"01","laborContractBeginTime":"2015-12-07T00:00:00","leaveReason":null,"leaveTime":null,"level":null,"major":"计算机辅助设计","marriedStatus":"0","mobile":"18637506007","politicsStatus":"5","projectName":null,"projectNameSignStat":null,"properties":{},"sex":"1","shortProjectName":null,"stock":0,"taskCount":0,"taskCountFinish":null,"taskcountPercentage":null,"totalTaskCounts":null,"workLife":null},{"actualEmployeeId":null,"actualPositiveTime":"2016-10-19T00:00:00","applicationId":null,"applicationKey":null,"beginWorkTime":"2013-12-02T00:00:00","beyondCompany":null,"birthTime":"1992-11-16T00:00:00","boardingTime":"2016-04-19T00:00:00","cardid":"421281199211162333","checkType":null,"company":null,"contractno":null,"degree":"学士学位","departmentId":"0181","departmentInfo":null,"departmentName":null,"educationBackground":"3","email":"fandg@i2finance.net","employeeAdress":"上海市浦东新区华夏东路孙耀路","employeeDuty":"0050","employeeGrade":"1","employeeId":"00000884","employeeName":"范登高","employeeNation":"汉","employmentWay":"社会招聘","expectedPositiveTime":"2016-10-19T00:00:00","grade":null,"gradename":null,"gradutedSchool":"华中科技大学文华学院","gradutedTime":"2015-07-01T00:00:00","grassLevelId":null,"householdAddress":"湖北省武汉市洪山区文化园路8号","hukou":"湖北","hukouType":"5","imageId":"http://210.14.78.246:82/images/3/00000202/1461035243680.jpg","integration":54,"isCheckImsi":null,"isFixedPlaceWorking":null,"isLeave":null,"isOnDuty":"01","laborContractBeginTime":"2016-04-19T00:00:00","leaveReason":null,"leaveTime":null,"level":null,"major":"软件工程","marriedStatus":"0","mobile":"15607106852","politicsStatus":"4","projectName":null,"projectNameSignStat":null,"properties":{},"sex":"1","shortProjectName":null,"stock":0,"taskCount":0,"taskCountFinish":null,"taskcountPercentage":null,"totalTaskCounts":null,"workLife":2},{"actualEmployeeId":null,"actualPositiveTime":"2016-11-16T00:00:00","applicationId":null,"applicationKey":null,"beginWorkTime":"2014-03-03T00:00:00","beyondCompany":null,"birthTime":"1989-04-27T00:00:00","boardingTime":"2016-05-17T00:00:00","cardid":"342422198904274319","checkType":null,"company":null,"contractno":null,"degree":"学士学位","departmentId":"0181","departmentInfo":null,"departmentName":null,"educationBackground":"3","email":"xiacl@i2finance.net","employeeAdress":"浦东新区唐镇龙新路395号","employeeDuty":"0049","employeeGrade":"1","employeeId":"00000910","employeeName":"夏成龙","employeeNation":"汉","employmentWay":"招聘","expectedPositiveTime":"2016-11-16T00:00:00","grade":null,"gradename":null,"gradutedSchool":"池州学院","gradutedTime":"2013-06-15T00:00:00","grassLevelId":null,"householdAddress":"皖六安市寿县安丰镇","hukou":"安徽","hukouType":"6","imageId":"http://210.14.78.246:82/images/3/00000202/1469686671431.jpg","integration":109,"isCheckImsi":null,"isFixedPlaceWorking":null,"isLeave":null,"isOnDuty":"01","laborContractBeginTime":"2016-05-16T00:00:00","leaveReason":null,"leaveTime":null,"level":null,"major":"数学与应用数学","marriedStatus":"1","mobile":"13310088140","politicsStatus":"6","projectName":null,"projectNameSignStat":null,"properties":{},"sex":"1","shortProjectName":null,"stock":0,"taskCount":0,"taskCountFinish":null,"taskcountPercentage":null,"totalTaskCounts":null,"workLife":null},{"actualEmployeeId":null,"actualPositiveTime":null,"applicationId":null,"applicationKey":null,"beginWorkTime":"2014-07-01T00:00:00","beyondCompany":null,"birthTime":"1993-02-01T00:00:00","boardingTime":"2016-09-12T00:00:00","cardid":"410526199302018213","checkType":null,"company":null,"contractno":null,"degree":"学士学位","departmentId":"0181","departmentInfo":null,"departmentName":null,"educationBackground":"3","email":"zhangpan@i2finance.net","employeeAdress":"上海市闵行区莘庄镇畹町路莘南花苑二村61号楼501室","employeeDuty":"0048","employeeGrade":"1","employeeId":"00001057","employeeName":"张攀","employeeNation":"汉","employmentWay":"社会招聘","expectedPositiveTime":"2017-03-12T00:00:00","grade":null,"gradename":null,"gradutedSchool":"河南大学","gradutedTime":"2014-07-01T00:00:00","grassLevelId":null,"householdAddress":"河南省滑焦虎乡双沟前街村202号","hukou":"河南","hukouType":"6","imageId":"http://210.14.78.246:82/images/3/00000202/1473664409746.jpg","integration":47,"isCheckImsi":null,"isFixedPlaceWorking":null,"isLeave":null,"isOnDuty":"01","laborContractBeginTime":"2016-09-12T00:00:00","leaveReason":null,"leaveTime":null,"level":null,"major":"网络工程","marriedStatus":"0","mobile":"15515269670","politicsStatus":"5","projectName":null,"projectNameSignStat":null,"properties":{},"sex":"1","shortProjectName":null,"stock":0,"taskCount":0,"taskCountFinish":null,"taskcountPercentage":null,"totalTaskCounts":null,"workLife":null},{"actualEmployeeId":null,"actualPositiveTime":null,"applicationId":null,"applicationKey":null,"beginWorkTime":"2013-07-01T00:00:00","beyondCompany":null,"birthTime":"1990-01-07T00:00:00","boardingTime":"2016-11-15T00:00:00","cardid":"430725199001070059","checkType":null,"company":null,"contractno":null,"degree":"学士学位","departmentId":"0181","departmentInfo":null,"departmentName":null,"educationBackground":"3","email":"huangjl@i2finance.net","employeeAdress":"上海市浦东新区长岛路1280弄599号502","employeeDuty":"0050","employeeGrade":"1","employeeId":"00001097","employeeName":"黄佳礼","employeeNation":"汉","employmentWay":"内部推荐（刘鹏）","expectedPositiveTime":"2017-05-15T00:00:00","grade":null,"gradename":null,"gradutedSchool":"北方民族大学","gradutedTime":"2013-07-01T00:00:00","grassLevelId":null,"householdAddress":"湖南省桃源县漳江镇文昌西路015号","hukou":"湖南","hukouType":"5","imageId":"http://210.14.78.246:82/images/3/00000202/1479697803304.jpg","integration":11,"isCheckImsi":null,"isFixedPlaceWorking":null,"isLeave":null,"isOnDuty":"01","laborContractBeginTime":"2016-11-15T00:00:00","leaveReason":null,"leaveTime":null,"level":null,"major":"数学与应用数学","marriedStatus":"0","mobile":"18321019848","politicsStatus":"5","projectName":null,"projectNameSignStat":null,"properties":{},"sex":"1","shortProjectName":null,"stock":0,"taskCount":0,"taskCountFinish":null,"taskcountPercentage":null,"totalTaskCounts":null,"workLife":null},{"actualEmployeeId":null,"actualPositiveTime":null,"applicationId":null,"applicationKey":null,"beginWorkTime":"2012-07-01T00:00:00","beyondCompany":null,"birthTime":"1990-07-22T00:00:00","boardingTime":"2016-11-15T00:00:00","cardid":"211103199007222112","checkType":null,"company":null,"contractno":null,"degree":"学士学位","departmentId":"0181","departmentInfo":null,"departmentName":null,"educationBackground":"3","email":"dongwen@i2finance.net","employeeAdress":"上海市杨浦区殷高路7弄8号602","employeeDuty":"0050","employeeGrade":"1","employeeId":"00001098","employeeName":"董文","employeeNation":"汉","employmentWay":"社会招聘","expectedPositiveTime":"2017-05-15T00:00:00","grade":null,"gradename":null,"gradutedSchool":"辽宁对外经贸学院","gradutedTime":"2012-07-01T00:00:00","grassLevelId":null,"householdAddress":"辽宁省盘锦市兴隆台区曙光街曙新社区14-4-401","hukou":"辽宁","hukouType":"5","imageId":"http://210.14.78.246:82/images/3/00000202/1481620173965.jpg","integration":5,"isCheckImsi":null,"isFixedPlaceWorking":null,"isLeave":null,"isOnDuty":"01","laborContractBeginTime":"2016-11-15T00:00:00","leaveReason":null,"leaveTime":null,"level":null,"major":"信息管理与信息系统","marriedStatus":"0","mobile":"17717338623","politicsStatus":"5","projectName":null,"projectNameSignStat":null,"properties":{},"sex":"1","shortProjectName":null,"stock":0,"taskCount":0,"taskCountFinish":null,"taskcountPercentage":null,"totalTaskCounts":null,"workLife":null}]
     * retCode : 000000
     * retMsg : 交易成功
     * method : tc.colleague.getcollegeList
     * format : JSON
     * version : 1.9.1
     */

    private String retCode;
    private String retMsg;
    private String method;
    private String format;
    private String version;
    private List<EmployeeListBean> employeeList;

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<EmployeeListBean> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<EmployeeListBean> employeeList) {
        this.employeeList = employeeList;
    }

    public static class EmployeeListBean implements Serializable {
        /**
         * actualEmployeeId : null
         * actualPositiveTime : 2015-08-06T00:00:00
         * applicationId : null
         * applicationKey : null
         * beginWorkTime : 2014-01-01T00:00:00
         * beyondCompany : null
         * birthTime : 1989-05-12T00:00:00
         * boardingTime : 2015-05-06T00:00:00
         * cardid : 410823198905127913
         * checkType : null
         * company : null
         * contractno : null
         * degree : 
         * departmentId : 0181
         * departmentInfo : null
         * departmentName : null
         * educationBackground : 3
         * email : chenzg@i2finance.net
         * employeeAdress : 上海市浦东新区源深路乳山路505弄22栋302室
         * employeeDuty : 0049
         * employeeGrade : 1
         * employeeId : 00000102
         * employeeName : 陈志刚
         * employeeNation : 汉
         * employmentWay : 
         * expectedPositiveTime : 2015-08-06T00:00:00
         * grade : null
         * gradename : null
         * gradutedSchool : 河南工业大学
         * gradutedTime : 2014-07-01T00:00:00
         * grassLevelId : null
         * householdAddress : 河南省武陟县龙源镇任徐店村八号院
         * hukou : 河南
         * hukouType : 2
         * imageId : http://210.14.78.246:82/images/4/00000279/1446108016885.jpg
         * integration : 553
         * isCheckImsi : null
         * isFixedPlaceWorking : null
         * isLeave : null
         * isOnDuty : 01
         * laborContractBeginTime : 2015-05-06T00:00:00
         * leaveReason : null
         * leaveTime : null
         * level : null
         * major : 通讯工程
         * marriedStatus : 0
         * mobile : 13612381239
         * politicsStatus : 6
         * projectName : null
         * projectNameSignStat : null
         * properties : {}
         * sex : 1
         * shortProjectName : null
         * stock : 200
         * taskCount : 0
         * taskCountFinish : null
         * taskcountPercentage : null
         * totalTaskCounts : null
         * workLife : null
         */

        private Object actualEmployeeId;
        private String actualPositiveTime;
        private Object applicationId;
        private Object applicationKey;
        private String beginWorkTime;
        private Object beyondCompany;
        private String birthTime;
        private String boardingTime;
        private String cardid;
        private Object checkType;
        private Object company;
        private Object contractno;
        private String degree;
        private String departmentId;
        private Object departmentInfo;
        private Object departmentName;
        private String educationBackground;
        private String email;
        private String employeeAdress;
        private String employeeDuty;
        private String employeeGrade;
        private String employeeId;
        private String employeeName;
        private String employeeNation;
        private String employmentWay;
        private String expectedPositiveTime;
        private Object grade;
        private Object gradename;
        private String gradutedSchool;
        private String gradutedTime;
        private Object grassLevelId;
        private String householdAddress;
        private String hukou;
        private String hukouType;
        private String imageId;
        private int integration;
        private Object isCheckImsi;
        private Object isFixedPlaceWorking;
        private Object isLeave;
        private String isOnDuty;
        private String laborContractBeginTime;
        private Object leaveReason;
        private Object leaveTime;
        private Object level;
        private String major;
        private String marriedStatus;
        private String mobile;
        private String politicsStatus;
        private Object projectName;
        private Object projectNameSignStat;
        private PropertiesBean properties;
        private String sex;
        private Object shortProjectName;
        private int stock;
        private int taskCount;
        private Object taskCountFinish;
        private Object taskcountPercentage;
        private Object totalTaskCounts;
        private Object workLife;
        private String duty;
        private String groupIn;
        private String part;

        public String getDuty() {
            return duty;
        }

        public void setDuty(String duty) {
            this.duty = duty;
        }

        public String getGroupIn() {
            return groupIn;
        }

        public void setGroupIn(String groupIn) {
            this.groupIn = groupIn;
        }

        public String getPart() {
            return part;
        }

        public void setPart(String part) {
            this.part = part;
        }

        public Object getActualEmployeeId() {
            return actualEmployeeId;
        }

        public void setActualEmployeeId(Object actualEmployeeId) {
            this.actualEmployeeId = actualEmployeeId;
        }

        public String getActualPositiveTime() {
            return actualPositiveTime;
        }

        public void setActualPositiveTime(String actualPositiveTime) {
            this.actualPositiveTime = actualPositiveTime;
        }

        public Object getApplicationId() {
            return applicationId;
        }

        public void setApplicationId(Object applicationId) {
            this.applicationId = applicationId;
        }

        public Object getApplicationKey() {
            return applicationKey;
        }

        public void setApplicationKey(Object applicationKey) {
            this.applicationKey = applicationKey;
        }

        public String getBeginWorkTime() {
            return beginWorkTime;
        }

        public void setBeginWorkTime(String beginWorkTime) {
            this.beginWorkTime = beginWorkTime;
        }

        public Object getBeyondCompany() {
            return beyondCompany;
        }

        public void setBeyondCompany(Object beyondCompany) {
            this.beyondCompany = beyondCompany;
        }

        public String getBirthTime() {
            return birthTime;
        }

        public void setBirthTime(String birthTime) {
            this.birthTime = birthTime;
        }

        public String getBoardingTime() {
            return boardingTime;
        }

        public void setBoardingTime(String boardingTime) {
            this.boardingTime = boardingTime;
        }

        public String getCardid() {
            return cardid;
        }

        public void setCardid(String cardid) {
            this.cardid = cardid;
        }

        public Object getCheckType() {
            return checkType;
        }

        public void setCheckType(Object checkType) {
            this.checkType = checkType;
        }

        public Object getCompany() {
            return company;
        }

        public void setCompany(Object company) {
            this.company = company;
        }

        public Object getContractno() {
            return contractno;
        }

        public void setContractno(Object contractno) {
            this.contractno = contractno;
        }

        public String getDegree() {
            return degree;
        }

        public void setDegree(String degree) {
            this.degree = degree;
        }

        public String getDepartmentId() {
            return departmentId;
        }

        public void setDepartmentId(String departmentId) {
            this.departmentId = departmentId;
        }

        public Object getDepartmentInfo() {
            return departmentInfo;
        }

        public void setDepartmentInfo(Object departmentInfo) {
            this.departmentInfo = departmentInfo;
        }

        public Object getDepartmentName() {
            return departmentName;
        }

        public void setDepartmentName(Object departmentName) {
            this.departmentName = departmentName;
        }

        public String getEducationBackground() {
            return educationBackground;
        }

        public void setEducationBackground(String educationBackground) {
            this.educationBackground = educationBackground;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getEmployeeAdress() {
            return employeeAdress;
        }

        public void setEmployeeAdress(String employeeAdress) {
            this.employeeAdress = employeeAdress;
        }

        public String getEmployeeDuty() {
            return employeeDuty;
        }

        public void setEmployeeDuty(String employeeDuty) {
            this.employeeDuty = employeeDuty;
        }

        public String getEmployeeGrade() {
            return employeeGrade;
        }

        public void setEmployeeGrade(String employeeGrade) {
            this.employeeGrade = employeeGrade;
        }

        public String getEmployeeId() {
            return employeeId;
        }

        public void setEmployeeId(String employeeId) {
            this.employeeId = employeeId;
        }

        public String getEmployeeName() {
            return employeeName;
        }

        public void setEmployeeName(String employeeName) {
            this.employeeName = employeeName;
        }

        public String getEmployeeNation() {
            return employeeNation;
        }

        public void setEmployeeNation(String employeeNation) {
            this.employeeNation = employeeNation;
        }

        public String getEmploymentWay() {
            return employmentWay;
        }

        public void setEmploymentWay(String employmentWay) {
            this.employmentWay = employmentWay;
        }

        public String getExpectedPositiveTime() {
            return expectedPositiveTime;
        }

        public void setExpectedPositiveTime(String expectedPositiveTime) {
            this.expectedPositiveTime = expectedPositiveTime;
        }

        public Object getGrade() {
            return grade;
        }

        public void setGrade(Object grade) {
            this.grade = grade;
        }

        public Object getGradename() {
            return gradename;
        }

        public void setGradename(Object gradename) {
            this.gradename = gradename;
        }

        public String getGradutedSchool() {
            return gradutedSchool;
        }

        public void setGradutedSchool(String gradutedSchool) {
            this.gradutedSchool = gradutedSchool;
        }

        public String getGradutedTime() {
            return gradutedTime;
        }

        public void setGradutedTime(String gradutedTime) {
            this.gradutedTime = gradutedTime;
        }

        public Object getGrassLevelId() {
            return grassLevelId;
        }

        public void setGrassLevelId(Object grassLevelId) {
            this.grassLevelId = grassLevelId;
        }

        public String getHouseholdAddress() {
            return householdAddress;
        }

        public void setHouseholdAddress(String householdAddress) {
            this.householdAddress = householdAddress;
        }

        public String getHukou() {
            return hukou;
        }

        public void setHukou(String hukou) {
            this.hukou = hukou;
        }

        public String getHukouType() {
            return hukouType;
        }

        public void setHukouType(String hukouType) {
            this.hukouType = hukouType;
        }

        public String getImageId() {
            return imageId;
        }

        public void setImageId(String imageId) {
            this.imageId = imageId;
        }

        public int getIntegration() {
            return integration;
        }

        public void setIntegration(int integration) {
            this.integration = integration;
        }

        public Object getIsCheckImsi() {
            return isCheckImsi;
        }

        public void setIsCheckImsi(Object isCheckImsi) {
            this.isCheckImsi = isCheckImsi;
        }

        public Object getIsFixedPlaceWorking() {
            return isFixedPlaceWorking;
        }

        public void setIsFixedPlaceWorking(Object isFixedPlaceWorking) {
            this.isFixedPlaceWorking = isFixedPlaceWorking;
        }

        public Object getIsLeave() {
            return isLeave;
        }

        public void setIsLeave(Object isLeave) {
            this.isLeave = isLeave;
        }

        public String getIsOnDuty() {
            return isOnDuty;
        }

        public void setIsOnDuty(String isOnDuty) {
            this.isOnDuty = isOnDuty;
        }

        public String getLaborContractBeginTime() {
            return laborContractBeginTime;
        }

        public void setLaborContractBeginTime(String laborContractBeginTime) {
            this.laborContractBeginTime = laborContractBeginTime;
        }

        public Object getLeaveReason() {
            return leaveReason;
        }

        public void setLeaveReason(Object leaveReason) {
            this.leaveReason = leaveReason;
        }

        public Object getLeaveTime() {
            return leaveTime;
        }

        public void setLeaveTime(Object leaveTime) {
            this.leaveTime = leaveTime;
        }

        public Object getLevel() {
            return level;
        }

        public void setLevel(Object level) {
            this.level = level;
        }

        public String getMajor() {
            return major;
        }

        public void setMajor(String major) {
            this.major = major;
        }

        public String getMarriedStatus() {
            return marriedStatus;
        }

        public void setMarriedStatus(String marriedStatus) {
            this.marriedStatus = marriedStatus;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getPoliticsStatus() {
            return politicsStatus;
        }

        public void setPoliticsStatus(String politicsStatus) {
            this.politicsStatus = politicsStatus;
        }

        public Object getProjectName() {
            return projectName;
        }

        public void setProjectName(Object projectName) {
            this.projectName = projectName;
        }

        public Object getProjectNameSignStat() {
            return projectNameSignStat;
        }

        public void setProjectNameSignStat(Object projectNameSignStat) {
            this.projectNameSignStat = projectNameSignStat;
        }

        public PropertiesBean getProperties() {
            return properties;
        }

        public void setProperties(PropertiesBean properties) {
            this.properties = properties;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public Object getShortProjectName() {
            return shortProjectName;
        }

        public void setShortProjectName(Object shortProjectName) {
            this.shortProjectName = shortProjectName;
        }

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }

        public int getTaskCount() {
            return taskCount;
        }

        public void setTaskCount(int taskCount) {
            this.taskCount = taskCount;
        }

        public Object getTaskCountFinish() {
            return taskCountFinish;
        }

        public void setTaskCountFinish(Object taskCountFinish) {
            this.taskCountFinish = taskCountFinish;
        }

        public Object getTaskcountPercentage() {
            return taskcountPercentage;
        }

        public void setTaskcountPercentage(Object taskcountPercentage) {
            this.taskcountPercentage = taskcountPercentage;
        }

        public Object getTotalTaskCounts() {
            return totalTaskCounts;
        }

        public void setTotalTaskCounts(Object totalTaskCounts) {
            this.totalTaskCounts = totalTaskCounts;
        }

        public Object getWorkLife() {
            return workLife;
        }

        public void setWorkLife(Object workLife) {
            this.workLife = workLife;
        }

        public static class PropertiesBean implements Serializable {
        }
    }
}
