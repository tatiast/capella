/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.migration.ju.model;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.test.framework.api.BasicTestCase;

public abstract class MigrationDiagram extends BasicTestCase {

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(MigrationDiagram.class.getSimpleName());
  }
}