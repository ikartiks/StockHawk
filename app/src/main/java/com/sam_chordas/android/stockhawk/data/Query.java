
package com.sam_chordas.android.stockhawk.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Query {

    @SerializedName("count")
    @Expose
    private Long count;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("lang")
    @Expose
    private String lang;
    @SerializedName("diagnostics")
    @Expose
    private Diagnostics diagnostics;
    @SerializedName("results")
    @Expose
    private Results results;

    /**
     * 
     * @return
     *     The count
     */
    public Long getCount() {
        return count;
    }

    /**
     * 
     * @param count
     *     The count
     */
    public void setCount(Long count) {
        this.count = count;
    }

    /**
     * 
     * @return
     *     The created
     */
    public String getCreated() {
        return created;
    }

    /**
     * 
     * @param created
     *     The created
     */
    public void setCreated(String created) {
        this.created = created;
    }

    /**
     * 
     * @return
     *     The lang
     */
    public String getLang() {
        return lang;
    }

    /**
     * 
     * @param lang
     *     The lang
     */
    public void setLang(String lang) {
        this.lang = lang;
    }

    /**
     * 
     * @return
     *     The diagnostics
     */
    public Diagnostics getDiagnostics() {
        return diagnostics;
    }

    /**
     * 
     * @param diagnostics
     *     The diagnostics
     */
    public void setDiagnostics(Diagnostics diagnostics) {
        this.diagnostics = diagnostics;
    }

    /**
     * 
     * @return
     *     The results
     */
    public Results getResults() {
        return results;
    }

    /**
     * 
     * @param results
     *     The results
     */
    public void setResults(Results results) {
        this.results = results;
    }

}
