package com.excelsoft.gquery.client;

import static com.google.gwt.query.client.GQuery.$;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.query.client.Function;
import com.google.gwt.query.client.GQuery;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.event.dom.client.LoadHandler;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class TestGQuery implements EntryPoint {
	private TextBox url = new TextBox();
	private Button submit = new Button("Submit");
	private Button bind = new Button("Bind");
	
	private Frame frame = null;
	
	public void onModuleLoad() {
		HorizontalPanel header = new HorizontalPanel();
		header.add( new Label("URL") );
		header.add( url );
		header.add( submit );
		header.add(bind);
		
		
		frame = new Frame(){
			{
		        addDomHandler(new LoadHandler() {
					
					@Override
					public void onLoad(LoadEvent event) {
//						 Window.alert("ONLOAD");
					}
				}, LoadEvent.getType() );
		    }

		};
		
		frame.getElement().setId("container-frame");
		frame.setSize("1024px", "768px");
		
		VerticalPanel v = new VerticalPanel();
		v.setSize("640", "480");
		v.add( header );
		v.add(frame);
		
		
		RootLayoutPanel.get().add(v);
		

		
		
		// Add a handler to close the DialogBox
		submit.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				frame.setUrl( url.getValue() );
			}
		});
		
		bind.addClickHandler( new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				String html = TestGQuery.getInnerHTML( frame.getElement() );
				
				GQuery q = $(TestGQuery.getDocumentElement(frame.getElement())).find("*");
				int size = q.size();
				q.hover( new Function(){
					@Override
					public boolean f(Event e) {
						$(e).addClass("selected")
						.css( "border-color", "red" )
						.css( "border-style", "solid" );
						return false;
					}
//					@Override
//					public boolean f(Element e) {
//						$(e).addClass("selected")
//						.css( "border-color", "red" )
//						.css( "border-style", "solid" );
//						return false;
//					}
				}, new Function(){
					@Override
					public boolean f(Event e) {
						$(e).removeClass("selected")
						.css( "border-color", "none" )
						.css( "border-style", "none" );
						return false;
					}
//					@Override
//					public boolean f(Element e) {
//						$(e).removeClass("selected")
//						.css( "border-color", "none" )
//						.css( "border-style", "none" );
//						return false;
//					}
				});
			}
		});
	}
	
	public static native String getInnerHTML(Element iframe)/*-{
		try {
          // Make sure the iframe's window & document are loaded.
          if (!iframe.contentWindow || !iframe.contentWindow.document)
            return null;
 
          // Get the response from window.name
          return iframe.contentWindow.document.documentElement.innerHTML;
         } catch (e) {
          return null;
         }
	}-*/;
	
	public static native Element getDocumentElement(Element iframe)/*-{
	try {
      // Make sure the iframe's window & document are loaded.
      if (!iframe.contentWindow || !iframe.contentWindow.document)
        return null;

      // Get the response from window.name
      return iframe.contentWindow.document.documentElement;
     } catch (e) {
      return null;
     }
}-*/;
}
