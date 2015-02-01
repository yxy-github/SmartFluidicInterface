package com.smartfluidicinterface.view;

import info.monitorenter.gui.chart.Chart2D;
import info.monitorenter.gui.chart.IAxis;
import info.monitorenter.gui.chart.ITrace2D;
import info.monitorenter.gui.chart.labelformatters.LabelFormatterNumber;
import info.monitorenter.gui.chart.rangepolicies.RangePolicyFixedViewport;
import info.monitorenter.gui.chart.traces.Trace2DLtd;
import info.monitorenter.util.Range;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public final class Chart extends JPanel {
  private final Chart2D chart = new Chart2D();

  public Chart() {
    chart.setPreferredSize(new Dimension(600, 400));

    final ITrace2D[] traces = createITraces();
    addITracesToChart(traces);
    initChartColours();

    // Set a number formatter to get rid of the unnecessary ".0" prefixes for the X-Axis:
    final NumberFormat format = new DecimalFormat("#");

    initAxisX(format);
    initAxisY(format);
    add(chart);
  }

  private void initAxisY(final NumberFormat format) {
    final IAxis axisY = chart.getAxisY();
    axisY.getAxisTitle().setTitle("Voltage");
    axisY.setPaintGrid(true);
    axisY.setFormatter(new LabelFormatterNumber(format));
    axisY.setRangePolicy(new RangePolicyFixedViewport(new Range(0, 5)));
    //axisX.setRangePolicy(new RangePolicyMinimumViewport(new Range(0, +10)));
    //axisX.setRangePolicy(new RangePolicyFixedViewport(new Range(-10, 10)));
  }

  private void initAxisX(final NumberFormat format) {
    final IAxis axisX = chart.getAxisX();
    axisX.getAxisTitle().setTitle("Time");
    axisX.setPaintGrid(false);
    // Important!
    // Or it will allow more than 100 integer digits and rendering will be
    // confused.
    // See the comment for java.text.DecimalFormat#applyPattern(String)
    format.setMaximumIntegerDigits(3);
    axisX.setFormatter(new LabelFormatterNumber(format));
    //axisX.setRange(new Range(-10, 10));
  }

  private void initChartColours() {
    chart.setBackground(Color.WHITE);
    chart.setForeground(Color.BLACK);
    chart.setGridColor(Color.BLACK);
  }

  private void addITracesToChart(final ITrace2D[] traces) {
    for (final ITrace2D trace : traces) {
      chart.addTrace(trace);
    }
  }

  private static ITrace2D[] createITraces() {
    final ITrace2D[] traces = new ITrace2D[4];
    traces[0] = createITrace("Channel 0", Color.BLUE);
    traces[1] = createITrace("Channel 1", Color.RED);
    traces[2] = createITrace("Channel 2", Color.GREEN);
    traces[3] = createITrace("Channel 3", Color.BLACK);
    return traces;
  }

  private static ITrace2D createITrace(final String name, final Color color) {
    final ITrace2D trace = new Trace2DLtd(100);
    trace.setColor(color);
    trace.setName(name);
    return trace;
  }


}
