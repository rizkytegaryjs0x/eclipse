/* **************************************************************************************
 * Copyright (c) 2021 Calypso Networks Association https://www.calypsonet-asso.org/
 *
 * See the NOTICE file(s) distributed with this work for additional information
 * regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the terms of the
 * Eclipse Public License 2.0 which is available at http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 ************************************************************************************** */
package org.eclipse.keyple.distributed;

import org.eclipse.keyple.core.common.KeypleReaderExtension;
import org.eclipse.keyple.core.common.KeypleSmartCard;

/**
 * API of the <b>Remote Reader Server</b> provided by the <b>Remote Plugin Server</b>.
 *
 * <p>This reader is an extension of a Keyple observable reader but adds some specific features.
 *
 * @since 2.0
 */
public interface RemoteReaderServer extends KeypleReaderExtension {

  /**
   * Gets the ID of the remote service to execute server side.
   *
   * @return A not empty string.
   * @since 2.0
   */
  String getServiceId();

  /**
   * Gets the user input data if it is set.
   *
   * @param classOfUserInputData The expected user input data type.
   * @param <T> The type of the expected user input data.
   * @return Null if there is no user input data.
   * @throws IllegalArgumentException If the provided class is null.
   * @since 2.0
   */
  <T> T getUserInputData(Class<T> classOfUserInputData);

  /**
   * Gets the initial content of the smart card if it is set.
   *
   * <p>The returned {@link KeypleSmartCard} can be cast into the expected type.
   *
   * @return Null if there is no initial card content.
   * @since 2.0
   */
  KeypleSmartCard getInitialCardContent();
}
