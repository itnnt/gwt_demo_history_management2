package com.mySampleApplication.client;

import com.google.gwt.core.client.EntryPoint;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class MySampleApplication implements EntryPoint {

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        /* create a tab panel to carry multiple pages */
        final TabPanel tabPanel = new TabPanel();

        /* create pages */
        HTML firstPage = new HTML("<h1>We are on first Page.</h1>");
        HTML secondPage = new HTML("<h1>We are on second Page.</h1>");
        HTML thirdPage = new HTML("<h1>We are on third Page.</h1>");

        String firstPageTitle = "First Page";
        String secondPageTitle = "Second Page";
        String thirdPageTitle = "Third Page";
        tabPanel.setWidth("400");

        /* add pages to tabPanel*/
        tabPanel.add(firstPage, firstPageTitle);
        tabPanel.add(secondPage, secondPageTitle);
        tabPanel.add(thirdPage, thirdPageTitle);

        /* add tab selection handler */
        tabPanel.addSelectionHandler(new SelectionHandler<Integer>() {
            @Override
            public void onSelection(SelectionEvent<Integer> event) {
            /* add a token to history containing pageIndex
             History class will change the URL of application
             by appending the token to it.
            */
                History.newItem("pageIndex" + event.getSelectedItem());
            }
        });

      /* add value change handler to History
       this method will be called, when browser's
       Back button or Forward button are clicked
       and URL of application changes.
       */
        History.addValueChangeHandler(new ValueChangeHandler<String>() {
            @Override
            public void onValueChange(ValueChangeEvent<String> event) {
                String historyToken = event.getValue();
                /* parse the history token */
                try {
                    if (historyToken.substring(0, 9).equals("pageIndex")) {
                        String tabIndexToken = historyToken.substring(9, 10);
                        int tabIndex = Integer.parseInt(tabIndexToken);
                        /* select the specified tab panel */
                        tabPanel.selectTab(tabIndex);
                    } else {
                        tabPanel.selectTab(0);
                    }
                } catch (IndexOutOfBoundsException e) {
                    tabPanel.selectTab(0);
                }
            }
        });

        /* select the first tab by default */
        tabPanel.selectTab(0);

        /* add controls to RootPanel */
        RootPanel.get().add(tabPanel);
    }

}


