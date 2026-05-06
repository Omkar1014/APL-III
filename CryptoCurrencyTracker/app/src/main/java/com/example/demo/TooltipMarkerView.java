package com.example.demo;

import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

public class TooltipMarkerView extends MarkerView {

    private final TextView tvContent;

    public TooltipMarkerView(Context context, int layoutResource) {
        super(context, layoutResource);
        tvContent = findViewById(R.id.tvContent);
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        // Customize the content shown in the tooltip
        tvContent.setText(String.format("Value: %.2f", e.getY()));
        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        // Center the marker horizontally, show above the point
        return new MPPointF(-(getWidth() / 2f), -getHeight());
    }
}
