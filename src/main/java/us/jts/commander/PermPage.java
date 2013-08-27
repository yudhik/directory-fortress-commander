/*
 * Copyright (c) 2013, JoshuaTree Software. All rights reserved.
 */

package us.jts.commander;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.ajax.markup.html.AjaxLazyLoadPanel;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import us.jts.commander.panel.Displayable;
import us.jts.commander.panel.InfoPanel;
import us.jts.commander.panel.NavPanel;
import us.jts.commander.panel.PermDetailPanel;
import us.jts.commander.panel.PermListPanel;

/**
 * @author Shawn McKinney
 * @version $Rev$
 */
public class PermPage extends CommanderBasePage
{
    private boolean isAdmin = false;
    private String label = "RBAC Permission Operation Page";

    public PermPage(PageParameters parameters)
    {
        add(new Label( GlobalIds.PAGE_HEADER, label));
        WebMarkupContainer container = new WebMarkupContainer(GlobalIds.LAYOUT);
        FourWaySplitter splitter = new FourWaySplitter();
        splitter.addBorderLayout(container);

        // Add the four necessary panels for Commander Page: 1. Nav,, 2. List, 3. Info, 4. Detail
        // Nav and Info are generic and work across all entities, the others are specific to this entity type.

        // 1. Nav Panel:
        NavPanel navPanel = new NavPanel(GlobalIds.NAVPANEL);

        // 2. List Panel:
        container.add(new AjaxLazyLoadPanel(GlobalIds.PERMLISTPANEL)
         {
           @Override
           public Component getLazyLoadComponent(String id)
           {
                return new PermListPanel(id, isAdmin);
           }
         });

        // 3. Info Panel:
        InfoPanel infoPanel = new InfoPanel(GlobalIds.INFOPANEL);
        container.add(infoPanel);

        // 4. Detail Panel:
        Displayable display = infoPanel.getDisplay();
        PermDetailPanel permDetail = new PermDetailPanel( GlobalIds.PERMDETAILPANEL, display, isAdmin );
        container.add(permDetail);

        container.add(navPanel);
        this.add(container);
    }
}