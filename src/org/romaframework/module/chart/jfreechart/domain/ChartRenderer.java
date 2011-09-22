/*
 * Copyright 2008 Luigi Dell'Aquila (luigi.dellaquila@assetdata.it)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.romaframework.module.chart.jfreechart.domain;

import java.io.IOException;
import java.io.OutputStream;

/**
 * this interface represents the ability of an object to be rendered as a chart
 * @author luigi
 *
 */
public interface ChartRenderer {
  /**
   * renders the chart on an output stream
   * @param options the options for chart rendering
   * @param outputStream the output stream where this method has to write the chart
   * @throws IOException
   */
  public void render(RenderOptions options, OutputStream outputStream) throws IOException;
  
  
  /**
   * renders the chart on an output stream
   * @param outputStream the output stream where this method has to write the chart
   * @throws IOException
   */
  public void render(OutputStream outputStream) throws IOException;
  
  /**
   * cleans the data set
   */
  public void clear();
  
  /**
   * returns the supported chart types as strings
   * @return
   */
  public String[] getSupportedChartTypes();
  
  public void setOptions(RenderOptions options);
  public RenderOptions getOptions();
}
