package com.website.websiteVaadin.UI;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.PushStateNavigation;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.Registration;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;
import com.website.websiteVaadin.Views.Database;
import com.website.websiteVaadin.Views.Logowanie;
import com.website.websiteVaadin.Views.Rejestracja;
import com.website.websiteVaadin.Views.Welcome;
import com.website.websiteVaadin.Views.Wylogowanie;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;

@SpringUI
@PushStateNavigation
public class VaadinUI extends UI {
	
	public static Button view1;
	public static HorizontalLayout viewContainer = new HorizontalLayout();
    public static Registration registrationDoLogowania;
    public static CssLayout mainLayout;
    public static Button zalogowanko = new Button("Jestes aktualnie niezalogowany.");

    @Override
    protected void init(VaadinRequest request) {

    	Label title = new Label("Menu");
        title.addStyleName(ValoTheme.MENU_TITLE);

        view1 = new Button("Logowanie");
        registrationDoLogowania = view1.addClickListener(e->{
      	  UI.getCurrent().getNavigator().navigateTo("logowanie");
        });
       
        view1.addStyleNames(ValoTheme.BUTTON_LINK, ValoTheme.MENU_ITEM);
        Button view2 = new Button("Rejestracja", e -> getNavigator().navigateTo("rejestracja"));
        view2.addStyleNames(ValoTheme.BUTTON_LINK, ValoTheme.MENU_ITEM);
        Button view3 = new Button("Database", e -> getNavigator().navigateTo("database"));
        view3.addStyleNames(ValoTheme.BUTTON_LINK, ValoTheme.MENU_ITEM);
        //return "background-image: linear-gradient(to right, rgba(150, 7, 52,0), rgba(150, 7, 52,1));";
        CssLayout menu = new CssLayout(title, view1, view2,view3) {
        	 @Override
     	    protected String getCss(Component c) {
     	        if (c instanceof Button) {
     	            return  "text-align : left;"; 		            		
     	        }
     	        return null;
     	    }
     	};
     	
     	
     	zalogowanko.addStyleName(ValoTheme.MENU_TITLE);
     	zalogowanko.setEnabled(false);
     	zalogowanko.setWidth(75f, Unit.PERCENTAGE);
     	
        menu.addStyleName(ValoTheme.MENU_ROOT);

         mainLayout = new CssLayout(menu, zalogowanko, viewContainer) {
        	 @Override
        	    protected String getCss(Component c) {
        	        if (c instanceof HorizontalLayout) {
   
        	            return  "position: fixed;" + 
        	            		"  top: 48%;" + 
        	            		"  left: 55%;" + 
        	            		"  transform: translate(-50%, -50%);";
        	            		
        	            		
        	        }
        	        if(c instanceof Button) {
        	        	return "position:absolute;"+
        	        			"top:3%;"+
        	        			"left:55%;"+
        	        			"  transform: translate(-50%, -50%);"+
        	        			"background-image: linear-gradient(to right, red , blue);";
        	        					
        	        }
        	        if(c instanceof CssLayout) {
        	        	
        	        	
        	        }
        	     
        	        return null;
        	    }
        	};
        	System.out.println("\"");
         
         mainLayout.setSizeFull();
         setContent(mainLayout);

         Navigator navigator = new Navigator(this, viewContainer);
         navigator.addView("", Welcome.class);
         navigator.addView("logowanie", Logowanie.class);
         navigator.addView("rejestracja", Rejestracja.class);
         navigator.addView("wylogowanie",  Wylogowanie.class);
         navigator.addView("database", Database.class);
         
         
    }
    
    public static void poZalogowaniu() {
    	view1.setCaption("Wyloguj sie");
    	registrationDoLogowania.remove();
    	registrationDoLogowania = view1.addClickListener(e->{
    	      UI.getCurrent().getNavigator().navigateTo("wylogowanie");
  	  });
    	
    }
    
    public static void poWylogowaniu() {
    	view1.setCaption("Zaloguj sie");
    	registrationDoLogowania.remove();
    	registrationDoLogowania = view1.addClickListener(e->{
        	  UI.getCurrent().getNavigator().navigateTo("logowanie");
          });
    }
    public static void goToWelcomePage() {
    	UI.getCurrent().getNavigator().navigateTo("");
    }

	

  }


        
    	 
         
    	