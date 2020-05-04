package com.ankita.complaintdemo.repository;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CreateResponse {
    @SerializedName("error")
    @Expose
    public String error;
    @SerializedName("status")
    @Expose
    public Boolean status;

    @SerializedName("data")
    @Expose
    public UserData data;

    public class UserData {
        @SerializedName("ComplaintListModel")
        public ComplainList obj;

        public class ComplainList {
            @SerializedName("complaintList")
            public ArrayList<User> comp_list;

            @SerializedName("pageDetails")
            public PageDetail objDetail;

            public  class PageDetail{
                @SerializedName("totalPages")
                public int totalPages;
                @SerializedName("totalRecords")
                public int totalRecords;
                @SerializedName("pagerSize")
                public int pagerSize;
                @SerializedName("pageSize")
                public int pageSize;
                @SerializedName("pageNo")
                public int pageNo;
                @SerializedName("currentPagerNo")
                public int currentPagerNo;
                @SerializedName("currentPagerRecords")
                public int currentPagerRecords;
                @SerializedName("hasPreviousPage")
                public boolean hasPreviousPage;
                @SerializedName("hasNextPage")
                public boolean hasNextPage;

            }

        }

    }
//    @SerializedName("has_more")
//    public boolean hasMore;
    public class User {
        @SerializedName("code")
        @Expose
        public String code;
        @SerializedName("createdDate")
        @Expose
        public String createdDate;
        @SerializedName("detail")
        @Expose
        public String detail ;
    @SerializedName("callStatus")
    @Expose
    public int callStatus ;




    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getCallStatus() {
        return callStatus;
    }

    public void setCallStatus(int callStatus) {
        this.callStatus = callStatus;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

}
