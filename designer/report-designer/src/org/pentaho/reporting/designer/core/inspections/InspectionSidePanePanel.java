/*
 * This program is free software; you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License, version 2.1 as published by the Free Software
 * Foundation.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this
 * program; if not, you can obtain a copy at http://www.gnu.org/licenses/old-licenses/lgpl-2.1.html
 * or from the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * Copyright (c) 2009 Pentaho Corporation.  All rights reserved.
 */

package org.pentaho.reporting.designer.core.inspections;

import java.awt.BorderLayout;

import org.pentaho.reporting.designer.core.editor.ReportRenderContext;
import org.pentaho.reporting.designer.core.util.SidePanel;

/**
 * This panel contains all inspections for the current report. Once fully implemented, there are at least two tables;
 * the auto-inspections table and the table for manually run inspections.
 *
 * @author Thomas Morgner
 */
public class InspectionSidePanePanel extends SidePanel
{
  private InspectionsMessagePanel autoInspectionPanel;

  public InspectionSidePanePanel()
  {
    autoInspectionPanel = new InspectionsMessagePanel();
    setLayout(new BorderLayout());
    add(autoInspectionPanel, BorderLayout.CENTER);
  }

  public void setEnabled(final boolean enabled)
  {
    super.setEnabled(enabled);
    autoInspectionPanel.setEnabled(enabled);
  }

  protected void updateActiveContext(final ReportRenderContext oldContext, final ReportRenderContext newContext)
  {
    if (oldContext != null)
    {
      oldContext.getInspectionRunner().removeInspectionListener(autoInspectionPanel.getResultHandler());
    }
    if (newContext != null)
    {
      newContext.getInspectionRunner().addInspectionListener(autoInspectionPanel.getResultHandler());
      setEnabled(true);
    }
    else
    {
      setEnabled(false);
    }

    autoInspectionPanel.setReportRenderContext(newContext);
  }
}