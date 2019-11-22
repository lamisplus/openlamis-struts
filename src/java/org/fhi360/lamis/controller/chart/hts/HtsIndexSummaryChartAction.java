/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fhi360.lamis.controller.chart.hts;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.fhi360.lamis.service.chart.hts.HtsIndexSummaryChartService;

import org.fhi360.lamis.utility.ChartUtil;
import org.fhi360.lamis.utility.DateUtil;

/**
 *
 * @author user10
 */
public class HtsIndexSummaryChartAction extends ActionSupport {

    private static final String DATA_ELEMENT_IDS = "120,121,122";

    private List<String> categories;
    private List<Map<String, Object>> series;
    private String title;
    private String subtitle;
    private String titleForYAxis;

    private HtsIndexSummaryChartService htsIndexChartService;

    List<Object> data = new ArrayList<Object>();
    List<Object> biologicalContainer = new ArrayList<Object>();
    List<Object> socialContainer = new ArrayList<Object>();
    Map<String, Object> sexualContainer = new HashMap<String, Object>();

    public String chartData() {

        ChartUtil chartUtil = new ChartUtil();
        HttpServletRequest request = ServletActionContext.getRequest();

        htsIndexChartService = new HtsIndexSummaryChartService();

        categories = new ArrayList<String>();
        series = new ArrayList<Map<String, Object>>();

        long ipId = Long.parseLong(request.getParameter("ipId"));
        long stateId = Long.parseLong(request.getParameter("stateId"));
        long lgaId = Long.parseLong(request.getParameter("lgaId"));
        long facilityId = Long.parseLong(request.getParameter("facilityId"));

        Date today = new Date();
        Date reportingDateBegin = (!request.getParameter("reportingDateBegin").isEmpty()) ? DateUtil.parseStringToDate(request.getParameter("reportingDateBegin"), "yyyy-MM-dd") : today;
        Date reportingDateEnd = (!request.getParameter("reportingDateEnd").isEmpty()) ? DateUtil.parseStringToDate(request.getParameter("reportingDateEnd"), "yyyy-MM-dd") : today;

        Map values = htsIndexChartService.chartData(ipId, stateId, lgaId, facilityId, DATA_ELEMENT_IDS, reportingDateBegin, reportingDateEnd);

        if (values != null) {
            biologicalContainer.add("BIOLOGICAL");
            biologicalContainer.add((Integer) values.get("BIOLOGICAL"));

            socialContainer.add("SOCIAL");
            socialContainer.add((Integer) values.get("SOCIAL"));

            sexualContainer.put("name", "SEXUAL");
            sexualContainer.put("y", (Integer) values.get("SEXUAL"));
            sexualContainer.put("sliced", true);
            sexualContainer.put("selected", true);

        }

        data.add(biologicalContainer);
        data.add(socialContainer);
        data.add(sexualContainer);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("type", "pie");
        map.put("name", "INDEX TESTING");
        map.put("data", data);
        series.add(map);
        title = "INDEX TESTING";

        return SUCCESS;
    }

    /**
     * @return the categories
     */
    public List<String> getCategories() {
        return categories;
    }

    /**
     * @param categories the categories to set
     */
    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    /**
     * @return the series
     */
    public List<Map<String, Object>> getSeries() {
        return series;
    }

    /**
     * @param series the series to set
     */
    public void setSeries(List<Map<String, Object>> series) {
        this.series = series;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the subtitle
     */
    public String getSubtitle() {
        return subtitle;
    }

    /**
     * @param subtitle the subtitle to set
     */
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    /**
     * @return the titleForYAxis
     */
    public String getTitleForYAxis() {
        return titleForYAxis;
    }

    /**
     * @param titleForYAxis the titleForYAxis to set
     */
    public void setTitleForYAxis(String titleForYAxis) {
        this.titleForYAxis = titleForYAxis;
    }

}
