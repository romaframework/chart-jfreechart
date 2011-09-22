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

import org.jfree.chart.plot.PlotOrientation;

public class EditableRenderOptions extends RenderOptions{

  public EditableRenderOptions(){
    
  }
  
  public EditableRenderOptions(RenderOptions model){
    this.width=model.width;
    this.height=model.height;
    this.chartType=model.chartType;
    this.chartTitle=model.chartTitle;
    this.showLegend=model.showLegend;
    this.xLabel=model.xLabel;
    this.yLabel=model.yLabel;
    this.orientation=model.orientation;
  }
  
  public void setOrientation(PlotOrientation orientation) {
    this.orientation = orientation;
  }

  public void setXLabel(String label) {
    xLabel = label;
  }

  public void setYLabel(String label) {
    yLabel = label;
  }

  public void setChartTitle(String chartTitle) {
    this.chartTitle = chartTitle;
  }

  public void setShowLegend(boolean showLegend) {
    this.showLegend = showLegend;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public void setChartType(String chartType) {
    this.chartType = chartType;
  }
  
	public void setLabelOrientation(LabelOrietation labelOrientation) {
		this.labelOrientation = labelOrientation;
	}
  
  public static EditableRenderOptions newInstance(){
    EditableRenderOptions options = new EditableRenderOptions();
    options.chartTitle="";
    options.chartType=RenderOptions.CHART_TYPE_BAR_CHART;
    options.height=200;
    options.orientation=ORIENTATION_VERTICAL;
    options.showLegend=false;
    options.xLabel="";
    options.yLabel="";
    options.width=200;
    return options;
  }
}
