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

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Map;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.romaframework.module.chart.jfreechart.domain.ChartRenderer;
import org.romaframework.module.chart.jfreechart.domain.ChartRendererImpl;
import org.romaframework.module.chart.jfreechart.domain.RenderOptions;
import org.romaframework.module.chart.jfreechart.excepton.InvalidDataFormatException;

/**
 * provides methods to render collections as charts
 * 
 * @author Luigi Dell'Aquila (luigi.dellaquila@assetdata.it)
 * 
 */
public class ChartHelper {

	/**
	 * renders a collection as a chart
	 * 
	 * @param coll
	 *          the collection (must be a Collection&lt;Number&gt;, or a Collection&lt;Collection&lt;Number&gt;&gt; )
	 * @param stream
	 *          the stream where the chart has to be written
	 * @throws InvalidDataFormatException
	 * @throws IOException
	 */
	public static void render(Collection<?> coll, OutputStream stream) throws InvalidDataFormatException, IOException {
		render(coll, RenderOptionsHelper.RENDER_OPTIONS_BAR_3D_500, stream);
	}

	/**
	 * renders a collection as a chart
	 * 
	 * @param coll
	 *          the collection (must be a Collection&lt;Number&gt;, or a Collection&lt;Collection&lt;Number&gt;&gt; )
	 * @param options
	 *          the options about how to render the chart
	 * @param stream
	 *          the stream where the chart has to be written
	 * @throws InvalidDataFormatException
	 * @throws IOException
	 */
	public static void render(Collection<?> coll, RenderOptions options, OutputStream stream) throws InvalidDataFormatException,
			IOException {
		ChartRenderer renderer = generateChartRenderer(coll);
		renderer.render(options, stream);
	}

	/**
	 * renders a Map as a chart
	 * 
	 * @param map
	 *          the map to be rendered as a chart (must be a Map&lt;Comparable, Number&gt;, or a Map&lt;Comparable, Map&lt;Comparable,
	 *          Number&gt;&gt; )
	 * @param stream
	 *          the stream where the chart has to be written
	 * @throws InvalidDataFormatException
	 * @throws IOException
	 */
	public static void render(Map<?, ?> map, OutputStream stream) throws InvalidDataFormatException, IOException {
		render(map, RenderOptionsHelper.RENDER_OPTIONS_BAR_3D_500, stream);
	}

	/**
	 * renders a Map as a chart
	 * 
	 * @param map
	 *          the map to be rendered as a chart (must be a Map&lt;Comparable, Number&gt;, or a Map&lt;Comparable, Map&lt;Comparable,
	 *          Number&gt;&gt; )
	 * @param options
	 *          the options about how to render the chart
	 * @param stream
	 *          the stream where the chart has to be written
	 * @throws InvalidDataFormatException
	 * @throws IOException
	 */
	public static void render(Map<?, ?> map, RenderOptions options, OutputStream stream) throws InvalidDataFormatException,
			IOException {
		ChartRenderer renderer = generateChartRenderer(map);
		renderer.render(options, stream);
	}

	/**
	 * creates a ChartRenderer object from a Collection
	 * 
	 * @param coll
	 *          the collection that must be transformed in a ChartRenderer (must be a Collection&lt;Number&gt;, or a
	 *          Collection&lt;Collection&lt;Number&gt;&gt; )
	 * @return the ChartRenderer representation of the collection
	 * @throws InvalidDataFormatException
	 */
	public static ChartRenderer generateChartRenderer(Collection<?> coll) throws InvalidDataFormatException {
		ChartRendererImpl renderer = new ChartRendererImpl();
		Object item = null;
		for (Object tmpItem : coll) {
			item = tmpItem;
			if (item != null)
				break;
		}

		if (item == null)
			throw new InvalidDataFormatException("invalid data set");

		if (item instanceof Number) {
			fillWithNumbers((Collection<Number>) coll, renderer);
		} else if (item instanceof Collection) {
			Collection<?> collItem = (Collection<?>) item;
			for (Object subItem : collItem) {
				if (subItem instanceof Number) {
					fillWithBiDimItems((Collection<Collection<Number>>) coll, renderer);
					break;
				} else
					throw new InvalidDataFormatException("invalid data set");
			}
		} else
			throw new InvalidDataFormatException("invalid data set");

		return renderer;
	}

