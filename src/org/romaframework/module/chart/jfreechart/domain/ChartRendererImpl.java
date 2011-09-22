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
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.DefaultXYDataset;
import org.romaframework.core.domain.type.Pair;
import org.romaframework.module.chart.jfreechart.charttypes.AreaTypeRenderer;
import org.romaframework.module.chart.jfreechart.charttypes.BarChart3DTypeRenderer;
import org.romaframework.module.chart.jfreechart.charttypes.BarChartTypeRenderer;
import org.romaframework.module.chart.jfreechart.charttypes.ChartTypeRenderer;
import org.romaframework.module.chart.jfreechart.charttypes.LineChart3DTypeRenderer;
import org.romaframework.module.chart.jfreechart.charttypes.LineChartTypeRenderer;
import org.romaframework.module.chart.jfreechart.charttypes.MultiplePieChart3DTypeRenderer;
import org.romaframework.module.chart.jfreechart.charttypes.MultiplePieChartTypeRenderer;
import org.romaframework.module.chart.jfreechart.charttypes.SpiderTypeRenderer;
import org.romaframework.module.chart.jfreechart.charttypes.StackedAreaChartTypeRenderer;
import org.romaframework.module.chart.jfreechart.charttypes.StackedBarChart3DTypeRenderer;
import org.romaframework.module.chart.jfreechart.charttypes.StackedBarChartTypeRenderer;
import org.romaframework.module.chart.jfreechart.charttypes.XYLineChartRender;
import org.romaframework.module.chart.jfreechart.helper.RenderOptionsHelper;

/**
 * this implementation of {@link ChartRenderer} is able to render 2 and 3 dimensional charts
 * 
 */
public class ChartRendererImpl implements ChartRenderer {
	RenderOptions																						options				= RenderOptionsHelper.RENDER_OPTIONS_BAR_ICON;
	private Map<Pair<Comparable<?>, Comparable<?>>, Number>	values				= new LinkedHashMap<Pair<Comparable<?>, Comparable<?>>, Number>();

	private static Map<String, ChartTypeRenderer>						typeRenderers	= new LinkedHashMap<String, ChartTypeRenderer>();
	static {
		typeRenderers.put(RenderOptions.CHART_TYPE_AREA, new AreaTypeRenderer());
		typeRenderers.put(RenderOptions.CHART_TYPE_BAR_CHART, new BarChartTypeRenderer());
		typeRenderers.put(RenderOptions.CHART_TYPE_BAR_CHART_3D, new BarChart3DTypeRenderer());
		typeRenderers.put(RenderOptions.CHART_TYPE_LINE, new LineChartTypeRenderer());
		typeRenderers.put(RenderOptions.CHART_TYPE_LINE_3D, new LineChart3DTypeRenderer());
		typeRenderers.put(RenderOptions.CHART_TYPE_MULTIPLE_PIE, new MultiplePieChartTypeRenderer());
		typeRenderers.put(RenderOptions.CHART_TYPE_MULTIPLE_PIE_3D, new MultiplePieChart3DTypeRenderer());
		typeRenderers.put(RenderOptions.CHART_TYPE_SPIDER, new SpiderTypeRenderer());
		typeRenderers.put(RenderOptions.CHART_TYPE_STACKED_AREA, new StackedAreaChartTypeRenderer());
		typeRenderers.put(RenderOptions.CHART_TYPE_STACKED_BAR, new StackedBarChartTypeRenderer());
		typeRenderers.put(RenderOptions.CHART_TYPE_STACKED_BAR_3D, new StackedBarChart3DTypeRenderer());
		typeRenderers.put(RenderOptions.CHART_TYPE_XYLINE, new XYLineChartRender());
	}

	public void clear() {
		values.clear();
	}

	/**
	 * adds a data point to the chart
	 * 
	 * @param x
	 *          the x coordinate of the point in the space of the graph
	 * @param y
	 *          the y coordinate of the point in the space of the graph
	 * @param value
	 *          the value of the point
	 */
	public void setPoint(Comparable<?> x, Comparable<?> y, Number value) {
		values.put(new Pair<Comparable<?>, Comparable<?>>(x, y), value);
	}

