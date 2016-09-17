package com.sainsbury.ripefruit.pojo;

import java.util.List;

/**
 * Created by chaitanyanaidu on 9/17/16.
 */
public class JSONResponse {
    private List<RipeFruit> results;
    private String total;

    public List<RipeFruit> getResults() {
        return results;
    }

    public void setResults(List<RipeFruit> results) {
        this.results = results;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
