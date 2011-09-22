package org.romaframework.module.chart.jfreechart.view.domain.chartviewer;

import java.util.ArrayList;
import java.util.List;

import org.romaframework.module.chart.jfreechart.domain.ChartRenderer;
import org.romaframework.module.chart.jfreechart.domain.ChartRepresentable;
import org.romaframework.module.chart.jfreechart.domain.EditableRenderOptions;
import org.romaframework.module.chart.jfreechart.domain.MeterChartRendererImpl;
import org.romaframework.module.chart.jfreechart.domain.RenderOptions;

public class MeterChart implements ChartRepresentable {

  private List<MeterChartRange> ranges;
  private Double value;

  private EditableRenderOptions options;
  
  public MeterChart(){
    ranges = new ArrayList<MeterChartRange>();
    value = 0d;
    options = new EditableRenderOptions(RenderOptions.newInstance());
    options.setChartType(RenderOptions.CHART_TYPE_METER_CHART);
  }
  public ChartRenderer generateGraph() {
    MeterChartRendererImpl renderer = new MeterChartRendererImpl();
    renderer.setRanges(ranges);
    renderer.setValue(value);
    return renderer;
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
