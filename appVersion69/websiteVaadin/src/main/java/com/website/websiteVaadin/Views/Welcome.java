package com.website.websiteVaadin.Views;

import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.Composite;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupView;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class Welcome extends Composite implements View {

	Panel panel = new Panel("Hello World");
	VerticalLayout POTRZEBNY = new VerticalLayout();
	HorizontalLayout helloLayout = new HorizontalLayout();
	HorizontalLayout popupContent = new HorizontalLayout();
	PopupView popup = new PopupView(null, popupContent);
	Button submitBtn = new Button("Welcome", 
        	click -> popup.setPopupVisible(true));
	Button helloWorld = new Button("Have a nice day!");
	
    public Welcome() {
        
    	popupContent.setSizeFull();
  	  	helloWorld.addStyleName(ValoTheme.MENU_TITLE);
  	  	helloWorld.setEnabled(false);
        popupContent.addComponent(helloWorld);
      
        helloLayout.setMargin(true);     
        helloLayout.addComponent(submitBtn);
        helloLayout.addComponent(popup);
    	panel.setContent(helloLayout);

        POTRZEBNY.addComponents(panel);
        setCompositionRoot(POTRZEBNY);
        
 }
}