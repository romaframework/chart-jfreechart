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

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.Dataset;
import org.romaframework.module.chart.jfreechart.domain.RenderOptions;
import org.romaframework.module.chart.jfreechart.helper.ChartHelper;

/**
 * provides the logic to represent a dataset as a stacked bar chart
 * @author Luigi Dell'Aquila
 *
 */
public class StackedBarChartTypeRenderer  implements ChartTypeRenderer{


	public void render(Dataset dataset, RenderOptions options, OutputStream outputStream) {
		JFreeChart chart = ChartFactory.createStackedBarChart(options.getChartTitle(), options.getXLabel(), options.getYLabel(), (CategoryDataset) dataset, options.getOrientation(), options.isShowLegend(), false, false);
		ChartHelper.setLabelOrientation(chart, options);
		try{
			ChartUtilities.writeChartAsPNG(outputStream, chart, options.getWidth(), options.getHeight());
		}catch(Exception e){
			e.printStackTrace();//TODO
		}
	}


}