	/**
	 * adds a set of points to the chart
	 * 
	 * @param points
	 */
	public void setPoints(Map<Comparable<?>, Map<Comparable<?>, Number>> points) {
		for (Comparable<?> keyX : points.keySet()) {
			Map<Comparable<?>, Number> tmp = points.get(keyX);
			for (Comparable<?> keyY : tmp.keySet()) {
				setPoint(keyX, keyY, tmp.get(keyY));
			}
		}
	}

	/**
	 * adds a point on a 2d chart
	 * 
	 * @param key
	 *          the x of the point
	 * @param value
	 *          the value of the point
	 */
	public void setSingleDimensionPoint(Comparable<?> key, Number value) {
		setPoint("", key, value);
	}

	/**
	 * adds a set of points to the chart
	 * 
	 * @param points
	 */
	public void setSingleDimensionPoints(Map<Comparable<?>, Number> points) {
		for (Comparable<?> key : points.keySet()) {
			setSingleDimensionPoint(key, points.get(key));
		}
	}

	public void render(RenderOptions options, OutputStream outputStream) {
		if (RenderOptions.CHART_TYPE_XYLINE.equals(options.getChartType())) {
			DefaultXYDataset dataset = new DefaultXYDataset();
			addSeries(dataset);
			ChartTypeRenderer renderer = typeRenderers.get(options.getChartType());
			renderer.render(dataset, options, outputStream);
		} else {
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			for (Map.Entry<Pair<Comparable<?>, Comparable<?>>, Number> entry : values.entrySet()) {
				dataset.setValue(entry.getValue(), entry.getKey().getKey(), entry.getKey().getValue());
			}
			ChartTypeRenderer renderer = typeRenderers.get(options.getChartType());
			renderer.render(dataset, options, outputStream);
		}
	}

	private void addSeries(DefaultXYDataset dataset) {

		Map<String, List<Pair<Number, Number>>> series = new LinkedHashMap<String, List<Pair<Number, Number>>>();
		// Create The series
		for (Map.Entry<Pair<Comparable<?>, Comparable<?>>, Number> entry : values.entrySet()) {

			Comparable<?> seriesKey = entry.getKey().getKey();
			List<Pair<Number, Number>> seriesValues = series.get(seriesKey);
			if (seriesValues == null) {
				seriesValues = new LinkedList<Pair<Number, Number>>();
				series.put(seriesKey.toString(), seriesValues);
			}

			seriesValues.add(new Pair<Number, Number>((Number) entry.getKey().getValue(), entry.getValue()));
		}

		for (Map.Entry<String, List<Pair<Number, Number>>> entry : series.entrySet()) {
			double[][] graphValues = new double[2][entry.getValue().size()];
			for (int i = 0; i < entry.getValue().size(); i++) {

				graphValues[0][i] = entry.getValue().get(i).getKey().doubleValue();
				graphValues[1][i] = entry.getValue().get(i).getValue().doubleValue();
			}

			dataset.addSeries(entry.getKey(), graphValues);
		}

	}

	public String[] getSupportedChartTypes() {
		return typeRenderers.keySet().toArray(new String[0]);
	}

	/**
	 * Allow to register custom renders.
	 * 
	 * @param iName
	 * @param iChartRenderImpl
	 */
	public static void registerCustomChartType(final String iName, final ChartTypeRenderer iChartRenderImpl) {
		typeRenderers.put(iName, iChartRenderImpl);
	}

	public void setOptions(RenderOptions options) {
		this.options = options;
	}

	public RenderOptions getOptions() {
		return options;
	}

	public void render(OutputStream outputStream) throws IOException {
		this.render(options, outputStream);
	}

	@Override
	public String toString() {
		return options != null && options.getChartTitle() != null ? options.getChartTitle() : "No chart title";
	}
}
