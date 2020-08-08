package com.packagename.myapp.ui;

import com.packagename.myapp.Business.ICustomerService;
import com.packagename.myapp.entities.Customer;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.grid.GridMultiSelectionModel;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


@Route(value="",layout= MainLayout.class)
@PageTitle("Customers | Vaadin CRM")

public class ListView extends VerticalLayout {

 	private Grid<Customer> grid = new Grid<>(Customer.class);
 	private TextField filterText = new TextField();
 	private ICustomerService service;
 	private CustomerForm customerForm;
 	
 	@Autowired
    public ListView( ICustomerService service) {
          
 		 this.service= service;
    	 addClassName("list-view");
         setSizeFull();
         configureGrid();
         
         customerForm = new CustomerForm(service.getAll());
         customerForm.addListener(CustomerForm.SaveEvent.class, this::saveCustomer);
         customerForm.addListener(CustomerForm.DeleteEvent.class, this::deleteCustomer);
         customerForm.addListener(CustomerForm.CloseEvent.class, e-> closeEditor());


         
         
         Div content = new Div(grid, customerForm); 
         content.addClassName("content");
         content.setSizeFull();
         
         add(getToolbar(),content);
         updateList();
         closeEditor();
         
    
    }
 	
    private void saveCustomer(CustomerForm.SaveEvent evt)
    {
    	service.add(evt.getCustomer());
    	updateList();
    	closeEditor();
    }
    
    private void deleteCustomer(CustomerForm.DeleteEvent evt)
    {
    	service.delete(evt.getCustomer());
    	updateList();
    	closeEditor();
    }

    private HorizontalLayout getToolbar() { 
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addCustomerButton = new Button("Add Customer");
        addCustomerButton.addClickListener(click -> addCustomer()); 

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addCustomerButton); 
        toolbar.addClassName("toolbar");
        return toolbar;
      }

	private void addCustomer() {
		    grid.asSingleSelect().clear(); 
		    editCustomer(new Customer());
	}

	private void configureGrid() {
		
	 grid.addClassName("customer-grid");
	 grid.setSizeFull();
	 grid.setColumns("firstName", "lastName");
	 
	 grid.getColumns().forEach(col -> col.setAutoWidth(true)) ;
	 
	 grid.asSingleSelect().addValueChangeListener(event -> 
     editCustomer(event.getValue()));
		
	}
	
	public void editCustomer(Customer customer) { 
	    if (customer == null) {
	        closeEditor();
	    } else {
	        customerForm.setCustomer(customer);
	        customerForm.setVisible(true);
	        addClassName("editing");
	    }
	}
	
	private void closeEditor() {
 		customerForm.setCustomer(null);
 		customerForm.setVisible(false);
 	    removeClassName("editing");
 		
	}

	private void updateList() {
	    
		grid.setItems(service.findAll(filterText.getValue()));
		
	}
}
