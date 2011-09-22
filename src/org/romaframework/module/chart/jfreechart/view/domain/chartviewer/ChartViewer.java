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
package org.romaframework.module.chart.jfreechart.view.domain.chartviewer;

import java.util.Collection;
import java.util.Map;

import org.romaframework.aspect.core.annotation.AnnotationConstants;
import org.romaframework.aspect.core.annotation.CoreClass;
import org.romaframework.aspect.view.ViewConstants;
import org.romaframework.aspect.view.annotation.ViewAction;
import org.romaframework.aspect.view.annotation.ViewField;
import org.romaframework.core.Roma;
import org.romaframework.core.config.Refreshable;
import org.romaframework.module.chart.jfreechart.domain.ChartRenderer;
import org.romaframework.module.chart.jfreechart.domain.ChartRepresentable;
import org.romaframework.module.chart.jfreechart.domain.EditableRenderOptions;
import org.romaframework.module.chart.jfreechart.helper.ChartHelper;
import org.romaframework.module.chart.jfreechart.view.domain.renderoptions.RenderOptionsMain;

/**
 * this class can represent charts allowing you to modify
 * the chart structure (dimension, chart type, orientation etc.) 
 * through a simple user interface
 * @author Luigi Dell'Aquila (luigi.dellaquila@assetdata.it)
 */
@CoreClass(orderFields = "options chart")
public class ChartViewer implements Refreshable {

  @ViewField(label = "", render = ViewConstants.RENDER_CHART)
  private ChartRenderer chart;
  @ViewField(label = "", render=ViewConstants.RENDER_OBJECTEMBEDDED)
  private RenderOptionsMain options;
  
  /**
   * builds an empty chart helper
   */
  public ChartViewer(){
    
  }
  
  /**
   * builds a chart helper creating a chart from the given object
   * @param obj the object to be represented as a chart
   */
  public ChartViewer(Object obj){
    setObjectToRender(obj);
  }
  
  public void apply(){
    chart.setOptions(options.getEntity());
    refresh();
  }
  
  /**
   * changes the object to be rendered as a chart
   * @param obj the object to be rendered as a chart
   */
  public void setObjectToRender(Object obj){
    if(obj instanceof ChartRenderer){
      chart = (ChartRenderer)obj; 
    }else if (obj instanceof ChartRepresentable){
      chart = ((ChartRepresentable)obj).generateGraph();
    }else if (obj instanceof Collection<?>){
      try{
        chart = ChartHelper.generateChartRenderer((Collection<?>)obj);
      }catch(Exception e){
        //TODO
      }
    }else if (obj instanceof Map<?, ?>){
      try{
        chart = ChartHelper.generateChartRenderer((Map<?, ?>)obj);
      }catch(Exception e){
        //TODO
      }
    }
    if(chart!=null){
      this.options = new RenderOptionsMain(new EditableRenderOptions(chart.getOptions()));
      this.options.setTypes(chart.getSupportedChartTypes());
    }
    refresh();
  }

  /**
   * returns the object that is being rendered as a chart
   * @return the object that is being rendered as a chart
   */
  public ChartRenderer getChart() {
    return chart;
  }

  @ViewAction(visible = AnnotationConstants.FALSE)
  public void refresh() {
  	Roma.fieldChanged(this, "options");
  	Roma.fieldChanged(this, "chart");
  }

  public RenderOptionsMain getOptions() {
    return options;
  }
  
  
}

