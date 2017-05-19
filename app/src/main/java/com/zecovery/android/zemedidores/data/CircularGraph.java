package com.zecovery.android.zemedidores.data;

import android.graphics.Color;

import com.hookedonplay.decoviewlib.DecoView;
import com.hookedonplay.decoviewlib.charts.SeriesItem;
import com.hookedonplay.decoviewlib.events.DecoEvent;

/**
 * Created by fbarrios80 on 16-05-17.
 */

public class CircularGraph {

    public void graph(DecoView view, int value) {
        view.addSeries(new SeriesItem.Builder(Color.argb(255, 218, 218, 218))
                .setRange(0, 100, 100)
                .setInitialVisibility(false)
                .setLineWidth(12f)
                .build());

        SeriesItem seriesItem1 = new SeriesItem.Builder(Color.argb(255, 64, 196, 0))
                .setRange(0, 100, 0)
                .setLineWidth(12f)
                .setSpinDuration(500)
                .build();

        int series1Index = view.addSeries(seriesItem1);

        view.addEvent(new DecoEvent.Builder(DecoEvent.EventType.EVENT_SHOW, true)
                .setDelay(200)
                .setDuration(500)
                .build());

        view.addEvent(new DecoEvent.Builder(value)
                .setIndex(series1Index)
                .setDelay(1000)
                .build());

    }
}
