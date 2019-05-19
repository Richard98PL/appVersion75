package com.website.websiteVaadin.Views;

import java.util.List;
import com.vaadin.navigator.View;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.Composite;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.ValoTheme;
import com.website.websiteVaadin.DataUser.User;
import com.website.websiteVaadin.DataUser.UserService;

public class Database extends Composite implements View {

	UserService service = new UserService();
	User user;
	Grid<User> gridZUzytkownikami = new Grid(User.class);
	TextField login = new TextField("login");
	PasswordField password = new PasswordField("password");
	TextField email = new TextField("email");
    Button cleanTableBtn = new Button("CLEAN TABLE");
    public Button save = new Button("Save", e -> saveUser());
    Panel panel = new Panel("Database");
    PasswordField hasloDoUsuwania = new PasswordField("Haslo do czyszczenia");
    VerticalLayout POTRZEBNY = new VerticalLayout();
	HorizontalLayout glownyLayout = new HorizontalLayout();
    FormLayout mainLayout = new FormLayout();
    Button coWybranoFieldLogin = new Button("login");
    Button coWybranoFieldPassword = new Button("password");
    Button coWybranoFieldEmail = new Button("email");
    
    public Database() {
    		
    	updateGrid();
        gridZUzytkownikami.setColumns("login", "password","email");
        gridZUzytkownikami.addSelectionListener(e -> updateForm());
        gridZUzytkownikami.addItemClickListener(event -> Notification.show("essa"));
        gridZUzytkownikami.setHeightByRows(4);
        
        mainLayout.setMargin(true);
        mainLayout.addComponent(gridZUzytkownikami);
        mainLayout.addComponent(hasloDoUsuwania);
        mainLayout.addComponent(cleanTableBtn);
        mainLayout.addComponents(coWybranoFieldLogin,coWybranoFieldPassword,coWybranoFieldEmail);
        panel.setContent(mainLayout);
        
        coWybranoFieldLogin.setSizeFull();
        coWybranoFieldLogin.setEnabled(false);
        coWybranoFieldLogin.setStyleName(ValoTheme.BUTTON_FRIENDLY);
        coWybranoFieldPassword.setSizeFull();
        coWybranoFieldPassword.setEnabled(false);
        coWybranoFieldPassword.setStyleName(ValoTheme.BUTTON_FRIENDLY);
        coWybranoFieldEmail.setSizeFull();
        coWybranoFieldEmail.setEnabled(false);
        coWybranoFieldEmail.setStyleName(ValoTheme.BUTTON_FRIENDLY);
     
        
        gridZUzytkownikami.setSelectionMode(SelectionMode.SINGLE);
        gridZUzytkownikami.addItemClickListener(event->{
        	String coWybranoLogin = event.getItem().getLogin().toString();
        	String coWybranoPassword = event.getItem().getPassword().toString();
        	String coWybranoEmail = event.getItem().getEmail().toString();
        	
        	Notification coWybranoNotif = new Notification("Wybrano");
        	coWybranoNotif.setDelayMsec(1250);
        	coWybranoNotif.show(Page.getCurrent());
        	coWybranoFieldLogin.setCaption(coWybranoLogin);
        	coWybranoFieldPassword.setCaption(coWybranoPassword);
        	coWybranoFieldEmail.setCaption(coWybranoEmail);
        });
        
        
    
        glownyLayout.addComponent(panel);
        POTRZEBNY.addComponents(glownyLayout);
        setCompositionRoot(POTRZEBNY);
      
        cleanTableBtn.addClickListener(new Button.ClickListener() {
          	 
        	@Override
   			public void buttonClick(ClickEvent event) {
   				
        		if(hasloDoUsuwania.getValue().equals("justynka"))
        		{
   				service.clearTable();
   				Notification cleanTable = new Notification("Table is clean.");
   				cleanTable.setDelayMsec(1250);
   				cleanTable.show(Page.getCurrent());
   				updateGrid();
        		}
        		else {
        			Notification wrongCleanPassword = new Notification("Delete password wrong.");
        			wrongCleanPassword.setDelayMsec(1250);
        			wrongCleanPassword.show(Page.getCurrent());
        		}
        			
   				
   			}
   		});
         
      }
   
    public void updateGrid() {
          List<User> users = service.findAll();
          gridZUzytkownikami.setItems(users);
          setFormVisible(false);
      }
      
      public void updateForm() {
          if (gridZUzytkownikami.asSingleSelect().isEmpty()) {
              setFormVisible(false);
          } else {
              user = gridZUzytkownikami.asSingleSelect().getValue();  
              setFormVisible(true);
          }
      }
      
      public void setFormVisible(boolean visible) {
          login.setVisible(visible);
          password.setVisible(visible);
          email.setVisible(visible);
          save.setVisible(visible);
      }
      
      public void saveUser() {
          service.update(user);
          updateGrid();
      }
      
}



    

   	
    
   

      
      