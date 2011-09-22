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
package org.romaframework.module.chart.jfreechart.charttypes;

import java.io.OutputStream;
import java.util.List;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.MeterInterval;
import org.jfree.chart.plot.MeterPlot;
import org.jfree.data.Range;
import org.jfree.data.general.Dataset;
import org.jfree.data.general.ValueDataset;
import org.romaframework.module.chart.jfreechart.domain.RenderOptions;
import org.romaframework.module.chart.jfreechart.helper.ChartHelper;
import org.romaframework.module.chart.jfreechart.view.domain.chartviewer.MeterChartRange;

/**
 * provides the logic to represent a dataset as an 3D bar chart
 * @author Luigi Dell'Aquila
 *
 */
public class MeterTypeRenderer implements ChartTypeRenderer{


	public void render(Dataset dataset, RenderOptions options, OutputStream outputStream) {
	  throw new UnsupportedOperationException("invalid dataset for Meter chart");
	}
	
	public void render(List<MeterChartRange> ranges, Dataset dataset, RenderOptions options, OutputStream outputStream) {
      MeterPlot plot = new MeterPlot();
      for(MeterChartRange range:ranges){
        plot.addInterval(new MeterInterval(range.getLabel(), new Range(range.getMin(), range.getMax())));
      }
      plot.setDataset((ValueDataset)dataset);
      JFreeChart chart = new JFreeChart(options.getChartTitle(), plot);
      ChartHelper.setLabelOrientation(chart, options);
        try{
            ChartUtilities.writeChartAsPNG(outputStream, chart, options.getWidth(), options.getHeight());
        }catch(Exception e){
            e.printStackTrace();
        }
    }


}