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
import com.website.websiteVaadin.DataUser.User;
import com.website.websiteVaadin.DataUser.UserService;

public class Rejestracja extends Composite implements View {

	UserService service = new UserService();
	User user;
    Button cleanTableBtn = new Button("CLEAN TABLE");
    TextField loginRegister = new TextField("login");
    PasswordField passwordRegister = new PasswordField("password");
    TextField emailRegister = new TextField("email");
    Button registerBtn = new Button("REGISTER");
    Panel panel = new Panel("Register window");
    VerticalLayout POTRZEBNY = new VerticalLayout();
	HorizontalLayout glownyLayout = new HorizontalLayout();
    FormLayout mainLayout = new FormLayout();
   
    
    public Rejestracja() {
    	
        mainLayout.setMargin(true);
        mainLayout.addComponent(loginRegister);
        mainLayout.addComponent(passwordRegister);
        mainLayout.addComponent(emailRegister);
        mainLayout.addComponent(registerBtn);    
        panel.setContent(mainLayout);
        
        

        glownyLayout.addComponent(panel);
        POTRZEBNY.addComponents(glownyLayout);
        setCompositionRoot(POTRZEBNY);
      
        registerBtn.addClickListener(new Button.ClickListener() {
   			
   			@Override
   			public void buttonClick(ClickEvent event) {
   				
   				String loginToRegister = loginRegister.getValue();
   				String passwordToRegister = passwordRegister.getValue();
   				String emailToRegister = emailRegister.getValue();
   				
   				Boolean czyMoznaTworzyc = true;
   				
   				if(service.checkLogin(loginToRegister)!=0) {
   					Notification loginAlreadyUsed = new Notification("Login already used.");
   					loginAlreadyUsed.setDelayMsec(1250);
   					loginAlreadyUsed.show(Page.getCurrent());
   					czyMoznaTworzyc = false;
   				}
   				else if(service.checkEmail(emailToRegister)!=0) {
   					Notification emailAlreadyUsed = new Notification("Email already used.");
   					emailAlreadyUsed.setDelayMsec(1250);
   					emailAlreadyUsed.show(Page.getCurrent());	
   					czyMoznaTworzyc = false;
   				}
   				else if(loginRegister.isEmpty()) {
   					Notification emptyLogin = new Notification("Login can't be empty!");
   					emptyLogin.setDelayMsec(1250);
   					emptyLogin.show(Page.getCurrent());
   				}
   				else if(passwordToRegister.isEmpty()) {
   					Notification emptyPassword = new Notification("Password can't be empty!");
   					emptyPassword.setDelayMsec(1250);
   					emptyPassword.show(Page.getCurrent());
   				}
   				else if(emailToRegister.isEmpty()) {
   					Notification emptyEmail = new Notification("Email can't be empty!");
   					emptyEmail.setDelayMsec(1250);
   					emptyEmail.show(Page.getCurrent());
   				}
   				else if(czyMoznaTworzyc) {
   					service.insert(loginToRegister,passwordToRegister,emailToRegister);
   					Notification successRegistration = new Notification("Succes registration.");
   					successRegistration.setDelayMsec(1250);
   					successRegistration.show(Page.getCurrent());
   				}
   			}
   		});
         
      }


      
}



    

   	
    
   

      
      