
package com.sam_chordas.android.stockhawk.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Query_ {

    @SerializedName("execution-start-time")
    @Expose
    private String executionStartTime;
    @SerializedName("execution-stop-time")
    @Expose
    private String executionStopTime;
    @SerializedName("execution-time")
    @Expose
    private String executionTime;
    @SerializedName("params")
    @Expose
    private String params;
    @SerializedName("content")
    @Expose
    private String content;

    /**
     * 
     * @return
     *     The executionStartTime
     */
    public String getExecutionStartTime() {
        return executionStartTime;
    }

    /**
     * 
     * @param executionStartTime
     *     The execution-start-time
     */
    public void setExecutionStartTime(String executionStartTime) {
        this.executionStartTime = executionStartTime;
    }

    /**
     * 
     * @return
     *     The executionStopTime
     */
    public String getExecutionStopTime() {
        return executionStopTime;
    }

    /**
     * 
     * @param executionStopTime
     *     The execution-stop-time
     */
    public void setExecutionStopTime(String executionStopTime) {
        this.executionStopTime = executionStopTime;
    }

    /**
     * 
     * @return
     *     The executionTime
     */
    public String getExecutionTime() {
        return executionTime;
    }

    /**
     * 
     * @param executionTime
     *     The execution-time
     */
    public void setExecutionTime(String executionTime) {
        this.executionTime = executionTime;
    }

    /**
     * 
     * @return
     *     The params
     */
    public String getParams() {
        return params;
    }

    /**
     * 
     * @param params
     *     The params
     */
    public void setParams(String params) {
        this.params = params;
    }

    /**
     * 
     * @return
     *     The content
     */
    public String getContent() {
        return content;
    }

    /**
     * 
     * @param content
     *     The content
     */
    public void setContent(String content) {
        this.content = content;
    }

}