	/**
	 * creates a ChartRenderer object from a Map
	 * 
	 * @param map
	 *          the Map that must be transformed in a ChartRenderer (must be a Map&lt;Comparable, Number&gt;, or a Map&lt;Comparable,
	 *          Map&lt;Comparable, Number&gt;&gt; )
	 * @return the ChartRenderer representation of the Map
	 * @throws InvalidDataFormatException
	 */
	public static ChartRenderer generateChartRenderer(Map<?, ?> map) throws InvalidDataFormatException {
		if (map == null)
			throw new InvalidDataFormatException("invalid data set");
		ChartRendererImpl renderer = new ChartRendererImpl();
		Object value = null;

		for (Object tmpValue : map.values()) {
			value = tmpValue;
			if (value != null)
				break;
		}
		if (value == null)
			throw new InvalidDataFormatException("invalid data set");

		if (value instanceof Number) {
			fillWithOneLabeledItems((Map<Object, Number>) map, renderer);
		} else if (value instanceof Map) {
			Map mapValue = (Map) value;
			for (Object subvalue : mapValue.values()) {
				if (subvalue instanceof Number) {
					fillWithTwoLabeledItems((Map<Object, Map<Object, Number>>) map, renderer);
					break;
				} else
					throw new InvalidDataFormatException("invalid data set");
			}
		} else
			throw new InvalidDataFormatException("invalid data set");

		return renderer;
	}

	private static void fillWithNumbers(Collection<Number> coll, ChartRendererImpl renderer) {
		int i = 0;
		for (Number n : coll) {
			renderer.setSingleDimensionPoint(i++, n);
		}
	}

	private static void fillWithBiDimItems(Collection<Collection<Number>> coll, ChartRendererImpl renderer) {
		int x = 0;
		for (Collection<Number> subColl : coll) {
			int y = 0;
			for (Number n : subColl) {
				renderer.setPoint(x, y++, n);
			}
			x++;
		}
	}

	private static void fillWithOneLabeledItems(Map<Object, Number> map, ChartRendererImpl renderer) {
		for (Map.Entry<Object, Number> entry : map.entrySet()) {
			renderer.setSingleDimensionPoint(entry.getKey().toString(), entry.getValue());
		}
	}

	private static void fillWithTwoLabeledItems(Map<Object, Map<Object, Number>> map, ChartRendererImpl renderer) {
		for (Map.Entry<Object, Map<Object, Number>> entry : map.entrySet()) {
			for (Map.Entry<Object, Number> subEntry : entry.getValue().entrySet()) {
				renderer.setPoint(entry.getKey() == null ? "" : entry.getKey().toString(), subEntry.getKey() == null ? "" : subEntry
						.getKey().toString(), subEntry.getValue());
			}
		}
	}

	public static void setLabelOrientation(JFreeChart chart, RenderOptions options) {
		if (options.getLabelOrientation() != null) {
			CategoryLabelPositions pos = null;
			switch (options.getLabelOrientation()) {
			case DOWN_45:
				pos = CategoryLabelPositions.DOWN_45;
				break;
			case DOWN_90:
				pos = CategoryLabelPositions.DOWN_90;
				break;
			case UP_45:
				pos = CategoryLabelPositions.UP_45;
				break;
			case UP_90:
				pos = CategoryLabelPositions.UP_90;
				break;
			case STANDART:
				pos = CategoryLabelPositions.STANDARD;
				break;

			}
			final CategoryPlot plot = chart.getCategoryPlot();
			CategoryAxis domainAxis = plot.getDomainAxis();
			domainAxis.setMaximumCategoryLabelLines(3);
			domainAxis.setCategoryLabelPositions(pos);
		}
	}

}
