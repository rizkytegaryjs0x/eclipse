/* **************************************************************************************
 * Copyright (c) 2021 Calypso Networks Association https://calypsonet.org/
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

import org.eclipse.keyple.core.util.Assert;
import org.eclipse.keyple.distributed.spi.AsyncEndpointServerSpi;

/**
 * Builder of {@link RemotePluginServerFactory} for Keyple <b>ObservablePlugin</b> type.
 *
 * @since 2.0.0
 */
public final class RemotePluginServerFactoryBuilder {

  /**
   * (private)<br>
   * Constructor
   */
  private RemotePluginServerFactoryBuilder() {}

  /**
   * Gets the first step of the builder to use in order to create a new factory instance.
   *
   * @param remotePluginName The identifier of the remote plugin.
   * @return Next configuration step.
   * @throws IllegalArgumentException If the plugin name is null or empty.
   * @since 2.0.0
   */
  public static NodeStep builder(String remotePluginName) {
    return new Builder(remotePluginName);
  }

  /**
   * Step to configure the node associated with the service.
   *
   * @since 2.0.0
   */
  public interface NodeStep {

    /**
     * Configures the service with a {@link SyncNodeServer} node.
     *
     * @return Next configuration step.
     * @since 2.0.0
     */
    BuilderStep withSyncNode();

    /**
     * Configures the service with a {@link AsyncNodeServer} node.
     *
     * @param endpoint The {@link AsyncEndpointServerSpi} network endpoint to use.
     * @return Next configuration step.
     * @throws IllegalArgumentException If the endpoint is null.
     * @since 2.0.0
     */
    BuilderStep withAsyncNode(AsyncEndpointServerSpi endpoint);
  }

  /**
   * Last step : builds a new instance.
   *
   * @since 2.0.0
   */
  public interface BuilderStep {

    /**
     * Creates a new instance of {@link RemotePluginServerFactory} using the current configuration.
     *
     * @return A not null reference.
     * @since 2.0.0
     */
    RemotePluginServerFactory build();
  }

  /**
   * (private)<br>
   * The internal step builder.
   */
  private static final class Builder implements NodeStep, BuilderStep {

    private final String remotePluginName;
    private AsyncEndpointServerSpi asyncEndpoint;

    private Builder(String remotePluginName) {
      Assert.getInstance().notEmpty(remotePluginName, "remotePluginName");
      this.remotePluginName = remotePluginName;
    }

    /**
     * {@inheritDoc}
     *
     * @since 2.0.0
     */
    @Override
    public BuilderStep withSyncNode() {
      return this;
    }

    /**
     * {@inheritDoc}
     *
     * @since 2.0.0
     */
    @Override
    public BuilderStep withAsyncNode(AsyncEndpointServerSpi endpoint) {
      Assert.getInstance().notNull(endpoint, "endpoint");
      this.asyncEndpoint = endpoint;
      return this;
    }

    /**
     * {@inheritDoc}
     *
     * @since 2.0.0
     */
    @Override
    public RemotePluginServerFactory build() {
      return new RemotePluginServerFactoryAdapter(remotePluginName, asyncEndpoint);
    }
  }
}
