package org.romaframework.module.chart.jfreechart.domain;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jfree.data.general.DefaultValueDataset;
import org.jfree.data.general.ValueDataset;
import org.romaframework.module.chart.jfreechart.charttypes.ChartTypeRenderer;
import org.romaframework.module.chart.jfreechart.charttypes.MeterTypeRenderer;
import org.romaframework.module.chart.jfreechart.helper.RenderOptionsHelper;
import org.romaframework.module.chart.jfreechart.view.domain.chartviewer.MeterChartRange;

public class MeterChartRendererImpl implements ChartRenderer {
	RenderOptions																		options				= RenderOptionsHelper.RENDER_OPTIONS_METER_ICON;
	protected List<MeterChartRange>									ranges				= new ArrayList<MeterChartRange>();
	protected Double																value;

	protected static Map<String, ChartTypeRenderer>	typeRenderers	= new LinkedHashMap<String, ChartTypeRenderer>();
	static {
		typeRenderers.put(RenderOptions.CHART_TYPE_METER_CHART, new MeterTypeRenderer());
	}

	public void clear() {
		ranges.clear();
	}

	public void render(RenderOptions options, OutputStream outputStream) {
		ValueDataset dataset = new DefaultValueDataset(value);
		MeterTypeRenderer renderer = (MeterTypeRenderer) typeRenderers.get(options.getChartType());
		renderer.render(ranges, dataset, options, outputStream);
	}

	public String[] getSupportedChartTypes() {
		return typeRenderers.keySet().toArray(new String[0]);
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

	public List<MeterChartRange> getRanges() {
		return ranges;
	}

	public void setRanges(List<MeterChartRange> ranges) {
		this.ranges = ranges;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

}
