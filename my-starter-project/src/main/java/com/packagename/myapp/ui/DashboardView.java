package com.packagename.myapp.ui;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.packagename.myapp.Business.CustomerManager;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.DataSeries;
import com.vaadin.flow.component.charts.model.DataSeriesItem;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "dashboard", layout = MainLayout.class) 
@PageTitle("Dashboard | Vaadin CRM") 
public class DashboardView extends VerticalLayout {

    private CustomerManager customerService;


    @Autowired
    public DashboardView(CustomerManager customerService) { 
  
    	this.customerService = customerService;
    	add(getCustomerStats(), getCustomersChart());
        addClassName("dashboard-view");
        setDefaultHorizontalComponentAlignment(Alignment.CENTER); 
    }
    
   
    private Component getCustomerStats() {
        Span stats = new Span(customerService.count() + " customers"); 
        stats.addClassName("customer-stats");
        return stats;
    }
    
    private Chart getCustomersChart() {
        Chart chart = new Chart(ChartType.PIE); 

        DataSeries dataSeries = new DataSeries(); 
        Map<String, Integer> customers = customerService.getStats();
        customers.forEach((customer, count) ->
            dataSeries.add(new DataSeriesItem(customer, count))); 
        chart.getConfiguration().setSeries(dataSeries); 
        return chart;
    }
}