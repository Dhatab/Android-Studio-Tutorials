package com.example.user.recyclerviewtutorial.graph;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.example.user.recyclerviewtutorial.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.IMarker;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.DefaultAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.MPPointF;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;


public class graph extends AppCompatActivity {

    protected static final String TAG = "graph";
    public String url, open, time, ticker;
    private LineChart mChart;
    private int mFillColor = Color.argb(150, 51, 181, 229);
    ArrayList<String> xValues = new ArrayList<String>();
    ArrayList<Entry> yVals1 = new ArrayList<Entry>();
    ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
    private Handler handler = new Handler(Looper.getMainLooper());
    private String jsonResponse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        mChart = (LineChart) findViewById(R.id.chart1);
        mChart.setNoDataText("Getting Data From Server");
        mChart.setNoDataTextColor(Color.BLACK);
        makeChart();


    }

    private void makeChart() {

        StringRequest req = new StringRequest(Request.Method.GET, "https://min-api.cryptocompare.com/data/histoday?fsym=BTC&tsym=USD&limit=60&aggregate=3&e=CCCAGG",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d(TAG, response.toString());

                        try {
                            jsonResponse = "";
                            JSONObject jObject = new JSONObject(response);
                            JSONArray jsonArray = jObject.getJSONArray("Data");
                            for (int i = 0; i <= jsonArray.length(); i++) {

                                JSONObject o = jsonArray.getJSONObject(i);

                                time = o.getString("time");
                                open = o.getString("close");

                                float val = Float.parseFloat(open);

                                yVals1.add(new Entry(i, val));

                                long unixSeconds = Long.parseLong(time);
                                Date date = new Date(unixSeconds * 1000L);
                                SimpleDateFormat sdf = new SimpleDateFormat("dd.MMM");
                                sdf.setTimeZone(TimeZone.getTimeZone("America/New_York"));
                                String formattedDate = sdf.format(date);

                                xValues.add(formattedDate);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        });

        AppController.getInstance().addToRequestQueue(req);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {

                mChart.setBackgroundColor(Color.WHITE);
                mChart.setGridBackgroundColor(Color.WHITE);
                mChart.setDrawGridBackground(true);
                mChart.setDrawBorders(true);

                Legend l = mChart.getLegend();
                l.setEnabled(false);

                XAxis xAxis = mChart.getXAxis();
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                xAxis.setDrawGridLines(false);
                xAxis.setAxisLineColor(Color.BLACK);
                xAxis.setTextColor(Color.BLACK);

                xAxis.setValueFormatter(new DefaultAxisValueFormatter(0) {
                    @Override
                    public String getFormattedValue(float value, AxisBase axis) {
                        return xValues.get((int) value % xValues.size());
                    }

                    @Override
                    public int getDecimalDigits() {
                        return 0;
                    }
                });

                YAxis leftAxis = mChart.getAxisLeft();
                leftAxis.setTextColor(Color.BLACK);
                leftAxis.setDrawAxisLine(true);
                leftAxis.setDrawZeroLine(false);
                leftAxis.setDrawGridLines(true);

                leftAxis.setGridColor(Color.BLUE);
                leftAxis.setAxisLineColor(Color.BLACK);

                mChart.getAxisRight().setEnabled(false);
                LineDataSet set1;

                set1 = new LineDataSet(yVals1, "DataSet 1");

                set1.setAxisDependency(YAxis.AxisDependency.LEFT);
                set1.setColor(Color.parseColor("#50FFEB3B"));
                set1.setDrawCircles(true);
                set1.setLineWidth(1.5f);
                set1.setCircleRadius(4f);
                set1.setCircleColor(Color.RED);
                set1.setFillAlpha(50);
                set1.setDrawFilled(true);
                set1.setFillColor(Color.BLUE);
                set1.setHighLightColor(Color.rgb(244, 117, 117));
                set1.setDrawCircleHole(false);

                dataSets.add(set1);

                LineData datab = new LineData(dataSets);
                datab.setDrawValues(false);

                mChart.setData(datab);
                mChart.setDrawMarkers(true);
                IMarker marker = new YourMarkerView(graph.this,R.layout.custom_marker);
                mChart.setMarker(marker);

            }
        }, 1000);
    }

    public class YourMarkerView extends MarkerView {

        private TextView tvContent;

        public YourMarkerView(Context context, int layoutResource) {
            super(context, layoutResource);

            // find your layout components
            tvContent = (TextView) findViewById(R.id.tvContent);
        }

        // callbacks everytime the MarkerView is redrawn, can be used to update the
        // content (user-interface)
        @Override
        public void refreshContent(Entry e, Highlight highlight) {

            tvContent.setText("$" + e.getY());

            // this will perform necessary layouting
            super.refreshContent(e, highlight);
        }

        private MPPointF mOffset;

        @Override
        public MPPointF getOffset() {

            if(mOffset == null) {
                // center the marker horizontally and vertically
                mOffset = new MPPointF(-(getWidth() / 2), -getHeight());
            }

            return mOffset;
        }
    }
}
