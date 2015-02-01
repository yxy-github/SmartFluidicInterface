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
  private final ITrace2D[] traces = createITraces();

  public Chart() {
    chart.setPreferredSize(new Dimension(600, 400));
    addITracesToChart(traces);
    initChartColours();

    // Set a number formatter to get rid of the unnecessary ".0" prefixes for the X-Axis:
    final NumberFormat format = new DecimalFormat("#");

    initAxisX(format);
    initAxisY(format);
    add(chart);
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

  private void addITracesToChart(final ITrace2D[] traces) {
    for (final ITrace2D trace : traces) {
      chart.addTrace(trace);
    }
  }

  private void initChartColours() {
    chart.setBackground(Color.WHITE);
    chart.setForeground(Color.BLACK);
    chart.setGridColor(Color.BLACK);
  }

  private void initAxisX(final NumberFormat format) {
    final IAxis axisX = chart.getAxisX();
    axisX.getAxisTitle().setTitle("Time");
    axisX.setPaintGrid(false);
    format.setMaximumIntegerDigits(3);
    axisX.setFormatter(new LabelFormatterNumber(format));
  }

  private void initAxisY(final NumberFormat format) {
    final IAxis axisY = chart.getAxisY();
    axisY.getAxisTitle().setTitle("Voltage");
    axisY.setPaintGrid(true);
    axisY.setFormatter(new LabelFormatterNumber(format));
    axisY.setRangePolicy(new RangePolicyFixedViewport(new Range(2, 4.5)));
  }

  public ITrace2D[] getITraces() {
    return traces;
  }

}
