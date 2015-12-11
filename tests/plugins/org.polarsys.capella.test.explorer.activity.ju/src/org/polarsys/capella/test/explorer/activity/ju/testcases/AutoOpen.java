/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.explorer.activity.ju.testcases;

import java.util.Collection;

import org.eclipse.amalgam.explorer.activity.ui.api.editor.ActivityExplorerEditor;
import org.eclipse.amalgam.explorer.activity.ui.api.editor.Messages;
import org.eclipse.amalgam.explorer.activity.ui.api.editor.pages.CommonActivityExplorerPage;
import org.eclipse.core.resources.IFile;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.core.sirius.ui.helper.SessionHelper;
import org.polarsys.capella.test.framework.helpers.GuiActions;

/**
 * Test - Regarding corresponding preference:<br>
 * <ol>
 * <li>ActivityExplorer is opened when a Session opens,</li>
 * <li>ActivityExplorer is closed when a Session is closed.</li>
 * </ol> 
 */
public class AutoOpen extends AbstractActivityExplorerTestCase {

  @Override
  public void test() throws Exception {
    // Hypothesis: project already imported in workspace (by setUp()), but session not open.
    
    // Open session using Capella's OpenSessionAction.
    IFile airdFile = getAirdFileForLoadedModel(TEST_PROJECT_NAME);
    GuiActions.openSession(airdFile, true);
    // Check ActivityExplorer Editor is open.
    IEditorPart activeEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
    assertTrue(activeEditor instanceof ActivityExplorerEditor);
    
    // Check ActivityExplorer content is valid.
    ActivityExplorerEditor activityExplorerEditor = (ActivityExplorerEditor) activeEditor;
    Collection<CommonActivityExplorerPage> activityExpolrerPages = activityExplorerEditor.getPages();
    assertTrue(activityExplorerEditor.getPartName().contains(Messages.ActivityExplorerEditor_Title_Suffix));
    assertTrue(activityExplorerEditor.getPartName().contains(TEST_PROJECT_NAME));
    assertEquals(NB_ACTIVITY_EXPLORER_TABS, activityExpolrerPages.size());

    // Close session.
    Session session = SessionHelper.getSession(airdFile);
    GuiActions.closeSession(session);
    
    // No editor (i.e. ActivityExplorer is closed).
    assertNull(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor());
  }
}
