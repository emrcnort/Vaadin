package com.packagename.myapp.ui;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("login")
@PageTitle("Login | Vaadin")
public class LoginView extends VerticalLayout implements BeforeEnterObserver{

	private LoginForm login = new LoginForm();
	
	public LoginView()
	{
		addClassName("login-view");
		setSizeFull();
		setAlignItems(Alignment.CENTER);
		setJustifyContentMode(JustifyContentMode.CENTER); // Formu tam boyut yapar ve dikey ve yatayda ortalar
		
		login.setAction("login");
		
		add(new H1("Vaadin CRUD"),login);
	}
	
	@Override
	public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
		
		if(beforeEnterEvent.getLocation() 
		        .getQueryParameters()
		        .getParameters()
		        .containsKey("error")) {
		            login.setError(true);
		}
	}

}
