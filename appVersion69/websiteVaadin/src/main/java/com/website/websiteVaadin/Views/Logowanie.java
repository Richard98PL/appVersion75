package com.website.websiteVaadin.Views;

import com.vaadin.navigator.View;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.Composite;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.website.websiteVaadin.WebsiteVaadinApplication;
import com.website.websiteVaadin.DataUser.UserService;
import com.website.websiteVaadin.UI.VaadinUI;

public class Logowanie extends Composite implements View {
	
	UserService service = new UserService();
	TextField loginWindowField = new TextField("login");
	PasswordField passwordWindowField = new PasswordField("password");
	Button submitBtn = new Button("SUBMIT");
	Panel panel = new Panel("Login Window");
	VerticalLayout POTRZEBNY = new VerticalLayout();
    FormLayout formLayout = new FormLayout();
	HorizontalLayout glownyLayout = new HorizontalLayout();
	
    public Logowanie() {

        formLayout.setMargin(true);
        formLayout.addComponent(loginWindowField);
        formLayout.addComponent(passwordWindowField);
        formLayout.addComponent(submitBtn);
    	panel.setContent(formLayout);
    	
    	
    	
        glownyLayout.addComponent(panel);
        POTRZEBNY.addComponents(glownyLayout);
        setCompositionRoot(POTRZEBNY);

        submitBtn.addClickListener(new Button.ClickListener() {
    			
    			@Override
    			public void buttonClick(ClickEvent event) {
    				String loginToCheck = loginWindowField.getValue().toString();
    				String passwordToCheck = passwordWindowField.getValue().toString();
    				
    				Boolean czyZalogowac = true;
    				if(loginToCheck.isEmpty()) {
    					Notification emptyLogin = new Notification("Login can't be empty!");
    					emptyLogin.setDelayMsec(1250);
    					emptyLogin.show(Page.getCurrent());
    				}
    				else if(service.checkLogin(loginToCheck)==0) {
    					Notification noUser = new Notification("This user doesn't exist.");
    					noUser.setDelayMsec(1250);
    					noUser.show(Page.getCurrent());
    					czyZalogowac = false;
    				}
    				else if(passwordToCheck.isEmpty()) {
    					Notification emptyPassword = new Notification("Password can't be empty!");
    					emptyPassword.setDelayMsec(1250);
    					emptyPassword.show(Page.getCurrent());
    				}
    				else if(service.checkPasswordCorrectness(loginToCheck,passwordToCheck)){
    					Notification successLogin = new Notification("Success!");
    					successLogin.setDelayMsec(1250);
    					successLogin.show(Page.getCurrent());
    					WebsiteVaadinApplication.loginZalogowanego = loginToCheck;
    					WebsiteVaadinApplication.czyZalogowany = true;
    					VaadinUI.zalogowanko.setCaption("Zalogowany jako "+loginToCheck);
    					VaadinUI.poZalogowaniu();
    					VaadinUI.goToWelcomePage();
    					
    				}
    				else{
    					Notification wrongPassword = new Notification("Wrong password!");
    					wrongPassword.setDelayMsec(1250);
    					wrongPassword.show(Page.getCurrent());
    					czyZalogowac = false;
    				}	
    			}
    		});

    }
}