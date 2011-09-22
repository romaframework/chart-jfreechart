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
package org.romaframework.module.chart.jfreechart.helper;

import org.romaframework.module.chart.jfreechart.domain.RenderOptions;

public class RenderOptionsHelper {
  public static RenderOptions RENDER_OPTIONS_PIE_ICON = new RenderOptions(60, 60, RenderOptions.CHART_TYPE_MULTIPLE_PIE, "", false,
      "", "", RenderOptions.ORIENTATION_VERTICAL);
  public static RenderOptions RENDER_OPTIONS_SPIDER_ICON = new RenderOptions(60, 60, RenderOptions.CHART_TYPE_SPIDER, "", false,
      "", "", RenderOptions.ORIENTATION_VERTICAL);
  public static RenderOptions RENDER_OPTIONS_BAR_ICON = new RenderOptions(60, 60, RenderOptions.CHART_TYPE_BAR_CHART, "", false,
      "", "", RenderOptions.ORIENTATION_VERTICAL);
  public static RenderOptions RENDER_OPTIONS_METER_ICON = new RenderOptions(60, 60, RenderOptions.CHART_TYPE_METER_CHART, "", false,
      "", "", RenderOptions.ORIENTATION_VERTICAL);
  
  public static RenderOptions RENDER_OPTIONS_PIE_500 = new RenderOptions(500, 500, RenderOptions.CHART_TYPE_MULTIPLE_PIE, "", true,
      "", "", RenderOptions.ORIENTATION_VERTICAL);
  public static RenderOptions RENDER_OPTIONS_SPIDER_500 = new RenderOptions(500, 500, RenderOptions.CHART_TYPE_SPIDER, "", true,
      "", "", RenderOptions.ORIENTATION_VERTICAL);
  public static RenderOptions RENDER_OPTIONS_BAR_3D_500 = new RenderOptions(500, 500, RenderOptions.CHART_TYPE_BAR_CHART_3D, "", true,
      "", "", RenderOptions.ORIENTATION_VERTICAL);
  public static RenderOptions RENDER_OPTIONS_METER_500 = new RenderOptions(500, 500, RenderOptions.CHART_TYPE_METER_CHART, "", false,
      "", "", RenderOptions.ORIENTATION_VERTICAL);
  

}
