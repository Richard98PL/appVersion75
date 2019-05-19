package com.website.websiteVaadin.Views;

import com.vaadin.navigator.View;
import com.vaadin.server.Page;
import com.vaadin.ui.Alignment;
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
import com.website.websiteVaadin.UI.VaadinUI;

public class Wylogowanie extends Composite implements View {
	
	TextField loginWindowField = new TextField("login");
	PasswordField passwordWindowField = new PasswordField("password");
	Button submitBtn = new Button("Wyloguj sie");
	Panel panel = new Panel("Logout Window");
	VerticalLayout POTRZEBNY = new VerticalLayout();
	HorizontalLayout glownyLayout = new HorizontalLayout();
    FormLayout formLayout = new FormLayout();
	 
    public Wylogowanie() {
    	
        formLayout.setMargin(true);
        formLayout.addComponent(submitBtn);
        formLayout.setComponentAlignment(submitBtn, Alignment.MIDDLE_LEFT);
    	panel.setContent(formLayout);

    	glownyLayout.addComponent(panel);
        POTRZEBNY.addComponents(glownyLayout);
        setCompositionRoot(POTRZEBNY);

        submitBtn.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				WebsiteVaadinApplication.czyZalogowany = false;
				VaadinUI.zalogowanko.setCaption("Jestes aktualnie niezalogowany.");
				
				Notification logoutNotif = new Notification("Logout success.");
				logoutNotif.setDelayMsec(1250);
				logoutNotif.show(Page.getCurrent());
				
				VaadinUI.poWylogowaniu();
				VaadinUI.goToWelcomePage();
				
			}
        });

 }
}